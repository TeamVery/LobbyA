package com.teamverymc.manage.event;

import com.teamverymc.configframework.cfg;
import net.kyori.adventure.text.Component;
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

import java.util.Objects;

import static com.teamverymc.manage.main.c;
import static com.teamverymc.manage.main.p;
import static org.bukkit.Bukkit.getServer;

public class joinquitevent implements Listener {

    ConsoleCommandSender console = getServer().getConsoleSender();

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        boolean hsb = player.hasPlayedBefore();

        if(!hsb) {
            if (cfg.get(p, c).getBoolean("첫접속.소리.활성화")) {
                player.playSound(player.getLocation(), Sound.valueOf(cfg.get(p, c).getString("첫접속.소리.사운드")), SoundCategory.MASTER, 1, 1);
            }
            if (cfg.get(p, c).getBoolean("첫접속.명령어(콘솔).활성화")) {
                String cmd = cfg.get(p, c).getString("첫접속.명령어(콘솔).명령어");
                Bukkit.dispatchCommand(console, Objects.requireNonNull(cmd)
                        .replace("&", "§")
                        .replace("%player%", player.getName())
                        .replace("%n%", "\n"));
            }
            if (cfg.get(p, c).getBoolean("첫접속.폭죽")) {
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
            if (cfg.get(p, c).getBoolean("첫접속.입장 메시지.활성화")) {
                if (!(cfg.get(p, c).getString("첫접속.입장 메시지.내용") == null)) {
                    e.joinMessage(Component.text(Objects.requireNonNull(cfg.get(p, c).getString("첫접속.입장"))
                            .replace("&", "§")
                            .replace("%player%", player.getName())
                            .replace("%n%", "\n")));
                }
            } else {
                e.joinMessage(null);
            }
        } else {
            if (cfg.get(p, c).getBoolean("재접속.소리.활성화")) {
                player.playSound(player.getLocation(), Sound.valueOf(cfg.get(p, c).getString("재접속.소리.사운드")), SoundCategory.MASTER, 1, 1);
            }
            if (cfg.get(p, c).getBoolean("재접속.명령어(콘솔).활성화")) {
                String cmd = cfg.get(p, c).getString("재접속.명령어(콘솔).명령어");
                Bukkit.dispatchCommand(console, Objects.requireNonNull(cmd)
                        .replace("&", "§")
                        .replace("%player%", player.getName())
                        .replace("%n%", "\n"));
            }
            if (cfg.get(p, c).getBoolean("재접속.폭죽")) {
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
            if (cfg.get(p, c).getBoolean("재접속.입장 메시지.활성화")) {
                if (!(cfg.get(p, c).getString("재접속.입장 메시지.내용") == null)) {
                    e.joinMessage(Component.text(Objects.requireNonNull(cfg.get(p, c).getString("재접속.입장 메시지.내용"))
                            .replace("&", "§")
                            .replace("%player%", player.getName())
                            .replace("%n%", "\n")));
                }
            } else {
                e.joinMessage(null);
            }
        }
        if (cfg.get(p, c).getBoolean("초기화")) {
            player.getInventory().clear();
        }
    }

    @EventHandler
    void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (cfg.get(p, c).getBoolean("재접속.퇴장 메시지.활성화")) {
            if (!(cfg.get(p, c).getString("재접속.퇴장 메시지.내용") == null)) {
                e.quitMessage(Component.text((Objects.requireNonNull(cfg.get(p, c).getString("재접속.퇴장 메시지.내용"))
                        .replace("&", "§")
                        .replace("%player%", player.getName())
                        .replace("%n%", "\n"))));
            }
        } else {
            e.quitMessage(null);
        }
    }
}
