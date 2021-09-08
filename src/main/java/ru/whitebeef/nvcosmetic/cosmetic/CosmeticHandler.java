package ru.whitebeef.nvcosmetic.cosmetic;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
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

    private ArmorStand spawnArmorStand(Player player) {
        ArmorStand armorStand = (ArmorStand) player.getWorld()
                .spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
        armorStand.setInvisible(true);
        armorStand.setInvulnerable(true);
        //armorStand.getLocation().setYaw(0);
        return armorStand;
    }
}
