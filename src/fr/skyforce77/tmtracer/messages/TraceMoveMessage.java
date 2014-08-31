package fr.skyforce77.tmtracer.messages;


public class TraceMoveMessage extends TraceMessage{

	private static final long serialVersionUID = -5844463373264112423L;

	private int x, y;
	
	public TraceMoveMessage(int x, int y) {
		super(MessageType.MOVE_MESSAGE);
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
