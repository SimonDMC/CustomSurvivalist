package me.simondmcplayer.customsurvivalist.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.simondmcplayer.customsurvivalist.config.GUI;

public class Cmd implements CommandExecutor {
	
	public int m, s, repeat;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("svh")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Not a Player!");
				return true;
			}
			Player player = (Player) sender;
			GUI.createInventory(player);
			player.openInventory(GUI.inv);
			return true;
		}
		return false;
	}
}
