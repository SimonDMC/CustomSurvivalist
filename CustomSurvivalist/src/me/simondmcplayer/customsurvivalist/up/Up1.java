package me.simondmcplayer.customsurvivalist.up;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.simondmcplayer.customsurvivalist.Main;

public class Up1 implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void broadcastHealth(PlayerMoveEvent event) {
		Integer d;
		Boolean on;
		on = (Main.getData().get("data.on") == null ? true : Main.getData().getBoolean("data.on"));
		if (!on) return;
		d = (Main.getData().get("data.u1") == null ? 120 : Integer.parseInt(Main.getData().get("data.u1").toString()));
		if (event.getTo().getY() > d) {
			
			if (event.getPlayer().getScoreboardTags().contains("u1"))
				return;
			
			event.getPlayer().addScoreboardTag("u1");
			
			if (!event.getPlayer().getScoreboardTags().contains("s")) {
				event.getPlayer().sendMessage(ChatColor.GREEN + "You just passed the first upwards boundary!");
				return;
			}
			
			event.getPlayer().sendMessage(ChatColor.RED + "You just passed the first upwards boundary! Your health has been limited to 8 hearts!");
			
			event.getPlayer().setMaxHealth(16);
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void enableHB(PlayerMoveEvent event) {
		Integer d;
		d = (Main.getData().get("data.u1") == null ? 120 : Integer.parseInt(Main.getData().get("data.u1").toString()));
		if (event.getTo().getY() < d-1) {
			
			if (!event.getPlayer().getScoreboardTags().contains("u1"))
				return;
			event.getPlayer().removeScoreboardTag("u1");
			event.getPlayer().setMaxHealth(20);
		}
	}
}
