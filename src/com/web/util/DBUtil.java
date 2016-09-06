package com.web.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBUtil {

	private static String DRIVERCLASS;
	
	private static String URL;
	
	private static String USER;
	
	private static String PASSWORD;
	
	/**
	 * 静态初始化块
	 */
	static{
		Properties p = new Properties();
		InputStream in = null;
		try {
			in = DBUtil.class.getResourceAsStream("/com/web/util/db.properties");
			p.load(in);
			DRIVERCLASS = p.getProperty("driverclass");
			URL = p.getProperty("url");
			USER = p.getProperty("user");
			PASSWORD = p.getProperty("password");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		
		Connection conn = null;
		try {
			Class.forName(DRIVERCLASS);
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 通用的DML执行方法，支持预处理
	 * 注意：当SQL语句中带有问号时，则必须传入相同个数的值组成的数组
	 * @param sql
	 * @param params
	 * @return 执行成功返回true
	 */
	public static boolean executeDML(String sql,Object[] params){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		boolean b = false;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			
			//SQL语句中带有问号，需要设置参数
			if(null != params && params.length>0){
				for(int i=0;i<params.length;i++){
					ps.setObject((i+1), params[i]);
				}
			}
			b = ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(ps != null)ps.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return b;
	}
	
	
	public static List<Object[]> executeQuery(String sql,Object[] params){
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Object[]> list = new ArrayList<Object[]>();
		try {
			
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			//SQL语句中带有问号，需要设置参数
			if(null != params && params.length>0){
				for(int i=0;i<params.length;i++){
					ps.setObject((i+1), params[i]);
				}
			}
			rs = ps.executeQuery();
			int columnCount = rs.getMetaData().getColumnCount();
			Object[] os = null;
			while(rs.next()){
				os = new Object[columnCount];
				for(int i=0;i<columnCount;i++){
					os[i] =rs.getObject(i+1);
				}
				list.add(os);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs != null)rs.close();
				if(ps != null)ps.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//私有构造方法 防止创建对象
	private DBUtil(){}
		
}
