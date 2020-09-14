package com.teamvery.lobbya.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

import static com.teamvery.lobbya.config.config;

public class joinevent implements @NotNull Listener {

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        boolean hasjoined = player.hasPlayedBefore();

        if(!hasjoined) {
            e.setJoinMessage(Objects.requireNonNull(config.getString("첫접속.입장"))
                    .replace("&", "§")
                    .replace("%player%", player.getName()));
        } else {
            e.setJoinMessage(Objects.requireNonNull(config.getString("재접속.입장"))
                    .replace("&", "§")
                    .replace("%player%", player.getName()));
        }
        if (config.getBoolean("초기화")) {
            player.getInventory().clear();
        }
    }

    @EventHandler
    void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        e.setQuitMessage(Objects.requireNonNull(config.getString("재접속.퇴장"))
                .replace("&", "§")
                .replace("%player%", player.getName()));
    }
}
