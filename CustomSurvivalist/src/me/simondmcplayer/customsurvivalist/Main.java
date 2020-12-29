package me.simondmcplayer.customsurvivalist;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.simondmcplayer.customsurvivalist.cmd.Cmd;
import me.simondmcplayer.customsurvivalist.config.GUIClick;
import me.simondmcplayer.customsurvivalist.config.NumGUIClick;
import me.simondmcplayer.customsurvivalist.config.SettingsGUIClick;
import me.simondmcplayer.customsurvivalist.game.Game;
import me.simondmcplayer.customsurvivalist.game.Particles;
import me.simondmcplayer.customsurvivalist.out.Out1;
import me.simondmcplayer.customsurvivalist.out.Out2;
import me.simondmcplayer.customsurvivalist.out.Out3;
import me.simondmcplayer.customsurvivalist.out.OutForce;
import me.simondmcplayer.customsurvivalist.up.Up1;
import me.simondmcplayer.customsurvivalist.up.Up2;

public class Main extends JavaPlugin implements Listener {
	
	public static DataManager data;
	
	@Override
	public void onEnable() {
		data = new DataManager(this);
		this.getCommand("svh").setExecutor(new Cmd());
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
		Game.game();
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
