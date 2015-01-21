package fr.skyforce77.tmtracer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import fr.skyforce77.towerminer.TowerMiner;
import fr.skyforce77.towerminer.api.Plugin;
import fr.skyforce77.towerminer.api.PluginManager;
import fr.skyforce77.towerminer.api.PluginStatus;
import fr.skyforce77.towerminer.render.RenderRunnable;


public class TMTracer extends Plugin{
	
	private int otherx, othery;
	private int clickx, clicky, clickprogress;
	private String otherplayer;
	public RenderRunnable rendermulti;
	
	public static TMTracer plugin;
	
	@Override
	public boolean isPluginNeededByClient() {
		return false;
	}
	
	@Override
	public PluginStatus onEnable() {
		plugin = this;
		PluginManager.registerListener(new TraceListener());
		rendermulti = new RenderRunnable() {
			public void run(Graphics2D g2d) {
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
			};
		};
		return PluginStatus.OK;
	}
	
	public static ImageIcon getTexture(String texture) {
        ImageIcon image = new ImageIcon(TMTracer.class.getResource("/ressources/textures/" + texture + ".png"));
        return image;
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
