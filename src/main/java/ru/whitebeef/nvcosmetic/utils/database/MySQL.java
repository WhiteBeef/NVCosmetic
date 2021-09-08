package ru.whitebeef.nvcosmetic.utils.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQL extends Database {

	private HikariDataSource dataSource;

	private final String host;
	private final String database;
	private final String username;
	private final String password;
	private final int port;

	public MySQL(FileConfiguration config) {
		host = config.getString("connection.mysql.host");
		port = config.getInt("connection.mysql.port");
		database = config.getString("connection.mysql.database");
		username = config.getString("connection.mysql.username");
		password = config.getString("connection.mysql.password");

		setupPool();
	}

	private void setupPool() {
		HikariConfig config = new HikariConfig();
		try {
			config.setDriverClassName(Class.forName("com.mysql.cj.jdbc.Driver").getName());
		} catch (ClassNotFoundException e) {
			config.setDriverClassName("com.mysql.jdbc.Driver");
		}
		config.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false");
		config.setUsername(username);
		config.setPassword(password);
		dataSource = new HikariDataSource(config);
	}

	@Override
	public void close() {
		if (dataSource != null && !dataSource.isClosed()) dataSource.close();
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}
