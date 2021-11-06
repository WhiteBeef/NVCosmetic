package ru.whitebeef.nvcosmetic.cosmetic;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.whitebeef.nvcosmetic.NVCosmetic;

public class CosmeticHandler implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        spawnArmorStand(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        NVCosmetic.getCosmeticManager().removeCosmeticPlayer(event.getPlayer());
    }

    private ArmorStand spawnArmorStand(LivingEntity entity) {
        ArmorStand armorStand = (ArmorStand) entity.getWorld()
                .spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
        armorStand.setInvisible(true);
        armorStand.setInvulnerable(true);
        return armorStand;
    }
}
