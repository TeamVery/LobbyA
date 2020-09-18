package com.teamvery.manage.event;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import static com.teamvery.manage.config.config;

public class playerevents implements Listener {

    @EventHandler
    void onDamage(EntityDamageEvent e) {
        if (config.getBoolean("플레이어 무적")) {
            if(e.getEntity().getType() == EntityType.PLAYER) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    void OnPVP(EntityDamageByEntityEvent e) {
        if (config.getBoolean("플레이어 PVP 비활성화")) {
            if (e.getDamager() instanceof Player){
                if (e.getEntity() instanceof Player) {
                    e.setCancelled(true);
                    e.getDamager().sendMessage("§c서버 내 PVP가 비활성화 되어있습니다");
                }
            }
        }
    }

    @EventHandler
    void onHunger(FoodLevelChangeEvent e) {
        if (config.getBoolean("플레이어 허기 비활성화")) {
            e.setFoodLevel(20);
            e.setCancelled(true);
        }
    }

    @EventHandler
    void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (config.getBoolean("블럭 설치금지")) {
            if (!(player.hasPermission("manage.bypass.blockplace"))) {
                player.sendMessage("§c서버 내 블럭 설치가 비활성화 되어있습니다");
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (config.getBoolean("블럭 파괴금지")) {
            if (!(player.hasPermission("manage.bypass.blockbreak"))) {
                e.setCancelled(true);
                player.sendMessage("§c서버 내 블럭 파괴가 비활성화 되어있습니다");
            }
        }
    }

    @EventHandler
    void onPickupItem(EntityPickupItemEvent e) {
        Player player = (Player) e.getEntity();
        if (config.getBoolean("아이템 획득 비활성화")) {
            if (!(player.hasPermission("manage.bypass.pickupitem"))) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    void onDropItem(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        if (config.getBoolean("아이템 드랍 비활성화")) {
            if (!(player.hasPermission("manage.bypass.dropitem"))) {
                e.setCancelled(true);
                player.sendMessage("§c서버 내 아이템 드랍이 비활성화 되어있습니다");
            }
        }
    }

    @EventHandler
    void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (config.getBoolean("플레이어 상호작용 비활성화")) {
            if (!(player.hasPermission("manage.bypass.interact"))) {
                player.sendActionBar("§c§l서버 내 상호작용이 비활성화 되어있습니다");
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    void onWeather(WeatherChangeEvent e) {
        if (config.getBoolean("날씨 차단")) {
            e.setCancelled(e.toWeatherState());
        }
    }

    @EventHandler
    void onEntitySpawn(final EntitySpawnEvent e) {
        if (config.getBoolean("엔티티 소환 차단")) {
            Entity entity = e.getEntity();
            if (!(entity instanceof Player) && !(entity instanceof Item))
                entity.remove();
        }
    }

    @EventHandler
    void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (config.getBoolean("플레이어 채팅 비활성화")) {
            if (!(player.hasPermission("manage.bypass.chat"))) {
                e.setCancelled(true);
                player.sendMessage("§c서버 내 채팅이 비활성화 되어있습니다");
            }
        }
    }
}