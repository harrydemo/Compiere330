package com.compiere.client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import org.compiere.Compiere;
import org.compiere.apps.AEnv;
import org.compiere.apps.ALayout;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.apps.ConfirmPanel;
import org.compiere.db.CConnection;
import org.compiere.model.MEntityType;
import org.compiere.model.MRefList;
import org.compiere.model.MSystem;
import org.compiere.swing.CButton;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CFrame;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CPassword;
import org.compiere.swing.CTextArea;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogErrorBuffer;
import org.compiere.util.CLogger;
import org.compiere.util.CPreparedStatement;
import org.compiere.util.Ctx;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.NamePair;
import org.compiere.util.Trx;
import org.compiere.util.Util;

public final class Support extends CFrame
  implements ActionListener
{
  public static final String TITLE;
  public static final String COPYRIGHT;
  static final boolean a = false;
  static final boolean b = false;
  static final String c;
  static final String d;
  static final String e;
  private static final String f;
  private static final long serialVersionUID = -875163484858750714L;
  private static CLogger g;
  private List h = null;
  private final Ctx i = Env.getCtx();
  private MSystem j = null;
  private static int k;
  private boolean l = false;
  private String m = "";
  private String n = "";
  private String o = "";
  private String p = "";
  private String q = "";
  private String r = "";
  private final ConfirmPanel s = new ConfirmPanel(true);
  private final CPanel t = new CPanel(new ALayout(5, 4, true));
  private final CTextField u = new CTextField(10);
  private final CLabel v = new CLabel(Msg.getMsg(this.i, Y[3], true), this.u);
  private CComboBox w = null;
  private CLabel x = null;
  private final CTextField y = new CTextField(10);
  private final CLabel z = new CLabel(Y[56], this.y);
  private final CPassword A = new CPassword(10);
  private final CLabel B = new CLabel(Msg.getElement(this.i, Y[52]), this.A);
  private final CTextField C = new CTextField(10);
  private final CLabel D = new CLabel(Msg.translate(this.i, Y[54]), this.y);
  private final CTextField E = new CTextField(15);
  private final CLabel F = new CLabel(Msg.translate(this.i, Y[62]), this.E);
  private final CTextField G = new CTextField(10);
  private final CLabel H = new CLabel(Msg.translate(this.i, Y[59]), this.G);
  private final CTextField I = new CTextField(10);
  private final CLabel J = new CLabel(Msg.translate(this.i, Y[58]), this.I);
  private final CTextField K = new CTextField(10);
  private final CLabel L = new CLabel(Msg.getMsg(this.i, Y[8], true), this.K);
  private final CCheckBox M = new CCheckBox(Msg.getMsg(this.i, Y[12], true));
  private final CTextField N = new CTextField(10);
  private final CLabel O = new CLabel(Msg.translate(this.i, Y[55]), this.N);
  private final CTextField P = new CTextField(15);
  private final CLabel Q = new CLabel(Msg.translate(this.i, Y[57]), this.P);
  private final CTextField R = new CTextField(10);
  private final CLabel S = new CLabel(Msg.translate(this.i, Y[61]), this.R);
  private final CPassword T = new CPassword(10);
  private final CLabel U = new CLabel(Msg.translate(this.i, Y[60]), this.T);
  private final CTextArea V = new CTextArea(3, 30);
  private final CTextArea W = new CTextArea(3, 30);
  private CButton X = null;
  private static final String[] Y;

  static void a(Object paramObject)
  {
    int i1 = SysEnv.m;
    if ((i1 != 0) || (paramObject != null))
    {
      if (i1 != 0)
        break label25;
      if (!(paramObject instanceof InstallInfo))
        return;
    }
    label25: CLogErrorBuffer localCLogErrorBuffer = CLogErrorBuffer.get(true);
    if (i1 == 0)
    {
      if ((localCLogErrorBuffer != null) && ((i1 != 0) || (localCLogErrorBuffer.isIssueError())))
        localCLogErrorBuffer.setIssueError(false);
      new g(paramObject).start();
    }
  }

  static String a()
  {
    String str = System.getProperty(Y[83], d);
    return c + str + f;
  }

  Support(InstallInfo paramInstallInfo)
  {
    super(TITLE);
    setIconImage(Compiere.getImage16());
    if (i1 == 0)
    {
      if (paramInstallInfo != null)
      {
        if (i1 == 0)
        {
          this.h = paramInstallInfo.get(false);
          if (g != null)
            g.info(paramInstallInfo.b(false));
        }
        if (i1 == 0)
        {
          if (this.h != null)
            if (i1 != 0)
              break label770;
        }
        else
          if (this.h.size() > 0)
            break label761;
        localObject = Y[53];
        JOptionPane.showMessageDialog(this, localObject, TITLE, 1);
        if (i1 != 0)
          break label769;
        if (g != null)
          g.info((String)localObject);
      }
      label761: b();
      pack();
    }
    label769: label770: Object localObject = getSize();
    ((Dimension)localObject).width = 600;
    setPreferredSize((Dimension)localObject);
    AEnv.showCenterScreen(this);
    this.j.setInfo();
    if (InstallInfo.f)
    {
      i1++;
      SysEnv.m = i1;
    }
  }

  private void b()
  {
    int i3 = SysEnv.m;
    this.j = MSystem.get(this.i);
    this.w = new CComboBox(MRefList.getList(374, false), this.j.getSystemStatus());
    this.x = new CLabel(Msg.translate(this.i, Y[4]), this.w);
    c();
    int i1 = 0;
    this.v.setToolTipText(Msg.getMsg(this.i, Y[3], false));
    this.u.setText(this.j.getName());
    this.t.add(this.v, new ALayoutConstraint(i1, 0));
    this.t.add(this.u, null);
    this.t.add(this.x, null);
    this.t.add(this.w, null);
    this.w.setValue(this.j.getSystemStatus());
    this.y.setText(this.j.getUserName());
    this.A.setText(this.j.getPassword());
    i1++;
    this.t.add(this.z, new ALayoutConstraint(i1, 0));
    this.t.add(this.y, null);
    this.t.add(this.B, null);
    this.t.add(this.A, null);
    this.C.setText(this.j.getSupportEMail());
    i1++;
    this.t.add(this.D, new ALayoutConstraint(i1, 0));
    this.t.add(this.C, null);
    String str = d();
    this.j.setDBAddress(str);
    this.E.setText(str);
    this.E.setReadWrite(false);
    int i2 = f();
    this.G.setText(String.valueOf(i2));
    this.G.setReadWrite(false);
    i1++;
    this.t.add(this.F, new ALayoutConstraint(i1, 0));
    this.t.add(this.E, null);
    this.t.add(this.H, null);
    this.t.add(this.G, null);
    this.I.setReadWrite(false);
    this.K.setReadWrite(false);
    this.L.setToolTipText(Msg.getMsg(this.i, Y[8], false));
    i1++;
    this.t.add(this.J, new ALayoutConstraint(i1, 0));
    this.t.add(this.I, null);
    this.t.add(this.L, null);
    this.t.add(this.K, null);
    this.M.setReadWrite(false);
    this.M.setToolTipText(Msg.getMsg(this.i, Y[12], false));
    if (i3 == 0);
    this.M.setSelected(this.j.getRecord_ID() != 0);
    if (i3 == 0)
      if (this.j.getRecord_ID() == 0)
      {
        this.M.setToolTipText(Msg.getMsg(this.i, Y[12], false));
        if (i3 == 0)
          break label654;
      }
    this.M.setToolTipText(Y[10] + this.j.getRecord_ID());
    label654: i1++;
    this.t.add(this.M, new ALayoutConstraint(i1, 1));
    this.N.setReadWrite(false);
    this.t.add(this.O, null);
    this.t.add(this.N, null);
    a(Y[2]);
    this.P.setText(this.p);
    this.R.setText(this.q);
    this.R.setToolTipText(this.S.getText());
    this.T.setText(this.r);
    this.T.setToolTipText(this.U.getText());
    i1++;
    this.t.add(this.Q, new ALayoutConstraint(i1, 0));
    this.t.add(this.P, null);
    i1++;
    this.t.add(this.S, new ALayoutConstraint(i1, 0));
    this.t.add(this.R, null);
    this.t.add(this.U, null);
    this.t.add(this.T, null);
    CPanel localCPanel = new CPanel(new BorderLayout());
    localCPanel.add(this.V, Y[7]);
    this.V.setReadWrite(false);
    localCPanel.add(this.W, Y[9]);
    this.W.setReadWrite(false);
    getContentPane().add(this.t, Y[7]);
    getContentPane().add(localCPanel, Y[9]);
    getContentPane().add(this.s, Y[6]);
    this.X = ConfirmPanel.createProcessButton(true);
    this.X.setText(Msg.getMsg(Env.getCtx(), Y[11]));
    this.X.setEnabled(false);
    this.X.addActionListener(this);
    this.s.getOKButton().setText(Y[5]);
    this.s.addActionListener(this);
    this.s.addButton(this.X);
  }

  private void c()
  {
    int i2 = SysEnv.m;
    boolean bool = false;
    SysEnv.a();
    this.V.setText("");
    SysEnv localSysEnv1 = SysEnv.get(null);
    if (i2 == 0)
    {
      if (localSysEnv1 != null)
        this.V.setText(localSysEnv1.a(Y[76], Y[77], null, null, Y[75]));
    }
    else
    {
      bool = localSysEnv1.isLicensed();
      if (i2 != 0)
        break label93;
      if (bool)
        this.I.setText(localSysEnv1.getGuaranteeDate().toString());
    }
    label93: if (!bool);
    if (i2 == 0)
    {
      this.l = bool;
      if (!bool)
      {
        this.I.setText("");
        this.K.setText("");
        this.N.setText("");
      }
    }
    if (i2 == 0)
    {
      if (this.X != null)
        this.X.setEnabled(bool);
      this.P.setEnabled(bool);
      this.R.setEnabled(bool);
      this.T.setEnabled(bool);
    }
    MEntityType[] arrayOfMEntityType = MEntityType.getEntityTypes(this.i, true);
    int i1 = 0;
    do
    {
      if (i1 >= arrayOfMEntityType.length)
        break;
      MEntityType localMEntityType = arrayOfMEntityType[i1];
      if ((i2 != 0) || (localMEntityType.isLicensable()))
      {
        SysEnv localSysEnv2 = SysEnv.get(localMEntityType.getEntityType());
        if (i2 != 0)
          continue;
        if (localSysEnv2 != null)
          this.V.addText(localSysEnv2.a(localMEntityType.getName(), Y[77], null, null, null));
      }
      i1++;
    }
    while (i2 == 0);
  }

  private String d()
  {
    int i1 = SysEnv.m;
    CConnection localCConnection = CConnection.get();
    this.m = localCConnection.getConnectionURL();
    this.n = localCConnection.getDbUid();
    this.o = localCConnection.getDbPwd();
    if ((i1 != 0) || (this.h == null))
    {
      this.p = this.m;
      if (i1 == 0);
    }
    else
    {
      String str = System.getenv(Y[15]);
      str = str + File.separator + Y[14] + File.separator + Y[13];
      File localFile = new File(str);
      try
      {
        this.p = localFile.toURI().toURL().toString();
      }
      catch (Exception localException)
      {
        this.p = "";
      }
    }
    return e();
  }

  static String e()
  {
    CConnection localCConnection = CConnection.get();
    String str1 = localCConnection.getConnectionURL();
    String str2 = localCConnection.getDbUid();
    String str3 = str1 + "#" + str2;
    return str3.toLowerCase();
  }

  static int f()
  {
    int i1 = SysEnv.m;
    if (i1 == 0)
      if (k != -1)
        return k;
    k = 9999;
    String str = Y[0];
    CPreparedStatement localCPreparedStatement = null;
    try
    {
      localCPreparedStatement = DB.prepareStatement(str, (Trx)null);
      ResultSet localResultSet = localCPreparedStatement.executeQuery();
      if (i1 == 0)
        if (localResultSet.next())
          k = localResultSet.getInt(1);
      localResultSet.close();
      localCPreparedStatement.close();
      localCPreparedStatement = null;
    }
    catch (Exception localException1)
    {
      k = -2;
      if ((i1 != 0) || (g != null))
        g.log(Level.SEVERE, Y[1], localException1);
    }
    try
    {
      if ((i1 != 0) || (localCPreparedStatement != null))
        localCPreparedStatement.close();
      localCPreparedStatement = null;
    }
    catch (Exception localException2)
    {
      localCPreparedStatement = null;
    }
    return k;
  }

  static String g()
  {
    int i1 = SysEnv.m;
    String str1 = null;
    String str2 = DB.TO_CHAR(Y[19], 22, Env.getAD_Language(Env.getCtx()));
    String str3 = Y[32] + str2 + Y[37] + Y[30] + str2 + Y[20] + Y[31] + str2 + Y[22] + Y[25] + str2 + Y[16] + Y[26] + str2 + Y[33] + Y[34] + str2 + Y[23] + Y[24] + str2 + Y[36] + Y[27] + str2 + Y[18] + Y[21] + str2 + Y[28] + Y[17] + str2 + Y[35] + Y[29];
    CPreparedStatement localCPreparedStatement = null;
    try
    {
      localCPreparedStatement = DB.prepareStatement(str3, (Trx)null);
      ResultSet localResultSet = localCPreparedStatement.executeQuery();
      if ((i1 != 0) || (localResultSet.next()))
        str1 = localResultSet.getString(1);
      localResultSet.close();
      localCPreparedStatement.close();
      localCPreparedStatement = null;
    }
    catch (Exception localException1)
    {
      if ((i1 != 0) || (g != null))
        g.log(Level.SEVERE, Y[38], localException1);
    }
    try
    {
      if ((i1 != 0) || (localCPreparedStatement != null))
        localCPreparedStatement.close();
      localCPreparedStatement = null;
    }
    catch (Exception localException2)
    {
      localCPreparedStatement = null;
    }
    return str1;
  }

  static String h()
  {
    int i1 = SysEnv.m;
    String str = Y[51];
    CPreparedStatement localCPreparedStatement = null;
    StringBuffer localStringBuffer = new StringBuffer();
    try
    {
      localCPreparedStatement = DB.prepareStatement(str, (Trx)null);
      ResultSet localResultSet = localCPreparedStatement.executeQuery();
      while (true)
        if (localResultSet.next())
        {
          localStringBuffer.append(localResultSet.getString(1)).append('|');
          if (i1 != 0)
            break;
          if (i1 == 0)
            continue;
        }
        else
        {
          localResultSet.close();
          localCPreparedStatement.close();
        }
      localCPreparedStatement = null;
    }
    catch (Exception localException1)
    {
      if ((i1 != 0) || (g != null))
        g.log(Level.SEVERE, Y[50], localException1);
    }
    try
    {
      if ((i1 != 0) || (localCPreparedStatement != null))
        localCPreparedStatement.close();
      localCPreparedStatement = null;
    }
    catch (Exception localException2)
    {
      localCPreparedStatement = null;
    }
    return localStringBuffer.toString();
  }

  static String i()
  {
    int i1 = SysEnv.m;
    Object localObject = null;
    try
    {
      Trx localTrx = Trx.get(Y[65]);
      DatabaseMetaData localDatabaseMetaData = localTrx.getConnection().getMetaData();
      localObject = localDatabaseMetaData.getDatabaseProductName();
      String str = localDatabaseMetaData.getDatabaseProductVersion();
      if (i1 == 0)
        if (str.startsWith((String)localObject))
        {
          localObject = str;
          if (i1 == 0)
            break label85;
        }
      localObject = (String)localObject + "-" + str;
      label85: if (i1 == 0)
      {
        if (((String)localObject).length() > 60)
        {
          localObject = Util.replace((String)localObject, Y[66], "");
          localObject = Util.replace((String)localObject, Y[67], "");
          localObject = Util.replace((String)localObject, Y[63], "");
          localObject = Util.replace((String)localObject, Y[68], "");
        }
        localObject = Util.removeCRLF((String)localObject);
      }
      localTrx.close();
    }
    catch (Exception localException)
    {
      if ((i1 != 0) || (g != null))
        g.log(Level.SEVERE, Y[64], localException);
    }
    return (String)localObject;
  }

  public void actionPerformed(ActionEvent paramActionEvent)
  {
    int i1 = SysEnv.m;
    if (i1 == 0)
    {
      if (paramActionEvent.getActionCommand().equals(Y[41]))
      {
        dispose();
        if (i1 == 0)
          return;
      }
      if (i1 != 0);
    }
    else if (paramActionEvent.getActionCommand().equals(Y[43]))
    {
      try
      {
        this.l = j();
      }
      catch (Exception localException)
      {
        if (i1 == 0)
        {
          if (g != null)
            g.log(Level.SEVERE, "t", localException);
          this.s.getOKButton().setEnabled(false);
          a(Y[40]);
        }
        if (i1 == 0)
          return;
      }
    }
    if (paramActionEvent.getSource() == this.X)
    {
      if (i1 == 0)
        if (this.h == null)
        {
          if (i1 == 0)
            if (this.P.getText().length() != 0)
            {
              if (i1 != 0)
                break label201;
              if (this.R.getText().length() != 0)
              {
                if (i1 != 0)
                  break label207;
                if (this.T.getPassword().length != 0)
                  break label206;
              }
            }
          label201: JOptionPane.showConfirmDialog(this, Y[39], Y[42], -1, 0);
          if (i1 == 0)
            return;
        }
      label206: label207: k();
    }
  }

  private boolean j()
    throws Exception
  {
    int i1 = SysEnv.m;
    this.l = false;
    this.s.getOKButton().setEnabled(false);
    a(Y[47]);
    String str1 = this.u.getText();
    this.j.setName(str1);
    String str2 = this.y.getText();
    this.j.setUserName(str2);
    String str3 = (String)this.A.getValue();
    this.j.setPassword(str3);
    String str4 = this.C.getText();
    if ((i1 != 0) || (this.j.getReleaseNo().compareTo(Y[46]) >= 0))
      this.j.setSupportEMail(str4);
    String str5 = null;
    NamePair localNamePair = (NamePair)this.w.getSelectedItem();
    if ((i1 != 0) || (localNamePair != null))
    {
      str5 = localNamePair.getID();
      this.j.setSystemStatus(str5);
    }
    String str6 = this.j.getReleaseNo();
    String str7 = this.j.getVersion();
    if (i1 == 0)
      if (str6.compareTo(Y[46]) >= 0)
        this.j.save();
    String str8 = "";
    if (i1 == 0)
      if (str6.compareTo(Y[46]) >= 0)
        str8 = this.j.getSupportEMail();
    String str9 = i();
    this.j.setDBInstance(str9);
    c localc = new c(g);
    boolean bool = localc.a(this.j.getName(), this.j.getUserName(), this.j.getPassword(), f(), g(), h(), this.j.getDBAddress(true), this.j.getDBInstance(), this.j.getSystemStatus(), str8, this.j.getRecord_ID(), str6, str7, null);
    if (i1 == 0)
    {
      if (!bool)
      {
        this.s.getOKButton().setEnabled(true);
        a(Y[45]);
      }
    }
    else
      return false;
    String str10 = localc.a();
    this.j.setSummary(str10);
    if (i1 == 0)
      if (str6.compareTo(Y[46]) >= 0)
      {
        new e(this.j);
        if (i1 != 0)
          break label533;
        if (this.j.save())
          break label528;
        if ((i1 != 0) || (g != null))
          g.log(Level.SEVERE, Y[49]);
        a(Y[48]);
        if (i1 == 0)
          break label528;
      }
    if (i1 == 0)
      if (!this.j.saveLicenseOnly())
      {
        if ((i1 != 0) || (g != null))
          g.log(Level.SEVERE, Y[49]);
        a(Y[48]);
      }
    label528: label533: if (str10.length() >= 200)
    {
      a(Y[44]);
      if (i1 == 0);
    }
    else
    {
      this.s.getOKButton().setEnabled(true);
      if (i1 == 0)
      {
        if (this.X != null)
          this.X.setEnabled(false);
        this.P.setEnabled(false);
        this.R.setEnabled(false);
        this.T.setEnabled(false);
        a(localc, str8);
      }
      c();
      return false;
    }
    this.l = true;
    a(localc, str8);
    c();
    this.s.getOKButton().setEnabled(true);
    return this.l;
  }

  private void a(c paramc, String paramString)
  {
    int i3 = SysEnv.m;
    String str1 = Y[80];
    MEntityType[] arrayOfMEntityType = MEntityType.getEntityTypes(this.i, true);
    int i1 = 0;
    label330: 
    do
    {
      if (i1 >= arrayOfMEntityType.length)
        break;
      MEntityType localMEntityType = arrayOfMEntityType[i1];
      if ((i3 != 0) || (localMEntityType.isLicensable()))
      {
        String str2 = localMEntityType.getVersion();
        String str3 = localMEntityType.getEntityType();
        SysEnv localSysEnv = SysEnv.get(str3);
        if (i3 == 0);
        int i2 = localSysEnv == null ? 0 : localSysEnv.getUnitOne();
        boolean bool = paramc.a(this.j.getName(), this.j.getUserName(), this.j.getPassword(), i2, g(), h(), this.j.getDBAddress(true), this.j.getDBInstance(), this.j.getSystemStatus(), paramString, this.j.getRecord_ID(), str1, str2, localMEntityType.getEntityType());
        if (i3 == 0)
        {
          if (!bool)
            a(localMEntityType.getName() + Y[78]);
        }
        else
          if (i3 == 0)
            break label330;
        String str4 = paramc.a();
        if (i3 == 0)
        {
          if (str4.length() >= 200)
          {
            localMEntityType.setSummary(str4);
            a(localMEntityType.getName() + Y[79]);
            if (i3 == 0);
          }
          else
          {
            localMEntityType.setSummary(null);
          }
        }
        else
          a(localMEntityType.getName() + Y[81] + str4);
        localMEntityType.save();
      }
      i1++;
    }
    while (i3 == 0);
  }

  private void a(String paramString)
  {
    int i1 = SysEnv.m;
    String str = this.W.getText();
    if (i1 == 0)
      if (str != null)
      {
        if (i1 != 0)
          break label61;
        if (str.length() != 0)
          break label37;
      }
    str = paramString;
    if (i1 != 0)
      label37: label61: str = str + "\n" + paramString;
    this.W.setText(str);
    this.W.setCaretPosition(str.length());
  }

  private void k()
  {
    int i1 = SysEnv.m;
    try
    {
      URL localURL = new URL(a());
      ClassLoader localClassLoader = Thread.currentThread().getContextClassLoader();
      URLClassLoader localURLClassLoader = new URLClassLoader(new URL[] { localURL }, localClassLoader);
      Class localClass = null;
      if (localURLClassLoader != null)
      {
        if ((i1 != 0) || (g != null))
          g.config(Y[71]);
        localClass = localURLClassLoader.loadClass(Y[70]);
        if ((i1 != 0) || (g != null))
          g.config(Y[73]);
      }
      if (localClass != null)
      {
        if ((i1 != 0) || (g != null))
          g.config(Y[74]);
        Object localObject = null;
        Constructor localConstructor;
        if (this.h == null)
        {
          localConstructor = localClass.getConstructor(new Class[] { String.class, String.class, String.class, String.class, String.class, String.class });
          this.p = this.P.getText();
          this.q = this.R.getText();
          this.r = new String(this.T.getPassword());
          if (i1 == 0)
            if (g != null)
              g.config(Y[69]);
          localObject = localConstructor.newInstance(new Object[] { this.p, this.q, this.r, this.m, this.n, this.o });
        }
        else
        {
          localConstructor = localClass.getConstructor(new Class[] { List.class, String.class, String.class, String.class });
          if (i1 == 0)
            if (g != null)
              g.config(Y[69]);
          localObject = localConstructor.newInstance(new Object[] { this.h, this.m, this.n, this.o });
        }
        dispose();
        localObject.toString();
      }
    }
    catch (Exception localException)
    {
      g.severe(Y[72]);
    }
  }

  public String startEntityType(MEntityType paramMEntityType, String paramString1, String paramString2)
  {
    int i1 = SysEnv.m;
    String str1 = "";
    c localc = new c(g);
    boolean bool = localc.a(this.j.getName(), this.j.getUserName(), this.j.getPassword(), f(), g(), h(), this.j.getDBAddress(true), i(), this.j.getSystemStatus(), str1, this.j.getRecord_ID(), paramString1, paramString2, paramMEntityType.getEntityType());
    if (!bool)
      return Y[45];
    String str2 = localc.a();
    if (i1 == 0)
      if (str2.length() >= 200)
      {
        paramMEntityType.setSummary(str2);
        paramMEntityType.save();
        if (i1 == 0)
          break label145;
      }
    return str2;
    label145: return Y[82];
  }

  public static void main(String[] paramArrayOfString)
  {
    Compiere.startup(true, false, null);
    Object localObject = null;
    a(localObject);
  }

  static
  {
    // Byte code:
    //   0: bipush 84
    //   2: anewarray 279	java/lang/String
    //   5: dup
    //   6: iconst_0
    //   7: ldc 9
    //   9: bipush 83
    //   11: goto +998 -> 1009
    //   14: putstatic 1039	com/compiere/client/Support:d	Ljava/lang/String;
    //   17: ldc 15
    //   19: bipush 84
    //   21: goto +988 -> 1009
    //   24: putstatic 1045	com/compiere/client/Support:f	Ljava/lang/String;
    //   27: ldc 17
    //   29: bipush 85
    //   31: goto +978 -> 1009
    //   34: putstatic 1043	com/compiere/client/Support:TITLE	Ljava/lang/String;
    //   37: ldc_w 364
    //   40: bipush 86
    //   42: goto +967 -> 1009
    //   45: putstatic 1037	com/compiere/client/Support:e	Ljava/lang/String;
    //   48: ldc_w 356
    //   51: bipush 87
    //   53: goto +956 -> 1009
    //   56: putstatic 1041	com/compiere/client/Support:COPYRIGHT	Ljava/lang/String;
    //   59: ldc 13
    //   61: bipush 88
    //   63: goto +946 -> 1009
    //   66: putstatic 1035	com/compiere/client/Support:c	Ljava/lang/String;
    //   69: ldc 199
    //   71: bipush 255
    //   73: goto +936 -> 1009
    //   76: aastore
    //   77: dup
    //   78: iconst_1
    //   79: ldc 208
    //   81: iconst_0
    //   82: goto +927 -> 1009
    //   85: aastore
    //   86: dup
    //   87: iconst_2
    //   88: ldc 140
    //   90: iconst_1
    //   91: goto +918 -> 1009
    //   94: aastore
    //   95: dup
    //   96: iconst_3
    //   97: ldc 43
    //   99: iconst_2
    //   100: goto +909 -> 1009
    //   103: aastore
    //   104: dup
    //   105: iconst_4
    //   106: ldc 116
    //   108: iconst_3
    //   109: goto +900 -> 1009
    //   112: aastore
    //   113: dup
    //   114: iconst_5
    //   115: ldc 160
    //   117: iconst_4
    //   118: goto +891 -> 1009
    //   121: aastore
    //   122: dup
    //   123: bipush 6
    //   125: ldc 152
    //   127: iconst_5
    //   128: goto +881 -> 1009
    //   131: aastore
    //   132: dup
    //   133: bipush 7
    //   135: ldc 147
    //   137: bipush 6
    //   139: goto +870 -> 1009
    //   142: aastore
    //   143: dup
    //   144: bipush 8
    //   146: ldc 72
    //   148: bipush 7
    //   150: goto +859 -> 1009
    //   153: aastore
    //   154: dup
    //   155: bipush 9
    //   157: ldc 149
    //   159: bipush 8
    //   161: goto +848 -> 1009
    //   164: aastore
    //   165: dup
    //   166: bipush 10
    //   168: ldc 138
    //   170: bipush 9
    //   172: goto +837 -> 1009
    //   175: aastore
    //   176: dup
    //   177: bipush 11
    //   179: ldc 154
    //   181: bipush 10
    //   183: goto +826 -> 1009
    //   186: aastore
    //   187: dup
    //   188: bipush 12
    //   190: ldc 75
    //   192: bipush 11
    //   194: goto +815 -> 1009
    //   197: aastore
    //   198: dup
    //   199: bipush 13
    //   201: ldc 188
    //   203: bipush 12
    //   205: goto +804 -> 1009
    //   208: aastore
    //   209: dup
    //   210: bipush 14
    //   212: ldc 187
    //   214: bipush 13
    //   216: goto +793 -> 1009
    //   219: aastore
    //   220: dup
    //   221: bipush 15
    //   223: ldc 184
    //   225: bipush 14
    //   227: goto +782 -> 1009
    //   230: aastore
    //   231: dup
    //   232: bipush 16
    //   234: ldc 220
    //   236: bipush 15
    //   238: goto +771 -> 1009
    //   241: aastore
    //   242: dup
    //   243: bipush 17
    //   245: ldc 231
    //   247: bipush 16
    //   249: goto +760 -> 1009
    //   252: aastore
    //   253: dup
    //   254: bipush 18
    //   256: ldc 228
    //   258: bipush 17
    //   260: goto +749 -> 1009
    //   263: aastore
    //   264: dup
    //   265: bipush 19
    //   267: ldc 210
    //   269: bipush 18
    //   271: goto +738 -> 1009
    //   274: aastore
    //   275: dup
    //   276: bipush 20
    //   278: ldc 216
    //   280: bipush 19
    //   282: goto +727 -> 1009
    //   285: aastore
    //   286: dup
    //   287: bipush 21
    //   289: ldc 229
    //   291: bipush 20
    //   293: goto +716 -> 1009
    //   296: aastore
    //   297: dup
    //   298: bipush 22
    //   300: ldc 218
    //   302: bipush 21
    //   304: goto +705 -> 1009
    //   307: aastore
    //   308: dup
    //   309: bipush 23
    //   311: ldc 224
    //   313: bipush 22
    //   315: goto +694 -> 1009
    //   318: aastore
    //   319: dup
    //   320: bipush 24
    //   322: ldc 225
    //   324: bipush 23
    //   326: goto +683 -> 1009
    //   329: aastore
    //   330: dup
    //   331: bipush 25
    //   333: ldc 219
    //   335: bipush 24
    //   337: goto +672 -> 1009
    //   340: aastore
    //   341: dup
    //   342: bipush 26
    //   344: ldc 221
    //   346: bipush 25
    //   348: goto +661 -> 1009
    //   351: aastore
    //   352: dup
    //   353: bipush 27
    //   355: ldc 227
    //   357: bipush 26
    //   359: goto +650 -> 1009
    //   362: aastore
    //   363: dup
    //   364: bipush 28
    //   366: ldc 230
    //   368: bipush 27
    //   370: goto +639 -> 1009
    //   373: aastore
    //   374: dup
    //   375: bipush 29
    //   377: ldc 233
    //   379: bipush 28
    //   381: goto +628 -> 1009
    //   384: aastore
    //   385: dup
    //   386: bipush 30
    //   388: ldc 215
    //   390: bipush 29
    //   392: goto +617 -> 1009
    //   395: aastore
    //   396: dup
    //   397: bipush 31
    //   399: ldc 217
    //   401: bipush 30
    //   403: goto +606 -> 1009
    //   406: aastore
    //   407: dup
    //   408: bipush 32
    //   410: ldc 213
    //   412: bipush 31
    //   414: goto +595 -> 1009
    //   417: aastore
    //   418: dup
    //   419: bipush 33
    //   421: ldc 222
    //   423: bipush 32
    //   425: goto +584 -> 1009
    //   428: aastore
    //   429: dup
    //   430: bipush 34
    //   432: ldc 223
    //   434: bipush 33
    //   436: goto +573 -> 1009
    //   439: aastore
    //   440: dup
    //   441: bipush 35
    //   443: ldc 232
    //   445: bipush 34
    //   447: goto +562 -> 1009
    //   450: aastore
    //   451: dup
    //   452: bipush 36
    //   454: ldc 226
    //   456: bipush 35
    //   458: goto +551 -> 1009
    //   461: aastore
    //   462: dup
    //   463: bipush 37
    //   465: ldc 214
    //   467: bipush 36
    //   469: goto +540 -> 1009
    //   472: aastore
    //   473: dup
    //   474: bipush 38
    //   476: ldc 235
    //   478: bipush 37
    //   480: goto +529 -> 1009
    //   483: aastore
    //   484: dup
    //   485: bipush 39
    //   487: ldc_w 271
    //   490: bipush 38
    //   492: goto +517 -> 1009
    //   495: aastore
    //   496: dup
    //   497: bipush 40
    //   499: ldc_w 267
    //   502: bipush 39
    //   504: goto +505 -> 1009
    //   507: aastore
    //   508: dup
    //   509: bipush 41
    //   511: ldc_w 261
    //   514: bipush 40
    //   516: goto +493 -> 1009
    //   519: aastore
    //   520: dup
    //   521: bipush 42
    //   523: ldc_w 272
    //   526: bipush 41
    //   528: goto +481 -> 1009
    //   531: aastore
    //   532: dup
    //   533: bipush 43
    //   535: ldc_w 264
    //   538: bipush 42
    //   540: goto +469 -> 1009
    //   543: aastore
    //   544: dup
    //   545: bipush 44
    //   547: ldc_w 309
    //   550: bipush 43
    //   552: goto +457 -> 1009
    //   555: aastore
    //   556: dup
    //   557: bipush 45
    //   559: ldc_w 300
    //   562: bipush 44
    //   564: goto +445 -> 1009
    //   567: aastore
    //   568: dup
    //   569: bipush 46
    //   571: ldc_w 282
    //   574: bipush 45
    //   576: goto +433 -> 1009
    //   579: aastore
    //   580: dup
    //   581: bipush 47
    //   583: ldc_w 275
    //   586: bipush 46
    //   588: goto +421 -> 1009
    //   591: aastore
    //   592: dup
    //   593: bipush 48
    //   595: ldc_w 307
    //   598: bipush 47
    //   600: goto +409 -> 1009
    //   603: aastore
    //   604: dup
    //   605: bipush 49
    //   607: ldc_w 305
    //   610: bipush 48
    //   612: goto +397 -> 1009
    //   615: aastore
    //   616: dup
    //   617: bipush 50
    //   619: ldc 241
    //   621: bipush 49
    //   623: goto +386 -> 1009
    //   626: aastore
    //   627: dup
    //   628: bipush 51
    //   630: ldc 236
    //   632: bipush 50
    //   634: goto +375 -> 1009
    //   637: aastore
    //   638: dup
    //   639: bipush 52
    //   641: ldc 55
    //   643: bipush 51
    //   645: goto +364 -> 1009
    //   648: aastore
    //   649: dup
    //   650: bipush 53
    //   652: ldc 102
    //   654: bipush 52
    //   656: goto +353 -> 1009
    //   659: aastore
    //   660: dup
    //   661: bipush 54
    //   663: ldc 59
    //   665: bipush 53
    //   667: goto +342 -> 1009
    //   670: aastore
    //   671: dup
    //   672: bipush 55
    //   674: ldc 79
    //   676: bipush 54
    //   678: goto +331 -> 1009
    //   681: aastore
    //   682: dup
    //   683: bipush 56
    //   685: ldc 50
    //   687: bipush 55
    //   689: goto +320 -> 1009
    //   692: aastore
    //   693: dup
    //   694: bipush 57
    //   696: ldc 82
    //   698: bipush 56
    //   700: goto +309 -> 1009
    //   703: aastore
    //   704: dup
    //   705: bipush 58
    //   707: ldc 69
    //   709: bipush 57
    //   711: goto +298 -> 1009
    //   714: aastore
    //   715: dup
    //   716: bipush 59
    //   718: ldc 66
    //   720: bipush 58
    //   722: goto +287 -> 1009
    //   725: aastore
    //   726: dup
    //   727: bipush 60
    //   729: ldc 88
    //   731: bipush 59
    //   733: goto +276 -> 1009
    //   736: aastore
    //   737: dup
    //   738: bipush 61
    //   740: ldc 85
    //   742: bipush 60
    //   744: goto +265 -> 1009
    //   747: aastore
    //   748: dup
    //   749: bipush 62
    //   751: ldc 63
    //   753: bipush 61
    //   755: goto +254 -> 1009
    //   758: aastore
    //   759: dup
    //   760: bipush 63
    //   762: ldc 255
    //   764: bipush 62
    //   766: goto +243 -> 1009
    //   769: aastore
    //   770: dup
    //   771: bipush 64
    //   773: ldc_w 259
    //   776: bipush 63
    //   778: goto +231 -> 1009
    //   781: aastore
    //   782: dup
    //   783: bipush 65
    //   785: ldc 243
    //   787: bipush 64
    //   789: goto +220 -> 1009
    //   792: aastore
    //   793: dup
    //   794: bipush 66
    //   796: ldc 252
    //   798: bipush 65
    //   800: goto +209 -> 1009
    //   803: aastore
    //   804: dup
    //   805: bipush 67
    //   807: ldc 254
    //   809: bipush 66
    //   811: goto +198 -> 1009
    //   814: aastore
    //   815: dup
    //   816: bipush 68
    //   818: ldc_w 256
    //   821: bipush 67
    //   823: goto +186 -> 1009
    //   826: aastore
    //   827: dup
    //   828: bipush 69
    //   830: ldc_w 338
    //   833: bipush 68
    //   835: goto +174 -> 1009
    //   838: aastore
    //   839: dup
    //   840: bipush 70
    //   842: ldc_w 331
    //   845: bipush 69
    //   847: goto +162 -> 1009
    //   850: aastore
    //   851: dup
    //   852: bipush 71
    //   854: ldc_w 329
    //   857: bipush 70
    //   859: goto +150 -> 1009
    //   862: aastore
    //   863: dup
    //   864: bipush 72
    //   866: ldc_w 343
    //   869: bipush 71
    //   871: goto +138 -> 1009
    //   874: aastore
    //   875: dup
    //   876: bipush 73
    //   878: ldc_w 333
    //   881: bipush 72
    //   883: goto +126 -> 1009
    //   886: aastore
    //   887: dup
    //   888: bipush 74
    //   890: ldc_w 334
    //   893: bipush 73
    //   895: goto +114 -> 1009
    //   898: aastore
    //   899: dup
    //   900: bipush 75
    //   902: ldc 168
    //   904: bipush 74
    //   906: goto +103 -> 1009
    //   909: aastore
    //   910: dup
    //   911: bipush 76
    //   913: ldc 166
    //   915: bipush 75
    //   917: goto +92 -> 1009
    //   920: aastore
    //   921: dup
    //   922: bipush 77
    //   924: ldc 167
    //   926: bipush 76
    //   928: goto +81 -> 1009
    //   931: aastore
    //   932: dup
    //   933: bipush 78
    //   935: ldc_w 314
    //   938: bipush 77
    //   940: goto +69 -> 1009
    //   943: aastore
    //   944: dup
    //   945: bipush 79
    //   947: ldc_w 316
    //   950: bipush 78
    //   952: goto +57 -> 1009
    //   955: aastore
    //   956: dup
    //   957: bipush 80
    //   959: ldc_w 311
    //   962: bipush 79
    //   964: goto +45 -> 1009
    //   967: aastore
    //   968: dup
    //   969: bipush 81
    //   971: ldc_w 317
    //   974: bipush 80
    //   976: goto +33 -> 1009
    //   979: aastore
    //   980: dup
    //   981: bipush 82
    //   983: ldc_w 345
    //   986: bipush 81
    //   988: goto +21 -> 1009
    //   991: aastore
    //   992: dup
    //   993: bipush 83
    //   995: ldc 8
    //   997: bipush 82
    //   999: goto +10 -> 1009
    //   1002: aastore
    //   1003: putstatic 1053	com/compiere/client/Support:Y	[Ljava/lang/String;
    //   1006: goto +482 -> 1488
    //   1009: swap
    //   1010: invokevirtual 1048	java/lang/String:toCharArray	()[C
    //   1013: dup
    //   1014: arraylength
    //   1015: swap
    //   1016: iconst_0
    //   1017: istore_0
    //   1018: swap
    //   1019: dup_x1
    //   1020: iconst_1
    //   1021: if_icmpgt +77 -> 1098
    //   1024: dup
    //   1025: iload_0
    //   1026: dup2
    //   1027: caload
    //   1028: iload_0
    //   1029: iconst_5
    //   1030: irem
    //   1031: tableswitch	default:+49 -> 1080, 0:+29->1060, 1:+34->1065, 2:+39->1070, 3:+44->1075
    //   1061: lmul
    //   1062: goto +20 -> 1082
    //   1065: bipush 109
    //   1067: goto +15 -> 1082
    //   1070: bipush 80
    //   1072: goto +10 -> 1082
    //   1075: bipush 41
    //   1077: goto +5 -> 1082
    //   1080: bipush 95
    //   1082: ixor
    //   1083: i2c
    //   1084: castore
    //   1085: iinc 0 1
    //   1088: swap
    //   1089: dup_x1
    //   1090: ifne +8 -> 1098
    //   1093: dup2
    //   1094: swap
    //   1095: goto -69 -> 1026
    //   1098: swap
    //   1099: dup_x1
    //   1100: iload_0
    //   1101: if_icmpgt -77 -> 1024
    //   1104: new 279	java/lang/String
    //   1107: dup_x1
    //   1108: swap
    //   1109: invokespecial 337	java/lang/String:<init>	([C)V
    //   1112: invokevirtual 1051	java/lang/String:intern	()Ljava/lang/String;
    //   1115: swap
    //   1116: pop
    //   1117: swap
    //   1118: tableswitch	default:+-1042 -> 76, 0:+-1033->85, 1:+-1024->94, 2:+-1015->103, 3:+-1006->112, 4:+-997->121, 5:+-987->131, 6:+-976->142, 7:+-965->153, 8:+-954->164, 9:+-943->175, 10:+-932->186, 11:+-921->197, 12:+-910->208, 13:+-899->219, 14:+-888->230, 15:+-877->241, 16:+-866->252, 17:+-855->263, 18:+-844->274, 19:+-833->285, 20:+-822->296, 21:+-811->307, 22:+-800->318, 23:+-789->329, 24:+-778->340, 25:+-767->351, 26:+-756->362, 27:+-745->373, 28:+-734->384, 29:+-723->395, 30:+-712->406, 31:+-701->417, 32:+-690->428, 33:+-679->439, 34:+-668->450, 35:+-657->461, 36:+-646->472, 37:+-635->483, 38:+-623->495, 39:+-611->507, 40:+-599->519, 41:+-587->531, 42:+-575->543, 43:+-563->555, 44:+-551->567, 45:+-539->579, 46:+-527->591, 47:+-515->603, 48:+-503->615, 49:+-492->626, 50:+-481->637, 51:+-470->648, 52:+-459->659, 53:+-448->670, 54:+-437->681, 55:+-426->692, 56:+-415->703, 57:+-404->714, 58:+-393->725, 59:+-382->736, 60:+-371->747, 61:+-360->758, 62:+-349->769, 63:+-337->781, 64:+-326->792, 65:+-315->803, 66:+-304->814, 67:+-292->826, 68:+-280->838, 69:+-268->850, 70:+-256->862, 71:+-244->874, 72:+-232->886, 73:+-220->898, 74:+-209->909, 75:+-198->920, 76:+-187->931, 77:+-175->943, 78:+-163->955, 79:+-151->967, 80:+-139->979, 81:+-127->991, 82:+-116->1002, 83:+-1104->14, 84:+-1094->24, 85:+-1084->34, 86:+-1073->45, 87:+-1062->56, 88:+-1052->66
    //   1489: aconst_null
    //   1490: dup2
    //   1491: invokestatic 349	org/compiere/util/CLogger:getCLogger	(Ljava/lang/Class;)Lorg/compiere/util/CLogger;
    //   1494: putstatic 98	com/compiere/client/Support:g	Lorg/compiere/util/CLogger;
    //   1497: iconst_m1
    //   1498: putstatic 198	com/compiere/client/Support:k	I
    //   1501: return
  }
}