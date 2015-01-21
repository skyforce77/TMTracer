package fr.skyforce77.tmtracer.messages;

import java.io.Serializable;
import java.util.UUID;

import fr.skyforce77.tmtracer.TMTracer;
import fr.skyforce77.towerminer.TowerMiner;

public class TraceMessage implements Serializable{

	private static final long serialVersionUID = -5844463373264112423L;

	private UUID player;
	private String protocol;
	private int id;
	
	public TraceMessage(int id) {
		this.player = TowerMiner.id;
		this.protocol = TMTracer.plugin.getInfos().getValue("protocol");
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public UUID getPlayer() {
		return player;
	}
	
	public String getProtocol() {
		return protocol;
	}
}
