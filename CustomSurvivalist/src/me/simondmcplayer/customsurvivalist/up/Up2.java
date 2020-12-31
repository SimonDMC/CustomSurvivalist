package me.simondmcplayer.customsurvivalist.up;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.simondmcplayer.customsurvivalist.Main;

public class Up2 implements Listener {

	/*
	 	Outs and Ups are all basically the same. First they check if the setting is on, then they check if a player moved past the boundary, and they add them a tag. If they
	 	are a survivalist just tell them "hey you passed this boundary", otherwise tell them what the boundary does and give them the effect (not applicable in some cases).
	 	The tag is there to check if they are past the boundary, as otherwise we would have to check if they are past x but not z, z but not x, -x but not z, and so on so forth.
	 	So once they reach the boudary at least once we mark them with a tag. We also check if a player moved ouside the boundary, AND if they have the tag (same reason as above)
	 	and if so, get rid of the negative effect or just remove the tag.
	*/
	
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
