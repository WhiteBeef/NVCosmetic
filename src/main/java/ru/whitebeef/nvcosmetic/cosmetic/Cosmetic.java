package ru.whitebeef.nvcosmetic.cosmetic;

import org.bukkit.Material;

public class Cosmetic {

    private String namespace;
    private String name;
    private int customModelData;
    private int color;
    private Material material;
    private CosmeticPosition position;

    public Cosmetic(String namespace, int customModelData) {
        this.namespace = namespace;
        this.customModelData = customModelData;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getNamespace() {
        return namespace;
    }

    public int getCustomModelData() {
        return customModelData;
    }
}
