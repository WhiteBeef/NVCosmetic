package ru.whitebeef.nvcosmetic.managers;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import ru.whitebeef.nvcosmetic.NVCosmetic;
import ru.whitebeef.nvcosmetic.cosmetic.Cosmetic;
import ru.whitebeef.nvcosmetic.cosmetic.CosmeticEntity;
import ru.whitebeef.nvcosmetic.cosmetic.CosmeticPosition;
import ru.whitebeef.nvcosmetic.utils.database.Database;

import java.util.Collection;
import java.util.HashMap;

public class CosmeticManager {

    private static Database database;
    // namespace - Cosmetic
    private HashMap<String, Cosmetic> cosmetics = new HashMap<>();
    private HashMap<Player, CosmeticEntity> cosmeticPlayers = new HashMap<>();

    public CosmeticManager() {
        Database.closeDatabase();
        database = Database.setupDatabase(NVCosmetic.getInstance());
        registerCosmetics();
    }

    private void registerCosmetics() {
        FileConfiguration config = NVCosmetic.getInstance().getConfig();
        for (String namespace : config.getConfigurationSection("cosmetics").getKeys(false)) {
            Cosmetic cosmetic = new Cosmetic(
                    namespace,
                    config.getString("cosmetics." + namespace + ".name"),
                    config.getInt("cosmetics." + namespace + ".customModelData"),
                    Material.valueOf(config.getString("cosmetics." + namespace + ".material").toUpperCase()),
                    CosmeticPosition.valueOf(config.getString("cosmetics." + namespace + ".position").toUpperCase())
            );
            cosmetics.put(namespace, cosmetic);
        }
    }


    public Cosmetic getCosmetic(String namespace) {
        return cosmetics.get(namespace);
    }

    public Collection<Cosmetic> getLoadedCosmetics() {
        return cosmetics.values();
    }

    public void removeAllCosmeticPlayers() {
        cosmeticPlayers.forEach((player, cosmeticPlayer) -> cosmeticPlayer.getArmorStand().remove());
        cosmeticPlayers.clear();
    }

    public void removeCosmeticPlayer(Player player) {
        cosmeticPlayers.get(player).getArmorStand().remove();
    }


}
