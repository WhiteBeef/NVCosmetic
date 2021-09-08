package ru.whitebeef.nvcosmetic.utils.database;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Database {

	private static Database database;

	public static void setupDatabase(Plugin plugin) {
		if (plugin.getConfig().getBoolean("connection.use-mysql")) {
			try {
				database = new MySQL(plugin.getConfig());
				return;
			} catch (Exception ex) {
				Bukkit.getLogger().info("Couldn't connect to the database! Using SQLite instead.");
			}
		}
		database = new SQLite(plugin.getDataFolder());
	}

	public static void closeDatabase() {
		database.close();
	}

	private static final String TEST_TABLE = "CREATE TABLE IF NOT EXISTS Test(`UUID` varchar(64) NOT NULL, `IP` varchar(64), `Discord` BIGINT(20), PRIMARY KEY(`UUID`));";

	public static void setupTables() {
		try (Connection con = openConnection();
			 PreparedStatement ps = con.prepareStatement(TEST_TABLE)) {
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection openConnection() throws SQLException {
		return database.getConnection();
	}

	public abstract Connection getConnection() throws SQLException;

	public abstract void close();

}
