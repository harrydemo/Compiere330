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
package org.compiere.model;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import org.compiere.util.*;

/**
 *	AP Payment Selection
 *	
 *  @author Jorg Janke
 *  @version $Id: MPaySelection.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MPaySelection extends X_C_PaySelection
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param C_PaySelection_ID id
	 *	@param trx transaction
	 */
	public MPaySelection (Ctx ctx, int C_PaySelection_ID, Trx trx)
	{
		super(ctx, C_PaySelection_ID, trx);
		if (C_PaySelection_ID == 0)
		{
		//	setC_BankAccount_ID (0);
		//	setName (null);	// @#Date@
		//	setPayDate (new Timestamp(System.currentTimeMillis()));	// @#Date@
			setTotalAmt (Env.ZERO);
			setIsApproved (false);
			setProcessed (false);
			setProcessing (false);
		}
	}	//	MPaySelection

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trx transaction
	 */
	public MPaySelection(Ctx ctx, ResultSet rs, Trx trx)
	{
		super(ctx, rs, trx);
	}	//	MPaySelection

	/**	Lines						*/
	private MPaySelectionLine[]	m_lines = null;
	/**	Currency of Bank Account	*/
	private int					m_C_Currency_ID = 0;
	
	/**
	 * 	Get Lines
	 *	@param requery requery
	 *	@return lines
	 */
	public MPaySelectionLine[] getLines(boolean requery)
	{
		if (m_lines != null && !requery)
			return m_lines;
		ArrayList<MPaySelectionLine> list = new ArrayList<MPaySelectionLine>();
		String sql = "SELECT * FROM C_PaySelectionLine WHERE C_PaySelection_ID=? ORDER BY Line";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_Trx());
			pstmt.setInt (1, getC_PaySelection_ID());
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MPaySelectionLine(getCtx(), rs, get_Trx()));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "getLines", e); 
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		//
		m_lines = new MPaySelectionLine[list.size ()];
		list.toArray (m_lines);
		return m_lines;
	}	//	getLines
	
	/**
	 * 	Get Currency of Bank Account
	 *	@return C_Currency_ID
	 */
	public int getC_Currency_ID()
	{
		if (m_C_Currency_ID == 0)
		{
			String sql = "SELECT C_Currency_ID FROM C_BankAccount " 
				+ "WHERE C_BankAccount_ID=?";
			m_C_Currency_ID = DB.getSQLValue(null, sql, getC_BankAccount_ID());
		}
		return m_C_Currency_ID;
	}	//	getC_Currency_ID
	
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MPaySelection[");
		sb.append(get_ID()).append(",").append(getName())
			.append("]");
		return sb.toString();
	}	//	toString

	
	
}	//	MPaySelection
