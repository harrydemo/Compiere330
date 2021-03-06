/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 3600 Bridge Parkway #102, Redwood City, CA 94065, USA      *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.install;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Handler;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import org.compiere.Compiere;
import org.compiere.apps.AEnv;
import org.compiere.plaf.CompierePLAF;
import org.compiere.swing.CFrame;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogErrorBuffer;
import org.compiere.util.CLogFile;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;

import com.compiere.client.ComponentPanel;

/**
 * Compiere Setup Frame.
 *
 * @author Jorg Janke
 * @version $Id: Setup.java,v 1.2 2006/07/30 00:57:42 jjanke Exp $
 */
public class Setup extends CFrame implements ActionListener, PropertyChangeListener {
	/** */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public Setup() {
		log.info(Compiere.getSummaryAscii());
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		CLogErrorBuffer eb = CLogErrorBuffer.get(true);
		if (eb != null && eb.isIssueError()) {
			eb.setIssueError(false);
		}
		//
		// addWindowListener(this);
		try {
			res.getString("CompiereServerSetup");
		} catch (Exception e) {
			System.err.print("Resource not found - use different language - " + e.toString());
			System.exit(1);
		}
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		/** Init Panel **/
		AEnv.showCenterScreen(this);
		try {
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			configurationPanel.dynInit();
			AEnv.positionCenterScreen(this);
			setCursor(Cursor.getDefaultCursor());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	} // Setup

	/** Logger */
	private static CLogger log = CLogger.getCLogger(Setup.class);
	/** Sync */
	private Object m_info = new Object();

	// Static UI
	static public ResourceBundle res = ResourceBundle.getBundle("org.compiere.translate.SetupRes");
	//
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFile = new JMenu();
	CMenuItem menuFileExit = new CMenuItem();
	private JMenu menuHelp = new JMenu();
	private CMenuItem menuHelpInfo = new CMenuItem();
	/** Window Status Bar */
	public JLabel statusBar = new JLabel();

	/** Environment Properties */
	protected Properties p_properties = new Properties();
	//
	private CardLayout cardLayout = new CardLayout();
	private CPanel cards = new CPanel(cardLayout);
	public final static String SWITCHCARDS = "switchCards";
	public final static String CONFIGURATION = "configuration";
	public final static String COMPONENTS = "components";
	public final static String PROGRESS = "progress";
	//
	private ConfigurationPanel configurationPanel = new ConfigurationPanel(statusBar, p_properties);
	private ComponentPanel componentPanel = new ComponentPanel();
	private ProgressPanel progressPanel = new ProgressPanel();

	/**
	 * Static Init
	 * 
	 * @throws Exception
	 */
	private void jbInit() throws Exception {
		this.setIconImage(Compiere.getImage16());
		this.setTitle(res.getString("CompiereServerSetup") + " - " + Compiere.MAIN_VERSION);
		//
		JPanel contentPane = (JPanel) this.getContentPane();
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(5);
		borderLayout.setVgap(5);
		contentPane.setLayout(borderLayout);
		//
		configurationPanel.addPropertyChangeListener(SWITCHCARDS, this);
		cards.add(configurationPanel, CONFIGURATION);
		componentPanel.addPropertyChangeListener(SWITCHCARDS, this);
		cards.add(componentPanel, COMPONENTS);
		cards.add(progressPanel, PROGRESS);
		contentPane.add(cards, BorderLayout.CENTER);
		cardLayout.show(cards, CONFIGURATION);

		// Status Bar
		statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
		statusBar.setText(" ");
		contentPane.add(statusBar, BorderLayout.SOUTH);
		// Menu
		menuFile.setText(res.getString("File"));
		menuFileExit.setText(res.getString("Exit"));
		menuFileExit.addActionListener(this);
		menuHelp.setText(res.getString("Help"));
		menuHelpInfo.setText(res.getString("Help"));
		menuHelpInfo.addActionListener(this);
		menuFile.add(menuFileExit);
		menuHelp.add(menuHelpInfo);
		menuBar.add(menuFile);
		menuBar.add(menuHelp);
		this.setJMenuBar(menuBar);
	} // jbInit

	/**
	 * Dispose
	 */
	@Override
	public void dispose() {
		super.dispose();
		log.info("");
	} // dispose

	/**
	 * Get Sync Info
	 * 
	 * @return installInfo
	 */
	public Object getInfo() {
		return m_info;
	} // getInfo

	/**
	 * Set Sync info
	 * 
	 * @param info info
	 */
	public void setInfo(Object info) {
		m_info = info;
	} // setInfo

	/**
	 * Action Listener. Exit or Help
	 * 
	 * @param e event
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menuFileExit) {
			System.exit(0);
		}
		else if (e.getSource() == menuHelpInfo) {
			new Setup_Help(this);
		}
	} // actionPerformed

	/**
	 * Property Change Listener. Change to Progress Card
	 * 
	 * @param evt event
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		log.config(evt.getOldValue() + "->" + evt.getNewValue());
		//
		String newCard = (String) evt.getNewValue();

		// Switch to Progress
		if (newCard.equals(PROGRESS)) {
			progressPanel.init(this, p_properties);
		} else if (newCard.equals(COMPONENTS)) {
			componentPanel.init(this, p_properties);
		} else
			return;

		cardLayout.show(cards, newCard);
	} // evt

	/**************************************************************************
	 * Start
	 * 
	 * @param args Log Level e.g. ALL, FINE
	 */
	public static void main(String[] args) {
		CLogMgt.initialize(true);
		Handler fileHandler = new CLogFile(System.getProperty("user.dir"), false, false);
		CLogMgt.addHandler(fileHandler);
		// Log Level
		if (args.length > 0) {
			CLogMgt.setLevel(args[0]);
		}else {
			CLogMgt.setLevel(Level.INFO);
		}
		// File Loger at least FINE
		if (fileHandler.getLevel().intValue() > Level.FINE.intValue()) {
			fileHandler.setLevel(Level.FINE);
		}
		// PLAF
		CompierePLAF.setPLAF(null);

		new Setup();
	} // main

} // Setup
