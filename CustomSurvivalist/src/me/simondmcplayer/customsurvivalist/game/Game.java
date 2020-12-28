package me.simondmcplayer.customsurvivalist.game;

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

import me.simondmcplayer.customsurvivalist.GUIClick;
import me.simondmcplayer.customsurvivalist.Main;

public class Game implements Listener {
	
	List<Player> informed = new ArrayList<Player>();
	public static int g;
	
	@EventHandler
	public void onCompassClick(PlayerInteractEvent event) {
		if (!event.getPlayer().getScoreboardTags().contains("h")) return;
		if (!event.getHand().equals(EquipmentSlot.HAND)) return;
		if (!(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR)) return;
		if (!event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.COMPASS)) return;
		Location l = null;
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (p.getScoreboardTags().contains("s")) {
				l = p.getLocation();
				event.getPlayer().sendMessage(ChatColor.YELLOW + "Your compass is now tracking " + p.getName());
			}
		}
		if (l == null) {
			event.getPlayer().sendMessage(ChatColor.RED + "No player found!");
			return;
		}
		event.getPlayer().setCompassTarget(l);
		
	}
	
	@EventHandler
	public void cancelCompassDrop(PlayerDeathEvent event) {
		Player player = (Player) event.getEntity();
		if (!player.getScoreboardTags().contains("h")) return;
		for (ItemStack i : event.getDrops()) {
			if (i.getType().equals(Material.COMPASS)) event.getDrops().remove(i);
		}
	}
	
	@EventHandler
	public void survivorDeath(PlayerRespawnEvent event) {
		Player player = (Player) event.getPlayer();
		if (!player.getScoreboardTags().contains("h")) return;
		player.getInventory().setItem(8, new ItemStack(Material.COMPASS));
	}
	
	@EventHandler
	public void hunterRespawn(PlayerDeathEvent event) {
		Player player = (Player) event.getEntity();
		if (!player.getScoreboardTags().contains("s")) return;
		if (!GUIClick.game) return;
		for (Player p : Bukkit.getOnlinePlayers()) {
			
			if (p.getScoreboardTags().contains("h")) {
				p.sendMessage(ChatColor.GREEN + "You win!");
				Firework firework = p.getWorld().spawn(p.getLocation(), Firework.class);
				FireworkMeta m = firework.getFireworkMeta();
	           
	            FireworkEffect effect = FireworkEffect.builder().flicker(true).withColor(Color.LIME).withFade(Color.LIME).with(Type.BALL).trail(true).build();
	            m.addEffect(effect);
				m.setPower(1);
				m.setDisplayName("victory");
				
				firework.setFireworkMeta(m);
				
			} else {
				p.sendMessage(ChatColor.RED + "Hitmen win!");
			}
		}
		
		Bukkit.getScheduler().cancelTask(GUIClick.repeat);
		GUIClick.game = false;
	}
	
	@EventHandler
	public void onCompassDrop(PlayerDropItemEvent event) {
		if (!event.getPlayer().getScoreboardTags().contains("h")) return;
		if (!(event.getPlayer().getInventory().getHeldItemSlot() == 8)) return;
		if (!(event.getItemDrop().getItemStack().getType().equals(Material.COMPASS))) return;
		if (!GUIClick.game) return;
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onCompassMove(InventoryClickEvent event) {
		try {if (!event.getClickedInventory().getType().equals(InventoryType.PLAYER)) return;} catch (Exception e) {}
		if (!event.getWhoClicked().getScoreboardTags().contains("h")) return;
		if (!(event.getSlot() == 8)) return;
		if (!(event.getCurrentItem().getType().equals(Material.COMPASS))) return;
		if (!GUIClick.game) return;
		event.setCancelled(true);
	}
	
	@EventHandler
	public void hitmanGraceMove(PlayerMoveEvent event) {
		if (!event.getPlayer().getScoreboardTags().contains("h")) return;
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
	
	@EventHandler
	public void hitmanGraceHit(EntityDamageByEntityEvent event) {
		if (!event.getDamager().getScoreboardTags().contains("h")) return;
		if (GUIClick.gt > -1 && GUIClick.game) {
			event.getDamager().sendMessage(ChatColor.RED + "You can't damage entities while in grace period!");
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void hitmanGracePlaceBlock(BlockPlaceEvent event) {
		if (!event.getPlayer().getScoreboardTags().contains("h")) return;
		if (GUIClick.gt > -1 && GUIClick.game) {
			event.getPlayer().sendMessage(ChatColor.RED + "You can't place blocks while in grace period!");
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void hitmanGraceBreakBlock(BlockBreakEvent event) {
		if (!event.getPlayer().getScoreboardTags().contains("h")) return;
		if (GUIClick.gt > -1 && GUIClick.game) {
			event.getPlayer().sendMessage(ChatColor.RED + "You can't break blocks while in grace period!");
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onGUIItemDrop(PlayerDropItemEvent event) {
		try {if (event.getItemDrop().getItemStack().getItemMeta().getCustomModelData() == 5426) {
			event.getItemDrop().remove();
		}} catch (Exception e) {}
	}
	
	@EventHandler
	public void restartParticles(PlayerJoinEvent event) {
		Bukkit.getScheduler().cancelTask(Particles.p);
		Particles.displayParticles(event.getPlayer());
	}
	
	@EventHandler
    public void onDamage(EntityDamageEvent event) {
        for(Entity entity : event.getEntity().getNearbyEntities(5, 5, 5)) {
            if(entity instanceof Firework) {
                event.setCancelled(true);
                return;
            }
        }
    }
	
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
