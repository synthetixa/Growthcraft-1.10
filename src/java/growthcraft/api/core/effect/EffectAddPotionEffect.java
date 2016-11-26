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
package growthcraft.api.core.effect;

import java.util.List;
import java.util.Random;
import javax.annotation.Nonnull;

import growthcraft.api.core.GrcCoreApi;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * As its name implies, this Effect, will ADD a Potion Effect to the target.
 */
public class EffectAddPotionEffect extends AbstractEffect
{
	private IPotionEffectFactory potionFactory;

	public EffectAddPotionEffect(@Nonnull IPotionEffectFactory factory)
	{
		this.potionFactory = factory;
	}

	public EffectAddPotionEffect() {}

	public EffectAddPotionEffect setPotionFactory(@Nonnull IPotionEffectFactory factory)
	{
		this.potionFactory = factory;
		return this;
	}

	/**
	 * @return potion factory
	 */
	public IPotionEffectFactory getPotionFactory()
	{
		return potionFactory;
	}

	/**
	 * Adds the potion effect to the entity, if the entity is a EntityLivingBase.
	 *
	 * @param world - world that the entity is currently present ing
	 * @param entity - entity to apply the effect to
	 * @param data - any extra data you want to pass along
	 */
	@Override
	public void apply(World world, Entity entity, Random random, Object data)
	{
		if (potionFactory == null) return;

		if (entity instanceof EntityLivingBase)
		{
			final PotionEffect effect = potionFactory.createPotionEffect(world, entity, random, data);

			((EntityLivingBase)entity).addPotionEffect(effect);
		}
	}

	@Override
	protected void getActualDescription(List<String> list)
	{
		if (potionFactory == null) return;
		potionFactory.getDescription(list);
	}

	@Override
	protected void readFromNBT(NBTTagCompound data)
	{
		if (data.hasKey("potion_factory"))
		{
			this.potionFactory = GrcCoreApi.instance().getPotionEffectFactoryRegistry().loadPotionEffectFactoryFromNBT(data, "potion_factory");
		}
	}

	@Override
	protected void writeToNBT(NBTTagCompound data)
	{
		if (potionFactory != null)
		{
			potionFactory.writeToNBT(data, "potion_factory");
		}
	}
}
