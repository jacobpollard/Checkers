public class Tools
{
    /*
     * @method setBit
     * @param value the value to to be modifed
     * @param what to set the bit to
     * @param the Bit to be set
     *
     * This sets a bit in a int to a 0 or 1
     * Notice this always only sets 4 bits at a time.
     */
	final static int setFourBits(int value, int bitVal, int start)
	{
		int mask = 0xF;
		mask = mask << start;
		value &= (0xFFFFFFFF ^ mask);
		value |= bitVal;
		
		return value;
	}
	final static int setThreeBits(int value, int bitVal, int start)
	{ 
		int mask = 0x7;
		mask = mask << start;
		value &= (0xFFFFFFFF ^ mask);
		value |= (bitVal << start);
		
		return value;
	}
	final static int setBit(int value, boolean setTrue, int bitNo)
    {
        if(!setTrue)
        {
            value = value & ~(1 << bitNo);
        }
        else
        {
            value = value | (1 << bitNo);
        }
        return value;
    }
    /*
     * @method getBits
     * @param start The start bit of the value to be gotten
     * @param end The end bit of the value to be gotten
     * @param value The value to be shortened
     *
     * This gets the value as a unsigned value
     *
     */
    final static int getBits(int start, int end, int value)
    {
        if(start == 0 && end == 31)// if we want whole num
            return value;
        //shift to the left (must be at least 1 or start > 0)
        value = value << (31 - end);
        value = value >> 1; // shift left one to eliminate neg
        value = setBit(value, false, 31); // remove neg
        value = value >> (31-end) + start - 1;
        return value;
    }
    /*
     * @method getBits
     * @param start The start bit of the value to be gotten
     * @param end The end bit of the value to be gotten
     * @param value The value to be shortened
     *
     * This gets the value as a signed value
     */
    final static int getBitsSigned(int start, int end, int value)
    {
        if(start == 0 && end == 31)// if we want whole num
            return value;
        //shift to the left (must be at least 1 or start > 0)
        value = value << (31 - end);
        value = value >> 1; // shift left one to eliminate neg
        value = value >> (31-end) + start - 1;
        return value;
    }
    /*
     * @method divideWordToByte
     * @param value this is the value to be decomposed
     * @return byte[] This is partition of value into an array of byte
     *
     */
    final static byte[] divideWordToBytes(int value)
    {
        byte valueInArr[] = new byte [4];
        valueInArr[0] = (byte)Tools.getBits(0, 7, value);
        valueInArr[1] = (byte)Tools.getBits(8, 15, value);
        valueInArr[2] = (byte)Tools.getBits(16, 23, value);
        valueInArr[3] = (byte)Tools.getBits(24, 31, value);
        return valueInArr;
    }
    /*
     * @method buildWord
     * @param values This is the array of bytes that should be used to build a word
     * @ return the build word
     */
    final static int buildWord(byte[] values)
    {
        int total = 0;
        total += Tools.getBits(0, 7, (int) values[0]);
        total += (Tools.getBits(0, 7, (int) values[1]) << 8);
        total += (Tools.getBits(0, 7, (int) values[2]) << 16);
        total += (Tools.getBits(0, 7, (int) values[3]) << 24);
        return total;
    }

}

