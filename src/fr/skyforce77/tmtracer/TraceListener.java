package fr.skyforce77.tmtracer;

import java.awt.Color;
import java.awt.event.MouseEvent;

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
import fr.skyforce77.towerminer.api.events.menu.MenuRenderEvent;
import fr.skyforce77.towerminer.api.events.menu.MenuUsedEvent;
import fr.skyforce77.towerminer.api.events.menu.interact.mouse.MenuMouseClickedEvent;
import fr.skyforce77.towerminer.api.events.menu.interact.mouse.MenuMouseMovedEvent;
import fr.skyforce77.towerminer.menus.MultiPlayer;
import fr.skyforce77.towerminer.protocol.chat.ChatMessage;
import fr.skyforce77.towerminer.protocol.chat.ChatModel;

public class TraceListener extends TMListener{
	
	@EventHandler
	public void onPluginMessage(PluginMessageEvent e) {
		if(e.getPlugin().equals(TMTracer.plugin.getName())) {
			if(e.getType() == MessageType.MOVE_MESSAGE) {
				TraceMoveMessage tcm = (TraceMoveMessage)e.getData();
				if(TowerMiner.menu instanceof MultiPlayer) {
					TMTracer.plugin.setOtherX(tcm.getX());
					TMTracer.plugin.setOtherY(tcm.getY());
				}
			} else if(e.getType() == MessageType.CLICK_MESSAGE) {
				TraceClickMessage tcm = (TraceClickMessage)e.getData();
				if(TowerMiner.menu instanceof MultiPlayer) {
					TMTracer.plugin.setClickX(tcm.getX());
					TMTracer.plugin.setClickY(tcm.getY());
					TMTracer.plugin.setClickProgress(90);
				}
			} else if(e.getType() == MessageType.ENABLE_MESSAGE) {
				TraceEnableMessage tcm = (TraceEnableMessage)e.getData();
				if(TowerMiner.menu instanceof MultiPlayer) {
					TMTracer.plugin.setOtherPlayer(tcm.getPlayer());
					ChatModel player = new ChatModel(tcm.getPlayer());
					player.setForegroundColor(Color.CYAN);
					if(!tcm.isResponse()) {
						PluginManager.sendPluginMessage(TMTracer.plugin, MessageType.ENABLE_MESSAGE, new TraceEnableMessage(TowerMiner.player, true));
						Utils.write(new ChatMessage(player, new ChatModel(" is using TMTracer too!")));
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onMultiPlayerUsed(MenuUsedEvent e) {
		if(e.getMenu() instanceof MultiPlayer) {
			PluginManager.sendPluginMessage(TMTracer.plugin, MessageType.ENABLE_MESSAGE, new TraceEnableMessage(TowerMiner.player, false));
		}
	}
	
	@EventHandler
	public void onMultiPlayerRendered(MenuRenderEvent e) {
		if(e.getMenu() instanceof MultiPlayer) {
			e.addRender(TMTracer.plugin.rendermulti);
		}
	}
	
	@EventHandler
	public void onMouseClicked(MenuMouseClickedEvent event) {
		if(TMTracer.plugin.getOtherPlayer() != null) {
			MouseEvent e = event.getRawEvent();
			PluginManager.sendPluginMessage(TMTracer.plugin, MessageType.CLICK_MESSAGE, new TraceClickMessage(e.getX()-2, e.getY()-30, e.getModifiers()));
		}
	}
	
	@EventHandler
	public void onMouseMoved(MenuMouseMovedEvent event) {
		if(TMTracer.plugin.getOtherPlayer() != null) {
			MouseEvent e = event.getRawEvent();
			PluginManager.sendPluginMessage(TMTracer.plugin, MessageType.MOVE_MESSAGE, new TraceMoveMessage(e.getX()-2, e.getY()-30));
		}
	}

}
