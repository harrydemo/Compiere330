package com.compiere.client;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.compiere.apps.ConfirmPanel;
import org.compiere.db.CConnection;
import org.compiere.db.CompiereDatabase;
import org.compiere.install.Setup;
import org.compiere.model.MEntityType;
import org.compiere.model.MSession;
import org.compiere.model.MSystem;
import org.compiere.swing.CButton;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CPassword;
import org.compiere.swing.CScrollPane;
import org.compiere.swing.CTable;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.Ctx;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.Util;

public class ComponentPanel extends CPanel
  implements ActionListener
{
  private static final long serialVersionUID = -6626945837034475105L;
  private static CLogger a;
  private Setup b = null;
  private Properties c = null;
  private String d = null;
  private MEntityType[] e = null;
  private ArrayList f = new ArrayList();
  private boolean g = false;
  private CPanel h = new CPanel(new BorderLayout());
  private CTextField i = new CTextField(10);
  private CLabel j = new CLabel(A[63], this.i);
  private String k = null;
  private CTextField l = new CTextField(10);
  private CLabel m = new CLabel(A[61], this.l);
  private String n = null;
  private CPassword o = new CPassword(10);
  private CLabel p = new CLabel(A[64], this.o);
  private String q = null;
  private CButton r = new CButton(A[62]);
  private CLabel s = new CLabel("");
  private CLabel t = new CLabel();
  private CLabel u = new CLabel();
  CButton v = new CButton();
  DefaultTableModel w = new DefaultTableModel(new String[] { A[65], A[60] }, 0);
  CTable x = new CTable(false, this.w);
  CScrollPane y = new CScrollPane(this.x);
  ConfirmPanel z = new ConfirmPanel(true, false, false, false, false, false, false);
  private static final String[] A;

  public void init(Setup paramSetup, Properties paramProperties)
  {
    int i1 = SysEnv.m;
    this.b = paramSetup;
    this.c = paramProperties;
    this.d = this.c.getProperty(A[70]);
    setLayout(new BorderLayout(10, 10));
    Font localFont = this.t.getFont();
    Insets localInsets = new Insets(2, 3, 2, 0);
    CPanel localCPanel1 = new CPanel(new GridBagLayout());
    localCPanel1.setBorder(BorderFactory.createTitledBorder(A[67]));
    this.i.setText("-");
    this.l.setText("?");
    this.o.setText("?");
    this.s.setHorizontalAlignment(0);
    localCPanel1.add(this.j, new GridBagConstraints(0, 0, 1, 1, 1.0D, 1.0D, 13, 0, localInsets, 0, 0));
    localCPanel1.add(this.i, new GridBagConstraints(1, 0, 1, 1, 1.0D, 1.0D, 17, 2, localInsets, 0, 0));
    localCPanel1.add(this.m, new GridBagConstraints(0, 1, 1, 1, 1.0D, 1.0D, 13, 0, localInsets, 0, 0));
    localCPanel1.add(this.l, new GridBagConstraints(1, 1, 1, 1, 1.0D, 1.0D, 17, 2, localInsets, 0, 0));
    localCPanel1.add(this.p, new GridBagConstraints(2, 1, 1, 1, 1.0D, 1.0D, 13, 0, localInsets, 0, 0));
    localCPanel1.add(this.o, new GridBagConstraints(3, 1, 1, 1, 1.0D, 1.0D, 17, 2, localInsets, 0, 0));
    localCPanel1.add(this.r, new GridBagConstraints(0, 2, 2, 1, 1.0D, 1.0D, 17, 2, localInsets, 0, 0));
    localCPanel1.add(this.s, new GridBagConstraints(2, 2, 2, 1, 1.0D, 1.0D, 17, 2, localInsets, 0, 0));
    this.h.add(localCPanel1, A[73]);
    add(this.h, A[66]);
    CPanel localCPanel2 = new CPanel(new BorderLayout());
    this.t.setText(Setup.res.getString(A[74]));
    this.t.setFont(new Font(localFont.getName(), 1, localFont.getSize()));
    this.t.setHorizontalAlignment(0);
    this.t.setToolTipText(Setup.res.getString(A[69]));
    localCPanel2.add(this.t, A[66]);
    localCPanel2.add(this.u, A[73]);
    this.u.setHorizontalAlignment(0);
    this.v.setText(Setup.res.getString(A[71]));
    localCPanel2.add(this.v, A[68]);
    add(localCPanel2, A[72]);
    TableColumn localTableColumn = this.x.getColumnModel().getColumn(0);
    localTableColumn.setPreferredWidth(400);
    ComponentComboBox localComponentComboBox = new ComponentComboBox(new String[0]);
    localTableColumn = this.x.getColumnModel().getColumn(1);
    localTableColumn.setCellRenderer(localComponentComboBox);
    localTableColumn.setCellEditor(localComponentComboBox);
    localTableColumn.setPreferredWidth(135);
    add(this.y, A[73]);
    add(this.z, A[68]);
    this.b.statusBar.setText(this.r.getText());
    this.v.addActionListener(this);
    this.v.setEnabled(false);
    this.z.addActionListener(this);
    this.z.getOKButton().setEnabled(false);
    this.r.addActionListener(this);
    a();
    d();
    if (i1 != 0)
      InstallInfo.f = !InstallInfo.f;
  }

  public void actionPerformed(ActionEvent paramActionEvent)
  {
    int i1 = SysEnv.m;
    Object localObject = paramActionEvent.getSource();
    String str = paramActionEvent.getActionCommand();
    if (i1 == 0)
    {
      if (str.equals(A[21]))
      {
        System.exit(-1);
        if (i1 == 0)
          break label103;
      }
      if (i1 != 0);
    }
    else if (str.equals(A[22]))
    {
      e();
      if (i1 == 0)
        break label103;
    }
    if (i1 == 0)
      if (localObject == this.r)
        b();
    if ((i1 != 0) && (localObject == this.v))
      c();
    label103: d();
  }

  private void a()
  {
    if (SysEnv.m == 0)
      if (!"Y".equals(this.c.getProperty(A[44])))
        return;
    String str1 = this.c.getProperty(A[6]);
    String str2 = this.c.getProperty(A[39]);
    String str3 = this.c.getProperty(A[42]);
    String str4 = this.c.getProperty(A[41]);
    String str5 = this.c.getProperty(A[38]);
    String str6 = this.c.getProperty(A[40]);
    try
    {
      DB.setDBTarget(CConnection.get(str1, str2, str3, str4, str5, str6));
      DB.getDatabase();
    }
    catch (Exception localException1)
    {
      a.config(A[37]);
      return;
    }
    g();
    try
    {
      Ctx localCtx = Env.getCtx();
      MSystem localMSystem = MSystem.get(localCtx);
      this.i.setText(localMSystem.getName());
      this.l.setText(localMSystem.getUserName());
      this.o.setText(localMSystem.getPassword());
      this.e = MEntityType.getEntityTypes(localCtx, false);
    }
    catch (Exception localException2)
    {
      a.config(A[43]);
      return;
    }
  }

  private void b()
  {
    int i2 = SysEnv.m;
    this.s.setText("");
    do
    {
      if (this.w.getRowCount() <= 0)
        break;
      if (i2 != 0)
        break label43;
      this.w.removeRow(0);
    }
    while (i2 == 0);
    label43: ArrayList localArrayList = a.a(this.d);
    if (localArrayList == null)
      return;
    String str1 = this.i.getText();
    String str2 = this.l.getText();
    String str3 = new String(this.o.getPassword());
    if (i2 == 0)
    {
      if ((!this.g) && ((i2 != 0) || (str1.equals(this.k))) && ((i2 != 0) || (str2.equals(this.n))))
      {
        if (i2 != 0)
          break label177;
        if (str3.equals(this.q));
      }
      else
      {
        this.f.clear();
        this.g = false;
        this.k = str1;
        this.n = str2;
      }
    }
    else
      this.q = str3;
    label177: int i1 = 0;
    do
    {
      if (i1 >= localArrayList.size())
        break;
      a locala = (a)localArrayList.get(i1);
      a(locala);
      i1++;
    }
    while (i2 == 0);
    String str4 = Setup.res.getString(A[74]);
    this.b.statusBar.setText(str4);
    this.v.setEnabled(true);
    this.z.getOKButton().setEnabled(true);
  }

  private void c()
  {
    JFileChooser localJFileChooser = new JFileChooser();
    localJFileChooser.setDialogType(0);
    localJFileChooser.setFileSelectionMode(2);
    localJFileChooser.setMultiSelectionEnabled(false);
    localJFileChooser.setDialogTitle(this.t.getToolTipText());
    localJFileChooser.setFileFilter(new ComponentFileFilter());
    if ((SysEnv.m != 0) || (localJFileChooser.showOpenDialog(this) == 0))
    {
      File localFile = localJFileChooser.getSelectedFile();
      a locala = new a(localFile);
      a(locala);
    }
  }

  private void a(a parama)
  {
    int i4 = SysEnv.m;
    String str = parama.b();
    MEntityType localMEntityType = null;
    if (this.e != null)
      for (int i1 = 0; i1 < this.e.length; i1++)
      {
        if ((i4 == 0) && (!this.e[i1].getEntityType().equals(str)))
          continue;
        localMEntityType = this.e[i1];
        break;
      }
    b localb = null;
    for (int i2 = 0; i2 < this.f.size(); i2++)
    {
      if ((i4 == 0) && (!((b)this.f.get(i2)).a().equals(str)))
        continue;
      localb = (b)this.f.get(i2);
      break;
    }
    if (i4 == 0)
    {
      if (localb != null)
        break label176;
      if (i4 == 0)
        localb = b(parama);
    }
    else
    {
      if (localb == null)
        break label176;
    }
    this.f.add(localb);
    label176: String[] arrayOfString = parama.a(localMEntityType, localb);
    ComponentComboBox localComponentComboBox = new ComponentComboBox(arrayOfString);
    localComponentComboBox.addActionListener(this);
    Object[] arrayOfObject = { parama, localComponentComboBox };
    this.w.addRow(arrayOfObject);
    int i3 = this.w.getRowCount() - 1;
    this.w.fireTableRowsInserted(0, i3);
  }

  private b b(a parama)
  {
    int i1 = SysEnv.m;
    String str1 = parama.b();
    if (str1 == null)
      return new b(A[17], false, A[18]);
    boolean bool = false;
    String str2 = A[17];
    if (i1 == 0)
      if (str1.equals("D"))
      {
        bool = true;
        str2 = A[16];
        return new b(str1, bool, str2);
      }
    String str3 = this.i.getText();
    String str4 = this.l.getText();
    String str5 = new String(this.o.getPassword());
    if (i1 == 0)
      if (!Util.isEmpty(str4))
      {
        if (i1 != 0)
          break label162;
        if (!Util.isEmpty(str5))
        {
          if (i1 != 0)
            break label192;
          if (!str4.equals("?"))
            break label169;
        }
      }
    label162: str2 = A[15];
    if (i1 != 0)
    {
      label169: str2 = a(str3, str4, str5, parama);
      label192: if (i1 == 0);
      bool = str2.indexOf(A[4]) == -1;
    }
    b localb = new b(str1, bool, str2);
    return localb;
  }

  private String a(String paramString1, String paramString2, String paramString3, a parama)
  {
    String str1 = parama.b();
    String str2 = parama.k();
    String str3 = this.c.getProperty(A[5]);
    String str4 = this.c.getProperty(A[6]);
    c localc = new c(a);
    boolean bool = localc.a(paramString1, paramString2, paramString3, str1, str2, str3, str4);
    String str5 = null;
    if (!bool)
    {
      str5 = A[7];
      this.s.setText(A[3]);
      this.g = true;
    }
    else
    {
      str5 = localc.a();
    }
    if (SysEnv.m == 0)
      if (str5.startsWith(A[4]))
        JOptionPane.showMessageDialog(this.b, str5, parama.m(), 0);
    return str5;
  }

  // ERROR //
  private void d()
  {
    // Byte code:
    //   0: getstatic 984	com/compiere/client/SysEnv:m	I
    //   3: istore 8
    //   5: aload_0
    //   6: getfield 50	com/compiere/client/ComponentPanel:w	Ljavax/swing/table/DefaultTableModel;
    //   9: invokevirtual 146	javax/swing/table/DefaultTableModel:getRowCount	()I
    //   12: istore_1
    //   13: iconst_0
    //   14: istore_2
    //   15: iconst_0
    //   16: istore_3
    //   17: iconst_0
    //   18: istore 4
    //   20: iload 4
    //   22: iload_1
    //   23: if_icmpge +154 -> 177
    //   26: aload_0
    //   27: getfield 50	com/compiere/client/ComponentPanel:w	Ljavax/swing/table/DefaultTableModel;
    //   30: iload 4
    //   32: iconst_1
    //   33: invokevirtual 202	javax/swing/table/DefaultTableModel:getValueAt	(II)Ljava/lang/Object;
    //   36: astore 5
    //   38: getstatic 203	com/compiere/client/a:a	Ljava/lang/String;
    //   41: astore 6
    //   43: aload 5
    //   45: instanceof 204
    //   48: iload 8
    //   50: ifne +46 -> 96
    //   53: ifeq +35 -> 88
    //   56: aload 5
    //   58: checkcast 204	javax/swing/JComboBox
    //   61: astore 7
    //   63: aload 7
    //   65: invokevirtual 205	javax/swing/JComboBox:getSelectedItem	()Ljava/lang/Object;
    //   68: checkcast 46	java/lang/String
    //   71: astore 6
    //   73: aload 6
    //   75: iload 8
    //   77: ifne +13 -> 90
    //   80: ifnonnull +8 -> 88
    //   83: getstatic 203	com/compiere/client/a:a	Ljava/lang/String;
    //   86: astore 6
    //   88: aload 6
    //   90: getstatic 206	com/compiere/client/a:c	Ljava/lang/String;
    //   93: invokevirtual 117	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   96: iload 8
    //   98: ifne +14 -> 112
    //   101: ifne +35 -> 136
    //   104: aload 6
    //   106: getstatic 207	com/compiere/client/a:d	Ljava/lang/String;
    //   109: invokevirtual 117	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   112: iload 8
    //   114: ifne +14 -> 128
    //   117: ifne +19 -> 136
    //   120: aload 6
    //   122: getstatic 208	com/compiere/client/a:f	Ljava/lang/String;
    //   125: invokevirtual 117	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   128: iload 8
    //   130: ifne +17 -> 147
    //   133: ifeq +6 -> 139
    //   136: iinc 2 1
    //   139: aload 6
    //   141: getstatic 207	com/compiere/client/a:d	Ljava/lang/String;
    //   144: invokevirtual 117	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   147: iload 8
    //   149: ifne +14 -> 163
    //   152: ifne +14 -> 166
    //   155: aload 6
    //   157: getstatic 208	com/compiere/client/a:f	Ljava/lang/String;
    //   160: invokevirtual 117	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   163: ifeq +6 -> 169
    //   166: iinc 3 1
    //   169: iinc 4 1
    //   172: iload 8
    //   174: ifeq -154 -> 20
    //   177: new 209	java/lang/StringBuffer
    //   180: dup
    //   181: getstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   184: iconst_0
    //   185: aaload
    //   186: invokespecial 211	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
    //   189: iload_2
    //   190: invokevirtual 212	java/lang/StringBuffer:append	(I)Ljava/lang/StringBuffer;
    //   193: getstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   196: iconst_2
    //   197: aaload
    //   198: invokevirtual 214	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   201: iload_3
    //   202: invokevirtual 212	java/lang/StringBuffer:append	(I)Ljava/lang/StringBuffer;
    //   205: getstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   208: iconst_1
    //   209: aaload
    //   210: invokevirtual 214	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   213: iload_1
    //   214: invokevirtual 212	java/lang/StringBuffer:append	(I)Ljava/lang/StringBuffer;
    //   217: astore 4
    //   219: aload_0
    //   220: getfield 42	com/compiere/client/ComponentPanel:u	Lorg/compiere/swing/CLabel;
    //   223: aload 4
    //   225: invokevirtual 216	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   228: invokevirtual 86	org/compiere/swing/CLabel:setText	(Ljava/lang/String;)V
    //   231: return
  }

  private void e()
  {
    int i3 = SysEnv.m;
    File localFile1 = new File(this.d);
    File localFile2 = new File(localFile1, A[13] + File.separator + A[9]);
    f();
    InstallInfo localInstallInfo = new InstallInfo();
    int i1 = this.w.getRowCount();
    int i2 = 0;
    Object localObject1;
    do
    {
      if (i2 >= i1)
        break;
      localObject1 = this.w.getValueAt(i2, 1);
      String str1 = a.a;
      if (i3 == 0)
        if ((localObject1 instanceof JComboBox))
        {
          localObject2 = (JComboBox)localObject1;
          str1 = (String)((JComboBox)localObject2).getSelectedItem();
        }
      Object localObject2 = this.w.getValueAt(i2, 0);
      if (i3 != 0)
        continue;
      if (((localObject2 instanceof a)) && ((i3 != 0) || ((str1.equals(a.d)) || ((i3 != 0) || (str1.equals(a.f))))))
      {
        a locala = (a)localObject2;
        String str2 = A[12];
        if ((i3 != 0) || (locala.b(str1)))
          str2 = locala.a(localFile1, localFile2);
        if (str2 != null)
          return;
        String str3 = null;
        String str4 = null;
        try
        {
          str4 = locala.i();
          str3 = a(str4);
        }
        catch (Exception localException2)
        {
          a.severe(localException2.getMessage());
        }
        if (str3 != null)
        {
          localInstallInfo.a(str4);
          localInstallInfo.b(str4);
        }
      }
      i2++;
    }
    while (i3 == 0);
    Ctx localCtx = Env.getCtx();
    try
    {
      localObject1 = MSystem.get(localCtx);
      ((MSystem)localObject1).setName(this.i.getText());
      ((MSystem)localObject1).setUserName(this.l.getText());
      ((MSystem)localObject1).setPassword(new String(this.o.getPassword()));
    }
    catch (Exception localException1)
    {
    }
    localInstallInfo.c(this.i.getText());
    localInstallInfo.d(this.l.getText());
    localInstallInfo.e(new String(this.o.getPassword()));
    MSession localMSession = MSession.get(localCtx);
    if (i3 == 0)
    {
      if (localMSession != null)
        localMSession.logout();
      DB.closeTarget();
      this.b.setInfo(localInstallInfo);
    }
    this.c.setProperty(A[11], String.valueOf(localInstallInfo.a(false)));
    firePropertyChange(A[10], A[8], A[14]);
  }

  private void f()
  {
    int i2 = SysEnv.m;
    File localFile1 = new File(this.d);
    File localFile2 = new File(localFile1, A[13] + File.separator + A[9]);
    if (i2 == 0)
      if (localFile2.exists())
      {
        localObject = localFile2.listFiles();
        int i1 = 0;
        do
        {
          if (i1 >= localObject.length)
            break;
          str2 = localObject[i1];
          str2.delete();
          i1++;
          if (i2 != 0)
            break label122;
        }
        while (i2 == 0);
        if (i2 == 0)
          break label122;
      }
    localFile2.mkdir();
    label122: Object localObject = this.d + File.separator + A[13];
    a((String)localObject, A[57]);
    String str1 = this.d + File.separator + A[33] + File.separator + A[27];
    a(str1, null);
    String str2 = this.d + File.separator + A[33] + File.separator + A[36];
    a(str2, null);
  }

  // ERROR //
  private void a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: getstatic 984	com/compiere/client/SysEnv:m	I
    //   3: istore 9
    //   5: new 217	java/io/File
    //   8: dup
    //   9: aload_1
    //   10: invokespecial 218	java/io/File:<init>	(Ljava/lang/String;)V
    //   13: astore_3
    //   14: aload_3
    //   15: iload 9
    //   17: ifne +53 -> 70
    //   20: invokevirtual 257	java/io/File:exists	()Z
    //   23: ifne +46 -> 69
    //   26: aload_3
    //   27: iload 9
    //   29: ifne +41 -> 70
    //   32: invokevirtual 260	java/io/File:mkdir	()Z
    //   35: ifne +34 -> 69
    //   38: new 266	java/lang/RuntimeException
    //   41: dup
    //   42: new 219	java/lang/StringBuilder
    //   45: dup
    //   46: invokespecial 220	java/lang/StringBuilder:<init>	()V
    //   49: getstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   52: bipush 19
    //   54: aaload
    //   55: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: aload_1
    //   59: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: invokevirtual 225	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: invokespecial 268	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   68: athrow
    //   69: aload_3
    //   70: invokevirtual 258	java/io/File:listFiles	()[Ljava/io/File;
    //   73: astore 4
    //   75: aload 4
    //   77: astore 5
    //   79: aload 5
    //   81: arraylength
    //   82: istore 6
    //   84: iconst_0
    //   85: istore 7
    //   87: iload 7
    //   89: iload 6
    //   91: if_icmpge +88 -> 179
    //   94: aload 5
    //   96: iload 7
    //   98: aaload
    //   99: astore 8
    //   101: aload_2
    //   102: iload 9
    //   104: ifne +11 -> 115
    //   107: ifnull +23 -> 130
    //   110: aload 8
    //   112: invokevirtual 269	java/io/File:getName	()Ljava/lang/String;
    //   115: aload_2
    //   116: invokevirtual 270	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   119: iload 9
    //   121: ifne +14 -> 135
    //   124: ifne +6 -> 130
    //   127: goto +44 -> 171
    //   130: aload 8
    //   132: invokevirtual 259	java/io/File:delete	()Z
    //   135: ifne +36 -> 171
    //   138: getstatic 135	com/compiere/client/ComponentPanel:a	Lorg/compiere/util/CLogger;
    //   141: new 219	java/lang/StringBuilder
    //   144: dup
    //   145: invokespecial 220	java/lang/StringBuilder:<init>	()V
    //   148: getstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   151: bipush 20
    //   153: aaload
    //   154: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: aload 8
    //   159: invokevirtual 272	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   162: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: invokevirtual 225	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   168: invokevirtual 273	org/compiere/util/CLogger:warning	(Ljava/lang/String;)V
    //   171: iinc 7 1
    //   174: iload 9
    //   176: ifeq -89 -> 87
    //   179: return
  }

  // ERROR //
  private String a(String paramString)
  {
    // Byte code:
    //   0: getstatic 984	com/compiere/client/SysEnv:m	I
    //   3: istore 13
    //   5: aload_1
    //   6: iload 13
    //   8: ifne +7 -> 15
    //   11: ifnull +10 -> 21
    //   14: aload_1
    //   15: invokevirtual 274	java/lang/String:length	()I
    //   18: ifgt +34 -> 52
    //   21: new 266	java/lang/RuntimeException
    //   24: dup
    //   25: new 219	java/lang/StringBuilder
    //   28: dup
    //   29: invokespecial 220	java/lang/StringBuilder:<init>	()V
    //   32: getstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   35: bipush 34
    //   37: aaload
    //   38: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: aload_1
    //   42: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: invokevirtual 225	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   48: invokespecial 268	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   51: athrow
    //   52: new 217	java/io/File
    //   55: dup
    //   56: aload_1
    //   57: invokespecial 218	java/io/File:<init>	(Ljava/lang/String;)V
    //   60: astore_2
    //   61: aload_2
    //   62: invokevirtual 257	java/io/File:exists	()Z
    //   65: ifne +34 -> 99
    //   68: new 266	java/lang/RuntimeException
    //   71: dup
    //   72: new 219	java/lang/StringBuilder
    //   75: dup
    //   76: invokespecial 220	java/lang/StringBuilder:<init>	()V
    //   79: getstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   82: bipush 23
    //   84: aaload
    //   85: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: aload_1
    //   89: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: invokevirtual 225	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   95: invokespecial 268	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   98: athrow
    //   99: aconst_null
    //   100: astore_3
    //   101: aconst_null
    //   102: astore 4
    //   104: aconst_null
    //   105: astore 5
    //   107: aconst_null
    //   108: astore 6
    //   110: aconst_null
    //   111: astore 7
    //   113: aconst_null
    //   114: astore 8
    //   116: new 277	org/compiere/util/ZipUtil
    //   119: dup
    //   120: aload_1
    //   121: invokespecial 278	org/compiere/util/ZipUtil:<init>	(Ljava/lang/String;)V
    //   124: astore_3
    //   125: aload_3
    //   126: invokevirtual 279	org/compiere/util/ZipUtil:entries	()Ljava/util/Enumeration;
    //   129: astore 9
    //   131: aload 9
    //   133: invokeinterface 280 1 0
    //   138: ifeq +417 -> 555
    //   141: aload 9
    //   143: invokeinterface 281 1 0
    //   148: checkcast 282	java/util/zip/ZipEntry
    //   151: astore 4
    //   153: aload 4
    //   155: invokevirtual 283	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   158: astore 5
    //   160: aload 5
    //   162: iload 13
    //   164: ifne +432 -> 596
    //   167: iload 13
    //   169: ifne +81 -> 250
    //   172: getstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   175: bipush 32
    //   177: aaload
    //   178: invokevirtual 117	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   181: ifne +22 -> 203
    //   184: aload 5
    //   186: getstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   189: bipush 28
    //   191: aaload
    //   192: invokevirtual 199	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   195: iload 13
    //   197: ifne +76 -> 273
    //   200: ifeq +57 -> 257
    //   203: new 219	java/lang/StringBuilder
    //   206: dup
    //   207: invokespecial 220	java/lang/StringBuilder:<init>	()V
    //   210: aload_0
    //   211: getfield 4	com/compiere/client/ComponentPanel:d	Ljava/lang/String;
    //   214: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: getstatic 223	java/io/File:separator	Ljava/lang/String;
    //   220: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: getstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   226: bipush 13
    //   228: aaload
    //   229: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   232: getstatic 223	java/io/File:separator	Ljava/lang/String;
    //   235: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: aload 5
    //   240: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: invokevirtual 225	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   246: astore 6
    //   248: aload 6
    //   250: astore 7
    //   252: iload 13
    //   254: ifeq +280 -> 534
    //   257: aload 5
    //   259: iload 13
    //   261: ifne +92 -> 353
    //   264: getstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   267: bipush 30
    //   269: aaload
    //   270: invokevirtual 270	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   273: ifne +22 -> 295
    //   276: aload 5
    //   278: getstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   281: bipush 25
    //   283: aaload
    //   284: invokevirtual 270	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   287: iload 13
    //   289: ifne +87 -> 376
    //   292: ifeq +68 -> 360
    //   295: new 219	java/lang/StringBuilder
    //   298: dup
    //   299: invokespecial 220	java/lang/StringBuilder:<init>	()V
    //   302: aload_0
    //   303: getfield 4	com/compiere/client/ComponentPanel:d	Ljava/lang/String;
    //   306: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   309: getstatic 223	java/io/File:separator	Ljava/lang/String;
    //   312: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   315: getstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   318: bipush 33
    //   320: aaload
    //   321: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   324: getstatic 223	java/io/File:separator	Ljava/lang/String;
    //   327: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   330: getstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   333: bipush 27
    //   335: aaload
    //   336: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   339: getstatic 223	java/io/File:separator	Ljava/lang/String;
    //   342: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   345: aload 5
    //   347: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   350: invokevirtual 225	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   353: astore 7
    //   355: iload 13
    //   357: ifeq +177 -> 534
    //   360: aload 5
    //   362: iload 13
    //   364: ifne +92 -> 456
    //   367: getstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   370: bipush 24
    //   372: aaload
    //   373: invokevirtual 270	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   376: ifne +22 -> 398
    //   379: aload 5
    //   381: getstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   384: bipush 31
    //   386: aaload
    //   387: invokevirtual 270	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   390: iload 13
    //   392: ifne +82 -> 474
    //   395: ifeq +68 -> 463
    //   398: new 219	java/lang/StringBuilder
    //   401: dup
    //   402: invokespecial 220	java/lang/StringBuilder:<init>	()V
    //   405: aload_0
    //   406: getfield 4	com/compiere/client/ComponentPanel:d	Ljava/lang/String;
    //   409: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   412: getstatic 223	java/io/File:separator	Ljava/lang/String;
    //   415: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   418: getstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   421: bipush 33
    //   423: aaload
    //   424: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   427: getstatic 223	java/io/File:separator	Ljava/lang/String;
    //   430: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   433: getstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   436: bipush 36
    //   438: aaload
    //   439: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   442: getstatic 223	java/io/File:separator	Ljava/lang/String;
    //   445: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   448: aload 5
    //   450: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   453: invokevirtual 225	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   456: astore 7
    //   458: iload 13
    //   460: ifeq +74 -> 534
    //   463: aload 5
    //   465: getstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   468: bipush 29
    //   470: aaload
    //   471: invokevirtual 117	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   474: iload 13
    //   476: ifne +17 -> 493
    //   479: ifne -348 -> 131
    //   482: aload 5
    //   484: getstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   487: bipush 35
    //   489: aaload
    //   490: invokevirtual 117	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   493: ifeq +6 -> 499
    //   496: goto -365 -> 131
    //   499: getstatic 135	com/compiere/client/ComponentPanel:a	Lorg/compiere/util/CLogger;
    //   502: new 219	java/lang/StringBuilder
    //   505: dup
    //   506: invokespecial 220	java/lang/StringBuilder:<init>	()V
    //   509: getstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   512: bipush 26
    //   514: aaload
    //   515: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   518: aload 5
    //   520: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   523: invokevirtual 225	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   526: invokevirtual 273	org/compiere/util/CLogger:warning	(Ljava/lang/String;)V
    //   529: iload 13
    //   531: ifeq -400 -> 131
    //   534: aload_3
    //   535: aload 4
    //   537: invokevirtual 293	org/compiere/util/ZipUtil:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   540: astore 8
    //   542: aload_0
    //   543: aload 8
    //   545: aload 7
    //   547: invokespecial 294	com/compiere/client/ComponentPanel:a	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   550: iload 13
    //   552: ifeq -421 -> 131
    //   555: aload_3
    //   556: invokevirtual 295	org/compiere/util/ZipUtil:close	()V
    //   559: goto +35 -> 594
    //   562: astore 10
    //   564: getstatic 135	com/compiere/client/ComponentPanel:a	Lorg/compiere/util/CLogger;
    //   567: aload 10
    //   569: invokevirtual 297	java/io/IOException:toString	()Ljava/lang/String;
    //   572: invokevirtual 273	org/compiere/util/CLogger:warning	(Ljava/lang/String;)V
    //   575: aconst_null
    //   576: astore 11
    //   578: aload_3
    //   579: invokevirtual 295	org/compiere/util/ZipUtil:close	()V
    //   582: aload 11
    //   584: areturn
    //   585: astore 12
    //   587: aload_3
    //   588: invokevirtual 295	org/compiere/util/ZipUtil:close	()V
    //   591: aload 12
    //   593: athrow
    //   594: aload 6
    //   596: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   131	555	562	java/io/IOException
    //   131	555	585	finally
    //   562	578	585	finally
    //   585	587	585	finally
  }

  private void a(InputStream paramInputStream, String paramString)
  {
    int i1 = SysEnv.m;
    File localFile = null;
    FileOutputStream localFileOutputStream = null;
    BufferedOutputStream localBufferedOutputStream = null;
    BufferedInputStream localBufferedInputStream = null;
    try
    {
      localFile = new File(paramString);
      if (i1 == 0)
      {
        if (localFile.exists())
          a.info(A[59] + paramString);
      }
      else
        localFile.delete();
      localFileOutputStream = new FileOutputStream(localFile);
      localBufferedOutputStream = new BufferedOutputStream(localFileOutputStream);
      localBufferedInputStream = new BufferedInputStream(paramInputStream);
      do
      {
        if (localBufferedInputStream.available() <= 0)
          break;
        localBufferedOutputStream.write(localBufferedInputStream.read());
        if (i1 != 0)
          break label145;
      }
      while (i1 == 0);
    }
    catch (IOException localIOException2)
    {
      label145: throw new RuntimeException(A[58] + paramString);
    }
    finally
    {
      try
      {
        if ((i1 != 0) || (localBufferedOutputStream != null))
          localBufferedOutputStream.close();
        if ((i1 != 0) || (localBufferedInputStream != null))
          localBufferedInputStream.close();
      }
      catch (IOException localIOException3)
      {
      }
    }
  }

  private void g()
  {
    int i1 = SysEnv.m;
    try
    {
      Trx localTrx = Trx.get(A[53]);
      DatabaseMetaData localDatabaseMetaData = localTrx.getConnection().getMetaData();
      String str1 = this.c.getProperty(A[38]);
      String str2 = A[47];
      String str3 = A[48];
      if (i1 == 0)
      {
        if (localDatabaseMetaData.storesUpperCaseIdentifiers())
        {
          str1 = str1.toUpperCase();
          str2 = str2.toUpperCase();
          str3 = str3.toUpperCase();
          if (i1 == 0);
        }
        else
        {
          str1 = str1.toLowerCase();
          str2 = str2.toLowerCase();
        }
      }
      else
        str3 = str3.toLowerCase();
      ResultSet localResultSet1 = localDatabaseMetaData.getTables(null, str1, str2, new String[] { A[46] });
      ResultSet localResultSet2;
      String str4;
      if (i1 == 0)
      {
        if (localResultSet1.next())
        {
          localResultSet2 = localDatabaseMetaData.getColumns(null, str1, str2, str3);
          if (i1 != 0)
            break label268;
          if (localResultSet2.next())
          {
            str4 = A[56];
            str4 = DB.getDatabase().convertStatement(str4);
            DB.executeUpdate(str4, (Trx)null);
            str4 = A[49];
            str4 = DB.getDatabase().convertStatement(str4);
            DB.executeUpdate(str4, (Trx)null);
          }
        }
        str2 = A[55];
        str3 = A[50];
        if (i1 != 0)
          break label297;
      }
      if (localDatabaseMetaData.storesUpperCaseIdentifiers())
      {
        label268: str2 = str2.toUpperCase();
        str3 = str3.toUpperCase();
        if (i1 == 0);
      }
      else
      {
        str2 = str2.toLowerCase();
        label297: str3 = str3.toLowerCase();
      }
      localResultSet1 = localDatabaseMetaData.getTables(null, str1, str2, new String[] { A[46] });
      if (i1 == 0)
      {
        if (localResultSet1.next())
        {
          localResultSet2 = localDatabaseMetaData.getColumns(null, str1, str2, str3);
          if (i1 != 0)
            break label430;
          if (!localResultSet2.next())
          {
            str4 = A[45];
            str4 = DB.getDatabase().convertStatement(str4);
            DB.executeUpdate(str4, (Trx)null);
          }
        }
        str2 = A[52];
        str3 = A[51];
        if (i1 != 0)
          break label459;
      }
      if (localDatabaseMetaData.storesUpperCaseIdentifiers())
      {
        label430: str2 = str2.toUpperCase();
        str3 = str3.toUpperCase();
        if (i1 == 0);
      }
      else
      {
        str2 = str2.toLowerCase();
        label459: str3 = str3.toLowerCase();
      }
      localResultSet1 = localDatabaseMetaData.getTables(null, str1, str2, new String[] { A[46] });
      if (i1 == 0)
        if (localResultSet1.next())
        {
          localResultSet2 = localDatabaseMetaData.getColumns(null, str1, str2, str3);
          if (i1 != 0)
            break label569;
          if (!localResultSet2.next())
          {
            str4 = A[54];
            str4 = DB.getDatabase().convertStatement(str4);
            DB.executeUpdate(str4, (Trx)null);
          }
        }
      label569: localTrx.close();
    }
    catch (SQLException localSQLException)
    {
      localSQLException.printStackTrace();
      a.log(Level.SEVERE, localSQLException.toString());
    }
  }

  static
  {
    // Byte code:
    //   0: bipush 75
    //   2: anewarray 46	java/lang/String
    //   5: dup
    //   6: iconst_0
    //   7: ldc 210
    //   9: bipush 255
    //   11: goto +844 -> 855
    //   14: aastore
    //   15: dup
    //   16: iconst_1
    //   17: ldc 215
    //   19: iconst_0
    //   20: goto +835 -> 855
    //   23: aastore
    //   24: dup
    //   25: iconst_2
    //   26: ldc 213
    //   28: iconst_1
    //   29: goto +826 -> 855
    //   32: aastore
    //   33: dup
    //   34: iconst_3
    //   35: ldc 197
    //   37: iconst_2
    //   38: goto +817 -> 855
    //   41: aastore
    //   42: dup
    //   43: iconst_4
    //   44: ldc 189
    //   46: iconst_3
    //   47: goto +808 -> 855
    //   50: aastore
    //   51: dup
    //   52: iconst_5
    //   53: ldc 192
    //   55: iconst_4
    //   56: goto +799 -> 855
    //   59: aastore
    //   60: dup
    //   61: bipush 6
    //   63: ldc 125
    //   65: iconst_5
    //   66: goto +789 -> 855
    //   69: aastore
    //   70: dup
    //   71: bipush 7
    //   73: ldc 196
    //   75: bipush 6
    //   77: goto +778 -> 855
    //   80: aastore
    //   81: dup
    //   82: bipush 8
    //   84: ldc 254
    //   86: bipush 7
    //   88: goto +767 -> 855
    //   91: aastore
    //   92: dup
    //   93: bipush 9
    //   95: ldc 224
    //   97: bipush 8
    //   99: goto +756 -> 855
    //   102: aastore
    //   103: dup
    //   104: bipush 10
    //   106: ldc 253
    //   108: bipush 9
    //   110: goto +745 -> 855
    //   113: aastore
    //   114: dup
    //   115: bipush 11
    //   117: ldc 249
    //   119: bipush 10
    //   121: goto +734 -> 855
    //   124: aastore
    //   125: dup
    //   126: bipush 12
    //   128: ldc 230
    //   130: bipush 11
    //   132: goto +723 -> 855
    //   135: aastore
    //   136: dup
    //   137: bipush 13
    //   139: ldc 221
    //   141: bipush 12
    //   143: goto +712 -> 855
    //   146: aastore
    //   147: dup
    //   148: bipush 14
    //   150: ldc 255
    //   152: bipush 13
    //   154: goto +701 -> 855
    //   157: aastore
    //   158: dup
    //   159: bipush 15
    //   161: ldc 187
    //   163: bipush 14
    //   165: goto +690 -> 855
    //   168: aastore
    //   169: dup
    //   170: bipush 16
    //   172: ldc 185
    //   174: bipush 15
    //   176: goto +679 -> 855
    //   179: aastore
    //   180: dup
    //   181: bipush 17
    //   183: ldc 181
    //   185: bipush 16
    //   187: goto +668 -> 855
    //   190: aastore
    //   191: dup
    //   192: bipush 18
    //   194: ldc 182
    //   196: bipush 17
    //   198: goto +657 -> 855
    //   201: aastore
    //   202: dup
    //   203: bipush 19
    //   205: ldc_w 267
    //   208: bipush 18
    //   210: goto +645 -> 855
    //   213: aastore
    //   214: dup
    //   215: bipush 20
    //   217: ldc_w 271
    //   220: bipush 19
    //   222: goto +633 -> 855
    //   225: aastore
    //   226: dup
    //   227: bipush 21
    //   229: ldc 116
    //   231: bipush 20
    //   233: goto +622 -> 855
    //   236: aastore
    //   237: dup
    //   238: bipush 22
    //   240: ldc 119
    //   242: bipush 21
    //   244: goto +611 -> 855
    //   247: aastore
    //   248: dup
    //   249: bipush 23
    //   251: ldc_w 276
    //   254: bipush 22
    //   256: goto +599 -> 855
    //   259: aastore
    //   260: dup
    //   261: bipush 24
    //   263: ldc_w 288
    //   266: bipush 23
    //   268: goto +587 -> 855
    //   271: aastore
    //   272: dup
    //   273: bipush 25
    //   275: ldc_w 287
    //   278: bipush 24
    //   280: goto +575 -> 855
    //   283: aastore
    //   284: dup
    //   285: bipush 26
    //   287: ldc_w 292
    //   290: bipush 25
    //   292: goto +563 -> 855
    //   295: aastore
    //   296: dup
    //   297: bipush 27
    //   299: ldc_w 264
    //   302: bipush 26
    //   304: goto +551 -> 855
    //   307: aastore
    //   308: dup
    //   309: bipush 28
    //   311: ldc_w 285
    //   314: bipush 27
    //   316: goto +539 -> 855
    //   319: aastore
    //   320: dup
    //   321: bipush 29
    //   323: ldc_w 290
    //   326: bipush 28
    //   328: goto +527 -> 855
    //   331: aastore
    //   332: dup
    //   333: bipush 30
    //   335: ldc_w 286
    //   338: bipush 29
    //   340: goto +515 -> 855
    //   343: aastore
    //   344: dup
    //   345: bipush 31
    //   347: ldc_w 289
    //   350: bipush 30
    //   352: goto +503 -> 855
    //   355: aastore
    //   356: dup
    //   357: bipush 32
    //   359: ldc_w 284
    //   362: bipush 31
    //   364: goto +491 -> 855
    //   367: aastore
    //   368: dup
    //   369: bipush 33
    //   371: ldc_w 263
    //   374: bipush 32
    //   376: goto +479 -> 855
    //   379: aastore
    //   380: dup
    //   381: bipush 34
    //   383: ldc_w 275
    //   386: bipush 33
    //   388: goto +467 -> 855
    //   391: aastore
    //   392: dup
    //   393: bipush 35
    //   395: ldc_w 291
    //   398: bipush 34
    //   400: goto +455 -> 855
    //   403: aastore
    //   404: dup
    //   405: bipush 36
    //   407: ldc_w 265
    //   410: bipush 35
    //   412: goto +443 -> 855
    //   415: aastore
    //   416: dup
    //   417: bipush 37
    //   419: ldc 136
    //   421: bipush 36
    //   423: goto +432 -> 855
    //   426: aastore
    //   427: dup
    //   428: bipush 38
    //   430: ldc 129
    //   432: bipush 37
    //   434: goto +421 -> 855
    //   437: aastore
    //   438: dup
    //   439: bipush 39
    //   441: ldc 126
    //   443: bipush 38
    //   445: goto +410 -> 855
    //   448: aastore
    //   449: dup
    //   450: bipush 40
    //   452: ldc 130
    //   454: bipush 39
    //   456: goto +399 -> 855
    //   459: aastore
    //   460: dup
    //   461: bipush 41
    //   463: ldc 128
    //   465: bipush 40
    //   467: goto +388 -> 855
    //   470: aastore
    //   471: dup
    //   472: bipush 42
    //   474: ldc 127
    //   476: bipush 41
    //   478: goto +377 -> 855
    //   481: aastore
    //   482: dup
    //   483: bipush 43
    //   485: ldc 145
    //   487: bipush 42
    //   489: goto +366 -> 855
    //   492: aastore
    //   493: dup
    //   494: bipush 44
    //   496: ldc 124
    //   498: bipush 43
    //   500: goto +355 -> 855
    //   503: aastore
    //   504: dup
    //   505: bipush 45
    //   507: ldc_w 332
    //   510: bipush 44
    //   512: goto +343 -> 855
    //   515: aastore
    //   516: dup
    //   517: bipush 46
    //   519: ldc_w 321
    //   522: bipush 45
    //   524: goto +331 -> 855
    //   527: aastore
    //   528: dup
    //   529: bipush 47
    //   531: ldc_w 316
    //   534: bipush 46
    //   536: goto +319 -> 855
    //   539: aastore
    //   540: dup
    //   541: bipush 48
    //   543: ldc_w 317
    //   546: bipush 47
    //   548: goto +307 -> 855
    //   551: aastore
    //   552: dup
    //   553: bipush 49
    //   555: ldc_w 329
    //   558: bipush 48
    //   560: goto +295 -> 855
    //   563: aastore
    //   564: dup
    //   565: bipush 50
    //   567: ldc_w 331
    //   570: bipush 49
    //   572: goto +283 -> 855
    //   575: aastore
    //   576: dup
    //   577: bipush 51
    //   579: ldc_w 334
    //   582: bipush 50
    //   584: goto +271 -> 855
    //   587: aastore
    //   588: dup
    //   589: bipush 52
    //   591: ldc_w 333
    //   594: bipush 51
    //   596: goto +259 -> 855
    //   599: aastore
    //   600: dup
    //   601: bipush 53
    //   603: ldc_w 312
    //   606: bipush 52
    //   608: goto +247 -> 855
    //   611: aastore
    //   612: dup
    //   613: bipush 54
    //   615: ldc_w 335
    //   618: bipush 53
    //   620: goto +235 -> 855
    //   623: aastore
    //   624: dup
    //   625: bipush 55
    //   627: ldc_w 330
    //   630: bipush 54
    //   632: goto +223 -> 855
    //   635: aastore
    //   636: dup
    //   637: bipush 56
    //   639: ldc_w 325
    //   642: bipush 55
    //   644: goto +211 -> 855
    //   647: aastore
    //   648: dup
    //   649: bipush 57
    //   651: ldc_w 261
    //   654: bipush 56
    //   656: goto +199 -> 855
    //   659: aastore
    //   660: dup
    //   661: bipush 58
    //   663: ldc_w 311
    //   666: bipush 57
    //   668: goto +187 -> 855
    //   671: aastore
    //   672: dup
    //   673: bipush 59
    //   675: ldc_w 298
    //   678: bipush 58
    //   680: goto +175 -> 855
    //   683: aastore
    //   684: dup
    //   685: bipush 60
    //   687: ldc 48
    //   689: bipush 59
    //   691: goto +164 -> 855
    //   694: aastore
    //   695: dup
    //   696: bipush 61
    //   698: ldc 24
    //   700: bipush 60
    //   702: goto +153 -> 855
    //   705: aastore
    //   706: dup
    //   707: bipush 62
    //   709: ldc 34
    //   711: bipush 61
    //   713: goto +142 -> 855
    //   716: aastore
    //   717: dup
    //   718: bipush 63
    //   720: ldc 19
    //   722: bipush 62
    //   724: goto +131 -> 855
    //   727: aastore
    //   728: dup
    //   729: bipush 64
    //   731: ldc 30
    //   733: bipush 63
    //   735: goto +120 -> 855
    //   738: aastore
    //   739: dup
    //   740: bipush 65
    //   742: ldc 47
    //   744: bipush 64
    //   746: goto +109 -> 855
    //   749: aastore
    //   750: dup
    //   751: bipush 66
    //   753: ldc 81
    //   755: bipush 65
    //   757: goto +98 -> 855
    //   760: aastore
    //   761: dup
    //   762: bipush 67
    //   764: ldc 69
    //   766: bipush 66
    //   768: goto +87 -> 855
    //   771: aastore
    //   772: dup
    //   773: bipush 68
    //   775: ldc 96
    //   777: bipush 67
    //   779: goto +76 -> 855
    //   782: aastore
    //   783: dup
    //   784: bipush 69
    //   786: ldc 92
    //   788: bipush 68
    //   790: goto +65 -> 855
    //   793: aastore
    //   794: dup
    //   795: bipush 70
    //   797: ldc 60
    //   799: bipush 69
    //   801: goto +54 -> 855
    //   804: aastore
    //   805: dup
    //   806: bipush 71
    //   808: ldc 94
    //   810: bipush 70
    //   812: goto +43 -> 855
    //   815: aastore
    //   816: dup
    //   817: bipush 72
    //   819: ldc 97
    //   821: bipush 71
    //   823: goto +32 -> 855
    //   826: aastore
    //   827: dup
    //   828: bipush 73
    //   830: ldc 80
    //   832: bipush 72
    //   834: goto +21 -> 855
    //   837: aastore
    //   838: dup
    //   839: bipush 74
    //   841: ldc 84
    //   843: bipush 73
    //   845: goto +10 -> 855
    //   848: aastore
    //   849: putstatic 996	com/compiere/client/ComponentPanel:A	[Ljava/lang/String;
    //   852: goto +424 -> 1276
    //   855: swap
    //   856: invokevirtual 991	java/lang/String:toCharArray	()[C
    //   859: dup
    //   860: arraylength
    //   861: swap
    //   862: iconst_0
    //   863: istore_0
    //   864: swap
    //   865: dup_x1
    //   866: iconst_1
    //   867: if_icmpgt +79 -> 946
    //   870: dup
    //   871: iload_0
    //   872: dup2
    //   873: caload
    //   874: iload_0
    //   875: iconst_5
    //   876: irem
    //   877: tableswitch	default:+51 -> 928, 0:+31->908, 1:+36->913, 2:+41->918, 3:+46->923
    //   909: fstore_3
    //   910: goto +20 -> 930
    //   913: bipush 103
    //   915: goto +15 -> 930
    //   918: bipush 38
    //   920: goto +10 -> 930
    //   923: bipush 59
    //   925: goto +5 -> 930
    //   928: bipush 59
    //   930: ixor
    //   931: i2c
    //   932: castore
    //   933: iinc 0 1
    //   936: swap
    //   937: dup_x1
    //   938: ifne +8 -> 946
    //   941: dup2
    //   942: swap
    //   943: goto -71 -> 872
    //   946: swap
    //   947: dup_x1
    //   948: iload_0
    //   949: if_icmpgt -79 -> 870
    //   952: new 46	java/lang/String
    //   955: dup_x1
    //   956: swap
    //   957: invokespecial 151	java/lang/String:<init>	([C)V
    //   960: invokevirtual 994	java/lang/String:intern	()Ljava/lang/String;
    //   963: swap
    //   964: pop
    //   965: swap
    //   966: tableswitch	default:+-952 -> 14, 0:+-943->23, 1:+-934->32, 2:+-925->41, 3:+-916->50, 4:+-907->59, 5:+-897->69, 6:+-886->80, 7:+-875->91, 8:+-864->102, 9:+-853->113, 10:+-842->124, 11:+-831->135, 12:+-820->146, 13:+-809->157, 14:+-798->168, 15:+-787->179, 16:+-776->190, 17:+-765->201, 18:+-753->213, 19:+-741->225, 20:+-730->236, 21:+-719->247, 22:+-707->259, 23:+-695->271, 24:+-683->283, 25:+-671->295, 26:+-659->307, 27:+-647->319, 28:+-635->331, 29:+-623->343, 30:+-611->355, 31:+-599->367, 32:+-587->379, 33:+-575->391, 34:+-563->403, 35:+-551->415, 36:+-540->426, 37:+-529->437, 38:+-518->448, 39:+-507->459, 40:+-496->470, 41:+-485->481, 42:+-474->492, 43:+-463->503, 44:+-451->515, 45:+-439->527, 46:+-427->539, 47:+-415->551, 48:+-403->563, 49:+-391->575, 50:+-379->587, 51:+-367->599, 52:+-355->611, 53:+-343->623, 54:+-331->635, 55:+-319->647, 56:+-307->659, 57:+-295->671, 58:+-283->683, 59:+-272->694, 60:+-261->705, 61:+-250->716, 62:+-239->727, 63:+-228->738, 64:+-217->749, 65:+-206->760, 66:+-195->771, 67:+-184->782, 68:+-173->793, 69:+-162->804, 70:+-151->815, 71:+-140->826, 72:+-129->837, 73:+-118->848
    //   1277: aconst_null
    //   1278: sastore
    //   1279: putstatic 135	com/compiere/client/ComponentPanel:a	Lorg/compiere/util/CLogger;
    //   1282: return
  }
}