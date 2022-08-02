package me.simondmc.customsurvivalist.config;

import java.util.ArrayList;
import java.util.List;

import me.simondmc.customsurvivalist.game.RoleControl;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.simondmc.customsurvivalist.Main;

public class GUI {
	
	// this class defines the basic gui
	
	public static Inventory inv;
	
	public static void createInventory(Player player) {

		inv = Bukkit.createInventory(null, 54, "Survivalist VS Hitmen");
		Material[] bed = {Material.WHITE_BED, Material.ORANGE_BED, Material.MAGENTA_BED, Material.LIGHT_BLUE_BED, Material.YELLOW_BED, Material.LIME_BED, Material.PINK_BED, Material.GRAY_BED, Material.LIGHT_GRAY_BED, Material.CYAN_BED, Material.PURPLE_BED, Material.BLUE_BED, Material.BROWN_BED, Material.GREEN_BED, Material.RED_BED, Material.BLACK_BED};
		
		// survivalist
		ItemStack i = new ItemStack(Material.FEATHER);
		ItemMeta m = i.getItemMeta();
		// gives it custommodeldata as a safety check for clicking + making it unable to drop
		m.setCustomModelData(5426);
		m.setDisplayName(ChatColor.GREEN + "Survivalist");
		List<String> l = new ArrayList<>();
		l.add("");
		if (RoleControl.getRole(player).equals("survivalist")) {
			l.add(ChatColor.GREEN + "Already selected!");
			m.addEnchant(Enchantment.DURABILITY, 1, true);
		} else {
			l.add(ChatColor.YELLOW + "Click to select!");
		}
		m.setLore(l);
		m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		i.setItemMeta(m);
		inv.setItem(11, i);
		
		// hitman
		i = new ItemStack(Material.IRON_SWORD);
		m.setDisplayName(ChatColor.RED + "Hitman");
		l.clear();
		l.add("");
		if (RoleControl.getRole(player).equals("hitman")) {
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
		
		// spectator
		i = new ItemStack(Material.BARRIER);
		m.setDisplayName(ChatColor.WHITE + "Spectator");
		l.clear();
		l.add("");
		if (!RoleControl.getRole(player).equals("survivalist") && !RoleControl.getRole(player).equals("hitman")) {
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
		
		// out 1
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
		
		// out 2
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
		
		// out 3
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
		
		// up 1
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
		
		// up 2
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
		
		// timer and grace (order is a bit shuffled as i was moving icons around)
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
		
		// radom loc
		i = new ItemStack(Material.CAMPFIRE);
		m.setDisplayName(ChatColor.YELLOW + "Set Center to a Random Location");
		l.clear();
		l.add(ChatColor.translateAlternateColorCodes('&', "&7Teleport to a random location"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7based on the spawn filter and"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7set the center there."));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to teleport!"));
		m.setLore(l);
		i.setItemMeta(m);
		inv.setItem(39, i);
		
		// start/stop
		// tests if the game is on, if so displays the stop game, otherwise displays start game, actual logic is defined later in GUIClick
		if (me.simondmc.customsurvivalist.config.GUIClick.game) {
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
		
		// set center
		int r = (int) Math.floor(Math.random() * bed.length);
		// it takes a random bed from the array
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
		
		// settings
		i = new ItemStack(Material.REDSTONE_TORCH);
		m.setDisplayName(ChatColor.GREEN + "Settings");
		l.clear();
		l.add(ChatColor.translateAlternateColorCodes('&', "&7Open a submenu for various"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7toggles, such as disabling"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7boundary checks, glowing,"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7spawn filters and more."));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to open!"));
		m.setLore(l);
		i.setItemMeta(m);
		inv.setItem(53, i);
		
		// credits
		i = new ItemStack(Material.NAME_TAG);
		m.setDisplayName(ChatColor.YELLOW + "Plugin by SimonDMC");
		l.clear();
		l.add(ChatColor.translateAlternateColorCodes('&', "&7Click to get links to my"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7YouTube and Github."));
		l.add(ChatColor.translateAlternateColorCodes('&', "&7"));
		l.add(ChatColor.translateAlternateColorCodes('&', "&eClick to get links!"));
		m.setLore(l);
		i.setItemMeta(m);
		inv.setItem(0, i);
		
		// checks all slots if they're empty and fills them with glass
		i = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		m.setDisplayName(ChatColor.WHITE + "");
		l.clear();
		m.setLore(l);
		i.setItemMeta(m);
		for (int j = 0; j < 54; j++) {
			if (inv.getItem(j) == null)
				inv.setItem(j, i);
		}
	}
}
