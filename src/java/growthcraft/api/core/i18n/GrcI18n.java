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
package growthcraft.api.core.i18n;

/**
 * An abstraction for translating, use this instead of Minecraft's I18n or
 * Forge's I18n and a fallback NullTranslator,
 * this will hide the difference between them.
 */
public class GrcI18n
{
	private static ITranslator translator;

	private GrcI18n() {}

	public static void setTranslator(ITranslator tr)
	{
		translator = tr;
	}

	public static ITranslator getTranslator()
	{
		if (translator == null)
		{
			// Defaults to the I18n version
			setTranslator(I18nTranslator.INSTANCE);
		}
		return translator;
	}

	public static String translate(String str, Object... objs)
	{
		return getTranslator().translate(str, objs);
	}
}
