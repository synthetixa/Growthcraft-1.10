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

import growthcraft.core.common.definition.ItemDefinition;
import growthcraft.core.common.item.ItemBottleSalt;
import growthcraft.core.common.item.ItemBucketSalt;
import growthcraft.core.common.item.ItemRope;
import growthcraft.core.common.item.ItemSalt;

public class GrcCoreItems extends GrcModuleBase
{
	public ItemDefinition salt;
	public ItemDefinition rope;
	public ItemDefinition saltBucket;
	public ItemDefinition saltBottle;

	@Override
	public void preInit()
	{
		this.rope = new ItemDefinition(new ItemRope());
		this.salt = new ItemDefinition(new ItemSalt());
		this.saltBucket = new ItemDefinition(new ItemBucketSalt());
		this.saltBottle = new ItemDefinition(new ItemBottleSalt());
	}

	@Override
	public void register()
	{
		rope.register("rope");
		salt.register("salt");
		saltBucket.register("salt_bucket");
		saltBottle.register("salt_bottle");
	}
}
