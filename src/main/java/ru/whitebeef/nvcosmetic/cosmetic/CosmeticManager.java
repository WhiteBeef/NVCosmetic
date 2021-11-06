package ru.whitebeef.nvcosmetic.cosmetic;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class CosmeticManager {

    // namespace - Cosmetic
    private HashMap<String, Cosmetic> cosmetics = new HashMap<>();
    private HashMap<Player, CosmeticEntity> cosmeticPlayers = new HashMap<>();

    public CosmeticManager() {
        registerCosmetics();
    }

    private void registerCosmetics() {

    }


    public Cosmetic getCosmetic(String namespace) {
        return cosmetics.get(namespace);
    }

    public List<Cosmetic> getLoadedCosmetics() {
        return (List<Cosmetic>) cosmetics.values();
    }

    public void removeAllCosmeticPlayers() {
        cosmeticPlayers.forEach((player, cosmeticPlayer) -> cosmeticPlayer.getArmorStand().remove());
        cosmeticPlayers.clear();
    }

    public void removeCosmeticPlayer(Player player) {
        cosmeticPlayers.get(player).getArmorStand().remove();
    }


}
