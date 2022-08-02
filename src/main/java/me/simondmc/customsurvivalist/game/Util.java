package me.simondmc.customsurvivalist.game;

import me.simondmc.customsurvivalist.Main;
import me.simondmc.customsurvivalist.config.GUIClick;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Util {
    public static Location getRandomLocation(World w) {
        Location l = w.getSpawnLocation();
        double r1 = Math.floor(Math.random() * 200000);
        double r2 = Math.floor(Math.random() * 200000);
        l.setX(r1-100000);
        l.setY(w.getHighestBlockYAt((int)r1-100000, (int)r2-100000)+1);
        l.setZ(r2-100000);
        return l;
    }

    public static void postTP(Player player) {
        GUIClick.l1 = Math.round(Math.floor(player.getLocation().getX()));
        GUIClick.i1= (int) GUIClick.l1;
        GUIClick.l2 = Math.round(Math.floor(player.getLocation().getZ()));
        GUIClick.i2= (int) GUIClick.l2;

        // gives resistance to combat fall damage, easier than disabling it
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.teleport(player.getLocation());
            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 4));
        }
        Main.getData().set("data.cx", GUIClick.i1);
        Main.getData().set("data.cz", GUIClick.i2);
        Main.saveData();
        player.sendMessage(ChatColor.GREEN + "Set the center to " + Math.round(Math.floor(player.getLocation().getX())) + ", " + Math.round(Math.floor(player.getLocation().getZ())) + "!");
        player.getWorld().setGameRuleValue("sendCommandFeedback", "false");
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(),
                "spawnpoint @a " + Math.round(Math.floor(player.getLocation().getX())) + " " +
                        Math.round(Math.floor(player.getLocation().getY())) + " " +
                        Math.round(Math.floor(player.getLocation().getZ())));
        player.getWorld().setGameRuleValue("sendCommandFeedback", "true");
        Bukkit.getWorld(player.getWorld().getName()).setSpawnLocation(player.getLocation());

        GUIClick.game = false;
        Bukkit.getScheduler().cancelTask(GUIClick.repeat);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.removePotionEffect(PotionEffectType.GLOWING);
        }

        player.closeInventory();
    }

    public static Location getOcean(World w) {
        for (int i = 0; i < 100; i++) {
            Location l = getRandomLocation(w);
            if (w.getBiome(l.getBlockX(), l.getBlockZ()).toString().contains("OCEAN")) {
                System.out.println("[CustomSurvivalist] Debug: Found " + w.getBiome(l.getBlockX(), l.getBlockZ()) + " on attempt #" + (i+1));
                return l;
            }
        }
        return null;
    }

    public static String formatString(String s) {
        String[] words = s.split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String firstLetter = word.substring(0,1).toUpperCase();
            String restLetters = word.substring(1).toLowerCase();
            word = firstLetter + restLetters;
            if (i == 0) {
                sb.append(word);
            } else {
                sb.append(" ").append(word);
            }
        }
        return sb.toString();
    }
}
