package fr.skyforce77.tmtracer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import fr.skyforce77.tmtracer.messages.MessageType;
import fr.skyforce77.tmtracer.messages.TraceClickMessage;
import fr.skyforce77.tmtracer.messages.TraceMoveMessage;
import fr.skyforce77.towerminer.TowerMiner;
import fr.skyforce77.towerminer.api.PluginManager;
import fr.skyforce77.towerminer.menus.MultiPlayer;

public class TraceMultiPlayer extends MultiPlayer{

	private int otherx, othery;
	private int clickx, clicky, clickprogress;
	private String otherplayer;

	public TraceMultiPlayer(boolean server) {
		super(server);
	}

	@Override
	public void drawMenu(Graphics2D g2d) {
		super.drawMenu(g2d);

		if(otherplayer != null) {
			g2d.setFont(TowerMiner.getFont(12));
			FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
			int hgt = metrics.getHeight();
			int adv = metrics.stringWidth(otherplayer);
			Dimension size = new Dimension(adv + 2, hgt + 2);
			g2d.setColor(new Color(0, 0, 0, 150));
			g2d.fillRect(otherx+20, othery, (int) (4 + size.getWidth()), 16);
			g2d.setColor(Color.WHITE);

			g2d.drawString(otherplayer, otherx+22, othery+14);
			g2d.drawImage(TMTracer.getTexture("cursor").getImage(), otherx, othery, 16, 16, null);

			if(clickprogress > 0) {
				g2d.drawOval(clickx-(clickprogress/2), clicky-(clickprogress/2), clickprogress, clickprogress);
				clickprogress-=5;
			}
		}
	}

	@Override
	public void onMouseClicked(MouseEvent e) {
		super.onMouseClicked(e);

		if(otherplayer != null)
			PluginManager.sendPluginMessage(TMTracer.plugin, MessageType.CLICK_MESSAGE, new TraceClickMessage(e.getX()-2, e.getY()-30, e.getModifiers()));
	}

	@Override
	public void onMouseMoved(MouseEvent e) {
		super.onMouseMoved(e);

		if(otherplayer != null)
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

	public String getOtherPlayer() {
		return otherplayer;
	}

	public void setOtherPlayer(String otherplayer) {
		this.otherplayer = otherplayer;
	}

}
