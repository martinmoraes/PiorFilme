package com.moraes.presenter;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.moraes.service.worstProducer.MinMaxInterval;

@Component
public class MinMaxIntervalToJson {
//	@Autowired
//	private WorstProducer worstProducer;

	public static String translate(MinMaxInterval minMaxInterval) {
		Gson toJson = new Gson();
		return toJson.toJson(minMaxInterval);
		
	}
}
