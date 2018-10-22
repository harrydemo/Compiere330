package com.compiere.client;

import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import org.compiere.util.CLogger;

final class c
{
  private static final String a;
  private static CLogger b;
  private InputStreamReader c = null;
  private static final String[] z;

  c(CLogger paramCLogger)
  {
    b = paramCLogger;
  }

  boolean a(String paramString1, String paramString2, String paramString3, int paramInt1, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, int paramInt2, String paramString10, String paramString11, String paramString12)
  {
    int j = SysEnv.m;
    URL localURL = null;
    try
    {
      String str1 = System.getProperty(z[22], z[9]);
      String str2 = z[0].substring(8);
      String str3 = z[2];
      String str4 = InetAddress.getLocalHost().toString();
      int i = Runtime.getRuntime().availableProcessors();
      String str5 = i + z[12] + System.getenv(z[19]);
      String str6 = System.getProperty(z[16]) + " " + System.getProperty(z[5]) + " " + System.getProperty(z[21]) + " " + System.getProperty(z[18]);
      StringBuffer localStringBuffer = new StringBuffer(z[17]).append(str1).append(z[34]);
      if (j == 0)
      {
        if (paramString1 != null)
          localStringBuffer.append(z[13]).append(URLEncoder.encode(paramString1, a));
      }
      else
        if (j == 0)
          break label249;
      localStringBuffer.append(z[8]);
      label249: if (j == 0)
      {
        if (paramString2 != null)
        {
          localStringBuffer.append(z[14]).append(URLEncoder.encode(paramString2, a));
          if (j != 0)
            break label339;
        }
      }
      else if (paramString3 != null)
        localStringBuffer.append(z[10]).append(URLEncoder.encode(paramString3, a));
      if (j == 0)
      {
        localStringBuffer.append(z[7]).append(URLEncoder.encode(str4, a));
        label339: if (paramInt1 < 0);
      }
      else
      {
        localStringBuffer.append(z[28]).append(paramInt1);
      }
      if (j == 0)
        if (paramString4 != null)
          localStringBuffer.append(z[33]).append(URLEncoder.encode(paramString4, a));
      if (j == 0)
      {
        if (paramString5 != null)
          localStringBuffer.append(z[31]).append(URLEncoder.encode(paramString5, a));
        localStringBuffer.append(z[15]).append(URLEncoder.encode(paramString6, a)).append(z[6]).append(URLEncoder.encode(paramString7, a));
      }
      if (j == 0)
        if (paramString8 != null)
          localStringBuffer.append(z[37]).append(URLEncoder.encode(paramString8, a));
      if (paramString9 != null)
        localStringBuffer.append(z[35]).append(URLEncoder.encode(paramString9, a));
      if (paramInt2 >= 0)
        localStringBuffer.append(z[29]).append(paramInt2);
      localStringBuffer.append(z[20]).append(URLEncoder.encode(str2, a)).append(z[11]).append(URLEncoder.encode(str3, a));
      if (j == 0)
        if (paramString10 != null)
          localStringBuffer.append(z[30]).append(URLEncoder.encode(paramString10, a));
      if (j == 0)
        if (paramString11 != null)
          localStringBuffer.append(z[36]).append(URLEncoder.encode(paramString11, a));
      if (paramString12 != null)
        localStringBuffer.append(z[1]).append(URLEncoder.encode(paramString12, a));
      localURL = new URL(localStringBuffer.toString());
    }
    catch (Exception localException1)
    {
      if ((j != 0) || (b != null))
        b.log(Level.SEVERE, z[27], localException1);
      return false;
    }
    try
    {
      URLConnection localURLConnection = localURL.openConnection();
      this.c = new InputStreamReader(localURLConnection.getInputStream());
    }
    catch (Exception localException2)
    {
      if ((j != 0) || (b != null))
        b.log(Level.WARNING, z[32], localException2);
      return false;
    }
    return true;
  }

  boolean a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    int j = SysEnv.m;
    URL localURL = null;
    try
    {
      String str1 = System.getProperty(z[22], z[9]);
      String str2 = z[0].substring(8);
      String str3 = z[2];
      String str4 = InetAddress.getLocalHost().toString();
      int i = Runtime.getRuntime().availableProcessors();
      String str5 = i + z[12] + System.getenv(z[19]);
      String str6 = System.getProperty(z[16]) + " " + System.getProperty(z[5]) + " " + System.getProperty(z[21]) + " " + System.getProperty(z[18]);
      StringBuffer localStringBuffer = new StringBuffer(z[17]).append(str1).append(z[4]);
      if (j == 0)
      {
        if (paramString1 != null)
          localStringBuffer.append(z[13]).append(URLEncoder.encode(paramString1, a));
      }
      else
        if (j == 0)
          break label248;
      localStringBuffer.append(z[8]);
      label248: if (j == 0)
      {
        if (paramString2 != null)
        {
          localStringBuffer.append(z[14]).append(URLEncoder.encode(paramString2, a));
          if (j != 0)
            break label460;
        }
      }
      else if (paramString3 != null)
        localStringBuffer.append(z[10]).append(URLEncoder.encode(paramString3, a));
      localStringBuffer.append(z[7]).append(URLEncoder.encode(str4, a));
      localStringBuffer.append(z[15]).append(URLEncoder.encode(paramString6, a)).append(z[6]).append(URLEncoder.encode(paramString7, a));
      localStringBuffer.append(z[20]).append(URLEncoder.encode(str2, a)).append(z[11]).append(URLEncoder.encode(str3, a));
      localStringBuffer.append(z[1]).append(URLEncoder.encode(paramString4, a)).append(z[3]).append(URLEncoder.encode(paramString5, a));
      label460: localURL = new URL(localStringBuffer.toString());
    }
    catch (Exception localException1)
    {
      return false;
    }
    try
    {
      URLConnection localURLConnection = localURL.openConnection();
      this.c = new InputStreamReader(localURLConnection.getInputStream());
    }
    catch (Exception localException2)
    {
      return false;
    }
    return true;
  }

  boolean b(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    int j = SysEnv.m;
    URL localURL = null;
    try
    {
      String str1 = System.getProperty(z[22], z[9]);
      String str2 = z[0].substring(8);
      String str3 = z[2];
      String str4 = InetAddress.getLocalHost().toString();
      int i = Runtime.getRuntime().availableProcessors();
      String str5 = i + z[12] + System.getenv(z[19]);
      String str6 = System.getProperty(z[16]) + " " + System.getProperty(z[5]) + " " + System.getProperty(z[21]) + " " + System.getProperty(z[18]);
      StringBuffer localStringBuffer = new StringBuffer(z[17]).append(str1).append(z[38]);
      if (j == 0)
      {
        if (paramString1 != null)
          localStringBuffer.append(z[13]).append(URLEncoder.encode(paramString1, a));
      }
      else
        if (j == 0)
          break label249;
      localStringBuffer.append(z[8]);
      label249: if (j == 0)
      {
        if (paramString2 != null)
        {
          localStringBuffer.append(z[14]).append(URLEncoder.encode(paramString2, a));
          if (j != 0)
            break label461;
        }
      }
      else if (paramString3 != null)
        localStringBuffer.append(z[10]).append(URLEncoder.encode(paramString3, a));
      localStringBuffer.append(z[7]).append(URLEncoder.encode(str4, a));
      localStringBuffer.append(z[15]).append(URLEncoder.encode(paramString6, a)).append(z[6]).append(URLEncoder.encode(paramString7, a));
      localStringBuffer.append(z[20]).append(URLEncoder.encode(str2, a)).append(z[11]).append(URLEncoder.encode(str3, a));
      localStringBuffer.append(z[1]).append(URLEncoder.encode(paramString4, a)).append(z[3]).append(URLEncoder.encode(paramString5, a));
      label461: localURL = new URL(localStringBuffer.toString());
    }
    catch (Exception localException1)
    {
      return false;
    }
    try
    {
      URLConnection localURLConnection = localURL.openConnection();
      this.c = new InputStreamReader(localURLConnection.getInputStream());
    }
    catch (Exception localException2)
    {
      return false;
    }
    return true;
  }

  String a()
  {
    int j = SysEnv.m;
    if (this.c == null)
      return z[25];
    StringBuffer localStringBuffer = new StringBuffer();
    try
    {
      while (true)
      {
        int i;
        if ((i = this.c.read()) != -1)
        {
          localStringBuffer.append((char)i);
          if (j != 0)
            break;
          if (j == 0)
            continue;
        }
        else
        {
          this.c.close();
        }
      }
      if ((j != 0) || (b != null))
        b.fine("(" + localStringBuffer.length() + z[23] + localStringBuffer);
    }
    catch (Exception localException)
    {
      if ((j != 0) || (b != null))
        b.log(Level.WARNING, z[26], localException);
      return z[24];
    }
    return localStringBuffer.toString();
  }

  static
  {
    // Byte code:
    //   0: bipush 39
    //   2: anewarray 98	java/lang/String
    //   5: dup
    //   6: iconst_0
    //   7: ldc 34
    //   9: bipush 38
    //   11: goto +427 -> 438
    //   14: putstatic 246	com/compiere/client/c:a	Ljava/lang/String;
    //   17: ldc 7
    //   19: bipush 255
    //   21: goto +417 -> 438
    //   24: aastore
    //   25: dup
    //   26: iconst_1
    //   27: ldc 53
    //   29: iconst_0
    //   30: goto +408 -> 438
    //   33: aastore
    //   34: dup
    //   35: iconst_2
    //   36: ldc 9
    //   38: iconst_1
    //   39: goto +399 -> 438
    //   42: aastore
    //   43: dup
    //   44: iconst_3
    //   45: ldc 68
    //   47: iconst_2
    //   48: goto +390 -> 438
    //   51: aastore
    //   52: dup
    //   53: iconst_4
    //   54: ldc 67
    //   56: iconst_3
    //   57: goto +381 -> 438
    //   60: aastore
    //   61: dup
    //   62: iconst_5
    //   63: ldc 25
    //   65: iconst_4
    //   66: goto +372 -> 438
    //   69: aastore
    //   70: dup
    //   71: bipush 6
    //   73: ldc 45
    //   75: iconst_5
    //   76: goto +362 -> 438
    //   79: aastore
    //   80: dup
    //   81: bipush 7
    //   83: ldc 39
    //   85: bipush 6
    //   87: goto +351 -> 438
    //   90: aastore
    //   91: dup
    //   92: bipush 8
    //   94: ldc 36
    //   96: bipush 7
    //   98: goto +340 -> 438
    //   101: aastore
    //   102: dup
    //   103: bipush 9
    //   105: ldc 5
    //   107: bipush 8
    //   109: goto +329 -> 438
    //   112: aastore
    //   113: dup
    //   114: bipush 10
    //   116: ldc 38
    //   118: bipush 9
    //   120: goto +318 -> 438
    //   123: aastore
    //   124: dup
    //   125: bipush 11
    //   127: ldc 50
    //   129: bipush 10
    //   131: goto +307 -> 438
    //   134: aastore
    //   135: dup
    //   136: bipush 12
    //   138: ldc 17
    //   140: bipush 11
    //   142: goto +296 -> 438
    //   145: aastore
    //   146: dup
    //   147: bipush 13
    //   149: ldc 33
    //   151: bipush 12
    //   153: goto +285 -> 438
    //   156: aastore
    //   157: dup
    //   158: bipush 14
    //   160: ldc 37
    //   162: bipush 13
    //   164: goto +274 -> 438
    //   167: aastore
    //   168: dup
    //   169: bipush 15
    //   171: ldc 44
    //   173: bipush 14
    //   175: goto +263 -> 438
    //   178: aastore
    //   179: dup
    //   180: bipush 16
    //   182: ldc 22
    //   184: bipush 15
    //   186: goto +252 -> 438
    //   189: aastore
    //   190: dup
    //   191: bipush 17
    //   193: ldc 29
    //   195: bipush 16
    //   197: goto +241 -> 438
    //   200: aastore
    //   201: dup
    //   202: bipush 18
    //   204: ldc 27
    //   206: bipush 17
    //   208: goto +230 -> 438
    //   211: aastore
    //   212: dup
    //   213: bipush 19
    //   215: ldc 19
    //   217: bipush 18
    //   219: goto +219 -> 438
    //   222: aastore
    //   223: dup
    //   224: bipush 20
    //   226: ldc 49
    //   228: bipush 19
    //   230: goto +208 -> 438
    //   233: aastore
    //   234: dup
    //   235: bipush 21
    //   237: ldc 26
    //   239: bipush 20
    //   241: goto +197 -> 438
    //   244: aastore
    //   245: dup
    //   246: bipush 22
    //   248: ldc 4
    //   250: bipush 21
    //   252: goto +186 -> 438
    //   255: aastore
    //   256: dup
    //   257: bipush 23
    //   259: ldc 77
    //   261: bipush 22
    //   263: goto +175 -> 438
    //   266: aastore
    //   267: dup
    //   268: bipush 24
    //   270: ldc 81
    //   272: bipush 23
    //   274: goto +164 -> 438
    //   277: aastore
    //   278: dup
    //   279: bipush 25
    //   281: ldc 70
    //   283: bipush 24
    //   285: goto +153 -> 438
    //   288: aastore
    //   289: dup
    //   290: bipush 26
    //   292: ldc 80
    //   294: bipush 25
    //   296: goto +142 -> 438
    //   299: aastore
    //   300: dup
    //   301: bipush 27
    //   303: ldc 59
    //   305: bipush 26
    //   307: goto +131 -> 438
    //   310: aastore
    //   311: dup
    //   312: bipush 28
    //   314: ldc 40
    //   316: bipush 27
    //   318: goto +120 -> 438
    //   321: aastore
    //   322: dup
    //   323: bipush 29
    //   325: ldc 48
    //   327: bipush 28
    //   329: goto +109 -> 438
    //   332: aastore
    //   333: dup
    //   334: bipush 30
    //   336: ldc 51
    //   338: bipush 29
    //   340: goto +98 -> 438
    //   343: aastore
    //   344: dup
    //   345: bipush 31
    //   347: ldc 43
    //   349: bipush 30
    //   351: goto +87 -> 438
    //   354: aastore
    //   355: dup
    //   356: bipush 32
    //   358: ldc 66
    //   360: bipush 31
    //   362: goto +76 -> 438
    //   365: aastore
    //   366: dup
    //   367: bipush 33
    //   369: ldc 42
    //   371: bipush 32
    //   373: goto +65 -> 438
    //   376: aastore
    //   377: dup
    //   378: bipush 34
    //   380: ldc 32
    //   382: bipush 33
    //   384: goto +54 -> 438
    //   387: aastore
    //   388: dup
    //   389: bipush 35
    //   391: ldc 47
    //   393: bipush 34
    //   395: goto +43 -> 438
    //   398: aastore
    //   399: dup
    //   400: bipush 36
    //   402: ldc 52
    //   404: bipush 35
    //   406: goto +32 -> 438
    //   409: aastore
    //   410: dup
    //   411: bipush 37
    //   413: ldc 46
    //   415: bipush 36
    //   417: goto +21 -> 438
    //   420: aastore
    //   421: dup
    //   422: bipush 38
    //   424: ldc 69
    //   426: bipush 37
    //   428: goto +10 -> 438
    //   431: aastore
    //   432: putstatic 258	com/compiere/client/c:z	[Ljava/lang/String;
    //   435: goto +285 -> 720
    //   438: swap
    //   439: invokevirtual 250	java/lang/String:toCharArray	()[C
    //   442: dup
    //   443: arraylength
    //   444: swap
    //   445: iconst_0
    //   446: istore_0
    //   447: swap
    //   448: dup_x1
    //   449: iconst_1
    //   450: if_icmpgt +80 -> 530
    //   453: dup
    //   454: iload_0
    //   455: dup2
    //   456: caload
    //   457: iload_0
    //   458: iconst_5
    //   459: irem
    //   460: tableswitch	default:+52 -> 512, 0:+32->492, 1:+37->497, 2:+42->502, 3:+47->507
    //   493: ldc 167
    //   495: nop
    //   496: ldc2_w 4204
    //   499: goto +15 -> 514
    //   502: bipush 98
    //   504: goto +10 -> 514
    //   507: bipush 111
    //   509: goto +5 -> 514
    //   512: bipush 32
    //   514: ixor
    //   515: i2c
    //   516: castore
    //   517: iinc 0 1
    //   520: swap
    //   521: dup_x1
    //   522: ifne +8 -> 530
    //   525: dup2
    //   526: swap
    //   527: goto -72 -> 455
    //   530: swap
    //   531: dup_x1
    //   532: iload_0
    //   533: if_icmpgt -80 -> 453
    //   536: new 98	java/lang/String
    //   539: dup_x1
    //   540: swap
    //   541: invokespecial 253	java/lang/String:<init>	([C)V
    //   544: invokevirtual 256	java/lang/String:intern	()Ljava/lang/String;
    //   547: swap
    //   548: pop
    //   549: swap
    //   550: tableswitch	default:+-526 -> 24, 0:+-517->33, 1:+-508->42, 2:+-499->51, 3:+-490->60, 4:+-481->69, 5:+-471->79, 6:+-460->90, 7:+-449->101, 8:+-438->112, 9:+-427->123, 10:+-416->134, 11:+-405->145, 12:+-394->156, 13:+-383->167, 14:+-372->178, 15:+-361->189, 16:+-350->200, 17:+-339->211, 18:+-328->222, 19:+-317->233, 20:+-306->244, 21:+-295->255, 22:+-284->266, 23:+-273->277, 24:+-262->288, 25:+-251->299, 26:+-240->310, 27:+-229->321, 28:+-218->332, 29:+-207->343, 30:+-196->354, 31:+-185->365, 32:+-174->376, 33:+-163->387, 34:+-152->398, 35:+-141->409, 36:+-130->420, 37:+-119->431, 38:+-536->14
    //   721: putstatic 3	com/compiere/client/c:b	Lorg/compiere/util/CLogger;
    //   724: return
  }
}