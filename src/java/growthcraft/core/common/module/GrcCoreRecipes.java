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

import growthcraft.core.GrowthCraftCore;

import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;

public class GrcCoreRecipes extends GrcModuleRecipesBase
{
	public void init()
	{
		final ItemStack salt = GrowthCraftCore.proxy.items.salt.asStack();
		final ItemStack salt2 = GrowthCraftCore.proxy.items.salt.asStack(2);
		final ItemStack salt6 = GrowthCraftCore.proxy.items.salt.asStack(6);
		final ItemStack saltBottle = GrowthCraftCore.proxy.items.saltBottle.asStack();
		final ItemStack saltBucket = GrowthCraftCore.proxy.items.saltBucket.asStack();
		addShapelessRecipe(salt2, saltBottle);
		addShapelessRecipe(salt6, saltBucket);
		addShapelessRecipe(saltBottle, salt, salt, Items.GLASS_BOTTLE);
		addShapelessRecipe(saltBucket, salt, salt, salt, salt, salt, salt, Items.BUCKET);
	}
}
