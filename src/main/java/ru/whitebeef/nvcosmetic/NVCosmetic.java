package ru.whitebeef.nvcosmetic;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class NVCosmetic extends JavaPlugin implements Listener {

    private HashMap<Player, ArmorStand> map = new HashMap<>();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getOnlinePlayers().forEach(player -> spawnArmorStand(player));
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            Bukkit.getOnlinePlayers().forEach(player -> {
                map.get(player).teleport(player);
                player.addPassenger(map.get(player));
            });
        }, 0l, 1l);
    }


}
