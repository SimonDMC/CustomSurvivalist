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
	
	// this class does all the things when you click on the gui, probably the main class tbh
	
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
		try {
			if(event.getClickedInventory().getType() == InventoryType.PLAYER) return;
		} catch (Exception e) {}
		
		// survivalist
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
		
		// hitman
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
		
		// spec
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
		
		// these 5 open the num gui with their labels, see NumGUI and NumGUIClick for more info
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
		
		// random loc - moved to its own function as i need to loop it in case it spawns in a bad loc
		if (event.getSlot() == 39) {
			randomSpread((Player) event.getWhoClicked());
		}
		
		// timer/grace
		if (event.getSlot() == 45) {
			t = (Main.getData().get("data.t") == null ? 60 : (int) Main.getData().get("data.t"));
			g = (Main.getData().get("data.g") == null ? 0 : (int) Main.getData().get("data.g"));
			
			// if its a right click it cycles through grace with a switch statement
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
				
				// if its anything else (left or middle or hotkey???) it cycles through the timer with a switch statement
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
		
		// start/stop
		if (event.getSlot() == 40) {
			// if game is not running rn it first sets up all the things like clearing inv, healing, saturating, etc.
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
				
				// then it kills all zombies spiders and skeletons
				for (Entity e : event.getWhoClicked().getWorld().getEntities()) {
					if (e instanceof Zombie) e.remove();
					if (e instanceof Skeleton) e.remove();
					if (e instanceof Spider) e.remove();
				}
				
				// does some more stuff like time reset and advancement clear
				s = 0;
				game = true;
				gt = (Main.getData().get("data.g") == null ? 0 : (int) Main.getData().get("data.g"));
				m = (Main.getData().get("data.t") == null ? 60 : (int) Main.getData().get("data.t"));
				event.getWhoClicked().getWorld().setTime(1000);
				
				// didnt figure out how to reset advancements through api so i just dispatch a command
				event.getWhoClicked().getWorld().setGameRuleValue("sendCommandFeedback", "false");
				Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "advancement revoke @a everything");
				event.getWhoClicked().getWorld().setGameRuleValue("sendCommandFeedback", "true");	
				Bukkit.getScheduler().cancelTask(repeat);
					
				// this is where the timer logic begins
				repeat = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {
						
					@Override
					public void run() {
						
						// reset for minutes (1:00 -> 0:59)
						if (s == -1) {
							s = 59;
							m--;
						}
							
						// when timer ends stop the game
						if (s == 0 && m == 0) {
							Bukkit.getScheduler().cancelTask(repeat);
							for (Player p : Bukkit.getOnlinePlayers()) {
								
								if (p.getScoreboardTags().contains("s")) {
									p.sendMessage(ChatColor.GREEN + "You win!");
									
									// setting up the firework and launching it at the player's position
									Firework firework = p.getWorld().spawn(p.getLocation(), Firework.class);
									FireworkMeta m = firework.getFireworkMeta();
						           
						            FireworkEffect effect = FireworkEffect.builder().flicker(true).withColor(Color.LIME).withFade(Color.LIME).with(Type.BALL).trail(true).build();
						            m.addEffect(effect);
									m.setPower(1);
									
									firework.setFireworkMeta(m);
									
									// set the game back to false so you can start it through gui
									game = false;
									
								} else {
									p.sendMessage(ChatColor.RED + "Survivalist wins!");
								}
							}
						}
						
						// formatting (0:5 -> 0:05)
						String s0 = (s < 10 ? "0" : "") + s;
						
						// check if showtimer is on
						boolean showtimer = (Main.getData().get("data.showtimer") == null ? true : Main.getData().getBoolean("data.showtimer"));
						for (Player p : Bukkit.getOnlinePlayers()) {
							if (showtimer) {
								
								// check for grace period and display message with color accordingly
								if (gt > 0) {
									p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.AQUA + String.valueOf(m) + ":" + s0));
								} else {
									p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + String.valueOf(m) + ":" + s0));
								}
							}
						}
						
						// remove seconds and grace timer each second to progress timer
						s--;
						gt--;
					}
						
					// run every 20 ticks = second
				}, 0, 20);
				
				// if clicked while game is on
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
		
		// set location
		if (event.getSlot() == 41) {
			long l1 = Math.round(Math.floor(event.getWhoClicked().getLocation().getX()));  	
			int i1=(int)l1;
			long l2 = Math.round(Math.floor(event.getWhoClicked().getLocation().getZ()));  	
			int i2=(int)l2;
			
			// get location of who clicked and tp everyone
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.teleport(event.getWhoClicked().getLocation());
			}
			
			// also stops the game because why not
			game = false;
			Bukkit.getScheduler().cancelTask(repeat);
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.removePotionEffect(PotionEffectType.GLOWING);
			}
			
			// sets config and spawnpoint
			Main.getData().set("data.cx", i1);
			Main.getData().set("data.cz", i2);
			Main.saveData();
			event.getWhoClicked().sendMessage(ChatColor.GREEN + "Set the center to " + Math.round(Math.floor(event.getWhoClicked().getLocation().getX())) + ", " + Math.round(Math.floor(event.getWhoClicked().getLocation().getZ())) + "!");
			event.getWhoClicked().getWorld().setGameRuleValue("sendCommandFeedback", "false");
			
			// spawnpoint done through commands as the api only has player.setBedLocation() which doesn't work if there's not a bed there and instead does the your home bed missing obstructed whatever this works dont stab me pls and ty
			Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), 
					"spawnpoint @a " + Math.round(Math.floor(event.getWhoClicked().getLocation().getX())) + " " + 
									   Math.round(Math.floor(event.getWhoClicked().getLocation().getY())) + " " + 
									   Math.round(Math.floor(event.getWhoClicked().getLocation().getZ())));
			event.getWhoClicked().getWorld().setGameRuleValue("sendCommandFeedback", "true");
			
			// also set the world spawn in case 1) someone joins 2) survivalist breaks hitman's bed
			Bukkit.getWorld(event.getWhoClicked().getWorld().getName()).setSpawnLocation(event.getWhoClicked().getLocation());
			GUI.createInventory((Player) event.getWhoClicked());
			event.getWhoClicked().openInventory(GUI.inv);
		}
		
		// settings, just sets up the settings gui and opens it for the player
		if (event.getSlot() == 53) {
			SettingsGUI.createInventory((Player) event.getWhoClicked());
			event.getWhoClicked().openInventory(SettingsGUI.settingsinv);
		}
		
		// credits, as i said will be probably reworked/changed
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
	
	// fires when you click random loc
	public void randomSpread(Player player) {
		
		// first it gets two random numbers from 0 to 20000
		double r1 = Math.floor(Math.random() * 20000);
		double r2 = Math.floor(Math.random() * 20000);
		Location l = player.getLocation();
		
		// then it teleports you to that minus 10000 aka somewhere inbetween -10000 and 10000 on both x and z
		l.setX(r1-10000);
		
		// aritrarily chosen y level, works best from testing
		l.setY(100);
		l.setZ(r2-10000);
		player.teleport(l);
		
		l1 = Math.round(Math.floor(player.getLocation().getX()));  	
		i1=(int)l1;
		l2 = Math.round(Math.floor(player.getLocation().getZ()));  	
		i2=(int)l2;
		
		// gives resistance to combat fall damage, easier than disabling it
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.teleport(player.getLocation());
			p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 4));
		}
		
		// this is an ocean checker, for y = 100 to y = 40 it checks if there is water anywhere, and if so, runs randomspread again, hence why it's in its own function
		for (int oc = 60; oc > 0; oc--) {
			if (player.getLocation().add(0, -1 * oc, 0).getBlock().getType().equals(Material.WATER)) {
				randomSpread(player);
				return;
			}
		}
		
		// mountain checker, if you spawn in anything else than air it randomspreads you again
		if (!player.getLocation().getBlock().getType().equals(Material.AIR)) {
			randomSpread(player);
			return;
		}
		
		// if it passes through checkers it does the rest, similar to set spawn except one thing
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
			
			// it waits 100 ticks = 5 seconds before setting the spawn, as you usually spawn in the air
		}, 100);
		
		player.closeInventory();
	}
}
