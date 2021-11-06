package ru.whitebeef.nvcosmetic;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import ru.whitebeef.nvcosmetic.cosmetic.CosmeticHandler;
import ru.whitebeef.nvcosmetic.cosmetic.CosmeticManager;
import ru.whitebeef.nvcosmetic.utils.Database;

public final class NVCosmetic extends JavaPlugin implements Listener {


    private static NVCosmetic instance;
    private static CosmeticManager cosmeticManager;
    private static Database database;

    public static NVCosmetic getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        reload();
        Bukkit.getPluginManager().registerEvents(new CosmeticHandler(), this);
    }

    public void reload() {
        instance = this;
        Database.closeDatabase();
        database = Database.setupDatabase(this);
        cosmeticManager = new CosmeticManager();
    }

    @Override
    public void onDisable() {
    }

    public static CosmeticManager getCosmeticManager() {
        return cosmeticManager;
    }
}
