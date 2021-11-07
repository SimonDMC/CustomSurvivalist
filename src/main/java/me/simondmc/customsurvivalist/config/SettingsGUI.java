package me.simondmc.customsurvivalist.config;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.simondmc.customsurvivalist.Main;

public class SettingsGUI {
	
	// this class defines the gui for the settings submenu, won't go into everything but just the new stuff

	public static Inventory settingsinv;
	
	public static void createInventory(Player player) {

		settingsinv = Bukkit.createInventory(player, 45, "Settings");
		
		ItemStack i = new ItemStack(Material.LIME_DYE);
		ItemMeta m = i.getItemMeta();
		m.setCustomModelData(5426);
		List<String> l = new ArrayList<>();
		
		// takes on from the config and checks if its on, if so shows the first option, otherwise the second
		boolean on = (Main.getData().get("data.on") == null || Main.getData().getBoolean("data.on"));
		if (on) {
			i = new ItemStack(Material.LIME_DYE);
			m.setDisplayName(ChatColor.GREEN + "Boundaries Enabled");
			l.add(ChatColor.translateAlternateColorCodes('&', "&7Disable checks for passing"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7the boundaries."));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to disable!"));
		} else {
			i = new ItemStack(Material.GRAY_DYE);
			m.setDisplayName(ChatColor.RED + "Boundaries Disabled");
			l.add(ChatColor.translateAlternateColorCodes('&', "&7Enable checks for passing"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7the boundaries."));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to enable!"));
		}
		m.setLore(l);
		i.setItemMeta(m);
		settingsinv.setItem(20, i);
		
		// this repeats with every other setting just with different config names
		boolean glow = (Main.getData().get("data.glow") == null || Main.getData().getBoolean("data.glow"));
		if (glow) {
			i = new ItemStack(Material.LIME_DYE);
			m.setDisplayName(ChatColor.GREEN + "Glowing Enabled");
			l.clear();
			l.add(ChatColor.translateAlternateColorCodes('&', "&7Disable getting the glowing"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7effect upon starting the game"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7as a survivalist."));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to disable!"));
		} else {
			i = new ItemStack(Material.GRAY_DYE);
			m.setDisplayName(ChatColor.RED + "Glowing Disabled");
			l.clear();
			l.add(ChatColor.translateAlternateColorCodes('&', "&7Enable getting the glowing"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7effect upon starting the game"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7as a survivalist."));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to enable!"));
		}
		m.setLore(l);
		i.setItemMeta(m);
		settingsinv.setItem(21, i);
		
		boolean particles = (Main.getData().get("data.particles") == null || Main.getData().getBoolean("data.particles"));
		if (particles) {
			i = new ItemStack(Material.LIME_DYE);
			m.setDisplayName(ChatColor.GREEN + "Particles Enabled");
			l.clear();
			l.add(ChatColor.translateAlternateColorCodes('&', "&7Disable rendering particles"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7at borders, should improve"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7performance and lag less."));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to disable!"));
		} else {
			i = new ItemStack(Material.GRAY_DYE);
			m.setDisplayName(ChatColor.RED + "Particles Disabled");
			l.clear();
			l.add(ChatColor.translateAlternateColorCodes('&', "&7Enable rendering particles"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7at borders, should improve"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7performance and lag less."));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to enable!"));
		}
		m.setLore(l);
		i.setItemMeta(m);
		settingsinv.setItem(22, i);
		
		boolean lastborder = (Main.getData().get("data.lastborder") == null || Main.getData().getBoolean("data.lastborder"));
		if (lastborder) {
			i = new ItemStack(Material.LIME_DYE);
			m.setDisplayName(ChatColor.GREEN + "Last Border Enabled");
			l.clear();
			l.add(ChatColor.translateAlternateColorCodes('&', "&7Disable the last border"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7at 500 blocks out."));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7(not recommended)"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to disable!"));
		} else {
			i = new ItemStack(Material.GRAY_DYE);
			m.setDisplayName(ChatColor.RED + "Last Border Disabled");
			l.clear();
			l.add(ChatColor.translateAlternateColorCodes('&', "&7Enable the last border"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7at 500 blocks out."));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7(recommended)"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to enable!"));
		}
		m.setLore(l);
		i.setItemMeta(m);
		settingsinv.setItem(23, i);
		
		boolean showtimer = (Main.getData().get("data.showtimer") == null || Main.getData().getBoolean("data.showtimer"));
		if (showtimer) {
			i = new ItemStack(Material.LIME_DYE);
			m.setDisplayName(ChatColor.GREEN + "Timer Enabled");
			l.clear();
			l.add(ChatColor.translateAlternateColorCodes('&', "&7Disable the timer in the"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7actionbar. Timer will"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7still work, but it won't"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7be visible."));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to disable!"));
		} else {
			i = new ItemStack(Material.GRAY_DYE);
			m.setDisplayName(ChatColor.RED + "Timer Disabled");
			l.clear();
			l.add(ChatColor.translateAlternateColorCodes('&', "&7Enable the timer in the"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7actionbar. Timer still"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7works, but it isn't visible."));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to enable!"));
		}
		m.setLore(l);
		i.setItemMeta(m);
		settingsinv.setItem(24, i);
		
		i = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		m.setDisplayName(ChatColor.WHITE + "");
		l.clear();
		m.setLore(l);
		i.setItemMeta(m);
		for (int j = 0; j < 45; j++) {
			if (settingsinv.getItem(j) == null)
				settingsinv.setItem(j, i);
		}
	}
}
