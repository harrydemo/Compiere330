package com.compiere.client;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import oracle.jdbc.OracleDriver;
import org.compiere.db.CConnection;
import org.compiere.db.CompiereDatabase;
import org.compiere.install.DatabaseCreate;
import org.compiere.model.MEntityType;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Util;

public class CommandLineInstall
{
  private static final long serialVersionUID = -6626945837034475105L;
  private static CLogger a;
  private Properties b = null;
  private Properties c = new Properties();
  private String d = null;
  private ArrayList e = new ArrayList();
  private ArrayList f = new ArrayList();
  private boolean g = false;
  private MEntityType[] h = null;
  private boolean i = false;
  private final boolean j;
  private InstallInfo k = new InstallInfo();
  private List l = new ArrayList();
  static final String m;
  static final int n;
  private final String o;
  private final String p;
  private final String q;
  private final boolean r;
  private static final String[] z;

  private boolean a()
  {
    int i2 = SysEnv.m;
    String str1 = Ini.getCompiereHome() + File.separator + z[24];
    File localFile = new File(str1);
    if (localFile.exists())
    {
      try
      {
        FileInputStream localFileInputStream = new FileInputStream(localFile);
        this.c.load(localFileInputStream);
        localFileInputStream.close();
      }
      catch (Exception localException1)
      {
        a.warning(localException1.toString());
      }
      a.info(localFile.toString());
    }
    String str2 = this.c.getProperty(z[0]);
    String str3 = this.c.getProperty(z[8]);
    String str4 = this.c.getProperty(z[10]);
    String str5 = this.c.getProperty(z[20]);
    String str6 = this.c.getProperty(z[9]);
    String str7 = this.c.getProperty(z[3]);
    String str8 = this.c.getProperty(z[5]);
    int i1 = 0;
    Connection localConnection = null;
    String str9 = null;
    try
    {
      Object localObject1;
      if ((i2 != 0) || (!z[22].equals(str2)))
      {
        if (i2 != 0)
          break label331;
        if (!z[19].equals(str2));
      }
      else
      {
        localObject1 = new OracleDriver();
        DriverManager.registerDriver((java.sql.Driver)localObject1);
        str9 = z[21] + str7 + ":" + str8 + "/" + str4;
        localConnection = DriverManager.getConnection(str9, z[25], str3);
        break label528;
      }
      label331: if (i2 == 0)
        if (z[2].equals(str2))
        {
          localObject1 = new com.edb.Driver();
          DriverManager.registerDriver((java.sql.Driver)localObject1);
          str9 = z[6] + str7 + ":" + str8 + z[11];
          localConnection = DriverManager.getConnection(str9, z[13], str3);
          break label528;
        }
      if (z[17].equals(str2))
      {
        localObject1 = new SQLServerDriver();
        DriverManager.registerDriver((java.sql.Driver)localObject1);
        str9 = z[15] + str7 + ":" + str8;
        localConnection = DriverManager.getConnection(str9, z[18], str3);
      }
      else
      {
        throw new IllegalArgumentException(z[12] + str2);
      }
      label528: a.info(str9);
      i1 = 1;
      localConnection.close();
    }
    catch (Exception localException2)
    {
      a.severe(localException2.toString());
      if (i2 != 0)
        break label589;
    }
    i1 = 0;
    if (localConnection != null)
      try
      {
        localConnection.close();
      }
      catch (Exception localException5)
      {
      }
    label589: if (i2 == 0)
      if (i1 == 0)
      {
        a.severe(z[7] + str4 + "(" + str9 + ")");
        return false;
      }
    i1 = 0;
    localConnection = null;
    str9 = null;
    try
    {
      Object localObject2;
      if ((i2 != 0) || (!z[22].equals(str2)))
      {
        if (i2 != 0)
          break label772;
        if (!z[19].equals(str2));
      }
      else
      {
        localObject2 = new OracleDriver();
        DriverManager.registerDriver((java.sql.Driver)localObject2);
        str9 = z[21] + str7 + ":" + str8 + "/" + str4;
        localConnection = DriverManager.getConnection(str9, str5, str6);
        break label961;
      }
      label772: if (i2 == 0)
        if (z[2].equals(str2))
        {
          localObject2 = new com.edb.Driver();
          DriverManager.registerDriver((java.sql.Driver)localObject2);
          str9 = z[6] + str7 + ":" + str8 + z[11];
          localConnection = DriverManager.getConnection(str9, str5, str6);
          break label961;
        }
      if (z[17].equals(str2))
      {
        localObject2 = new SQLServerDriver();
        DriverManager.registerDriver((java.sql.Driver)localObject2);
        str9 = z[15] + str7 + ":" + str8;
        localConnection = DriverManager.getConnection(str9, str5, str6);
      }
      else
      {
        throw new IllegalArgumentException(z[12] + str2);
      }
      label961: a.info(str9);
      i1 = 1;
      this.g = true;
      localConnection.close();
    }
    catch (Exception localException3)
    {
      a.severe(localException3.toString());
      if (i2 != 0)
        break label1027;
    }
    i1 = 0;
    if (localConnection != null)
      try
      {
        localConnection.close();
      }
      catch (Exception localException6)
      {
      }
    label1027: if (i2 == 0)
      if (i1 == 0)
        a.info(z[7] + str4 + "(" + str9 + ")" + z[23] + str5);
    if ((i2 != 0) || (!this.g))
    {
      if (i2 == 0)
        if (this.j)
        {
          a.severe(z[16]);
          return false;
        }
      return true;
    }
    try
    {
      DB.setDBTarget(CConnection.get(str2, str7, str8, str4, str5, str6));
      DB.getDatabase();
    }
    catch (Exception localException4)
    {
      a.config(z[4]);
      if (i2 == 0)
        if (this.j)
        {
          a.severe(z[14]);
          return false;
        }
      return true;
    }
    c();
    return true;
  }

  private b a(a parama)
  {
    int i1 = SysEnv.m;
    String str1 = parama.b();
    if (str1 == null)
      return new b(z[86], false, z[84]);
    boolean bool = false;
    String str2 = z[86];
    if (i1 == 0)
      if (str1.equals("D"))
      {
        bool = true;
        str2 = z[85];
        return new b(str1, bool, str2);
      }
    String str3 = this.o;
    String str4 = this.p;
    String str5 = this.q;
    if (i1 == 0)
      if (!Util.isEmpty(str4))
      {
        if (i1 != 0)
          break label146;
        if (!Util.isEmpty(str5))
        {
          if (i1 != 0)
            break label177;
          if (!str4.equals("?"))
            break label153;
        }
      }
    label146: str2 = z[83];
    if (i1 != 0)
    {
      label153: str2 = a(str3, str4, str5, parama);
      label177: if (i1 == 0);
      bool = str2.indexOf(z[82]) == -1;
    }
    b localb = new b(str1, bool, str2);
    return localb;
  }

  private String a(String paramString1, String paramString2, String paramString3, a parama)
  {
    String str1 = parama.b();
    String str2 = parama.k();
    CConnection localCConnection = CConnection.get();
    String str3 = localCConnection.getConnectionURL();
    String str4 = localCConnection.getDBType();
    c localc = new c(a);
    boolean bool = localc.a(paramString1, paramString2, paramString3, str1, str2, str3, str4);
    String str5 = null;
    if (!bool)
    {
      str5 = z[64];
      b(str5);
    }
    else
    {
      str5 = localc.a();
    }
    return str5;
  }

  private void b()
  {
    int i2 = SysEnv.m;
    File localFile1 = new File(this.d);
    File localFile2 = new File(localFile1, z[41] + File.separator + z[80]);
    if (i2 == 0)
      if (localFile2.exists())
      {
        localObject = localFile2.listFiles();
        int i1 = 0;
        do
        {
          if (i1 >= localObject.length)
            break;
          str2 = localObject[i1];
          str2.delete();
          i1++;
          if (i2 != 0)
            break label122;
        }
        while (i2 == 0);
        if (i2 == 0)
          break label122;
      }
    localFile2.mkdir();
    label122: Object localObject = this.d + File.separator + z[41];
    a((String)localObject, z[81]);
    String str1 = this.d + File.separator + z[57] + File.separator + z[54];
    a(str1, null);
    String str2 = this.d + File.separator + z[57] + File.separator + z[52];
    a(str2, null);
  }

  // ERROR //
  private void a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: getstatic 898	com/compiere/client/SysEnv:m	I
    //   3: istore 9
    //   5: new 9	java/io/File
    //   8: dup
    //   9: aload_1
    //   10: invokespecial 10	java/io/File:<init>	(Ljava/lang/String;)V
    //   13: astore_3
    //   14: aload_3
    //   15: iload 9
    //   17: ifne +53 -> 70
    //   20: invokevirtual 11	java/io/File:exists	()Z
    //   23: ifne +46 -> 69
    //   26: aload_3
    //   27: iload 9
    //   29: ifne +41 -> 70
    //   32: invokevirtual 103	java/io/File:mkdir	()Z
    //   35: ifne +34 -> 69
    //   38: new 109	java/lang/RuntimeException
    //   41: dup
    //   42: new 2	java/lang/StringBuilder
    //   45: dup
    //   46: invokespecial 3	java/lang/StringBuilder:<init>	()V
    //   49: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   52: bipush 78
    //   54: aaload
    //   55: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: aload_1
    //   59: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: invokevirtual 8	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: invokespecial 111	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   68: athrow
    //   69: aload_3
    //   70: invokevirtual 101	java/io/File:listFiles	()[Ljava/io/File;
    //   73: astore 4
    //   75: aload 4
    //   77: astore 5
    //   79: aload 5
    //   81: arraylength
    //   82: istore 6
    //   84: iconst_0
    //   85: istore 7
    //   87: iload 7
    //   89: iload 6
    //   91: if_icmpge +88 -> 179
    //   94: aload 5
    //   96: iload 7
    //   98: aaload
    //   99: astore 8
    //   101: aload_2
    //   102: iload 9
    //   104: ifne +11 -> 115
    //   107: ifnull +23 -> 130
    //   110: aload 8
    //   112: invokevirtual 112	java/io/File:getName	()Ljava/lang/String;
    //   115: aload_2
    //   116: invokevirtual 113	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   119: iload 9
    //   121: ifne +14 -> 135
    //   124: ifne +6 -> 130
    //   127: goto +44 -> 171
    //   130: aload 8
    //   132: invokevirtual 102	java/io/File:delete	()Z
    //   135: ifne +36 -> 171
    //   138: getstatic 1	com/compiere/client/CommandLineInstall:a	Lorg/compiere/util/CLogger;
    //   141: new 2	java/lang/StringBuilder
    //   144: dup
    //   145: invokespecial 3	java/lang/StringBuilder:<init>	()V
    //   148: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   151: bipush 79
    //   153: aaload
    //   154: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: aload 8
    //   159: invokevirtual 115	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   162: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: invokevirtual 8	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   168: invokevirtual 19	org/compiere/util/CLogger:warning	(Ljava/lang/String;)V
    //   171: iinc 7 1
    //   174: iload 9
    //   176: ifeq -89 -> 87
    //   179: return
  }

  // ERROR //
  private String a(String paramString)
  {
    // Byte code:
    //   0: getstatic 898	com/compiere/client/SysEnv:m	I
    //   3: istore 13
    //   5: aload_1
    //   6: iload 13
    //   8: ifne +7 -> 15
    //   11: ifnull +10 -> 21
    //   14: aload_1
    //   15: invokevirtual 116	java/lang/String:length	()I
    //   18: ifgt +34 -> 52
    //   21: new 109	java/lang/RuntimeException
    //   24: dup
    //   25: new 2	java/lang/StringBuilder
    //   28: dup
    //   29: invokespecial 3	java/lang/StringBuilder:<init>	()V
    //   32: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   35: bipush 60
    //   37: aaload
    //   38: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: aload_1
    //   42: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: invokevirtual 8	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   48: invokespecial 111	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   51: athrow
    //   52: new 9	java/io/File
    //   55: dup
    //   56: aload_1
    //   57: invokespecial 10	java/io/File:<init>	(Ljava/lang/String;)V
    //   60: astore_2
    //   61: aload_2
    //   62: invokevirtual 11	java/io/File:exists	()Z
    //   65: ifne +34 -> 99
    //   68: new 109	java/lang/RuntimeException
    //   71: dup
    //   72: new 2	java/lang/StringBuilder
    //   75: dup
    //   76: invokespecial 3	java/lang/StringBuilder:<init>	()V
    //   79: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   82: bipush 48
    //   84: aaload
    //   85: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: aload_1
    //   89: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: invokevirtual 8	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   95: invokespecial 111	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   98: athrow
    //   99: aconst_null
    //   100: astore_3
    //   101: aconst_null
    //   102: astore 4
    //   104: aconst_null
    //   105: astore 5
    //   107: aconst_null
    //   108: astore 6
    //   110: aconst_null
    //   111: astore 7
    //   113: aconst_null
    //   114: astore 8
    //   116: new 119	org/compiere/util/ZipUtil
    //   119: dup
    //   120: aload_1
    //   121: invokespecial 120	org/compiere/util/ZipUtil:<init>	(Ljava/lang/String;)V
    //   124: astore_3
    //   125: aload_3
    //   126: invokevirtual 121	org/compiere/util/ZipUtil:entries	()Ljava/util/Enumeration;
    //   129: astore 9
    //   131: aload 9
    //   133: invokeinterface 122 1 0
    //   138: ifeq +417 -> 555
    //   141: aload 9
    //   143: invokeinterface 123 1 0
    //   148: checkcast 124	java/util/zip/ZipEntry
    //   151: astore 4
    //   153: aload 4
    //   155: invokevirtual 125	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   158: astore 5
    //   160: aload 5
    //   162: iload 13
    //   164: ifne +432 -> 596
    //   167: iload 13
    //   169: ifne +81 -> 250
    //   172: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   175: bipush 51
    //   177: aaload
    //   178: invokevirtual 31	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   181: ifne +22 -> 203
    //   184: aload 5
    //   186: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   189: bipush 53
    //   191: aaload
    //   192: invokevirtual 128	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   195: iload 13
    //   197: ifne +76 -> 273
    //   200: ifeq +57 -> 257
    //   203: new 2	java/lang/StringBuilder
    //   206: dup
    //   207: invokespecial 3	java/lang/StringBuilder:<init>	()V
    //   210: aload_0
    //   211: getfield 97	com/compiere/client/CommandLineInstall:d	Ljava/lang/String;
    //   214: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: getstatic 6	java/io/File:separator	Ljava/lang/String;
    //   220: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   226: bipush 41
    //   228: aaload
    //   229: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   232: getstatic 6	java/io/File:separator	Ljava/lang/String;
    //   235: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: aload 5
    //   240: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: invokevirtual 8	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   246: astore 6
    //   248: aload 6
    //   250: astore 7
    //   252: iload 13
    //   254: ifeq +280 -> 534
    //   257: aload 5
    //   259: iload 13
    //   261: ifne +92 -> 353
    //   264: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   267: bipush 56
    //   269: aaload
    //   270: invokevirtual 113	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   273: ifne +22 -> 295
    //   276: aload 5
    //   278: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   281: bipush 49
    //   283: aaload
    //   284: invokevirtual 113	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   287: iload 13
    //   289: ifne +87 -> 376
    //   292: ifeq +68 -> 360
    //   295: new 2	java/lang/StringBuilder
    //   298: dup
    //   299: invokespecial 3	java/lang/StringBuilder:<init>	()V
    //   302: aload_0
    //   303: getfield 97	com/compiere/client/CommandLineInstall:d	Ljava/lang/String;
    //   306: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   309: getstatic 6	java/io/File:separator	Ljava/lang/String;
    //   312: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   315: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   318: bipush 57
    //   320: aaload
    //   321: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   324: getstatic 6	java/io/File:separator	Ljava/lang/String;
    //   327: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   330: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   333: bipush 54
    //   335: aaload
    //   336: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   339: getstatic 6	java/io/File:separator	Ljava/lang/String;
    //   342: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   345: aload 5
    //   347: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   350: invokevirtual 8	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   353: astore 7
    //   355: iload 13
    //   357: ifeq +177 -> 534
    //   360: aload 5
    //   362: iload 13
    //   364: ifne +92 -> 456
    //   367: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   370: bipush 50
    //   372: aaload
    //   373: invokevirtual 113	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   376: ifne +22 -> 398
    //   379: aload 5
    //   381: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   384: bipush 61
    //   386: aaload
    //   387: invokevirtual 113	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   390: iload 13
    //   392: ifne +82 -> 474
    //   395: ifeq +68 -> 463
    //   398: new 2	java/lang/StringBuilder
    //   401: dup
    //   402: invokespecial 3	java/lang/StringBuilder:<init>	()V
    //   405: aload_0
    //   406: getfield 97	com/compiere/client/CommandLineInstall:d	Ljava/lang/String;
    //   409: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   412: getstatic 6	java/io/File:separator	Ljava/lang/String;
    //   415: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   418: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   421: bipush 57
    //   423: aaload
    //   424: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   427: getstatic 6	java/io/File:separator	Ljava/lang/String;
    //   430: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   433: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   436: bipush 52
    //   438: aaload
    //   439: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   442: getstatic 6	java/io/File:separator	Ljava/lang/String;
    //   445: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   448: aload 5
    //   450: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   453: invokevirtual 8	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   456: astore 7
    //   458: iload 13
    //   460: ifeq +74 -> 534
    //   463: aload 5
    //   465: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   468: bipush 58
    //   470: aaload
    //   471: invokevirtual 31	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   474: iload 13
    //   476: ifne +17 -> 493
    //   479: ifne -348 -> 131
    //   482: aload 5
    //   484: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   487: bipush 59
    //   489: aaload
    //   490: invokevirtual 31	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   493: ifeq +6 -> 499
    //   496: goto -365 -> 131
    //   499: getstatic 1	com/compiere/client/CommandLineInstall:a	Lorg/compiere/util/CLogger;
    //   502: new 2	java/lang/StringBuilder
    //   505: dup
    //   506: invokespecial 3	java/lang/StringBuilder:<init>	()V
    //   509: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   512: bipush 55
    //   514: aaload
    //   515: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   518: aload 5
    //   520: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   523: invokevirtual 8	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   526: invokevirtual 19	org/compiere/util/CLogger:warning	(Ljava/lang/String;)V
    //   529: iload 13
    //   531: ifeq -400 -> 131
    //   534: aload_3
    //   535: aload 4
    //   537: invokevirtual 136	org/compiere/util/ZipUtil:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   540: astore 8
    //   542: aload_0
    //   543: aload 8
    //   545: aload 7
    //   547: invokespecial 137	com/compiere/client/CommandLineInstall:a	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   550: iload 13
    //   552: ifeq -421 -> 131
    //   555: aload_3
    //   556: invokevirtual 138	org/compiere/util/ZipUtil:close	()V
    //   559: goto +35 -> 594
    //   562: astore 10
    //   564: getstatic 1	com/compiere/client/CommandLineInstall:a	Lorg/compiere/util/CLogger;
    //   567: aload 10
    //   569: invokevirtual 140	java/io/IOException:toString	()Ljava/lang/String;
    //   572: invokevirtual 19	org/compiere/util/CLogger:warning	(Ljava/lang/String;)V
    //   575: aconst_null
    //   576: astore 11
    //   578: aload_3
    //   579: invokevirtual 138	org/compiere/util/ZipUtil:close	()V
    //   582: aload 11
    //   584: areturn
    //   585: astore 12
    //   587: aload_3
    //   588: invokevirtual 138	org/compiere/util/ZipUtil:close	()V
    //   591: aload 12
    //   593: athrow
    //   594: aload 6
    //   596: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   131	555	562	java/io/IOException
    //   131	555	585	finally
    //   562	578	585	finally
    //   585	587	585	finally
  }

  private void a(InputStream paramInputStream, String paramString)
  {
    int i1 = SysEnv.m;
    File localFile = null;
    FileOutputStream localFileOutputStream = null;
    BufferedOutputStream localBufferedOutputStream = null;
    BufferedInputStream localBufferedInputStream = null;
    try
    {
      localFile = new File(paramString);
      if (i1 == 0)
      {
        if (localFile.exists())
          a.info(z[26] + paramString);
      }
      else
        localFile.delete();
      localFileOutputStream = new FileOutputStream(localFile);
      localBufferedOutputStream = new BufferedOutputStream(localFileOutputStream);
      localBufferedInputStream = new BufferedInputStream(paramInputStream);
      do
      {
        if (localBufferedInputStream.available() <= 0)
          break;
        localBufferedOutputStream.write(localBufferedInputStream.read());
        if (i1 != 0)
          break label145;
      }
      while (i1 == 0);
    }
    catch (IOException localIOException2)
    {
      label145: throw new RuntimeException(z[27] + paramString);
    }
    finally
    {
      try
      {
        if ((i1 != 0) || (localBufferedOutputStream != null))
          localBufferedOutputStream.close();
        if ((i1 != 0) || (localBufferedInputStream != null))
          localBufferedInputStream.close();
      }
      catch (IOException localIOException3)
      {
      }
    }
  }

  private void c()
  {
    try
    {
      this.h = MEntityType.getEntityTypes(Env.getCtx(), false);
      String str = z[76];
      str = DB.getDatabase().convertStatement(str);
      DB.executeUpdate(str, null);
      str = z[77];
      str = DB.getDatabase().convertStatement(str);
      DB.executeUpdate(str, null);
    }
    catch (Exception localException)
    {
    }
  }

  public CommandLineInstall(String paramString1, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.o = paramString1;
    this.p = paramString3;
    this.q = paramString2;
    this.k.c(paramString1);
    this.k.d(paramString3);
    this.k.e(paramString2);
    this.j = paramBoolean1;
    this.r = paramBoolean2;
  }

  // ERROR //
  protected boolean d()
  {
    // Byte code:
    //   0: getstatic 898	com/compiere/client/SysEnv:m	I
    //   3: istore 10
    //   5: iconst_0
    //   6: invokestatic 178	org/compiere/util/Ini:setUi	(Z)V
    //   9: iconst_0
    //   10: invokestatic 179	org/compiere/util/Ini:setClient	(Z)V
    //   13: iconst_0
    //   14: invokestatic 180	org/compiere/util/Ini:loadProperties	(Z)V
    //   17: aload_0
    //   18: invokestatic 181	org/compiere/util/Ini:getProperties	()Ljava/util/Properties;
    //   21: putfield 162	com/compiere/client/CommandLineInstall:b	Ljava/util/Properties;
    //   24: aload_0
    //   25: invokestatic 4	org/compiere/util/Ini:getCompiereHome	()Ljava/lang/String;
    //   28: putfield 97	com/compiere/client/CommandLineInstall:d	Ljava/lang/String;
    //   31: aload_0
    //   32: iload 10
    //   34: ifne +35 -> 69
    //   37: invokespecial 182	com/compiere/client/CommandLineInstall:a	()Z
    //   40: ifne +17 -> 57
    //   43: getstatic 1	com/compiere/client/CommandLineInstall:a	Lorg/compiere/util/CLogger;
    //   46: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   49: bipush 30
    //   51: aaload
    //   52: invokevirtual 56	org/compiere/util/CLogger:severe	(Ljava/lang/String;)V
    //   55: iconst_0
    //   56: ireturn
    //   57: aload_0
    //   58: aload_0
    //   59: getfield 97	com/compiere/client/CommandLineInstall:d	Ljava/lang/String;
    //   62: invokestatic 184	com/compiere/client/a:a	(Ljava/lang/String;)Ljava/util/ArrayList;
    //   65: putfield 168	com/compiere/client/CommandLineInstall:f	Ljava/util/ArrayList;
    //   68: aload_0
    //   69: getfield 168	com/compiere/client/CommandLineInstall:f	Ljava/util/ArrayList;
    //   72: iload 10
    //   74: ifne +10 -> 84
    //   77: ifnull +18 -> 95
    //   80: aload_0
    //   81: getfield 168	com/compiere/client/CommandLineInstall:f	Ljava/util/ArrayList;
    //   84: invokevirtual 185	java/util/ArrayList:size	()I
    //   87: iload 10
    //   89: ifne +9 -> 98
    //   92: ifne +5 -> 97
    //   95: iconst_0
    //   96: ireturn
    //   97: iconst_0
    //   98: istore_1
    //   99: iload_1
    //   100: aload_0
    //   101: getfield 168	com/compiere/client/CommandLineInstall:f	Ljava/util/ArrayList;
    //   104: invokevirtual 185	java/util/ArrayList:size	()I
    //   107: if_icmpge +52 -> 159
    //   110: aload_0
    //   111: getfield 168	com/compiere/client/CommandLineInstall:f	Ljava/util/ArrayList;
    //   114: iload_1
    //   115: invokevirtual 186	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   118: checkcast 187	com/compiere/client/a
    //   121: astore_2
    //   122: aload_0
    //   123: aload_2
    //   124: invokespecial 188	com/compiere/client/CommandLineInstall:a	(Lcom/compiere/client/a;)Lcom/compiere/client/b;
    //   127: astore_3
    //   128: iload 10
    //   130: ifne +24 -> 154
    //   133: aload_3
    //   134: iload 10
    //   136: ifne +63 -> 199
    //   139: ifnull +12 -> 151
    //   142: aload_0
    //   143: getfield 167	com/compiere/client/CommandLineInstall:e	Ljava/util/ArrayList;
    //   146: iload_1
    //   147: aload_3
    //   148: invokevirtual 189	java/util/ArrayList:add	(ILjava/lang/Object;)V
    //   151: iinc 1 1
    //   154: iload 10
    //   156: ifeq -57 -> 99
    //   159: aload_0
    //   160: getfield 177	com/compiere/client/CommandLineInstall:r	Z
    //   163: iload 10
    //   165: ifne +11 -> 176
    //   168: ifeq +7 -> 175
    //   171: aload_0
    //   172: invokespecial 190	com/compiere/client/CommandLineInstall:b	()V
    //   175: iconst_0
    //   176: istore_1
    //   177: iload_1
    //   178: aload_0
    //   179: getfield 168	com/compiere/client/CommandLineInstall:f	Ljava/util/ArrayList;
    //   182: invokevirtual 185	java/util/ArrayList:size	()I
    //   185: if_icmpge +294 -> 479
    //   188: aload_0
    //   189: getfield 167	com/compiere/client/CommandLineInstall:e	Ljava/util/ArrayList;
    //   192: iload_1
    //   193: invokevirtual 186	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   196: checkcast 72	com/compiere/client/b
    //   199: astore_2
    //   200: iload 10
    //   202: ifne +313 -> 515
    //   205: iload 10
    //   207: ifne +267 -> 474
    //   210: aload_2
    //   211: ifnull +260 -> 471
    //   214: aload_2
    //   215: iload 10
    //   217: ifne +20 -> 237
    //   220: invokevirtual 191	com/compiere/client/b:b	()Z
    //   223: ifne +6 -> 229
    //   226: goto +245 -> 471
    //   229: aload_0
    //   230: getfield 168	com/compiere/client/CommandLineInstall:f	Ljava/util/ArrayList;
    //   233: iload_1
    //   234: invokevirtual 186	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   237: checkcast 187	com/compiere/client/a
    //   240: astore_3
    //   241: aload_3
    //   242: invokevirtual 71	com/compiere/client/a:b	()Ljava/lang/String;
    //   245: astore 4
    //   247: aconst_null
    //   248: astore 5
    //   250: aload_0
    //   251: getfield 156	com/compiere/client/CommandLineInstall:h	[Lorg/compiere/model/MEntityType;
    //   254: ifnull +62 -> 316
    //   257: iconst_0
    //   258: istore 6
    //   260: iload 6
    //   262: aload_0
    //   263: getfield 156	com/compiere/client/CommandLineInstall:h	[Lorg/compiere/model/MEntityType;
    //   266: arraylength
    //   267: if_icmpge +49 -> 316
    //   270: aload_0
    //   271: getfield 156	com/compiere/client/CommandLineInstall:h	[Lorg/compiere/model/MEntityType;
    //   274: iload 6
    //   276: aaload
    //   277: iload 10
    //   279: ifne +26 -> 305
    //   282: invokevirtual 192	org/compiere/model/MEntityType:getEntityType	()Ljava/lang/String;
    //   285: aload 4
    //   287: invokevirtual 31	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   290: iload 10
    //   292: ifne -114 -> 178
    //   295: ifeq +15 -> 310
    //   298: aload_0
    //   299: getfield 156	com/compiere/client/CommandLineInstall:h	[Lorg/compiere/model/MEntityType;
    //   302: iload 6
    //   304: aaload
    //   305: astore 5
    //   307: goto +9 -> 316
    //   310: iinc 6 1
    //   313: goto -53 -> 260
    //   316: aload_3
    //   317: aload 5
    //   319: aload_2
    //   320: invokevirtual 193	com/compiere/client/a:a	(Lorg/compiere/model/MEntityType;Lcom/compiere/client/b;)[Ljava/lang/String;
    //   323: astore 6
    //   325: aconst_null
    //   326: astore 7
    //   328: aconst_null
    //   329: astore 8
    //   331: aload_3
    //   332: invokevirtual 194	com/compiere/client/a:i	()Ljava/lang/String;
    //   335: astore 8
    //   337: aload_0
    //   338: aload 8
    //   340: invokespecial 195	com/compiere/client/CommandLineInstall:a	(Ljava/lang/String;)Ljava/lang/String;
    //   343: astore 7
    //   345: goto +16 -> 361
    //   348: astore 9
    //   350: getstatic 1	com/compiere/client/CommandLineInstall:a	Lorg/compiere/util/CLogger;
    //   353: aload 9
    //   355: invokevirtual 196	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   358: invokevirtual 56	org/compiere/util/CLogger:severe	(Ljava/lang/String;)V
    //   361: aload 7
    //   363: ifnonnull +6 -> 369
    //   366: goto +105 -> 471
    //   369: aload_0
    //   370: iload 10
    //   372: ifne +88 -> 460
    //   375: getfield 172	com/compiere/client/CommandLineInstall:k	Lcom/compiere/client/InstallInfo;
    //   378: aload 8
    //   380: invokevirtual 197	com/compiere/client/InstallInfo:a	(Ljava/lang/String;)V
    //   383: aload 6
    //   385: ifnull +74 -> 459
    //   388: iconst_0
    //   389: istore 9
    //   391: iload 9
    //   393: aload 6
    //   395: arraylength
    //   396: if_icmpge +63 -> 459
    //   399: aload 6
    //   401: iload 9
    //   403: aaload
    //   404: getstatic 198	com/compiere/client/a:d	Ljava/lang/String;
    //   407: invokevirtual 31	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   410: iload 10
    //   412: ifne +58 -> 470
    //   415: iload 10
    //   417: ifne +17 -> 434
    //   420: ifne +17 -> 437
    //   423: aload 6
    //   425: iload 9
    //   427: aaload
    //   428: getstatic 199	com/compiere/client/a:f	Ljava/lang/String;
    //   431: invokevirtual 31	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   434: ifeq +17 -> 451
    //   437: aload_0
    //   438: getfield 172	com/compiere/client/CommandLineInstall:k	Lcom/compiere/client/InstallInfo;
    //   441: aload 8
    //   443: invokevirtual 200	com/compiere/client/InstallInfo:b	(Ljava/lang/String;)V
    //   446: aload_0
    //   447: iconst_1
    //   448: putfield 169	com/compiere/client/CommandLineInstall:i	Z
    //   451: iinc 9 1
    //   454: iload 10
    //   456: ifeq -65 -> 391
    //   459: aload_0
    //   460: getfield 173	com/compiere/client/CommandLineInstall:l	Ljava/util/List;
    //   463: aload 7
    //   465: invokeinterface 201 2 0
    //   470: pop
    //   471: iinc 1 1
    //   474: iload 10
    //   476: ifeq -299 -> 177
    //   479: aload_0
    //   480: getfield 177	com/compiere/client/CommandLineInstall:r	Z
    //   483: iload 10
    //   485: ifne +10 -> 495
    //   488: ifeq +27 -> 515
    //   491: aload_0
    //   492: invokevirtual 202	com/compiere/client/CommandLineInstall:f	()Z
    //   495: iload 10
    //   497: ifne +17 -> 514
    //   500: ifne +15 -> 515
    //   503: aload_0
    //   504: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   507: bipush 28
    //   509: aaload
    //   510: invokespecial 95	com/compiere/client/CommandLineInstall:b	(Ljava/lang/String;)V
    //   513: iconst_0
    //   514: ireturn
    //   515: invokestatic 154	org/compiere/util/Env:getCtx	()Lorg/compiere/util/Ctx;
    //   518: astore_1
    //   519: aload_1
    //   520: invokestatic 204	org/compiere/model/MSession:get	(Lorg/compiere/util/Ctx;)Lorg/compiere/model/MSession;
    //   523: astore_2
    //   524: iload 10
    //   526: ifne +14 -> 540
    //   529: aload_2
    //   530: ifnull +7 -> 537
    //   533: aload_2
    //   534: invokevirtual 205	org/compiere/model/MSession:logout	()V
    //   537: invokestatic 206	org/compiere/util/DB:closeTarget	()V
    //   540: aload_0
    //   541: getfield 62	com/compiere/client/CommandLineInstall:j	Z
    //   544: iload 10
    //   546: ifne +45 -> 591
    //   549: ifeq +38 -> 587
    //   552: aload_0
    //   553: getfield 169	com/compiere/client/CommandLineInstall:i	Z
    //   556: iload 10
    //   558: ifne +28 -> 586
    //   561: ifeq +12 -> 573
    //   564: aload_0
    //   565: invokespecial 207	com/compiere/client/CommandLineInstall:h	()V
    //   568: iload 10
    //   570: ifeq +41 -> 611
    //   573: getstatic 1	com/compiere/client/CommandLineInstall:a	Lorg/compiere/util/CLogger;
    //   576: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   579: bipush 29
    //   581: aaload
    //   582: invokevirtual 56	org/compiere/util/CLogger:severe	(Ljava/lang/String;)V
    //   585: iconst_0
    //   586: ireturn
    //   587: aload_0
    //   588: invokevirtual 209	com/compiere/client/CommandLineInstall:g	()Z
    //   591: iload 10
    //   593: ifne +19 -> 612
    //   596: ifne +15 -> 611
    //   599: aload_0
    //   600: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   603: bipush 31
    //   605: aaload
    //   606: invokespecial 95	com/compiere/client/CommandLineInstall:b	(Ljava/lang/String;)V
    //   609: iconst_0
    //   610: ireturn
    //   611: iconst_1
    //   612: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   331	345	348	java/lang/Exception
  }

  // ERROR //
  public static void main(String[] paramArrayOfString)
  {
    // Byte code:
    //   0: getstatic 898	com/compiere/client/SysEnv:m	I
    //   3: istore 11
    //   5: aload_0
    //   6: arraylength
    //   7: iload 11
    //   9: ifne +8 -> 17
    //   12: ifeq +26 -> 38
    //   15: aload_0
    //   16: arraylength
    //   17: bipush 6
    //   19: iload 11
    //   21: ifne +10 -> 31
    //   24: if_icmpeq +14 -> 38
    //   27: aload_0
    //   28: arraylength
    //   29: bipush 8
    //   31: if_icmpeq +7 -> 38
    //   34: invokestatic 211	com/compiere/client/CommandLineInstall:e	()V
    //   37: return
    //   38: ldc 212
    //   40: astore_1
    //   41: ldc 212
    //   43: astore_2
    //   44: ldc 212
    //   46: astore_3
    //   47: iconst_0
    //   48: istore 4
    //   50: iconst_1
    //   51: istore 5
    //   53: iconst_0
    //   54: istore 6
    //   56: iload 6
    //   58: aload_0
    //   59: arraylength
    //   60: if_icmpge +234 -> 294
    //   63: aload_0
    //   64: iload 6
    //   66: aaload
    //   67: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   70: bipush 68
    //   72: aaload
    //   73: invokevirtual 214	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   76: iload 11
    //   78: ifne +217 -> 295
    //   81: iload 11
    //   83: ifne +32 -> 115
    //   86: ifne +16 -> 102
    //   89: aload_0
    //   90: iinc 6 1
    //   93: iload 6
    //   95: aaload
    //   96: astore_3
    //   97: iload 11
    //   99: ifeq +187 -> 286
    //   102: aload_0
    //   103: iload 6
    //   105: aaload
    //   106: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   109: bipush 70
    //   111: aaload
    //   112: invokevirtual 214	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   115: iload 11
    //   117: ifne +32 -> 149
    //   120: ifne +16 -> 136
    //   123: aload_0
    //   124: iinc 6 1
    //   127: iload 6
    //   129: aaload
    //   130: astore_1
    //   131: iload 11
    //   133: ifeq +153 -> 286
    //   136: aload_0
    //   137: iload 6
    //   139: aaload
    //   140: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   143: bipush 65
    //   145: aaload
    //   146: invokevirtual 214	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   149: iload 11
    //   151: ifne +32 -> 183
    //   154: ifne +16 -> 170
    //   157: aload_0
    //   158: iinc 6 1
    //   161: iload 6
    //   163: aaload
    //   164: astore_2
    //   165: iload 11
    //   167: ifeq +119 -> 286
    //   170: aload_0
    //   171: iload 6
    //   173: aaload
    //   174: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   177: bipush 67
    //   179: aaload
    //   180: invokevirtual 214	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   183: iload 11
    //   185: ifne +38 -> 223
    //   188: ifne +22 -> 210
    //   191: ldc 218
    //   193: aload_0
    //   194: iinc 6 1
    //   197: iload 6
    //   199: aaload
    //   200: invokevirtual 219	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   203: istore 4
    //   205: iload 11
    //   207: ifeq +79 -> 286
    //   210: aload_0
    //   211: iload 6
    //   213: aaload
    //   214: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   217: bipush 66
    //   219: aaload
    //   220: invokevirtual 214	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   223: iload 11
    //   225: ifne +18 -> 243
    //   228: ifne +22 -> 250
    //   231: ldc 218
    //   233: aload_0
    //   234: iinc 6 1
    //   237: iload 6
    //   239: aaload
    //   240: invokevirtual 219	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   243: istore 5
    //   245: iload 11
    //   247: ifeq +39 -> 286
    //   250: getstatic 1	com/compiere/client/CommandLineInstall:a	Lorg/compiere/util/CLogger;
    //   253: new 2	java/lang/StringBuilder
    //   256: dup
    //   257: invokespecial 3	java/lang/StringBuilder:<init>	()V
    //   260: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   263: bipush 71
    //   265: aaload
    //   266: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: aload_0
    //   270: iload 6
    //   272: aaload
    //   273: invokevirtual 5	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   276: invokevirtual 8	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   279: invokevirtual 56	org/compiere/util/CLogger:severe	(Ljava/lang/String;)V
    //   282: invokestatic 211	com/compiere/client/CommandLineInstall:e	()V
    //   285: return
    //   286: iinc 6 1
    //   289: iload 11
    //   291: ifeq -235 -> 56
    //   294: iconst_1
    //   295: invokestatic 222	org/compiere/util/CLogMgt:initialize	(Z)V
    //   298: new 223	org/compiere/util/CLogFile
    //   301: dup
    //   302: getstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   305: bipush 69
    //   307: aaload
    //   308: invokestatic 225	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   311: iconst_0
    //   312: iconst_0
    //   313: invokespecial 226	org/compiere/util/CLogFile:<init>	(Ljava/lang/String;ZZ)V
    //   316: astore 6
    //   318: aload 6
    //   320: invokestatic 227	org/compiere/util/CLogMgt:addHandler	(Ljava/util/logging/Handler;)V
    //   323: getstatic 228	java/util/logging/Level:INFO	Ljava/util/logging/Level;
    //   326: invokestatic 229	org/compiere/util/CLogMgt:setLevel	(Ljava/util/logging/Level;)V
    //   329: aload 6
    //   331: invokevirtual 230	java/util/logging/Handler:getLevel	()Ljava/util/logging/Level;
    //   334: invokevirtual 231	java/util/logging/Level:intValue	()I
    //   337: iload 11
    //   339: ifne +21 -> 360
    //   342: getstatic 232	java/util/logging/Level:FINE	Ljava/util/logging/Level;
    //   345: invokevirtual 231	java/util/logging/Level:intValue	()I
    //   348: if_icmple +11 -> 359
    //   351: aload 6
    //   353: getstatic 232	java/util/logging/Level:FINE	Ljava/util/logging/Level;
    //   356: invokevirtual 233	java/util/logging/Handler:setLevel	(Ljava/util/logging/Level;)V
    //   359: iconst_1
    //   360: invokestatic 234	org/compiere/util/CLogErrorBuffer:get	(Z)Lorg/compiere/util/CLogErrorBuffer;
    //   363: astore 7
    //   365: aload 7
    //   367: iload 11
    //   369: ifne +8 -> 377
    //   372: ifnull +22 -> 394
    //   375: aload 7
    //   377: iload 11
    //   379: ifne +11 -> 390
    //   382: invokevirtual 235	org/compiere/util/CLogErrorBuffer:isIssueError	()Z
    //   385: ifeq +9 -> 394
    //   388: aload 7
    //   390: iconst_0
    //   391: invokevirtual 236	org/compiere/util/CLogErrorBuffer:setIssueError	(Z)V
    //   394: new 237	com/compiere/client/CommandLineInstall
    //   397: dup
    //   398: aload_1
    //   399: aload_2
    //   400: aload_3
    //   401: iload 4
    //   403: iload 5
    //   405: invokespecial 238	com/compiere/client/CommandLineInstall:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V
    //   408: astore 8
    //   410: iconst_0
    //   411: istore 9
    //   413: aload 8
    //   415: invokevirtual 239	com/compiere/client/CommandLineInstall:d	()Z
    //   418: istore 9
    //   420: goto +24 -> 444
    //   423: astore 10
    //   425: iconst_0
    //   426: istore 9
    //   428: getstatic 1	com/compiere/client/CommandLineInstall:a	Lorg/compiere/util/CLogger;
    //   431: aload 10
    //   433: invokevirtual 196	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   436: invokevirtual 21	org/compiere/util/CLogger:info	(Ljava/lang/String;)V
    //   439: aload 10
    //   441: invokevirtual 240	java/lang/Exception:printStackTrace	()V
    //   444: iload 9
    //   446: iload 11
    //   448: ifne +7 -> 455
    //   451: ifne +7 -> 458
    //   454: iconst_1
    //   455: invokestatic 241	java/lang/System:exit	(I)V
    //   458: return
    //
    // Exception table:
    //   from	to	target	type
    //   413	420	423	java/lang/Exception
  }

  private static void e()
  {
    a.severe(z[74]);
    a.severe(z[73]);
    a.severe(z[75]);
    a.severe(z[72]);
  }

  private void b(String paramString)
  {
    a.info(paramString);
  }

  boolean f()
  {
    int i4 = SysEnv.m;
    String str1 = this.d;
    System.setProperty(z[33], str1);
    a.info(str1);
    b(z[62]);
    int i1 = -1;
    PrintStream localPrintStream1 = System.out;
    PrintStream localPrintStream2 = System.err;
    PipedInputStream localPipedInputStream = null;
    PipedOutputStream localPipedOutputStream = null;
    try
    {
      localPipedInputStream = new PipedInputStream();
      localPipedOutputStream = new PipedOutputStream(localPipedInputStream);
      PrintStream localPrintStream3 = new PrintStream(localPipedOutputStream);
      f localf = new f(this, localPrintStream3, str1);
      localf.start();
      byte[] arrayOfByte = new byte[10000];
      while (true)
        try
        {
          int i2 = localPipedInputStream.read(arrayOfByte);
          if (i2 >= 0)
            continue;
          if (i4 != 0)
            break label243;
          if (i4 == 0)
            break;
          String str2 = new String(arrayOfByte, 0, i2);
          if (i4 != 0)
            continue;
          if (str2.indexOf(m) == -1)
            continue;
          int i3 = str2.indexOf(m);
          i1 = Integer.parseInt(str2.substring(i3 + n, i3 + n + 1));
          str2 = str2.substring(0, i3);
          b(str2);
          if (i4 == 0)
            break;
          b(str2);
          continue;
        }
        catch (IOException localIOException)
        {
        }
    }
    catch (Exception localException3)
    {
      label243: b(localException2.toString());
    }
    finally
    {
      System.setOut(localPrintStream1);
      System.setErr(localPrintStream2);
      try
      {
        if ((i4 != 0) || (localPipedOutputStream != null))
          localPipedOutputStream.close();
        if ((i4 != 0) || (localPipedInputStream != null))
          localPipedInputStream.close();
      }
      catch (Exception localException4)
      {
      }
    }
    b(z[63]);
    if (i4 == 0);
    return i1 == 0;
  }

  boolean g()
  {
    int i1 = SysEnv.m;
    boolean bool = false;
    String str1 = this.c.getProperty(z[0]);
    String str2 = this.c.getProperty(z[8]);
    String str3 = this.c.getProperty(z[10]);
    String str4 = this.c.getProperty(z[20]);
    String str5 = this.c.getProperty(z[9]);
    String str6 = this.c.getProperty(z[3]);
    String str7 = this.c.getProperty(z[5]);
    b(z[37] + str6 + " " + str4 + ")");
    Connection localConnection = null;
    try
    {
      String str8 = null;
      if ((i1 != 0) || (!z[22].equals(str1)))
      {
        if (i1 != 0)
          break label279;
        if (!z[19].equals(str1));
      }
      else
      {
        localObject1 = new OracleDriver();
        DriverManager.registerDriver((java.sql.Driver)localObject1);
        str8 = z[21] + str6 + ":" + str7 + "/" + str3;
        localConnection = DriverManager.getConnection(str8, z[25], str2);
        break label474;
      }
      label279: if (i1 == 0)
        if (z[2].equals(str1))
        {
          localObject1 = new com.edb.Driver();
          DriverManager.registerDriver((java.sql.Driver)localObject1);
          str8 = z[6] + str6 + ":" + str7 + z[11];
          localConnection = DriverManager.getConnection(str8, z[13], str2);
          break label474;
        }
      if (z[17].equals(str1))
      {
        localObject1 = new SQLServerDriver();
        DriverManager.registerDriver((java.sql.Driver)localObject1);
        str8 = z[15] + str6 + ":" + str7;
        localConnection = DriverManager.getConnection(str8, z[18], str2);
      }
      else
      {
        throw new IllegalArgumentException(z[12] + str1);
      }
      label474: a.info(str8);
      Object localObject1 = new DatabaseCreate(localConnection, str1, str3, str4, str5, a, this.g);
      ((DatabaseCreate)localObject1).run();
      bool = ((DatabaseCreate)localObject1).isSuccess();
      localConnection.close();
    }
    catch (Exception localException1)
    {
      a.severe(localException1.toString());
      if (i1 != 0)
        break label566;
    }
    bool = false;
    if (localConnection != null)
      try
      {
        localConnection.close();
      }
      catch (Exception localException2)
      {
      }
    label566: if (i1 == 0)
    {
      if (bool)
      {
        a.info("");
        String str9 = this.c.getProperty(z[33]);
        String str10 = str9 + File.separator + z[41];
        HashMap localHashMap = new HashMap();
        localHashMap.put(z[0], this.c.getProperty(z[0]));
        localHashMap.put(z[34], this.c.getProperty(z[34]));
        localHashMap.put(z[36], this.c.getProperty(z[36]));
        a.info(this.k.b(true));
        a(this.c, localHashMap);
        Object localObject2 = null;
        try
        {
          URL localURL = new URL(Support.a());
          ClassLoader localClassLoader = Thread.currentThread().getContextClassLoader();
          URLClassLoader localURLClassLoader = new URLClassLoader(new URL[] { localURL }, localClassLoader);
          Class localClass = null;
          if ((i1 != 0) || (localURLClassLoader != null))
            localClass = localURLClassLoader.loadClass(z[40]);
          Object localObject3 = localClass.newInstance();
          Class[] arrayOfClass = { String.class, Object.class };
          Object[] arrayOfObject = { str10, this.k };
          Method localMethod = localClass.getMethod(z[35], arrayOfClass);
          localObject2 = localMethod.invoke(localObject3, arrayOfObject);
          a.info(localObject2.toString());
          if ((i1 != 0) || (!localObject2.toString().startsWith(z[32])))
            return false;
        }
        catch (Exception localException3)
        {
          a.severe(z[38] + localException3.getMessage());
          return false;
        }
      }
      a.info(z[39]);
    }
    return true;
  }

  private void h()
  {
    int i1 = SysEnv.m;
    String str1 = Ini.getCompiereHome() + File.separator + z[24];
    File localFile = new File(str1);
    Properties localProperties = new Properties();
    if (localFile.exists())
    {
      try
      {
        FileInputStream localFileInputStream = new FileInputStream(localFile);
        localProperties.load(localFileInputStream);
        localFileInputStream.close();
      }
      catch (Exception localException1)
      {
        a.warning(localException1.toString());
      }
      a.info(localFile.toString());
    }
    try
    {
      URL localURL = new URL(Support.a());
      ClassLoader localClassLoader = Thread.currentThread().getContextClassLoader();
      URLClassLoader localURLClassLoader = new URLClassLoader(new URL[] { localURL }, localClassLoader);
      Class localClass = null;
      if (localURLClassLoader != null)
      {
        if ((i1 != 0) || (a != null))
          a.config(z[47]);
        localClass = localURLClassLoader.loadClass(z[46]);
        if ((i1 != 0) || (a != null))
          a.config(z[43]);
      }
      if (localClass != null)
      {
        if ((i1 != 0) || (a != null))
          a.config(z[42]);
        Object localObject = null;
        List localList = this.k.get(false);
        CConnection localCConnection = CConnection.get();
        String str2 = localCConnection.getConnectionURL();
        String str3 = localCConnection.getDbUid();
        String str4 = localCConnection.getDbPwd();
        if (localList != null)
        {
          Constructor localConstructor = localClass.getConstructor(new Class[] { List.class, String.class, String.class, String.class });
          if (i1 == 0)
            if (a != null)
              a.config(z[44]);
          localObject = localConstructor.newInstance(new Object[] { localList, str2, str3, str4 });
        }
        localObject.toString();
      }
    }
    catch (Exception localException2)
    {
      a.severe(z[45]);
    }
  }

  private void a(Properties paramProperties, Map paramMap)
  {
    if (paramMap == null)
      paramMap = new HashMap();
    String str1 = paramProperties.getProperty(z[1]);
    String str2 = paramProperties.getProperty(z[0]);
    c localc = new c(a);
    localc.a(null, null, null, -1, null, null, str1, str2, null, null, -1, null, null, null);
  }

  static CLogger i()
  {
    return a;
  }

  static
  {
    // Byte code:
    //   0: bipush 87
    //   2: anewarray 261	java/lang/String
    //   5: dup
    //   6: iconst_0
    //   7: ldc_w 263
    //   10: bipush 86
    //   12: goto +971 -> 983
    //   15: putstatic 901	com/compiere/client/CommandLineInstall:m	Ljava/lang/String;
    //   18: ldc 22
    //   20: bipush 255
    //   22: goto +961 -> 983
    //   25: aastore
    //   26: dup
    //   27: iconst_1
    //   28: ldc_w 316
    //   31: iconst_0
    //   32: goto +951 -> 983
    //   35: aastore
    //   36: dup
    //   37: iconst_2
    //   38: ldc 41
    //   40: iconst_1
    //   41: goto +942 -> 983
    //   44: aastore
    //   45: dup
    //   46: iconst_3
    //   47: ldc 28
    //   49: iconst_2
    //   50: goto +933 -> 983
    //   53: aastore
    //   54: dup
    //   55: iconst_4
    //   56: ldc 67
    //   58: iconst_3
    //   59: goto +924 -> 983
    //   62: aastore
    //   63: dup
    //   64: iconst_5
    //   65: ldc 29
    //   67: iconst_4
    //   68: goto +915 -> 983
    //   71: aastore
    //   72: dup
    //   73: bipush 6
    //   75: ldc 44
    //   77: iconst_5
    //   78: goto +905 -> 983
    //   81: aastore
    //   82: dup
    //   83: bipush 7
    //   85: ldc 57
    //   87: bipush 6
    //   89: goto +894 -> 983
    //   92: aastore
    //   93: dup
    //   94: bipush 8
    //   96: ldc 24
    //   98: bipush 7
    //   100: goto +883 -> 983
    //   103: aastore
    //   104: dup
    //   105: bipush 9
    //   107: ldc 27
    //   109: bipush 8
    //   111: goto +872 -> 983
    //   114: aastore
    //   115: dup
    //   116: bipush 10
    //   118: ldc 25
    //   120: bipush 9
    //   122: goto +861 -> 983
    //   125: aastore
    //   126: dup
    //   127: bipush 11
    //   129: ldc 45
    //   131: bipush 10
    //   133: goto +850 -> 983
    //   136: aastore
    //   137: dup
    //   138: bipush 12
    //   140: ldc 53
    //   142: bipush 11
    //   144: goto +839 -> 983
    //   147: aastore
    //   148: dup
    //   149: bipush 13
    //   151: ldc 46
    //   153: bipush 12
    //   155: goto +828 -> 983
    //   158: aastore
    //   159: dup
    //   160: bipush 14
    //   162: ldc 69
    //   164: bipush 13
    //   166: goto +817 -> 983
    //   169: aastore
    //   170: dup
    //   171: bipush 15
    //   173: ldc 50
    //   175: bipush 14
    //   177: goto +806 -> 983
    //   180: aastore
    //   181: dup
    //   182: bipush 16
    //   184: ldc 63
    //   186: bipush 15
    //   188: goto +795 -> 983
    //   191: aastore
    //   192: dup
    //   193: bipush 17
    //   195: ldc 47
    //   197: bipush 16
    //   199: goto +784 -> 983
    //   202: aastore
    //   203: dup
    //   204: bipush 18
    //   206: ldc 51
    //   208: bipush 17
    //   210: goto +773 -> 983
    //   213: aastore
    //   214: dup
    //   215: bipush 19
    //   217: ldc 32
    //   219: bipush 18
    //   221: goto +762 -> 983
    //   224: aastore
    //   225: dup
    //   226: bipush 20
    //   228: ldc 26
    //   230: bipush 19
    //   232: goto +751 -> 983
    //   235: aastore
    //   236: dup
    //   237: bipush 21
    //   239: ldc 36
    //   241: bipush 20
    //   243: goto +740 -> 983
    //   246: aastore
    //   247: dup
    //   248: bipush 22
    //   250: ldc 30
    //   252: bipush 21
    //   254: goto +729 -> 983
    //   257: aastore
    //   258: dup
    //   259: bipush 23
    //   261: ldc 61
    //   263: bipush 22
    //   265: goto +718 -> 983
    //   268: aastore
    //   269: dup
    //   270: bipush 24
    //   272: ldc 7
    //   274: bipush 23
    //   276: goto +707 -> 983
    //   279: aastore
    //   280: dup
    //   281: bipush 25
    //   283: ldc 39
    //   285: bipush 24
    //   287: goto +696 -> 983
    //   290: aastore
    //   291: dup
    //   292: bipush 26
    //   294: ldc 141
    //   296: bipush 25
    //   298: goto +685 -> 983
    //   301: aastore
    //   302: dup
    //   303: bipush 27
    //   305: ldc 153
    //   307: bipush 26
    //   309: goto +674 -> 983
    //   312: aastore
    //   313: dup
    //   314: bipush 28
    //   316: ldc 203
    //   318: bipush 27
    //   320: goto +663 -> 983
    //   323: aastore
    //   324: dup
    //   325: bipush 29
    //   327: ldc 208
    //   329: bipush 28
    //   331: goto +652 -> 983
    //   334: aastore
    //   335: dup
    //   336: bipush 30
    //   338: ldc 183
    //   340: bipush 29
    //   342: goto +641 -> 983
    //   345: aastore
    //   346: dup
    //   347: bipush 31
    //   349: ldc 210
    //   351: bipush 30
    //   353: goto +630 -> 983
    //   356: aastore
    //   357: dup
    //   358: bipush 32
    //   360: ldc_w 301
    //   363: bipush 31
    //   365: goto +618 -> 983
    //   368: aastore
    //   369: dup
    //   370: bipush 33
    //   372: ldc 246
    //   374: bipush 32
    //   376: goto +607 -> 983
    //   379: aastore
    //   380: dup
    //   381: bipush 34
    //   383: ldc_w 281
    //   386: bipush 33
    //   388: goto +595 -> 983
    //   391: aastore
    //   392: dup
    //   393: bipush 35
    //   395: ldc_w 297
    //   398: bipush 34
    //   400: goto +583 -> 983
    //   403: aastore
    //   404: dup
    //   405: bipush 36
    //   407: ldc_w 282
    //   410: bipush 35
    //   412: goto +571 -> 983
    //   415: aastore
    //   416: dup
    //   417: bipush 37
    //   419: ldc_w 272
    //   422: bipush 36
    //   424: goto +559 -> 983
    //   427: aastore
    //   428: dup
    //   429: bipush 38
    //   431: ldc_w 302
    //   434: bipush 37
    //   436: goto +547 -> 983
    //   439: aastore
    //   440: dup
    //   441: bipush 39
    //   443: ldc_w 303
    //   446: bipush 38
    //   448: goto +535 -> 983
    //   451: aastore
    //   452: dup
    //   453: bipush 40
    //   455: ldc_w 292
    //   458: bipush 39
    //   460: goto +523 -> 983
    //   463: aastore
    //   464: dup
    //   465: bipush 41
    //   467: ldc 98
    //   469: bipush 40
    //   471: goto +512 -> 983
    //   474: aastore
    //   475: dup
    //   476: bipush 42
    //   478: ldc_w 307
    //   481: bipush 41
    //   483: goto +500 -> 983
    //   486: aastore
    //   487: dup
    //   488: bipush 43
    //   490: ldc_w 306
    //   493: bipush 42
    //   495: goto +488 -> 983
    //   498: aastore
    //   499: dup
    //   500: bipush 44
    //   502: ldc_w 313
    //   505: bipush 43
    //   507: goto +476 -> 983
    //   510: aastore
    //   511: dup
    //   512: bipush 45
    //   514: ldc_w 315
    //   517: bipush 44
    //   519: goto +464 -> 983
    //   522: aastore
    //   523: dup
    //   524: bipush 46
    //   526: ldc_w 305
    //   529: bipush 45
    //   531: goto +452 -> 983
    //   534: aastore
    //   535: dup
    //   536: bipush 47
    //   538: ldc_w 304
    //   541: bipush 46
    //   543: goto +440 -> 983
    //   546: aastore
    //   547: dup
    //   548: bipush 48
    //   550: ldc 118
    //   552: bipush 47
    //   554: goto +429 -> 983
    //   557: aastore
    //   558: dup
    //   559: bipush 49
    //   561: ldc 130
    //   563: bipush 48
    //   565: goto +418 -> 983
    //   568: aastore
    //   569: dup
    //   570: bipush 50
    //   572: ldc 131
    //   574: bipush 49
    //   576: goto +407 -> 983
    //   579: aastore
    //   580: dup
    //   581: bipush 51
    //   583: ldc 126
    //   585: bipush 50
    //   587: goto +396 -> 983
    //   590: aastore
    //   591: dup
    //   592: bipush 52
    //   594: ldc 108
    //   596: bipush 51
    //   598: goto +385 -> 983
    //   601: aastore
    //   602: dup
    //   603: bipush 53
    //   605: ldc 127
    //   607: bipush 52
    //   609: goto +374 -> 983
    //   612: aastore
    //   613: dup
    //   614: bipush 54
    //   616: ldc 107
    //   618: bipush 53
    //   620: goto +363 -> 983
    //   623: aastore
    //   624: dup
    //   625: bipush 55
    //   627: ldc 135
    //   629: bipush 54
    //   631: goto +352 -> 983
    //   634: aastore
    //   635: dup
    //   636: bipush 56
    //   638: ldc 129
    //   640: bipush 55
    //   642: goto +341 -> 983
    //   645: aastore
    //   646: dup
    //   647: bipush 57
    //   649: ldc 106
    //   651: bipush 56
    //   653: goto +330 -> 983
    //   656: aastore
    //   657: dup
    //   658: bipush 58
    //   660: ldc 133
    //   662: bipush 57
    //   664: goto +319 -> 983
    //   667: aastore
    //   668: dup
    //   669: bipush 59
    //   671: ldc 134
    //   673: bipush 58
    //   675: goto +308 -> 983
    //   678: aastore
    //   679: dup
    //   680: bipush 60
    //   682: ldc 117
    //   684: bipush 59
    //   686: goto +297 -> 983
    //   689: aastore
    //   690: dup
    //   691: bipush 61
    //   693: ldc 132
    //   695: bipush 60
    //   697: goto +286 -> 983
    //   700: aastore
    //   701: dup
    //   702: bipush 62
    //   704: ldc 248
    //   706: bipush 61
    //   708: goto +275 -> 983
    //   711: aastore
    //   712: dup
    //   713: bipush 63
    //   715: ldc_w 271
    //   718: bipush 62
    //   720: goto +263 -> 983
    //   723: aastore
    //   724: dup
    //   725: bipush 64
    //   727: ldc 94
    //   729: bipush 63
    //   731: goto +252 -> 983
    //   734: aastore
    //   735: dup
    //   736: bipush 65
    //   738: ldc 216
    //   740: bipush 64
    //   742: goto +241 -> 983
    //   745: aastore
    //   746: dup
    //   747: bipush 66
    //   749: ldc 220
    //   751: bipush 65
    //   753: goto +230 -> 983
    //   756: aastore
    //   757: dup
    //   758: bipush 67
    //   760: ldc 217
    //   762: bipush 66
    //   764: goto +219 -> 983
    //   767: aastore
    //   768: dup
    //   769: bipush 68
    //   771: ldc 213
    //   773: bipush 67
    //   775: goto +208 -> 983
    //   778: aastore
    //   779: dup
    //   780: bipush 69
    //   782: ldc 224
    //   784: bipush 68
    //   786: goto +197 -> 983
    //   789: aastore
    //   790: dup
    //   791: bipush 70
    //   793: ldc 215
    //   795: bipush 69
    //   797: goto +186 -> 983
    //   800: aastore
    //   801: dup
    //   802: bipush 71
    //   804: ldc 221
    //   806: bipush 70
    //   808: goto +175 -> 983
    //   811: aastore
    //   812: dup
    //   813: bipush 72
    //   815: ldc 245
    //   817: bipush 71
    //   819: goto +164 -> 983
    //   822: aastore
    //   823: dup
    //   824: bipush 73
    //   826: ldc 243
    //   828: bipush 72
    //   830: goto +153 -> 983
    //   833: aastore
    //   834: dup
    //   835: bipush 74
    //   837: ldc 242
    //   839: bipush 73
    //   841: goto +142 -> 983
    //   844: aastore
    //   845: dup
    //   846: bipush 75
    //   848: ldc 244
    //   850: bipush 74
    //   852: goto +131 -> 983
    //   855: aastore
    //   856: dup
    //   857: bipush 76
    //   859: ldc 157
    //   861: bipush 75
    //   863: goto +120 -> 983
    //   866: aastore
    //   867: dup
    //   868: bipush 77
    //   870: ldc 160
    //   872: bipush 76
    //   874: goto +109 -> 983
    //   877: aastore
    //   878: dup
    //   879: bipush 78
    //   881: ldc 110
    //   883: bipush 77
    //   885: goto +98 -> 983
    //   888: aastore
    //   889: dup
    //   890: bipush 79
    //   892: ldc 114
    //   894: bipush 78
    //   896: goto +87 -> 983
    //   899: aastore
    //   900: dup
    //   901: bipush 80
    //   903: ldc 99
    //   905: bipush 79
    //   907: goto +76 -> 983
    //   910: aastore
    //   911: dup
    //   912: bipush 81
    //   914: ldc 104
    //   916: bipush 80
    //   918: goto +65 -> 983
    //   921: aastore
    //   922: dup
    //   923: bipush 82
    //   925: ldc 85
    //   927: bipush 81
    //   929: goto +54 -> 983
    //   932: aastore
    //   933: dup
    //   934: bipush 83
    //   936: ldc 83
    //   938: bipush 82
    //   940: goto +43 -> 983
    //   943: aastore
    //   944: dup
    //   945: bipush 84
    //   947: ldc 74
    //   949: bipush 83
    //   951: goto +32 -> 983
    //   954: aastore
    //   955: dup
    //   956: bipush 85
    //   958: ldc 77
    //   960: bipush 84
    //   962: goto +21 -> 983
    //   965: aastore
    //   966: dup
    //   967: bipush 86
    //   969: ldc 73
    //   971: bipush 85
    //   973: goto +10 -> 983
    //   976: aastore
    //   977: putstatic 913	com/compiere/client/CommandLineInstall:z	[Ljava/lang/String;
    //   980: goto +476 -> 1456
    //   983: swap
    //   984: invokevirtual 905	java/lang/String:toCharArray	()[C
    //   987: dup
    //   988: arraylength
    //   989: swap
    //   990: iconst_0
    //   991: istore_0
    //   992: swap
    //   993: dup_x1
    //   994: iconst_1
    //   995: if_icmpgt +79 -> 1074
    //   998: dup
    //   999: iload_0
    //   1000: dup2
    //   1001: caload
    //   1002: iload_0
    //   1003: iconst_5
    //   1004: irem
    //   1005: tableswitch	default:+51 -> 1056, 0:+31->1036, 1:+36->1041, 2:+41->1046, 3:+46->1051
    //   1037: lstore 167
    //   1039: nop
    //   1040: ldc2_w 4136
    //   1043: goto +15 -> 1058
    //   1046: bipush 77
    //   1048: goto +10 -> 1058
    //   1051: bipush 38
    //   1053: goto +5 -> 1058
    //   1056: bipush 9
    //   1058: ixor
    //   1059: i2c
    //   1060: castore
    //   1061: iinc 0 1
    //   1064: swap
    //   1065: dup_x1
    //   1066: ifne +8 -> 1074
    //   1069: dup2
    //   1070: swap
    //   1071: goto -71 -> 1000
    //   1074: swap
    //   1075: dup_x1
    //   1076: iload_0
    //   1077: if_icmpgt -79 -> 998
    //   1080: new 261	java/lang/String
    //   1083: dup_x1
    //   1084: swap
    //   1085: invokespecial 908	java/lang/String:<init>	([C)V
    //   1088: invokevirtual 911	java/lang/String:intern	()Ljava/lang/String;
    //   1091: swap
    //   1092: pop
    //   1093: swap
    //   1094: tableswitch	default:+-1069 -> 25, 0:+-1059->35, 1:+-1050->44, 2:+-1041->53, 3:+-1032->62, 4:+-1023->71, 5:+-1013->81, 6:+-1002->92, 7:+-991->103, 8:+-980->114, 9:+-969->125, 10:+-958->136, 11:+-947->147, 12:+-936->158, 13:+-925->169, 14:+-914->180, 15:+-903->191, 16:+-892->202, 17:+-881->213, 18:+-870->224, 19:+-859->235, 20:+-848->246, 21:+-837->257, 22:+-826->268, 23:+-815->279, 24:+-804->290, 25:+-793->301, 26:+-782->312, 27:+-771->323, 28:+-760->334, 29:+-749->345, 30:+-738->356, 31:+-726->368, 32:+-715->379, 33:+-703->391, 34:+-691->403, 35:+-679->415, 36:+-667->427, 37:+-655->439, 38:+-643->451, 39:+-631->463, 40:+-620->474, 41:+-608->486, 42:+-596->498, 43:+-584->510, 44:+-572->522, 45:+-560->534, 46:+-548->546, 47:+-537->557, 48:+-526->568, 49:+-515->579, 50:+-504->590, 51:+-493->601, 52:+-482->612, 53:+-471->623, 54:+-460->634, 55:+-449->645, 56:+-438->656, 57:+-427->667, 58:+-416->678, 59:+-405->689, 60:+-394->700, 61:+-383->711, 62:+-371->723, 63:+-360->734, 64:+-349->745, 65:+-338->756, 66:+-327->767, 67:+-316->778, 68:+-305->789, 69:+-294->800, 70:+-283->811, 71:+-272->822, 72:+-261->833, 73:+-250->844, 74:+-239->855, 75:+-228->866, 76:+-217->877, 77:+-206->888, 78:+-195->899, 79:+-184->910, 80:+-173->921, 81:+-162->932, 82:+-151->943, 83:+-140->954, 84:+-129->965, 85:+-118->976, 86:+-1079->15
    //   1457: aconst_null
    //   1458: istore_3
    //   1459: putstatic 1	com/compiere/client/CommandLineInstall:a	Lorg/compiere/util/CLogger;
    //   1462: getstatic 901	com/compiere/client/CommandLineInstall:m	Ljava/lang/String;
    //   1465: invokevirtual 116	java/lang/String:length	()I
    //   1468: putstatic 264	com/compiere/client/CommandLineInstall:n	I
    //   1471: return
  }
}