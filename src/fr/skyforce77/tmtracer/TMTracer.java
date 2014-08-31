package fr.skyforce77.tmtracer;
import javax.swing.ImageIcon;

import fr.skyforce77.towerminer.api.Plugin;
import fr.skyforce77.towerminer.api.PluginManager;


public class TMTracer extends Plugin{
	
	public static TMTracer plugin;
	
	@Override
	public boolean isPluginNeededByClient() {
		return false;
	}
	
	@Override
	public void onEnable() {
		plugin = this;
		PluginManager.registerListener(new TraceListener());
	}
	
	public static ImageIcon getTexture(String texture) {
        ImageIcon image = new ImageIcon(TMTracer.class.getResource("/ressources/textures/" + texture + ".png"));
        return image;
    }

}
