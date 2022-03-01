package ru.whitebeef.nvcosmetic.managers;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.whitebeef.nvcosmetic.NVCosmetic;
import ru.whitebeef.nvcosmetic.cosmetic.Cosmetic;
import ru.whitebeef.nvcosmetic.cosmetic.CosmeticEntity;
import ru.whitebeef.nvcosmetic.cosmetic.CosmeticPosition;
import ru.whitebeef.nvcosmetic.utils.database.Database;

import java.util.Collection;
import java.util.HashMap;

public class CosmeticManager {

    @Nullable
    public static CosmeticEntity getCosmeticEntity(@NotNull LivingEntity entity) {
        return NVCosmetic.getCosmeticManager().cosmeticEntities.get(entity);
    }

    private static Database database;
    // namespace - Cosmetic
    private HashMap<String, Cosmetic> cosmetics = new HashMap<>();
    private HashMap<LivingEntity, CosmeticEntity> cosmeticEntities = new HashMap<>();

    public CosmeticManager() {
        Database.closeDatabase();
        database = Database.setupDatabase(NVCosmetic.getInstance());
        registerCosmetics();
        updateWearingCosmetics();
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

    public void removeAllCosmeticEntities() {
        cosmeticEntities.forEach((player, cosmeticPlayer) -> cosmeticPlayer.getArmorStand().remove());
        cosmeticEntities.clear();
    }

    public void removeCosmeticEntity(CosmeticEntity entity) {
        if (entity == null) return;
        entity.getArmorStand().remove();
    }

    public void addCosmeticEntity(LivingEntity livingEntity, CosmeticEntity entity) {
        cosmeticEntities.put(livingEntity, entity);
    }


    private void updateWearingCosmetics() {
        for (CosmeticEntity entity : cosmeticEntities.values()) {
            ArmorStand as = entity.getArmorStand();
            if (as == null) {
                entity.wearCosmetic(entity.getCosmetic());
                return;
            }
            entity.getEntity().addPassenger(as);
        }
    }

}
