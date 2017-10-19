package com.detrav.events;

import com.detrav.utils.DetravConfig;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;

import java.util.UUID;

/**
 * Created by Detrav on 26.03.2017.
 */
public class DetravLevelUpEvent {

    public static UUID mod_id = UUID.fromString("9a090263-953b-4d9f-947e-d4636cf3cd7e");



    @SubscribeEvent
    public void onPlayerPickupXpEvent(PlayerPickupXpEvent ev) {
        EntityPlayer player = ev.entityPlayer;
        if (player != null) {
            if (!player.getEntityWorld().isRemote) {
                if ((player.experience + ev.orb.xpValue*2) >= player.xpBarCap()) {
                    UpdateHealthAttribute(player,1);
                }
            }
        }
    }

    public static void UpdateHealthAttribute(EntityPlayer player)
    {
        UpdateHealthAttribute(player,0);
    }

    public static void UpdateHealthAttribute(EntityPlayer player,int bonus) {
        if (DetravConfig.EXTRA_HP_ENABLE)
            if (!player.getEntityWorld().isRemote) {
                AttributeModifier mod = GetAttributeModifier(player.experienceLevel + bonus);
                player.getEntityAttribute(
                        SharedMonsterAttributes.maxHealth
                ).removeModifier(mod);
                player.getEntityAttribute(
                        SharedMonsterAttributes.maxHealth
                ).applyModifier(mod);
                player.heal(player.getMaxHealth());
            }
    }

    public static AttributeModifier GetAttributeModifier(int level) {
        int hp_boost = 0;
        if(level > DetravConfig.EXTRA_HP_MAX_LEVEL )
            level = DetravConfig.EXTRA_HP_MAX_LEVEL ;
        if(level >= DetravConfig.EXTRA_HP_LEVELS.length)
            level = DetravConfig.EXTRA_HP_LEVELS.length-1;
        if(level >=0) {
            hp_boost = DetravConfig.EXTRA_HP_LEVELS[level];
        }
        else hp_boost = 0;

        return new AttributeModifier(mod_id, "detravlevelup", hp_boost, 0);
    }

    static boolean inited = false;

    public static void register() {
        if (!inited) {
            inited = true;
            if (DetravConfig.EXTRA_HP_ENABLE) {
                DetravLevelUpEvent handler = new DetravLevelUpEvent();
                MinecraftForge.EVENT_BUS.register(handler);
                FMLCommonHandler.instance().bus().register(handler);
            }
        }
    }
}
