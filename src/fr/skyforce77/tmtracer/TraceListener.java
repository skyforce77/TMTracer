package fr.skyforce77.tmtracer;

import java.awt.Color;

import fr.skyforce77.tmtracer.messages.MessageType;
import fr.skyforce77.tmtracer.messages.TraceClickMessage;
import fr.skyforce77.tmtracer.messages.TraceEnableMessage;
import fr.skyforce77.tmtracer.messages.TraceMoveMessage;
import fr.skyforce77.towerminer.TowerMiner;
import fr.skyforce77.towerminer.api.EventHandler;
import fr.skyforce77.towerminer.api.PluginManager;
import fr.skyforce77.towerminer.api.TMListener;
import fr.skyforce77.towerminer.api.Utils;
import fr.skyforce77.towerminer.api.events.PluginMessageEvent;
import fr.skyforce77.towerminer.api.events.menu.MenuPreChangeEvent;
import fr.skyforce77.towerminer.api.events.menu.MenuUsedEvent;
import fr.skyforce77.towerminer.menus.MultiPlayer;
import fr.skyforce77.towerminer.protocol.chat.ChatMessage;
import fr.skyforce77.towerminer.protocol.chat.ChatModel;

public class TraceListener extends TMListener{
	
	@EventHandler
	public void onPluginMessage(PluginMessageEvent e) {
		if(e.getPlugin().equals(TMTracer.plugin.getName())) {
			if(e.getType() == MessageType.MOVE_MESSAGE) {
				TraceMoveMessage tcm = (TraceMoveMessage)e.getData();
				if(TowerMiner.menu instanceof TraceMultiPlayer) {
					TraceMultiPlayer tmp = (TraceMultiPlayer)TowerMiner.menu;
					tmp.setOtherX(tcm.getX());
					tmp.setOtherY(tcm.getY());
				}
			} else if(e.getType() == MessageType.CLICK_MESSAGE) {
				TraceClickMessage tcm = (TraceClickMessage)e.getData();
				if(TowerMiner.menu instanceof TraceMultiPlayer) {
					TraceMultiPlayer tmp = (TraceMultiPlayer)TowerMiner.menu;
					tmp.setClickX(tcm.getX());
					tmp.setClickY(tcm.getY());
					tmp.setClickProgress(90);
				}
			} else if(e.getType() == MessageType.ENABLE_MESSAGE) {
				TraceEnableMessage tcm = (TraceEnableMessage)e.getData();
				if(TowerMiner.menu instanceof TraceMultiPlayer) {
					TraceMultiPlayer tmp = (TraceMultiPlayer)TowerMiner.menu;
					tmp.setOtherPlayer(tcm.getPlayer());
					ChatModel player = new ChatModel(tcm.getPlayer());
					player.setForegroundColor(Color.CYAN);
					Utils.write(new ChatMessage(player, new ChatModel(" is using TMTracer too!")));
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
	
	@EventHandler
	public void onMultiPlayerUsed(MenuUsedEvent e) {
		if(e.getMenu() instanceof MultiPlayer) {
			PluginManager.sendPluginMessage(TMTracer.plugin, MessageType.ENABLE_MESSAGE, new TraceEnableMessage(TowerMiner.player));
		}
	}

}
