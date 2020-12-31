package me.simondmcplayer.customsurvivalist.config;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NumGUI {
	
	// this class defines and sets up the number gui, when you click o1 o2 o3 u1 or u2
	
	public static Inventory numinv;
	static int num;
	
	public static void createNumInventory(Player player, String cfg) {

		// sets the string to nothing so it doesn't show anything when you open it
		NumGUIClick.num = "";
		
		// sets the title to Modify o1 for example from the string passed in, used later in saving the number in NumGUIClick to distinguish which config it should update
		numinv = Bukkit.createInventory(player, 54, "Modify " + cfg);
		int[] slot = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 13, 14, 15, 16, 17, 18, 22, 23, 25, 26, 27, 31, 32, 34, 35, 36, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};
		
		// just defines all the buttons, really nothing special lmao
		ItemStack i = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
		ItemMeta m = i.getItemMeta();
		m.setCustomModelData(5426);
		m.setDisplayName(ChatColor.WHITE + "1");
		i.setItemMeta(m);
		numinv.setItem(10, i);
		
		i = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 2);
		m.setDisplayName(ChatColor.WHITE + "2");
		i.setItemMeta(m);
		numinv.setItem(11, i);
		
		i = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 3);
		m.setDisplayName(ChatColor.WHITE + "3");
		i.setItemMeta(m);
		numinv.setItem(12, i);
		
		i = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 4);
		m.setDisplayName(ChatColor.WHITE + "4");
		i.setItemMeta(m);
		numinv.setItem(19, i);
		
		i = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 5);
		m.setDisplayName(ChatColor.WHITE + "5");
		i.setItemMeta(m);
		numinv.setItem(20, i);
		
		i = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 6);
		m.setDisplayName(ChatColor.WHITE + "6");
		i.setItemMeta(m);
		numinv.setItem(21, i);
		
		i = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 7);
		m.setDisplayName(ChatColor.WHITE + "7");
		i.setItemMeta(m);
		numinv.setItem(28, i);
		
		i = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 8);
		m.setDisplayName(ChatColor.WHITE + "8");
		i.setItemMeta(m);
		numinv.setItem(29, i);
		
		i = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 9);
		m.setDisplayName(ChatColor.WHITE + "9");
		i.setItemMeta(m);
		numinv.setItem(30, i);
		
		i = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
		m.setDisplayName(ChatColor.WHITE + "0");
		i.setItemMeta(m);
		numinv.setItem(38, i);
		
		i = new ItemStack(Material.NAME_TAG);
		m.setDisplayName(ChatColor.YELLOW + "Backspace");
		i.setItemMeta(m);
		numinv.setItem(37, i);
		
		i = new ItemStack(Material.SCUTE);
		m.setDisplayName(ChatColor.GREEN + "Confirm");
		i.setItemMeta(m);
		numinv.setItem(39, i);
		
		i = new ItemStack(Material.PAPER);
		m.setDisplayName(ChatColor.WHITE + "");
		i.setItemMeta(m);
		numinv.setItem(24, i);
		
		i = new ItemStack(Material.BARRIER);
		m.setDisplayName(ChatColor.RED + "Clear");
		i.setItemMeta(m);
		numinv.setItem(33, i);
		
		// fills rest with panes again
		i = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		m.setDisplayName(ChatColor.WHITE + "");
		i.setItemMeta(m);
		for (int s : slot) {
			numinv.setItem(s, i);
		}
	}
}
