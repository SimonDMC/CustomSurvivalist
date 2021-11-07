package me.simondmc.customsurvivalist.out;

import me.simondmc.customsurvivalist.game.RoleControl;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.simondmc.customsurvivalist.Main;

public class Out1 implements Listener {
	
	/*
	 	Outs and Ups are all basically the same. First they check if the setting is on, then they check if a player moved past the boundary, and they add them a tag. If they
	 	are a survivalist just tell them "hey you passed this boundary", otherwise tell them what the boundary does and give them the effect (not applicable in some cases).
	 	The tag is there to check if they are past the boundary, as otherwise we would have to check if they are past x but not z, z but not x, -x but not z, and so on so forth.
	 	So once they reach the boudary at least once we mark them with a tag. We also check if a player moved ouside the boundary, AND if they have the tag (same reason as above)
	 	and if so, get rid of the negative effect or just remove the tag.
	*/
	
	@EventHandler
	public void broadcastHealth(PlayerMoveEvent event) {
		Integer d, cx, cz;
		Boolean on;
		on = (Main.getData().get("data.on") == null || Main.getData().getBoolean("data.on"));
		if (!on) return;
		d = (Main.getData().get("data.o1") == null ? 100 : Integer.parseInt(Main.getData().get("data.o1").toString()));
		cx = (Main.getData().get("data.cx") == null ? 0 : (Integer) Main.getData().get("data.cx"));
		cz = (Main.getData().get("data.cz") == null ? 0 : (Integer) Main.getData().get("data.cz"));
		if (event.getTo().getX()-cx > d || event.getTo().getZ()-cz > d || event.getTo().getX()-cx < (d*-1)+1 || event.getTo().getZ()-cz < (d*-1)+1) {
			
			if (event.getPlayer().getScoreboardTags().contains("o1"))
				return;
			
			event.getPlayer().addScoreboardTag("o1");
			
			if (!RoleControl.getRole(event.getPlayer()).equals("survivalist")) {
				event.getPlayer().sendMessage(ChatColor.GREEN + "You just passed the first boundary!");
				return;
			}
			
			event.getPlayer().sendMessage(ChatColor.RED + "You just passed the first boundary! Your health has been broadcasted!");
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				p.sendMessage(ChatColor.YELLOW + event.getPlayer().getName() + " is on " + Math.round(Math.floor(event.getPlayer().getHealth())) + " HP!");
			}
		}
	}
	
	@EventHandler
	public void enableHB(PlayerMoveEvent event) {
		Integer d, cx, cz;
		d = (Main.getData().get("data.o1") == null ? 100 : Integer.parseInt(Main.getData().get("data.o1").toString()));
		cx = (Main.getData().get("data.cx") == null ? 0 : (Integer) Main.getData().get("data.cx"));
		cz = (Main.getData().get("data.cz") == null ? 0 : (Integer) Main.getData().get("data.cz"));
		if (event.getTo().getX()-cx < d && event.getTo().getZ()-cz < d && event.getTo().getX()-cx > (d*-1)+1 && event.getTo().getZ()-cz > (d*-1)+1) {
			
			if (!event.getPlayer().getScoreboardTags().contains("o1"))
				return;
			event.getPlayer().removeScoreboardTag("o1");
		}
	}
}
