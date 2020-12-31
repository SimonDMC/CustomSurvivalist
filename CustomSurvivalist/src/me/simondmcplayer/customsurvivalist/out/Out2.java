package me.simondmcplayer.customsurvivalist.out;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.player.PlayerMoveEvent;

import me.simondmcplayer.customsurvivalist.Main;

public class Out2 implements Listener {
	
	/*
	 	Outs and Ups are all basically the same. First they check if the setting is on, then they check if a player moved past the boundary, and they add them a tag. If they
	 	are a survivalist just tell them "hey you passed this boundary", otherwise tell them what the boundary does and give them the effect (not applicable in some cases).
	 	The tag is there to check if they are past the boundary, as otherwise we would have to check if they are past x but not z, z but not x, -x but not z, and so on so forth.
	 	So once they reach the boudary at least once we mark them with a tag. We also check if a player moved ouside the boundary, AND if they have the tag (same reason as above)
	 	and if so, get rid of the negative effect or just remove the tag.
	 */
	
	@EventHandler
	public void registerNROff(PlayerMoveEvent event) {
		Integer d, cx, cz;
		Boolean on;
		on = (Main.getData().get("data.on") == null ? true : Main.getData().getBoolean("data.on"));
		if (!on) return;
		d = (Main.getData().get("data.o2") == null ? 200 : Integer.parseInt(Main.getData().get("data.o2").toString()));
		cx = (Main.getData().get("data.cx") == null ? 0 : (Integer) Main.getData().get("data.cx"));
		cz = (Main.getData().get("data.cz") == null ? 0 : (Integer) Main.getData().get("data.cz"));
		if (event.getTo().getX()-cx > d || event.getTo().getZ()-cz > d || event.getTo().getX()-cx < (d*-1)+1 || event.getTo().getZ()-cz < (d*-1)+1) {
			
			if (event.getPlayer().getScoreboardTags().contains("o2"))
				return;
			
			event.getPlayer().addScoreboardTag("o2");
			
			if (!event.getPlayer().getScoreboardTags().contains("s")) {
				event.getPlayer().sendMessage(ChatColor.GREEN + "You just passed the second boundary!");
				return;
			}
			
			event.getPlayer().sendMessage(ChatColor.RED + "You just passed the second boundary! Natural regeneration has now been disabled!");	
		}
	}
	
	@EventHandler
	public void registerNROn(PlayerMoveEvent event) {
		Integer d, cx, cz;
		d = (Main.getData().get("data.o2") == null ? 200 : Integer.parseInt(Main.getData().get("data.o2").toString()));
		cx = (Main.getData().get("data.cx") == null ? 0 : (Integer) Main.getData().get("data.cx"));
		cz = (Main.getData().get("data.cz") == null ? 0 : (Integer) Main.getData().get("data.cz"));
		if (event.getTo().getX()-cx < d && event.getTo().getZ()-cz < d && event.getTo().getX()-cx > (d*-1)+1 && event.getTo().getZ()-cz > (d*-1)+1) {
			
			if (!event.getPlayer().getScoreboardTags().contains("o2"))
				return;
			event.getPlayer().removeScoreboardTag("o2");
		}
	}
	
	@EventHandler
	public void blockNR(EntityRegainHealthEvent event) {
		if (event.getEntity() instanceof Player)
			if (event.getRegainReason() == RegainReason.SATIATED)
				if (event.getEntity().getScoreboardTags().contains("o2"))
					if (event.getEntity().getScoreboardTags().contains("s"))
						event.setCancelled(true);
	}
}
