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
package org.compiere.print;

import java.math.*;

//import java.util.logging.*;

/**
 *	Print Data Column.
 * 	Optional Meta Data of Columns
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: PrintDataColumn.java,v 1.2 2006/07/30 00:53:02 jjanke Exp $
 */
public class PrintDataColumn
{
	/**
	 * 	Print Data Column
	 *
	 * 	@param AD_Column_ID Column
	 * 	@param columnName Column Name
	 * 	@param displayType Display Type
	 * 	@param columnSize Column Size
	 *  @param alias Alias in query or the same as column name or null
	 *  @param isPageBreak if true force page break after function
	 */
	public PrintDataColumn (int AD_Column_ID, String columnName,
		int displayType, int columnSize,
		String alias, boolean isPageBreak)
	{
		m_AD_Column_ID = AD_Column_ID;
		m_columnName = columnName;
		//
		m_displayType = displayType;
		m_columnSize = columnSize;
		//
		m_alias = alias;
		if (m_alias == null)
			m_alias = columnName;
		m_pageBreak = isPageBreak;
		
		m_minFractionDigits = 0;		
	}	//	PrintDataColumn

	private int			m_AD_Column_ID;
	private String		m_columnName;
	private int			m_displayType;
	private int			m_columnSize;
	private String		m_alias;
	private boolean		m_pageBreak;
	private int         m_minFractionDigits;
	

	/*************************************************************************/

	/**
	 * 	Get AD_Column_ID
	 * 	@return AD_Column_ID
	 */
	public int getAD_Column_ID()
	{
		return m_AD_Column_ID;
	}	//	getAD_Column_ID

	/**
	 * 	Get Column Name
	 * 	@return column name
	 */
	public String getColumnName()
	{
		return m_columnName;
	}	//	getColumnName

	/**
	 * 	Get Display Type
	 * 	@return display type
	 */
	public int getDisplayType()
	{
		return m_displayType;
	}	//	getDisplayType

	/**
	 * 	Get Alias Name
	 * 	@return alias column name
	 */
	public String getAlias()
	{
		return m_alias;
	}	//	getAlias

	/**
	 *	Column has Alias.
	 *  (i.e. has a key)
	 * 	@return true if Alias
	 */
	public boolean hasAlias()
	{
		return !m_columnName.equals(m_alias);
	}	//	hasAlias

	/**
	 * 	Column value forces page break
	 * 	@return true if page break
	 */
	public boolean isPageBreak()
	{
		return m_pageBreak;
	}	//	isPageBreak

	/**
	 *	Get minimum number of decimal digits to be displayed
	 * 	@return minimum number of decimal digits
	 */
	public int getMinFractionDigits()
	{
		return m_minFractionDigits;
	}

	/**
	 *	Set minimum number of decimal digits to be displayed to help align the decimal point
	 *  @param value value to be displayed
	 *  @param displayType display type
	 */
	public void setMinFractionDigits( Object value, int displayType )
	{
		if ( value instanceof BigDecimal )
		{
			int decimalDigits = Math.abs(((BigDecimal) value).scale());
			if ( decimalDigits > m_minFractionDigits )
				m_minFractionDigits = decimalDigits;
		}
	}
	
	/**
	 *	String Representation
	 * 	@return info
	 */
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer("PrintDataColumn[");
		sb.append("ID=").append(m_AD_Column_ID)
			.append("-").append(m_columnName);
		if (hasAlias())
			sb.append("(").append(m_alias).append(")");
		sb.append(",DisplayType=").append(m_displayType)
			.append(",Size=").append(m_columnSize)
			.append(",MinFractionDigits=").append(m_minFractionDigits)
			.append("]");
		return sb.toString();
	}	//	toString

}	//	PrintDataColumn
