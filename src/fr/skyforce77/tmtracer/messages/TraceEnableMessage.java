package fr.skyforce77.tmtracer.messages;


public class TraceEnableMessage extends TraceMessage{

	private static final long serialVersionUID = -5844463373264112423L;

	private String player;
	
	public TraceEnableMessage(String player) {
		super(MessageType.ENABLE_MESSAGE);
		this.player = player;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}
}
