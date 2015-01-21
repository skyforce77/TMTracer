package fr.skyforce77.tmtracer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.UUID;

import javax.swing.ImageIcon;

import fr.skyforce77.towerminer.TowerMiner;
import fr.skyforce77.towerminer.api.Plugin;
import fr.skyforce77.towerminer.api.PluginManager;
import fr.skyforce77.towerminer.api.PluginStatus;
import fr.skyforce77.towerminer.render.RenderRunnable;


public class TMTracer extends Plugin{
	
	public RenderRunnable rendermulti;
	public HashMap<UUID, Tracer> tracers = new HashMap<UUID, Tracer>();
	
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
				for(UUID id : tracers.keySet()) {
					Tracer tracer = tracers.get(id);
					g2d.setFont(TowerMiner.getFont(12));
					FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
					int hgt = metrics.getHeight();
					int adv = metrics.stringWidth(tracer.getName());
					Dimension size = new Dimension(adv + 2, hgt + 2);
					g2d.setColor(new Color(0, 0, 0, 150));
					g2d.fillRect(tracer.getX()+20, tracer.getY(), (int) (4 + size.getWidth()), 16);
					g2d.setColor(Color.WHITE);

					g2d.drawString(tracer.getName(), tracer.getX()+22, tracer.getY()+14);
					g2d.drawImage(TMTracer.getTexture("cursor").getImage(), tracer.getX(), tracer.getY(), 16, 16, null);

					if(tracer.getClickProgress() > 0) {
						g2d.drawOval(tracer.getClickX()-(tracer.getClickProgress()/2), tracer.getClickY()-(tracer.getClickProgress()/2), tracer.getClickProgress(), tracer.getClickProgress());
						tracer.setClickProgress(tracer.getClickProgress()-5);
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
}
