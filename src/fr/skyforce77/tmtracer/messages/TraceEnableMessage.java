package fr.skyforce77.tmtracer.messages;


public class TraceEnableMessage extends TraceMessage{

	private static final long serialVersionUID = -5844463373264112423L;

	private String name;
	private boolean response;
	
	public TraceEnableMessage(String name, boolean response) {
		super(MessageType.ENABLE_MESSAGE);
		this.name = name;
		this.response = response;
	}
	
	public boolean isResponse() {
		return response;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
