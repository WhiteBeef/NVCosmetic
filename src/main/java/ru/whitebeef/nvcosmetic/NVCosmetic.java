package ru.whitebeef.nvcosmetic;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import ru.whitebeef.nvcosmetic.commands.NVCosmecticCommandExecutor;
import ru.whitebeef.nvcosmetic.handlers.CosmeticHandler;
import ru.whitebeef.nvcosmetic.managers.CosmeticManager;
import ru.whitebeef.nvcosmetic.utils.database.Database;

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
        getCommand("nvcosmetic").setExecutor(new NVCosmecticCommandExecutor());
    }

    public void reload() {
        instance = this;
        saveDefaultConfig();
        cosmeticManager = new CosmeticManager();
        database = Database.setupDatabase(this);
    }


    @Override
    public void onDisable() {
        database.close();
    }

    public static CosmeticManager getCosmeticManager() {
        return cosmeticManager;
    }

    public static Database getDatabase() {
        return database;
    }
}
