/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Jamie Quinn
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package growthcraft.fishtrap;

import growthcraft.fishtrap.common.CommonProxy;
import growthcraft.fishtrap.common.module.GrcFishTrapBlocks;
import growthcraft.core.lib.GrcCoreConst;
import growthcraft.api.core.log.GrcLogger;
import growthcraft.api.core.module.ModuleContainer;
import growthcraft.api.core.log.ILogger;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod;

@Mod(
	modid = GrowthCraftFishTrap.MOD_ID,
	name = GrowthCraftFishTrap.MOD_NAME,
	version = GrcCoreConst.VERSION,
	dependencies = "required-after:GrowthCraft-Core@[" + GrcCoreConst.VERSION + ",)"
)
public class GrowthCraftFishTrap
{
	public static final String MOD_ID = "GrowthCraft-Fishtrap";
	public static final String MOD_NAME = "Growthcraft Fishtrap";

	public static final GrcFishTrapBlocks blocks = new GrcFishTrapBlocks();

	@Instance(MOD_ID)
	public static GrowthCraftFishTrap instance = new GrowthCraftFishTrap();
	
	@SidedProxy(clientSide="growthcraft.fishtrap.client.ClientProxy", serverSide="growthcraft.fishtrap.server.ServerProxy")
	public static CommonProxy proxy;

	private final ModuleContainer modules = new ModuleContainer();
	private final GrcFishTrapConfig config = new GrcFishTrapConfig();
	private final ILogger logger = new GrcLogger(MOD_ID);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		config.setLogger(logger);
		config.load(event.getModConfigurationDirectory(), "growthcraft/fishtrap.conf");
		
		if (config.debugEnabled) 
		{
			modules.setLogger(logger);
		}

		modules.add(blocks);
		modules.preInit();
		modules.register();
		
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		modules.init();
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		modules.postInit();
		proxy.postInit(event);
	}
}

