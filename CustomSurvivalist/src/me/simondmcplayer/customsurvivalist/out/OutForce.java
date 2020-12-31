package me.simondmcplayer.customsurvivalist.out;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.simondmcplayer.customsurvivalist.Main;

public class OutForce implements Listener {
	
	/*
	 	Outs and Ups are all basically the same. First they check if the setting is on, then they check if a player moved past the boundary, and they add them a tag. If they
	 	are a survivalist just tell them "hey you passed this boundary", otherwise tell them what the boundary does and give them the effect (not applicable in some cases).
	 	The tag is there to check if they are past the boundary, as otherwise we would have to check if they are past x but not z, z but not x, -x but not z, and so on so forth.
	 	So once they reach the boudary at least once we mark them with a tag. We also check if a player moved ouside the boundary, AND if they have the tag (same reason as above)
	 	and if so, get rid of the negative effect or just remove the tag.
	*/
	
	@EventHandler
	public void giveBigSlowness(PlayerMoveEvent event) {
		Integer cx, cz;
		
		boolean on = (Main.getData().get("data.on") == null ? true : Main.getData().getBoolean("data.on"));
		if (!on) return;
		boolean lastborder = (Main.getData().get("data.lastborder") == null ? true : Main.getData().getBoolean("data.lastborder"));
		if (!lastborder) return;
		
		cx = (Main.getData().get("data.cx") == null ? 0 : (Integer) Main.getData().get("data.cx"));
		cz = (Main.getData().get("data.cz") == null ? 0 : (Integer) Main.getData().get("data.cz"));
		if ((event.getTo().getX()-cx > 500 && event.getFrom().getX()-cx < 500) || (event.getTo().getZ()-cz > 500 && event.getFrom().getZ()-cz < 500) || (event.getTo().getX()-cx < -499 && event.getFrom().getX()-cx > -499) || (event.getTo().getZ()-cz < -499 && event.getFrom().getZ()-cz > -499)) {
			
			if (event.getPlayer().getScoreboardTags().contains("of"))
				return;
			
			event.getPlayer().addScoreboardTag("of");
			
			if (!event.getPlayer().getScoreboardTags().contains("s")) {
				event.getPlayer().sendMessage(ChatColor.GREEN + "You just passed the final boundary!");
				return;
			}
			
			event.getPlayer().sendMessage(ChatColor.RED + "You just passed the final boundary! You have been given slowness 6, poison and hunger!");
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 5));
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 200));
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 1000000, 0));
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 1000000, 10));
		}
	}
	
	@EventHandler
	public void removeSlowness(PlayerMoveEvent event) {
		Integer cx, cz, d;
		cx = (Main.getData().get("data.cx") == null ? 0 : (Integer) Main.getData().get("data.cx"));
		cz = (Main.getData().get("data.cz") == null ? 0 : (Integer) Main.getData().get("data.cz"));
		d = (Main.getData().get("data.o2") == null ? 200 : Integer.parseInt(Main.getData().get("data.o2").toString()));
		if (event.getTo().getX()-cx < 500 && event.getTo().getZ()-cz < 500 && event.getTo().getX()-cx > -499 && event.getTo().getZ()-cz > -499) {
			if (!event.getPlayer().getScoreboardTags().contains("of"))
				return;
			event.getPlayer().removeScoreboardTag("of");
			
			if (!event.getPlayer().getScoreboardTags().contains("s"))
				return;
			
			event.getPlayer().removePotionEffect(PotionEffectType.SLOW);
			event.getPlayer().removePotionEffect(PotionEffectType.JUMP);
			event.getPlayer().removePotionEffect(PotionEffectType.POISON);
			event.getPlayer().removePotionEffect(PotionEffectType.HUNGER);
			if (event.getTo().getX()-cx > d || event.getTo().getZ()-cz > d || event.getTo().getX()-cx < (d*-1)+1 || event.getTo().getZ()-cz < (d*-1)+1)
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 0));
		}
	}
}