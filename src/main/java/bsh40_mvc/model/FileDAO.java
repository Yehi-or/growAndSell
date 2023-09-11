package bsh40_mvc.model;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.*;

public class FileDAO {
	private PreparedStatement pstmt = null;
	private Connection con = null;
	
	Context init = null;
	DataSource ds = null;
	ResultSet rs = null;
	
//	public FileDAO() {
//		super();
//	}
	
	public void dbConnect() {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc_mariadb");
			con = ds.getConnection();
			
			System.out.println("DB연결 성공!!!!");
		}catch(Exception e) {
			System.out.println("DB연결 실패!!");
			e.printStackTrace();
		}
	}
	
	public void disConnect() {	
		if(pstmt != null) {                                                                                                                                                                                                             
			try {
				pstmt.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(rs != null) {
			try {
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void upload(String pdt_code, String fileName, String fileRealName, long length, String extension) {
		dbConnect();
		String SQL = "insert into productimage values (?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, pdt_code);
			pstmt.setString(2, fileName);
			pstmt.setString(3, fileRealName);
			pstmt.setLong(4, length);
			pstmt.setString(5, extension);
			pstmt.executeQuery();
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
}
