package com.compiere.client;

import java.util.ArrayList;
import java.util.List;

public class InstallInfo
{
  private List a = new ArrayList();
  private List b = new ArrayList();
  private String c = null;
  private String d = null;
  private String e = null;
  public static boolean f;
  private static final String[] z;

  public final List get(boolean paramBoolean)
  {
    if (paramBoolean)
      return this.a;
    return this.b;
  }

  int a(boolean paramBoolean)
  {
    if (SysEnv.m == 0)
      if (paramBoolean)
        return this.a.size();
    return this.b.size();
  }

  void a(String paramString)
  {
    if (paramString != null)
      this.a.add(paramString);
  }

  void b(String paramString)
  {
    if (paramString != null)
      this.b.add(paramString);
  }

  String b(boolean paramBoolean)
  {
    int j = SysEnv.m;
    StringBuffer localStringBuffer = new StringBuffer();
    List localList = null;
    if (j == 0)
    {
      if (paramBoolean)
      {
        localStringBuffer.append(z[1]);
        localList = this.a;
      }
      else
      {
        localStringBuffer.append(z[0]);
      }
    }
    else
      localList = this.b;
    int i = 0;
    do
    {
      if (i >= localList.size())
        break;
      if (i != 0)
        localStringBuffer.append(",");
      localStringBuffer.append((String)localList.get(i));
      i++;
    }
    while (j == 0);
    return localStringBuffer.toString();
  }

  void c(String paramString)
  {
    this.c = paramString;
  }

  void d(String paramString)
  {
    this.d = paramString;
  }

  void e(String paramString)
  {
    this.e = paramString;
  }

  public final String getSystemName()
  {
    return this.c;
  }

  public final String getemail()
  {
    return this.d;
  }

  public final String getpwd()
  {
    return this.e;
  }

  static
  {
    // Byte code:
    //   0: iconst_2
    //   1: anewarray 18	java/lang/String
    //   4: dup
    //   5: iconst_0
    //   6: ldc 15
    //   8: bipush 255
    //   10: goto +19 -> 29
    //   13: aastore
    //   14: dup
    //   15: iconst_1
    //   16: ldc 13
    //   18: iconst_0
    //   19: goto +10 -> 29
    //   22: aastore
    //   23: putstatic 96	com/compiere/client/InstallInfo:z	[Ljava/lang/String;
    //   26: goto +130 -> 156
    //   29: swap
    //   30: invokevirtual 88	java/lang/String:toCharArray	()[C
    //   33: dup
    //   34: arraylength
    //   35: swap
    //   36: iconst_0
    //   37: istore_0
    //   38: swap
    //   39: dup_x1
    //   40: iconst_1
    //   41: if_icmpgt +76 -> 117
    //   44: dup
    //   45: iload_0
    //   46: dup2
    //   47: caload
    //   48: iload_0
    //   49: iconst_5
    //   50: irem
    //   51: tableswitch	default:+48 -> 99, 0:+29->80, 1:+33->84, 2:+38->89, 3:+43->94
    //   81: goto +20 -> 101
    //   84: bipush 58
    //   86: goto +15 -> 101
    //   89: bipush 100
    //   91: goto +10 -> 101
    //   94: bipush 52
    //   96: goto +5 -> 101
    //   99: bipush 8
    //   101: ixor
    //   102: i2c
    //   103: castore
    //   104: iinc 0 1
    //   107: swap
    //   108: dup_x1
    //   109: ifne +8 -> 117
    //   112: dup2
    //   113: swap
    //   114: goto -68 -> 46
    //   117: swap
    //   118: dup_x1
    //   119: iload_0
    //   120: if_icmpgt -76 -> 44
    //   123: new 18	java/lang/String
    //   126: dup_x1
    //   127: swap
    //   128: invokespecial 91	java/lang/String:<init>	([C)V
    //   131: invokevirtual 94	java/lang/String:intern	()Ljava/lang/String;
    //   134: swap
    //   135: pop
    //   136: swap
    //   137: tableswitch	default:+-124 -> 13, 0:+-115->22
  }
}