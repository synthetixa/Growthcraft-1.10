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
package growthcraft.core.registry;

import javax.annotation.Nonnull;

import growthcraft.api.core.effect.IPotionEffectFactory;
import growthcraft.api.core.effect.IPotionEffectFactoryRegistry;
import growthcraft.api.core.log.ILogger;
import growthcraft.api.core.log.NullLogger;

import net.minecraft.nbt.NBTTagCompound;

public class PotionEffectFactoryRegistry extends AbstractClassRegistry<IPotionEffectFactory> implements IPotionEffectFactoryRegistry
{
	private ILogger logger = NullLogger.INSTANCE;

	public void setLogger(@Nonnull ILogger l)
	{
		this.logger = l;
	}

	@Override
	public IPotionEffectFactory loadPotionEffectFactoryFromNBT(@Nonnull NBTTagCompound data, @Nonnull String name)
	{
		return loadObjectFromNBT(data, name);
	}
}
