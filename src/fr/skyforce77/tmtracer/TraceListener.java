package fr.skyforce77.tmtracer;

import fr.skyforce77.tmtracer.messages.MessageType;
import fr.skyforce77.tmtracer.messages.TraceClickMessage;
import fr.skyforce77.tmtracer.messages.TraceMoveMessage;
import fr.skyforce77.towerminer.TowerMiner;
import fr.skyforce77.towerminer.api.EventHandler;
import fr.skyforce77.towerminer.api.TMListener;
import fr.skyforce77.towerminer.api.events.PluginMessageEvent;
import fr.skyforce77.towerminer.api.events.menu.MenuPreChangeEvent;
import fr.skyforce77.towerminer.menus.MultiPlayer;

public class TraceListener extends TMListener{
	
	@EventHandler
	public void onPluginMessage(PluginMessageEvent e) {
		if(e.getPlugin().equals(TMTracer.plugin.getName())) {
			if(e.getType() == MessageType.CLICK_MESSAGE) {
				TraceClickMessage tcm = (TraceClickMessage)e.getData();
				if(TowerMiner.menu instanceof TraceMultiPlayer) {
					TraceMultiPlayer tmp = (TraceMultiPlayer)TowerMiner.menu;
					tmp.setClickX(tcm.getX());
					tmp.setClickY(tcm.getY());
					tmp.setClickProgress(90);
				}
			} else if(e.getType() == MessageType.MOVE_MESSAGE) {
				TraceMoveMessage tcm = (TraceMoveMessage)e.getData();
				if(TowerMiner.menu instanceof TraceMultiPlayer) {
					TraceMultiPlayer tmp = (TraceMultiPlayer)TowerMiner.menu;
					tmp.setOtherX(tcm.getX());
					tmp.setOtherY(tcm.getY());
				}
			}
		}
	}
	
	@EventHandler
	public void onMultiPlayerCreated(MenuPreChangeEvent e) {
		if(e.getNextMenu() instanceof MultiPlayer) {
			e.setNextMenu(new TraceMultiPlayer(((MultiPlayer)e.getNextMenu()).server));
		}
	}

}
