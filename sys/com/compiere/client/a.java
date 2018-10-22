package com.compiere.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import org.compiere.framework.VO;
import org.compiere.model.MEntityType;
import org.compiere.util.CLogger;
import org.compiere.util.IniDialog;
import org.compiere.util.Util;
import org.compiere.util.ZipUtil;

class a
{
  static String a;
  static String b;
  static String c;
  static String d;
  static String e;
  static String f;
  static String g;
  private static ArrayList h;
  private File i;
  private boolean j = false;
  private boolean k = false;
  private String l = null;
  private String m = null;
  private String n = null;
  private String o = null;
  private String p = "";
  private byte[] q = null;
  private static int r;
  private static int s;
  private static int t;
  private int u = 0;
  private String[] v = null;
  private String w = null;
  private MEntityType x = null;
  private b y = null;
  protected CLogger z = CLogger.getCLogger(a.class);
  private static final String[] A;

  static ArrayList a(String paramString)
  {
    int i2 = SysEnv.m;
    File localFile = new File(paramString + File.separator + A[17]);
    if ((i2 != 0) || (localFile.exists()))
    {
      String[] arrayOfString = localFile.list(new ComponentFileFilter());
      h = new ArrayList(arrayOfString.length);
      int i1 = 0;
      do
      {
        if (i1 >= arrayOfString.length)
          break;
        a locala = new a(new File(localFile, arrayOfString[i1]));
        if (i2 != 0)
          break label131;
        h.add(locala);
        i1++;
      }
      while (i2 == 0);
      label131: return h;
    }
    return null;
  }

  static ArrayList a()
  {
    return h;
  }

  a(File paramFile)
  {
    a(paramFile);
  }

  private void a(File paramFile)
  {
    int i1 = SysEnv.m;
    this.i = paramFile;
    if ((i1 != 0) || (!this.i.exists()))
    {
      this.z.warning(A[10] + paramFile);
      return;
    }
    try
    {
      ZipUtil localZipUtil = new ZipUtil(this.i);
      ZipEntry localZipEntry = localZipUtil.getEntry(A[9]);
      if (i1 == 0)
      {
        if (localZipEntry == null)
        {
          this.z.warning(A[11] + this.i);
          this.u = -1;
        }
      }
      else
        return;
      InputStream localInputStream = localZipUtil.getInputStream(localZipEntry);
      ObjectInputStream localObjectInputStream = new ObjectInputStream(localInputStream);
      Object localObject = localObjectInputStream.readObject();
      if ((i1 != 0) || ((localObject instanceof VO)))
      {
        VO localVO = (VO)localObject;
        this.l = localVO.get(A[7]);
        if (i1 == 0)
        {
          this.m = localVO.get(A[15]);
          if ("D".equals(this.m))
          {
            this.l = A[8];
            this.u = r;
            if (i1 == 0)
              break label248;
          }
        }
        this.u = s;
        label248: this.n = localVO.get(A[12]);
        this.o = localVO.get(A[16]);
        String str = localVO.get(A[13]);
        if (i1 == 0)
        {
          if (str != null)
            this.q = Util.toByteArray(str);
          this.p = localVO.get(A[14]);
        }
        this.j = true;
      }
      this.k = (this.o == null);
    }
    catch (Exception localException)
    {
      this.j = false;
      this.u = -1;
    }
    if ((i1 != 0) || (this.j))
      this.j = g();
  }

  String[] a(MEntityType paramMEntityType, b paramb)
  {
    int i1 = SysEnv.m;
    if (paramMEntityType != null)
      this.x = paramMEntityType;
    if (paramb != null)
      this.y = paramb;
    if (i1 == 0)
      if (this.v == null)
      {
        this.v = new String[] { b };
        if (i1 != 0)
          break label288;
        if (h())
        {
          if (i1 == 0)
            if (c())
            {
              if (i1 == 0)
                if (f())
                {
                  this.v = new String[] { f, c };
                  if (i1 == 0)
                    break label287;
                }
              this.v = new String[] { d };
              if (i1 == 0)
                break label287;
            }
          if (i1 == 0)
          {
            if (d())
            {
              if (i1 == 0)
              {
                if (!this.y.b())
                {
                  this.v = new String[] { g };
                  if (i1 == 0)
                    break label287;
                }
                if (i1 != 0);
              }
              else if (f())
              {
                this.v = new String[] { f, c };
                if (i1 == 0)
                  break label287;
              }
              this.v = new String[] { d, a };
              if (i1 == 0);
            }
            else
            {
              if (i1 != 0)
                break label288;
            }
          }
          else if (e())
          {
            if (i1 == 0)
              if (!this.y.b())
              {
                this.v = new String[] { g };
                if (i1 == 0)
                  break label287;
              }
            this.v = new String[] { f, a };
          }
        }
      }
    label287: label288: return this.v;
  }

  String b()
  {
    return this.m;
  }

  boolean b(String paramString)
  {
    int i2 = SysEnv.m;
    if (paramString != null)
    {
      if (i2 != 0)
        break label30;
      if (this.v != null);
    }
    else
    {
      this.w = null;
      return false;
    }
    label30: if (i2 == 0)
    {
      if (this.v.length == 1)
      {
        this.w = this.v[0];
        if (i2 == 0);
      }
      else
      {
        this.w = null;
      }
    }
    else
    {
      int i1 = 0;
      do
      {
        if (i1 >= this.v.length)
          break;
        if (i2 == 0)
        {
          if (i2 != 0)
            break label114;
          if (!this.v[i1].equals(paramString));
        }
        else
        {
          this.w = paramString;
          if (i2 == 0)
            break;
        }
        i1++;
      }
      while (i2 == 0);
    }
    label114: return paramString.equals(this.w);
  }

  boolean c()
  {
    if (SysEnv.m == 0);
    return this.u == r;
  }

  boolean d()
  {
    if (SysEnv.m == 0);
    return this.u == s;
  }

  boolean e()
  {
    if (SysEnv.m == 0);
    return this.u == t;
  }

  boolean f()
  {
    if (SysEnv.m == 0)
      if (this.x == null)
        return false;
    return this.m.equals(this.x.getEntityType());
  }

  private boolean g()
  {
    int i1 = SysEnv.m;
    if (i1 == 0)
      if (this.u == r)
        return true;
    if (i1 == 0)
    {
      if (this.u == s)
        return true;
      if (i1 != 0);
    }
    else if (this.u == t)
    {
      return false;
    }
    return false;
  }

  String a(File paramFile1, File paramFile2)
  {
    int i1 = SysEnv.m;
    String str = j();
    Object localObject;
    if ((i1 != 0) || (str != null))
      if (i1 == 0)
      {
        if (str.length() > 0)
          this.k = IniDialog.accept(m(), str);
      }
      else
      {
        if (!this.k)
          return A[0] + toString();
        try
        {
          File localFile = new File(paramFile1, l());
          localObject = new FileWriter(localFile, false);
          ((FileWriter)localObject).write(str);
          ((FileWriter)localObject).flush();
          ((FileWriter)localObject).close();
        }
        catch (Exception localException1)
        {
          return A[3] + toString();
        }
      }
    byte[] arrayOfByte = n();
    if (arrayOfByte != null)
      try
      {
        localObject = new File(paramFile2, A[1]);
        FileOutputStream localFileOutputStream = new FileOutputStream((File)localObject, false);
        localFileOutputStream.write(arrayOfByte);
        localFileOutputStream.flush();
        localFileOutputStream.close();
      }
      catch (Exception localException2)
      {
        return A[2] + toString();
      }
    return (String)null;
  }

  boolean h()
  {
    return this.j;
  }

  String i()
  {
    if (SysEnv.m == 0)
      if (this.i == null)
        return null;
    return this.i.getAbsolutePath();
  }

  String j()
  {
    return this.o;
  }

  String k()
  {
    int i1 = SysEnv.m;
    if (i1 == 0)
      if (this.p != null)
      {
        if (i1 != 0)
          break label36;
        if (this.p.length() != 0)
          break label32;
      }
    return "0";
    label32: label36: return this.p;
  }

  String l()
  {
    int i1 = SysEnv.m;
    StringBuffer localStringBuffer = new StringBuffer(this.l);
    if (i1 == 0)
    {
      if (this.n != null)
      {
        if (i1 != 0)
          break label79;
        if (this.n.length() > 0)
          localStringBuffer.append(" ").append(this.n);
      }
      localStringBuffer.append(A[6]);
      Util.replace(localStringBuffer, " ", "_");
    }
    label79: return localStringBuffer.toString();
  }

  String m()
  {
    int i1 = SysEnv.m;
    StringBuffer localStringBuffer = new StringBuffer();
    if (i1 == 0)
    {
      if (this.l != null)
        localStringBuffer.append(this.l);
    }
    else
      if (i1 == 0)
        break label45;
    localStringBuffer.append(i());
    label45: if (i1 == 0)
      if (this.n != null)
        localStringBuffer.append(" ").append(this.n);
    return localStringBuffer.toString();
  }

  byte[] n()
  {
    return this.q;
  }

  public String toString()
  {
    int i1 = SysEnv.m;
    StringBuffer localStringBuffer = new StringBuffer();
    if (i1 == 0)
    {
      if (this.l != null)
        localStringBuffer.append(this.l);
    }
    else
      if (i1 == 0)
        break label45;
    localStringBuffer.append(i());
    label45: if (i1 == 0)
      if (this.n != null)
        localStringBuffer.append(" ").append(this.n);
    if (i1 == 0)
      if (!h())
        localStringBuffer.append(A[5]);
    if ((i1 != 0) && (!this.k))
      localStringBuffer.append(A[4]);
    return localStringBuffer.toString();
  }

  static
  {
    break label197;
    0["\022^0bi-Rsih*\0272dd;G'bc~Q<u'"] = -1;
    break label197;
    1["\035X>wn;E665nO`7).Y4"] = 0;
    break label197;
    2["\035X&kc~Y<s')E:sb~{<`h~Q<u'"] = 1;
    break label197;
    3["\035X&kc~Y<s')E:sb~{:db0D6'a1Es"] = 2;
    break label197;
    4["~L\001bv+^!bt~V0db.C:i`~{:db0D6z"] = 3;
    break label197;
    5["~\035y'I1CsQf2^7'-t"] = 4;
    break label197;
    6["~[:db0D6)o*Z?"] = 5;
    break label197;
    7["\020V>b"] = 6;
    break label197;
    8["\035X>wn;E6'T*V=cf,S"] = 7;
    break label197;
    9["\033Y'ns'c*wb\001a\034"] = 8;
    break label197;
    10["\020X''a1B=c=~"] = 9;
    break label197;
    11["\033Y'ns'c*wb\001a\034'i1Csah+Y7='"] = 10;
    break label197;
    12["\bR!tn1Y"] = 11;
    break label197;
    13["\034^=fu's2sf"] = 12;
    break label197;
    14["\fR0hu:h\032C"] = 13;
    break label197;
    15["\033Y'ns'c*wb"] = 14;
    String[] tmp169_2 = new String[18];
    break label197;
    16["\022^0bi-R\007b*"] = 15;
    break label197;
    17[":V'f"] = 16;
    break label288;
    label197: switch (a.A = tmp169_2)
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    case 9:
    case 10:
    case 11:
    case 12:
    case 13:
    case 14:
    case 15:
    case 16:
    }
    while (true)
      switch (z(z(tmp169_2)))
      {
      default:
        continue;
        a = -1;
        break;
      case 0:
        b = 0;
        break;
      case 1:
        c = 1;
        break;
      case 2:
        d = 2;
        break;
      case 3:
        e = 3;
        break;
      case 4:
        label288: f = 4;
      case 5:
      }
    g = 5;
    h = null;
    r = 1;
    s = 2;
    t = 3;
  }

  private static char[] z(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 368	java/lang/String:toCharArray	()[C
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
    //   20: bipush 7
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
        tmpTernaryOp = 94;
        break;
      case 1:
        tmpTernaryOp = 55;
        break;
      case 2:
        tmpTernaryOp = 83;
        break;
      case 3:
        tmpTernaryOp = 7;
        break;
      }
      paramArrayOfChar[tmp14_13] = (char)(paramArrayOfChar[tmp14_13] ^ 0x7);
      i1++;
    }
    while (paramArrayOfChar > i1);
    return new String(paramArrayOfChar).intern();
  }
}