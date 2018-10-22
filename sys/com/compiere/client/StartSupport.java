package com.compiere.client;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.Compiere;
import org.compiere.install.ProgressPanel;
import org.compiere.model.MEntityType;
import org.compiere.model.MSystem;
import org.compiere.swing.PublishInterface;
import org.compiere.util.CLogErrorBuffer;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;

public final class StartSupport
{
  private static CLogger a;
  private static final String[] z;

  public StartSupport()
  {
    CLogErrorBuffer localCLogErrorBuffer = CLogErrorBuffer.get(true);
    if (((i != 0) || (localCLogErrorBuffer != null)) && ((i != 0) || (localCLogErrorBuffer.isIssueError())))
      localCLogErrorBuffer.setIssueError(false);
  }

  public void startMigrate(ProgressPanel paramProgressPanel, Object paramObject)
  {
    try
    {
      Compiere.startup(true, z[1]);
      CLogMgt.setLevel(Level.INFO);
    }
    catch (Exception localException)
    {
      if ((SysEnv.m != 0) || (a != null))
        a.log(Level.SEVERE, z[0], localException);
      System.out.println(localException.toString());
    }
    Support.a(paramObject);
  }

  public String startImport(String paramString, ProgressPanel paramProgressPanel, Object paramObject, Map paramMap)
  {
    int i = SysEnv.m;
    if ((i != 0) || (paramProgressPanel != null))
    {
      if (i != 0)
        break label34;
      if (paramProgressPanel.getProperties() != null);
    }
    else
    {
      return z[7];
    }
    label34: if (((paramObject instanceof InstallInfo)) && ((i != 0) || (a != null)))
      a.info(((InstallInfo)paramObject).b(true));
    a(paramProgressPanel.getProperties(), paramMap);
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
      Class[] arrayOfClass = { String.class, PublishInterface.class, Object.class };
      Object[] arrayOfObject = { paramString, paramProgressPanel, paramObject };
      Method localMethod = localClass.getMethod(z[8], arrayOfClass);
      localObject1 = localMethod.invoke(localObject2, arrayOfObject);
      return localObject1.toString();
    }
    catch (Exception localException)
    {
    }
    return z[10] + localException.getMessage();
  }

  public String startEntityType(MEntityType paramMEntityType, String paramString1, String paramString2)
  {
    String str1 = "";
    MSystem localMSystem = MSystem.get(paramMEntityType.getCtx());
    c localc = new c(a);
    boolean bool = localc.a(localMSystem.getName(), localMSystem.getUserName(), localMSystem.getPassword(), Support.f(), Support.g(), Support.h(), localMSystem.getDBAddress(true), Support.i(), localMSystem.getSystemStatus(), str1, localMSystem.getRecord_ID(), paramString1, paramString2, paramMEntityType.getEntityType());
    if (!bool)
      return z[2];
    String str2 = localc.a();
    if (SysEnv.m == 0)
      if (str2.length() >= 200)
      {
        paramMEntityType.setSummary(str2);
        if (paramMEntityType.save())
          break label160;
        return z[3] + paramMEntityType.getEntityType();
      }
    return str2;
    label160: return z[4];
  }

  private void a(Properties paramProperties, Map paramMap)
  {
    if (paramMap == null)
      paramMap = new HashMap();
    String str1 = paramProperties.getProperty(z[6]);
    String str2 = paramProperties.getProperty(z[5]);
    c localc = new c(a);
    localc.a(null, null, null, -1, null, null, str1, str2, null, null, -1, null, null, null);
  }

  static
  {
    // Byte code:
    //   0: bipush 11
    //   2: anewarray 35	java/lang/String
    //   5: dup
    //   6: iconst_0
    //   7: ldc 12
    //   9: bipush 255
    //   11: goto +109 -> 120
    //   14: aastore
    //   15: dup
    //   16: iconst_1
    //   17: ldc 5
    //   19: iconst_0
    //   20: goto +100 -> 120
    //   23: aastore
    //   24: dup
    //   25: iconst_2
    //   26: ldc 65
    //   28: iconst_1
    //   29: goto +91 -> 120
    //   32: aastore
    //   33: dup
    //   34: iconst_3
    //   35: ldc 70
    //   37: iconst_2
    //   38: goto +82 -> 120
    //   41: aastore
    //   42: dup
    //   43: iconst_4
    //   44: ldc 71
    //   46: iconst_3
    //   47: goto +73 -> 120
    //   50: aastore
    //   51: dup
    //   52: iconst_5
    //   53: ldc 76
    //   55: iconst_4
    //   56: goto +64 -> 120
    //   59: aastore
    //   60: dup
    //   61: bipush 6
    //   63: ldc 74
    //   65: iconst_5
    //   66: goto +54 -> 120
    //   69: aastore
    //   70: dup
    //   71: bipush 7
    //   73: ldc 19
    //   75: bipush 6
    //   77: goto +43 -> 120
    //   80: aastore
    //   81: dup
    //   82: bipush 8
    //   84: ldc 38
    //   86: bipush 7
    //   88: goto +32 -> 120
    //   91: aastore
    //   92: dup
    //   93: bipush 9
    //   95: ldc 31
    //   97: bipush 8
    //   99: goto +21 -> 120
    //   102: aastore
    //   103: dup
    //   104: bipush 10
    //   106: ldc 44
    //   108: bipush 9
    //   110: goto +10 -> 120
    //   113: aastore
    //   114: putstatic 294	com/compiere/client/StartSupport:z	[Ljava/lang/String;
    //   117: goto +167 -> 284
    //   120: swap
    //   121: invokevirtual 286	java/lang/String:toCharArray	()[C
    //   124: dup
    //   125: arraylength
    //   126: swap
    //   127: iconst_0
    //   128: istore_0
    //   129: swap
    //   130: dup_x1
    //   131: iconst_1
    //   132: if_icmpgt +78 -> 210
    //   135: dup
    //   136: iload_0
    //   137: dup2
    //   138: caload
    //   139: iload_0
    //   140: iconst_5
    //   141: irem
    //   142: tableswitch	default:+50 -> 192, 0:+30->172, 1:+35->177, 2:+40->182, 3:+45->187
    //   173: istore_3
    //   174: goto +20 -> 194
    //   177: bipush 62
    //   179: goto +15 -> 194
    //   182: bipush 79
    //   184: goto +10 -> 194
    //   187: bipush 15
    //   189: goto +5 -> 194
    //   192: bipush 31
    //   194: ixor
    //   195: i2c
    //   196: castore
    //   197: iinc 0 1
    //   200: swap
    //   201: dup_x1
    //   202: ifne +8 -> 210
    //   205: dup2
    //   206: swap
    //   207: goto -70 -> 137
    //   210: swap
    //   211: dup_x1
    //   212: iload_0
    //   213: if_icmpgt -78 -> 135
    //   216: new 35	java/lang/String
    //   219: dup_x1
    //   220: swap
    //   221: invokespecial 289	java/lang/String:<init>	([C)V
    //   224: invokevirtual 292	java/lang/String:intern	()Ljava/lang/String;
    //   227: swap
    //   228: pop
    //   229: swap
    //   230: tableswitch	default:+-216 -> 14, 0:+-207->23, 1:+-198->32, 2:+-189->41, 3:+-180->50, 4:+-171->59, 5:+-161->69, 6:+-150->80, 7:+-139->91, 8:+-128->102, 9:+-117->113
    //   285: putstatic 10	com/compiere/client/StartSupport:a	Lorg/compiere/util/CLogger;
    //   288: return
  }
}