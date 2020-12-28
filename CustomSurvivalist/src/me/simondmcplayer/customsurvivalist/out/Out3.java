package me.simondmcplayer.customsurvivalist.out;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.simondmcplayer.customsurvivalist.Main;

public class Out3 implements Listener {
	
	@EventHandler
	public void giveSlowness(PlayerMoveEvent event) {
		Integer d, cx, cz;
		Boolean on;
		on = (Main.getData().get("data.on") == null ? true : Main.getData().getBoolean("data.on"));
		if (!on) return;
		d = (Main.getData().get("data.o3") == null ? 300 : Integer.parseInt(Main.getData().get("data.o3").toString()));
		cx = (Main.getData().get("data.cx") == null ? 0 : (Integer) Main.getData().get("data.cx"));
		cz = (Main.getData().get("data.cz") == null ? 0 : (Integer) Main.getData().get("data.cz"));
		if (event.getTo().getX()-cx > d || event.getTo().getZ()-cz > d || event.getTo().getX()-cx < (d*-1)+1 || event.getTo().getZ()-cz < (d*-1)+1) {
			
			if (event.getPlayer().getScoreboardTags().contains("o3"))
				return;
			
			event.getPlayer().addScoreboardTag("o3");
			
			if (!event.getPlayer().getScoreboardTags().contains("s")) {
				event.getPlayer().sendMessage(ChatColor.GREEN + "You just passed the third boundary!");
				return;
			}
			
			event.getPlayer().sendMessage(ChatColor.RED + "You just passed the third boundary! You have been given slowness!");
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 0));
		}
	}
	
	@EventHandler
	public void removeSlowness(PlayerMoveEvent event) {
		Integer d, cx, cz;
		d = (Main.getData().get("data.o3") == null ? 300 : Integer.parseInt(Main.getData().get("data.o3").toString()));
		cx = (Main.getData().get("data.cx") == null ? 0 : (Integer) Main.getData().get("data.cx"));
		cz = (Main.getData().get("data.cz") == null ? 0 : (Integer) Main.getData().get("data.cz"));
		if (event.getTo().getX()-cx < d && event.getTo().getZ()-cz < d && event.getTo().getX()-cx > (d*-1)+1 && event.getTo().getZ()-cz > (d*-1)+1) {
			if (!event.getPlayer().getScoreboardTags().contains("o3"))
				return;
			event.getPlayer().removeScoreboardTag("o3");
			
			if (!event.getPlayer().getScoreboardTags().contains("s"))
				return;
			
			event.getPlayer().removePotionEffect(PotionEffectType.SLOW);
		}
	}
}
