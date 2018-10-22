package com.Verisign.payment;

public final class PFProAPI extends b
{
  public int CreateContext(String paramString1, int paramInt1, int paramInt2, String paramString2, int paramInt3, String paramString3, String paramString4)
  {
    return a(paramString1, paramInt1, paramInt2, paramString2, paramInt3, paramString3, paramString4);
  }

  public int DestroyContext()
  {
    return a();
  }

  public void SetCertPath(String paramString)
  {
    a(paramString);
  }

  public synchronized String SubmitTransaction(String paramString)
  {
    return b(paramString);
  }

  public String Version()
  {
    return b();
  }
}