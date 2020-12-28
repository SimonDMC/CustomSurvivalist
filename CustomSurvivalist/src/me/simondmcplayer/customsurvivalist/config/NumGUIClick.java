package me.simondmcplayer.customsurvivalist.config;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.simondmcplayer.customsurvivalist.Main;

public class NumGUIClick implements Listener {
	
	public static String num = "";
	
	@EventHandler
	public void onSelect(InventoryClickEvent event) {
		if (!event.getInventory().equals(NumGUI.numinv)) return;
		event.setCancelled(true);
		if(event.getClickedInventory().getType() == InventoryType.PLAYER) return;
		
		if (event.getSlot() == 10) {
			num += 1;
		}
		if (event.getSlot() == 11) {
			num += 2;
		}
		if (event.getSlot() == 12) {
			num += 3;
		}
		if (event.getSlot() == 19) {
			num += 4;
		}
		if (event.getSlot() == 20) {
			num += 5;
		}
		if (event.getSlot() == 21) {
			num += 6;
		}
		if (event.getSlot() == 28) {
			num += 7;
		}
		if (event.getSlot() == 29) {
			num += 8;
		}
		if (event.getSlot() == 30) {
			num += 9;
		}
		if (event.getSlot() == 38) {
			num += 0;
		}
		if (event.getSlot() == 33) {
			num = "";
		}
		if (event.getSlot() == 37) {
			 if (num != null && num.length() > 0) {
			        num = num.substring(0, num.length() - 1);
			    }
		}
		if (event.getSlot() == 39) {
			if (!(num == "")) {
				Main.getData().set("data." + event.getView().getTitle().substring(event.getView().getTitle().length() - 2), Integer.parseInt(num));
				Main.saveData();
				event.getWhoClicked().sendMessage(ChatColor.GREEN + "Changed the value of " + event.getView().getTitle().substring(event.getView().getTitle().length() - 2) + " to " + num);
			}
			GUI.createInventory((Player) event.getWhoClicked());
			event.getWhoClicked().openInventory(GUI.inv);
		}
		
		ItemStack i = new ItemStack(Material.PAPER);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(ChatColor.WHITE + num);
		i.setItemMeta(m);
		NumGUI.numinv.setItem(24, i);
		
	}
}
