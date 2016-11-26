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
package growthcraft.core.eventhandler;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

import growthcraft.api.core.log.ILoggable;
import growthcraft.api.core.log.ILogger;
import growthcraft.api.core.log.NullLogger;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerBucketFill implements ILoggable
{
	public static interface IBucketEntry
	{
		ItemStack getItemStack();
		boolean matches(@Nonnull World world, @Nonnull BlockPos pos);
		void commit(@Nonnull World world, @Nonnull BlockPos pos);
	}

	public static class SimpleBucketEntry implements IBucketEntry
	{
		private IBlockState blockState;
		private ItemStack itemStack;

		public SimpleBucketEntry(IBlockState state, ItemStack stack)
		{
			this.blockState = state;
			this.itemStack = stack;
		}

		public ItemStack getItemStack()
		{
			return itemStack;
		}

		public boolean matches(@Nonnull World world, @Nonnull BlockPos pos)
		{
			final IBlockState state = world.getBlockState(pos);
			if (state != null)
			{
				return state == blockState;
			}
			return false;
		}

		public void commit(@Nonnull World world, @Nonnull BlockPos pos)
		{
			world.setBlockToAir(pos);
		}
	}

	private static EventHandlerBucketFill INSTANCE = new EventHandlerBucketFill();
	private ILogger logger = NullLogger.INSTANCE;
	private List<IBucketEntry> buckets = new ArrayList<IBucketEntry>();

	public void setLogger(ILogger l)
	{
		this.logger = l;
	}

	public static EventHandlerBucketFill instance()
	{
		return INSTANCE;
	}

	public void register(@Nonnull IBucketEntry entry)
	{
		buckets.add(entry);
		logger.debug("Adding new BucketEntry entry=%s", entry);
	}

	public void register(@Nonnull Block block, @Nonnull ItemStack stack)
	{
		// TODO
		logger.warn("DEPRECATED: Adding Bucket Entry via block and stack: block=%s stack=%s", block, stack);
	}

	private ItemStack fillCustomBucket(@Nonnull World world, @Nonnull BlockPos pos)
	{
		for (IBucketEntry entry : buckets)
		{
			if (entry.matches(world, pos))
			{
				entry.commit(world, pos);
				final ItemStack stack = entry.getItemStack();
				return stack != null ? stack.copy() : null;
			}
		}
		return null;
	}

	private ItemStack fillCustomBucket(@Nonnull World world, @Nonnull RayTraceResult pos)
	{
		return fillCustomBucket(world, pos.getBlockPos());
	}

	@SubscribeEvent
	public void onBucketFill(FillBucketEvent event)
	{
		final ItemStack result = fillCustomBucket(event.getWorld(), event.getTarget());
		if (result == null) return;
		event.setFilledBucket(result);
	}
}
