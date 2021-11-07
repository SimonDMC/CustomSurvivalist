package me.simondmc.customsurvivalist.config;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import me.simondmc.customsurvivalist.Main;

public class SettingsGUIClick implements Listener {

	// simple class, for each setting it checks if its off and it sets it to on, or the other way around
	
	@EventHandler
	public void onSelect(InventoryClickEvent event) {
		if (!event.getInventory().equals(SettingsGUI.settingsinv)) return;
		event.setCancelled(true);
		try {
			if(event.getClickedInventory().getType() == InventoryType.PLAYER) return;
		} catch (Exception e) {}
			if (event.getSlot() == 20) {
				boolean on = (Main.getData().get("data.on") == null || Main.getData().getBoolean("data.on"));
				if (on)
					Main.getData().set("data.on", false);
				if (!on)
					Main.getData().set("data.on", true);
				Main.saveData();
				SettingsGUI.createInventory((Player) event.getWhoClicked());
				event.getWhoClicked().openInventory(SettingsGUI.settingsinv);
			}
			
			if (event.getSlot() == 21) {
				boolean glow = (Main.getData().get("data.glow") == null || Main.getData().getBoolean("data.glow"));
				if (glow)
					Main.getData().set("data.glow", false);
				if (!glow)
					Main.getData().set("data.glow", true);
				Main.saveData();
				SettingsGUI.createInventory((Player) event.getWhoClicked());
				event.getWhoClicked().openInventory(SettingsGUI.settingsinv);
			}
			
			if (event.getSlot() == 22) {
				boolean particles = (Main.getData().get("data.particles") == null || Main.getData().getBoolean("data.particles"));
				if (particles)
					Main.getData().set("data.particles", false);
				if (!particles)
					Main.getData().set("data.particles", true);
				Main.saveData();
				SettingsGUI.createInventory((Player) event.getWhoClicked());
				event.getWhoClicked().openInventory(SettingsGUI.settingsinv);
			}
			
			if (event.getSlot() == 23) {
				boolean lastborder = (Main.getData().get("data.lastborder") == null || Main.getData().getBoolean("data.lastborder"));
				if (lastborder)
					Main.getData().set("data.lastborder", false);
				if (!lastborder)
					Main.getData().set("data.lastborder", true);
				Main.saveData();
				SettingsGUI.createInventory((Player) event.getWhoClicked());
				event.getWhoClicked().openInventory(SettingsGUI.settingsinv);
			}
			
			if (event.getSlot() == 24) {
				boolean showtimer = (Main.getData().get("data.showtimer") == null || Main.getData().getBoolean("data.showtimer"));
				if (showtimer)
					Main.getData().set("data.showtimer", false);
				if (!showtimer)
					Main.getData().set("data.showtimer", true);
				Main.saveData();
				SettingsGUI.createInventory((Player) event.getWhoClicked());
				event.getWhoClicked().openInventory(SettingsGUI.settingsinv);
			}
	}
}
