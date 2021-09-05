package ru.whitebeef.nvcosmetic.cosmetic;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CosmeticManager {



    private void spawnArmorStand(Player player) {
        ArmorStand armorStand = (ArmorStand) player.getWorld()
                .spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
        armorStand.setBasePlate(false);
        armorStand.setArms(true);
        armorStand.getLocation().setYaw(0);
        player.getPlayer().addPassenger(armorStand);
    }

    @Override
    public void onDisable() {
        map.forEach((player, armorStand) -> armorStand.remove());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        spawnArmorStand(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        map.get(event.getPlayer()).remove();
    }

}
