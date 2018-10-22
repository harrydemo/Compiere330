package com.compiere.client;

import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import org.compiere.model.MSystem;

class e
{
  String a;
  HashMap b = new HashMap();
  private Boolean c = null;
  private boolean d = false;
  private static final String[] z;

  e(Object paramObject)
  {
    if (paramObject == null)
    {
      b(null);
    }
    else if ((paramObject instanceof String))
    {
      b((String)paramObject);
    }
    else if ((paramObject instanceof MSystem))
    {
      MSystem localMSystem = (MSystem)paramObject;
      try
      {
        b(localMSystem.getSummary());
        if (a())
        {
          localMSystem.setRecord_ID(h());
          localMSystem.setSupportExpDate(new Timestamp(m()));
          localMSystem.setSupportLevel(l());
          localMSystem.setSupportUnits((int)i());
        }
      }
      catch (Exception localException)
      {
        System.err.println(localException.toString());
      }
    }
  }

  public String toString()
  {
    return this.a;
  }

  String a(String paramString)
  {
    int i = SysEnv.m;
    StringBuffer localStringBuffer = new StringBuffer(paramString).append(z[5]);
    if (i == 0)
      if (this.a == null)
        return localStringBuffer.append(z[4]).toString();
    if (i == 0)
      if (this.c == null)
        a();
    if (((i == 0) && (this.c == null)) || (!this.c.booleanValue()))
      return localStringBuffer.append(z[3]).toString();
    return localStringBuffer.toString();
  }

  boolean a()
  {
    if (this.a == null)
      return false;
    return d();
  }

  boolean a(String paramString1, String paramString2, String paramString3, int paramInt, long paramLong1, long paramLong2, long paramLong3, String paramString4, String paramString5)
  {
    return (a()) && (h(paramString1)) && (j(paramString2)) && (l(paramString3)) && (b(paramInt)) && (f(paramLong1)) && (b(paramLong2)) && (d(paramLong3)) && (n(paramString4)) && (p(paramString5));
  }

  boolean a(String paramString1, String paramString2, String paramString3, int paramInt, long paramLong)
  {
    return (a()) && (h(paramString1)) && (j(paramString2)) && (l(paramString3)) && (b(paramInt)) && (f(paramLong));
  }

  boolean b(String paramString1, String paramString2, String paramString3, int paramInt, long paramLong)
  {
    return (a()) && (h(paramString1)) && (j(paramString2)) && (l(paramString3)) && (b(paramInt)) && (!f(paramLong));
  }

  private void b(String paramString)
  {
    this.a = paramString;
    if ((this.a != null) && (this.a.length() == 0))
      this.a = null;
    this.c = null;
  }

  Boolean b()
  {
    return this.c;
  }

  void c()
  {
    int j = SysEnv.m;
    Iterator localIterator = this.b.keySet().iterator();
    ArrayList localArrayList = new ArrayList();
    while (true)
      if (localIterator.hasNext())
      {
        localArrayList.add(localIterator.next());
        if (j != 0)
          break;
        if (j == 0)
          continue;
      }
      else
      {
        Collections.sort(localArrayList);
      }
    StringBuffer localStringBuffer1 = new StringBuffer();
    int i = 0;
    do
    {
      if (i >= localArrayList.size())
        break;
      String str2 = (String)localArrayList.get(i);
      String str3 = (String)this.b.get(str2);
      if (j != 0)
        break label168;
      if ((j != 0) || (str3 == null))
        str3 = z[1];
      if (i > 0)
        localStringBuffer1.append(",");
      localStringBuffer1.append(str2).append("=").append(str3);
      i++;
    }
    while (j == 0);
    label168: String str1 = localStringBuffer1.toString();
    long l = c(str1);
    str1 = d(str1);
    String str4 = String.valueOf(l);
    if ((j != 0) || (str4.length() % 2 == 1))
      str4 = "0" + str4;
    StringBuffer localStringBuffer2 = new StringBuffer();
    localStringBuffer2.append(str4).append(z[0]).append(str1);
    this.a = localStringBuffer2.toString();
  }

  private boolean d()
  {
    if (this.a == null)
      return false;
    if (this.c != null)
      return this.c.booleanValue();
    int i = this.a.indexOf(z[0]);
    if (i == -1)
    {
      this.c = Boolean.FALSE;
      return false;
    }
    String str1 = this.a.substring(0, i);
    String str2 = this.a.substring(i + 2);
    str2 = e(str2);
    long l = c(str2);
    int j = 0;
    try
    {
      j = Integer.parseInt(str1);
    }
    catch (Exception localException)
    {
    }
    if (l != j)
    {
      this.c = Boolean.FALSE;
      return false;
    }
    this.b = new HashMap();
    StringTokenizer localStringTokenizer = new StringTokenizer(str2, ",");
    while (localStringTokenizer.hasMoreTokens())
    {
      String str3 = localStringTokenizer.nextToken();
      int k = str3.indexOf("=");
      if (k > 0)
      {
        String str4 = str3.substring(0, k);
        String str5 = str3.substring(k + 1);
        if (str5.equals(z[1]))
          str5 = null;
        this.b.put(str4, str5);
      }
    }
    this.c = Boolean.TRUE;
    return true;
  }

  private long c(String paramString)
  {
    long l = 19560129L;
    char[] arrayOfChar = paramString.toCharArray();
    for (int i = 0; i < arrayOfChar.length; i++)
    {
      int j = arrayOfChar[i];
      int k = i % 49;
      if (k == 0)
        k = 50;
      l += j * k;
    }
    if (l % 10L == 0L)
      l += 1L;
    return l;
  }

  private String d(String paramString)
  {
    int k = SysEnv.m;
    byte[] arrayOfByte = paramString.getBytes();
    int i = 0;
    do
    {
      if (i >= arrayOfByte.length)
        break;
      int j = i % 23;
      if (k != 0)
        continue;
      if (i % 2 == 0)
        j *= -1;
      int tmp43_42 = i;
      byte[] tmp43_41 = arrayOfByte;
      tmp43_41[tmp43_42] = (byte)(tmp43_41[tmp43_42] - j);
      i++;
    }
    while (k == 0);
    return a(arrayOfByte);
  }

  private String e(String paramString)
  {
    byte[] arrayOfByte = f(paramString);
    for (int i = 0; i < arrayOfByte.length; i++)
    {
      int j = i % 23;
      if (i % 2 == 0)
        j *= -1;
      int tmp34_33 = i;
      byte[] tmp34_32 = arrayOfByte;
      tmp34_32[tmp34_33] = (byte)(tmp34_32[tmp34_33] + j);
    }
    return new String(arrayOfByte);
  }

  private String a(byte[] paramArrayOfByte)
  {
    int m = SysEnv.m;
    int i = paramArrayOfByte.length;
    StringBuffer localStringBuffer = new StringBuffer(i * 2);
    int j = 0;
    do
    {
      if (j >= i)
        break;
      int k = paramArrayOfByte[j];
      if (m == 0)
        if (k < 0)
          k += 256;
      String str = Integer.toHexString(k);
      if (m != 0)
        continue;
      if (str.length() == 1)
        localStringBuffer.append("0");
      localStringBuffer.append(str);
      j++;
    }
    while (m == 0);
    return localStringBuffer.toString();
  }

  private byte[] f(String paramString)
  {
    int i = paramString.length() / 2;
    byte[] arrayOfByte = new byte[i];
    String str = paramString;
    try
    {
      for (int j = 0; j < i; j++)
        try
        {
          int k = j * 2;
          int m = Integer.parseInt(str.substring(k, k + 2), 16);
          arrayOfByte[j] = (byte)m;
        }
        catch (Exception localException2)
        {
        }
      return arrayOfByte;
    }
    catch (Exception localException1)
    {
    }
    return null;
  }

  private String e()
  {
    return (String)this.b.get(z[6]);
  }

  void g(String paramString)
  {
    this.b.put(z[6], paramString);
  }

  private boolean h(String paramString)
  {
    String str = e();
    if (str == null)
      str = "";
    if (paramString == null)
      paramString = "";
    return str.compareTo(paramString) == 0;
  }

  private String f()
  {
    return (String)this.b.get(z[15]);
  }

  void i(String paramString)
  {
    this.b.put(z[15], paramString);
  }

  private boolean j(String paramString)
  {
    String str = f();
    if (str == null)
      str = "";
    if (paramString == null)
      paramString = "";
    return str.compareTo(paramString) == 0;
  }

  private String g()
  {
    return (String)this.b.get(z[7]);
  }

  void k(String paramString)
  {
    this.b.put(z[7], paramString);
  }

  private boolean l(String paramString)
  {
    String str = g();
    if (str == null)
      str = "";
    if (paramString == null)
      paramString = "";
    return str.compareTo(paramString) == 0;
  }

  private int h()
  {
    return q((String)this.b.get(z[12]));
  }

  void a(int paramInt)
  {
    this.b.put(z[12], String.valueOf(paramInt));
  }

  private boolean b(int paramInt)
  {
    int i = h();
    if ((i == 0) || (paramInt == 0))
      return true;
    if (paramInt != i)
    {
      if (!this.d)
      {
        String str = e();
        System.err.println(z[10] + paramInt + z[11] + i + z[9] + str + ")");
      }
      this.d = true;
    }
    return true;
  }

  long i()
  {
    return r((String)this.b.get(z[8]));
  }

  void a(long paramLong)
  {
    this.b.put(z[8], String.valueOf(paramLong));
  }

  private boolean b(long paramLong)
  {
    long l = i();
    if (l == 0L)
      return true;
    return l >= paramLong;
  }

  long j()
  {
    return r((String)this.b.get(z[16]));
  }

  void c(long paramLong)
  {
    this.b.put(z[16], String.valueOf(paramLong));
  }

  private boolean d(long paramLong)
  {
    long l = j();
    if (l == 0L)
      return true;
    return l >= paramLong;
  }

  String k()
  {
    return (String)this.b.get(z[14]);
  }

  void m(String paramString)
  {
    this.b.put(z[14], String.valueOf(paramString));
  }

  private boolean n(String paramString)
  {
    String str = k();
    if ((str == null) || (str.length() == 0))
      return true;
    if ((paramString == null) || (paramString.length() == 0))
      return false;
    return str.indexOf(paramString) != -1;
  }

  String l()
  {
    return (String)this.b.get(z[13]);
  }

  void o(String paramString)
  {
    this.b.put(z[13], String.valueOf(paramString));
  }

  private boolean p(String paramString)
  {
    String str = l();
    if ((str == null) || (str.length() == 0))
      return true;
    if ((paramString == null) || (paramString.length() == 0))
      return false;
    return str.indexOf(paramString) != -1;
  }

  long m()
  {
    return r((String)this.b.get(z[2]));
  }

  void e(long paramLong)
  {
    this.b.put(z[2], String.valueOf(paramLong));
  }

  private boolean f(long paramLong)
  {
    long l = m();
    return l >= paramLong;
  }

  private int q(String paramString)
  {
    if (paramString != null)
      try
      {
        return Integer.parseInt(paramString);
      }
      catch (Exception localException)
      {
      }
    return 0;
  }

  private long r(String paramString)
  {
    if (paramString != null)
      try
      {
        return Long.parseLong(paramString);
      }
      catch (Exception localException)
      {
      }
    return 0L;
  }

  static
  {
    // Byte code:
    //   0: bipush 17
    //   2: anewarray 8	java/lang/String
    //   5: dup
    //   6: iconst_0
    //   7: ldc 69
    //   9: bipush 255
    //   11: goto +175 -> 186
    //   14: aastore
    //   15: dup
    //   16: iconst_1
    //   17: ldc 58
    //   19: iconst_0
    //   20: goto +166 -> 186
    //   23: aastore
    //   24: dup
    //   25: iconst_2
    //   26: ldc 119
    //   28: iconst_1
    //   29: goto +157 -> 186
    //   32: aastore
    //   33: dup
    //   34: iconst_3
    //   35: ldc 34
    //   37: iconst_2
    //   38: goto +148 -> 186
    //   41: aastore
    //   42: dup
    //   43: iconst_4
    //   44: ldc 31
    //   46: iconst_3
    //   47: goto +139 -> 186
    //   50: aastore
    //   51: dup
    //   52: iconst_5
    //   53: ldc 29
    //   55: iconst_4
    //   56: goto +130 -> 186
    //   59: aastore
    //   60: dup
    //   61: bipush 6
    //   63: ldc 95
    //   65: iconst_5
    //   66: goto +120 -> 186
    //   69: aastore
    //   70: dup
    //   71: bipush 7
    //   73: ldc 101
    //   75: bipush 6
    //   77: goto +109 -> 186
    //   80: aastore
    //   81: dup
    //   82: bipush 8
    //   84: ldc 111
    //   86: bipush 7
    //   88: goto +98 -> 186
    //   91: aastore
    //   92: dup
    //   93: bipush 9
    //   95: ldc 109
    //   97: bipush 8
    //   99: goto +87 -> 186
    //   102: aastore
    //   103: dup
    //   104: bipush 10
    //   106: ldc 106
    //   108: bipush 9
    //   110: goto +76 -> 186
    //   113: aastore
    //   114: dup
    //   115: bipush 11
    //   117: ldc 108
    //   119: bipush 10
    //   121: goto +65 -> 186
    //   124: aastore
    //   125: dup
    //   126: bipush 12
    //   128: ldc 103
    //   130: bipush 11
    //   132: goto +54 -> 186
    //   135: aastore
    //   136: dup
    //   137: bipush 13
    //   139: ldc 118
    //   141: bipush 12
    //   143: goto +43 -> 186
    //   146: aastore
    //   147: dup
    //   148: bipush 14
    //   150: ldc 115
    //   152: bipush 13
    //   154: goto +32 -> 186
    //   157: aastore
    //   158: dup
    //   159: bipush 15
    //   161: ldc 99
    //   163: bipush 14
    //   165: goto +21 -> 186
    //   168: aastore
    //   169: dup
    //   170: bipush 16
    //   172: ldc 113
    //   174: bipush 15
    //   176: goto +10 -> 186
    //   179: aastore
    //   180: putstatic 381	com/compiere/client/e:z	[Ljava/lang/String;
    //   183: goto +193 -> 376
    //   186: swap
    //   187: invokevirtual 85	java/lang/String:toCharArray	()[C
    //   190: dup
    //   191: arraylength
    //   192: swap
    //   193: iconst_0
    //   194: istore_0
    //   195: swap
    //   196: dup_x1
    //   197: iconst_1
    //   198: if_icmpgt +80 -> 278
    //   201: dup
    //   202: iload_0
    //   203: dup2
    //   204: caload
    //   205: iload_0
    //   206: iconst_5
    //   207: irem
    //   208: tableswitch	default:+52 -> 260, 0:+32->240, 1:+37->245, 2:+42->250, 3:+47->255
    //   241: istore_3
    //   242: goto +20 -> 262
    //   245: bipush 35
    //   247: goto +15 -> 262
    //   250: bipush 11
    //   252: goto +10 -> 262
    //   255: bipush 64
    //   257: goto +5 -> 262
    //   260: bipush 11
    //   262: ixor
    //   263: i2c
    //   264: castore
    //   265: iinc 0 1
    //   268: swap
    //   269: dup_x1
    //   270: ifne +8 -> 278
    //   273: dup2
    //   274: swap
    //   275: goto -72 -> 203
    //   278: swap
    //   279: dup_x1
    //   280: iload_0
    //   281: if_icmpgt -80 -> 201
    //   284: new 8	java/lang/String
    //   287: dup_x1
    //   288: swap
    //   289: invokespecial 376	java/lang/String:<init>	([C)V
    //   292: invokevirtual 379	java/lang/String:intern	()Ljava/lang/String;
    //   295: swap
    //   296: pop
    //   297: swap
    //   298: tableswitch	default:+-284 -> 14, 0:+-275->23, 1:+-266->32, 2:+-257->41, 3:+-248->50, 4:+-239->59, 5:+-229->69, 6:+-218->80, 7:+-207->91, 8:+-196->102, 9:+-185->113, 10:+-174->124, 11:+-163->135, 12:+-152->146, 13:+-141->157, 14:+-130->168, 15:+-119->179
  }
}