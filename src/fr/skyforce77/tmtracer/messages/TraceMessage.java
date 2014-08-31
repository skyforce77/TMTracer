package fr.skyforce77.tmtracer.messages;

import java.io.Serializable;

public class TraceMessage implements Serializable{

	private static final long serialVersionUID = -5844463373264112423L;

	private int id;
	
	public TraceMessage(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
