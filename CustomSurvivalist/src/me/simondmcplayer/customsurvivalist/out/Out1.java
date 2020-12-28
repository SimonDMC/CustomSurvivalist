package me.simondmcplayer.customsurvivalist.out;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.simondmcplayer.customsurvivalist.Main;

public class Out1 implements Listener {
	
	@EventHandler
	public void broadcastHealth(PlayerMoveEvent event) {
		Integer d, cx, cz;
		Boolean on;
		on = (Main.getData().get("data.on") == null ? true : Main.getData().getBoolean("data.on"));
		if (!on) return;
		d = (Main.getData().get("data.o1") == null ? 100 : Integer.parseInt(Main.getData().get("data.o1").toString()));
		cx = (Main.getData().get("data.cx") == null ? 0 : (Integer) Main.getData().get("data.cx"));
		cz = (Main.getData().get("data.cz") == null ? 0 : (Integer) Main.getData().get("data.cz"));
		if (event.getTo().getX()-cx > d || event.getTo().getZ()-cz > d || event.getTo().getX()-cx < (d*-1)+1 || event.getTo().getZ()-cz < (d*-1)+1) {
			
			if (event.getPlayer().getScoreboardTags().contains("o1"))
				return;
			
			event.getPlayer().addScoreboardTag("o1");
			
			if (!event.getPlayer().getScoreboardTags().contains("s")) {
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
