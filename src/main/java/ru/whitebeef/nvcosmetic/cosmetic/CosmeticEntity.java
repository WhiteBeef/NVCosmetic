package ru.whitebeef.nvcosmetic.cosmetic;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import ru.whitebeef.nvcosmetic.NVCosmetic;
import ru.whitebeef.nvcosmetic.managers.CosmeticManager;

import javax.naming.OperationNotSupportedException;

public class CosmeticEntity implements Cosmetable {

    private final LivingEntity entity;
    private Cosmetic cosmetic;
    private ArmorStand armorStand;

    public CosmeticEntity(LivingEntity entity) {
        NVCosmetic.getCosmeticManager().addCosmeticEntity(entity, this);
        this.entity = entity;
    }

    @Override
    public ArmorStand getArmorStand() {
        return armorStand;
    }

    @Override
    public void wearCosmetic(Cosmetic cosmetic) {
        this.cosmetic = cosmetic;
        if (cosmetic.getPosition() == CosmeticPosition.BODY) {
            armorStand =
                    (ArmorStand) Bukkit.getWorld(entity.getWorld().getUID()).spawnEntity(entity.getEyeLocation(), EntityType.ARMOR_STAND);

            entity.getPassengers().forEach(entity::removePassenger);
            entity.addPassenger(armorStand);
        } else {
            //TODO: add cosmetic into head
        }
    }

    @Override
    public void wearCosmetic() {
        wearCosmetic(NVCosmetic.getDatabase().getCosmetic(entity));
    }

    @Override
    public Cosmetic getCosmetic() {
        return cosmetic;
    }

    public LivingEntity getEntity() {
        return entity;
    }
}
