package com.person.cloud.storm.topology;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.utils.Utils;

import com.person.cloud.storm.bolt.EntryBolt;
import com.person.cloud.storm.bolt.FilterBolt;
import com.person.cloud.storm.bolt.PersistentBolt;
import com.person.cloud.storm.bolt.SenqueceBolt;
import com.person.cloud.storm.scheme.MessageScheme;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.AuthorizationException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.ZkHosts;

public class StormKafkaTopo {
	public static void main(String[] args) {
		BrokerHosts brokerHosts = new ZkHosts("192.168.2.31:2181,192.168.2.32:2181,192.168.2.33:2181");
		SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, "test", "/kafka", "kafkaspout");

		Config conf = new Config();
		Map<String, String> map = new HashMap<String, String>();
		map.put("metadata.broker.list", "192.168.2.31:9092,192.168.2.32:9092,192.168.2.33:9092");
		map.put("acks", "1");
		map.put("serializer.class", "kafka.serializer.StringEncoder");
		map.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		map.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		conf.put("kafka.broker.properties", map);
		conf.put("topic", "topic2");

		spoutConfig.scheme = new SchemeAsMultiScheme(new MessageScheme());

		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("spout", new KafkaSpout(spoutConfig));
		builder.setBolt("senquece", new SenqueceBolt()).shuffleGrouping("spout");
		builder.setBolt("filter", new FilterBolt()).shuffleGrouping("senquece");
		builder.setBolt("entry", new EntryBolt()).shuffleGrouping("filter");
		builder.setBolt("persistent", new PersistentBolt()).shuffleGrouping("entry");

		if (args != null && args.length > 0) {
			// 提交到集群运行
			try {
				StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
			} catch (InvalidTopologyException e) {
				e.printStackTrace();
			} catch (AlreadyAliveException e) {
				e.printStackTrace();
			} catch (AuthorizationException e) {
				e.printStackTrace();
			}
		} else {
			// 本地模式运行
			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("personClond", conf, builder.createTopology());
			Utils.sleep(100000);
			cluster.killTopology("personClond");
			cluster.shutdown();
		}

	}
}
