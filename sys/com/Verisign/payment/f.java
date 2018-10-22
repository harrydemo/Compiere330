package com.Verisign.payment;

import com.sun.net.ssl.SSLContext;
import com.sun.net.ssl.TrustManagerFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.Socket;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.text.DateFormat;
import java.util.Date;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class f
{
  public SSLSocketFactory a = null;
  public static final boolean b = true;
  public static final boolean c = false;
  public static final boolean d = true;
  public static final String e = ".verisign.com";
  public static final Class[] f;
  public static final boolean g = false;
  public static Class h;

  static
  {
    Class[] tmp4_1 = new Class[1];
    Class tmp23_20 = class$("java.lang.String");
    h = h != null ? h : tmp23_20;
    tmp4_1[0] = tmp23_20;
    f = tmp4_1;
  }

  public f(String paramString)
    throws IOException, SSLException
  {
    try
    {
      java.security.cert.X509Certificate[] arrayOfX509Certificate = null;
      arrayOfX509Certificate = c(paramString);
      if (arrayOfX509Certificate.length <= 0)
      {
        localObject = new File("");
        String str1 = ((File)localObject).getAbsolutePath();
        throw new SSLException("Cert Path = " + paramString + ", Working Directory = " + str1);
      }
      Object localObject = KeyStore.getInstance("JKS");
      ((KeyStore)localObject).load(null, null);
      for (int i = 0; i < arrayOfX509Certificate.length; i++)
        ((KeyStore)localObject).setCertificateEntry("PFPRO" + i, arrayOfX509Certificate[i]);
      b("getting defaultAlgorithm...");
      String str2 = TrustManagerFactory.getDefaultAlgorithm();
      b("algorithm = " + str2);
      TrustManagerFactory localTrustManagerFactory = TrustManagerFactory.getInstance(str2);
      b("got TrustManagerFactory.");
      localTrustManagerFactory.init((KeyStore)localObject);
      DateFormat localDateFormat = DateFormat.getDateTimeInstance(0, 0);
      String str3 = localDateFormat.format(new Date());
      SecureRandom localSecureRandom = SecureRandom.getInstance("SHA1PRNG");
      localSecureRandom.setSeed(str3.getBytes());
      SSLContext localSSLContext = SSLContext.getInstance("SSLv3");
      b("got SSLContext (SSLv3).");
      localSSLContext.init(null, localTrustManagerFactory.getTrustManagers(), localSecureRandom);
      b("SSLContext initialized.");
      this.a = localSSLContext.getSocketFactory();
      b("got SSLSocketFactory.");
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new SSLException("Modify java.security file to add security provider");
    }
    catch (Exception localException)
    {
      throw new SSLException(localException.toString());
    }
  }

  private String a(java.security.cert.X509Certificate paramX509Certificate, String paramString)
  {
    String str = paramString;
    try
    {
      byte[] arrayOfByte1 = null;
      String[] arrayOfString = { null };
      Class localClass = null;
      Principal localPrincipal = paramX509Certificate.getSubjectDN();
      arrayOfString[0] = localPrincipal.getName();
      b("got SubjectName(): " + arrayOfString[0]);
      try
      {
        b("trying javax.security.auth.x500.X500Principal...");
        localClass = Class.forName("javax.security.auth.x500.X500Principal");
        b("found javax.security.auth.x500.X500Principal!");
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        b("trying com.sun.net.ssl.internal.ssl.X500Name...");
        localClass = Class.forName("com.sun.net.ssl.internal.ssl.X500Name");
        b("found com.sun.net.ssl.internal.ssl.X500Name!");
      }
      Constructor localConstructor = localClass.getConstructor(f);
      Method localMethod = localClass.getMethod("getEncoded", null);
      Object localObject = localConstructor.newInstance(arrayOfString);
      arrayOfByte1 = (byte[])localMethod.invoke(localObject, null);
      byte[] arrayOfByte2 = a(arrayOfByte1);
      str = b(arrayOfByte2);
    }
    catch (Exception localException)
    {
      b("caught: " + localException.toString());
    }
    return str;
  }

  public static Class class$(String paramString)
  {
    try
    {
      return Class.forName(paramString);
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
    }
    throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
  }

  public static java.security.cert.X509Certificate[] a(javax.security.cert.X509Certificate[] paramArrayOfX509Certificate)
    throws java.security.cert.CertificateException
  {
    java.security.cert.X509Certificate[] arrayOfX509Certificate = new java.security.cert.X509Certificate[paramArrayOfX509Certificate.length];
    try
    {
      for (int i = 0; i < arrayOfX509Certificate.length; i++)
      {
        byte[] arrayOfByte = paramArrayOfX509Certificate[i].getEncoded();
        CertificateFactory localCertificateFactory = CertificateFactory.getInstance("X.509");
        ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
        arrayOfX509Certificate[i] = ((java.security.cert.X509Certificate)localCertificateFactory.generateCertificate(localByteArrayInputStream));
      }
    }
    catch (javax.security.cert.CertificateException localCertificateException)
    {
      throw new java.security.cert.CertificateException(localCertificateException.getMessage());
    }
    return arrayOfX509Certificate;
  }

  public byte[] a(byte[] paramArrayOfByte)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.reset();
      localMessageDigest.update(paramArrayOfByte);
      return localMessageDigest.digest();
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
    }
    return null;
  }

  public java.security.cert.X509Certificate[] a(SSLSession paramSSLSession)
    throws java.security.cert.CertificateException
  {
    java.security.cert.X509Certificate[] arrayOfX509Certificate = null;
    try
    {
      try
      {
        b("trying SSLSession.getPeerCertificates()...");
        Class localClass = Class.forName("javax.net.ssl.SSLSession");
        Method localMethod = localClass.getMethod("getPeerCertificates", null);
        b("got method getPeerCertificates()...");
        arrayOfX509Certificate = (java.security.cert.X509Certificate[])localMethod.invoke(paramSSLSession, null);
        b("invoked SSLSession.getPeerCertificates()!");
      }
      catch (Exception localException1)
      {
        b(localException1.toString());
        b("trying SSLSession.getPeerCertificateChain()...");
        arrayOfX509Certificate = a(paramSSLSession.getPeerCertificateChain());
        b("invoked SSLSession.getPeerCertificateChain()!");
      }
    }
    catch (Exception localException2)
    {
      throw new java.security.cert.CertificateException(localException2.toString());
    }
    return arrayOfX509Certificate;
  }

  public SSLSocket a(Socket paramSocket, String paramString, int paramInt)
    throws IOException
  {
    SSLSocket localSSLSocket = (SSLSocket)this.a.createSocket(paramSocket, paramString, paramInt, true);
    localSSLSocket.startHandshake();
    return localSSLSocket;
  }

  public String a(int paramInt1, int paramInt2)
  {
    String str1 = new String("0123456789ABCDEF");
    String str2 = "";
    for (int i = paramInt2 - 1; i >= 0; i--)
      str2 = str2 + String.valueOf(str1.charAt(paramInt1 >> i * 4 & 0xF));
    b("hexChar output = " + str2);
    return str2;
  }

  public void b(int paramInt1, int paramInt2)
  {
  }

  public String b(byte[] paramArrayOfByte)
  {
    String str = "";
    if (paramArrayOfByte == null)
      return str;
    for (int i = 3; i >= 0; i--)
    {
      str = str + a(paramArrayOfByte[i], 2);
      b("output = " + str);
    }
    return str;
  }

  public byte[] a(int[] paramArrayOfInt)
  {
    int i = paramArrayOfInt.length;
    byte[] arrayOfByte = new byte[i];
    for (int j = 0; j < i; j++)
      arrayOfByte[j] = (byte)paramArrayOfInt[j];
    return arrayOfByte;
  }

  private java.security.cert.X509Certificate[] c(String paramString)
    throws IOException
  {
    File localFile1 = new File(paramString);
    g localg = new g();
    String[] arrayOfString = localFile1.list(localg);
    String str1 = "loadCertificateDirectory: dir (certpath) = ";
    str1 = str1 + paramString;
    b(str1);
    if (arrayOfString == null)
    {
      b("loadCertificateDirectory: either dir (certpath) does not exist, or no files found");
      return new java.security.cert.X509Certificate[0];
    }
    java.security.cert.X509Certificate[] arrayOfX509Certificate = new java.security.cert.X509Certificate[arrayOfString.length];
    byte[] arrayOfByte2 = null;
    Object localObject = null;
    try
    {
      int i = 0;
      do
      {
        b("ls[" + i + "] = " + arrayOfString[i]);
        File localFile2 = new File(localFile1, arrayOfString[i]);
        FileInputStream localFileInputStream = new FileInputStream(localFile2);
        long l = localFile2.length();
        byte[] arrayOfByte1 = new byte[(int)l];
        try
        {
          int k = 0;
          int j;
          while ((j = localFileInputStream.read(arrayOfByte1)) > 0)
          {
            k++;
            str1 = "loadCertificateDirectory: iReadCount = ";
            str1 = str1 + k;
            str1 = str1 + ", count = ";
            str1 = str1 + j;
            b(str1);
          }
          localFileInputStream.close();
          String str2 = new String(arrayOfByte1);
          String str3 = "-----BEGIN CERTIFICATE-----";
          String str4 = "-----END CERTIFICATE-----";
          int m = str2.indexOf(str3);
          int n = str2.indexOf(str4);
          b("iEnd = " + n);
          n += str4.length() + 1;
          b("iStart = " + m);
          b("iEnd = " + n);
          String str5 = str2.substring(m, n);
          b("certBody = ");
          b(str5);
          int i1 = 0;
          int i2 = 0;
          StringBuffer localStringBuffer = new StringBuffer(str5.length());
          while ((i1 = str5.indexOf('\n', i2)) != -1)
          {
            localStringBuffer.append(str5.substring(i2, i1));
            i2 = i1 + 1;
          }
          String str6 = new String(localStringBuffer);
          arrayOfByte2 = str5.getBytes();
          try
          {
            CertificateFactory localCertificateFactory = CertificateFactory.getInstance("X.509");
            ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte2);
            arrayOfX509Certificate[i] = ((java.security.cert.X509Certificate)localCertificateFactory.generateCertificate(localByteArrayInputStream));
            b(arrayOfX509Certificate[i], arrayOfString[i]);
          }
          catch (java.security.cert.CertificateException localCertificateException)
          {
            b("loadCertificateDirectory: Caught Certificate exception, removing cert...");
            str1 = "\t";
            str1 = str1 + localCertificateException.getMessage();
            b(str1);
            arrayOfX509Certificate[i] = null;
          }
        }
        catch (IOException localIOException)
        {
          b("loadCertificateDirectory: Caught IOException, ignoring...");
          str1 = "\t";
          str1 = str1 + localIOException.getMessage();
          b(str1);
        }
        i++;
        if (arrayOfString == null)
          break;
      }
      while (i < arrayOfString.length);
    }
    catch (Exception localException)
    {
      b("loadCertificateDirectory: Caught Exception, ignoring...");
      str1 = "\t";
      str1 = str1 + localException.getMessage();
      b(str1);
    }
    return arrayOfX509Certificate;
  }

  public void a(String paramString)
  {
  }

  public void c(byte[] paramArrayOfByte)
  {
  }

  public void a(byte[] paramArrayOfByte, int paramInt)
  {
  }

  private void d(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return;
    byte[] arrayOfByte = a(paramArrayOfByte);
    b("md5 = ");
    c(arrayOfByte);
    String str = b(arrayOfByte);
    b("hash = " + str);
  }

  public void a()
  {
  }

  public void b(String paramString)
  {
  }

  public void a(SSLSocket paramSSLSocket)
  {
    b("Removing SSL Session cache");
  }

  public java.security.cert.X509Certificate[] b()
  {
    int[] arrayOfInt = { 48, 130, 2, 41, 48, 130, 1, 150, 2, 5, 2, 65, 0, 0, 1, 48, 13, 6, 9, 42, 134, 72, 134, 247, 13, 1, 1, 2, 5, 0, 48, 95, 49, 11, 48, 9, 6, 3, 85, 4, 6, 19, 2, 85, 83, 49, 32, 48, 30, 6, 3, 85, 4, 10, 19, 23, 82, 83, 65, 32, 68, 97, 116, 97, 32, 83, 101, 99, 117, 114, 105, 116, 121, 44, 32, 73, 110, 99, 46, 49, 46, 48, 44, 6, 3, 85, 4, 11, 19, 37, 83, 101, 99, 117, 114, 101, 32, 83, 101, 114, 118, 101, 114, 32, 67, 101, 114, 116, 105, 102, 105, 99, 97, 116, 105, 111, 110, 32, 65, 117, 116, 104, 111, 114, 105, 116, 121, 48, 30, 23, 13, 57, 52, 49, 49, 48, 57, 50, 51, 53, 52, 49, 55, 90, 23, 13, 57, 57, 49, 50, 51, 49, 50, 51, 53, 52, 49, 55, 90, 48, 95, 49, 11, 48, 9, 6, 3, 85, 4, 6, 19, 2, 85, 83, 49, 32, 48, 30, 6, 3, 85, 4, 10, 19, 23, 82, 83, 65, 32, 68, 97, 116, 97, 32, 83, 101, 99, 117, 114, 105, 116, 121, 44, 32, 73, 110, 99, 46, 49, 46, 48, 44, 6, 3, 85, 4, 11, 19, 37, 83, 101, 99, 117, 114, 101, 32, 83, 101, 114, 118, 101, 114, 32, 67, 101, 114, 116, 105, 102, 105, 99, 97, 116, 105, 111, 110, 32, 65, 117, 116, 104, 111, 114, 105, 116, 121, 48, 129, 155, 48, 13, 6, 9, 42, 134, 72, 134, 247, 13, 1, 1, 1, 5, 0, 3, 129, 137, 0, 48, 129, 133, 2, 126, 0, 146, 206, 122, 193, 174, 131, 62, 90, 170, 137, 131, 87, 172, 37, 1, 118, 12, 173, 174, 142, 44, 55, 206, 235, 53, 120, 100, 84, 3, 229, 132, 64, 81, 201, 191, 143, 8, 226, 138, 130, 8, 210, 22, 134, 55, 85, 233, 177, 33, 2, 173, 118, 104, 129, 154, 5, 162, 75, 201, 75, 37, 102, 34, 86, 108, 136, 7, 143, 247, 129, 89, 109, 132, 7, 101, 112, 19, 113, 118, 62, 155, 119, 76, 227, 80, 137, 86, 152, 72, 185, 29, 167, 41, 26, 19, 46, 74, 17, 89, 156, 30, 21, 213, 73, 84, 44, 115, 58, 105, 130, 177, 151, 57, 156, 109, 112, 103, 72, 229, 221, 45, 214, 200, 30, 123, 2, 3, 1, 0, 1, 48, 13, 6, 9, 42, 134, 72, 134, 247, 13, 1, 1, 2, 5, 0, 3, 126, 0, 136, 209, 209, 121, 33, 206, 226, 139, 232, 248, 193, 125, 52, 83, 63, 97, 131, 217, 182, 11, 56, 23, 182, 232, 190, 33, 141, 143, 0, 184, 139, 83, 126, 68, 103, 30, 34, 189, 151, 39, 224, 156, 133, 204, 74, 246, 133, 59, 178, 226, 190, 146, 211, 229, 13, 233, 175, 92, 14, 12, 70, 149, 255, 161, 28, 94, 62, 232, 54, 88, 122, 115, 166, 10, 248, 34, 17, 107, 195, 9, 56, 126, 38, 187, 115, 239, 0, 189, 2, 164, 243, 20, 13, 48, 63, 97, 112, 123, 32, 254, 50, 163, 159, 179, 244, 103, 82, 220, 180, 238, 132, 140, 150, 54, 32, 222, 129, 8, 131, 113, 33, 138, 15, 158, 169 };
    byte[] arrayOfByte = a(arrayOfInt);
    byte[][] arrayOfByte1 = { arrayOfByte };
    int i = arrayOfByte1.length;
    java.security.cert.X509Certificate[] arrayOfX509Certificate = new java.security.cert.X509Certificate[i];
    for (int j = 0; j < i; j++)
      try
      {
        CertificateFactory localCertificateFactory = CertificateFactory.getInstance("X.509");
        ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte1[j]);
        arrayOfX509Certificate[j] = ((java.security.cert.X509Certificate)localCertificateFactory.generateCertificate(localByteArrayInputStream));
      }
      catch (java.security.cert.CertificateException localCertificateException)
      {
      }
    return arrayOfX509Certificate;
  }

  private void b(java.security.cert.X509Certificate paramX509Certificate, String paramString)
    throws java.security.cert.CertificateException
  {
    String str1 = "";
    try
    {
      if (paramX509Certificate != null)
      {
        String str2 = a(paramX509Certificate, paramString);
        b("hash = " + str2);
        if (paramString.equalsIgnoreCase(str2 + ".0"))
        {
          paramX509Certificate.verify(paramX509Certificate.getPublicKey());
          return;
        }
      }
    }
    catch (Exception localException)
    {
      b("caught: " + localException.toString());
      str1 = localException.toString();
    }
    throw new java.security.cert.CertificateException("Certificate validation failed. " + str1);
  }

  public int a(SSLSocket paramSSLSocket, String paramString)
    throws java.security.cert.CertificateException
  {
    java.security.cert.X509Certificate[] arrayOfX509Certificate = null;
    try
    {
      b("strServerName = " + paramString);
      b("PAYFLOW_SERVER = .verisign.com");
      b("session = " + (paramSSLSocket.getSession() == null ? "null" : "not null"));
      try
      {
        paramSSLSocket.getOutputStream();
        paramSSLSocket.getInputStream();
        b("session = " + (paramSSLSocket.getSession() == null ? "null" : "not null"));
        arrayOfX509Certificate = a(paramSSLSocket.getSession());
        b("clientChain = " + (arrayOfX509Certificate == null ? "null" : "not null"));
        if ((arrayOfX509Certificate == null) || (arrayOfX509Certificate.length == 0))
          throw new java.security.cert.CertificateException("Server certificate is empty");
      }
      catch (IOException localIOException)
      {
        b("verifyCertificate() - IO Exception: " + localIOException.toString());
        return -1;
      }
      Principal localPrincipal = arrayOfX509Certificate[0].getSubjectDN();
      String str1 = localPrincipal.getName();
      int i = str1.indexOf("CN=") + 3;
      int j = str1.indexOf(",", i);
      j = j < 0 ? str1.length() : j;
      String str2 = str1.substring(i, j).toLowerCase();
      b("commonName = " + str2);
      if ((!str2.equalsIgnoreCase(paramString)) && (!str2.endsWith(".verisign.com")))
        throw new java.security.cert.CertificateException("Server Common Name = " + str2);
    }
    catch (java.security.cert.CertificateException localCertificateException)
    {
      throw localCertificateException;
    }
    return 0;
  }
}