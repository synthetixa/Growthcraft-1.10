/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 IceDragon200
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

import java.util.List;
import javax.annotation.Nonnull;

import growthcraft.api.core.i18n.GrcI18n;
import growthcraft.api.core.description.IDescribable;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

/**
 * An extension of the default Forge Fluid class, adding descriptions and color
 * settings
 */
public class GrcFluid extends Fluid implements IDescribable
{
	protected int color;

	public GrcFluid(@Nonnull String fluidName, @Nonnull ResourceLocation still, @Nonnull ResourceLocation flowing)
	{
		super(fluidName, still, flowing);
		this.color = 0xFFFFFF;
	}

	/**
	 * Returns the color of the fluid
	 *
	 * @return color - a RGB24 value
	 */
	@Override
	public int getColor()
	{
		return color;
	}

	/**
	 * Sets the color of the fluid
	 *
	 * @param col - a RGB24 color
	 */
	public GrcFluid setColor(int col)
	{
		this.color = col;
		return this;
	}

	/**
	 * Adds the fluids's description to the list
	 *
	 * @param list - list to add description to
	 */
	@Override
	public void getDescription(@Nonnull List<String> list)
	{
		final String unloc = getUnlocalizedName() + ".desc";
		final String result = GrcI18n.translate(unloc);
		if (!unloc.equals(result))
		{
			list.add(result);
		}
	}

	/**
	 * Fluids don't produce any useful information when converted to a String.
	 * This changes that.
	 *
	 * @return string
	 */
	@Override
	public String toString()
	{
		return String.format("GrcFluid(name=%s, color=%x)", getName(), getColor());
	}
}
