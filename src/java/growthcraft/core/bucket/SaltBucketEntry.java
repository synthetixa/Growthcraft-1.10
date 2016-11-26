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
package growthcraft.core.bucket;

import growthcraft.core.GrowthCraftCore;
import growthcraft.core.eventhandler.EventHandlerBucketFill.IBucketEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nonnull;

public class SaltBucketEntry implements IBucketEntry
{
	@Override
	public ItemStack getItemStack()
	{
		return GrowthCraftCore.proxy.fluids.saltWater.bucket.asStack();
	}

	@Override
	public boolean matches(@Nonnull World world, @Nonnull BlockPos pos)
	{
		//if (world.getBlockMetadata(pos.getX(), pos.getY(), pos.getZ()) == 0)
		//{
			final Biome biome = world.getBiomeGenForCoords(pos);
			if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.OCEAN))
			{
				return true;
			}
		//}
		return false;
	}

	@Override
	public void commit(@Nonnull World world, @Nonnull BlockPos pos)
	{
		world.setBlockToAir(pos);
	}
}
