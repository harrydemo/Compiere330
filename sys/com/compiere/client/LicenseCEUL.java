package com.compiere.client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import org.compiere.api.LicenseInterface;
import org.compiere.util.CLogger;
import org.compiere.util.CPreparedStatement;
import org.compiere.util.DB;
import org.compiere.util.Trx;

final class LicenseCEUL
  implements LicenseInterface
{
  private CLogger log = CLogger.getCLogger(LicenseCEUL.class);
  private static final String[] z;

  public int getUnitOne()
  {
    int j = SysEnv.m;
    int i = -1;
    String str = z[2];
    CPreparedStatement localCPreparedStatement = null;
    try
    {
      localCPreparedStatement = DB.prepareStatement(str, (Trx)null);
      ResultSet localResultSet = localCPreparedStatement.executeQuery();
      if (j == 0)
        if (localResultSet.next())
          i = localResultSet.getInt(1);
      localResultSet.close();
      localCPreparedStatement.close();
      localCPreparedStatement = null;
    }
    catch (Exception localException1)
    {
      if ((j != 0) || (this.log != null))
        this.log.log(Level.SEVERE, z[3], localException1);
    }
    try
    {
      if ((j != 0) || (localCPreparedStatement != null))
        localCPreparedStatement.close();
      localCPreparedStatement = null;
    }
    catch (Exception localException2)
    {
      localCPreparedStatement = null;
    }
    return i;
  }

  public int getUnitTwo()
  {
    int j = SysEnv.m;
    int i = -1;
    String str = z[0];
    CPreparedStatement localCPreparedStatement = null;
    try
    {
      localCPreparedStatement = DB.prepareStatement(str, (Trx)null);
      ResultSet localResultSet = localCPreparedStatement.executeQuery();
      if (j == 0)
        if (localResultSet.next())
          i = localResultSet.getInt(1);
      localResultSet.close();
      localCPreparedStatement.close();
      localCPreparedStatement = null;
    }
    catch (Exception localException1)
    {
      if ((j != 0) || (this.log != null))
        this.log.log(Level.SEVERE, z[1], localException1);
    }
    try
    {
      if ((j != 0) || (localCPreparedStatement != null))
        localCPreparedStatement.close();
      localCPreparedStatement = null;
    }
    catch (Exception localException2)
    {
      localCPreparedStatement = null;
    }
    return i;
  }

  public String getStatusOne()
  {
    return "";
  }

  public String getStatusTwo()
  {
    return "";
  }

  static
  {
    // Byte code:
    //   0: iconst_4
    //   1: anewarray 31	java/lang/String
    //   4: dup
    //   5: iconst_0
    //   6: ldc 17
    //   8: bipush 255
    //   10: goto +37 -> 47
    //   13: aastore
    //   14: dup
    //   15: iconst_1
    //   16: ldc 18
    //   18: iconst_0
    //   19: goto +28 -> 47
    //   22: aastore
    //   23: dup
    //   24: iconst_2
    //   25: ldc 5
    //   27: iconst_1
    //   28: goto +19 -> 47
    //   31: aastore
    //   32: dup
    //   33: iconst_3
    //   34: ldc 15
    //   36: iconst_2
    //   37: goto +10 -> 47
    //   40: aastore
    //   41: putstatic 105	com/compiere/client/LicenseCEUL:z	[Ljava/lang/String;
    //   44: goto +140 -> 184
    //   47: swap
    //   48: invokevirtual 97	java/lang/String:toCharArray	()[C
    //   51: dup
    //   52: arraylength
    //   53: swap
    //   54: iconst_0
    //   55: istore_0
    //   56: swap
    //   57: dup_x1
    //   58: iconst_1
    //   59: if_icmpgt +79 -> 138
    //   62: dup
    //   63: iload_0
    //   64: dup2
    //   65: caload
    //   66: iload_0
    //   67: iconst_5
    //   68: irem
    //   69: tableswitch	default:+51 -> 120, 0:+31->100, 1:+36->105, 2:+41->110, 3:+46->115
    //   101: lstore_1
    //   102: goto +20 -> 122
    //   105: bipush 48
    //   107: goto +15 -> 122
    //   110: bipush 55
    //   112: goto +10 -> 122
    //   115: bipush 120
    //   117: goto +5 -> 122
    //   120: bipush 109
    //   122: ixor
    //   123: i2c
    //   124: castore
    //   125: iinc 0 1
    //   128: swap
    //   129: dup_x1
    //   130: ifne +8 -> 138
    //   133: dup2
    //   134: swap
    //   135: goto -71 -> 64
    //   138: swap
    //   139: dup_x1
    //   140: iload_0
    //   141: if_icmpgt -79 -> 62
    //   144: new 31	java/lang/String
    //   147: dup_x1
    //   148: swap
    //   149: invokespecial 100	java/lang/String:<init>	([C)V
    //   152: invokevirtual 103	java/lang/String:intern	()Ljava/lang/String;
    //   155: swap
    //   156: pop
    //   157: swap
    //   158: tableswitch	default:+-145 -> 13, 0:+-136->22, 1:+-127->31, 2:+-118->40
  }
}