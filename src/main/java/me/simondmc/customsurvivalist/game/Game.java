package me.simondmc.customsurvivalist.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import me.simondmc.customsurvivalist.Main;
import me.simondmc.customsurvivalist.config.GUIClick;

public class Game implements Listener {
	
	// this is the class that handles most of the game logic except timer
	
	List<Player> informed = new ArrayList<Player>();
	public static int g;
	
	// when a hitman right clicks compass
	@EventHandler
	public void onCompassClick(PlayerInteractEvent event) {
		if (!RoleControl.getRole(event.getPlayer()).equals("hitman")) return;
		if (!event.getHand().equals(EquipmentSlot.HAND)) return;
		if (!(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR)) return;
		if (!event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.COMPASS)) return;
		Location l = null;
		
		// takes a survivalist and gets their location
		Player nearestp = null;
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (RoleControl.getRole(p).equals("survivalist")) {
				if (nearestp == null)
					nearestp = p;
				if (nearestp != null)
					if (p.getLocation().distance(event.getPlayer().getLocation()) < nearestp.getLocation().distance(event.getPlayer().getLocation()))
						nearestp = p;
			}
		}
		if (nearestp == null) {
			event.getPlayer().sendMessage(ChatColor.RED + "No player found!");
			return;
		}

		l = nearestp.getLocation();
		event.getPlayer().sendMessage(ChatColor.YELLOW + "Your compass is now tracking " + nearestp.getName());
		
		// set their compass to track them
		event.getPlayer().setCompassTarget(l);
	}
	
	// when a hitman dies it removes the compass from their drops
	@EventHandler
	public void cancelCompassDrop(PlayerDeathEvent event) {
		Player player = (Player) event.getEntity();
		if (!RoleControl.getRole(player).equals("hitman")) return;
		event.getDrops().removeIf(i -> i.getType().equals(Material.COMPASS));
	}
	
	// when a hitman respawns set their 8th slot to a compass
	@EventHandler
	public void hitmanRespawn(PlayerRespawnEvent event) {
		Player player = (Player) event.getPlayer();
		if (!RoleControl.getRole(player).equals("hitman")) return;
		player.getInventory().setItem(8, new ItemStack(Material.COMPASS));
	}
	
	// when a survivor dies aka game over
	@EventHandler
	public void survivorDeath(PlayerDeathEvent event) {
		Player player = (Player) event.getEntity();
		if (!RoleControl.getRole(player).equals("survivalist")) return;
		if (!GUIClick.game) return;
		for (Player p : Bukkit.getOnlinePlayers()) {
			
			// end the game and launch a firework
			if (RoleControl.getRole(p).equals("hitman")) {
				p.sendMessage(ChatColor.GREEN + "You win!");
				Firework firework = p.getWorld().spawn(p.getLocation(), Firework.class);
				FireworkMeta m = firework.getFireworkMeta();
	           
	            FireworkEffect effect = FireworkEffect.builder().flicker(true).withColor(Color.LIME).withFade(Color.LIME).with(Type.BALL_LARGE).trail(true).build();
	            m.addEffect(effect);
				m.setPower(4);
				
				firework.setFireworkMeta(m);
				
			} else {
				p.sendMessage(ChatColor.RED + "Hitmen win!");
			}
		}
		
		// stop the game and kill the timer cycle
		Bukkit.getScheduler().cancelTask(GUIClick.repeat);
		GUIClick.game = false;
	}
	
	// when a hitman drops a compass cancel the event
	@EventHandler
	public void onCompassDrop(PlayerDropItemEvent event) {
		if (!RoleControl.getRole(event.getPlayer()).equals("hitman")) return;
		if (!(event.getPlayer().getInventory().getHeldItemSlot() == 8)) return;
		if (!(event.getItemDrop().getItemStack().getType().equals(Material.COMPASS))) return;
		if (!GUIClick.game) return;
		event.setCancelled(true);
	}
	
	// when a hitman clicks the compass cancel the event
	@EventHandler
	public void onCompassMove(InventoryClickEvent event) {
		try {if (!event.getClickedInventory().getType().equals(InventoryType.PLAYER)) return;} catch (Exception ignored) {}
		if (!RoleControl.getRole((Player) event.getWhoClicked()).equals("hitman")) return;
		if (!(event.getSlot() == 8)) return;
		if (!(event.getCurrentItem().getType().equals(Material.COMPASS))) return;
		if (!GUIClick.game) return;
		event.setCancelled(true);
	}
	
	// when the hitman moves while the grace period is active, it cancels the event and tells them they can't move every 3 seconds by adding them to a list and scheduling a
	// delayed task. then it removes them from the list and tells them again. done through a player list and not a variable to support multiple hitmen
	@EventHandler
	public void hitmanGraceMove(PlayerMoveEvent event) {
		g = (Main.getData().get("data.g") == null ? 0 : (int) Main.getData().get("data.g"));
		if (g == 0) return;
		if (!RoleControl.getRole(event.getPlayer()).equals("hitman")) return;
		if (GUIClick.gt > -1 && GUIClick.game) {
			if (event.getFrom().getX() != event.getTo().getX() || event.getFrom().getZ() != event.getTo().getZ()) {
				if (!informed.contains(event.getPlayer())) {
					event.getPlayer().sendMessage(ChatColor.RED + "You can't move while in grace period!");
					informed.add(event.getPlayer());
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
						
						@Override
						public void run() {
							informed.remove(event.getPlayer());
						}
					}, 60);
				}
			}
			event.getTo().setX(event.getFrom().getX());
			event.getTo().setZ(event.getFrom().getZ());
		}
	}
	
	// disables hitmen damaging entities during grace period
	@EventHandler
	public void hitmanGraceHit(EntityDamageByEntityEvent event) {
		g = (Main.getData().get("data.g") == null ? 0 : (int) Main.getData().get("data.g"));
		if (g == 0) return;
		if (!(event.getDamager() instanceof Player)) return;
		if (!RoleControl.getRole((Player) event.getDamager()).equals("hitman")) return;
		if (GUIClick.gt > -1 && GUIClick.game) {
			event.getDamager().sendMessage(ChatColor.RED + "You can't damage entities while in grace period!");
			event.setCancelled(true);
		}
	}
	
	// disables hitmen placing blocks during grace period
	@EventHandler
	public void hitmanGracePlaceBlock(BlockPlaceEvent event) {
		g = (Main.getData().get("data.g") == null ? 0 : (int) Main.getData().get("data.g"));
		if (g == 0) return;
		if (!RoleControl.getRole(event.getPlayer()).equals("hitman")) return;
		if (GUIClick.gt > -1 && GUIClick.game) {
			event.getPlayer().sendMessage(ChatColor.RED + "You can't place blocks while in grace period!");
			event.setCancelled(true);
		}
	}
	
	// disables hitmen breaking blocks during grace period
	@EventHandler
	public void hitmanGraceBreakBlock(BlockBreakEvent event) {
		g = (Main.getData().get("data.g") == null ? 0 : (int) Main.getData().get("data.g"));
		if (g == 0) return;
		if (!RoleControl.getRole(event.getPlayer()).equals("hitman")) return;
		if (GUIClick.gt > -1 && GUIClick.game) {
			event.getPlayer().sendMessage(ChatColor.RED + "You can't break blocks while in grace period!");
			event.setCancelled(true);
		}
	}
	
	// makes the player unable to drop GUI items (marked with custom model data)
	@EventHandler
	public void onGUIItemDrop(PlayerDropItemEvent event) {
		try {if (event.getItemDrop().getItemStack().getItemMeta().getCustomModelData() == 5426) {
			event.getItemDrop().remove();
		}} catch (Exception ignored) {}
	}
	
	// kills the particle task and starts it again when someone joins for example when the server is empty
	@EventHandler
	public void restartParticles(PlayerJoinEvent event) {
		Bukkit.getScheduler().cancelTask(Particles.p);
		Particles.displayParticles(event.getPlayer());
	}
	
	// disables firework damage; rn it disables all of it instead of just the victory one, might fix in the future but honestly seems unnecessary
	@EventHandler
    public void onDamage(EntityDamageEvent event) {
        for(Entity entity : event.getEntity().getNearbyEntities(5, 5, 5)) {
            if(entity instanceof Firework) {
            	Firework f = (Firework) entity;
				if (f.getFireworkMeta().getPower() == 4)
                	event.setCancelled(true);
                return;
            }
        }
    }
	
	// started on server start by Main, clears all GUI items from players (marked with custom model data)
	public static void game() {
		g = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {
			
			@Override
			public void run() {
				
				for (Player p : Bukkit.getOnlinePlayers()) {
					for (ItemStack i : p.getInventory().getContents()) {
						try {if (i.getItemMeta().getCustomModelData() == 5426) {
							p.getInventory().remove(i);
						}} catch (Exception e) {}
					}
				}
			}
			
		}, 0, 1);
	}
}
