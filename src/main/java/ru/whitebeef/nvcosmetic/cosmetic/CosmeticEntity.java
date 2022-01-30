package ru.whitebeef.nvcosmetic.cosmetic;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import ru.whitebeef.nvcosmetic.NVCosmetic;
import ru.whitebeef.nvcosmetic.managers.CosmeticManager;

public class CosmeticEntity implements Cosmetable {

    private final LivingEntity entity;
    private Cosmetic cosmetic;
    private ItemStack item;
    private ArmorStand armorStand;

    public CosmeticEntity(LivingEntity entity) {
        NVCosmetic.getCosmeticManager().addCosmeticEntity(entity, this);
        this.entity = entity;
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public ArmorStand getArmorStand() {
        return armorStand;
    }

    //TODO: сделать одевание косметики на энтити
    @Override
    public void wearCosmetic(Cosmetic cosmetic) {
        this.cosmetic = cosmetic;

    }

    // TODO: получение косметики из базы
    @Override
    public void wearCosmetic() {
        wearCosmetic(NVCosmetic.getDatabase().getCosmetic(entity));
    }

    @Override
    public Cosmetic getCosmetic() {
        return cosmetic;
    }
}
