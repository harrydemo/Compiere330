package com.compiere.client;

import java.io.File;
import java.io.PrintStream;
import org.compiere.install.CompiereAntMain;
import org.compiere.util.CLogger;

class f extends Thread
{
  final PrintStream a;
  final String b;
  final CommandLineInstall c;
  private static final String[] z;

  f(CommandLineInstall paramCommandLineInstall, PrintStream paramPrintStream, String paramString)
  {
  }

  public void run()
  {
    try
    {
      System.setOut(this.a);
      System.setErr(this.a);
      CommandLineInstall.i().info(z[5] + this.b);
      System.setProperty(z[1], this.b);
      String[] arrayOfString = { z[4], this.b + File.separator + z[3], z[0] };
      CompiereAntMain localCompiereAntMain = new CompiereAntMain();
      int i = localCompiereAntMain.startAnt(arrayOfString);
      System.out.println(z[2] + i);
      this.a.close();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  static
  {
    // Byte code:
    //   0: bipush 6
    //   2: anewarray 16	java/lang/String
    //   5: dup
    //   6: iconst_0
    //   7: ldc 20
    //   9: bipush 255
    //   11: goto +55 -> 66
    //   14: aastore
    //   15: dup
    //   16: iconst_1
    //   17: ldc 14
    //   19: iconst_0
    //   20: goto +46 -> 66
    //   23: aastore
    //   24: dup
    //   25: iconst_2
    //   26: ldc 25
    //   28: iconst_1
    //   29: goto +37 -> 66
    //   32: aastore
    //   33: dup
    //   34: iconst_3
    //   35: ldc 19
    //   37: iconst_2
    //   38: goto +28 -> 66
    //   41: aastore
    //   42: dup
    //   43: iconst_4
    //   44: ldc 17
    //   46: iconst_3
    //   47: goto +19 -> 66
    //   50: aastore
    //   51: dup
    //   52: iconst_5
    //   53: ldc 10
    //   55: iconst_4
    //   56: goto +10 -> 66
    //   59: aastore
    //   60: putstatic 123	com/compiere/client/f:z	[Ljava/lang/String;
    //   63: goto +149 -> 212
    //   66: swap
    //   67: invokevirtual 115	java/lang/String:toCharArray	()[C
    //   70: dup
    //   71: arraylength
    //   72: swap
    //   73: iconst_0
    //   74: istore_0
    //   75: swap
    //   76: dup_x1
    //   77: iconst_1
    //   78: if_icmpgt +80 -> 158
    //   81: dup
    //   82: iload_0
    //   83: dup2
    //   84: caload
    //   85: iload_0
    //   86: iconst_5
    //   87: irem
    //   88: tableswitch	default:+52 -> 140, 0:+32->120, 1:+37->125, 2:+42->130, 3:+47->135
    //   121: caload
    //   122: goto +20 -> 142
    //   125: bipush 93
    //   127: goto +15 -> 142
    //   130: bipush 31
    //   132: goto +10 -> 142
    //   135: bipush 77
    //   137: goto +5 -> 142
    //   140: bipush 121
    //   142: ixor
    //   143: i2c
    //   144: castore
    //   145: iinc 0 1
    //   148: swap
    //   149: dup_x1
    //   150: ifne +8 -> 158
    //   153: dup2
    //   154: swap
    //   155: goto -72 -> 83
    //   158: swap
    //   159: dup_x1
    //   160: iload_0
    //   161: if_icmpgt -80 -> 81
    //   164: new 16	java/lang/String
    //   167: dup_x1
    //   168: swap
    //   169: invokespecial 118	java/lang/String:<init>	([C)V
    //   172: invokevirtual 121	java/lang/String:intern	()Ljava/lang/String;
    //   175: swap
    //   176: pop
    //   177: swap
    //   178: tableswitch	default:+-164 -> 14, 0:+-155->23, 1:+-146->32, 2:+-137->41, 3:+-128->50, 4:+-119->59
  }
}