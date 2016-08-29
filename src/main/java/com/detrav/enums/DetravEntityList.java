package com.detrav.enums;

import com.detrav.DetravScannerMod;
import com.detrav.entities.EntityElectricTunnelBore;
import cpw.mods.fml.common.registry.EntityRegistry;
import mods.railcraft.common.carts.EntityCartBasic;
import mods.railcraft.common.carts.IRailcraftCart;
import mods.railcraft.common.util.misc.Game;
import mods.railcraft.common.util.misc.MiscTools;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.lang.reflect.Constructor;
import java.util.Locale;

/**
 * Created by Detrav on 25.08.2016.
 */
public enum DetravEntityList {

    Electric_Tunnel_Bore(1, 3,EntityElectricTunnelBore.class);

    private final Class<? extends EntityMinecart> type;
    private final byte id;
    private final byte rarity;
    private ItemStack contents = null;
    private ItemStack cartItem;
    //private String name;

    private DetravEntityList(int id , int rarity, Class<? extends EntityMinecart> type ) {
        int entityId = -1;
        try {
            entityId = (byte) id;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        this.id = (byte) entityId;
        this.rarity = (byte) rarity;
        this.type = type;
        this.contents = contents;
        //this.name = aName;
    }

    public byte getId() {
        return id;
    }

    public String getTag() {
        return "detrav.cart." + name().toLowerCase(Locale.ENGLISH).replace('_', '.');
    }

    public Class<? extends EntityMinecart> getCartClass() {
        return type;
    }

    public void setContents(ItemStack stack) {
        contents = stack.copy();
    }


    public EntityMinecart makeCart(ItemStack stack, World world, double i, double j, double k) {
        try {
            Constructor<? extends EntityMinecart> con = type.getConstructor(World.class, double.class, double.class, double.class);
            EntityMinecart entity = con.newInstance(world, i, j, k);
            if (entity instanceof IRailcraftCart)
                ((IRailcraftCart) entity).initEntityFromItem(stack);
            return entity;
        } catch (Throwable ex) {
            Game.logThrowable("Failed to create cart entity!", ex);
        }
        return new EntityCartBasic(world, i, j, k);
    }




    public void registerEntity() {
        if (id < 0)
            return;
        EntityRegistry.registerModEntity(type, MiscTools.cleanTag(getTag()), id, DetravScannerMod.instance, 256, 3, true);

        // Legacy stuff
        /*EntityList.stringToClassMapping.put("Railcraft." + getTag(), type);
        if (this == LOCO_STEAM_SOLID)
            EntityList.stringToClassMapping.put("Railcraft.railcraft.cart.loco.steam", type);*/
    }




}
