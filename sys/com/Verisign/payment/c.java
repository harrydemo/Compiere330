package com.Verisign.payment;

import java.io.PrintStream;
import java.util.Hashtable;

public class c
{
  public static final int a = -1;
  public static final int b = -6;
  public static final int c = -7;
  public static final int d = -8;
  public static final int e = -9;
  public static final int f = -10;
  public static final int g = -11;
  public static final int h = -12;
  public static final int i = -20;
  public static final int j = -21;
  public static final int k = -23;
  public static final int l = -27;
  public static final int m = -28;
  public static final int n = -30;
  public static final int o = -31;
  public static final int p = -32;
  public static final int q = -98;
  public static Hashtable r = new Hashtable();

  static
  {
    r.put(new Integer(-1), "Failed to connect to host");
    r.put(new Integer(-6), "Parameter list format error: & in name");
    r.put(new Integer(-7), "Parameter list format error: invalid [] name length clause");
    r.put(new Integer(-8), "SSL failed to connect to host");
    r.put(new Integer(-9), "SSL read failed");
    r.put(new Integer(-10), "SSL write failed");
    r.put(new Integer(-11), "Proxy authorization failed");
    r.put(new Integer(-12), "Timeout waiting for response");
    r.put(new Integer(-20), "Proxy read failed");
    r.put(new Integer(-21), "Proxy write failed");
    r.put(new Integer(-23), "Host address not specified");
    r.put(new Integer(-27), "Parameter list format error: unmatched name");
    r.put(new Integer(-28), "Parameter list format error: name");
    r.put(new Integer(-30), "Invalid timeout value");
    r.put(new Integer(-31), "The certificate chain did not validate, no local certificate found");
    r.put(new Integer(-32), "The certificate chain did not validate, common name did not match URL");
    r.put(new Integer(-98), "Internal client error");
  }

  public static String a(boolean paramBoolean, int paramInt)
  {
    String str2 = (String)r.get(new Integer(paramInt));
    String str1;
    if (paramBoolean)
      str1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><XMLPayResponse><ResponseData><MerchantId>M1234</MerchantId><TransactionResults><TransactionResult Id=\"004003\"><Result>" + paramInt + "</Result>" + "<Message>" + str2 + "</Message>" + "<PNRef>PN123412345</PNRef>" + "<AuthCode>12345678</AuthCode>" + "<HostCode>0</HostCode>" + "</TransactionResult>" + "</TransactionResults>" + "</ResponseData>" + "</XMLPayResponse>";
    else
      str1 = "RESULT=" + paramInt + "&RESPMSG=" + str2;
    return str1;
  }

  public static String a(boolean paramBoolean, int paramInt, Exception paramException)
  {
    return a(paramBoolean, paramInt, paramException.toString());
  }

  public static String a(boolean paramBoolean, int paramInt, String paramString)
  {
    String str1 = a(paramBoolean, paramInt);
    if ((paramString == null) || (paramString.length() == 0))
      return str1;
    String str2;
    if (!paramBoolean)
    {
      str2 = str1 + ", " + paramString;
    }
    else
    {
      int i1 = str1.indexOf("</Message>");
      str2 = str1.substring(0, i1) + ", " + paramString + str1.substring(i1);
    }
    return str2;
  }

  public static void a(String[] paramArrayOfString)
  {
    System.out.println(a(false, -1));
    System.out.println(a(false, -23));
    System.out.println(a(true, -1));
    System.out.println(a(true, -23));
    System.out.println(a(false, -1, new Exception()));
    System.out.println(a(false, -23, new Exception()));
    System.out.println(a(true, -1, new Exception()));
    System.out.println(a(true, -23, new Exception()));
    System.out.println(a(false, -1, "Any Text"));
    System.out.println(a(false, -23, "Any Text"));
    System.out.println(a(true, -1, "Any Text"));
    System.out.println(a(true, -23, "Any Text"));
  }
}