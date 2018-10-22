package com.Verisign.payment;

public class d
{
  public String a;
  public int b = 0;
  public String c = "";
  public String d = "";

  public d(String paramString)
  {
    this.a = paramString;
  }

  public String a()
  {
    return this.c;
  }

  public String b()
  {
    return this.d;
  }

  public boolean c()
  {
    this.c = "";
    while ((this.b < this.a.length()) && (this.a.charAt(this.b) != '='))
    {
      this.c += this.a.charAt(this.b);
      this.b += 1;
    }
    this.b += 1;
    this.d = "";
    while ((this.b < this.a.length()) && (this.a.charAt(this.b) != '&'))
    {
      this.d += this.a.charAt(this.b);
      this.b += 1;
    }
    this.b += 1;
    int i;
    if ((this.c.length() > 0) && (this.d.length() > 0))
    {
      i = 1;
    }
    else
    {
      this.b = 0;
      this.c = "";
      this.d = "";
      i = 0;
    }
    return i;
  }

  public static int a(String paramString, StringBuffer paramStringBuffer)
  {
    int i = 0;
    int j = 0;
    long l = paramString.length();
    char[] arrayOfChar = new char[256];
    paramString = paramString + "   ";
    paramStringBuffer.append("name = ");
    while (j < l)
    {
      int n = 0;
      int i1 = 0;
      int i2 = 0;
      int i3 = 0;
      int k;
      for (int m = k = 0; (j < l) && (paramString.charAt(j) != 0) && (paramString.charAt(j) != '='); m++)
      {
        arrayOfChar[m] = paramString.charAt(j);
        if (paramString.charAt(j) == '&')
        {
          arrayOfChar[(m + 1)] = '\000';
          paramStringBuffer.append(arrayOfChar);
          return -6;
        }
        if (arrayOfChar[m] == '[')
        {
          i2 = m;
          n = 1;
        }
        else if (arrayOfChar[m] == ']')
        {
          i3 = m;
          i1 = 1;
        }
        j++;
      }
      arrayOfChar[m] = '\000';
      if (((n != 0) && (i1 == 0)) || ((i1 != 0) && (n == 0)) || (i3 < i2))
      {
        paramStringBuffer.append(arrayOfChar);
        return -7;
      }
      if ((n != 0) && (i1 != 0))
      {
        if (arrayOfChar[(m - 1)] != ']')
        {
          paramStringBuffer.append(arrayOfChar);
          return -7;
        }
        arrayOfChar[i3] = '\000';
        String str = new String(arrayOfChar).substring(i2 + 1, i3);
        k = new Integer(str).intValue();
        arrayOfChar[i3] = ']';
      }
      if (j >= l)
      {
        paramStringBuffer.append(arrayOfChar);
        return -27;
      }
      j++;
      switch (paramString.charAt(j))
      {
      case '"':
        j++;
        while ((j < l) && (paramString.charAt(j) != 0) && (paramString.charAt(j) != '"'))
          j++;
        if ((j < l) && (paramString.charAt(j) == '"'))
        {
          j++;
        }
        else
        {
          paramStringBuffer.append(arrayOfChar);
          return -28;
        }
        if (paramString.charAt(j) == '&')
        {
          j++;
        }
        else
        {
          if (j >= l)
            continue;
          paramStringBuffer.append(arrayOfChar);
          return -28;
        }
      default:
        if (k != 0)
        {
          j += k + 1;
        }
        else
        {
          do
            if (paramString.charAt(j) != '&')
            {
              if (paramString.charAt(j) == '=')
              {
                paramStringBuffer.append(arrayOfChar);
                return -28;
              }
              j++;
            }
            else
            {
              if (paramString.charAt(j + 1) != '&')
                break;
              j += 2;
            }
          while (j < l);
          if (paramString.charAt(j) == '&')
          {
            j++;
          }
          else
          {
            if (j >= l)
              continue;
            paramStringBuffer.append(arrayOfChar);
            return -28;
          }
        }
      }
    }
    return 0;
  }
}