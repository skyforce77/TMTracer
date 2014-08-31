package fr.skyforce77.tmtracer.messages;


public class TraceClickMessage extends TraceMessage{

	private static final long serialVersionUID = -5844463373264112423L;

	private int x, y, type;
	
	public TraceClickMessage(int x, int y, int type) {
		super(MessageType.CLICK_MESSAGE);
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getType() {
		return type;
	}
}
