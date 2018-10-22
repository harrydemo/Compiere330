package com.Verisign.payment;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.cert.CertificateException;
import java.util.Date;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;

public class e
{
  public boolean a = false;
  public int b = 30;
  public String c;
  public int d = 0;
  public Socket e = null;
  public SSLSocket f = null;
  public f g = null;
  public Socket h;
  public boolean i = false;
  public int j = 0;
  public String k = null;
  public String l = "";
  public int m = 0;
  public String n = "";
  public String o = "";
  public String p = new String("1");
  public static final int q = 1;
  public static final int r = 2;
  public static final int s = 3;
  public static final int t = 4;
  public static final int u = 6;
  public static final int v = 7;
  public static final int w = 1024;
  public static final boolean x = false;

  public e(String paramString)
    throws IOException
  {
    this("", 0, 30, paramString);
  }

  public e(String paramString1, int paramInt1, int paramInt2, String paramString2)
    throws IOException
  {
    this.c = paramString1;
    this.d = paramInt1;
    this.b = paramInt2;
    e(this.p);
    try
    {
      this.g = new f(paramString2);
    }
    catch (SSLException localSSLException)
    {
      throw new IOException(localSSLException.getMessage());
    }
  }

  public void a(String paramString)
  {
    c((b() != null ? b() : "") + paramString);
  }

  public void a(char[] paramArrayOfChar)
  {
    a(new String(paramArrayOfChar));
  }

  public int a()
  {
    int i1 = -1;
    for (int i2 = 0; (i2 < this.j) && (i1 < 0); i2++)
    {
      d();
      i1 = h();
      if (i1 < 0)
        continue;
      if (this.i)
      {
        i1 = j();
        if (i1 < 0)
          continue;
      }
      try
      {
        i1 = c();
      }
      catch (CertificateException localCertificateException)
      {
        c(localCertificateException.getMessage());
        return -32;
      }
    }
    return i1;
  }

  public String b()
  {
    return this.k;
  }

  public int c()
    throws CertificateException
  {
    int i1 = 0;
    this.a = true;
    i1 = a(6, null, 0, 0);
    if (i1 >= 0)
      i1 = this.g.a(this.f, this.c);
    if (i1 < 0)
      return -8;
    return 0;
  }

  public void a(String paramString, int paramInt)
  {
    this.l = paramString;
    this.m = paramInt;
  }

  public void b(String paramString)
  {
    this.c = paramString;
  }

  public void a(int paramInt)
  {
    this.d = paramInt;
  }

  public void c(String paramString)
  {
    this.k = paramString;
    f("last error = " + this.k);
  }

  public void b(char[] paramArrayOfChar)
  {
    c(new String(paramArrayOfChar));
  }

  public void a(String paramString1, String paramString2)
  {
    this.n = paramString1;
    this.o = paramString2;
    this.i = true;
  }

  public void b(int paramInt)
  {
    this.j = paramInt;
  }

  public void c(int paramInt)
  {
    this.b = paramInt;
  }

  public void d(String paramString)
  {
    this.p += paramString;
    i();
  }

  public int d()
  {
    e();
    if (this.e == null)
      return 0;
    return a(4, null, 0, 0);
  }

  public int e()
  {
    if (this.f == null)
      return 0;
    return a(7, null, 0, 0);
  }

  private int b(byte[] paramArrayOfByte)
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

  private int a(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    long l1 = 0L;
    long l2 = new Date().getTime();
    long l3 = new Date().getTime();
    int i1 = 0;
    c(null);
    try
    {
      switch (paramInt1)
      {
      case 6:
        d("u");
        if (this.f != null)
          break;
        this.f = this.g.a(this.e, this.l, this.m);
        break;
      case 1:
        d("o");
        this.e = new Socket(this.l, this.m);
        this.e.setSoTimeout(this.b * 1000);
        break;
      case 2:
        d("r");
        InputStream localInputStream = k();
        i1 = localInputStream.read(paramArrayOfByte, paramInt2, paramInt3);
        d(Integer.toString(paramInt3 - paramInt2));
        l3 = new Date().getTime();
        l1 = l3 - l2;
        f("start, end = " + Integer.toString(paramInt2) + ", " + Integer.toString(paramInt3));
        f("read result: " + Integer.toString(i1));
        if (i1 < 0)
          i1 = -12;
        f("retValue = " + Integer.toString(i1));
        f("timeTaken = " + Integer.toString((int)(l1 / 1000L)));
        break;
      case 3:
        d("w");
        OutputStream localOutputStream = l();
        localOutputStream.write(paramArrayOfByte, paramInt2, paramInt3);
        f("wrote request...");
        break;
      case 4:
        d("c");
        this.e.close();
        this.e = null;
        break;
      case 7:
        d("s");
        this.f.close();
        this.f = null;
      case 5:
      }
    }
    catch (Exception localException)
    {
      d("e");
      c("i/o error: " + localException.toString() + ", ");
      this.e = null;
      this.f = null;
      i1 = -1;
    }
    return i1;
  }

  private int j()
  {
    int i1 = 0;
    int i2 = 0;
    String str1 = this.n + ":" + this.o;
    String str2 = h.a(str1);
    str1 = this.c + ":" + this.d;
    String str3 = "CONNECT " + str1 + " HTTP/1.0\nProxy-authorization: Basic " + str2 + "\n\n";
    i2 = a(str3.getBytes());
    if (i2 < 0)
      return -21;
    byte[] arrayOfByte = new byte[1024];
    i1 = a(arrayOfByte, 0, arrayOfByte.length);
    if (i1 <= 0)
      return -20;
    i1 = i1 < arrayOfByte.length ? i1 : arrayOfByte.length;
    arrayOfByte[(i1 - 1)] = 0;
    String str4 = new String(arrayOfByte);
    int i3 = 0;
    do
    {
      i3++;
      if (i3 >= i1)
        break;
    }
    while (str4.charAt(i3) != ' ');
    while ((i3 < i1) && (str4.charAt(i3) == ' '))
      i3++;
    int i4 = i3;
    while ((i3 < i1) && (str4.charAt(i3) != ' '))
      i3++;
    int i5 = i3;
    String str5 = str4.substring(i4, i5);
    i1 = new Integer(str5).intValue();
    if (i1 != 200)
    {
      c(str4);
      return -11;
    }
    return 0;
  }

  public void f()
  {
    try
    {
      l().flush();
    }
    catch (Exception localException)
    {
    }
  }

  private InputStream k()
    throws IOException
  {
    if (this.f != null)
      return this.f.getInputStream();
    return this.e.getInputStream();
  }

  private OutputStream l()
    throws IOException
  {
    if (this.f != null)
      return this.f.getOutputStream();
    return this.e.getOutputStream();
  }

  public String g()
  {
    return this.p;
  }

  public int h()
  {
    int i1 = 0;
    i1 = a(1, null, 0, 0);
    if (i1 < 0)
      return -1;
    return 0;
  }

  public void i()
  {
    f("state = " + this.p);
  }

  public static void m()
  {
  }

  public static void f(String paramString)
  {
  }

  public int a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return a(2, paramArrayOfByte, paramInt1, paramInt2);
  }

  public void e(String paramString)
  {
    this.p = paramString;
    i();
  }

  public int a(byte[] paramArrayOfByte)
  {
    return b(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int b(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return a(3, paramArrayOfByte, paramInt1, paramInt2);
  }
}