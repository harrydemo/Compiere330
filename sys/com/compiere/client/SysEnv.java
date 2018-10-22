package com.compiere.client;

import com.qoppa.pdfViewer.PDFViewerBean;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;
import org.compiere.api.LicenseInterface;
import org.compiere.model.MEntityType;
import org.compiere.model.MSystem;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util;

public final class SysEnv
  implements LicenseInterface
{
  private static ArrayList a;
  protected static CLogger b;
  private MEntityType c = null;
  private e d = null;
  private boolean e = false;
  private boolean f = false;
  private boolean g = false;
  private boolean h = false;
  private long i = 0L;
  private long j = 0L;
  private String k = "";
  private String l = "";
  public static int m;
  private static final String[] z;

  public static SysEnv get(String paramString)
  {
    return get(paramString, true);
  }

  public static SysEnv get(String paramString, boolean paramBoolean)
  {
    if (a == null)
      a();
    for (int n = 0; n < a.size(); n++)
    {
      SysEnv localSysEnv = (SysEnv)a.get(n);
      if (localSysEnv.a(paramString))
        return localSysEnv;
    }
    if (paramBoolean)
    {
      String str = Msg.getMsg(Env.getAD_Language(Env.getCtx()), z[9], new Object[] { paramString });
      b.saveError(z[8], str);
    }
    return null;
  }

  static synchronized void a()
  {
    ArrayList localArrayList = new ArrayList();
    SysEnv localSysEnv1 = new SysEnv(null);
    localArrayList.add(localSysEnv1);
    MEntityType[] arrayOfMEntityType = MEntityType.getEntityTypes(Env.getCtx(), true);
    for (int n = 0; n < arrayOfMEntityType.length; n++)
    {
      MEntityType localMEntityType = arrayOfMEntityType[n];
      SysEnv localSysEnv2 = new SysEnv(localMEntityType);
      localArrayList.add(localSysEnv2);
    }
    a = localArrayList;
  }

  public static String getInfo(String paramString)
  {
    int i1 = m;
    StringBuffer localStringBuffer = new StringBuffer();
    if (paramString == null)
    {
      localObject1 = get(null);
      localStringBuffer.append(((SysEnv)localObject1).a(z[2], z[11], null, null, z[10]));
    }
    Object localObject1 = MEntityType.getEntityTypes(Env.getCtx(), true);
    int n = 0;
    do
    {
      if (n >= localObject1.length)
        break;
      Object localObject2 = localObject1[n];
      if ((i1 != 0) || (localObject2.isLicensable()))
      {
        String str = localObject2.getEntityType();
        if ((i1 != 0) || ((paramString == null) || ((i1 != 0) || (paramString.equals(str)))))
        {
          SysEnv localSysEnv = get(str);
          if (i1 == 0)
            if (localStringBuffer.length() > 0)
              localStringBuffer.append(Env.NL);
          localStringBuffer.append(localSysEnv.a(localObject2.getName(), z[11], null, null, null));
        }
      }
      n++;
    }
    while (i1 == 0);
    return (String)localStringBuffer.toString();
  }

  public static String getLicenseText(String paramString)
  {
    int i1 = m;
    if ((i1 != 0) || (paramString == null))
      paramString = "D";
    MEntityType[] arrayOfMEntityType = MEntityType.getEntityTypes(Env.getCtx(), true);
    int n = 0;
    do
    {
      if (n >= arrayOfMEntityType.length)
        break;
      MEntityType localMEntityType = arrayOfMEntityType[n];
      String str = localMEntityType.getEntityType();
      if (i1 != 0)
        continue;
      if (paramString.equals(str))
        return localMEntityType.getLicenseText();
      n++;
    }
    while (i1 == 0);
    return null;
  }

  private SysEnv(MEntityType paramMEntityType)
  {
    MSystem localMSystem = MSystem.get(Env.getCtx());
    int n = localMSystem.getRecord_ID();
    if (paramMEntityType != null)
    {
      this.c = paramMEntityType;
      this.d = new e(paramMEntityType.getSummary());
    }
    else
    {
      this.c = null;
      this.d = new e(localMSystem.getSummary());
    }
    a(localMSystem, n);
    if ((this.f) && (paramMEntityType == null))
    {
      String str = this.d.k();
      GregorianCalendar localGregorianCalendar = new GregorianCalendar();
      long l1 = localGregorianCalendar.get(1) * 1000L + localGregorianCalendar.get(6);
      Random localRandom = new Random(l1);
      for (int i1 = 0; i1 < 10; i1++)
        localRandom.nextInt();
      long l2 = Long.parseLong(str, 16) + localRandom.nextInt();
      PDFViewerBean.setKey(Long.toHexString(l2));
      boolean bool = PDFViewerBean.isLicensed();
    }
  }

  private void a(MSystem paramMSystem, int paramInt)
  {
    String str1 = this.c == null ? "" : this.c.getEntityType();
    String str2 = paramMSystem.getName();
    String str3 = Support.e();
    LicenseInterface localLicenseInterface = b();
    if (localLicenseInterface != null)
      try
      {
        this.i = localLicenseInterface.getUnitOne();
        this.j = localLicenseInterface.getUnitTwo();
        this.k = localLicenseInterface.getStatusOne();
        this.l = localLicenseInterface.getStatusTwo();
      }
      catch (Exception localException)
      {
      }
    long l1 = 86400000L;
    long l2 = c() - 31L * l1;
    this.e = this.d.b(str1, str2, str3, paramInt, l2);
    if (!this.e)
    {
      this.f = this.d.a(str1, str2, str3, paramInt, l2);
      if (this.f)
        this.g = this.d.a(str1, str2, str3, paramInt, l2, this.i, this.j, this.k, this.l);
    }
  }

  private LicenseInterface b()
  {
    if (this.c == null)
    {
      this.h = true;
      return this;
    }
    String str = z[1] + this.c.getEntityType();
    try
    {
      Class localClass1 = Class.forName(str);
      localLicenseInterface = (LicenseInterface)localClass1.newInstance();
      if ((localLicenseInterface instanceof LicenseCPRO))
        ((LicenseCPRO)localLicenseInterface).setEnabled(true);
      this.h = true;
      return localLicenseInterface;
    }
    catch (Exception localException2)
    {
      LicenseInterface localLicenseInterface;
      str = this.c.getModelPackage();
      if (Util.isEmpty(str))
        return null;
      str = str + z[0] + this.c.getEntityType();
      try
      {
        Class localClass2 = Class.forName(str);
        localLicenseInterface = (LicenseInterface)localClass2.newInstance();
        this.h = true;
        return localLicenseInterface;
      }
      catch (Exception localException2)
      {
      }
    }
    return null;
  }

  private long c()
  {
    return System.currentTimeMillis();
  }

  boolean a(String paramString)
  {
    if (this.c == null)
      return paramString == null;
    return this.c.getEntityType().equals(paramString);
  }

  public boolean isAssetExpired()
  {
    return this.e;
  }

  public boolean isLicensed(boolean paramBoolean)
  {
    int n = m;
    if (n == 0)
      if (paramBoolean)
      {
        if (n != 0)
          break label29;
        if (!this.h)
          return false;
      }
    label29: return this.f;
  }

  public boolean isLicensed()
  {
    return this.f;
  }

  public boolean checkLicense()
  {
    int n = m;
    if (n == 0)
      if (!this.h)
        return true;
    if (n == 0);
    String str1 = this.c == null ? z[6] : this.c.getName();
    String str2;
    if (n == 0)
      if (this.e)
      {
        str2 = Msg.getMsg(Env.getAD_Language(Env.getCtx()), z[5], new Object[] { str1, getGuaranteeDate().toString().substring(0, 10) });
        b.saveError(z[8], str2);
        return false;
      }
    if (n == 0)
      if (!this.f)
      {
        str2 = Msg.getMsg(Env.getAD_Language(Env.getCtx()), z[7], new Object[] { str1 });
        b.saveError(z[8], str2);
        return false;
      }
    return true;
  }

  public boolean isCompliant()
  {
    return this.g;
  }

  public Timestamp getGuaranteeDate()
  {
    if (m == 0)
      if (this.d == null)
        return null;
    long l1 = this.d.m();
    if (l1 == 0L)
      return null;
    return new Timestamp(l1);
  }

  public int getGuaranteeDays()
  {
    Timestamp localTimestamp1 = getGuaranteeDate();
    if (m == 0)
      if (localTimestamp1 == null)
        return 0;
    Timestamp localTimestamp2 = new Timestamp(c());
    return TimeUtil.getDaysBetween(localTimestamp2, localTimestamp1);
  }

  public int getUnitOne()
  {
    int n = m;
    if (n == 0)
      if (this.h)
      {
        if (n != 0)
          break label31;
        if (this.c == null)
          return Support.f();
      }
    label31: return (int)this.i;
  }

  public int getUnitTwo()
  {
    if ((m != 0) || ((this.h) && (this.c == null)));
    return (int)this.j;
  }

  public String getStatusOne()
  {
    int n = m;
    if (n == 0)
      if (this.h)
      {
        if (n != 0)
          break label30;
        if (this.c == null)
          return "";
      }
    label30: return this.k;
  }

  public String getStatusTwo()
  {
    int n = m;
    if (n == 0)
      if (this.h)
      {
        if (n != 0)
          break label30;
        if (this.c == null)
          return "";
      }
    label30: return this.l;
  }

  public String toString()
  {
    int n = m;
    String str = z[2];
    if ((n != 0) || (this.c != null))
      str = this.c.getEntityType();
    StringBuffer localStringBuffer = new StringBuffer(str);
    if (n == 0)
    {
      if (isCompliant())
      {
        localStringBuffer.append(z[3]);
        if (n == 0);
      }
      else
      {
        if (n != 0)
          break label85;
      }
    }
    else if (isLicensed())
      localStringBuffer.append(z[4]);
    label85: return super.toString();
  }

  String a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    int i1 = m;
    StringBuffer localStringBuffer = new StringBuffer(paramString1);
    if (i1 == 0)
      if (isCompliant())
      {
        localStringBuffer.append(z[3]);
        if (i1 == 0)
          break label173;
      }
    if (i1 == 0)
    {
      if (isLicensed())
      {
        localStringBuffer.append(z[4]);
        if (i1 == 0)
          break label173;
      }
      if (i1 != 0);
    }
    else if (isAssetExpired())
    {
      localStringBuffer.append(z[21]);
      if (i1 == 0)
        break label173;
    }
    if (i1 == 0)
      if (this.d.b() == null)
        return localStringBuffer.append(z[12]).toString();
    if (!this.d.b().booleanValue())
      return localStringBuffer.append(z[23]).toString();
    return localStringBuffer.append(z[18]).toString();
    label173: if ((i1 != 0) || (paramString1.equals(z[2])))
      if (PDFViewerBean.isLicensed())
      {
        localStringBuffer.append(z[22]);
        if (i1 == 0);
      }
      else
      {
        localStringBuffer.append(z[26]);
      }
    Timestamp localTimestamp = getGuaranteeDate();
    if (localTimestamp != null)
    {
      String str = DisplayType.getDateFormat().format(localTimestamp);
      int n = getGuaranteeDays();
      if (i1 == 0)
      {
        if (n > 0)
          localStringBuffer.append(z[14]).append(str).append(z[30]).append(n).append(z[17]);
      }
      else
        if (i1 == 0)
          break label349;
      localStringBuffer.append(z[27]).append(str).append(z[28]).append(-1 * n).append(z[16]);
    }
    label349: if (i1 == 0)
      if (paramString2 != null)
      {
        if (i1 == 0)
        {
          localStringBuffer.append(z[13]);
          if (Util.isEmpty(paramString2))
          {
            localStringBuffer.append(z[15]);
            if (i1 == 0)
              break label406;
          }
        }
        localStringBuffer.append(paramString2);
        localStringBuffer.append(z[20]).append(this.d.i());
        localStringBuffer.append(z[25]).append(getUnitOne()).append(")");
      }
    label406: if (i1 == 0)
      if (paramString3 != null)
      {
        if (i1 == 0)
        {
          localStringBuffer.append(z[13]);
          if (Util.isEmpty(paramString3))
          {
            localStringBuffer.append(z[19]);
            if (i1 == 0)
              break label509;
          }
        }
        localStringBuffer.append(paramString3);
        label509: localStringBuffer.append(z[20]).append(this.d.j());
        localStringBuffer.append(z[25]).append(getUnitTwo()).append(")");
      }
    if (i1 == 0)
      if (paramString4 != null)
      {
        if (i1 == 0)
        {
          localStringBuffer.append(z[13]);
          if (Util.isEmpty(paramString4))
          {
            localStringBuffer.append(z[24]);
            if (i1 == 0)
              break label615;
          }
        }
        localStringBuffer.append(paramString4);
        label615: localStringBuffer.append(z[20]).append(this.d.k());
        if (i1 != 0)
          break label678;
        if (!Util.isEmpty(getStatusOne()))
          localStringBuffer.append(z[25]).append(getStatusOne()).append(")");
      }
    label678: if (i1 == 0)
      if (paramString5 != null)
      {
        if (i1 == 0)
        {
          localStringBuffer.append(z[13]);
          if (Util.isEmpty(paramString5))
          {
            localStringBuffer.append(z[29]);
            if (i1 == 0)
              break label736;
          }
        }
        localStringBuffer.append(paramString5);
        label736: localStringBuffer.append(z[20]).append(this.d.l());
        if (i1 != 0)
          break label802;
        if (!Util.isEmpty(getStatusTwo()))
          localStringBuffer.append(z[25]).append(getStatusTwo()).append(")");
      }
    label802: return localStringBuffer.toString();
  }

  static
  {
    // Byte code:
    //   0: bipush 31
    //   2: anewarray 186	java/lang/String
    //   5: dup
    //   6: iconst_0
    //   7: ldc 98
    //   9: bipush 255
    //   11: goto +329 -> 340
    //   14: aastore
    //   15: dup
    //   16: iconst_1
    //   17: ldc 88
    //   19: iconst_0
    //   20: goto +320 -> 340
    //   23: aastore
    //   24: dup
    //   25: iconst_2
    //   26: ldc 24
    //   28: iconst_1
    //   29: goto +311 -> 340
    //   32: aastore
    //   33: dup
    //   34: iconst_3
    //   35: ldc 113
    //   37: iconst_2
    //   38: goto +302 -> 340
    //   41: aastore
    //   42: dup
    //   43: iconst_4
    //   44: ldc 115
    //   46: iconst_3
    //   47: goto +293 -> 340
    //   50: aastore
    //   51: dup
    //   52: iconst_5
    //   53: ldc 101
    //   55: iconst_4
    //   56: goto +284 -> 340
    //   59: aastore
    //   60: dup
    //   61: bipush 6
    //   63: ldc 100
    //   65: iconst_5
    //   66: goto +274 -> 340
    //   69: aastore
    //   70: dup
    //   71: bipush 7
    //   73: ldc 105
    //   75: bipush 6
    //   77: goto +263 -> 340
    //   80: aastore
    //   81: dup
    //   82: bipush 8
    //   84: ldc 14
    //   86: bipush 7
    //   88: goto +252 -> 340
    //   91: aastore
    //   92: dup
    //   93: bipush 9
    //   95: ldc 10
    //   97: bipush 8
    //   99: goto +241 -> 340
    //   102: aastore
    //   103: dup
    //   104: bipush 10
    //   106: ldc 26
    //   108: bipush 9
    //   110: goto +230 -> 340
    //   113: aastore
    //   114: dup
    //   115: bipush 11
    //   117: ldc 25
    //   119: bipush 10
    //   121: goto +219 -> 340
    //   124: aastore
    //   125: dup
    //   126: bipush 12
    //   128: ldc 120
    //   130: bipush 11
    //   132: goto +208 -> 340
    //   135: aastore
    //   136: dup
    //   137: bipush 13
    //   139: ldc 136
    //   141: bipush 12
    //   143: goto +197 -> 340
    //   146: aastore
    //   147: dup
    //   148: bipush 14
    //   150: ldc 129
    //   152: bipush 13
    //   154: goto +186 -> 340
    //   157: aastore
    //   158: dup
    //   159: bipush 15
    //   161: ldc 137
    //   163: bipush 14
    //   165: goto +175 -> 340
    //   168: aastore
    //   169: dup
    //   170: bipush 16
    //   172: ldc 135
    //   174: bipush 15
    //   176: goto +164 -> 340
    //   179: aastore
    //   180: dup
    //   181: bipush 17
    //   183: ldc 132
    //   185: bipush 16
    //   187: goto +153 -> 340
    //   190: aastore
    //   191: dup
    //   192: bipush 18
    //   194: ldc 123
    //   196: bipush 17
    //   198: goto +142 -> 340
    //   201: aastore
    //   202: dup
    //   203: bipush 19
    //   205: ldc 144
    //   207: bipush 18
    //   209: goto +131 -> 340
    //   212: aastore
    //   213: dup
    //   214: bipush 20
    //   216: ldc 138
    //   218: bipush 19
    //   220: goto +120 -> 340
    //   223: aastore
    //   224: dup
    //   225: bipush 21
    //   227: ldc 118
    //   229: bipush 20
    //   231: goto +109 -> 340
    //   234: aastore
    //   235: dup
    //   236: bipush 22
    //   238: ldc 124
    //   240: bipush 21
    //   242: goto +98 -> 340
    //   245: aastore
    //   246: dup
    //   247: bipush 23
    //   249: ldc 122
    //   251: bipush 22
    //   253: goto +87 -> 340
    //   256: aastore
    //   257: dup
    //   258: bipush 24
    //   260: ldc 147
    //   262: bipush 23
    //   264: goto +76 -> 340
    //   267: aastore
    //   268: dup
    //   269: bipush 25
    //   271: ldc 141
    //   273: bipush 24
    //   275: goto +65 -> 340
    //   278: aastore
    //   279: dup
    //   280: bipush 26
    //   282: ldc 125
    //   284: bipush 25
    //   286: goto +54 -> 340
    //   289: aastore
    //   290: dup
    //   291: bipush 27
    //   293: ldc 133
    //   295: bipush 26
    //   297: goto +43 -> 340
    //   300: aastore
    //   301: dup
    //   302: bipush 28
    //   304: ldc 134
    //   306: bipush 27
    //   308: goto +32 -> 340
    //   311: aastore
    //   312: dup
    //   313: bipush 29
    //   315: ldc 149
    //   317: bipush 28
    //   319: goto +21 -> 340
    //   322: aastore
    //   323: dup
    //   324: bipush 30
    //   326: ldc 130
    //   328: bipush 29
    //   330: goto +10 -> 340
    //   333: aastore
    //   334: putstatic 461	com/compiere/client/SysEnv:z	[Ljava/lang/String;
    //   337: goto +247 -> 584
    //   340: swap
    //   341: invokevirtual 453	java/lang/String:toCharArray	()[C
    //   344: dup
    //   345: arraylength
    //   346: swap
    //   347: iconst_0
    //   348: istore_0
    //   349: swap
    //   350: dup_x1
    //   351: iconst_1
    //   352: if_icmpgt +78 -> 430
    //   355: dup
    //   356: iload_0
    //   357: dup2
    //   358: caload
    //   359: iload_0
    //   360: iconst_5
    //   361: irem
    //   362: tableswitch	default:+50 -> 412, 0:+30->392, 1:+35->397, 2:+40->402, 3:+45->407
    //   393: istore_2
    //   394: goto +20 -> 414
    //   397: bipush 29
    //   399: goto +15 -> 414
    //   402: bipush 55
    //   404: goto +10 -> 414
    //   407: bipush 57
    //   409: goto +5 -> 414
    //   412: bipush 75
    //   414: ixor
    //   415: i2c
    //   416: castore
    //   417: iinc 0 1
    //   420: swap
    //   421: dup_x1
    //   422: ifne +8 -> 430
    //   425: dup2
    //   426: swap
    //   427: goto -70 -> 357
    //   430: swap
    //   431: dup_x1
    //   432: iload_0
    //   433: if_icmpgt -78 -> 355
    //   436: new 186	java/lang/String
    //   439: dup_x1
    //   440: swap
    //   441: invokespecial 456	java/lang/String:<init>	([C)V
    //   444: invokevirtual 459	java/lang/String:intern	()Ljava/lang/String;
    //   447: swap
    //   448: pop
    //   449: swap
    //   450: tableswitch	default:+-436 -> 14, 0:+-427->23, 1:+-418->32, 2:+-409->41, 3:+-400->50, 4:+-391->59, 5:+-381->69, 6:+-370->80, 7:+-359->91, 8:+-348->102, 9:+-337->113, 10:+-326->124, 11:+-315->135, 12:+-304->146, 13:+-293->157, 14:+-282->168, 15:+-271->179, 16:+-260->190, 17:+-249->201, 18:+-238->212, 19:+-227->223, 20:+-216->234, 21:+-205->245, 22:+-194->256, 23:+-183->267, 24:+-172->278, 25:+-161->289, 26:+-150->300, 27:+-139->311, 28:+-128->322, 29:+-117->333
    //   585: putstatic 2	com/compiere/client/SysEnv:a	Ljava/util/ArrayList;
    //   588: ldc 6
    //   590: invokestatic 152	org/compiere/util/CLogger:getCLogger	(Ljava/lang/Class;)Lorg/compiere/util/CLogger;
    //   593: putstatic 13	com/compiere/client/SysEnv:b	Lorg/compiere/util/CLogger;
    //   596: return
  }
}