package me.simondmc.customsurvivalist.game;

import me.simondmc.customsurvivalist.Main;
import org.bukkit.entity.Player;

public class RoleControl {
    public static String getRole(Player p) {
        try {
            return Main.getData().get("role." + p.getName().toLowerCase()).toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static void setRole(Player p, String s) {
        Main.getData().set("role." + p.getName().toLowerCase(), s);
        Main.saveData();
    }
}
