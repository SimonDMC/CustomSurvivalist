package me.simondmc.customsurvivalist;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.simondmc.customsurvivalist.cmd.Cmd;
import me.simondmc.customsurvivalist.config.GUIClick;
import me.simondmc.customsurvivalist.config.NumGUIClick;
import me.simondmc.customsurvivalist.config.SettingsGUIClick;
import me.simondmc.customsurvivalist.game.Game;
import me.simondmc.customsurvivalist.game.Particles;
import me.simondmc.customsurvivalist.out.Out1;
import me.simondmc.customsurvivalist.out.Out2;
import me.simondmc.customsurvivalist.out.Out3;
import me.simondmc.customsurvivalist.out.OutForce;
import me.simondmc.customsurvivalist.up.Up1;
import me.simondmc.customsurvivalist.up.Up2;

public class Main extends JavaPlugin implements Listener {
	
	public static me.simondmc.customsurvivalist.DataManager data;
	
	@Override
	public void onEnable() {
		data = new me.simondmc.customsurvivalist.DataManager(this);
		this.getCommand("svh").setExecutor(new Cmd());
		// registers all listener classes
		this.getServer().getPluginManager().registerEvents(new GUIClick(), this);
		this.getServer().getPluginManager().registerEvents(new Out1(), this);
		this.getServer().getPluginManager().registerEvents(new Out2(), this);
		this.getServer().getPluginManager().registerEvents(new Out3(), this);
		this.getServer().getPluginManager().registerEvents(new OutForce(), this);
		this.getServer().getPluginManager().registerEvents(new Up1(), this);
		this.getServer().getPluginManager().registerEvents(new Up2(), this);
		this.getServer().getPluginManager().registerEvents(new Game(), this);
		this.getServer().getPluginManager().registerEvents(new NumGUIClick(), this);
		this.getServer().getPluginManager().registerEvents(new SettingsGUIClick(), this);
		
		// starts repeating task in Game
		Game.game();
		
		// starts particles
		for (Player p : Bukkit.getOnlinePlayers()) {
			Bukkit.getScheduler().cancelTask(Particles.p);
			Particles.displayParticles(p);
		}
	}

	@Override
	public void onDisable() {

	}
	
	public static FileConfiguration getData() {
		return data.getConfig();
	}
	
	public static void saveData() {
		data.saveConfig();
	}
}
