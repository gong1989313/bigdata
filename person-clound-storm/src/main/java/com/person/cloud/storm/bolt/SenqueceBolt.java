package com.person.cloud.storm.bolt;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class SenqueceBolt extends BaseBasicBolt {
	/**
	 * Author gxq
	 * DateTime 20170514
	 */
	private static final long serialVersionUID = 1L;

	public void execute(Tuple input, BasicOutputCollector collector) {
		String word = (String) input.getValue(0);
		//System.out.println("SenqueceBolt=" + word);
		collector.emit(new Values(word));
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("senquece"));
	}
}