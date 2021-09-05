package ru.whitebeef.nvcosmetic.cosmetic;

import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

public interface Cosmetable {

    Cosmetic getCosmetic();

    void setCosmetic(Cosmetic cosmetic);

    ItemStack getItem();

    ArmorStand getArmorStand();

}
