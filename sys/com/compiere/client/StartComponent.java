package com.compiere.client;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import org.compiere.model.MDataMigration;
import org.compiere.model.MEntityType;
import org.compiere.model.MSystem;
import org.compiere.util.CLogErrorBuffer;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

public final class StartComponent
{
  private static CLogger a;
  private static final String b;
  private static final String[] z;

  public StartComponent()
  {
    CLogErrorBuffer localCLogErrorBuffer = CLogErrorBuffer.get(true);
    if (((i != 0) || (localCLogErrorBuffer != null)) && ((i != 0) || (localCLogErrorBuffer.isIssueError())))
      localCLogErrorBuffer.setIssueError(false);
  }

  private boolean a()
  {
    int i = SysEnv.m;
    try
    {
      String str = InetAddress.getLocalHost().toString().toUpperCase();
      if ((i != 0) || ((str.equals(z[13])) || ((i != 0) || ((str.equals(z[16])) || ((i != 0) || ((str.equals(z[14])) || ((i != 0) || (str.equals(z[15])))))))))
        return true;
    }
    catch (Exception localException)
    {
      return false;
    }
    return false;
  }

  public String createComponent(String paramString1, List paramList, String paramString2)
  {
    int i = SysEnv.m;
    if (i == 0)
      if (!paramString1.equals("D"))
      {
        if (i != 0)
          break label49;
        if (!paramString1.startsWith("C"))
          break label40;
      }
    return z[12];
    label40: if ((i != 0) || (!a()))
    {
      label49: localObject1 = a(paramString1);
      if ((i != 0) || (((String)localObject1).startsWith(z[3])))
        return localObject1;
    }
    Object localObject1 = null;
    try
    {
      URL localURL = new URL(Support.a());
      ClassLoader localClassLoader = Thread.currentThread().getContextClassLoader();
      URLClassLoader localURLClassLoader = new URLClassLoader(new URL[] { localURL }, localClassLoader);
      Class localClass = null;
      if ((i != 0) || (localURLClassLoader != null))
        localClass = localURLClassLoader.loadClass(z[9]);
      Object localObject2 = localClass.newInstance();
      Class[] arrayOfClass = { String.class, List.class, String.class };
      Object[] arrayOfObject = { paramString1, paramList, paramString2 };
      Method localMethod = localClass.getMethod(z[11], arrayOfClass);
      localObject1 = localMethod.invoke(localObject2, arrayOfObject);
    }
    catch (Exception localException)
    {
    }
    if ((i != 0) || (localObject1 != null))
      return ((Object)localObject1).toString();
    return (String)null;
  }

  private String a(String paramString)
  {
    MSystem localMSystem = MSystem.get(Env.getCtx());
    int i = 0;
    if (!b.equals(paramString))
    {
      localObject = MEntityType.getEntityType(Env.getCtx(), paramString);
      if ((SysEnv.m != 0) || (localObject != null))
        i = ((MEntityType)localObject).getRecord_ID();
    }
    Object localObject = new c(a);
    boolean bool = ((c)localObject).b(localMSystem.getName(), localMSystem.getUserName(), localMSystem.getPassword(), paramString, Integer.toString(i), localMSystem.getDBAddress(true), localMSystem.getDBInstance());
    String str = null;
    if (!bool)
      str = z[8];
    else
      str = ((c)localObject).a();
    return (String)str;
  }

  private String b(String paramString)
  {
    MSystem localMSystem = MSystem.get(Env.getCtx());
    int i = 0;
    if (!b.equals(paramString))
    {
      localObject = MEntityType.getEntityType(Env.getCtx(), paramString);
      if ((SysEnv.m != 0) || (localObject != null))
        i = ((MEntityType)localObject).getRecord_ID();
    }
    Object localObject = new c(a);
    boolean bool = ((c)localObject).a(localMSystem.getName(), localMSystem.getUserName(), localMSystem.getPassword(), paramString, Integer.toString(i), localMSystem.getDBAddress(true), localMSystem.getDBInstance());
    String str = null;
    if (!bool)
      str = z[8];
    else
      str = ((c)localObject).a();
    return (String)str;
  }

  public String removeComponent(String paramString, Boolean paramBoolean1, Boolean paramBoolean2)
  {
    int i = SysEnv.m;
    Object localObject1 = null;
    try
    {
      URL localURL = new URL(Support.a());
      ClassLoader localClassLoader = Thread.currentThread().getContextClassLoader();
      URLClassLoader localURLClassLoader = new URLClassLoader(new URL[] { localURL }, localClassLoader);
      Class localClass = null;
      if ((i != 0) || (localURLClassLoader != null))
        localClass = localURLClassLoader.loadClass(z[9]);
      Object localObject2 = localClass.newInstance();
      Class[] arrayOfClass = { String.class, Boolean.class, Boolean.class };
      Object[] arrayOfObject = { paramString, paramBoolean1, paramBoolean2 };
      Method localMethod = localClass.getMethod(z[17], arrayOfClass);
      localObject1 = localMethod.invoke(localObject2, arrayOfObject);
    }
    catch (Exception localException)
    {
    }
    if ((i != 0) || (localObject1 != null))
      return localObject1.toString();
    return null;
  }

  public String createDataMigration(MDataMigration paramMDataMigration, String paramString1, String paramString2)
  {
    int i = SysEnv.m;
    if ((i != 0) || (!a()))
    {
      localObject1 = a(b);
      if ((i != 0) || (((String)localObject1).startsWith(z[3])))
        return localObject1;
    }
    Object localObject1 = null;
    try
    {
      URL localURL = new URL(Support.a());
      ClassLoader localClassLoader = Thread.currentThread().getContextClassLoader();
      URLClassLoader localURLClassLoader = new URLClassLoader(new URL[] { localURL }, localClassLoader);
      Class localClass = null;
      if ((i != 0) || (localURLClassLoader != null))
        localClass = localURLClassLoader.loadClass(z[9]);
      Object localObject2 = localClass.newInstance();
      Class[] arrayOfClass = { MDataMigration.class, String.class, String.class };
      Object[] arrayOfObject = { paramMDataMigration, paramString1, paramString2 };
      Method localMethod = localClass.getMethod(z[11], arrayOfClass);
      localObject1 = localMethod.invoke(localObject2, arrayOfObject);
    }
    catch (Exception localException)
    {
    }
    if ((i != 0) || (localObject1 != null))
      return ((Object)localObject1).toString();
    return (String)null;
  }

  public String createDataMigrationPreview(MDataMigration paramMDataMigration, Boolean paramBoolean)
  {
    int i = SysEnv.m;
    if ((i != 0) || (!a()))
    {
      localObject1 = a(b);
      if ((i != 0) || (((String)localObject1).startsWith(z[3])))
        return localObject1;
    }
    Object localObject1 = null;
    try
    {
      URL localURL = new URL(Support.a());
      ClassLoader localClassLoader = Thread.currentThread().getContextClassLoader();
      URLClassLoader localURLClassLoader = new URLClassLoader(new URL[] { localURL }, localClassLoader);
      Class localClass = null;
      if ((i != 0) || (localURLClassLoader != null))
        localClass = localURLClassLoader.loadClass(z[9]);
      Object localObject2 = localClass.newInstance();
      Class[] arrayOfClass = { MDataMigration.class, Boolean.class };
      Object[] arrayOfObject = { paramMDataMigration, paramBoolean };
      Method localMethod = localClass.getMethod(z[10], arrayOfClass);
      localObject1 = localMethod.invoke(localObject2, arrayOfObject);
    }
    catch (Exception localException)
    {
    }
    if ((i != 0) || (localObject1 != null))
      return ((Object)localObject1).toString();
    return (String)null;
  }

  public String importDataMigration(String paramString, Integer paramInteger)
  {
    int j = SysEnv.m;
    if ((j != 0) || ((paramString == null) || ((j != 0) || (!paramString.endsWith(z[0])))))
      return z[6] + paramString;
    File localFile = new File(paramString);
    if (j == 0)
      if (!localFile.exists())
        return z[1] + paramString;
    if (!localFile.isFile())
      return z[4] + paramString;
    String str1 = null;
    try
    {
      URL localURL1 = localFile.toURI().toURL();
      str1 = localURL1.toString();
    }
    catch (Exception localException1)
    {
      return localException1.getMessage() + z[5] + paramString;
    }
    int i = 0;
    if ((j != 0) || (paramInteger != null))
      i = paramInteger.intValue();
    if ((j != 0) || (!a()))
    {
      String str2 = b(b);
      if ((j != 0) || (str2.startsWith(z[3])))
        return str2;
    }
    try
    {
      URL localURL2 = new URL(Support.a());
      ClassLoader localClassLoader = Thread.currentThread().getContextClassLoader();
      URLClassLoader localURLClassLoader = new URLClassLoader(new URL[] { localURL2 }, localClassLoader);
      Class localClass = null;
      if ((j != 0) || (localURLClassLoader != null))
        localClass = localURLClassLoader.loadClass(z[7]);
      Constructor localConstructor = localClass.getConstructor(new Class[] { String.class, Integer.TYPE });
      Object localObject = localConstructor.newInstance(new Object[] { str1, Integer.valueOf(i) });
      if (localObject == null)
        return z[2];
    }
    catch (Exception localException2)
    {
    }
    return null;
  }

  static
  {
    // Byte code:
    //   0: bipush 18
    //   2: anewarray 32	java/lang/String
    //   5: dup
    //   6: iconst_0
    //   7: ldc 41
    //   9: bipush 17
    //   11: goto +196 -> 207
    //   14: putstatic 308	com/compiere/client/StartComponent:b	Ljava/lang/String;
    //   17: ldc 61
    //   19: bipush 255
    //   21: goto +186 -> 207
    //   24: aastore
    //   25: dup
    //   26: iconst_1
    //   27: ldc 71
    //   29: iconst_0
    //   30: goto +177 -> 207
    //   33: aastore
    //   34: dup
    //   35: iconst_2
    //   36: ldc 86
    //   38: iconst_1
    //   39: goto +168 -> 207
    //   42: aastore
    //   43: dup
    //   44: iconst_3
    //   45: ldc 20
    //   47: iconst_2
    //   48: goto +159 -> 207
    //   51: aastore
    //   52: dup
    //   53: iconst_4
    //   54: ldc 73
    //   56: iconst_3
    //   57: goto +150 -> 207
    //   60: aastore
    //   61: dup
    //   62: iconst_5
    //   63: ldc 78
    //   65: iconst_4
    //   66: goto +141 -> 207
    //   69: aastore
    //   70: dup
    //   71: bipush 6
    //   73: ldc 65
    //   75: iconst_5
    //   76: goto +131 -> 207
    //   79: aastore
    //   80: dup
    //   81: bipush 7
    //   83: ldc 81
    //   85: bipush 6
    //   87: goto +120 -> 207
    //   90: aastore
    //   91: dup
    //   92: bipush 8
    //   94: ldc 54
    //   96: bipush 7
    //   98: goto +109 -> 207
    //   101: aastore
    //   102: dup
    //   103: bipush 9
    //   105: ldc 28
    //   107: bipush 8
    //   109: goto +98 -> 207
    //   112: aastore
    //   113: dup
    //   114: bipush 10
    //   116: ldc 60
    //   118: bipush 9
    //   120: goto +87 -> 207
    //   123: aastore
    //   124: dup
    //   125: bipush 11
    //   127: ldc 35
    //   129: bipush 10
    //   131: goto +76 -> 207
    //   134: aastore
    //   135: dup
    //   136: bipush 12
    //   138: ldc 17
    //   140: bipush 11
    //   142: goto +65 -> 207
    //   145: aastore
    //   146: dup
    //   147: bipush 13
    //   149: ldc 8
    //   151: bipush 12
    //   153: goto +54 -> 207
    //   156: aastore
    //   157: dup
    //   158: bipush 14
    //   160: ldc 11
    //   162: bipush 13
    //   164: goto +43 -> 207
    //   167: aastore
    //   168: dup
    //   169: bipush 15
    //   171: ldc 12
    //   173: bipush 14
    //   175: goto +32 -> 207
    //   178: aastore
    //   179: dup
    //   180: bipush 16
    //   182: ldc 10
    //   184: bipush 15
    //   186: goto +21 -> 207
    //   189: aastore
    //   190: dup
    //   191: bipush 17
    //   193: ldc 58
    //   195: bipush 16
    //   197: goto +10 -> 207
    //   200: aastore
    //   201: putstatic 320	com/compiere/client/StartComponent:z	[Ljava/lang/String;
    //   204: goto +200 -> 404
    //   207: swap
    //   208: invokevirtual 312	java/lang/String:toCharArray	()[C
    //   211: dup
    //   212: arraylength
    //   213: swap
    //   214: iconst_0
    //   215: istore_0
    //   216: swap
    //   217: dup_x1
    //   218: iconst_1
    //   219: if_icmpgt +79 -> 298
    //   222: dup
    //   223: iload_0
    //   224: dup2
    //   225: caload
    //   226: iload_0
    //   227: iconst_5
    //   228: irem
    //   229: tableswitch	default:+51 -> 280, 0:+31->260, 1:+36->265, 2:+41->270, 3:+46->275
    //   261: pop
    //   262: goto +20 -> 282
    //   265: bipush 91
    //   267: goto +15 -> 282
    //   270: bipush 54
    //   272: goto +10 -> 282
    //   275: bipush 20
    //   277: goto +5 -> 282
    //   280: bipush 69
    //   282: ixor
    //   283: i2c
    //   284: castore
    //   285: iinc 0 1
    //   288: swap
    //   289: dup_x1
    //   290: ifne +8 -> 298
    //   293: dup2
    //   294: swap
    //   295: goto -71 -> 224
    //   298: swap
    //   299: dup_x1
    //   300: iload_0
    //   301: if_icmpgt -79 -> 222
    //   304: new 32	java/lang/String
    //   307: dup_x1
    //   308: swap
    //   309: invokespecial 315	java/lang/String:<init>	([C)V
    //   312: invokevirtual 318	java/lang/String:intern	()Ljava/lang/String;
    //   315: swap
    //   316: pop
    //   317: swap
    //   318: tableswitch	default:+-294 -> 24, 0:+-285->33, 1:+-276->42, 2:+-267->51, 3:+-258->60, 4:+-249->69, 5:+-239->79, 6:+-228->90, 7:+-217->101, 8:+-206->112, 9:+-195->123, 10:+-184->134, 11:+-173->145, 12:+-162->156, 13:+-151->167, 14:+-140->178, 15:+-129->189, 16:+-118->200, 17:+-304->14
    //   405: pop
    //   406: invokestatic 88	org/compiere/util/CLogger:getCLogger	(Ljava/lang/Class;)Lorg/compiere/util/CLogger;
    //   409: putstatic 45	com/compiere/client/StartComponent:a	Lorg/compiere/util/CLogger;
    //   412: return
  }
}