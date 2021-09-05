package ru.whitebeef.nvcosmetic.cosmetic;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CosmeticPlayer implements Cosmetable {

    private Player player;
    private Cosmetic cosmetic;
    private ItemStack item;
    private ArmorStand armorStand;


    //TODO: Добавить получение CosmeticPlayer из базы данных
    public CosmeticPlayer(Player player) {
        this.player = player;
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
