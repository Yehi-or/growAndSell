package bsh40_mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PaymentDAO {
	private PreparedStatement ps = null;
	private Connection conn = null;
	
	Context init = null;
	DataSource ds = null;
	ResultSet rs = null;
	
	public void dbConnect() {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc_mariadb");
			conn = ds.getConnection();
			
			System.out.println("DB연결 성공!!!!");
		}catch(Exception e) {
			System.out.println("DB연결 실패!!");
			e.printStackTrace();
		}
	}
	
	public void disConnect() {	
		if(ps != null) {                                                                                                                                                                                                             
			try {
				ps.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
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
	public ArrayList<PaymentDTO> selectMyPayment(String pay_user_id, PageInfoVO pinfo) {
		ArrayList<PaymentDTO> list = new ArrayList<>();
		dbConnect();
		String sql = "select * from product_payment where pay_user_id = ? limit ?, ?";
		String sql2 = "select count(*) from product_payment where pay_user_id = ?";
		
		try {
			ps = conn.prepareStatement(sql2);
			ps.setString(1, pay_user_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				pinfo.setRecordCnt(rs.getInt(1));
			}
			System.out.println(pinfo.getRecordCnt());
		}catch(SQLException e) {
			e.printStackTrace();
		}
		pinfo.adjPageInfo();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pay_user_id);
			ps.setInt(2, pinfo.getStartRecord());
			ps.setInt(3, pinfo.getLimitCnt());
			rs = ps.executeQuery();
			while(rs.next()) {
				PaymentDTO pdto = new PaymentDTO();
				pdto.setPdt_cnt_req(rs.getInt("pdt_cnt_req"));
				pdto.setPdt_total_price(rs.getInt("pdt_total_price"));
				pdto.setPay_pdt_code(rs.getString("pay_pdt_code"));
				pdto.setPay_addr(rs.getString("pay_addr"));
				pdto.setPay_recipient(rs.getString("pay_recipient"));
				pdto.setPay_date(rs.getString("pay_date").substring(0, 10));
				pdto.setPay_price(rs.getInt("pay_price"));
				pdto.setPay_method(rs.getString("pay_method"));
				list.add(pdto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return list;
	}
	public ArrayList<PaymentDTO> selectAllPayment(PageInfoVO pinfo) {
		dbConnect();
		ArrayList<PaymentDTO> list = new ArrayList<>();
		String sql = "select * from product_payment limit ?, ?";
		String sql2 = "select count(*) from product_payment";
		
		try {
			ps = conn.prepareStatement(sql2);
			rs = ps.executeQuery();
			while(rs.next()) {
				pinfo.setRecordCnt(rs.getInt(1));
			}
			System.out.println(pinfo.getRecordCnt());
		}catch(SQLException e) {
			e.printStackTrace();
		}
		pinfo.adjPageInfo();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pinfo.getStartRecord());
			ps.setInt(2, pinfo.getLimitCnt());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PaymentDTO pdto = new PaymentDTO();
				pdto.setPay_request(rs.getString("pay_request"));
				pdto.setPay_user_id(rs.getString("pay_user_id"));
				pdto.setPay_pdt_code(rs.getString("pay_pdt_code"));
				pdto.setPay_addr(rs.getString("pay_addr"));
				pdto.setPay_recipient(rs.getString("pay_recipient"));
				pdto.setPay_date(rs.getString("pay_date").substring(0, 10));
				pdto.setPay_price(rs.getInt("pay_price"));
				pdto.setPay_method(rs.getString("pay_method"));
				list.add(pdto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return list;
	}
	
}
