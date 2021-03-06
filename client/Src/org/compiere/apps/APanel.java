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
package org.compiere.apps;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.*;

import javax.swing.*;
import javax.swing.event.*;

import org.compiere.apps.search.*;
import org.compiere.framework.*;
import org.compiere.grid.*;
import org.compiere.grid.ed.*;
import org.compiere.model.*;
import org.compiere.plaf.*;
import org.compiere.print.*;
import org.compiere.process.*;
import org.compiere.swing.*;
import org.compiere.util.*;
import org.compiere.vos.*;

/**
 *	Main Application Panel.
 *  <pre>
 *  Structure:
 *      (MenuBar) -> to be added to owning window
 *		northPanel  (ToolBar)
 *		tabPanel
 *		southPanel  (StatusBar)
 *  </pre>
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: APanel.java,v 1.4 2006/07/30 00:51:27 jjanke Exp $
 */
public final class APanel extends CPanel
	implements DataStatusListener, ChangeListener, ActionListener, ASyncProcess
{
	/** */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new instance.
	 * Need to call initPanel for dynamic initialization
	 */
	public APanel()
	{
		super();
		m_ctx = Env.getCtx();
		//
		try
		{
			jbInit();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
		createMenu();
	}	//	APanel

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(APanel.class);

	/**
	 *	Dispose
	 */
	public void dispose()
	{
	//	log.config("");
		//  ignore changes
		m_disposing = true;
		//
		if (m_curAPanelTab != null)
		{
			m_curAPanelTab.unregisterPanel();
			m_curAPanelTab = null;
		}
		//  close panels
		if (tabPanel != null)
			tabPanel.dispose(this);
		tabPanel = null;
		//  All Workbenches
		if (m_mWorkbench != null)
		{
			for (int i = 0; i < m_mWorkbench.getWindowCount(); i++)
			{
				m_curWindowNo = m_mWorkbench.getWindowNo(i);
				log.info("#" + m_curWindowNo);
				m_ctx.setAutoCommit(m_curWindowNo, false);
				m_mWorkbench.dispose(i);
				m_ctx.removeWindow (m_curWindowNo);
			}   //  all Workbenchens
		}

		//  Get rid of remaining model
		if (m_mWorkbench != null)
			m_mWorkbench.dispose();
		m_mWorkbench = null;
		//  MenuBar
		if (menuBar != null)
			menuBar.removeAll();
		menuBar = null;
		//  ToolBar
		if (toolBar != null)
			toolBar.removeAll();
		toolBar = null;
		//  Prepare GC
		this.removeAll();
	}	//	dispose

	/**
	 * The Layout.
	 */
	private final BorderLayout mainLayout = new BorderLayout();
	private VTabbedPane tabPanel = new VTabbedPane(true);
	private final StatusBar statusBar = new StatusBar();
	private final CPanel northPanel = new CPanel();
	private JToolBar toolBar = new JToolBar();
	private JMenuBar menuBar = new JMenuBar();
	private final FlowLayout northLayout = new FlowLayout();

	/** Shared action names, e.g. for redirecting child components' actions */
	public static final String ACTION_NAME_FIRST = "First";
	public static final String ACTION_NAME_LAST = "Last";
	public static final String ACTION_NAME_PREV = "Previous";
	public static final String ACTION_NAME_NEXT = "Next";

	public static final String ACTION_NAME_FIND = "Find";
	public static final String ACTION_NAME_CHAT = "Chat";
	public static final String ACTION_NAME_ARCHIVE = "Archive";
	

	/**
	 * Initializes the state of this instance.
	 * @throws Exception
	 */
	private void jbInit() throws Exception
	{
		this.setLocale(Language.getLoginLanguage().getLocale());
		this.setLayout(mainLayout);

		//	tabPanel
		mainLayout.setHgap(2);
		mainLayout.setVgap(2);
		this.add(tabPanel, BorderLayout.CENTER);
		//	southPanel
		this.add(statusBar, BorderLayout.SOUTH);
		//	northPanel
		this.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(northLayout);
		northLayout.setAlignment(FlowLayout.LEFT);
		northPanel.add(toolBar, null);
	}	//	jbInit

	private AppsAction 		aPrevious, aNext, aParent, aDetail, aFirst, aLast,
							aNew, aCopy, aDelete, aIgnore, aPrint,
							aRefresh, aAttachment, aChat, aMulti, aFind,
							aWorkflow, aZoomAcross, aRequest, aUserDef, aArchive;
	/** Save Button			*/
	public AppsAction		aSave;
	/** Private Lock Button	*/
	public AppsAction		aLock;
	//	Local (added to toolbar)
	private AppsAction	    aReport, aEnd, aHelp, aExit;

	/**************************************************************************
	 *	Create Menu and Toolbar and registers keyboard actions.
	 *  - started from constructor
	 */
	private void createMenu()
	{
		/**
		 *	Menu
		 */
	//	menuBar.setHelpMenu();
		//								File
		JMenu mFile = AEnv.getMenu("File");
		menuBar.add(mFile);
		addAction("PrintScreen",	mFile,	KeyStroke.getKeyStroke(KeyEvent.VK_PRINTSCREEN, 0), 	false);
		addAction("ScreenShot",		mFile,	KeyStroke.getKeyStroke(KeyEvent.VK_PRINTSCREEN, Event.SHIFT_MASK), 	false);
		aReport = 	addAction("Report",			mFile, 	KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0),	false);
		aPrint = 	addAction("Print",			mFile, 	KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0),	false);
		mFile.addSeparator();
		aEnd =	 	addAction("End",			mFile, 	KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.ALT_MASK),	false);
		aExit =		addAction("Exit",			mFile, 	KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.SHIFT_MASK+Event.ALT_MASK),	false);
		//								Edit
		JMenu mEdit = AEnv.getMenu("Edit");
		menuBar.add(mEdit);
		aNew = 		addAction("New", 			mEdit, 	KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), false);
		aCopy =		addAction("Copy", 			mEdit, 	KeyStroke.getKeyStroke(KeyEvent.VK_F2, Event.SHIFT_MASK),	false);
		aDelete = 	addAction("Delete",			mEdit, 	KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0),	false);
		aSave = 	addAction("Save",			mEdit, 	KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),	false);
		mEdit.addSeparator();
		aIgnore = 	addAction("Ignore",			mEdit, 	KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),	false);
		aRefresh = 	addAction("Refresh",		mEdit, 	KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0),	false);
		mEdit.addSeparator();
		aFind = 	addAction(ACTION_NAME_FIND,			mEdit, 	KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), true);	//	toggle
		if (m_isPersonalLock)
			aLock = addAction("Lock",			mEdit, 	null,	true);		//	toggle
		//								View
		JMenu mView = AEnv.getMenu("View");
		menuBar.add(mView);
		addAction("InfoProduct",	mView, 	KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.ALT_MASK),	false);
		addAction("InfoBPartner",	mView, 	KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.SHIFT_MASK+Event.ALT_MASK),	false);
		if (MRole.getDefault().isShowAcct())
			addAction("InfoAccount",mView, 	KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.ALT_MASK+Event.CTRL_MASK),	false);
		AEnv.addMenuItem("InfoSchedule", null, null, mView, this);
		mView.addSeparator();
		AEnv.addMenuItem("InfoOrder", "Info", null, mView, this);
		AEnv.addMenuItem("InfoInvoice", "Info", null, mView, this);
		AEnv.addMenuItem("InfoInOut", "Info", null, mView, this);
		AEnv.addMenuItem("InfoPayment", "Info", null, mView, this);
		AEnv.addMenuItem("InfoCashLine", "Info", null, mView, this);
		AEnv.addMenuItem("InfoAssignment", "Info", null, mView, this);
		AEnv.addMenuItem("InfoAsset", "Info", null, mView, this);
		mView.addSeparator();
		aAttachment = addAction("Attachment",	mView, 	KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0),	true);		//	toggle
		aChat = addAction(ACTION_NAME_CHAT,				mView, 	KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0),	true);		//	toggle
		mView.addSeparator();
		aMulti =	addAction("Multi",			mView, 	KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0),	true);		//	toggle
		//								Go
		JMenu mGo = AEnv.getMenu("Go");
		menuBar.add(mGo);
		aFirst =	addAction(ACTION_NAME_FIRST,mGo, 	KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, Event.ALT_MASK),	false);
		aPrevious = addAction(ACTION_NAME_PREV, mGo, 	KeyStroke.getKeyStroke(KeyEvent.VK_UP, Event.ALT_MASK),	false);
		aNext = 	addAction(ACTION_NAME_NEXT, mGo, 	KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, Event.ALT_MASK),	false);
		aLast =		addAction(ACTION_NAME_LAST,	mGo, 	KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, Event.ALT_MASK),	false);
		mGo.addSeparator();
		aParent =	addAction("Parent", 		mGo, 	KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, Event.ALT_MASK),	false);
		aDetail =	addAction("Detail", 		mGo,	KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, Event.ALT_MASK),	false);
		mGo.addSeparator();
		aZoomAcross = addAction("ZoomAcross",	mGo, 	KeyStroke.getKeyStroke(KeyEvent.VK_F9, Event.SHIFT_MASK),	false);
		aRequest =  addAction("Request",		mGo, 	KeyStroke.getKeyStroke(KeyEvent.VK_F11, Event.SHIFT_MASK),	false);
		aArchive =  addAction(ACTION_NAME_ARCHIVE,		mGo, 	KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0),	false);
		addAction("Home", 			mGo,	null,	false);

		addAction(ACTION_NAME_FIRST,null, 	KeyStroke.getKeyStroke(KeyEvent.VK_HOME, Event.CTRL_MASK),	false);
		addAction(ACTION_NAME_PREV, null, 	KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),	false);
		addAction(ACTION_NAME_NEXT, null, 	KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0),	false);
		addAction(ACTION_NAME_LAST,	null, 	KeyStroke.getKeyStroke(KeyEvent.VK_END, Event.CTRL_MASK),	false);

		//								Tools
		JMenu mTools = AEnv.getMenu("Tools");
		menuBar.add(mTools);
		addAction("Calculator",	mTools, 	null,	false);
		addAction("Calendar",		mTools, 	null,	false);
		addAction("Editor",			mTools, 	null,	false);
		if (m_ctx.isShowAdvanced())
		{
		//	addAction("Script",	        mTools, 	null,	false);
			addAction("DataMigration", 	mTools, 	null, 	false);
		}
		if (AEnv.isWorkflowProcess())
			aWorkflow = addAction("WorkFlow",	mTools,		KeyStroke.getKeyStroke(KeyEvent.VK_F10, Event.SHIFT_MASK),	false);
		if (MRole.getDefault().isShowPreference())
		{
			MClient client = MClient.get(Env.getCtx());
			if (client.isUseBetaFunctions())
			{
				aUserDef = addAction("WinSize",     mTools, 	null,	false);
				mTools.addSeparator();
			}
			addAction("Preference",	mTools, 	KeyStroke.getKeyStroke(KeyEvent.VK_F12, Event.ALT_MASK),	false);
		}
		//								Help
		JMenu mHelp = AEnv.getMenu("Help");
		menuBar.add(mHelp);
		aHelp = 	addAction("Help",			mHelp, 	KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0),	false);
		addAction("Online",			mHelp, 	null,	false);
		addAction("EMailSupport",	mHelp,	null,	false);
		addAction("About",			mHelp, 	KeyStroke.getKeyStroke(KeyEvent.VK_F12, Event.SHIFT_MASK),	false);

		/**
		 *	ToolBar
		 */
		toolBar.add(aIgnore.getButton());		//	ESC
		toolBar.addSeparator();
		toolBar.add(aHelp.getButton());			//	F1
		toolBar.add(aNew.getButton());
		toolBar.add(aDelete.getButton());
		toolBar.add(aSave.getButton());
		toolBar.addSeparator();
		toolBar.add(aRefresh.getButton());      //  F5
		toolBar.add(aFind.getButton());
		toolBar.add(aAttachment.getButton());
		toolBar.add(aChat.getButton());
		toolBar.addSeparator();
		toolBar.add(aMulti.getButton());		//	F9
		toolBar.add(aArchive.getButton());		//	F10 is Windows Menu Key
		toolBar.add(aReport.getButton());		//	F11
		toolBar.add(aPrint.getButton());		//	F12
		toolBar.addSeparator();
		toolBar.add(aFirst.getButton());
		toolBar.add(aPrevious.getButton());
		toolBar.add(aNext.getButton());
		toolBar.add(aLast.getButton());
		toolBar.addSeparator();
		if (m_isPersonalLock)
		{
			toolBar.add(aLock.getButton());
			toolBar.addSeparator();
		}
		toolBar.add(aZoomAcross.getButton());	//	Shift-F9
		if (aWorkflow != null)
			toolBar.add(aWorkflow.getButton());	//	Shift-F10
		toolBar.add(aRequest.getButton());		//	Shift-F11
		toolBar.add(aEnd.getButton());			//	Shift-F12
		//
		if (CLogMgt.isLevelAll())
			Util.printActionInputMap(this);
	}	//	createMenu


	/**
	 *	Add (Toggle) Action to Toolbar and Menu
	 *  @param actionName action name
	 *  @param menu manu
	 *  @param accelerator accelerator
	 *  @param toggle toggle button
	 *  @return AppsAction
	 */
	private AppsAction addAction (String actionName, JMenu menu, KeyStroke accelerator, boolean toggle)
	{
		AppsAction action = new AppsAction(actionName, accelerator, toggle);
		if (menu != null)
			menu.add(action.getMenuItem());
		action.setDelegate(this);
	//	AbstractButton b = action.getButton();
	//	String s = null;
	//	if (b != null)
	//		s = b.getToolTipText();

		//	Key Strokes
		if (accelerator != null)
		{
			getInputMap(WHEN_IN_FOCUSED_WINDOW).put(accelerator, actionName);
			getActionMap().put(actionName, action);
		}
		//
		return action;
	}	//	addAction

	/**
	 *	Return MenuBar
	 *  @return JMenuBar
	 */
	public JMenuBar getMenuBar()
	{
		return menuBar;
	}	//	getMenuBar

	/**
	 *	Get Title of Window
	 *  @return String with Title
	 */
	public String getTitle()
	{
		if (m_mWorkbench.getWindowCount() > 1)
		{
			StringBuffer sb = new StringBuffer();
			sb.append(m_mWorkbench.getName());
			if ("Y".equals(System.getProperty(Env.SWING_RT)))
			{
				sb.append("  ")
					.append(m_ctx.getContext("#AD_User_Name")).append("@")
					.append(m_ctx.getContext("#AD_Client_Name")).append(".")
					.append(m_ctx.getContext("#AD_Org_Name")).append(" [")
					.append(m_ctx.getContext("#DB_UID")).append("]");
			}
			return sb.toString();
		}
		return Env.getHeader(m_ctx, m_curWindowNo);
	}	//	getTitle


	/**	The Context										*/
	private final Ctx				m_ctx;

	/** Workbench Model                                 */
	private GridWorkbench	m_mWorkbench;
	/** Current MTab                                    */
	private GridTab			m_curTab;
	/** Current GridController                          */
	private GridController  m_curGC;
	/** Current Window Panel                            */
	private CTabbedPane     m_curWinTab = null;
	/** Current Window No                               */
	private int				m_curWindowNo;
	/** Current Window Panel Index                      */
	private int				m_curTabIndex = -1;
	/**	Current Tab Order								*/
	private APanelTab		m_curAPanelTab = null;

	/** Dispose active                                  */
	private boolean         m_disposing = false;
	/** Save Error Message indicator                    */
	private boolean         m_errorDisplayed = false;
	/** Process Info                                    */
	private boolean         m_isLocked = false;
	/** Show Personal Lock								*/
	private boolean 		m_isPersonalLock = MRole.getDefault().isPersonalLock();
	/**	Last Modifier of Action Event					*/
	private int 			m_lastModifiers;


	/**************************************************************************
	 *	Dynamic Panel Initialization - either single window or workbench.
	 *  <pre>
	 *  either
	 *  - Workbench tabPanel    (VTabbedPane)
	 *      - Tab               (GridController)
	 *  or
	 *  - Workbench tabPanel    (VTabbedPane)
	 *      - Window            (VTabbedPane)
	 *          - Tab           (GridController)
	 *  </pre>
	 *  tabPanel
	 *  @param AD_Workbench_ID  if > 0 this is a workbench, AD_Window_ID ignored
	 *  @param AD_Window_ID     if not a workbench, Window ID
	 *  @param query			if not a Workbench, Zoom Query - additional SQL where clause
	 *  @return true if Panel is initialized successfully
	 */
	public boolean initPanel (int AD_Workbench_ID, int AD_Window_ID, Query query)
	{
		log.info("WB=" + AD_Workbench_ID + ", Win=" + AD_Window_ID + ", Query=" + query);
		this.setName("APanel" + AD_Window_ID);

		//  Single Window
		if (AD_Workbench_ID == 0)
			m_mWorkbench = new GridWorkbench(m_ctx, AD_Window_ID);
		else
		//  Workbench
		{
		//	m_mWorkbench = new MWorkbench(m_ctx);
		//	if (!m_mWorkbench.initWorkbench (AD_Workbench_ID))
		//	{
		//		log.log(Level.SEVERE, "APanel.initWindow - No Workbench Model");
		//		return false;
		//	}
		//	tabPanel.setWorkbench(true);
		//	tabPanel.addChangeListener(this);
			ADialog.warn(0, this, "","Not implemented yet");
			return false;
		}

		Dimension windowSize = m_mWorkbench.getWindowSize();

		/**
		 *  WorkBench Loop
		 */
		for (int wb = 0; wb < m_mWorkbench.getWindowCount(); wb++)
		{
			//  Get/set WindowNo
			m_curWindowNo = Env.createWindowNo (this);			                //  Timing: ca. 1.5 sec
			m_mWorkbench.setWindowNo(wb, m_curWindowNo);
			//  Set AutoCommit for this Window
			m_ctx.setAutoCommit(m_curWindowNo, m_ctx.isAutoCommit());
			boolean autoNew = m_ctx.isAutoNew();
			m_ctx.setAutoNew(m_curWindowNo, autoNew);

			//  Workbench Window
			VTabbedPane window = null;
			//  just one window
			if (m_mWorkbench.getWindowCount() == 1)
			{
				window = tabPanel;
				window.setWorkbench(false);
			}
			else
			{
				VTabbedPane tp = new VTabbedPane(false);
				window = tp;
			}
			//  Window Init
			window.addChangeListener(this);

			/**
			 *  Init Model
			 */
			int wbType = m_mWorkbench.getWindowType(wb);

			/**
			 *  Window
			 */
			if (wbType == GridWorkbench.TYPE_WINDOW)
			{
				HashMap<Integer,GridController> includedMap = new HashMap<Integer,GridController>(4);
				//
				GridWindowVO wVO = AEnv.getMWindowVO(m_curWindowNo, m_mWorkbench.getWindowID(wb), 0);
				if (wVO == null)
				{
					ADialog.error(0, null, "AccessTableNoView", "(No Window Model Info)");
					return false;
				}
				GridWindow mWindow = new GridWindow (wVO);			                //  Timing: ca. 0.3-1 sec
				//	Set SO/AutoNew for Window
				m_ctx.setIsSOTrx (m_curWindowNo, mWindow.isSOTrx());
				if (!autoNew && mWindow.isTransaction())
					m_ctx.setAutoNew(m_curWindowNo, true);
				m_mWorkbench.setMWindow(wb, mWindow);
				if (windowSize == null)
					windowSize = mWindow.getWindowSize();

				/**
				 *  Window Tabs
				 */
				int tabSize = mWindow.getTabCount();
				boolean goSingleRow = query != null;	//	Zoom Query
				for (int tab = 0; tab < tabSize; tab++)
				{
					boolean included = false;
					//  MTab
					GridTab gTab = m_mWorkbench.getMWindow(wb).getTab(tab);
					//  Query first tab
					if (tab == 0)
					{
						//  initial user query for single workbench tab
						if (m_mWorkbench.getWindowCount() == 1)
						{
							query = initialQuery (query, gTab,	//	onlyCurrentRows
								wb == 0 && mWindow.isTransaction());
							if (query != null && query.getRecordCount() <= 1)
								goSingleRow = true;
						}
						else if (wb != 0)
						//  workbench dynamic query for dependent windows
						{
							query = m_mWorkbench.getQuery();
						}
						//	Set initial Query on first tab
						if (query != null)
							gTab.setQuery(query);
						if (wb == 0)
							m_curTab = gTab;
					}	//	query on first tab

					Component tabElement = null;
					//  GridController
					if (gTab.isSortTab())
					{
						VSortTab st = new VSortTab(m_curWindowNo, gTab.getAD_Table_ID(),
							gTab.getAD_ColumnSortOrder_ID(), gTab.getAD_ColumnSortYesNo_ID());
						st.setTabLevel(gTab.getTabLevel());
						tabElement = st;
					}
					else	//	normal tab
					{
						GridController gc = new GridController(true);	//  Timing: ca. .1 sec
						CompiereColor cc = mWindow.getColor();
						if (cc != null)
							gc.setBackgroundColor(cc);                  //  set color on Window level
						gc.initGrid(gTab, false, m_curWindowNo, this, mWindow);  //  will set color on Tab level
																		//  Timing: ca. 6-7 sec for first .2 for next
						gc.addDataStatusListener(this);
						gc.registerESCAction(aIgnore);      //  register Escape Key
						//	Set First Tab
						if (wb == 0 && tab == 0)
						{
							m_curGC = gc;
							Dimension size = gc.getPreferredSize();     //  Screen Sizing
							size.width += 4;
							size.height += 4;
							gc.setPreferredSize(size);
						}
						tabElement = gc;
						//	If we have a zoom query, switch to single row
						if (tab == 0 && goSingleRow)
							gc.switchSingleRow();

						//	Store GC if it has a included Tab
						if (gTab.getIncluded_Tab_ID() != 0)
							includedMap.put(Integer.valueOf(gTab.getIncluded_Tab_ID()), gc);

						//	Is this tab included?
						if (includedMap.size() > 0)
						{
							GridController parent = includedMap.get(Integer.valueOf(gTab.getAD_Tab_ID()));
							if (parent != null)
							{
								included = parent.includeTab(gc);
								if (!included)
									log.log(Level.SEVERE, "Not Included = " + gc);
							}
						}
					}	//	normal tab

					if (!included)	//  Add to TabbedPane
					{
						StringBuffer tabName = new StringBuffer ();
						tabName.append ("<html>");
						if (gTab.isReadOnly())
							tabName.append("<i>");
						int pos = gTab.getName ().indexOf (" ");
						if (pos == -1)
							tabName.append (gTab.getName ()).append ("<br>&nbsp;");
						else
						{
							tabName.append (gTab.getName().substring (0, pos))
							  .append ("<br>")
							  .append (gTab.getName().substring(pos + 1));
						}
						if (gTab.isReadOnly())
							tabName.append("</i>");
						tabName.append ("</html>");
						//	Add Tab - sets ALT-<number> and Shift-ALT-<x>
						window.addTab (tabName.toString(), gTab, tabElement);
					}
				}   //  Tab Loop
			//  Tab background
			//	window.setBackgroundColor(new CompiereColor(Color.magenta, Color.green));
			}   //  Type-MWindow

			//  Single Workbench Window Tab
			if (m_mWorkbench.getWindowCount() == 1)
			{
				window.setToolTipText(m_mWorkbench.getDescription(wb));
			}
			else
			//  Add Workbench Window Tab
			{
				tabPanel.addTab(m_mWorkbench.getName(wb), m_mWorkbench.getIcon(wb), window, m_mWorkbench.getDescription(wb));
			}
			//  Used for Env.getHeader
			m_ctx.setContext(m_curWindowNo, "WindowName", m_mWorkbench.getName(wb));

		}   //  Workbench Loop

		//  stateChanged (<->) triggered
		toolBar.setName(getTitle());
		m_curTab.getTableModel().setChanged(false);
		//	Set Detail Button
		aDetail.setEnabled(0 != m_curWinTab.getTabCount()-1);

		//	Enable/Disable Tabs dynamically
		if (m_curWinTab instanceof VTabbedPane)
			((VTabbedPane)m_curWinTab).evaluate(null);
		//	Size
		if (windowSize != null)
			setPreferredSize(windowSize);
		Dimension size = getPreferredSize();
		log.info( "fini - " + size);
		m_curWinTab.requestFocusInWindow();
		return true;
	}	//	initPanel

	/**
	 * 	Get Current Window No
	 *	@return win no
	 */
	public int getWindowNo()
	{
		return m_curWindowNo;
	}	//	getWindowNo

	/**
	 * 	Initial Query
	 *	@param query initial query
	 *	@param mTab tab
	 *	@param trxWindow first tab of p_trx window
	 *	@return query or null
	 */
	private Query initialQuery (Query query, GridTab mTab, boolean trxWindow)
	{
		//	We have a (Zoom) query
		if (query != null && query.isActive() && query.getRecordCount() < 10)
			return query;
		//
		StringBuffer where = new StringBuffer();
		//	Query automatically if high volume and no query
		boolean require = mTab.isHighVolume();
		if (!require && !trxWindow)				//	No Trx Window
		{
			String wh1 = mTab.getWhereExtended();
			if (wh1 == null || wh1.length() == 0)
				wh1 = mTab.getWhereClause();
			if (wh1 != null && wh1.length() > 0)
				where.append(wh1);
			//
			if (query != null)
			{
				String wh2 = query.getWhereClause();
				if (wh2.length() > 0)
				{
					if (where.length() > 0)
						where.append (" AND ");
					where.append(wh2);
				}
			}
			//
			StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM ")
				.append(mTab.getTableName());
			if (where.length() > 0)
				sql.append(" WHERE ").append(where);
			//	Does not consider security
			int no = DB.getSQLValue(null, sql.toString());
			//
			require = MRole.getDefault().isQueryRequire(no);
		}
		//	Show Query
		if (require)
		{
			GridField[] findFields = mTab.getFields();
			Find find = new Find (Env.getFrame(this), m_curWindowNo,
				mTab.getName(), mTab.getAD_Tab_ID(),
				mTab.getAD_Table_ID(), mTab.getTableName(),
				where.toString(), findFields, 10);	//	no query below 10
			query = find.getQuery();
			int onlyCurrentDays = find.getCurrentDays();
			boolean created = find.getIsCreated();
			find.dispose();
			find = null;
			//	only
			if (onlyCurrentDays > 0)
			{
				if (query == null)
					query = new Query(mTab.getTableName());
				query.addRestriction (created ? "Created" : "Updated",
					Query.GREATER_EQUAL, "addDays(SysDate, -" + onlyCurrentDays + ")");
			}
		}
		return query;
	}	//	initialQuery


	/**
	 *  Get Window Index
	 *  @return Window Index
	 */
	private int getWindowIndex()
	{
		//  only one window
		if (m_mWorkbench.getWindowCount() == 1)
			return 0;
		//  workbench
		return tabPanel.getSelectedIndex();
	}   //  getWindowIndex

	/**
	 *  Is first Tab (on Window)
	 *  @return true if the panel displays the first tab
	 */
	protected boolean isFirstTab()
	{
		return m_curWinTab.getSelectedIndex() == 0;
	}   //  isFirstTab

	/**
	 * 	Get Window Image
	 *	@return image or null
	 */
	public Image getImage()
	{
		return m_mWorkbench.getImage(getWindowIndex());
	}	//	getImage


	/**************************************************************************
	 *	Data Status Listener (row change)			^ | v
	 *  @param e event
	 */
	public void dataStatusChanged (DataStatusEvent e)
	{
		if (m_disposing)
			return;
		log.info(e.getMessage());
		String dbInfo = e.getMessage();
		boolean findPressed = m_curTab.isQueryActive() || m_curTab.getOnlyCurrentDays() > 0;
		if (findPressed)
			dbInfo = "[ " + dbInfo + " ]";
		statusBar.setStatusDB(dbInfo, e);

		//	Set Message / Info
		if (e.getAD_Message() != null || e.getInfo() != null)
		{
			StringBuffer sb = new StringBuffer();
			String msg = e.getMessage();
			if (msg != null && msg.length() > 0)
				sb.append(Msg.getMsg(m_ctx, e.getAD_Message()));
			String info = e.getInfo();
			if (info != null && info.length() > 0)
			{
				if (sb.length() > 0 && !sb.toString().trim().endsWith(":"))
					sb.append(": ");
				sb.append(info);
			}
			if (sb.length() > 0)
			{
				int pos = sb.indexOf("\n");
				if (pos != -1)  // replace CR/NL
					sb.replace(pos, pos+1, " - ");
				setStatusLine (sb.toString (), e.isError ());
			}
		}

		//  Confirm Error
		if (e.isError() && !e.isConfirmed())
		{
			ADialog.error(m_curWindowNo, this, e.getAD_Message(), e.getInfo());
			e.setConfirmed(true);   //  show just once - if MTable.setCurrentRow is involved the status event is re-issued
			m_errorDisplayed = true;
		}
		//  Confirm Warning
		else if (e.isWarning() && !e.isConfirmed())
		{
			ADialog.warn(m_curWindowNo, this, e.getAD_Message(), e.getInfo());
			e.setConfirmed(true);   //  show just once - if MTable.setCurrentRow is involved the status event is re-issued
		}

		//	update Navigation
		boolean firstRow = e.isFirstRow();
		aFirst.setEnabled(!firstRow);
		aPrevious.setEnabled(!firstRow);
		boolean lastRow = e.isLastRow();
		aNext.setEnabled(!lastRow);
		aLast.setEnabled(!lastRow);

		//	update Change
		boolean changed = e.isChanged() || e.isInserting();
		boolean readOnly = m_curTab.isReadOnly();
		boolean insertRecord = !readOnly;
		if (insertRecord)
			insertRecord = m_curTab.isInsertRecord();
		aNew.setEnabled(!changed && insertRecord);
		aCopy.setEnabled(!changed && insertRecord);
		aRefresh.setEnabled(!changed);
		aDelete.setEnabled(!changed && !readOnly);
		//
		if (readOnly && m_curTab.isAlwaysUpdateField())
			readOnly = false;
		aIgnore.setEnabled(changed && !readOnly);
		aSave.setEnabled(changed && !readOnly);
		//
		//	No Rows
		if (e.getTotalRows() == 0 && insertRecord)
		{
			aNew.setEnabled(true);
			aDelete.setEnabled(false);
		}

		//	Single-Multi
		aMulti.setPressed(!m_curGC.isSingleRow());

		//	History	or Query Active
		aFind.setPressed(findPressed);

		//	Transaction info
		String trxInfo = GridTab.getTrxInfo(m_curTab.getTableName(), m_ctx, m_curTab.getWindowNo(), m_curTab.getTabNo());
		if (trxInfo != null)
			statusBar.setInfo(trxInfo);

		//	Check Attachment
		boolean canHaveAttachment = m_curTab.canHaveAttachment();		//	not single _ID column
		//
		if (canHaveAttachment && e.isLoading() && m_curTab.getCurrentRow() > e.getLoadedRows())
			canHaveAttachment = false;
		if (canHaveAttachment && m_curTab.getRecord_ID() == -1)    //	No Key
			canHaveAttachment = false;
		if (canHaveAttachment)
		{
			aAttachment.setEnabled(true);
			aAttachment.setPressed(m_curTab.hasAttachment());
			aChat.setEnabled(true);
			aChat.setPressed(m_curTab.hasChat());
		}
		else
		{
			aAttachment.setEnabled(false);
			aChat.setEnabled(false);
		}
		//	Lock Indicator
		if (m_isPersonalLock)
			aLock.setPressed(m_curTab.isLocked());

		if (m_curWinTab instanceof VTabbedPane)
			((VTabbedPane)m_curWinTab).evaluate(e);
	//	log.info("- fini", e.getMessage());
	}	//	dataStatusChanged

	/**
	 *	Set Status Line to text
	 *  @param text clear text
	 *  @param error error flag
	 */
	public void setStatusLine (String text, boolean error)
	{
		log.fine(text);
		statusBar.setStatusLine(text, error);
	}	//	setStatusLine

	/**
	 *	Indicate Busy
	 *  @param busy busy
	 *  @param focus request focus
	 */
	private void setBusy (boolean busy, boolean focus)
	{
		m_isLocked = busy;
		//
		JFrame frame = Env.getFrame(this);
		if (frame == null)  //  during init
			return;
		if (frame instanceof AWindow)
			((AWindow)frame).setBusy(busy);
	//	String processing = Msg.getMsg(m_ctx, "Processing");
		if (busy)
		{
	//		setStatusLine(processing);
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		}
		else
		{
			this.setCursor(Cursor.getDefaultCursor());
			frame.setCursor(Cursor.getDefaultCursor());
			if (focus)
				m_curGC.requestFocusInWindow();
	//		if (statusBar.getStatusLine().equals(processing))
	//			statusBar.setStatusLine("");
		}
	}	//	set Busy


	/**************************************************************************
	 *	Change Listener - (tab change)			<->
	 *  @param e event
	 */
	public void stateChanged (ChangeEvent e)
	{
		if (m_disposing)
			return;
		log.info(e.toString());
		setBusy(true, true);

		VTabbedPane tp = (VTabbedPane)e.getSource();
		boolean back = false;
		boolean isAPanelTab = false;

		//  Workbench Tab Change
		if (tp.isWorkbench())
		{
			int WBIndex = tabPanel.getSelectedIndex();
			m_curWindowNo = m_mWorkbench.getWindowNo(WBIndex);
			//  Window Change
			log.info("curWin=" + m_curWindowNo + " - Win=" + tp);
			if (tp.getSelectedComponent() instanceof CTabbedPane)
				m_curWinTab = (CTabbedPane)tp.getSelectedComponent();
			else
				throw new java.lang.IllegalArgumentException("Window does not contain Tabs");
			if (m_curWinTab.getSelectedComponent() instanceof GridController)
				m_curGC = (GridController)m_curWinTab.getSelectedComponent();
		//	else if (m_curWinTab.getSelectedComponent() instanceof APanelTab)
		//		isAPanelTab = true;
			else
				throw new java.lang.IllegalArgumentException("Window-Tab does not contain GridControler");
			//  change pointers
			m_curTabIndex = m_curWinTab.getSelectedIndex();
		}
		else
		{
			//  Just a Tab Change
			log.info("Tab=" + tp);
			m_curWinTab = tp;
			int tpIndex = m_curWinTab.getSelectedIndex();
			back = tpIndex < m_curTabIndex;
			GridController gc = null;
			if (m_curWinTab.getSelectedComponent() instanceof GridController)
				gc = (GridController)m_curWinTab.getSelectedComponent();
			else if (m_curWinTab.getSelectedComponent() instanceof APanelTab)
				isAPanelTab = true;
			else
				throw new java.lang.IllegalArgumentException("Tab does not contain GridControler");
			//  Save old Tab
			if (m_curGC != null)
			{
				m_curGC.stopEditor(true);
				//  has anything changed?
				if (m_curTab.needSave(true, false))
				{   //  do we have real change
					if (m_curTab.needSave(true, true))
					{
						//	Automatic Save
						if (m_ctx.isAutoCommit(m_curWindowNo))
						{
							if (!m_curTab.dataSave(true))
							{	//  there is a problem, so we go back
								m_curWinTab.setSelectedIndex(m_curTabIndex);
								setBusy(false, true);
								return;
							}
						}
						//  explicitly ask when changing tabs
						else if (ADialog.ask(m_curWindowNo, this, "SaveChanges?", m_curTab.getCommitWarning()))
						{   //  yes we want to save
							if (!m_curTab.dataSave(true))
							{   //  there is a problem, so we go back
								m_curWinTab.setSelectedIndex(m_curTabIndex);
								setBusy(false, true);
								return;
							}
						}
						else    //  Don't save
							m_curTab.dataIgnore();
					}
					else    //  new record, but nothing changed
						m_curTab.dataIgnore();
				}   //  there is a change
			}
			if (m_curAPanelTab != null)
			{
				m_curAPanelTab.saveData();
				m_curAPanelTab.unregisterPanel();
				m_curAPanelTab = null;
			}

			//	new tab
		//	if (m_curTabIndex >= 0)
		//		m_curWinTab.setForegroundAt(m_curTabIndex, CompierePLAF.getTextColor_Normal());
		//	m_curWinTab.setForegroundAt(tpIndex, CompierePLAF.getTextColor_OK());
			m_curTabIndex = tpIndex;
			if (!isAPanelTab)
				m_curGC = gc;
		}

		boolean parentValid = true;

		//	Sort Tab Handling
		if (isAPanelTab)
		{
			m_curAPanelTab = (APanelTab)m_curWinTab.getSelectedComponent();
			m_curAPanelTab.registerAPanel(this);
			m_curAPanelTab.loadData();
		}
		else	//	Cur Tab Setting
		{
			m_curGC.activate();
			m_curTab = m_curGC.getMTab();


			//	Refresh only current row when tab is current
			if (back && m_curTab.isCurrent())
			{
				m_curTab.dataRefresh();
			}
			else	//	Requery & autoSize
			{
				MRole role = MRole.getDefault();
				m_curGC.query (m_curTab.getOnlyCurrentDays(),
					role.getMaxQueryRecords(), false);	//	updated
			}

			String lc = m_curTab.getLinkColumnName();
			String lcValue = m_ctx.getContext(m_curWindowNo, lc );
			if( lc.length() > 0 && lcValue.length() == 0 )
			{
				parentValid = false;
			}

			//  Set initial record
			if (m_curTab.getRowCount() == 0)
			{
				//	Automatically create New Record, if none & tab not RO
				if( !m_curTab.isReadOnly() &&
					(m_ctx.isAutoNew(m_curWindowNo)
							|| m_curTab.isQueryNewRecord()) && parentValid )
				{
					log.config("No record - New - AutoNew=" + m_ctx.isAutoNew(m_curWindowNo)
						+ " - QueryNew=" + m_curTab.isQueryNewRecord());
					m_curTab.dataNew(false);
				}
				else	//	No Records found
				{
					aSave.setEnabled(false);
					aDelete.setEnabled(false);
				}
				m_curTab.navigateCurrent();     //  updates counter
				m_curGC.dynamicDisplay(-1);
			}
		//	else		##CHANGE
		//		m_curTab.navigateCurrent();
		}

		//	Update <-> Navigation
		aDetail.setEnabled(m_curTabIndex != m_curWinTab.getTabCount()-1);
		aParent.setEnabled(m_curTabIndex != 0 && m_curWinTab.getTabCount() > 1);

		//	Document Print
		aPrint.setEnabled(m_curTab.isPrinted());
		//	Query
		boolean findPressed = m_curTab.isQueryActive() || m_curTab.getOnlyCurrentDays() > 0;
		aFind.setPressed(findPressed);


		//	Order Tab
		if (isAPanelTab)
		{
			aMulti.setPressed(false);
			aMulti.setEnabled(false);
			aNew.setEnabled(parentValid);
			aDelete.setEnabled(false);
			aFind.setEnabled(false);
			aRefresh.setEnabled(false);
			aAttachment.setEnabled(false);
			aChat.setEnabled(false);
		}
		else	//	Grid Tab
		{
			aMulti.setEnabled(true);
			aMulti.setPressed(!m_curGC.isSingleRow());
			aFind.setEnabled(true);
			aRefresh.setEnabled(true);
			aAttachment.setEnabled(true);
			aChat.setEnabled(true);
		}
		//
		m_curWinTab.requestFocusInWindow();
		setBusy(false, true);
		log.config( "fini");
	}	//	stateChanged

	/**
	 *	Navigate to Detail Tab			->
	 */
	private void cmd_detail()
	{
		int index = m_curWinTab.getSelectedIndex();
		if (index == m_curWinTab.getTabCount()-1)
			return;
		m_curGC.getTable().removeEditor();
		m_curWinTab.setSelectedIndex(index+1);
	}	//	navigateDetail

	/**
	 *	Navigate to Parent Tab			<-
	 */
	private void cmd_parent()
	{
		int index = m_curWinTab.getSelectedIndex();
		if (index == 0)
			return;
		m_curGC.getTable().removeEditor();
		m_curWinTab.setSelectedIndex(index-1);
	}	//	navigateParent


	/**************************************************************************
	 *	Action Listener
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		log.info(e.getActionCommand() + " - " + e.getModifiers());
		//	+ " - " + new Timestamp(e.getWhen()) + " " + isUILocked());
		if (m_disposing || isUILocked())
			return;

		m_lastModifiers = e.getModifiers();
		String cmd = e.getActionCommand();
		//	Do ScreenShot w/o busy
		if (cmd.equals("ScreenShot"))
		{
			AEnv.actionPerformed (e.getActionCommand(), m_curWindowNo, this);
			return;
		}
		//	Popup Menues
		else if (cmd.equals(aReport.getName()))
		{
			cmd_report();
			return;
		}
		else if (cmd.equals(aZoomAcross.getName()))
		{
			cmd_zoomAcross();
			return;
		}
		else if (cmd.equals(aRequest.getName()))
		{
			cmd_request();
			return;
		}


		//  Problem: doubleClick detection - can't disable button as clicking button may change button status
		setBusy (true, true);
		//  Command Buttons
		if (e.getSource() instanceof VButton)
		{
			actionButton((VButton)e.getSource());
			setBusy(false, true);
			return;
		}

		try
		{
			//	File
			if (cmd.equals(aPrint.getName()))
				cmd_print();
			else if (cmd.equals(aEnd.getName()))
				cmd_end(false);
			else if (cmd.equals(aExit.getName()))
				cmd_end(true);
			//	Edit
			else if (cmd.equals(aNew.getName()))
				cmd_new(false);
			else if (cmd.equals(aSave.getName()))
				cmd_save(true);
			else if (cmd.equals(aCopy.getName()))
				cmd_new(true);
			else if (cmd.equals(aDelete.getName()))
				cmd_delete();
			else if (cmd.equals(aIgnore.getName()))
				cmd_ignore();
			else if (cmd.equals(aRefresh.getName()))
				cmd_refresh();
			else if (cmd.equals(aFind.getName()))
				cmd_find();
			else if (m_isPersonalLock && cmd.equals(aLock.getName()))
				cmd_lock();
			//	View
			else if (cmd.equals(aAttachment.getName()))
				cmd_attachment();
			else if (cmd.equals(aChat.getName()))
				cmd_chat();
			else if (cmd.equals(aMulti.getName()))
				m_curGC.switchRowPresentation();
			//	Go
			else if (cmd.equals(aFirst.getName()))
			{	/*cmd_save(false);*/
				m_curGC.getTable().removeEditor();
				m_curTab.navigate(0);
				m_curGC.requestFocusInWindow();
			}
			else if (cmd.equals(aPrevious.getName()))
			{	/*cmd_save(false);*/
				m_curGC.getTable().removeEditor();
				m_curTab.navigateRelative(-1);
				m_curGC.requestFocusInWindow();
			}
			else if (cmd.equals(aNext.getName()))
			{	/*cmd_save(false); */
				m_curGC.getTable().removeEditor();
				m_curTab.navigateRelative(+1);
				m_curGC.requestFocusInWindow();
			}
			else if (cmd.equals(aLast.getName()))
			{	/*cmd_save(false);*/
				m_curGC.getTable().removeEditor();
				m_curTab.navigate(m_curTab.getRowCount()-1);
				m_curGC.requestFocusInWindow();
			}
			else if (cmd.equals(aParent.getName()))
				cmd_parent();
			else if (cmd.equals(aDetail.getName()))
				cmd_detail();
			else if (cmd.equals(aArchive.getName()))
				cmd_archive();
			//	Tools
			else if (aWorkflow != null && cmd.equals(aWorkflow.getName()))
			{
				if (m_curTab.getRecord_ID() <= 0)
					;
				else if (m_curTab.getTabNo() == 0 && m_mWorkbench.getMWindow(getWindowIndex()).isTransaction())
					AEnv.startWorkflowProcess(m_curTab.getAD_Table_ID(), m_curTab.getRecord_ID());
				else
					AEnv.startWorkflowProcess(m_curTab.getAD_Table_ID(), m_curTab.getRecord_ID());
			}
			else if (aUserDef != null && cmd.equals(aUserDef.getName()))
				cmd_userDef();
			//	Help
			else if (cmd.equals(aHelp.getName()))
				cmd_help();
			//  General Commands (Environment)
			else if (!AEnv.actionPerformed (e.getActionCommand(), m_curWindowNo, this))
				log.log(Level.SEVERE, "No action for: " + cmd);
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, cmd, ex);
			String msg = ex.getMessage();
			if (msg == null || msg.length() == 0)
				msg = ex.toString();
			msg = Msg.parseTranslation(m_ctx, msg);
			ADialog.error(m_curWindowNo, this, "Error", msg);
		}
		//
		m_curWinTab.requestFocusInWindow();
		setBusy(false, true);
	}	//	actionPerformed

	/**
	 *  Create New Record
	 *  @param copy true if current record is to be copied
	 */
	private void cmd_new (boolean copy)
	{
		log.config("copy=" + copy);
		if (!m_curTab.isInsertRecord())
		{
			log.warning("Insert Record disabled for Tab");
			return;
		}
		cmd_save(false);
		m_curTab.dataNew (copy);
		m_curGC.dynamicDisplay(-1);
	//	m_curTab.getTableModel().setChanged(false);
	}   //  cmd_new

	/**
	 *  Confirm & delete record
	 */
	private void cmd_delete()
	{
		if (m_curTab.isReadOnly())
			return;
		int keyID = m_curTab.getRecord_ID();
		if (ADialog.ask(m_curWindowNo, this, "DeleteRecord?")
			&& m_curTab.dataDelete())
		{
				m_curGC.requestFocus();
				m_curGC.rowChanged(false, keyID);
		}
		m_curWinTab.requestFocusInWindow();
		m_curGC.dynamicDisplay(-1);
	}   //  cmd_delete

	/**
	 *  If required ask if you want to save and save it
	 *  @param manualCmd true if invoked manually (i.e. force)
	 *  @return true if saved
	 */
	private boolean cmd_save (boolean manualCmd)
	{
		if (m_curAPanelTab != null)
			manualCmd = false;
		log.config("Manual=" + manualCmd);
		m_errorDisplayed = false;
		m_curGC.stopEditor(true);

		if (m_curAPanelTab != null)
		{
			m_curAPanelTab.saveData();
			aSave.setEnabled(false);	//	set explicitly
		}

		if (m_curTab.getCommitWarning().length() > 0 && m_curTab.needSave(true, false))
			if (!ADialog.ask(m_curWindowNo, this, "SaveChanges?", m_curTab.getCommitWarning()))
				return false;

		//  manually initiated
		boolean retValue = m_curTab.dataSave(manualCmd);
		//   if there is no previous error
		if (manualCmd && !retValue && !m_errorDisplayed)
		{
			ADialog.error(m_curWindowNo, this, "SaveIgnored");
			setStatusLine(Msg.getMsg(m_ctx, "SaveIgnored"), true);
		}
		m_curGC.rowChanged(true, m_curTab.getRecord_ID());
		if (manualCmd)
			m_curGC.dynamicDisplay(-1);
		return retValue;
	}   //  cmd_save

	/**
	 *  Ignore
	 */
	private void cmd_ignore()
	{
		m_curGC.stopEditor(false);
		m_curTab.dataIgnore();
		m_curGC.dynamicDisplay(-1);
	}   //  cmd_ignore

	/**
	 *  Refresh
	 */
	private void cmd_refresh()
	{
		cmd_save(false);
		m_curTab.dataRefreshAll();
		m_curGC.dynamicDisplay(-1);
	}   //  cmd_refresh

	/**
	 *	Print standard Report
	 */
	private void cmd_report ()
	{
		log.info("");
		if (!MRole.getDefault().isCanReport(m_curTab.getAD_Table_ID()))
		{
			ADialog.error(m_curWindowNo, this, "AccessCannotReport");
			return;
		}

		cmd_save(false);

		//	Query
		Query query = new Query(m_curTab.getTableName());
		//	Link for detail records
		String queryColumn = m_curTab.getLinkColumnName();
		//	Current row otherwise
		if (queryColumn.length() == 0)
			queryColumn = m_curTab.getKeyColumnName();
		//	Find display
		String infoName = null;
		String infoDisplay = null;
		for (int i = 0; i < m_curTab.getFieldCount(); i++)
		{
			GridField field = m_curTab.getField(i);
			if (field.isKey())
				infoName = field.getHeader();
			if ((field.getColumnName().equals("Name") || field.getColumnName().equals("DocumentNo") )
				&& field.getValue() != null)
				infoDisplay = field.getValue().toString();
			if (infoName != null && infoDisplay != null)
				break;
		}
		if (queryColumn.length() != 0)
		{
			if (queryColumn.endsWith("_ID"))
				query.addRestriction(queryColumn, Query.EQUAL,
					Integer.valueOf(m_ctx.getContextAsInt(m_curWindowNo, queryColumn)),
					infoName, infoDisplay);
			else
				query.addRestriction(queryColumn, Query.EQUAL,
					m_ctx.getContext(m_curWindowNo, queryColumn),
					infoName, infoDisplay);
		}

		new AReport (m_curTab.getAD_Table_ID(), aReport.getButton(), query);
	}	//	cmd_report


	/**
	 * 	Zoom Across Menu
	 */
	private void cmd_zoomAcross()
	{
		int record_ID = m_curTab.getRecord_ID();
		log.info("ID=" + record_ID);
		if (record_ID <= 0)
			return;

		//	Query
		Query query = new Query();
		//	Current row
		String link = m_curTab.getKeyColumnName();
		//	Link for detail records
		if (link.length() == 0)
			link = m_curTab.getLinkColumnName();
		if (link.length() != 0)
		{
			if (link.endsWith("_ID"))
				query.addRestriction(link, Query.EQUAL,
					Integer.valueOf(m_ctx.getContextAsInt(m_curWindowNo, link)));
			else
				query.addRestriction(link, Query.EQUAL,
					m_ctx.getContext( m_curWindowNo, link));
		}
		new AZoomAcross (aZoomAcross.getButton(),
			m_curTab.getTableName(), query, m_curTab.getAD_Window_ID());
	}	//	cmd_zoomAcross

	/**
	 * 	Open/View Request
	 */
	private void cmd_request()
	{
		int record_ID = m_curTab.getRecord_ID();
		log.info("ID=" + record_ID);
		if (record_ID <= 0)
			return;

		int AD_Table_ID = m_curTab.getAD_Table_ID();
		int C_BPartner_ID = 0;
		Object BPartner_ID = m_curTab.getValue("C_BPartner_ID");
		if (BPartner_ID != null)
			C_BPartner_ID = ((Integer)BPartner_ID).intValue();
		new ARequest (aRequest.getButton(), AD_Table_ID, record_ID,
			C_BPartner_ID, m_curTab);
	}	//	cmd_request

	/**
	 * 	Open/View Archive
	 */
	private void cmd_archive()
	{
		int record_ID = m_curTab.getRecord_ID();
		log.info("ID=" + record_ID);
		if (record_ID <= 0)
			return;

		int AD_Table_ID = m_curTab.getAD_Table_ID();
		new AArchive (aArchive.getButton(), AD_Table_ID, record_ID);
	}	//	cmd_archive

	/**
	 *	Print specific Report - or start default Report
	 */
	private void cmd_print()
	{
		//	Get process defined for this tab
		int AD_Process_ID = m_curTab.getAD_Process_ID();
		log.info("ID=" + AD_Process_ID);

		//	No report defined
		if (AD_Process_ID == 0)
		{
			cmd_report();
			return;
		}

		cmd_save(false);
		//
		int table_ID = m_curTab.getAD_Table_ID();
		int record_ID = m_curTab.getRecord_ID();
		ProcessInfo pi = new ProcessInfo (getTitle(), AD_Process_ID, table_ID, record_ID);
		pi.setAD_User_ID (m_ctx.getAD_User_ID());
		pi.setAD_Client_ID (m_ctx.getAD_Client_ID());

		ProcessCtl.process(this, m_curWindowNo, pi, null); //  calls lockUI, unlockUI
	}   //  cmd_print

	/**
	 *	Find - Set Query
	 */
	private void cmd_find()
	{
		if (m_curTab == null)
			return;
		cmd_save(false);
		//	Gets Fields from AD_Field_v
		GridField[] findFields = GridField.createFields(m_ctx, m_curWindowNo, 0, m_curTab.getAD_Tab_ID(), 0);
		Find find = new Find (Env.getFrame(this), m_curWindowNo,
			m_curTab.getName(), m_curTab.getAD_Tab_ID(),
			m_curTab.getAD_Table_ID(), m_curTab.getTableName(),
			m_curTab.getWhereClause(), findFields, 0);
		//	Simple/Advanced Query
		Query query = find.getQuery();
		//	History
		int onlyCurrentDays = find.getCurrentDays();
		boolean created = find.getIsCreated();
		find.dispose();
		find = null;

		//	Confirmed query
		if (query != null && query.isActive())
		{
			log.config(query.toString());
			m_curTab.setQuery(query);
			m_curGC.query(0, 0, created);   //  autoSize
		}
		else
		{
			int maxRows = MRole.getDefault().getMaxQueryRecords();
			log.config("OnlyCurrentDays=" + onlyCurrentDays
				+ ", MaxRows=" + maxRows);
			m_curTab.setQuery(null);	//	reset previous queries
			m_curGC.query(onlyCurrentDays, maxRows, created);   //  autoSize
		}
		boolean findPressed = m_curTab.isQueryActive() || m_curTab.getOnlyCurrentDays() > 0;
		aFind.setPressed(findPressed);
	}	//	cmd_find

	/**
	 *	Attachment
	 */
	private void cmd_attachment()
	{
		int record_ID = m_curTab.getRecord_ID();
		log.info("Record_ID=" + record_ID);
		if (record_ID == -1)	//	No Key
		{
			aAttachment.setEnabled(false);
			return;
		}

	//	Attachment va =
		new Attachment (Env.getFrame(this), m_curWindowNo,
			m_curTab.getAD_AttachmentID(), m_curTab.getAD_Table_ID(), record_ID, null);
		//
		m_curTab.loadAttachments();				//	reload
		aAttachment.setPressed(m_curTab.hasAttachment());
	}	//	attachment

	/**
	 *	Chat
	 */
	private void cmd_chat()
	{
		int record_ID = m_curTab.getRecord_ID();
		log.info("Record_ID=" + record_ID);
		if (record_ID == -1)	//	No Key
		{
			aChat.setEnabled(false);
			return;
		}
		//	Find display
		String infoName = null;
		String infoDisplay = null;
		for (int i = 0; i < m_curTab.getFieldCount(); i++)
		{
			GridField field = m_curTab.getField(i);
			if (field.isKey())
				infoName = field.getHeader();
			if ((field.getColumnName().equals("Name") || field.getColumnName().equals("DocumentNo") )
				&& field.getValue() != null)
				infoDisplay = field.getValue().toString();
			if (infoName != null && infoDisplay != null)
				break;
		}

		String description = infoName;
		String nullCheck="";
		if(infoDisplay!=null && !infoDisplay.equalsIgnoreCase(nullCheck))
		description = description+": " + infoDisplay;

		//
	//	AChat va =
		new AChat (Env.getFrame(this), m_curWindowNo,
			m_curTab.getCM_ChatID(), m_curTab.getAD_Table_ID(), record_ID,
			description, null);
		//
		m_curTab.loadChats();				//	reload
		aChat.setPressed(m_curTab.hasChat());
	}	//	chat

	/**
	 *	Lock
	 */
	private void cmd_lock()
	{
		log.info("Modifiers=" + m_lastModifiers);
		if (!m_isPersonalLock)
			return;
		int record_ID = m_curTab.getRecord_ID();
		if (record_ID == -1)	//	No Key
			return;
		//	Control Pressed
		if ((m_lastModifiers & InputEvent.CTRL_MASK) != 0)
		{
			new RecordAccessDialog(Env.getFrame(this), m_curTab.getAD_Table_ID(), record_ID);
		}
		else
		{
			m_curTab.lock (Env.getCtx(), record_ID, aLock.getButton().isSelected());
			m_curTab.loadAttachments();			//	reload
		}
		aLock.setPressed(m_curTab.isLocked());
	}	//	lock


	/**
	 *	Help
	 */
	private void cmd_help()
	{
		log.info("");
		Help hlp = new Help (Env.getFrame(this), this.getTitle(),
				m_mWorkbench.getMWindow(getWindowIndex()));
		hlp.setVisible(true);
	}	//	cmd_help

	/**
	 *  Close this screen - after save
	 *  @param exit ask if user wants to exit application
	 */
	private void cmd_end (boolean exit)
	{
		boolean exitSystem = false;
		if (!cmd_save(false))
			return;
		if (exit && ADialog.ask(m_curWindowNo, this, "ExitApplication?"))
			exitSystem = true;

		Env.getFrame(this).dispose();		//	calls this dispose

		if (exitSystem)
			AEnv.exit(0);
	}   //  cmd_end

	/**
	 * 	User Defined Window Customization
	 */
	private void cmd_userDef()
	{
		int AD_Window_ID = m_curTab.getAD_Window_ID();
		AUserDefDialog ud = new AUserDefDialog(Env.getFrame(this), AD_Window_ID, m_curWindowNo);
		int AD_Tab_ID = m_curTab.getAD_Tab_ID();
		Dimension size = getSize();
		ud.save(AD_Tab_ID, m_curGC.getTable(), size);
	}	//	cmdWinSize


	/**************************************************************************
	 *	Start Button Process
	 *  @param vButton button
	 */
	private void actionButton (VButton vButton)
	{
		log.info(vButton.toString());

		boolean startWOasking = false;
		boolean batch = false;
		String columnName = vButton.getColumnName();

		//  Zoom Button
		if (columnName.equals("Record_ID"))
		{
			int AD_Table_ID = m_ctx.getContextAsInt(m_curWindowNo, "AD_Table_ID");
			int Record_ID = m_ctx.getContextAsInt(m_curWindowNo, "Record_ID");
			AEnv.zoom(AD_Table_ID, Record_ID);
			return;
		}   //  Zoom

		//  save first	---------------
		if (m_curTab.needSave(true, false))
			if (!cmd_save(true))
				return;
		//
		int table_ID = m_curTab.getAD_Table_ID();
		//	Record_ID
		int record_ID = m_curTab.getRecord_ID();
		//	Record_ID - Language Handling
		if (record_ID == -1 && m_curTab.getKeyColumnName().equals("AD_Language"))
			record_ID = m_ctx.getContextAsInt(m_curWindowNo, "AD_Language_ID");
		//	Record_ID - Change Log ID
		if (record_ID == -1
			&& (vButton.getProcess_ID() == 306 || vButton.getProcess_ID() == 307))
		{
			Integer id = (Integer)m_curTab.getValue("AD_ChangeLog_ID");
			record_ID = id.intValue();
		}
		//	Record_ID - EntityType
		if (record_ID == -1 && m_curTab.getKeyColumnName().equals("EntityType"))
		{
			Integer id = (Integer)m_curTab.getValue("AD_EntityType_ID");
			record_ID = id.intValue();
		}
		//	Ensure it's saved
		if (record_ID == -1 && m_curTab.getKeyColumnName().endsWith("_ID"))
		{
			ADialog.error(m_curWindowNo, this, "SaveErrorRowNotFound");
			return;
		}

		//	Pop up Payment Rules
		if (columnName.equals("PaymentRule"))
		{
			VPayment vp = new VPayment(m_curWindowNo, m_curTab, vButton);
			if (vp.isInitOK())		//	may not be allowed
				vp.setVisible(true);
			vp.dispose();
			if (vp.needSave())
			{
				cmd_save(false);
				cmd_refresh();
			}
		}	//	PaymentRule

		//	Pop up Document Action (Workflow)
		else if (columnName.equals("DocAction"))
		{
			VDocAction vda = new VDocAction(m_curWindowNo, m_curTab, vButton, record_ID);
			//	Something to select from?
			if (vda.getNumberOfOptions() == 0)
			{
				vda.dispose ();
				log.info("DocAction - No Options");
				return;
			}
			else
			{
				vda.setVisible(true);
				if (!vda.isStartProcess())
					return;
				batch = vda.isBatch();
				startWOasking = true;
				vda.dispose();
			}
		}	//	DocAction

		//  Pop up Create From
		else if (columnName.equals("CreateFrom"))
		{
			//  m_curWindowNo
			VCreateFrom vcf = VCreateFrom.create (m_curTab);
			if (vcf != null)
			{
				if (vcf.isInitOK())
				{
					vcf.setVisible(true);
					vcf.dispose();
					m_curTab.dataRefresh();
				}
				else
					vcf.dispose();
				return;
			}
			//	else may start process
		}	//	CreateFrom

		//  Posting -----
		else if (columnName.equals("Posted") && MRole.getDefault().isShowAcct())
		{
			//  Check Doc Status
			String processed = m_ctx.getContext( m_curWindowNo, "Processed");
			if (!processed.equals("Y"))
			{
				String docStatus = m_ctx.getContext( m_curWindowNo, "DocStatus");
				if (DocActionConstants.STATUS_Completed.equals(docStatus)
					|| DocActionConstants.STATUS_Closed.equals(docStatus)
					|| DocActionConstants.STATUS_Reversed.equals(docStatus)
					|| DocActionConstants.STATUS_Voided.equals(docStatus))
					;
				else
				{
					ADialog.error(m_curWindowNo, this, "PostDocNotComplete");
					return;
				}
			}

			//  Check Post Status
			Object ps = m_curTab.getValue("Posted");
			if (ps != null && ps.equals("Y"))
			{
				new org.compiere.acct.AcctViewer (m_ctx.getContextAsInt(m_curWindowNo, "AD_Client_ID"),
					m_curTab.getAD_Table_ID(), m_curTab.getRecord_ID());
			}
			else
			{
				if (ADialog.ask(m_curWindowNo, this, "PostImmediate?"))
				{
					boolean force = ps != null && !ps.equals ("N");		//	force when problems
					String error = AEnv.postImmediate ( Env.getCtx(), m_curWindowNo, m_ctx.getAD_Client_ID(),
						m_curTab.getAD_Table_ID(), m_curTab.getRecord_ID(), force);
					m_curTab.dataRefresh();
					if (error != null)
						ADialog.error(m_curWindowNo, this, "PostingError-N", error);
				}
			}
			return;
		}   //  Posted

		//	Send Email -----
		else if (columnName.equals("SendNewEMail"))
		{
			int AD_Process_ID = vButton.getProcess_ID();
			if (AD_Process_ID != 0)
			{
			}
			//	Mail Defaults
			String title = getTitle();
			String to = null;
			Object oo = m_curTab.getValue("AD_User_ID");
			if (oo instanceof Integer)
			{
				MUser user = new MUser(Env.getCtx (), ((Integer)oo).intValue (), null);
				to = user.getEMail();
			}
			if (to == null)
				to = (String)m_curTab.getValue("EMail");
			String subject = (String)m_curTab.getValue("Name");;
			String message = "";
			new EMailDialog (Env.getFrame(this), title,
				MUser.get(Env.getCtx()),
				to,	subject, message,
				null);
			return;
		}

		/**
		 *  Start Process ----
		 */

		log.config("Process_ID=" + vButton.getProcess_ID() + ", Record_ID=" + record_ID);
		if (vButton.getProcess_ID() == 0)
			return;
		//	Save item changed
		if (m_curTab.needSave(true, false))
			if (!cmd_save(true))
				return;

		//	Ask user to start process, if Description and Help is not empty
		if (!startWOasking && !(vButton.getDescription().equals("") && vButton.getHelp().equals("")))
			if (!ADialog.ask(m_curWindowNo, this, "StartProcess?",
				//	"<b><i>" + vButton.getText() + "</i></b><br>" +
				vButton.getDescription() + "\n" + vButton.getHelp()))
				return;
		//
		String title = vButton.getDescription();
		if (title == null || title.length() == 0)
			title = vButton.getName();
		ProcessInfo pi = new ProcessInfo (title, vButton.getProcess_ID(), table_ID, record_ID);
		pi.setAD_User_ID (m_ctx.getAD_User_ID());
		pi.setAD_Client_ID (m_ctx.getAD_Client_ID());
		pi.setIsBatch(batch);

	//	Trx p_trx = Trx.get(Trx.createTrxName("AppsPanel"), true);
		ProcessCtl.process(this, m_curWindowNo, pi, null); //  calls lockUI, unlockUI
	}	//	actionButton


	/**************************************************************************
	 *  Lock User Interface.
	 *  Called from the Worker before processing
	 *  @param pi process info
	 */
	public void lockUI (ProcessInfo pi)
	{
	//	log.fine("" + pi);
		setBusy(true, false);
	}   //  lockUI

	/**
	 *  Unlock User Interface.
	 *  Called from the Worker when processing is done
	 *  @param pi of execute ASync call
	 */
	public void unlockUI (ProcessInfo pi)
	{
	//	log.fine("" + pi);
		boolean notPrint = pi != null
			&& pi.getAD_Process_ID() != m_curTab.getAD_Process_ID();
		//
		setBusy(false, notPrint);
		//  Process Result
		if (notPrint)		//	refresh if not print
		{
			//	Refresh data
			m_curTab.dataRefresh();
			//	Timeout
			if (pi.isTimeout())		//	set temporarily to R/O
				m_ctx.setContext(m_curWindowNo, "Processed", "Y");
			m_curGC.dynamicDisplay(-1);
			//	Update Status Line
			setStatusLine(pi.getSummary(), pi.isError());
			//	Get Log Info
			ProcessInfoUtil.setLogFromDB(pi);
			String logInfo = pi.getLogInfo();
			if (logInfo.length() > 0)
				ADialog.info(m_curWindowNo, this, Env.getHeader(m_ctx, m_curWindowNo),
					pi.getTitle(), logInfo);	//	 clear text
		}
	}   //  unlockUI

	/**
	 *  Is the UI locked (Internal method)
	 *  @return true, if UI is locked
	 */
	public boolean isUILocked()
	{
		return m_isLocked;
	}   //  isLoacked

	/**
	 *  Method to be executed async.
	 *  Called from the ASyncProcess worker
	 *  @param pi process info
	 */
	public void executeASync (ProcessInfo pi)
	{
		log.config("-");
	}   //  executeASync

	/**
	 * 	Get Current Tab
	 *	@return current tab
	 */
	protected GridTab getCurrentTab()
	{
		return m_curTab;
	}	//	getCurrentTab

	/**
	 *  String representation
	 *  @return String representation
	 */
	@Override
	public String toString()
	{
		String s = "APanel[curWindowNo=" + m_curWindowNo;
		if (m_mWorkbench != null)
			s += ",WB=" + m_mWorkbench.toString();
		s += "]";
		return s;
	}   //  toString

}	//	APanel
