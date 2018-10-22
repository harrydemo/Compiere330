package com.Verisign.payment;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.server.UID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

public class i
{
  public static String a(byte[] paramArrayOfByte, int paramInt)
  {
    String str1 = new String("0123456789ABCDEF");
    String str2 = new String();
    for (int i = 0; i < paramInt; i++)
    {
      str2 = str2 + str1.charAt(paramArrayOfByte[i] & 0xF);
      str2 = str2 + str1.charAt(paramArrayOfByte[i] >> 4 & 0xF);
    }
    return str2;
  }

  public static String a()
  {
    long l1 = new Date().getTime();
    long l2 = new Random().nextLong();
    String str1 = new Long(l1 + Math.abs(l2)).toString();
    String str2;
    try
    {
      str2 = InetAddress.getLocalHost().getHostAddress();
    }
    catch (UnknownHostException localUnknownHostException1)
    {
      str2 = "";
    }
    String str3;
    try
    {
      localObject1 = InetAddress.getLocalHost();
      localObject2 = new Socket(InetAddress.getLocalHost(), 80);
      str3 = ((Socket)localObject2).toString();
    }
    catch (UnknownHostException localUnknownHostException2)
    {
      str3 = "";
    }
    catch (IOException localIOException)
    {
      str3 = "";
    }
    Object localObject1 = new UID();
    Object localObject2 = str1 + str2 + str3 + ((UID)localObject1).toString();
    String str4;
    try
    {
      localObject3 = MessageDigest.getInstance("MD5");
      byte[] arrayOfByte1 = ((String)localObject2).getBytes();
      byte[] arrayOfByte2 = ((MessageDigest)localObject3).digest(arrayOfByte1);
      str4 = new String(arrayOfByte2);
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      str4 = ((String)localObject2).substring(0, 16);
    }
    Object localObject3 = new String(a(str4.getBytes(), str4.getBytes().length));
    return (String)(String)(String)localObject3;
  }

  public static void a(String[] paramArrayOfString)
  {
    System.out.println(a());
  }
}