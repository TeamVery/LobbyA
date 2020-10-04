package com.teamvery.manage.cmd;

import com.teamvery.manage.config;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.GameRule;
import org.bukkit.command.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class settings implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        Player player = (Player) sender;

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                config.reload();
                player.sendMessage("§a리로드가 완료되었습니다!");

                if (config.config.getBoolean("시간 고정.활성화")) {
                    Objects.requireNonNull(Bukkit.getWorld(Objects.requireNonNull(config.get().getString("시간 고정.월드"))))
                            .setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                } else {
                    Objects.requireNonNull(Bukkit.getWorld(Objects.requireNonNull(config.get().getString("시간 고정.월드"))))
                            .setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
                }
            }
            if (args[0].equalsIgnoreCase("debug")) {
                Firework firework = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
                FireworkMeta meta = firework.getFireworkMeta();
                FireworkEffect.Builder builder = FireworkEffect.builder();
                builder.withTrail().withFlicker().withColor(Color.GREEN, Color.WHITE, Color.YELLOW, Color.BLUE,
                        Color.FUCHSIA, Color.PURPLE, Color.MAROON, Color.LIME, Color.ORANGE)
                        .with(FireworkEffect.Type.BALL_LARGE);
                meta.addEffect(FireworkEffect.builder().withColor(Color.AQUA).trail(true).build());
                meta.setPower(1);
                firework.setFireworkMeta(meta);

                player.sendMessage("디버그 실행됨.");
            }
        } else {
            player.sendMessage("§7---------- §3Manage 플러그인 명령어 §7----------");
            player.sendMessage("§6/manage reload §7- Manage플러그인의 Config를 리로드합니다.");
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (args.length == 1) {
            List<String> arguments = new ArrayList<>();
            arguments.add("reload");
            arguments.add("debug");

            return arguments;
        }
        return null;
    }
}
