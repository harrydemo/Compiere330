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
package org.compiere.report.core;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

/**
 *  Cell Editor for Report Result Table
 *
 *  @author Jorg Janke
 *  @version  $Id: ResultTableCellEditor.java,v 1.2 2006/07/30 00:51:06 jjanke Exp $
 */
public class ResultTableCellEditor extends AbstractCellEditor implements TableCellEditor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *  Constructor (read only)
	 */
	public ResultTableCellEditor()
	{
	}   //  ResultTableCellEditor

	/**
	 *  Constructor
	 */
	public ResultTableCellEditor(RColumn rc)
	{
		m_rc = rc;
	}   //  ResultTableCellEditor

	/** Report Column           */
	private RColumn m_rc = null;

	/**
	 *  Return Editor
	 */
	public Component getTableCellEditorComponent (JTable table, Object value,
		boolean isSelected, int row, int col)
	{
		if (m_rc == null)
			return null;
		return null;
	}   //  getTableCellEditorComponent

	/**
	 *  Get Value
	 */
	public Object getCellEditorValue()
	{
		if (m_rc == null)
			return null;
		return null;
	}   //  getCellEditorValue

	/**
	 *  Is Cell editable
	 */
	@Override
	public boolean isCellEditable(EventObject anEvent)
	{
		if (m_rc == null)
			return false;
		return !m_rc.isReadOnly();
	}   //  isCellEditable

	/**
	 *  Should Cell be selected
	 */
	@Override
	public boolean shouldSelectCell(EventObject anEvent)
	{
		if (m_rc == null)
			return false;
		return !m_rc.isReadOnly();
	}   //  shouldSelectCell

}   //  ResultTableCellEditor
