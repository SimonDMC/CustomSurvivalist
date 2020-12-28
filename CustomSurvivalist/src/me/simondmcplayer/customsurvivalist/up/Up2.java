package me.simondmcplayer.customsurvivalist.up;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.simondmcplayer.customsurvivalist.Main;

public class Up2 implements Listener {

	@EventHandler
	public void broadcastHealth(PlayerMoveEvent event) {
		Integer d;
		Boolean on;
		on = (Main.getData().get("data.on") == null ? true : Main.getData().getBoolean("data.on"));
		if (!on) return;
		d = (Main.getData().get("data.u2") == null ? 160 : Integer.parseInt(Main.getData().get("data.u2").toString()));
		if (event.getTo().getY() > d) {
			
			if (event.getPlayer().getScoreboardTags().contains("u2"))
				return;
			
			event.getPlayer().addScoreboardTag("u2");
			
			if (!event.getPlayer().getScoreboardTags().contains("s")) {
				event.getPlayer().sendMessage(ChatColor.GREEN + "You just passed the second upwards boundary!");
				return;
			}
			
			event.getPlayer().sendMessage(ChatColor.RED + "You just passed the second upwards boundary! You have been gives blindness!");
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1000000, 0));
		}
	}
	
	@EventHandler
	public void enableHB(PlayerMoveEvent event) {
		Integer d;
		d = (Main.getData().get("data.u2") == null ? 160 : Integer.parseInt(Main.getData().get("data.u2").toString()));
		if (event.getTo().getY() < d-1) {
			
			if (!event.getPlayer().getScoreboardTags().contains("u2"))
				return;
			event.getPlayer().removeScoreboardTag("u2");
			event.getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);
		}
	}
}
