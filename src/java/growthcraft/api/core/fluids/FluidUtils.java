/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015, 2016 IceDragon200
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
package growthcraft.api.core.fluids;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.*;

import java.util.ArrayList;
import java.util.List;

public class FluidUtils
{
	private FluidUtils() {}

	public static FluidStack drainFluidBlock(World world, BlockPos pos, boolean doDrain)
	{
		final IBlockState state = world.getBlockState(pos);
		final Block block = state.getBlock();
		if (block instanceof BlockFluidBase)
		{
			final BlockFluidBase bfb = (BlockFluidBase)block;
			return bfb.drain(world, pos, doDrain);
		}
		else if (block == Blocks.LAVA)
		{
			if (doDrain) world.setBlockToAir(pos);
			return new FluidStack(FluidRegistry.LAVA, FluidContainerRegistry.BUCKET_VOLUME);
		}
		else if (block == Blocks.WATER)
		{
			if (doDrain) world.setBlockToAir(pos);
			return new FluidStack(FluidRegistry.WATER, FluidContainerRegistry.BUCKET_VOLUME);
		}
		return null;
	}

	public static List<Fluid> getFluidsByNames(List<String> names)
	{
		final List<Fluid> fluids = new ArrayList<Fluid>();
		for (String name : names)
		{
			fluids.add(FluidRegistry.getFluid(name));
		}
		return fluids;
	}

	public static FluidStack exchangeFluid(FluidStack stack, Fluid newFluid)
	{
		return new FluidStack(newFluid, stack.amount);
	}

	public static boolean doesFluidExist(String name)
	{
		return FluidRegistry.getFluid(name) != null && FluidRegistry.isFluidRegistered(name);
	}

	public static boolean doesFluidExist(Fluid fluid)
	{
		return fluid != null && FluidRegistry.isFluidRegistered(fluid);
	}

	public static boolean doesFluidsExist(Fluid[] fluid)
	{
		for (int i = 0; i < fluid.length; ++i)
		{
			if (!doesFluidExist(fluid[i]))
			{
				return false;
			}
		}
		return true;
	}

	public static FluidStack replaceFluidStack(String fluidName, FluidStack srcStack)
	{
		final Fluid fluid = FluidRegistry.getFluid(fluidName);
		if (fluid == null)
		{
			// An invalid fluid
			return null;
		}

		if (srcStack == null)
		{
			return new FluidStack(fluid, 0);
		}
		return new FluidStack(fluid, srcStack.amount);
	}

	public static FluidStack updateFluidStackAmount(FluidStack srcStack, int amount)
	{
		if (srcStack == null)
		{
			return new FluidStack(FluidRegistry.WATER, amount);
		}
		srcStack.amount = amount;
		return srcStack;
	}
}
