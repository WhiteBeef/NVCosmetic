package ru.whitebeef.nvcosmetic.utils.database;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import ru.whitebeef.nvcosmetic.NVCosmetic;
import ru.whitebeef.nvcosmetic.cosmetic.Cosmetic;

import java.sql.*;

public class MySQL extends Database {

    private static Connection connection = null;

    private final String host;
    private final String database;
    private final String username;
    private final String password;
    private final String port;
    private final String SQL;


    public MySQL(FileConfiguration config) throws SQLException {
        host = config.getString("database.host");
        port = config.getString("database.port");
        database = config.getString("database.database");
        username = config.getString("database.username");
        password = config.getString("database.password");
        SQL = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useUnicode=true&passwordCharacterEncoding=utf8&characterEncoding=utf8&useSSL=false&useTimezone=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        setupPool();
    }

    private void setupPool() throws SQLException {
        connection = getConnection();
    }


    private synchronized void connect() {
        try {
            if (!connection.isClosed()) {
                return;
            }
        } catch (Exception ignored) {
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(SQL, username, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException ignored) {
        }
    }


    public Connection getConnection(boolean forceConnect) throws SQLException {
        if (forceConnect) connect();
        return getConnection();
    }

    @Override
    public boolean hasCosmetic(Player player, String namespace) {
        String SQL = "SELECT * FROM nvcosmeticshop WHERE `uuid` = '" + player.getUniqueId() + "' AND `namespace` = '" + namespace + "';";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL);
             ResultSet rs = statement.executeQuery()) {
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void wearCosmetic(Player player, Cosmetic cosmetic) {
        Bukkit.getScheduler().runTaskAsynchronously(NVCosmetic.getInstance(), () -> {
            String SQL = "INSERT INTO " + WEAR_TABLE_NAME + " (uuid, namespace, color) VALUES (?,?,?) ;";
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQL)) {
                statement.setString(1, player.getUniqueId().toString());
                statement.setString(2, cosmetic.getNamespace());
                statement.setInt(3, cosmetic.getColor());
                statement.executeUpdate();
            } catch (SQLNonTransientConnectionException ex) {
                try {
                    getConnection(true);
                    wearCosmetic(player, cosmetic);
                } catch (SQLException ignored) {
                    Bukkit.getLogger().severe("База отвалилась, не удалось надеть косметику: " + cosmetic.toString());
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) connect();
        return connection;
    }

}
