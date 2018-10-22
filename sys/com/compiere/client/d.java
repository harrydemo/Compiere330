package com.compiere.client;

import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.RowSet;
import org.compiere.model.MSystem;
import org.compiere.util.DB;

class d
{
  private static final boolean a = true;
  private static final String b;
  private String c = null;
  private String[] d = null;
  private RowSet e = null;
  private RowSet f = null;
  private boolean g = false;
  private boolean h = false;
  private int i = 0;
  private int j = 0;
  private int k = 0;
  private static Connection l;
  private static Class m;
  private static String[] n;
  private static final String[] z;

  Boolean a()
  {
    c(z[15]);
    a(true);
    try
    {
      if ((SysEnv.m != 0) || (l != null))
        l.close();
    }
    catch (SQLException localSQLException)
    {
      return Boolean.FALSE;
    }
    return Boolean.TRUE;
  }

  Boolean a(MSystem paramMSystem)
  {
    int i2 = SysEnv.m;
    if (m != null)
      return Boolean.TRUE;
    String str1 = System.getProperty(z[28], b);
    String str2 = z[36];
    try
    {
      StringBuffer localStringBuffer1 = new StringBuffer(z[25]).append(str1).append(z[33]).append(z[31]).append(URLEncoder.encode(paramMSystem.getUserName(), str2)).append(z[27]).append(URLEncoder.encode(paramMSystem.getPassword(), str2)).append(z[29]).append(URLEncoder.encode(InetAddress.getLocalHost().toString(), str2)).append(z[30]).append(URLEncoder.encode(paramMSystem.getName(), str2)).append(z[35]).append(URLEncoder.encode(paramMSystem.getReleaseNo(), str2)).append(z[26]).append(URLEncoder.encode(paramMSystem.getVersion(), str2));
      URL localURL = new URL(localStringBuffer1.toString());
      URLConnection localURLConnection = localURL.openConnection();
      InputStreamReader localInputStreamReader = new InputStreamReader(localURLConnection.getInputStream());
      StringBuffer localStringBuffer2 = new StringBuffer();
      while (true)
      {
        int i1;
        if ((i1 = localInputStreamReader.read()) != -1)
        {
          localStringBuffer2.append((char)i1);
          if (i2 != 0)
            break;
          if (i2 == 0)
            continue;
        }
        else
        {
          localInputStreamReader.close();
        }
      }
      if (i2 == 0)
      {
        if (localStringBuffer2.toString().indexOf(b) == -1)
        {
          System.err.println(localStringBuffer2.toString());
          return Boolean.FALSE;
        }
        localURL = new URL(localStringBuffer2.toString());
      }
      ClassLoader localClassLoader = Thread.currentThread().getContextClassLoader();
      URLClassLoader localURLClassLoader = new URLClassLoader(new URL[] { localURL }, localClassLoader);
      if (i2 == 0)
        if (localURLClassLoader == null)
        {
          System.err.println(z[32]);
          if (i2 == 0)
            break label383;
        }
      m = localURLClassLoader.loadClass(z[24]);
    }
    catch (Exception localException)
    {
      label383: System.err.println(z[34]);
      return Boolean.FALSE;
    }
    if (m != null)
      a(false);
    return Boolean.TRUE;
  }

  private void a(boolean paramBoolean)
  {
    int i2 = SysEnv.m;
    int i1 = 0;
    do
    {
      if (i1 >= n.length)
        break;
      if (i2 == 0);
      String str = new StringBuilder().append(z[39]).append(n[i1]).append(paramBoolean ? z[37] : z[38]).toString();
      a(str, true);
      i1++;
    }
    while (i2 == 0);
  }

  Object a(String paramString, String[] paramArrayOfString, RowSet paramRowSet1, RowSet paramRowSet2, Boolean paramBoolean1, Boolean paramBoolean2)
  {
    int i1 = SysEnv.m;
    if (m == null)
    {
      System.err.println(z[6]);
      return Boolean.FALSE;
    }
    this.c = paramString;
    this.d = paramArrayOfString;
    this.e = paramRowSet1;
    this.f = paramRowSet2;
    this.g = paramBoolean1.booleanValue();
    this.h = paramBoolean2.booleanValue();
    if (i1 == 0)
    {
      if (this.h)
        System.out.println(new StringBuilder().append(z[1]).append(paramString).toString());
    }
    else
      if (i1 == 0)
        break label139;
    System.out.println(new StringBuilder().append(z[0]).append(paramString).toString());
    try
    {
      label139: Method localMethod = m.getMethod(z[3], new Class[] { d.class });
      localMethod.invoke(m.newInstance(), new Object[] { this });
    }
    catch (Exception localException)
    {
      System.err.println(new StringBuilder().append(z[5]).append(localException.toString()).toString());
      return Boolean.FALSE;
    }
    String str = new StringBuilder().append(z[2]).append(this.k).append(z[4]).append(this.j).append(z[8]).append(this.i).toString();
    System.out.println(new StringBuilder().append(z[7]).append(str).toString());
    return str;
  }

  String b()
  {
    return this.c;
  }

  String[] c()
  {
    return this.d;
  }

  RowSet d()
  {
    return this.e;
  }

  RowSet e()
  {
    return this.f;
  }

  void a(String paramString, Exception paramException)
  {
    int i1 = SysEnv.m;
    String str = new StringBuilder().append(z[14]).append(paramString).toString();
    if (i1 == 0)
    {
      if (paramException != null)
      {
        str = new StringBuilder().append(str).append(z[13]).append(paramException).toString();
        if (i1 != 0)
          return;
        if (paramException.getCause() != null)
          str = new StringBuilder().append(str).append(z[12]).append(paramException.getCause()).toString();
      }
      System.err.println(str);
      this.i += 1;
    }
  }

  void a(String paramString)
  {
    a(paramString, null);
  }

  void b(String paramString)
  {
    System.err.println(new StringBuilder().append(z[11]).append(paramString).toString());
    this.j += 1;
  }

  void c(String paramString)
  {
    System.out.println(new StringBuilder().append(z[10]).append(paramString).toString());
  }

  void d(String paramString)
  {
    System.out.println(new StringBuilder().append(z[9]).append(paramString).toString());
  }

  void e(String paramString)
  {
    System.out.println(new StringBuilder().append(z[9]).append(paramString).toString());
  }

  void a(int paramInt1, int paramInt2, int paramInt3)
  {
    this.k += paramInt1;
    this.j += paramInt2;
    this.i += paramInt3;
  }

  boolean f()
  {
    return this.g;
  }

  boolean g()
  {
    return this.h;
  }

  boolean h()
  {
    return true;
  }

  Connection b(boolean paramBoolean)
    throws Exception
  {
    int i1 = SysEnv.m;
    try
    {
      if ((paramBoolean) && ((i1 != 0) || (l != null)) && (l.isClosed()))
        l = null;
    }
    catch (SQLException localSQLException)
    {
    }
    if (i1 == 0)
      if (paramBoolean)
      {
        if (i1 != 0)
          break label82;
        if (l != null)
          break label79;
      }
    Connection localConnection = DB.createConnection(false, 2);
    if (i1 == 0)
    {
      if (paramBoolean)
        l = localConnection;
    }
    else
      if (i1 == 0)
        break label79;
    return localConnection;
    label79: label82: return l;
  }

  // ERROR //
  int a(String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: getstatic 423	com/compiere/client/SysEnv:m	I
    //   3: istore 7
    //   5: iconst_m1
    //   6: istore_3
    //   7: iconst_0
    //   8: istore 4
    //   10: iload_2
    //   11: iload 7
    //   13: ifne +37 -> 50
    //   16: ifeq +30 -> 46
    //   19: aload_0
    //   20: invokevirtual 105	com/compiere/client/d:h	()Z
    //   23: iload 7
    //   25: ifne +25 -> 50
    //   28: ifne +18 -> 46
    //   31: aload_0
    //   32: getstatic 444	com/compiere/client/d:z	[Ljava/lang/String;
    //   35: bipush 20
    //   37: aaload
    //   38: invokevirtual 12	com/compiere/client/d:c	(Ljava/lang/String;)V
    //   41: iload 7
    //   43: ifeq +412 -> 455
    //   46: aload_0
    //   47: getfield 6	com/compiere/client/d:g	Z
    //   50: iload 7
    //   52: ifne +405 -> 457
    //   55: ifne +400 -> 455
    //   58: aconst_null
    //   59: astore 5
    //   61: aload_0
    //   62: iconst_1
    //   63: invokevirtual 107	com/compiere/client/d:b	(Z)Ljava/sql/Connection;
    //   66: pop
    //   67: getstatic 14	com/compiere/client/d:l	Ljava/sql/Connection;
    //   70: getstatic 444	com/compiere/client/d:z	[Ljava/lang/String;
    //   73: bipush 17
    //   75: aaload
    //   76: invokeinterface 109 2 0
    //   81: astore 6
    //   83: getstatic 14	com/compiere/client/d:l	Ljava/sql/Connection;
    //   86: invokeinterface 110 1 0
    //   91: astore 5
    //   93: aload 5
    //   95: aload_1
    //   96: invokeinterface 111 2 0
    //   101: istore_3
    //   102: aload 5
    //   104: invokeinterface 112 1 0
    //   109: aconst_null
    //   110: astore 5
    //   112: iload_3
    //   113: iconst_1
    //   114: iload 7
    //   116: ifne +68 -> 184
    //   119: if_icmple +58 -> 177
    //   122: getstatic 14	com/compiere/client/d:l	Ljava/sql/Connection;
    //   125: aload 6
    //   127: invokeinterface 113 2 0
    //   132: aload_0
    //   133: new 67	java/lang/StringBuilder
    //   136: dup
    //   137: invokespecial 68	java/lang/StringBuilder:<init>	()V
    //   140: getstatic 444	com/compiere/client/d:z	[Ljava/lang/String;
    //   143: bipush 19
    //   145: aaload
    //   146: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: iload_3
    //   150: invokevirtual 90	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   153: getstatic 444	com/compiere/client/d:z	[Ljava/lang/String;
    //   156: bipush 16
    //   158: aaload
    //   159: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: aload_1
    //   163: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   169: invokevirtual 116	com/compiere/client/d:a	(Ljava/lang/String;)V
    //   172: iload 7
    //   174: ifeq +115 -> 289
    //   177: iload_3
    //   178: iload 7
    //   180: ifne +17 -> 197
    //   183: iconst_1
    //   184: if_icmpeq +34 -> 218
    //   187: aload_1
    //   188: getstatic 444	com/compiere/client/d:z	[Ljava/lang/String;
    //   191: bipush 22
    //   193: aaload
    //   194: invokevirtual 118	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   197: iload 7
    //   199: ifne +16 -> 215
    //   202: ifne +16 -> 218
    //   205: aload_1
    //   206: getstatic 444	com/compiere/client/d:z	[Ljava/lang/String;
    //   209: bipush 23
    //   211: aaload
    //   212: invokevirtual 118	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   215: ifeq +26 -> 241
    //   218: getstatic 14	com/compiere/client/d:l	Ljava/sql/Connection;
    //   221: invokeinterface 120 1 0
    //   226: aload_0
    //   227: dup
    //   228: getfield 10	com/compiere/client/d:k	I
    //   231: iconst_1
    //   232: iadd
    //   233: putfield 10	com/compiere/client/d:k	I
    //   236: iload 7
    //   238: ifeq +51 -> 289
    //   241: getstatic 14	com/compiere/client/d:l	Ljava/sql/Connection;
    //   244: invokeinterface 120 1 0
    //   249: aload_0
    //   250: new 67	java/lang/StringBuilder
    //   253: dup
    //   254: invokespecial 68	java/lang/StringBuilder:<init>	()V
    //   257: getstatic 444	com/compiere/client/d:z	[Ljava/lang/String;
    //   260: bipush 18
    //   262: aaload
    //   263: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   266: iload_3
    //   267: invokevirtual 90	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   270: getstatic 444	com/compiere/client/d:z	[Ljava/lang/String;
    //   273: bipush 21
    //   275: aaload
    //   276: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   279: aload_1
    //   280: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   283: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   286: invokevirtual 123	com/compiere/client/d:b	(Ljava/lang/String;)V
    //   289: goto +144 -> 433
    //   292: astore 6
    //   294: aload 6
    //   296: invokevirtual 124	java/sql/SQLException:getErrorCode	()I
    //   299: iload 7
    //   301: ifne +87 -> 388
    //   304: sipush 2292
    //   307: if_icmpne +45 -> 352
    //   310: iconst_0
    //   311: istore_3
    //   312: aload_0
    //   313: new 67	java/lang/StringBuilder
    //   316: dup
    //   317: invokespecial 68	java/lang/StringBuilder:<init>	()V
    //   320: aload 6
    //   322: invokevirtual 125	java/sql/SQLException:getMessage	()Ljava/lang/String;
    //   325: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   328: getstatic 444	com/compiere/client/d:z	[Ljava/lang/String;
    //   331: bipush 13
    //   333: aaload
    //   334: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   337: aload_1
    //   338: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   341: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   344: invokevirtual 123	com/compiere/client/d:b	(Ljava/lang/String;)V
    //   347: iload 7
    //   349: ifeq +41 -> 390
    //   352: aload_0
    //   353: new 67	java/lang/StringBuilder
    //   356: dup
    //   357: invokespecial 68	java/lang/StringBuilder:<init>	()V
    //   360: aload 6
    //   362: invokevirtual 125	java/sql/SQLException:getMessage	()Ljava/lang/String;
    //   365: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   368: getstatic 444	com/compiere/client/d:z	[Ljava/lang/String;
    //   371: bipush 13
    //   373: aaload
    //   374: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   377: aload_1
    //   378: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   381: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   384: invokevirtual 116	com/compiere/client/d:a	(Ljava/lang/String;)V
    //   387: iconst_1
    //   388: istore 4
    //   390: goto +43 -> 433
    //   393: astore 6
    //   395: aload_0
    //   396: new 67	java/lang/StringBuilder
    //   399: dup
    //   400: invokespecial 68	java/lang/StringBuilder:<init>	()V
    //   403: aload 6
    //   405: invokevirtual 126	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   408: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   411: getstatic 444	com/compiere/client/d:z	[Ljava/lang/String;
    //   414: bipush 13
    //   416: aaload
    //   417: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   420: aload_1
    //   421: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   424: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   427: invokevirtual 116	com/compiere/client/d:a	(Ljava/lang/String;)V
    //   430: iconst_1
    //   431: istore 4
    //   433: aload 5
    //   435: iload 7
    //   437: ifne +8 -> 445
    //   440: ifnull +10 -> 450
    //   443: aload 5
    //   445: invokeinterface 112 1 0
    //   450: goto +5 -> 455
    //   453: astore 6
    //   455: iload 4
    //   457: iload 7
    //   459: ifne +12 -> 471
    //   462: ifne +8 -> 470
    //   465: aload_0
    //   466: aload_1
    //   467: invokevirtual 127	com/compiere/client/d:e	(Ljava/lang/String;)V
    //   470: iload_3
    //   471: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   61	289	292	java/sql/SQLException
    //   61	289	393	java/lang/Exception
    //   433	450	453	java/lang/Exception
  }

  static
  {
    // Byte code:
    //   0: bipush 40
    //   2: anewarray 128	java/lang/String
    //   5: dup
    //   6: iconst_0
    //   7: ldc 21
    //   9: bipush 39
    //   11: goto +438 -> 449
    //   14: putstatic 426	com/compiere/client/d:b	Ljava/lang/String;
    //   17: ldc 79
    //   19: bipush 255
    //   21: goto +428 -> 449
    //   24: aastore
    //   25: dup
    //   26: iconst_1
    //   27: ldc 78
    //   29: iconst_0
    //   30: goto +419 -> 449
    //   33: aastore
    //   34: dup
    //   35: iconst_2
    //   36: ldc 89
    //   38: iconst_1
    //   39: goto +410 -> 449
    //   42: aastore
    //   43: dup
    //   44: iconst_3
    //   45: ldc 80
    //   47: iconst_2
    //   48: goto +401 -> 449
    //   51: aastore
    //   52: dup
    //   53: iconst_4
    //   54: ldc 91
    //   56: iconst_3
    //   57: goto +392 -> 449
    //   60: aastore
    //   61: dup
    //   62: iconst_5
    //   63: ldc 87
    //   65: iconst_4
    //   66: goto +383 -> 449
    //   69: aastore
    //   70: dup
    //   71: bipush 6
    //   73: ldc 75
    //   75: iconst_5
    //   76: goto +373 -> 449
    //   79: aastore
    //   80: dup
    //   81: bipush 7
    //   83: ldc 93
    //   85: bipush 6
    //   87: goto +362 -> 449
    //   90: aastore
    //   91: dup
    //   92: bipush 8
    //   94: ldc 92
    //   96: bipush 7
    //   98: goto +351 -> 449
    //   101: aastore
    //   102: dup
    //   103: bipush 9
    //   105: ldc 102
    //   107: bipush 8
    //   109: goto +340 -> 449
    //   112: aastore
    //   113: dup
    //   114: bipush 10
    //   116: ldc 101
    //   118: bipush 9
    //   120: goto +329 -> 449
    //   123: aastore
    //   124: dup
    //   125: bipush 11
    //   127: ldc 100
    //   129: bipush 10
    //   131: goto +318 -> 449
    //   134: aastore
    //   135: dup
    //   136: bipush 12
    //   138: ldc 98
    //   140: bipush 11
    //   142: goto +307 -> 449
    //   145: aastore
    //   146: dup
    //   147: bipush 13
    //   149: ldc 95
    //   151: bipush 12
    //   153: goto +296 -> 449
    //   156: aastore
    //   157: dup
    //   158: bipush 14
    //   160: ldc 94
    //   162: bipush 13
    //   164: goto +285 -> 449
    //   167: aastore
    //   168: dup
    //   169: bipush 15
    //   171: ldc 11
    //   173: bipush 14
    //   175: goto +274 -> 449
    //   178: aastore
    //   179: dup
    //   180: bipush 16
    //   182: ldc 115
    //   184: bipush 15
    //   186: goto +263 -> 449
    //   189: aastore
    //   190: dup
    //   191: bipush 17
    //   193: ldc 108
    //   195: bipush 16
    //   197: goto +252 -> 449
    //   200: aastore
    //   201: dup
    //   202: bipush 18
    //   204: ldc 121
    //   206: bipush 17
    //   208: goto +241 -> 449
    //   211: aastore
    //   212: dup
    //   213: bipush 19
    //   215: ldc 114
    //   217: bipush 18
    //   219: goto +230 -> 449
    //   222: aastore
    //   223: dup
    //   224: bipush 20
    //   226: ldc 106
    //   228: bipush 19
    //   230: goto +219 -> 449
    //   233: aastore
    //   234: dup
    //   235: bipush 21
    //   237: ldc 122
    //   239: bipush 20
    //   241: goto +208 -> 449
    //   244: aastore
    //   245: dup
    //   246: bipush 22
    //   248: ldc 117
    //   250: bipush 21
    //   252: goto +197 -> 449
    //   255: aastore
    //   256: dup
    //   257: bipush 23
    //   259: ldc 119
    //   261: bipush 22
    //   263: goto +186 -> 449
    //   266: aastore
    //   267: dup
    //   268: bipush 24
    //   270: ldc 62
    //   272: bipush 23
    //   274: goto +175 -> 449
    //   277: aastore
    //   278: dup
    //   279: bipush 25
    //   281: ldc 25
    //   283: bipush 24
    //   285: goto +164 -> 449
    //   288: aastore
    //   289: dup
    //   290: bipush 26
    //   292: ldc 41
    //   294: bipush 25
    //   296: goto +153 -> 449
    //   299: aastore
    //   300: dup
    //   301: bipush 27
    //   303: ldc 32
    //   305: bipush 26
    //   307: goto +142 -> 449
    //   310: aastore
    //   311: dup
    //   312: bipush 28
    //   314: ldc 20
    //   316: bipush 27
    //   318: goto +131 -> 449
    //   321: aastore
    //   322: dup
    //   323: bipush 29
    //   325: ldc 34
    //   327: bipush 28
    //   329: goto +120 -> 449
    //   332: aastore
    //   333: dup
    //   334: bipush 30
    //   336: ldc 37
    //   338: bipush 29
    //   340: goto +109 -> 449
    //   343: aastore
    //   344: dup
    //   345: bipush 31
    //   347: ldc 29
    //   349: bipush 30
    //   351: goto +98 -> 449
    //   354: aastore
    //   355: dup
    //   356: bipush 32
    //   358: ldc 61
    //   360: bipush 31
    //   362: goto +87 -> 449
    //   365: aastore
    //   366: dup
    //   367: bipush 33
    //   369: ldc 28
    //   371: bipush 32
    //   373: goto +76 -> 449
    //   376: aastore
    //   377: dup
    //   378: bipush 34
    //   380: ldc 65
    //   382: bipush 33
    //   384: goto +65 -> 449
    //   387: aastore
    //   388: dup
    //   389: bipush 35
    //   391: ldc 39
    //   393: bipush 34
    //   395: goto +54 -> 449
    //   398: aastore
    //   399: dup
    //   400: bipush 36
    //   402: ldc 23
    //   404: bipush 35
    //   406: goto +43 -> 449
    //   409: aastore
    //   410: dup
    //   411: bipush 37
    //   413: ldc 71
    //   415: bipush 36
    //   417: goto +32 -> 449
    //   420: aastore
    //   421: dup
    //   422: bipush 38
    //   424: ldc 72
    //   426: bipush 37
    //   428: goto +21 -> 449
    //   431: aastore
    //   432: dup
    //   433: bipush 39
    //   435: ldc 69
    //   437: bipush 38
    //   439: goto +10 -> 449
    //   442: aastore
    //   443: putstatic 444	com/compiere/client/d:z	[Ljava/lang/String;
    //   446: goto +230 -> 676
    //   449: swap
    //   450: invokestatic 439	com/compiere/client/d:z	(Ljava/lang/String;)[C
    //   453: invokestatic 442	com/compiere/client/d:z	([C)Ljava/lang/String;
    //   456: swap
    //   457: tableswitch	default:+-433 -> 24, 0:+-424->33, 1:+-415->42, 2:+-406->51, 3:+-397->60, 4:+-388->69, 5:+-378->79, 6:+-367->90, 7:+-356->101, 8:+-345->112, 9:+-334->123, 10:+-323->134, 11:+-312->145, 12:+-301->156, 13:+-290->167, 14:+-279->178, 15:+-268->189, 16:+-257->200, 17:+-246->211, 18:+-235->222, 19:+-224->233, 20:+-213->244, 21:+-202->255, 22:+-191->266, 23:+-180->277, 24:+-169->288, 25:+-158->299, 26:+-147->310, 27:+-136->321, 28:+-125->332, 29:+-114->343, 30:+-103->354, 31:+-92->365, 32:+-81->376, 33:+-70->387, 34:+-59->398, 35:+-48->409, 36:+-37->420, 37:+-26->431, 38:+-15->442, 39:+-443->14, 40:+241->698, 41:+251->708, 42:+261->718, 43:+271->728, 44:+281->738, 45:+291->748, 46:+302->759, 47:+313->770, 48:+324->781, 49:+335->792, 50:+346->803
    //   677: putstatic 14	com/compiere/client/d:l	Ljava/sql/Connection;
    //   680: aconst_null
    //   681: putstatic 19	com/compiere/client/d:m	Ljava/lang/Class;
    //   684: bipush 11
    //   686: anewarray 128	java/lang/String
    //   689: dup
    //   690: iconst_0
    //   691: ldc 129
    //   693: bipush 40
    //   695: goto -246 -> 449
    //   698: aastore
    //   699: dup
    //   700: iconst_1
    //   701: ldc 130
    //   703: bipush 41
    //   705: goto -256 -> 449
    //   708: aastore
    //   709: dup
    //   710: iconst_2
    //   711: ldc 131
    //   713: bipush 42
    //   715: goto -266 -> 449
    //   718: aastore
    //   719: dup
    //   720: iconst_3
    //   721: ldc 132
    //   723: bipush 43
    //   725: goto -276 -> 449
    //   728: aastore
    //   729: dup
    //   730: iconst_4
    //   731: ldc 133
    //   733: bipush 44
    //   735: goto -286 -> 449
    //   738: aastore
    //   739: dup
    //   740: iconst_5
    //   741: ldc 134
    //   743: bipush 45
    //   745: goto -296 -> 449
    //   748: aastore
    //   749: dup
    //   750: bipush 6
    //   752: ldc 135
    //   754: bipush 46
    //   756: goto -307 -> 449
    //   759: aastore
    //   760: dup
    //   761: bipush 7
    //   763: ldc 136
    //   765: bipush 47
    //   767: goto -318 -> 449
    //   770: aastore
    //   771: dup
    //   772: bipush 8
    //   774: ldc 137
    //   776: bipush 48
    //   778: goto -329 -> 449
    //   781: aastore
    //   782: dup
    //   783: bipush 9
    //   785: ldc 138
    //   787: bipush 49
    //   789: goto -340 -> 449
    //   792: aastore
    //   793: dup
    //   794: bipush 10
    //   796: ldc 139
    //   798: bipush 50
    //   800: goto -351 -> 449
    //   803: aastore
    //   804: putstatic 66	com/compiere/client/d:n	[Ljava/lang/String;
    //   807: return
  }

  private static char[] z(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 430	java/lang/String:toCharArray	()[C
    //   4: dup
    //   5: arraylength
    //   6: dup_x1
    //   7: iconst_2
    //   8: if_icmpge +17 -> 25
    //   11: dup_x1
    //   12: swap
    //   13: ifeq -8 -> 5
    //   16: iconst_0
    //   17: dup_x2
    //   18: dup2
    //   19: caload
    //   20: bipush 122
    //   22: ixor
    //   23: i2c
    //   24: castore
    //   25: areturn
  }

  private static String z(char[] paramArrayOfChar)
  {
    int i1 = 0;
    if (paramArrayOfChar.length <= 1);
    do
    {
      int tmp14_13 = i1;
      switch (i1 % 5)
      {
      case 0:
        tmpTernaryOp = 29;
        break;
      case 1:
        tmpTernaryOp = 81;
        break;
      case 2:
        tmpTernaryOp = 90;
        break;
      case 3:
        tmpTernaryOp = 24;
        break;
      }
      paramArrayOfChar[tmp14_13] = (char)(paramArrayOfChar[tmp14_13] ^ 0x7A);
      i1++;
    }
    while (paramArrayOfChar > i1);
    return new String(paramArrayOfChar).intern();
  }
}