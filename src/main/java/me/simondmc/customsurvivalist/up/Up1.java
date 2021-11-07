package me.simondmc.customsurvivalist.up;

import me.simondmc.customsurvivalist.game.RoleControl;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.simondmc.customsurvivalist.Main;

public class Up1 implements Listener {
	
	/*
	 	Outs and Ups are all basically the same. First they check if the setting is on, then they check if a player moved past the boundary, and they add them a tag. If they
	 	are a survivalist just tell them "hey you passed this boundary", otherwise tell them what the boundary does and give them the effect (not applicable in some cases).
	 	The tag is there to check if they are past the boundary, as otherwise we would have to check if they are past x but not z, z but not x, -x but not z, and so on so forth.
	 	So once they reach the boudary at least once we mark them with a tag. We also check if a player moved ouside the boundary, AND if they have the tag (same reason as above)
	 	and if so, get rid of the negative effect or just remove the tag.
	*/
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void broadcastHealth(PlayerMoveEvent event) {
		Integer d;
		Boolean on;
		on = (Main.getData().get("data.on") == null || Main.getData().getBoolean("data.on"));
		if (!on) return;
		d = (Main.getData().get("data.u1") == null ? 120 : Integer.parseInt(Main.getData().get("data.u1").toString()));
		if (event.getTo().getY() > d) {
			
			if (event.getPlayer().getScoreboardTags().contains("u1"))
				return;
			
			event.getPlayer().addScoreboardTag("u1");
			
			if (!RoleControl.getRole(event.getPlayer()).equals("survivalist")) {
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
