package com.compiere.client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import org.compiere.api.LicenseInterface;
import org.compiere.util.CLogger;
import org.compiere.util.CPreparedStatement;
import org.compiere.util.DB;
import org.compiere.util.Trx;

final class LicenseCWMS
  implements LicenseInterface
{
  private CLogger log = CLogger.getCLogger(LicenseCWMS.class);
  private static final String[] z;

  public int getUnitOne()
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

  public int getUnitTwo()
  {
    return 0;
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
    //   0: iconst_2
    //   1: anewarray 29	java/lang/String
    //   4: dup
    //   5: iconst_0
    //   6: ldc 5
    //   8: bipush 255
    //   10: goto +19 -> 29
    //   13: aastore
    //   14: dup
    //   15: iconst_1
    //   16: ldc 15
    //   18: iconst_0
    //   19: goto +10 -> 29
    //   22: aastore
    //   23: putstatic 101	com/compiere/client/LicenseCWMS:z	[Ljava/lang/String;
    //   26: goto +130 -> 156
    //   29: swap
    //   30: invokevirtual 93	java/lang/String:toCharArray	()[C
    //   33: dup
    //   34: arraylength
    //   35: swap
    //   36: iconst_0
    //   37: istore_0
    //   38: swap
    //   39: dup_x1
    //   40: iconst_1
    //   41: if_icmpgt +77 -> 118
    //   44: dup
    //   45: iload_0
    //   46: dup2
    //   47: caload
    //   48: iload_0
    //   49: iconst_5
    //   50: irem
    //   51: tableswitch	default:+49 -> 100, 0:+29->80, 1:+34->85, 2:+39->90, 3:+44->95
    //   81: dup
    //   82: goto +20 -> 102
    //   85: bipush 89
    //   87: goto +15 -> 102
    //   90: bipush 18
    //   92: goto +10 -> 102
    //   95: bipush 25
    //   97: goto +5 -> 102
    //   100: bipush 118
    //   102: ixor
    //   103: i2c
    //   104: castore
    //   105: iinc 0 1
    //   108: swap
    //   109: dup_x1
    //   110: ifne +8 -> 118
    //   113: dup2
    //   114: swap
    //   115: goto -69 -> 46
    //   118: swap
    //   119: dup_x1
    //   120: iload_0
    //   121: if_icmpgt -77 -> 44
    //   124: new 29	java/lang/String
    //   127: dup_x1
    //   128: swap
    //   129: invokespecial 96	java/lang/String:<init>	([C)V
    //   132: invokevirtual 99	java/lang/String:intern	()Ljava/lang/String;
    //   135: swap
    //   136: pop
    //   137: swap
    //   138: tableswitch	default:+-125 -> 13, 0:+-116->22
  }
}