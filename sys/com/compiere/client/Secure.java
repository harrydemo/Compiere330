package com.compiere.client;

import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.security.AlgorithmParameters;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.compiere.Compiere;
import org.compiere.api.SecureInterface;
import org.compiere.util.SecureUtil;

public class Secure
  implements SecureInterface
{
  private Cipher a = null;
  private SecretKey b = null;
  private MessageDigest c = null;
  private static SecretKey d;
  private static Logger e;
  private static final String[] z;

  public Secure()
  {
    a();
  }

  private synchronized void a()
  {
    int k = SysEnv.m;
    if (k == 0)
      if (this.a != null)
        return;
    this.b = d;
    try
    {
      Properties localProperties = new Properties();
      InputStream localInputStream = Compiere.class.getResourceAsStream(z[5]);
      localProperties.load(localInputStream);
      String str1 = localProperties.getProperty(z[7], z[10]);
      if (k == 0)
        if (!str1.equals(z[10]))
        {
          e.warning(z[11]);
          str1 = z[10];
        }
      String str2 = localProperties.getProperty(z[6]);
      byte[] arrayOfByte = new byte[8];
      StringTokenizer localStringTokenizer = new StringTokenizer(str2, z[12]);
      if (k == 0)
        if (localStringTokenizer.countTokens() != arrayOfByte.length)
          e.warning(z[13] + arrayOfByte.length);
      int i = 0;
      do
      {
        if ((i >= arrayOfByte.length) || ((k == 0) && (!localStringTokenizer.hasMoreTokens())))
          break;
        String str3 = localStringTokenizer.nextToken();
        int j = Integer.parseInt(str3);
        arrayOfByte[i] = (byte)j;
        i++;
      }
      while (k == 0);
      SecretKeySpec localSecretKeySpec = new SecretKeySpec(arrayOfByte, str1);
      this.b = localSecretKeySpec;
    }
    catch (Exception localException1)
    {
      e.warning(z[4] + localException1.getMessage());
      this.b = d;
    }
    Cipher localCipher = null;
    try
    {
      localCipher = Cipher.getInstance(z[8]);
    }
    catch (Exception localException2)
    {
      e.log(Level.WARNING, z[9], localException2);
    }
    this.a = localCipher;
  }

  public String encrypt(String paramString)
  {
    int i = SysEnv.m;
    String str1 = paramString;
    if ((i != 0) || (str1 == null))
      str1 = "";
    if (i == 0)
      if (this.a == null)
        a();
    if ((i != 0) || (this.a != null))
      try
      {
        this.a.init(1, this.b);
        byte[] arrayOfByte = this.a.doFinal(str1.getBytes());
        String str2 = SecureUtil.convertToHexString(arrayOfByte);
        e.log(Level.ALL, paramString + z[2] + str2);
        return str2;
      }
      catch (Exception localException)
      {
        e.log(Level.INFO, paramString, localException);
      }
    return z[3] + paramString + "";
  }

  public boolean isEncrypted(String paramString)
  {
    int j = SysEnv.m;
    if ((j != 0) || (paramString != null))
    {
      if (j != 0)
        break label34;
      if (paramString.length() != 0);
    }
    else
    {
      return false;
    }
    label34: if ((j != 0) || (paramString.startsWith("~")))
      if (j != 0);
    int i = paramString.endsWith("~") ? 1 : 0;
    if (i != 0)
      paramString = paramString.substring("~".length(), paramString.length() - "~".length());
    byte[] arrayOfByte = SecureUtil.convertHexString(paramString);
    if (arrayOfByte == null)
      return false;
    if (j == 0)
      if (this.a == null)
        a();
    if (this.a != null)
      if ((j != 0) || (paramString != null))
      {
        if (j != 0)
          break label224;
        if (paramString.length() > 0)
          try
          {
            AlgorithmParameters localAlgorithmParameters = this.a.getParameters();
            this.a.init(2, this.b, localAlgorithmParameters);
            this.a.doFinal(arrayOfByte);
            return true;
          }
          catch (Exception localException)
          {
            e.finer(z[15] + paramString + z[14] + localException.toString());
          }
      }
    label224: return false;
  }

  public String decrypt(String paramString)
  {
    int j = SysEnv.m;
    if (j == 0)
      if (paramString != null)
      {
        if (j != 0)
          break label34;
        if (paramString.length() != 0)
          break label28;
      }
    return paramString;
    label28: label34: if ((j != 0) || (paramString.startsWith("~")))
      if (j != 0);
    int i = paramString.endsWith("~") ? 1 : 0;
    if (i != 0)
      paramString = paramString.substring("~".length(), paramString.length() - "~".length());
    byte[] arrayOfByte1 = SecureUtil.convertHexString(paramString);
    if (arrayOfByte1 == null)
    {
      if (i != 0)
      {
        e.info(z[15] + paramString);
        return null;
      }
      return paramString;
    }
    if (j == 0)
      if (this.a == null)
        a();
    if (this.a != null)
    {
      if (j != 0)
        break label311;
      if (paramString != null)
      {
        if (j != 0)
          break label311;
        if (paramString.length() > 0)
          try
          {
            AlgorithmParameters localAlgorithmParameters = this.a.getParameters();
            this.a.init(2, this.b, localAlgorithmParameters);
            byte[] arrayOfByte2 = this.a.doFinal(arrayOfByte1);
            String str = new String(arrayOfByte2);
            e.log(Level.ALL, paramString + z[2] + str);
            return str;
          }
          catch (Exception localException)
          {
            e.log(Level.FINE, z[15] + paramString + z[14] + localException.getMessage());
          }
      }
    }
    label311: return paramString;
  }

  public Integer encrypt(Integer paramInteger)
  {
    return paramInteger;
  }

  public Integer decrypt(Integer paramInteger)
  {
    return paramInteger;
  }

  public BigDecimal encrypt(BigDecimal paramBigDecimal)
  {
    return paramBigDecimal;
  }

  public BigDecimal decrypt(BigDecimal paramBigDecimal)
  {
    return paramBigDecimal;
  }

  public Timestamp encrypt(Timestamp paramTimestamp)
  {
    return paramTimestamp;
  }

  public Timestamp decrypt(Timestamp paramTimestamp)
  {
    return paramTimestamp;
  }

  public String getDigest(String paramString)
  {
    if (SysEnv.m == 0)
      if (this.c == null)
        try
        {
          this.c = MessageDigest.getInstance(z[16]);
        }
        catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
        {
          localNoSuchAlgorithmException.printStackTrace();
        }
    this.c.reset();
    byte[] arrayOfByte1 = paramString.getBytes();
    this.c.update(arrayOfByte1);
    byte[] arrayOfByte2 = this.c.digest();
    this.c.reset();
    return SecureUtil.convertToHexString(arrayOfByte2);
  }

  public boolean isDigest(String paramString)
  {
    int i = SysEnv.m;
    if ((i != 0) || (paramString != null))
    {
      if (i != 0)
        break label28;
      if (paramString.length() == 32);
    }
    else
    {
      return false;
    }
    label28: return SecureUtil.convertHexString(paramString) != null;
  }

  public String toString()
  {
    int i = SysEnv.m;
    StringBuffer localStringBuffer = new StringBuffer(z[1]);
    if (i == 0)
      if (this.b != null)
      {
        if (i != 0)
          break label80;
        if (this.b.getEncoded() != null)
          localStringBuffer.append(this.b.getAlgorithm()).append(z[0]).append(this.b.getEncoded().length);
      }
    label80: if (this.a != null)
      localStringBuffer.append(",").append(this.a.getAlgorithm()).append(",").append(this.a.getProvider());
    localStringBuffer.append("]");
    return localStringBuffer.toString();
  }

  public static void main(String[] paramArrayOfString)
  {
    System.out.println(new Secure().toString());
  }

  static
  {
    // Byte code:
    //   0: bipush 17
    //   2: anewarray 68	java/lang/String
    //   5: dup
    //   6: iconst_0
    //   7: ldc 84
    //   9: bipush 255
    //   11: goto +175 -> 186
    //   14: aastore
    //   15: dup
    //   16: iconst_1
    //   17: ldc 79
    //   19: iconst_0
    //   20: goto +166 -> 186
    //   23: aastore
    //   24: dup
    //   25: iconst_2
    //   26: ldc 51
    //   28: iconst_1
    //   29: goto +157 -> 186
    //   32: aastore
    //   33: dup
    //   34: iconst_3
    //   35: ldc 54
    //   37: iconst_2
    //   38: goto +148 -> 186
    //   41: aastore
    //   42: dup
    //   43: iconst_4
    //   44: ldc 38
    //   46: iconst_3
    //   47: goto +139 -> 186
    //   50: aastore
    //   51: dup
    //   52: iconst_5
    //   53: ldc 10
    //   55: iconst_4
    //   56: goto +130 -> 186
    //   59: aastore
    //   60: dup
    //   61: bipush 6
    //   63: ldc 20
    //   65: iconst_5
    //   66: goto +120 -> 186
    //   69: aastore
    //   70: dup
    //   71: bipush 7
    //   73: ldc 13
    //   75: bipush 6
    //   77: goto +109 -> 186
    //   80: aastore
    //   81: dup
    //   82: bipush 8
    //   84: ldc 40
    //   86: bipush 7
    //   88: goto +98 -> 186
    //   91: aastore
    //   92: dup
    //   93: bipush 9
    //   95: ldc 43
    //   97: bipush 8
    //   99: goto +87 -> 186
    //   102: aastore
    //   103: dup
    //   104: bipush 10
    //   106: ldc 14
    //   108: bipush 9
    //   110: goto +76 -> 186
    //   113: aastore
    //   114: dup
    //   115: bipush 11
    //   117: ldc 18
    //   119: bipush 10
    //   121: goto +65 -> 186
    //   124: aastore
    //   125: dup
    //   126: bipush 12
    //   128: ldc 23
    //   130: bipush 11
    //   132: goto +54 -> 186
    //   135: aastore
    //   136: dup
    //   137: bipush 13
    //   139: ldc 28
    //   141: bipush 12
    //   143: goto +43 -> 186
    //   146: aastore
    //   147: dup
    //   148: bipush 14
    //   150: ldc 64
    //   152: bipush 13
    //   154: goto +32 -> 186
    //   157: aastore
    //   158: dup
    //   159: bipush 15
    //   161: ldc 63
    //   163: bipush 14
    //   165: goto +21 -> 186
    //   168: aastore
    //   169: dup
    //   170: bipush 16
    //   172: ldc 71
    //   174: bipush 15
    //   176: goto +10 -> 186
    //   179: aastore
    //   180: putstatic 346	com/compiere/client/Secure:z	[Ljava/lang/String;
    //   183: goto +193 -> 376
    //   186: swap
    //   187: invokevirtual 338	java/lang/String:toCharArray	()[C
    //   190: dup
    //   191: arraylength
    //   192: swap
    //   193: iconst_0
    //   194: istore_0
    //   195: swap
    //   196: dup_x1
    //   197: iconst_1
    //   198: if_icmpgt +79 -> 277
    //   201: dup
    //   202: iload_0
    //   203: dup2
    //   204: caload
    //   205: iload_0
    //   206: iconst_5
    //   207: irem
    //   208: tableswitch	default:+52 -> 260, 0:+32->240, 1:+37->245, 2:+42->250, 3:+47->255
    //   241: iload_3
    //   242: goto +19 -> 261
    //   245: bipush 104
    //   247: goto +14 -> 261
    //   250: bipush 65
    //   252: goto +9 -> 261
    //   255: bipush 33
    //   257: goto +4 -> 261
    //   260: iconst_3
    //   261: ixor
    //   262: i2c
    //   263: castore
    //   264: iinc 0 1
    //   267: swap
    //   268: dup_x1
    //   269: ifne +8 -> 277
    //   272: dup2
    //   273: swap
    //   274: goto -71 -> 203
    //   277: swap
    //   278: dup_x1
    //   279: iload_0
    //   280: if_icmpgt -79 -> 201
    //   283: new 68	java/lang/String
    //   286: dup_x1
    //   287: swap
    //   288: invokespecial 341	java/lang/String:<init>	([C)V
    //   291: invokevirtual 344	java/lang/String:intern	()Ljava/lang/String;
    //   294: swap
    //   295: pop
    //   296: swap
    //   297: tableswitch	default:+-283 -> 14, 0:+-274->23, 1:+-265->32, 2:+-256->41, 3:+-247->50, 4:+-238->59, 5:+-228->69, 6:+-217->80, 7:+-206->91, 8:+-195->102, 9:+-184->113, 10:+-173->124, 11:+-162->135, 12:+-151->146, 13:+-140->157, 14:+-129->168, 15:+-118->179
    //   377: nop
    //   378: fload_1
    //   379: dup
    //   380: bipush 8
    //   382: newarray byte
    //   384: dup
    //   385: iconst_0
    //   386: bipush 100
    //   388: bastore
    //   389: dup
    //   390: iconst_1
    //   391: bipush 25
    //   393: bastore
    //   394: dup
    //   395: iconst_2
    //   396: bipush 28
    //   398: bastore
    //   399: dup
    //   400: iconst_3
    //   401: bipush 134
    //   403: bastore
    //   404: dup
    //   405: iconst_4
    //   406: bipush 230
    //   408: bastore
    //   409: dup
    //   410: iconst_5
    //   411: bipush 94
    //   413: bastore
    //   414: dup
    //   415: bipush 6
    //   417: bipush 253
    //   419: bastore
    //   420: dup
    //   421: bipush 7
    //   423: bipush 230
    //   425: bastore
    //   426: getstatic 346	com/compiere/client/Secure:z	[Ljava/lang/String;
    //   429: bipush 10
    //   431: aaload
    //   432: invokespecial 36	javax/crypto/spec/SecretKeySpec:<init>	([BLjava/lang/String;)V
    //   435: putstatic 6	com/compiere/client/Secure:d	Ljavax/crypto/SecretKey;
    //   438: ldc 93
    //   440: invokevirtual 97	java/lang/Class:getName	()Ljava/lang/String;
    //   443: invokestatic 98	java/util/logging/Logger:getLogger	(Ljava/lang/String;)Ljava/util/logging/Logger;
    //   446: putstatic 17	com/compiere/client/Secure:e	Ljava/util/logging/Logger;
    //   449: return
  }
}