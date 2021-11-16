package com.moraes.service.worstProducer;

public class Worst {
	private String producer;
	private int interval;
	private int previousWin;
	private int followingWin;

	
	
	public Worst(String producer, int interval, int previousWin, int followingWin) {
		super();
		this.producer = producer;
		this.interval = interval;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
	}

	
	public String getProducer() {
		return producer;
	}

	public int getInterval() {
		return interval;
	}

	public int getPreviousWin() {
		return previousWin;
	}

	public int getFollowingWin() {
		return followingWin;
	}

	@Override
	public String toString() {
		return "Worst [producer=" + producer + ", interval=" + interval + ", previousWin=" + previousWin
				+ ", followingWin=" + followingWin + "]";
	}
}
