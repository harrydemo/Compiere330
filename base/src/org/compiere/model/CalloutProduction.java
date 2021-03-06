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

import org.compiere.common.constants.*;
import org.compiere.util.*;

/**
 *	Production Callouts
 *
 *  @author Jorg Janke
 *  @version $Id: CalloutProduction.java,v 1.2 2006/07/30 00:51:05 jjanke Exp $
 */
public class CalloutProduction extends CalloutEngine
{
	/**
	 *  Product modified
	 * 		Set Attribute Set Instance
	 *
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 *  @return Error message or ""
	 */
	public String product (Ctx ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		Integer M_Product_ID = (Integer)value;
		if (M_Product_ID == null || M_Product_ID.intValue() == 0)
			return "";
		//	Set Attribute
		if (ctx.getContextAsInt( EnvConstants.WINDOW_INFO, EnvConstants.TAB_INFO, "M_Product_ID") == M_Product_ID.intValue()
			&& ctx.getContextAsInt( EnvConstants.WINDOW_INFO, EnvConstants.TAB_INFO, "M_AttributeSetInstance_ID") != 0)
			mTab.setValue("M_AttributeSetInstance_ID", Integer.valueOf(ctx.getContextAsInt( EnvConstants.WINDOW_INFO, EnvConstants.TAB_INFO, "M_AttributeSetInstance_ID")));
		else
			mTab.setValue("M_AttributeSetInstance_ID", null);
		return "";
	}   //  product

}	//	CalloutProduction
