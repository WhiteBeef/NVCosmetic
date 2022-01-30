package ru.whitebeef.nvcosmetic.handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.whitebeef.nvcosmetic.NVCosmetic;
import ru.whitebeef.nvcosmetic.cosmetic.CosmeticEntity;

public class CosmeticHandler implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        CosmeticEntity cosmetic = new CosmeticEntity(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        NVCosmetic.getCosmeticManager().
    }


}
