package ru.whitebeef.nvcosmetic.cosmetic;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public class CosmeticEntity implements Cosmetable {

    private LivingEntity entity;
    private Cosmetic cosmetic;
    private ItemStack item;
    private ArmorStand armorStand;


    //TODO: Добавить получение CosmeticPlayer из базы данных
    public CosmeticEntity(LivingEntity entity) {
        this.entity = entity;
    }


    @Override
    public void setCosmetic(Cosmetic cosmetic) {
        this.cosmetic = cosmetic;
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public ArmorStand getArmorStand() {
        return armorStand;
    }

    @Override
    public Cosmetic getCosmetic() {
        return cosmetic;
    }
}
