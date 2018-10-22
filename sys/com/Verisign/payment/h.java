package com.Verisign.payment;

public class h
{
  public static char[] a = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };

  private static char a(int paramInt)
  {
    return a[paramInt];
  }

  public static String a(String paramString)
  {
    char[] arrayOfChar = new char[64];
    int i = paramString.length();
    paramString = paramString + "   ";
    byte[] arrayOfByte = paramString.getBytes();
    int j = 0;
    for (int k = 0; k < i; k += 3)
    {
      arrayOfChar[(j++)] = a(arrayOfByte[k] >> 2);
      arrayOfChar[(j++)] = a(arrayOfByte[k] << 4 & 0x30 | arrayOfByte[(k + 1)] >> 4 & 0xF);
      arrayOfChar[(j++)] = a(arrayOfByte[(k + 1)] << 2 & 0x3C | arrayOfByte[(k + 2)] >> 6 & 0x3);
      arrayOfChar[(j++)] = a(arrayOfByte[(k + 2)] & 0x3F);
    }
    if (k == i + 1)
    {
      arrayOfChar[(j - 1)] = '=';
    }
    else if (k == i + 2)
    {
      arrayOfChar[(j - 1)] = '=';
      arrayOfChar[(j - 2)] = '=';
    }
    arrayOfChar[j] = '\000';
    return new String(arrayOfChar, 0, j);
  }
}