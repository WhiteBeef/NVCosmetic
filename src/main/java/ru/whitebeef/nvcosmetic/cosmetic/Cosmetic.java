package ru.whitebeef.nvcosmetic.cosmetic;

import org.bukkit.Material;

public class Cosmetic implements Cloneable {

    private String namespace;
    private String name;
    private int customModelData;
    private int color;
    private Material material;
    private CosmeticPosition position;

    public Cosmetic(String namespace, String name, int customModelData, Material material, CosmeticPosition position) {
        this.namespace = namespace;
        this.name = name;
        this.customModelData = customModelData;
        this.material = material;
        this.position = position;
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
