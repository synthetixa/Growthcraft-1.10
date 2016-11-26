/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015, 2016 IceDragon200
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
package growthcraft.api.core.stream;

import java.io.UnsupportedEncodingException;
import javax.annotation.Nonnull;

import io.netty.buffer.ByteBuf;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

/**
 * Utility class for handling data streams
 */
public class StreamUtils
{
	private StreamUtils() {}

	/**
	 * Reads an ASCII string from the stream, the first int should be the length
	 * of the string.
	 *
	 * @param stream - stream to read from
	 * @return string
	 */
	public static String readStringASCII(@Nonnull ByteBuf stream) throws UnsupportedEncodingException
	{
		final int len = stream.readInt();
		final byte[] bytes = stream.readBytes(len).array();
		return new String(bytes, "US-ASCII");
	}

	/**
	 * Writes an ASCII string to the stream, the first value will be an integer for the length of the
	 * string, followed by bytes
	 *
	 * @param stream - stream to write to
	 * @param str - string to write
	 */
	public static void writeStringASCII(@Nonnull ByteBuf stream, @Nonnull String str) throws UnsupportedEncodingException
	{
		final byte[] bytes = str.getBytes("US-ASCII");
		stream.writeInt(str.length());
		stream.writeBytes(bytes);
	}

	public static void readFluidTank(@Nonnull ByteBuf stream, @Nonnull FluidTank tank)
	{
		final int capacity = stream.readInt();
		String fluidName = "";
		try
		{
			fluidName = readStringASCII(stream);
		}
		catch (UnsupportedEncodingException ex)
		{
			ex.printStackTrace();
		}
		final int fluidAmount = stream.readInt();

		final Fluid fluid = fluidName.equals("") ? null : FluidRegistry.getFluid(fluidName);
		final FluidStack fluidStack = fluid != null ? new FluidStack(fluid, fluidAmount) : null;

		tank.setCapacity(capacity);
		tank.setFluid(fluidStack);
	}

	public static void writeFluidTank(@Nonnull ByteBuf stream, @Nonnull FluidTank tank)
	{
		String fluidName = "";
		int fluidAmount = 0;
		final int capacity = tank.getCapacity();
		final FluidStack fs = tank.getFluid();

		if (fs != null)
		{
			if (fs.getFluid() != null)
				fluidName = fs.getFluid().getName();
			fluidAmount = fs.amount;
		}

		stream.writeInt(capacity);
		try
		{
			writeStringASCII(stream, fluidName);
		}
		catch (UnsupportedEncodingException ex)
		{
			ex.printStackTrace();
		}
		stream.writeInt(fluidAmount);
	}
}
