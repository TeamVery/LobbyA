package com.teamverymc.manage.event;

import com.teamverymc.configframework.cfg;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.Objects;

import static com.teamverymc.manage.main.*;

public class playerevents implements Listener {

    @EventHandler
    void onDamage(EntityDamageEvent e) {
        if (cfg.get(p, c).getBoolean("플레이어 무적")) {
            if(e.getEntity().getType() == EntityType.PLAYER) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    void OnPVP(EntityDamageByEntityEvent e) {
        if (cfg.get(p, c).getBoolean("플레이어 PVP 비활성화")) {
            if (e.getDamager() instanceof Player){
                if (e.getEntity() instanceof Player) {
                    e.setCancelled(true);
                    e.getDamager().sendMessage(Component.text(Objects.requireNonNull(cfg.get(p, m).getString("CANCEL_EVENT-PVP"))));
                }
            }
        }
    }

    @EventHandler
    void onHunger(FoodLevelChangeEvent e) {
        if (cfg.get(p, c).getBoolean("플레이어 허기 비활성화")) {
            e.setFoodLevel(20);
            e.setCancelled(true);
        }
    }

    @EventHandler
    void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (cfg.get(p, c).getBoolean("블럭 설치금지")) {
            if (!(player.hasPermission("manage.bypass.blockplace"))) {
                player.sendMessage(Component.text(Objects.requireNonNull(cfg.get(p, m).getString("CANCEL_EVENT-BLOCK_PLACE"))));
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (cfg.get(p, c).getBoolean("블럭 파괴금지")) {
            if (!(player.hasPermission("manage.bypass.blockbreak"))) {
                e.setCancelled(true);
                player.sendMessage(Component.text(Objects.requireNonNull(cfg.get(p, m).getString("CANCEL_EVENT-BLOCK_BREAK"))));
            }
        }
    }

    @EventHandler
    void onPickupItem(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player player) {
            if (cfg.get(p, c).getBoolean("아이템 획득 비활성화")) {
                if (!(player.hasPermission("manage.bypass.pickupitem"))) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    void onDropItem(PlayerDropItemEvent e) {
        if (cfg.get(p, c).getBoolean("아이템 드랍 비활성화")) {
            if (!(e.getPlayer().hasPermission("manage.bypass.dropitem"))) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(Component.text(Objects.requireNonNull(cfg.get(p, m).getString("CANCEL_EVENT-ITEM_DROP"))));
            }
        }
    }

    @EventHandler
    void onKick(PlayerKickEvent e) {
        if (cfg.get(p, c).getBoolean("플레이어 움직임 비활성화")) {
            if (e.reason().contains(Component.text("Flying is not enabled on this server"))) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (cfg.get(p, c).getBoolean("플레이어 상호작용 비활성화")) {
            if (!(player.hasPermission("manage.bypass.interact"))) {
                player.sendActionBar(Component.text(Objects.requireNonNull(cfg.get(p, m).getString("CANCEL_EVENT-PLAYER_INTERACT"))));
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    void onWeather(WeatherChangeEvent e) {
        if (cfg.get(p, c).getBoolean("날씨 비활성화")) {
            e.setCancelled(e.toWeatherState());
        }
    }

    @EventHandler
    void onEntitySpawn(final EntitySpawnEvent e) {
        if (cfg.get(p, c).getBoolean("엔티티 소환 차단")) {
            Entity entity = e.getEntity();
            if (!(entity instanceof Player) && !(entity instanceof Item))
                entity.remove();
        }
    }

    @EventHandler
    void onChat(AsyncChatEvent e) {
        Player player = e.getPlayer();
        if (cfg.get(p, c).getBoolean("플레이어 채팅 비활성화")) {
            if (!(player.hasPermission("manage.bypass.chat"))) {
                e.setCancelled(true);
                player.sendMessage(Component.text(Objects.requireNonNull(cfg.get(p, m).getString("CANCEL_EVENT-CHAT"))));
            }
        }
    }

    @EventHandler
    void onMovement(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (cfg.get(p, c).getBoolean("플레이어 움직임 비활성화")) {
            if (!(player.hasPermission("manage.bypass.movement"))) {
                e.setCancelled(true);
                player.sendActionBar(Component.text(Objects.requireNonNull(cfg.get(p, m).getString("CANCEL_EVENT-PLAYER_MOVEMENT"))));
            }
        }
    }

    @EventHandler
    void onInv(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (cfg.get(p, c).getBoolean("인벤토리 상호작용 비활성화")) {
            if (!(player.hasPermission("manage.bypass.inv"))) {
                e.setCancelled(true);
                player.sendMessage(Component.text(Objects.requireNonNull(cfg.get(p, m).getString("CANCEL_EVENT-INVENTORY_INTERACT"))));
            }
        }
    }

    @EventHandler
    void onInv2(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        if (cfg.get(p, c).getBoolean("인벤토리 상호작용 비활성화")) {
            if (!(player.hasPermission("manage.bypass.inv"))) {
                e.setCancelled(true);
                player.sendMessage(Component.text(Objects.requireNonNull(cfg.get(p, m).getString("CANCEL_EVENT-INVENTORY_INTERACT"))));
            }
        }
    }

    @EventHandler
    void onInv3(PlayerSwapHandItemsEvent e) {
        Player player = e.getPlayer();
        if (cfg.get(p, c).getBoolean("인벤토리 상호작용 비활성화")) {
            if (!(player.hasPermission("manage.bypass.inv"))) {
                e.setCancelled(true);
                player.sendMessage(Component.text(Objects.requireNonNull(cfg.get(p, m).getString("CANCEL_EVENT-INVENTORY_INTERACT"))));
            }
        }
    }
}