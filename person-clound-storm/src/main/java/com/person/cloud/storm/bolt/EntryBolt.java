package com.person.cloud.storm.bolt;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.person.cloud.jdbc.pojo.Person;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class EntryBolt extends BaseRichBolt {
	private static final long serialVersionUID = 1L;
	private OutputCollector collector;

	public void prepare(@SuppressWarnings("rawtypes") Map stormConf, TopologyContext context,
			OutputCollector collector) {
		this.collector = collector;
	}

	public void execute(Tuple input) {
		String personJson = input.getString(0);
		System.out.println("EntryBolt=" + personJson);
		Person person = JSON.parseObject(personJson, Person.class);
		this.collector.emit(new Values(person));
		collector.ack(input);
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("entry"));

	}
}
