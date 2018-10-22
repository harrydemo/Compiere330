package com.Verisign.payment;

import java.io.File;
import java.io.FilenameFilter;

public final class g
  implements FilenameFilter
{
  public boolean accept(File paramFile, String paramString)
  {
    return paramString.endsWith(".0");
  }
}