package me.simondmcplayer.customsurvivalist.game;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

import me.simondmcplayer.customsurvivalist.Main;

public class Particles {
	
	public static int p, cx, cz, o1, o2, o3, u1, u2;
	static int d = 10;
	
	public static void displayParticles(Player player) {
		
		Bukkit.getScheduler().cancelTask(p);
		p = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {
			
			@Override
			public void run() {
				
				World w = player.getWorld();
				
				cx = (Main.getData().get("data.cx") == null ? 0 : (Integer) Main.getData().get("data.cx"));
				cz = (Main.getData().get("data.cz") == null ? 0 : (Integer) Main.getData().get("data.cz"));
				o1 = (Main.getData().get("data.o1") == null ? 100 : (Integer) Main.getData().get("data.o1"));
				o2 = (Main.getData().get("data.o2") == null ? 200 : (Integer) Main.getData().get("data.o2"));
				o3 = (Main.getData().get("data.o3") == null ? 300 : (Integer) Main.getData().get("data.o3"));
				u1 = (Main.getData().get("data.u1") == null ? 120 : (Integer) Main.getData().get("data.u1"));
				u2 = (Main.getData().get("data.u2") == null ? 160 : (Integer) Main.getData().get("data.u2"));
				
				boolean particles = (Main.getData().get("data.particles") == null ? true : Main.getData().getBoolean("data.particles"));
				boolean lastborder = (Main.getData().get("data.lastborder") == null ? true : Main.getData().getBoolean("data.lastborder"));
				boolean on = (Main.getData().get("data.on") == null ? true : Main.getData().getBoolean("data.on"));
				
				if (particles && on) {
					// ========== O1 ==========
					if (renderParticles(cx, cz, o1)) {
					
						// SOUTH
						for (int y = 200; y > 0; y--) {
							for (int x = cx - o1; x < cx + o1; x++) {
								if (isPlayerClose(x + 1, y, cz + o1)) {
									w.spawnParticle(Particle.REDSTONE, x + 1, y, cz + o1, 0, 0.001, 1, 0, 1, new Particle.DustOptions(Color.LIME, 1));
								}
							}
						}
						
						// EAST
						for (int y = 200; y > 0; y--) {
							for (int z = cz - o1; z < cz + o1; z++) {
								if (isPlayerClose(cx + o1, y, z + 1)) {
									w.spawnParticle(Particle.REDSTONE, cx + o1, y, z + 1, 0, 0.001, 1, 0, 1, new Particle.DustOptions(Color.LIME, 1));
								}
							}
						}
						
						// NORTH
						for (int y = 200; y > 0; y--) {
							for (int x = cx - o1; x < cx + o1; x++) {
								if (isPlayerClose(x + 1, y, cz - o1 + 1)) {
									w.spawnParticle(Particle.REDSTONE, x + 1, y, cz - o1 + 1, 0, 0.001, 1, 0, 1, new Particle.DustOptions(Color.LIME, 1));
								}
							}
						}
						
						// WEST
						for (int y = 200; y > 0; y--) {
							for (int z = cz - o1; z < cz + o1; z++) {
								if (isPlayerClose(cx - o1 + 1, y, z + 1)) {
									w.spawnParticle(Particle.REDSTONE, cx - o1 + 1, y, z + 1, 0, 0.001, 1, 0, 1, new Particle.DustOptions(Color.LIME, 1));
								}
							}
						}
					}
					
					// ========== O2 ==========
					
					if (renderParticles(cx, cz, o2)) {
						
						// SOUTH
						for (int y = 200; y > 0; y--) {
							for (int x = cx - o2; x < cx + o2; x++) {
								if (isPlayerClose(x + 1, y, cz + o2)) {
									w.spawnParticle(Particle.REDSTONE, x + 1, y, cz + o2, 0, 0.001, 1, 0, 1, new Particle.DustOptions(Color.YELLOW, 1));
								}
							}
						}
						
						// EAST
						for (int y = 200; y > 0; y--) {
							for (int z = cz - o2; z < cz + o2; z++) {
								if (isPlayerClose(cx + o2, y, z + 1)) {
									w.spawnParticle(Particle.REDSTONE, cx + o2, y, z + 1, 0, 0.001, 1, 0, 1, new Particle.DustOptions(Color.YELLOW, 1));
								}
							}
						}
						
						// NORTH
						for (int y = 200; y > 0; y--) {
							for (int x = cx - o2; x < cx + o2; x++) {
								if (isPlayerClose(x + 1, y, cz - o2 + 1)) {
									w.spawnParticle(Particle.REDSTONE, x + 1, y, cz - o2 + 1, 0, 0.001, 1, 0, 1, new Particle.DustOptions(Color.YELLOW, 1));
								}
							}
						}
						
						// WEST
						for (int y = 200; y > 0; y--) {
							for (int z = cz - o2; z < cz + o2; z++) {
								if (isPlayerClose(cx - o2 + 1, y, z + 1)) {
									w.spawnParticle(Particle.REDSTONE, cx - o2 + 1, y, z + 1, 0, 0.001, 1, 0, 1, new Particle.DustOptions(Color.YELLOW, 1));
								}
							}
						}
					}
					
					// ========== O3 ==========
					
					if (renderParticles(cx, cz, o3)) {
						// SOUTH
						for (int y = 200; y > 0; y--) {
							for (int x = cx - o3; x < cx + o3; x++) {
								if (isPlayerClose(x + 1, y, cz + o3)) {
									w.spawnParticle(Particle.REDSTONE, x + 1, y, cz + o3, 0, 0.001, 1, 0, 1, new Particle.DustOptions(Color.RED, 1));
								}
							}
						}
						
						// EAST
						for (int y = 200; y > 0; y--) {
							for (int z = cz - o3; z < cz + o3; z++) {
								if (isPlayerClose(cx + o3, y, z + 1)) {
									w.spawnParticle(Particle.REDSTONE, cx + o3, y, z + 1, 0, 0.001, 1, 0, 1, new Particle.DustOptions(Color.RED, 1));
								}
							}
						}
						
						// NORTH
						for (int y = 200; y > 0; y--) {
							for (int x = cx - o3; x < cx + o3; x++) {
								if (isPlayerClose(x + 1, y, cz - o3 + 1)) {
									w.spawnParticle(Particle.REDSTONE, x + 1, y, cz - o3 + 1, 0, 0.001, 1, 0, 1, new Particle.DustOptions(Color.RED, 1));
								}
							}
						}
						
						// WEST
						for (int y = 200; y > 0; y--) {
							for (int z = cz - o3; z < cz + o3; z++) {
								if (isPlayerClose(cx - o3 + 1, y, z + 1)) {
									w.spawnParticle(Particle.REDSTONE, cx - o3 + 1, y, z + 1, 0, 0.001, 1, 0, 1, new Particle.DustOptions(Color.RED, 1));
								}
							}
						}
					}
					
					// ========= FORCE BARRIER =========
					
					if (lastborder) {
						if (renderParticles(cx, cz, 500)) {
							// SOUTH
							for (int y = 200; y > 0; y--) {
								for (int x = cx - 500; x < cx + 500; x++) {
									if (isPlayerClose(x + 1, y, cz + 500)) {
										w.spawnParticle(Particle.REDSTONE, x + 1, y, cz + 500, 0, 0.001, 1, 0, 1, new Particle.DustOptions(Color.BLACK, 1));
									}
								}
							}
							
							// EAST
							for (int y = 200; y > 0; y--) {
								for (int z = cz - 500; z < cz + 500; z++) {
									if (isPlayerClose(cx + 500, y, z + 1)) {
										w.spawnParticle(Particle.REDSTONE, cx + 500, y, z + 1, 0, 0.001, 1, 0, 1, new Particle.DustOptions(Color.BLACK, 1));
									}
								}
							}
							
							// NORTH
							for (int y = 200; y > 0; y--) {
								for (int x = cx - 500; x < cx + 500; x++) {
									if (isPlayerClose(x + 1, y, cz - 500 + 1)) {
										w.spawnParticle(Particle.REDSTONE, x + 1, y, cz - 500 + 1, 0, 0.001, 1, 0, 1, new Particle.DustOptions(Color.BLACK, 1));
									}
								}
							}
							
							// WEST
							for (int y = 200; y > 0; y--) {
								for (int z = cz - 500; z < cz + 500; z++) {
									if (isPlayerClose(cx - 500 + 1, y, z + 1)) {
										w.spawnParticle(Particle.REDSTONE, cx - 500 + 1, y, z + 1, 0, 0.001, 1, 0, 1, new Particle.DustOptions(Color.BLACK, 1));
									}
								}
							}
						}
					}
				}
			}
			
		}, 0, 5);
	}
	
	public static boolean isPlayerClose(double x, double y, double z) {
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.getLocation().getX() > x-d && p.getLocation().getX() < x+d && p.getLocation().getY() > y-d && p.getLocation().getY() < y+d && p.getLocation().getZ() > z-d && p.getLocation().getZ() < z+d)
				return true;
		}
		
		return false;
	}
	
	public static boolean renderParticles(int cx, int cz, int dist) {
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			if ((p.getLocation().getX() < cx + dist + d && p.getLocation().getX() > cx + dist - d) ||
				(p.getLocation().getX() < cx - dist + d && p.getLocation().getX() > cx - dist - d) ||	
				(p.getLocation().getZ() < cz + dist + d && p.getLocation().getZ() > cz + dist - d) || 
				(p.getLocation().getZ() < cz - dist + d && p.getLocation().getZ() > cz - dist - d)) {
				return true;
			}
		}
		return false;
	}
}
