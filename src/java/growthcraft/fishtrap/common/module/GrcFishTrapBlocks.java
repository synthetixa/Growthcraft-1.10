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
package growthcraft.fishtrap.common.module;

import growthcraft.fishtrap.common.block.BlockFishTrap;
import growthcraft.core.common.module.GrcModuleBase;
import growthcraft.core.common.definition.BlockDefinition;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraft.init.Items;

public class GrcFishTrapBlocks extends GrcModuleBase
{
	public BlockDefinition fishTrap;

	@Override
	public void preInit()
	{
		this.fishTrap = new BlockDefinition(new BlockFishTrap());
	}

	@Override
	public void register()
	{
		fishTrap.register("fish_trap");
	}

	@Override
	public void init()
	{
		registerRecipes();
	}

	/*
	 * Called from Client Proxy
	 */
	public void registerModels()
	{
		fishTrap.registerModel();
	}

	public void registerRecipes()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(fishTrap.asStack(1), "ACA", "CBC", "ACA", 'A', "plankWood", 'B', Items.LEAD, 'C', Items.STRING));
	}
}
