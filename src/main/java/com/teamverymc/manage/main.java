package com.teamverymc.manage;

import com.teamverymc.configframework.cfg;
import com.teamverymc.manage.cmd.settings;
import com.teamverymc.manage.event.joinquitevent;
import com.teamverymc.manage.event.playerevents;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class main extends JavaPlugin {

    public static String p = "Manage"; // plugin name
    public static String c = "config.yml";
    public static String m = "messages.yml";

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("manage")).setExecutor(new settings());
        getServer().getPluginManager().registerEvents(new joinquitevent(), this);
        getServer().getPluginManager().registerEvents(new playerevents(), this);

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        cfg.makeData(p, c);
        cfg.save(p, c, true);

        cfg.makeData(p, m);

        if (cfg.get(p, c).getBoolean("시간 고정.활성화")) {
            for (String worlds : cfg.get(p, c).getStringList("시간 고정.월드")) {
                if (Bukkit.getWorld(worlds) != null) {
                    Objects.requireNonNull(Bukkit.getWorld(worlds)).setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                } else {
                    getLogger().info(ChatColor.RED + worlds + " 는 존재하지 않는 월드입니다.");
                }
            }
        } else {
            for (String worlds : cfg.get(p, c).getStringList("시간 고정.월드")) {
                if (Bukkit.getWorld(worlds) != null) {
                    Objects.requireNonNull(Bukkit.getWorld(worlds)).setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
                } else {
                    getLogger().info(ChatColor.RED + worlds + " 는 존재하지 않는 월드입니다.");
                }
            }
        }
    }
}
