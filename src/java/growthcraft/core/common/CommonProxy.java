/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 IceDragon200
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
package growthcraft.core.common;

import growthcraft.api.core.log.GrcLogger;
import growthcraft.api.core.log.ILogger;
import growthcraft.api.core.module.ModuleContainer;
import growthcraft.core.common.module.GrcCoreBlocks;
import growthcraft.core.common.module.GrcCoreFluids;
import growthcraft.core.common.module.GrcCoreItems;
import growthcraft.core.common.module.GrcCoreRecipes;
import growthcraft.core.lib.GrcCoreConst;
import growthcraft.core.eventhandler.EventHandlerBucketFill;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy
{
	public final GrcCoreConfig config = new GrcCoreConfig();
	public final GrcCoreBlocks blocks = new GrcCoreBlocks();
	public final GrcCoreItems items = new GrcCoreItems();
	public final GrcCoreFluids fluids = new GrcCoreFluids();
	public final GrcCoreRecipes recipes = new GrcCoreRecipes();
	public final ILogger logger = new GrcLogger(GrcCoreConst.MODID);
	private final ModuleContainer modules = new ModuleContainer();

	public CommonProxy()
	{
		config.setLogger(logger);
		modules.add(blocks);
		modules.add(items);
		modules.add(fluids);
		modules.add(recipes);
	}

	protected void doPreInit()
	{
	}

	public void preInit(FMLPreInitializationEvent event)
	{
		modules.preInit();
		modules.register();
		doPreInit();
	}

	public void init(FMLInitializationEvent event)
	{
		modules.init();
	}

	public void postInit(FMLPostInitializationEvent event)
	{
		modules.postInit();
		MinecraftForge.EVENT_BUS.register(EventHandlerBucketFill.instance());
	}
}
