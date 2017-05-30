package com.person.cloud.storm.bolt;

import java.util.Map;

import com.person.cloud.jdbc.dao.PersonDAO;
import com.person.cloud.jdbc.pojo.Person;
import com.person.cloud.storm.utils.SpringBeanUtil;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

public class PersistentBolt extends BaseRichBolt {
	private static final long serialVersionUID = 1L;
	private OutputCollector collector;
	private PersonDAO personDAO;

	public void prepare(@SuppressWarnings("rawtypes") Map stormConf, TopologyContext context,
			OutputCollector collector) {
		this.collector = collector;
		this.personDAO = SpringBeanUtil.getBean("personDAO", PersonDAO.class);
	}

	public void execute(Tuple input) {
		Person person = (Person) input.getValue(0);
		System.out.println("Person:" + person);
		personDAO.persistence(person);
		collector.ack(input);
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("persistent"));
	}
}
