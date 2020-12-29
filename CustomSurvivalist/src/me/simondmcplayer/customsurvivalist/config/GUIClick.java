package me.simondmcplayer.customsurvivalist.config;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.simondmcplayer.customsurvivalist.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class GUIClick implements Listener {
	
	public int m, s, t, g;
	public static int repeat;
	public static int gt, i1, i2;
	public static boolean game = false;
	public static long l1, l2;
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onSelect(InventoryClickEvent event) {
		if (!event.getInventory().equals(GUI.inv)) return;
		event.setCancelled(true);
		if(event.getClickedInventory().getType() == InventoryType.PLAYER) return;
		if (event.getSlot() == 11) {
			if (event.getWhoClicked().getScoreboardTags().contains("s")) {
				event.getWhoClicked().sendMessage(ChatColor.GREEN + "You are the survivalist already!");
			} else {
				event.getWhoClicked().sendMessage(ChatColor.YELLOW + "You are now the survivalist!");
			}
			try {
				event.getWhoClicked().removeScoreboardTag("h");
				event.getWhoClicked().removeScoreboardTag("s");
			} catch (Exception e) {}
			event.getWhoClicked().addScoreboardTag("s");
			GUI.createInventory((Player) event.getWhoClicked());
			event.getWhoClicked().openInventory(GUI.inv);
			return;
		}
		
		if (event.getSlot() == 13) {
			if (event.getWhoClicked().getScoreboardTags().contains("h")) {
				event.getWhoClicked().sendMessage(ChatColor.GREEN + "You are a hitman already!");
			} else {
				event.getWhoClicked().sendMessage(ChatColor.YELLOW + "You are now a hitman!");
			}
			try {
				event.getWhoClicked().removeScoreboardTag("h");
				event.getWhoClicked().removeScoreboardTag("s");
			} catch (Exception e) {}
			event.getWhoClicked().addScoreboardTag("h");
			GUI.createInventory((Player) event.getWhoClicked());
			event.getWhoClicked().openInventory(GUI.inv);
		}
		
		if (event.getSlot() == 15) {
			if (!event.getWhoClicked().getScoreboardTags().contains("s") && !event.getWhoClicked().getScoreboardTags().contains("h")) {
				event.getWhoClicked().sendMessage(ChatColor.GREEN + "You are a spectator already!");
			} else {
				event.getWhoClicked().sendMessage(ChatColor.YELLOW + "You are now a spectator. You will be put into spectator mode once the game starts.");
			}
			try {
				event.getWhoClicked().removeScoreboardTag("h");
				event.getWhoClicked().removeScoreboardTag("s");
			} catch (Exception e) {}
			GUI.createInventory((Player) event.getWhoClicked());
			event.getWhoClicked().openInventory(GUI.inv);
		}
		
		if (event.getSlot() == 29) {
			NumGUI.createNumInventory((Player) event.getWhoClicked(), "o1");
			event.getWhoClicked().openInventory(NumGUI.numinv);
		}
		
		if (event.getSlot() == 30) {
			NumGUI.createNumInventory((Player) event.getWhoClicked(), "o2");
			event.getWhoClicked().openInventory(NumGUI.numinv);
		}
		
		if (event.getSlot() == 31) {
			NumGUI.createNumInventory((Player) event.getWhoClicked(), "o3");
			event.getWhoClicked().openInventory(NumGUI.numinv);
		}
		
		if (event.getSlot() == 32) {
			NumGUI.createNumInventory((Player) event.getWhoClicked(), "u1");
			event.getWhoClicked().openInventory(NumGUI.numinv);
		}
		
		if (event.getSlot() == 33) {
			NumGUI.createNumInventory((Player) event.getWhoClicked(), "u2");
			event.getWhoClicked().openInventory(NumGUI.numinv);
		}
		
		if (event.getSlot() == 39) {
			randomSpread((Player) event.getWhoClicked());
		}
		
		if (event.getSlot() == 45) {
			t = (Main.getData().get("data.t") == null ? 60 : (int) Main.getData().get("data.t"));
			g = (Main.getData().get("data.g") == null ? 0 : (int) Main.getData().get("data.g"));
			if (event.isRightClick()) {
				switch(g) {
					case 0:
						Main.getData().set("data.g", 5);
						Main.saveData();
						break;
					case 5:
						Main.getData().set("data.g", 10);
						Main.saveData();
						break;
					case 10:
						Main.getData().set("data.g", 15);
						Main.saveData();
						break;
					case 15:
						Main.getData().set("data.g", 20);
						Main.saveData();
						break;
					case 20:
						Main.getData().set("data.g", 30);
						Main.saveData();
						break;
					case 30:
						Main.getData().set("data.g", 45);
						Main.saveData();
						break;
					case 45:
						Main.getData().set("data.g", 60);
						Main.saveData();
						break;
					case 60:
						Main.getData().set("data.g", 0);
						Main.saveData();
						break;
					default:
						Main.getData().set("data.g", 0);
						Main.saveData();
				}
			} else {
				switch(t) {
					case 5:
						Main.getData().set("data.t", 10);
						Main.saveData();
						break;
					case 10:
						Main.getData().set("data.t", 15);
						Main.saveData();
						break;
					case 15:
						Main.getData().set("data.t", 20);
						Main.saveData();
						break;
					case 20:
						Main.getData().set("data.t", 30);
						Main.saveData();
						break;
					case 30:
						Main.getData().set("data.t", 45);
						Main.saveData();
						break;
					case 45:
						Main.getData().set("data.t", 60);
						Main.saveData();
						break;
					case 60:
						Main.getData().set("data.t", 90);
						Main.saveData();
						break;
					case 90:
						Main.getData().set("data.t", 5);
						Main.saveData();
						break;
					default:
						Main.getData().set("data.t", 60);
						Main.saveData();
				}
			}
			GUI.createInventory((Player) event.getWhoClicked());
			event.getWhoClicked().openInventory(GUI.inv);
		}
		
		if (event.getSlot() == 40) {
			if (!game) {
				boolean glow = (Main.getData().get("data.glow") == null ? true : Main.getData().getBoolean("data.glow"));
				for (Player player : Bukkit.getServer().getOnlinePlayers()) {
					player.getInventory().clear();
					player.setHealth(player.getMaxHealth());
					player.setFoodLevel((int) 20.0);
					player.setSaturation(5);
					player.getActivePotionEffects().clear();
					player.setExp(0);
					player.setLevel(0);
					if (player.getScoreboardTags().contains("s")) {
						if (glow)
							player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1000000, 0));
					}
					if (player.getScoreboardTags().contains("h")) {
						player.getInventory().setItem(8, new ItemStack(Material.COMPASS));
					}
					if (!player.getScoreboardTags().contains("s") && !player.getScoreboardTags().contains("h")) {
						player.setGameMode(GameMode.SPECTATOR);
					}
					player.sendMessage(ChatColor.GREEN + "Timer started!");
				}
				
				for (Entity e : event.getWhoClicked().getWorld().getEntities()) {
					if (e instanceof Zombie) e.remove();
					if (e instanceof Skeleton) e.remove();
					if (e instanceof Spider) e.remove();
				}
				
				s = 0;
				game = true;
				gt = (Main.getData().get("data.g") == null ? 0 : (int) Main.getData().get("data.g"));
				m = (Main.getData().get("data.t") == null ? 60 : (int) Main.getData().get("data.t"));
				event.getWhoClicked().getWorld().setTime(1000);
				event.getWhoClicked().getWorld().setGameRuleValue("sendCommandFeedback", "false");
				Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "advancement revoke @a everything");
				event.getWhoClicked().getWorld().setGameRuleValue("sendCommandFeedback", "true");	
				Bukkit.getScheduler().cancelTask(repeat);
					
				repeat = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {
						
					@Override
					public void run() {
							
						if (s == -1) {
							s = 59;
							m--;
						}
							
						if (s == 0 && m == 0) {
							Bukkit.getScheduler().cancelTask(repeat);
							for (Player p : Bukkit.getOnlinePlayers()) {
								
								if (p.getScoreboardTags().contains("s")) {
									p.sendMessage(ChatColor.GREEN + "You win!");
									Firework firework = p.getWorld().spawn(p.getLocation(), Firework.class);
									FireworkMeta m = firework.getFireworkMeta();
						           
						            FireworkEffect effect = FireworkEffect.builder().flicker(true).withColor(Color.LIME).withFade(Color.LIME).with(Type.BALL).trail(true).build();
						            m.addEffect(effect);
									m.setPower(1);
									m.setDisplayName("victory");
									
									firework.setFireworkMeta(m);
									
									game = false;
									
								} else {
									p.sendMessage(ChatColor.RED + "Survivalist wins!");
								}
							}
						}
							
						String s0 = (s < 10 ? "0" : "") + s;
						
						boolean showtimer = (Main.getData().get("data.showtimer") == null ? true : Main.getData().getBoolean("data.showtimer"));
						for (Player p : Bukkit.getOnlinePlayers()) {
							if (showtimer) {
								if (gt > 0) {
									p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.AQUA + String.valueOf(m) + ":" + String.valueOf(s0)));
								} else {
									p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + String.valueOf(m) + ":" + String.valueOf(s0)));
								}
							}
						}
							
						s--;
						gt--;
					}
						
				}, 0, 20);
			} else {
				Bukkit.getScheduler().cancelTask(repeat);
				for (Player p : Bukkit.getOnlinePlayers()) {
					p.sendMessage(ChatColor.RED + "Timer stopped!");
					p.removePotionEffect(PotionEffectType.GLOWING);
				}
				game = false;
			}
			event.getWhoClicked().closeInventory();
		}
		
		if (event.getSlot() == 41) {
			long l1 = Math.round(Math.floor(event.getWhoClicked().getLocation().getX()));  	
			int i1=(int)l1;
			long l2 = Math.round(Math.floor(event.getWhoClicked().getLocation().getZ()));  	
			int i2=(int)l2;
			
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.teleport(event.getWhoClicked().getLocation());
			}
			
			game = false;
			Bukkit.getScheduler().cancelTask(repeat);
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.removePotionEffect(PotionEffectType.GLOWING);
			}
			
			Main.getData().set("data.cx", i1);
			Main.getData().set("data.cz", i2);
			Main.saveData();
			event.getWhoClicked().sendMessage(ChatColor.GREEN + "Set the center to " + Math.round(Math.floor(event.getWhoClicked().getLocation().getX())) + ", " + Math.round(Math.floor(event.getWhoClicked().getLocation().getZ())) + "!");
			event.getWhoClicked().getWorld().setGameRuleValue("sendCommandFeedback", "false");
			Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), 
					"spawnpoint @a " + Math.round(Math.floor(event.getWhoClicked().getLocation().getX())) + " " + 
									   Math.round(Math.floor(event.getWhoClicked().getLocation().getY())) + " " + 
									   Math.round(Math.floor(event.getWhoClicked().getLocation().getZ())));
			event.getWhoClicked().getWorld().setGameRuleValue("sendCommandFeedback", "true");
			Bukkit.getWorld(event.getWhoClicked().getWorld().getName()).setSpawnLocation(event.getWhoClicked().getLocation());
			GUI.createInventory((Player) event.getWhoClicked());
			event.getWhoClicked().openInventory(GUI.inv);
		}
		
		if (event.getSlot() == 53) {
			SettingsGUI.createInventory((Player) event.getWhoClicked());
			event.getWhoClicked().openInventory(SettingsGUI.settingsinv);
		}
		
		if (event.getSlot() == 0) {
			TextComponent message = new TextComponent("YouTube");
			message.setColor(ChatColor.RED);
			message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://youtube.com/SimonDMCPlayer"));
			message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click")));
			event.getWhoClicked().spigot().sendMessage(message);
			
			message = new TextComponent("Twitter");
			message.setColor(ChatColor.AQUA);
			message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://twitter.com/SimonDMCPlayer_"));
			message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click")));
			event.getWhoClicked().spigot().sendMessage(message);
			
			event.getWhoClicked().closeInventory();
		}
		return;
	}
	
	public void randomSpread(Player player) {
		double r1 = Math.floor(Math.random() * 20000);
		double r2 = Math.floor(Math.random() * 20000);
		Location l = player.getLocation();
		l.setX(r1-10000);
		l.setY(100);
		l.setZ(r2-10000);
		player.teleport(l);
		
		l1 = Math.round(Math.floor(player.getLocation().getX()));  	
		i1=(int)l1;
		l2 = Math.round(Math.floor(player.getLocation().getZ()));  	
		i2=(int)l2;
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.teleport(player.getLocation());
			p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 4));
		}
		
		for (int oc = 60; oc > 0; oc--) {
			if (player.getLocation().add(0, -1 * oc, 0).getBlock().getType().equals(Material.WATER)) {
				randomSpread(player);
				return;
			}
		}
		
		if (!player.getLocation().getBlock().getType().equals(Material.AIR)) {
			randomSpread(player);
			return;
		}
		
		rest(player);
	}
	
	public void rest(Player player) {
		Main.getData().set("data.cx", i1);
		Main.getData().set("data.cz", i2);
		Main.saveData();
		player.sendMessage(ChatColor.GREEN + "Set the center to " + Math.round(Math.floor(player.getLocation().getX())) + ", " + Math.round(Math.floor(player.getLocation().getZ())) + "!");
		
		game = false;
		Bukkit.getScheduler().cancelTask(repeat);
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.removePotionEffect(PotionEffectType.GLOWING);
		}
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				player.getWorld().setGameRuleValue("sendCommandFeedback", "false");
				Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), 
						"spawnpoint @a " + Math.round(Math.floor(player.getLocation().getX())) + " " + 
										   Math.round(Math.floor(player.getLocation().getY())) + " " + 
										   Math.round(Math.floor(player.getLocation().getZ())));
				player.getWorld().setGameRuleValue("sendCommandFeedback", "true");
				Bukkit.getWorld(player.getWorld().getName()).setSpawnLocation(player.getLocation());
			}
		}, 100);
		
		player.closeInventory();
	}
}
