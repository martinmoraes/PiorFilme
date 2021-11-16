package com.moraes.service.worstProducer;

import java.util.List;

public class MinMaxInterval {
	private List<Worst> Min;
	private List<Worst> Max;

	public MinMaxInterval(List<Worst> min, List<Worst> max) {
		super();
		Min = min;
		Max = max;
	}

	public List<Worst> getMin() {
		return Min;
	}

	public List<Worst> getMax() {
		return Max;
	}
}
