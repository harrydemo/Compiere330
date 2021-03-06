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
package org.compiere.web;

import java.io.*;
import java.sql.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;

import org.compiere.db.*;
import org.compiere.interfaces.*;
import org.compiere.startup.*;

/**
 *	Status Info Servlet
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: StatusInfo.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 */
public class StatusInfo extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static final private String CONTENT_TYPE = "text/html";
	/**
	 *	Initialize global variables
	 *	@throws ServletException
	 */
	@Override
	public void init() throws ServletException
	{
		getServletContext().log("StatusInfo.init");
	}

	/**
	 * 	Get
	 *	@param request
	 *	@param response
	 *	@throws ServletException
	 *	@throws IOException
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Status Info</title></head>");
		out.println("<body>");

		InitialContext context = null;
		try
		{
			context = new InitialContext();
		}
		catch (Exception ex)
		{
			out.println("<p><b>" + ex + "</b></p>");
		}

		CConnection cc = CConnection.get();
		try
		{
			StatusHome statusHome = null;
			if (cc.getAppsType().equals(Environment.APPSTYPE_JBOSS))
				statusHome = (StatusHome)context.lookup(StatusHome.JNDI_NAME);
			else  // WAS
			{
				Object statusHomeObj = context.lookup("ejb/" + StatusHome.JNDI_NAME);
				statusHome = (StatusHome)javax.rmi.PortableRemoteObject.narrow( 
						statusHomeObj, StatusHome.class);
			}
			Status status = statusHome.create();
			out.println("<p>" + status.getStatus() + "</p>");
			status.remove();
		}
		catch (Exception ex)
		{
			out.println("<p><b>" + ex + "</b></p>");
		}

		try
		{
			ServerHome serverHome = null;
			if (cc.getAppsType().equals(Environment.APPSTYPE_JBOSS))
				serverHome = (ServerHome)context.lookup (ServerHome.JNDI_NAME);
			else  // WAS
			{
				Object serverHomeObj = context.lookup ("ejb/" + ServerHome.JNDI_NAME);
				serverHome = (ServerHome)javax.rmi.PortableRemoteObject.narrow( 
						serverHomeObj, ServerHome.class);
			}
			Server server = serverHome.create();
			out.println("<p>" + server.getStatus() + "</p>");
			server.remove();
		}
		catch (Exception ex)
		{
			out.println("<p><b>" + ex + "</b></p>");
		}

		NamingEnumeration<NameClassPair> ne = null;
		try
		{
			String rootContext = "/";
			if (cc.getAppsType().equals(Environment.APPSTYPE_IBM))
			{
				String nameSpace = context.getNameInNamespace();
				rootContext = "cell" + nameSpace.substring(nameSpace.indexOf('/'));
			}
			out.println("<h2>-- " + rootContext + "/</h2>");
			ne = context.list(rootContext);
			while (ne.hasMore())
				out.println("<br>   " + ne.nextElement());
		}
		catch (Exception e)
		{
			out.println("<p><b>" + e + "</b></p>");
		}
		try
		{
			out.println("<h2>-- java</h2>");
			ne = context.list("java:");
			while (ne.hasMore())
				out.println("<br>   " + ne.nextElement());
		}
		catch (Exception e)
		{
			out.println("<p><b>" + e + "</b></p>");
		}
		try
		{
			out.println("<h2>-- ejb</h2>");
			ne = context.list("ejb");
			while (ne.hasMore())
				out.println("<br>   " + ne.nextElement());
		}
		catch (Exception e)
		{
			out.println("<p><b>" + e + "</b></p>");
		}
			//
		try
		{
			out.println("<h2>-- DS</h2>");
			DataSource ds = (DataSource)context.lookup("java:/OracleDS");
			out.println("<br>  DataSource " + ds.getClass().getName() + " LoginTimeout=" + ds.getLoginTimeout());

			Connection con = ds.getConnection("compiere","compiere");
			out.println("<br>  Connection ");

			getServletContext().log("Connection closed=" + con.isClosed());
			DatabaseMetaData dbmd = con.getMetaData();
			getServletContext().log("DB " + dbmd.getDatabaseProductName());
			getServletContext().log("DB V " + dbmd.getDatabaseProductVersion());
			getServletContext().log("Driver " + dbmd.getDriverName());
			getServletContext().log("Driver V " + dbmd.getDriverVersion());
			getServletContext().log("JDBC " + dbmd.getJDBCMajorVersion());
			getServletContext().log("JDBC mV " + dbmd.getJDBCMinorVersion());

			getServletContext().log("User " + dbmd.getUserName());

			getServletContext().log("ANSI 92 " + dbmd.supportsANSI92FullSQL());
			getServletContext().log("Connection Alter Table ADD" + dbmd.supportsAlterTableWithAddColumn());
			getServletContext().log("Connection Alter Table DROP " + dbmd.supportsAlterTableWithDropColumn());
			getServletContext().log("Connection DDL&DML " + dbmd.supportsDataDefinitionAndDataManipulationTransactions());
			getServletContext().log("Connection CatalogsIn DML " + dbmd.supportsCatalogsInDataManipulation());
			getServletContext().log("Connection Schema In DML " + dbmd.supportsSchemasInDataManipulation());
		}
		catch (Exception e)
		{
			out.println("<p><b>" + e + "</b></p>");
		}


		out.println("</body></html>");
	}
	
	/**
	 * 	Put - Processes Get
	 *	@param request
	 *	@param response
	 *	@throws ServletException
	 *	@throws IOException
	 */
	@Override
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet (request, response);
	}
	/**
	 * 	Destroy
	 */
	@Override
	public void destroy()
	{
		getServletContext().log("StatusInfo.destroy");
	}
}
