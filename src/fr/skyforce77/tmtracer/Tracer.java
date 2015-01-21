package fr.skyforce77.tmtracer;

public class Tracer {
	
	private int x, y;
	private int clickx, clicky, clickprogress;
	private String name;
	
	public Tracer(String name) {
		this.name = name;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getClickX() {
		return clickx;
	}

	public void setClickX(int clickx) {
		this.clickx = clickx;
	}

	public int getClickY() {
		return clicky;
	}

	public void setClickY(int clicky) {
		this.clicky = clicky;
	}

	public int getClickProgress() {
		return clickprogress;
	}

	public void setClickProgress(int clickprogress) {
		this.clickprogress = clickprogress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}