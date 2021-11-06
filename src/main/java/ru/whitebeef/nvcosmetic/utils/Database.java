package ru.whitebeef.nvcosmetic.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import ru.whitebeef.nvcosmetic.cosmetic.Cosmetic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public abstract class Database {

    private static Database database;
    public static final String WEAR_TABLE_NAME = "NVCosmeticWear";
    public static final String SHOP_TABLE_NAME = "NVCosmeticShop";

    public static Database setupDatabase(Plugin plugin) {
        if (plugin.getConfig().getBoolean("database.use-mysql")) {
            try {
                database = new MySQL(plugin.getConfig());
                setupTables();
                return database;
            } catch (Exception ex) {
                Bukkit.getLogger().info("Couldn't connect to the database! Using SQLite instead.");
            }
        }
        database = new SQLite(plugin.getDataFolder());
        setupTables();
        return database;
    }

    public static void closeDatabase() {
        if (database != null)
            database.close();
    }

    private static final List<String> TABLES = Arrays.asList(
            "CREATE TABLE IF NOT EXISTS " + WEAR_TABLE_NAME + "(`uuid` varchar(64), `namespace` varchar(64), `color` int(4) );",
            "CREATE TABLE IF NOT EXISTS " + SHOP_TABLE_NAME + "(`uuid` varchar(64), `namespace` varchar(64) );");

    public static void setupTables() {
        for (String query : TABLES)
            try (Connection con = openConnection();
                 PreparedStatement ps = con.prepareStatement(query)) {
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }


    public abstract boolean hasCosmetic(Player player, String namespace);

    public abstract void wearCosmetic(Player player, Cosmetic cosmetic);

    public static Connection openConnection() throws SQLException {
        return database.getConnection();
    }

    public abstract Connection getConnection() throws SQLException;

    public abstract void close();


}
