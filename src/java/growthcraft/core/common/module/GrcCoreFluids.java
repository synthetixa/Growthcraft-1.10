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
package growthcraft.core.common.module;

import growthcraft.api.core.fluids.GrcFluid;
import growthcraft.core.bucket.SaltBucketEntry;
import growthcraft.core.eventhandler.EventHandlerBucketFill;
import growthcraft.core.GrowthCraftCore;
import growthcraft.core.util.FluidFactory;

import net.minecraft.util.ResourceLocation;
public class GrcCoreFluids extends GrcModuleFluidsBase
{
	public FluidFactory.FluidDetails saltWater;

	@Override
	public void preInit()
	{
		this.saltWater = FluidFactory.instance().create(new GrcFluid("grccore.SaltWater", new ResourceLocation("minecraft:blocks/water_still"), new ResourceLocation("minecraft:blocks/water_flow")));
		saltWater.setCreativeTab(GrowthCraftCore.creativeTab).setItemColor(0x2C41F6);
	}

	@Override
	public void register()
	{
		saltWater.registerObjects("grccore", "SaltWater");
	}

	@Override
	public void init()
	{
		if (GrowthCraftCore.proxy.config.bucketOfOceanSaltWater)
		{
			EventHandlerBucketFill.instance().register(new SaltBucketEntry());
		}
	}
}
