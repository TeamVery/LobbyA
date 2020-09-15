package com.teamvery.lobbya.event;

import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

import static com.teamvery.lobbya.config.config;

public class joinquitevent implements @NotNull Listener {

    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        boolean hsb = player.hasPlayedBefore();

        if(!hsb) {
            if (config.getBoolean("첫접속.소리.활성화")) {
                player.playSound(player.getLocation(), Sound.valueOf(config.getString("첫접속.소리.사운드")), SoundCategory.MASTER, 1, 1);
            }
            if (config.getBoolean("첫접속.명령어(콘솔).활성화")) {
                String cmd = config.getString("첫접속.명령어(콘솔).명령어");
                Bukkit.dispatchCommand(console, Objects.requireNonNull(cmd));
            }
            if (config.getBoolean("첫접속.폭죽")) {
                Firework firework = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
                FireworkMeta meta = firework.getFireworkMeta();
                FireworkEffect.Builder builder = FireworkEffect.builder();
                builder.withTrail().withFlicker().withColor(Color.GREEN, Color.WHITE, Color.YELLOW, Color.BLUE,
                        Color.FUCHSIA, Color.PURPLE, Color.MAROON, Color.LIME, Color.ORANGE)
                        .with(FireworkEffect.Type.BALL_LARGE);
                meta.addEffect(builder.build());
                meta.setPower(1);
                firework.setFireworkMeta(meta);
                firework.detonate();
            }
            if (!(config.getString("첫접속.입장") == null)) {
                e.setJoinMessage(Objects.requireNonNull(config.getString("첫접속.입장"))
                        .replace("&", "§")
                        .replace("%player%", player.getName()));
            }
        } else {
            if (config.getBoolean("재접속.소리.활성화")) {
                player.playSound(player.getLocation(), Sound.valueOf(config.getString("재접속.소리.사운드")), SoundCategory.MASTER, 1, 1);
            }
            if (config.getBoolean("재접속.명령어(콘솔).활성화")) {
                String cmd = config.getString("재접속.명령어(콘솔).명령어");
                Bukkit.dispatchCommand(console, Objects.requireNonNull(cmd));
            }
            if (config.getBoolean("재접속.폭죽")) {
                Firework firework = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
                FireworkMeta meta = firework.getFireworkMeta();
                FireworkEffect.Builder builder = FireworkEffect.builder();
                builder.withTrail().withFlicker().withColor(Color.GREEN, Color.WHITE, Color.YELLOW, Color.BLUE,
                        Color.FUCHSIA, Color.PURPLE, Color.MAROON, Color.LIME, Color.ORANGE)
                        .with(FireworkEffect.Type.BALL_LARGE);
                meta.addEffect(builder.build());
                meta.setPower(1);
                firework.setFireworkMeta(meta);
                firework.detonate();
            }
            if (!(config.getString("재접속.입장") == null)) {
                e.setJoinMessage(Objects.requireNonNull(config.getString("재접속.입장"))
                        .replace("&", "§")
                        .replace("%player%", player.getName()));
            }
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
