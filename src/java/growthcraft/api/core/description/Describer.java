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
package growthcraft.api.core.description;

import java.util.List;

import growthcraft.api.core.i18n.GrcI18n;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;

/**
 * A nice way to add descriptions to a list if the Object is an IDescribable
 */
public class Describer
{
	private Describer() {}

	public static void getDescription(List<String> list, Object obj)
	{
		if (obj instanceof IDescribable)
		{
			((IDescribable)obj).getDescription(list);
		}
	}

	public static void getPotionEffectDescription(List<String> list, PotionEffect pe)
	{
		if (pe == null) return;

		String s = GrcI18n.translate(pe.getEffectName()).trim();
		final Potion potion = pe.getPotion();
		if (potion != null)
		{
			if (potion.isBadEffect())
				s = TextFormatting.RED + s;
		}

		if (pe.getAmplifier() > 0)
		{
			s += " " + GrcI18n.translate("potion.potency." + pe.getAmplifier()).trim();
		}

		if (pe.getDuration() > 20)
		{
			s += "" + TextFormatting.GRAY + " (" + potion.getPotionDurationString(pe, 1) + ")";
		}
		list.add(s);
	}

	public static void addAllPrefixed(String prefix, List<String> dest, List<String> src)
	{
		for (String str : src)
		{
			dest.add(prefix + str);
		}
	}

	public static void addAllIndented(List<String> dest, List<String> src)
	{
		addAllPrefixed("  ", dest, src);
	}

	/**
	 * Attempts to compact the src list into the dest list.
	 * If the src list only has 1 entry, then it will inline with the head
	 * else it is treated as an indented list
	 *
	 * @param head - str to inline with or use as header
	 * @param dest - destination list
	 * @param src - source list
	 */
	public static void compactDescription(String head, List<String> dest, List<String> src)
	{
		if (src.size() > 0)
		{
			if (src.size() == 1)
			{
				final String line = src.get(0);
				dest.add(head + " " + line);
			}
			else
			{
				dest.add(head);
				addAllIndented(dest, src);
			}
		}
	}
}
