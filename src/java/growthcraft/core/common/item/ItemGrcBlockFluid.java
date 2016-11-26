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
package growthcraft.core.common.item;

import growthcraft.core.common.block.GrcBlockFluid;

import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;

public class ItemGrcBlockFluid extends GrcItemBlockBase
{
	protected int color = 0xFFFFFF;
	protected Fluid fluid;

	public ItemGrcBlockFluid(Block block)
	{
		super(block);
		if (block instanceof GrcBlockFluid)
		{
			final GrcBlockFluid fluidBlock = (GrcBlockFluid)block;
			//this.color = fluidBlock.getColor();
			this.fluid = fluidBlock.getFluid();
		}
	}

	public Fluid getFluid()
	{
		return this.fluid;
	}

	public int getFluidColor()
	{
		return this.color;
	}

	/*
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({"rawtypes", "unchecked"})
	protected void writeModifierTooltip(ItemStack stack, EntityPlayer player, List list, boolean bool)
	{
		final String modifier = UnitFormatter.fluidModifier(getFluid());
		if (modifier != null) list.add(modifier);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool)
	{
		super.addInformation(stack, player, list, bool);
		writeModifierTooltip(stack, player, list, bool);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass)
	{
		return getFluidColor();
	}*/
}
