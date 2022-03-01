package ru.whitebeef.nvcosmetic.cosmetic;

import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

public interface Cosmetable {

    Cosmetic getCosmetic();

    ArmorStand getArmorStand();

    void wearCosmetic();

    void wearCosmetic(Cosmetic cosmetic);
}
