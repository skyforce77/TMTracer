package fr.skyforce77.tmtracer;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import fr.skyforce77.tmtracer.messages.MessageType;
import fr.skyforce77.tmtracer.messages.TraceClickMessage;
import fr.skyforce77.tmtracer.messages.TraceMoveMessage;
import fr.skyforce77.towerminer.api.PluginManager;
import fr.skyforce77.towerminer.menus.MultiPlayer;

public class TraceMultiPlayer extends MultiPlayer{
	
	private int otherx, othery;
	private int clickx, clicky, clickprogress;

	public TraceMultiPlayer(boolean server) {
		super(server);
	}
	
	@Override
	public void drawMenu(Graphics2D g2d) {
		super.drawMenu(g2d);
		
		g2d.drawImage(TMTracer.getTexture("cursor").getImage(), otherx, othery, 16, 16, null);
		
		if(clickprogress > 0) {
			g2d.drawOval(clickx-(clickprogress/2), clicky-(clickprogress/2), clickprogress, clickprogress);
			clickprogress-=5;
		}
	}
	
	@Override
	public void onMouseClicked(MouseEvent e) {
		super.onMouseClicked(e);
		
		PluginManager.sendPluginMessage(TMTracer.plugin, MessageType.CLICK_MESSAGE, new TraceClickMessage(e.getX()-2, e.getY()-30, e.getModifiers()));
	}
	
	@Override
	public void onMouseMoved(MouseEvent e) {
		super.onMouseMoved(e);
		
		PluginManager.sendPluginMessage(TMTracer.plugin, MessageType.MOVE_MESSAGE, new TraceMoveMessage(e.getX()-2, e.getY()-30));
	}
	

	public int getOtherX() {
		return otherx;
	}

	public void setOtherX(int otherx) {
		this.otherx = otherx;
	}

	public int getOtherY() {
		return othery;
	}

	public void setOtherY(int othery) {
		this.othery = othery;
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

}
