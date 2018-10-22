package com.compiere.client;

import org.compiere.api.LicenseInterface;

final class LicenseCPRO
  implements LicenseInterface
{
  private boolean m_enable = false;

  void setEnabled(boolean paramBoolean)
  {
    this.m_enable = paramBoolean;
  }

  public int getUnitOne()
  {
    if (SysEnv.m == 0)
      if (this.m_enable)
        return Support.f();
    return 0;
  }

  public int getUnitTwo()
  {
    if (this.m_enable);
    return 0;
  }

  public String getStatusOne()
  {
    if (this.m_enable);
    return "";
  }

  public String getStatusTwo()
  {
    if (this.m_enable);
    return "";
  }
}