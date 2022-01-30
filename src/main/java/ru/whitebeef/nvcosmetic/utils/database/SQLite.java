package ru.whitebeef.nvcosmetic.utils.database;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import ru.whitebeef.nvcosmetic.NVCosmetic;
import ru.whitebeef.nvcosmetic.cosmetic.Cosmetic;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class SQLite extends Database {

    private static final String DATABASE = "database";
    private final File dataFolder;

    public SQLite(File folder) {
        dataFolder = new File(folder, DATABASE + ".db");
        if(!dataFolder.exists()) {
            try {
                if(!dataFolder.createNewFile())
                    Bukkit.getLogger().info("Could not create a database file!");
            } catch(IOException e) {
                Bukkit.getLogger().info("File write error: " + DATABASE + ".db");
            }
        }
    }

    @Override
    public boolean hasCosmetic(LivingEntity entity, String namespace) {
        String SQL = "SELECT * FROM nvcosmeticshop WHERE `uuid` = '" + entity.getUniqueId() + "' AND `namespace` = '" + namespace + "';";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet rs = statement.executeQuery()) {
            return rs.next();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void wearCosmetic(LivingEntity entity, Cosmetic cosmetic) {
        Bukkit.getScheduler().runTaskAsynchronously(NVCosmetic.getInstance(), () -> {
            String SQL = "INSERT INTO " + WEAR_TABLE_NAME + " (uuid, namespace, color) VALUES (?,?,?) ;";
            try(Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL)) {
                statement.setString(1, entity.getUniqueId().toString());
                statement.setString(2, cosmetic.getNamespace());
                statement.setInt(3, cosmetic.getColor());
                statement.executeUpdate();
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public Cosmetic getCosmetic(LivingEntity entity) {
        Cosmetic cosmetic = null;
        String SQL = "SELECT FROM " + WEAR_TABLE_NAME + " WHERE `uuid` = ? ;";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, entity.getUniqueId().toString());
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String namespace = rs.getString("namespace");
                cosmetic = NVCosmetic.getCosmeticManager().getCosmetic(namespace);
            }
        } catch(SQLNonTransientConnectionException ex) {
            Bukkit.getLogger().severe("База отвалилась, не удалось получить косметику для: " +
                    entity.getUniqueId() + " (" + entity.getName() + ")");
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return cosmetic;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
    }


    @Override
    public void close() {
        // We have nothing to close
    }

}
