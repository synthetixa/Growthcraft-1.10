package growthcraft.core;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = GrowthCraftCore.MOD_ID,
        name = GrowthCraftCore.MOD_NAME,
        version = GrowthCraftCore.MOD_VERSION,
        acceptedMinecraftVersions = GrowthCraftCore.MOD_ACC_MINECRAFT,
        dependencies = GrowthCraftCore.MOD_DEPENDENCIES
)
public class GrowthCraftCore {
    public static final String MOD_ID = "Growthcraft";
    public static final String MOD_NAME = "Growthcraft";
    public static final String MOD_VERSION = "3.0.0-alpha";
    public static final String MOD_ACC_MINECRAFT = "[1.10.2]";
    public static final String MOD_DEPENDENCIES = "required-after:Forge@[10.13.4.1566,)";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

}
