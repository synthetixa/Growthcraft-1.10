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

import growthcraft.api.core.effect.IEffectRegistry;
import growthcraft.api.core.effect.IPotionEffectFactoryRegistry;
import growthcraft.api.core.fluids.FluidDictionary;
import growthcraft.api.core.fluids.FluidTagsRegistry;
import growthcraft.api.core.fluids.IFluidDictionary;
import growthcraft.api.core.fluids.IFluidTagsRegistry;
import growthcraft.api.core.GrcCoreApi;
import growthcraft.api.core.ICoreRegistry;
import growthcraft.api.core.log.ILoggable;
import growthcraft.api.core.log.ILogger;
import growthcraft.api.core.log.NullLogger;
import growthcraft.api.core.vines.IVineDropRegistry;

public class CoreRegistry implements ILoggable, ICoreRegistry
{
	private static final CoreRegistry instance = new CoreRegistry();

	protected ILogger logger = NullLogger.INSTANCE;
	private final IFluidDictionary fluidDictionary = new FluidDictionary();
	private final IFluidTagsRegistry fluidTagsRegistry = new FluidTagsRegistry();
	private final IEffectRegistry effectRegistry = new EffectRegistry().initialize();
	private final IPotionEffectFactoryRegistry potionEffectFactoryRegistry = new PotionEffectFactoryRegistry();
	private final IVineDropRegistry vineDropRegistry = new VineDropRegistry();

	public CoreRegistry()
	{
		GrcCoreApi.registry = this;
	}

	@Override
	public void setLogger(@Nonnull ILogger l)
	{
		this.logger = l;
		fluidTagsRegistry.setLogger(logger);
		fluidDictionary.setLogger(logger);
		effectRegistry.setLogger(logger);
		potionEffectFactoryRegistry.setLogger(logger);
		vineDropRegistry.setLogger(logger);
	}

	public ILogger getLogger()
	{
		return logger;
	}

	public IEffectRegistry getEffectsRegistry()
	{
		return effectRegistry;
	}

	public IPotionEffectFactoryRegistry getPotionEffectFactoryRegistry()
	{
		return potionEffectFactoryRegistry;
	}

	/**
	 * @return instance of the FluidTagsRegistry
	 */
	public IFluidTagsRegistry fluidTags()
	{
		return fluidTagsRegistry;
	}

	public IFluidDictionary fluidDictionary()
	{
		return fluidDictionary;
	}

	public IVineDropRegistry vineDrops()
	{
		return vineDropRegistry;
	}
}
