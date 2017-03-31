package com.edu.thelam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.edu.thelam.connect.DBUtils;

/**
 * Servlet implementation class JSONReturn
 */
@WebServlet("/JSONReturn")
public class JSONReturn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JSONReturn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		Connection con = null;
		try {
			con = DBUtils.connection();
			String sql = "SELECT * FROM Post";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			ResultSetMetaData metadata = rs.getMetaData();
			int columnCount = metadata.getColumnCount();
			JSONObject obj = new JSONObject();
			/*
			 * for (int i = 1; i <= columnCount; i++) {
			 * out.print(metadata.getColumnName(i) + "\t"); }
			 */
			Map<Object, Object> rows = new HashMap<>();
			while (rs.next()) {

				for (int i = 1; i <= columnCount; i++) {
					rows.put(metadata.getColumnName(i), rs.getString(i));
				}
				obj.putAll(rows);
				out.print(obj);
			}
			
		} catch (Exception e) {
			System.out.println("Error! " + e);
			out.close();
			System.exit(0);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
