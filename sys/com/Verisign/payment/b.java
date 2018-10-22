package com.Verisign.payment;

import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;

public class b
{
  public boolean a = false;
  public static final int b = 30;
  public static final String c = "PNCLIENT";
  public static final String d = "J306";
  public static final String e = "HTTP";
  public static final String f = "1.0";
  public static final String g = "NOERROR";
  public static final int h = 3;
  public static final String i = "<?xml";
  public static final String j = "text/xml";
  public static final String k = "text/namevalue";
  public static final int l = 1024;
  public String m;
  public int n = 0;
  public String o = "";
  public int p = 0;
  public String q = "";
  public String r = "";
  public int s = 30;
  public static final String t = "TRANS";
  public static final String u = "RETRY";
  public static final String v = "ACK  ";
  public static final int w = 1000;
  public String x = "certs";
  public boolean y = false;
  public String z = "";
  public String aa = "";
  public String ab = "";
  public String ac = "";
  public String ad = "";
  public int ae = 2;
  public static final boolean af = false;

  public b()
  {
    c();
    d();
  }

  private synchronized void c()
  {
    try
    {
      Properties localProperties = System.getProperties();
      String str = (String)localProperties.get("PFPRO_ACK_TMO");
      this.ae = new Integer(str).intValue();
    }
    catch (Exception localException)
    {
      c("ack_time_out not specified as -D switch, defaulting to:");
    }
    c("ack_time_out=" + new Integer(this.ae).toString() + " seconds");
  }

  private synchronized void d()
  {
    try
    {
      Properties localProperties = System.getProperties();
      String str = (String)localProperties.get("PFPRO_CERT_PATH");
      if ((str != null) && (str.length() > 0))
        a(str);
    }
    catch (Exception localException)
    {
    }
  }

  public int a(String paramString1, int paramInt1, int paramInt2, String paramString2, int paramInt3, String paramString3, String paramString4)
  {
    a(paramString1, paramInt1);
    a(paramString2, paramInt3, paramString3, paramString4);
    a(paramInt2);
    g();
    return 0;
  }

  public int a()
  {
    return 0;
  }

  public void a(String paramString)
  {
    this.x = paramString;
  }

  public synchronized String b(String paramString)
  {
    return d(paramString);
  }

  public String b()
  {
    return "J306";
  }

  private int a(e parame, StringBuffer paramStringBuffer, String paramString)
  {
    int i1 = 0;
    int i3 = 0;
    byte[] arrayOfByte1 = new byte[4];
    for (int i2 = 0; i2 < 3; i2++)
    {
      if (i3 < 0)
      {
        i3 = parame.a();
        if (i3 < 0)
          return i3;
        i3 = b(parame, paramString);
        if (i3 < 0)
          return i3;
      }
      i3 = parame.a(arrayOfByte1, 0, 4);
      if (i3 != 4)
      {
        if (i3 < -1)
          continue;
        i3 = -9;
        parame.a("incorrect length received");
      }
      else
      {
        i1 = a(arrayOfByte1);
        byte[] arrayOfByte2 = new byte[1024];
        int i4 = 0;
        int i5 = 0;
        while (i4 < i1)
        {
          i5 = parame.a(arrayOfByte2, 0, Math.min(i1 - i4, 1024));
          if (i5 >= 0)
          {
            paramStringBuffer.append(new String(arrayOfByte2).substring(0, i5));
            i4 += i5;
          }
          else
          {
            i1 = i4;
          }
        }
        if (i5 < 0)
        {
          i3 = -9;
          parame.a("packet size mismatch");
        }
        else
        {
          return 0;
        }
      }
    }
    return i3;
  }

  private int a(e parame, String paramString)
  {
    return c(parame, paramString);
  }

  private int b(e parame, String paramString)
  {
    return c(parame, paramString);
  }

  private int c(e parame, String paramString)
  {
    int i1 = -1;
    int i2 = -1;
    for (int i3 = 0; (i3 < 3) && (i1 < 0); i3++)
    {
      i1 = parame.a(paramString.getBytes());
      if (i1 >= 0)
        break;
      i1 = -10;
      i2 = parame.a();
      if (i2 < 0)
        return i2;
    }
    return i1;
  }

  private void a(e parame)
  {
    if (this.y)
    {
      parame.a(this.o, this.p);
      parame.a(this.q, this.r);
    }
    else
    {
      parame.a(this.m, this.n);
    }
    parame.b(this.m);
    parame.a(this.n);
    parame.c(this.s);
    parame.b(3);
  }

  private String a(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    String str = "POST HTTP/1.0\nUser-Agent: PNCLIENT J306 HTTP 1.0\nContent-type: " + paramString1 + "\n" + "GUID: " + paramString2 + "\n" + "TransState: " + paramString3 + "\n" + "Duration: " + paramInt + "\n";
    return str;
  }

  private String a(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4)
  {
    String str1 = e();
    String str2 = new Integer(paramString4.length() + str1.length()).toString();
    String str3 = "POST HTTP/1.0\nUser-Agent: PNCLIENT J306 HTTP 1.0\nContent-type: " + paramString1 + "\n" + "GUID: " + paramString2 + "\n" + "TransState: " + paramString3 + "\n" + "Timeout: " + paramInt + "\n" + "Content-length: " + str2 + "\n\n" + paramString4 + str1;
    c("buildRequest(): newRequest=" + str3);
    return str3;
  }

  private int a(byte[] paramArrayOfByte)
  {
    int i1;
    if (paramArrayOfByte[3] < 0)
      i1 = 256 + paramArrayOfByte[3];
    else
      i1 = paramArrayOfByte[3];
    if (paramArrayOfByte[2] < 0)
      i1 += (256 + paramArrayOfByte[2]) * 256;
    else
      i1 += paramArrayOfByte[2] * 256;
    if (paramArrayOfByte[1] < 0)
      i1 += (256 + paramArrayOfByte[1]) * 65536;
    else
      i1 += paramArrayOfByte[1] * 65536;
    if (paramArrayOfByte[0] < 0)
      i1 += (256 + paramArrayOfByte[0]) * 16777216;
    else
      i1 += paramArrayOfByte[0] * 16777216;
    return i1;
  }

  private synchronized String a(String paramString1, String paramString2)
  {
    String str = "";
    if (paramString2 != null)
    {
      str = "&" + paramString1 + "[";
      str = str + paramString2.length();
      str = str + "]=";
      str = str + paramString2;
    }
    return str;
  }

  private synchronized String e()
  {
    String str = "";
    if (!this.a)
      str = a("VIT_OSNAME", this.z) + a("VIT_OSARCH", this.aa) + a("VIT_OSVERSION", this.ab) + a("VIT_JAVAVERSION", this.ac) + a("VIT_PROXY", this.ad);
    return str;
  }

  public static void f()
  {
  }

  public static void c(String paramString)
  {
  }

  private synchronized String d(String paramString)
  {
    int i1 = 0;
    int i2 = 0;
    e locale = null;
    String str2;
    try
    {
      String str1;
      if (paramString.indexOf("<?xml") >= 0)
      {
        this.a = true;
        str1 = "text/xml";
      }
      else
      {
        this.a = false;
        str1 = "text/namevalue";
      }
      if (this.m == null)
        return c.a(this.a, -23);
      if (!this.a)
      {
        StringBuffer localStringBuffer1 = new StringBuffer();
        i2 = d.a(paramString, localStringBuffer1);
        if (i2 < 0)
          return c.a(this.a, i2, localStringBuffer1.toString());
      }
      c("processTransaction: Timeout = (" + this.s + ")\n");
      if (this.s <= 0)
        return c.a(this.a, -30, Integer.toString(this.s));
      try
      {
        try
        {
          Class.forName("javax.net.ssl.SSLException");
          Class.forName("javax.security.cert.CertificateException");
          Class.forName("javax.net.SocketFactory");
        }
        catch (ClassNotFoundException localClassNotFoundException)
        {
          throw new Exception("JSSE jar files not found in CLASSPATH");
        }
        locale = new e(this.x);
      }
      catch (Exception localException1)
      {
        return c.a(this.a, -31, localException1.getMessage());
      }
      locale.e("2");
      a(locale);
      locale.e("3");
      i2 = locale.a();
      locale.d(":" + Integer.toString(i2));
      if (i2 < 0)
        return c.a(this.a, i2, locale.b());
      locale.e("4");
      String str3 = i.a();
      str4 = a(str1, str3, "TRANS", this.s, paramString);
      String str5 = a(str1, str3, "RETRY", this.s, paramString);
      locale.e("5");
      i2 = b(locale, str4);
      locale.d(":" + Integer.toString(i2));
      if (i2 < 0)
        return c.a(this.a, i2, locale.b());
      long l1 = new Date().getTime();
      StringBuffer localStringBuffer2 = new StringBuffer();
      locale.e("6");
      i2 = a(locale, localStringBuffer2, str5);
      locale.d(":" + Integer.toString(i2));
      locale.i();
      if (i2 < 0)
        return c.a(this.a, i2, locale.b());
      locale.e("7");
      str2 = localStringBuffer2.toString();
      long l2 = new Date().getTime();
      int i3 = (int)((l2 - l1) / 1000L);
      String str6 = a(str1, str3, "ACK  ", i3);
      locale.e("8");
      i2 = a(locale, str6);
      locale.d(":" + Integer.toString(i2));
      locale.e("9");
      locale.f();
      c("waiting for ack_time_out = " + this.ae + " seconds");
      try
      {
        wait(this.ae * 1000);
      }
      catch (Exception localException3)
      {
      }
      locale.e("10");
      locale.d();
    }
    catch (Exception localException2)
    {
      String str4 = (locale != null ? locale.g() : "0") + " " + b() + " " + localException2.toString();
      return c.a(this.a, -98, str4);
    }
    return str2;
  }

  private synchronized void a(String paramString, int paramInt)
  {
    this.m = paramString;
    this.n = paramInt;
  }

  private synchronized void a(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    this.o = (paramString1 != null ? paramString1 : "");
    this.p = paramInt;
    this.q = (paramString2 != null ? paramString2 : "");
    this.r = (paramString3 != null ? paramString3 : "");
    this.y = ((this.o.length() > 0) && (this.p > 0));
  }

  private synchronized void a(int paramInt)
  {
    this.s = paramInt;
  }

  private synchronized void g()
  {
    try
    {
      Properties localProperties = System.getProperties();
      try
      {
        this.z = ((String)localProperties.get("os.name"));
      }
      catch (Exception localException1)
      {
      }
      try
      {
        this.aa = ((String)localProperties.get("os.arch"));
      }
      catch (Exception localException2)
      {
      }
      try
      {
        this.ab = ((String)localProperties.get("os.version"));
      }
      catch (Exception localException3)
      {
      }
      try
      {
        this.ac = ((String)localProperties.get("java.version"));
      }
      catch (Exception localException4)
      {
      }
    }
    catch (Exception localException5)
    {
    }
    this.ad = (this.y ? "Y" : "N");
  }
}