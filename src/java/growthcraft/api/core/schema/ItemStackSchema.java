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
package growthcraft.api.core.schema;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

import growthcraft.api.core.item.ItemKey;
import growthcraft.api.core.definition.IItemStackFactory;
import growthcraft.api.core.definition.IItemStackListProvider;
import growthcraft.api.core.definition.IMultiItemStacks;
import growthcraft.api.core.item.MultiItemStacks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemStackSchema implements IItemStackFactory, IItemStackListProvider, IValidatable, ICommentable
{
	public String comment;
	public String mod_id;
	public String name;
	public int amount;
	public int meta;

	public ItemStackSchema(@Nonnull String mid, @Nonnull String nm, int amt, int mt)
	{
		this.mod_id = mid;
		this.name = nm;
		this.amount = amt;
		this.meta = mt;
		this.comment = "";
	}

	public ItemStackSchema(@Nonnull ItemStack stack)
	{
		this.mod_id = stack.getItem().getRegistryName().getResourceDomain();
		this.name = stack.getItem().getRegistryName().getResourcePath();
		this.amount = stack.stackSize;
		this.meta = stack.getItemDamage();
		this.comment = stack.getDisplayName();
	}

	public ItemStackSchema()
	{
		this.amount = 1;
		this.meta = ItemKey.WILDCARD_VALUE;
		this.comment = "";
	}

	@Override
	public void setComment(String comm)
	{
		this.comment = comm;
	}

	@Override
	public String getComment()
	{
		return comment;
	}

	public Item getItem()
	{
		if (mod_id == null || name == null) return null;
		return GameRegistry.findItem(mod_id, name);
	}

	@Override
	public ItemStack asStack(int a)
	{
		final Item item = getItem();
		if (item == null) return null;
		return new ItemStack(item, a, meta < 0 ? ItemKey.WILDCARD_VALUE : meta);
	}

	@Override
	public ItemStack asStack()
	{
		return asStack(amount);
	}

	@Override
	public List<ItemStack> getItemStacks()
	{
		final List<ItemStack> result = new ArrayList<ItemStack>();
		final ItemStack stack = asStack();
		if (stack != null) result.add(stack);
		return result;
	}

	public List<IMultiItemStacks> getMultiItemStacks()
	{
		final List<IMultiItemStacks> result = new ArrayList<IMultiItemStacks>();
		final ItemStack stack = asStack();
		if (stack != null) result.add(new MultiItemStacks(stack));
		return result;
	}

	@Override
	public String toString()
	{
		return String.format("Schema<ItemStack>(mod_id: '%s', name: '%s', meta: %d, amount: %d)", mod_id, name, meta, amount);
	}

	@Override
	public boolean isValid()
	{
		return asStack() != null;
	}

	@Override
	public boolean isInvalid()
	{
		return !isValid();
	}
}
