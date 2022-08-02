package me.simondmc.customsurvivalist.config;

import java.util.ArrayList;
import java.util.List;

import me.simondmc.customsurvivalist.game.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.StructureType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.simondmc.customsurvivalist.Main;

public class SettingsGUI {
	
	// this class defines the gui for the settings submenu, won't go into everything but just the new stuff

	public static Inventory settingsinv;
	public static StructureType[] structures = {
			StructureType.VILLAGE,
			StructureType.DESERT_PYRAMID,
			StructureType.RUINED_PORTAL,
			StructureType.SHIPWRECK,
			StructureType.IGLOO,
			StructureType.PILLAGER_OUTPOST,
			StructureType.JUNGLE_PYRAMID,
	};
	public static Material[] structureMats = {
			Material.BELL,
			Material.SAND,
			Material.OBSIDIAN,
			Material.JUNGLE_BOAT,
			Material.SNOW_BLOCK,
			Material.CROSSBOW,
			Material.MOSSY_COBBLESTONE,
	};
	
	public static void createInventory(Player player) {

		settingsinv = Bukkit.createInventory(player, 54, "Settings");
		
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

		boolean weakness = (Main.getData().get("data.weakness") == null || Main.getData().getBoolean("data.weakness"));
		if (weakness) {
			i = new ItemStack(Material.LIME_DYE);
			m.setDisplayName(ChatColor.GREEN + "Weakness Enabled");
			l.clear();
			l.add(ChatColor.translateAlternateColorCodes('&', "&7Disable giving the hunters"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7weakness for the duration"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7of the grace period."));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to disable!"));
		} else {
			i = new ItemStack(Material.GRAY_DYE);
			m.setDisplayName(ChatColor.RED + "Weakness Disabled");
			l.clear();
			l.add(ChatColor.translateAlternateColorCodes('&', "&7Enable giving the hunters"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7weakness for the duration"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7of the grace period."));
			l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
			l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to enable!"));
		}
		m.setLore(l);
		i.setItemMeta(m);
		settingsinv.setItem(30, i);

		int spawnNear = (Main.getData().get("data.spawnnear") != null ? Main.getData().getInt("data.spawnnear") : 0);
		// 0 = none, 1 = structure, 2 = ocean
		switch (spawnNear) {
			case 0:
				i = new ItemStack(Material.BARRIER);
				m.setDisplayName("§eSpawn Near: §cDisabled");
				l.clear();
				l.add(ChatColor.translateAlternateColorCodes('&', "&7Spawn filter is disabled."));
				l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
				l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to cycle through!"));
				break;
			case 1:
				i = new ItemStack(Material.OAK_LOG);
				m.setDisplayName("§eSpawn Near: §aStructure");
				l.clear();
				l.add(ChatColor.translateAlternateColorCodes('&', "&7You will always spawn near"));
				l.add(ChatColor.translateAlternateColorCodes('&', "&7a structure. Select the"));
				l.add(ChatColor.translateAlternateColorCodes('&', "&7structure on the right."));
				l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
				l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to cycle through!"));
				break;
			case 2:
				i = new ItemStack(Material.WATER_BUCKET);
				m.setDisplayName("§eSpawn Near: §bOcean");
				l.clear();
				l.add(ChatColor.translateAlternateColorCodes('&', "&7You will always spawn in"));
				l.add(ChatColor.translateAlternateColorCodes('&', "&7an ocean."));
				l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
				l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to cycle through!"));
				break;
		}
		m.setLore(l);
		i.setItemMeta(m);
		settingsinv.setItem(31, i);

		int structure = (Main.getData().get("data.structure") != null ? Main.getData().getInt("data.structure") : 0);
		i = new ItemStack(structureMats[structure]);
		m.setDisplayName("§eSpawn Near: §a" + Util.formatString(structures[structure].getName()));
		l.clear();
		l.add(ChatColor.translateAlternateColorCodes('&', "&7If the spawn filter is set"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7to structure, you will spawn"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7near a " + Util.formatString(structures[structure].getName()) + "."));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to cycle through!"));
		m.setLore(l);
		i.setItemMeta(m);
		settingsinv.setItem(32, i);

		i = new ItemStack(Material.ARROW);
		m.setDisplayName("§eGo Back");
		l.clear();
		m.setLore(l);
		i.setItemMeta(m);
		settingsinv.setItem(45, i);
		
		i = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		m.setDisplayName(ChatColor.WHITE + "");
		l.clear();
		m.setLore(l);
		i.setItemMeta(m);
		for (int j = 0; j < 54; j++) {
			if (settingsinv.getItem(j) == null)
				settingsinv.setItem(j, i);
		}
	}
}
