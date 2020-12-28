package me.simondmcplayer.customsurvivalist.config;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.simondmcplayer.customsurvivalist.GUIClick;
import me.simondmcplayer.customsurvivalist.Main;

public class GUI {
	
	public static Inventory inv;
	
	public static void createInventory(Player player) {

		inv = Bukkit.createInventory(player, 54, "Survivalist VS Hitmen");
		int[] slot = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 34, 35, 36, 37, 38, 42, 43, 44, 46, 47, 48, 49, 50, 51, 52};
		Material[] bed = {Material.WHITE_BED, Material.ORANGE_BED, Material.MAGENTA_BED, Material.LIGHT_BLUE_BED, Material.YELLOW_BED, Material.LIME_BED, Material.PINK_BED, Material.GRAY_BED, Material.LIGHT_GRAY_BED, Material.CYAN_BED, Material.PURPLE_BED, Material.BLUE_BED, Material.BROWN_BED, Material.GREEN_BED, Material.RED_BED, Material.BLACK_BED};
		
		ItemStack i = new ItemStack(Material.FEATHER);
		ItemMeta m = i.getItemMeta();
		m.setCustomModelData(5426);
		m.setDisplayName(ChatColor.GREEN + "Survivalist");
		List<String> l = new ArrayList<>();
		l.add("");
		if (player.getScoreboardTags().contains("s")) {
			l.add(ChatColor.GREEN + "Already selected!");
			m.addEnchant(Enchantment.DURABILITY, 1, true);
		} else {
			l.add(ChatColor.YELLOW + "Click to select!");
		}
		m.setLore(l);
		m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		i.setItemMeta(m);
		inv.setItem(11, i);
		
		i = new ItemStack(Material.IRON_SWORD);
		m.setDisplayName(ChatColor.RED + "Hitman");
		l.clear();
		l.add("");
		if (player.getScoreboardTags().contains("h")) {
			l.add(ChatColor.GREEN + "Already selected!");
			m.addEnchant(Enchantment.DURABILITY, 1, true);
		} else {
			l.add(ChatColor.YELLOW + "Click to select!");
			m.removeEnchant(Enchantment.DURABILITY);
		}
		m.setLore(l);
		m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		i.setItemMeta(m);
		inv.setItem(13, i);
		
		i = new ItemStack(Material.BARRIER);
		m.setDisplayName(ChatColor.WHITE + "Spectator");
		l.clear();
		l.add("");
		if (!player.getScoreboardTags().contains("s") && !player.getScoreboardTags().contains("h")) {
			l.add(ChatColor.GREEN + "Already selected!");
			m.addEnchant(Enchantment.DURABILITY, 1, true);
		} else {
			l.add(ChatColor.YELLOW + "Click to select!");
			m.removeEnchant(Enchantment.DURABILITY);
		}
		m.setLore(l);
		m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		i.setItemMeta(m);
		inv.setItem(15, i);
		
		i = new ItemStack(Material.GRASS);
		m.setDisplayName(ChatColor.YELLOW + "Modify Out 1");
		l.clear();
		l.add(ChatColor.translateAlternateColorCodes('&', "&7Modify how far the survivalist"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7can go before broadcasting"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7their health."));
		l.add(ChatColor.translateAlternateColorCodes('&', "&8Default 100 blocks."));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to modify!"));
		m.setLore(l);
		m.removeEnchant(Enchantment.DURABILITY);
		i.setItemMeta(m);
		inv.setItem(29, i);
		
		i = new ItemStack(Material.GRASS, 2);
		m.setDisplayName(ChatColor.YELLOW + "Modify Out 2");
		l.clear();
		l.add(ChatColor.translateAlternateColorCodes('&', "&7Modify how far the survivalist"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7can go before disabling natural"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7regeneration for them."));
		l.add(ChatColor.translateAlternateColorCodes('&', "&8Default 200 blocks."));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to modify!"));
		m.setLore(l);
		i.setItemMeta(m);
		inv.setItem(30, i);
		
		i = new ItemStack(Material.GRASS, 3);
		m.setDisplayName(ChatColor.YELLOW + "Modify Out 3");
		l.clear();
		l.add(ChatColor.translateAlternateColorCodes('&', "&7Modify how far the survivalist"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7can go before being given"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7slowness."));
		l.add(ChatColor.translateAlternateColorCodes('&', "&8Default 300 blocks."));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to modify!"));
		m.setLore(l);
		i.setItemMeta(m);
		inv.setItem(31, i);
		
		i = new ItemStack(Material.LADDER);
		m.setDisplayName(ChatColor.YELLOW + "Modify Up 1");
		l.clear();
		l.add(ChatColor.translateAlternateColorCodes('&', "&7Modify how far the survivalist"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7can go before having their"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7health limited to 8 hearts."));
		l.add(ChatColor.translateAlternateColorCodes('&', "&8Default 120 blocks."));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to modify!"));
		m.setLore(l);
		i.setItemMeta(m);
		inv.setItem(32, i);
		
		i = new ItemStack(Material.LADDER, 2);
		m.setDisplayName(ChatColor.YELLOW + "Modify Up 2");
		l.clear();
		l.add(ChatColor.translateAlternateColorCodes('&', "&7Modify how far the survivalist"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7can go before being given"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7blindness."));
		l.add(ChatColor.translateAlternateColorCodes('&', "&8Default 160 blocks."));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to modify!"));
		m.setLore(l);
		i.setItemMeta(m);
		inv.setItem(33, i);
		
		int t = (Main.getData().get("data.t") == null ? 60 : (int) Main.getData().get("data.t"));
		int g = (Main.getData().get("data.g") == null ? 60 : (int) Main.getData().get("data.g"));
		i = new ItemStack(Material.CLOCK);
		m.setDisplayName(ChatColor.YELLOW + "Modify Timer and Grace Period");
		l.clear();
		l.add(ChatColor.translateAlternateColorCodes('&', "&7Modify how long the survivalist"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7has to survive before winning"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7and how long before the hitmen"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7can start hunting."));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&eCurrent timer length: &a" + t + " minutes&e."));
		l.add(ChatColor.translateAlternateColorCodes('&', "&eCurrent grace period: &a" + g + " seconds&e."));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&eLeft click to edit timer!"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&eRight click to edit grace period!"));
		m.setLore(l);
		i.setItemMeta(m);
		inv.setItem(45, i);
		
		i = new ItemStack(Material.CAMPFIRE);
		m.setDisplayName(ChatColor.YELLOW + "Set Center to a Random Location");
		l.clear();
		l.add(ChatColor.translateAlternateColorCodes('&', "&7Teleport to a random"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7location and set the"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7center there!"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to teleport!"));
		m.setLore(l);
		i.setItemMeta(m);
		inv.setItem(39, i);
		
		if (GUIClick.game) {
			i = new ItemStack(Material.END_CRYSTAL);
			m.setDisplayName(ChatColor.YELLOW + "Stop the Game");
			l.clear();
			l.add(ChatColor.translateAlternateColorCodes('&', "&7Stop the timer!"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to stop!"));
		} else {
			i = new ItemStack(Material.NETHER_STAR);
			m.setDisplayName(ChatColor.YELLOW + "Start the Game");
			l.clear();
			l.add(ChatColor.translateAlternateColorCodes('&', "&7Let the games begin!"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to start!"));
		}
		m.setLore(l);
		i.setItemMeta(m);
		inv.setItem(40, i);
		
		int r = (int) Math.floor(Math.random() * 16);
		i = new ItemStack(bed[r]);
		m.setDisplayName(ChatColor.YELLOW + "Set Center");
		l.clear();
		l.add(ChatColor.translateAlternateColorCodes('&', "&7Set the center to your"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7current position."));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to set!"));
		m.setLore(l);
		i.setItemMeta(m);
		inv.setItem(41, i);
		
		boolean on = (Main.getData().get("data.on") == null ? true : Main.getData().getBoolean("data.on"));
		if (on) {
			i = new ItemStack(Material.LIME_DYE);
			m.setDisplayName(ChatColor.GREEN + "Enabled");
			l.clear();
			l.add(ChatColor.translateAlternateColorCodes('&', "&7Disable checks for passing"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7the boundaries."));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to disable!"));
		} else {
			i = new ItemStack(Material.GRAY_DYE);
			m.setDisplayName(ChatColor.RED + "Disabled");
			l.clear();
			l.add(ChatColor.translateAlternateColorCodes('&', "&7Enable checks for passing"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7the boundaries."));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to enable!"));
		}
		m.setLore(l);
		i.setItemMeta(m);
		inv.setItem(53, i);
		
		i = new ItemStack(Material.NAME_TAG);
		m.setDisplayName(ChatColor.YELLOW + "Plugin by SimonDMCPlayer");
		l.clear();
		l.add(ChatColor.translateAlternateColorCodes('&', "&7Click to get links to my"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7YouTube and Twitter."));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to get links!"));
		m.setLore(l);
		i.setItemMeta(m);
		inv.setItem(0, i);
		
		i = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		m.setDisplayName(ChatColor.WHITE + "");
		l.clear();
		m.setLore(l);
		i.setItemMeta(m);
		for (int s : slot) {
			inv.setItem(s, i);
		}
	}
}
