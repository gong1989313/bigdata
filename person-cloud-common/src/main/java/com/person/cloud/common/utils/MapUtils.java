package com.person.cloud.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class MapUtils {
	public static Map<?, ?> props2Map(Properties props) {
		if (props != null) {
			Set<Entry<Object, Object>> entrySet = props.entrySet();
			Map<Object, Object> map = new HashMap<Object, Object>();
			for (Entry<?, ?> entry : entrySet) {
				if (entry != null) {
					map.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return null;
	}

}
