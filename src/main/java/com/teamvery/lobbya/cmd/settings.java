package com.teamvery.lobbya.cmd;

import com.teamvery.lobbya.config;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.jetbrains.annotations.NotNull;

public class settings implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        Player player = (Player) sender;

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                config.reload();
                player.sendMessage("§a리로드가 완료되었습니다!");

                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

                if (config.config.getBoolean("시간 고정")) {
                    String cmd = "gamerule doDaylightCycle false";
                    Bukkit.dispatchCommand(console, cmd);
                } else {
                    String cmd = "gamerule doDaylightCycle true";
                    Bukkit.dispatchCommand(console, cmd);
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
            player.sendMessage("§7---------- §3LobbyA 플러그인 명령어 §7----------");
            player.sendMessage("§6/lobbya reload §7- LobbyA플러그인의 Config를 리로드합니다.");
        }
        return false;
    }
}
