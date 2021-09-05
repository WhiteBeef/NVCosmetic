package ru.whitebeef.nvcosmetic.cosmetic;

public class Cosmetic {

    private String namespace;
    private int customModelData;

    public Cosmetic(String namespace, int customModelData) {
        this.namespace = namespace;
        this.customModelData = customModelData;
    }

    public String getNamespace() {
        return namespace;
    }

    public int getCustomModelData() {
        return customModelData;
    }
}
