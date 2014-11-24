package mc.Mitchellbrine.advTutorial;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;

/**
 * Created by Mitchellbrine on 2014.
 */
@Mod(modid = "MitchellbrineAdvTutorials",name = "Mitchellbrine's Advanced Tutorial",version = "1.0")
public class AdvancedTutorial {

    @SuppressWarnings("unused")
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void playerData(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;

            if (player.worldObj.getWorldTime() % 200 == 0) {

                // Player NBT (PNBT)

                if (!player.getEntityData().hasKey("hasFired") || !player.getEntityData().getBoolean("hasFired")) {
                    player.getEntityData().setBoolean("hasFired", true);
                }

                // Persistent Player Data (PPD)

                NBTTagCompound persistTag = null;
                if (player.getEntityData().hasKey(player.PERSISTED_NBT_TAG)) {
                    persistTag = player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);
                } else {
                    persistTag = new NBTTagCompound();
                    player.getEntityData().setTag(player.PERSISTED_NBT_TAG, persistTag);
                }

                NBTTagCompound modTag = null;
                if (player.getEntityData().hasKey("Adv")) {
                    modTag = persistTag.getCompoundTag("Adv");
                } else {
                    modTag = new NBTTagCompound();
                    persistTag.setTag("Adv", modTag);
                }

                if (!modTag.hasKey("hasFired") || !modTag.getBoolean("hasFired")) {
                    modTag.setBoolean("hasFired", true);
                }

                System.err.println("Check complete!");

            }

        }
    }

}
