package bsh40_mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ProductDAO {
	ProductDTO vo = new ProductDTO();
	
	private PreparedStatement ps = null;
	private Connection conn = null;
	
	Context init = null;
	DataSource ds = null;
	ResultSet rs = null;
	
	String sql1;
	String sql2;
	
//	public ProductDao() {
//		dbConnect();
//		super();
//    }
	
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
	
	Gson gson = new Gson();
	JsonObject obj = new JsonObject();
	
	public String checkId(String id) {
		dbConnect();
		String s = null;
		sql1 = "select user_id from user where user_id = ? ";
		try {
			ps = conn.prepareStatement(sql1);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				id = "success";
				obj.addProperty("result", id);
			}else {
				id = "fail";
				obj.addProperty("result", id);
			}
			s = gson.toJson(obj);
//			File file = new File();
//	        OutputStream outputStream = new FileOutputStream(file);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return s;
	}
	
	public String checkLogin(String id, String passwd, HttpServletRequest request) {
		dbConnect();
		String s = null;
		sql1 = "select user_id, user_passwd, user_check, user_name from user where user_id = ?";
		try {
			ps = conn.prepareStatement(sql1);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				String user_id = rs.getString("user_id");
				String user_passwd = rs.getString("user_passwd");
				String user_check = rs.getString("user_check");
				String user_name = rs.getString("user_name");
				if(id.equals(user_id)) {
					obj.addProperty("result", "ok");
					if(passwd.equals(user_passwd)) {
						obj.addProperty("passwd", "ok");
						HttpSession session = request.getSession();
						session.setAttribute("loginState", "login");
						session.setAttribute("userid", user_id);
						session.setAttribute("userclass", user_check);
						session.setAttribute("user_name", user_name);
						System.out.println("확인했습니다.");
					}else {
						obj.addProperty("passwd", "fail");
					}
				}
			}else {
				obj.addProperty("result", "ng");
			}
			s = gson.toJson(obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return s;
	}
	public List<ProductDTO> selectAllProducts(String description){
		//카테고리별 출력
		dbConnect();
		String sql = "select * from product where pdt_desc = ? ";
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, description);
			rs = ps.executeQuery();
			while(rs.next()) {
				ProductDTO vo = new ProductDTO();
				vo.setProduct_serial(rs.getInt("pdt_serial"));
				vo.setProduct_name(rs.getString("pdt_name"));
				vo.setProduct_price(rs.getInt("pdt_price"));
				vo.setProduct_picture_url(rs.getString("pdt_thumb"));
				vo.setProduct_description(rs.getString("pdt_desc"));
				vo.setProduct_good(rs.getInt("pdt_good"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return list;
	}
	
	//상품 인서트
	public int insertProduct(String product_code, String pdt_name, String pdt_title, String pdt_desc, String pdt_thumb, int pdt_price, String pdt_regi, int pdt_good, String pdt_cate, String pdt_sebu_cate, int pdt_cnt) {
		dbConnect();
		String sql = "insert into product(pdt_code, pdt_name, pdt_title, pdt_desc, pdt_thumb ,pdt_price, pdt_regi, pdt_good, pdt_cate, pdt_sebu_cate, pdt_cnt) ";
			   sql += "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, product_code);
			ps.setString(2, pdt_name);
			ps.setString(3, pdt_title);
			ps.setString(4, pdt_desc);
			ps.setString(5, pdt_thumb);
			ps.setInt(6, pdt_price);
			ps.setString(7, pdt_regi);
			ps.setInt(8, pdt_good);
			ps.setString(9, pdt_cate);
			ps.setString(10, pdt_sebu_cate);
			ps.setInt(11, pdt_cnt);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			disConnect();
		}
		return 1;
	}
	
	//상품조회(일단 페이징 없이)
	public ArrayList<ProductDTO> getList() {
		dbConnect();
		ArrayList<ProductDTO> list = new ArrayList<>();
		String sql = "select * from product";
			
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductDTO pv = new ProductDTO();
				pv.setProduct_name(rs.getString("pdt_name"));
				pv.setProduct_price(rs.getInt("pdt_price"));
				pv.setProduct_picture_url(rs.getString("pdt_thumb"));
				pv.setProduct_title(rs.getString("pdt_title"));
				list.add(pv);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return list;
	}
	
	public ArrayList<ProductDTO> getListForPage(PageInfoVO pinfo) {
		dbConnect();
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		String sql = "select * from product limit ?, ?";
		String sql2 = "select count(*) from product";
		
		try {
			ps = conn.prepareStatement(sql2);
			rs = ps.executeQuery();
			while(rs.next()) {
				pinfo.setRecordCnt(rs.getInt(1));
			}
			System.out.println(pinfo.getRecordCnt());
			pinfo.adjPageInfo();
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
				ProductDTO pv = new ProductDTO();
				pv.setProduct_category(rs.getString("pdt_cate"));
				pv.setProduct_regi(rs.getString("pdt_regi"));
				pv.setProduct_code(rs.getString("pdt_code"));
				pv.setProduct_serial(rs.getInt("pdt_serial"));
				pv.setProduct_name(rs.getString("pdt_name"));
				pv.setProduct_price(rs.getInt("pdt_price"));
				pv.setProduct_picture_url(rs.getString("pdt_thumb"));
				pv.setProduct_title(rs.getString("pdt_title"));
				pv.setProduct_good(rs.getInt("pdt_good"));
				list.add(pv);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return list;
	}
	
	public ArrayList<ProductDTO> getListForPageSelect(String type, PageInfoVO pinfo) {
		dbConnect();
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		String sql = "select * from product where pdt_cate = ? limit ?, ?";
		String sql2 = "select count(*) from product where pdt_cate = ?";
		try {
			ps = conn.prepareStatement(sql2);
			ps.setString(1, type);
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
			ps.setString(1, type);
			ps.setInt(2, pinfo.getStartRecord());
			ps.setInt(3, pinfo.getLimitCnt());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductDTO pv = new ProductDTO();
				pv.setProduct_serial(rs.getInt("pdt_serial"));
				pv.setProduct_name(rs.getString("pdt_name"));
				pv.setProduct_price(rs.getInt("pdt_price"));
				pv.setProduct_picture_url(rs.getString("pdt_thumb"));
				pv.setProduct_title(rs.getString("pdt_title"));
				pv.setProduct_good(rs.getInt("pdt_good"));
				list.add(pv);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return list;
	}
	public ArrayList<ProductDTO> getProductListForAllSearch(String searchValue, PageInfoVO pinfo) {
		dbConnect();
		System.out.println(searchValue);
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		String sql1 = "select * from product where pdt_name" + " LIKE '%" + searchValue + "%' or ";
		sql1 += "pdt_cate" + " LIKE '%" + searchValue + "%' or ";
		sql1 += "pdt_sebu_cate" + " LIKE '%" + searchValue + "%' limit ?, ? ";
		String sql2 = "select count(*) from product where pdt_name LIKE '%" + searchValue + "%' or ";
		sql2 += "pdt_cate LIKE '%" + searchValue + "%' or " + "pdt_sebu_cate = '%" + searchValue + "%'";
		
		try {
			ps = conn.prepareStatement(sql2);
			rs = ps.executeQuery();
			while(rs.next()) {
				pinfo.setRecordCnt(rs.getInt(1));
				System.out.println(pinfo.getRecordCnt());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		pinfo.adjPageInfo();
		
		try {
			ps = conn.prepareStatement(sql1);
			ps.setInt(1, pinfo.getStartRecord());
			ps.setInt(2, pinfo.getLimitCnt());
			rs = ps.executeQuery();
			while(rs.next()) {
				ProductDTO pv = new ProductDTO();
				pv.setProduct_serial(rs.getInt("pdt_serial"));
				pv.setProduct_name(rs.getString("pdt_name"));
				pv.setProduct_price(rs.getInt("pdt_price"));
				pv.setProduct_picture_url(rs.getString("pdt_thumb"));
				pv.setProduct_title(rs.getString("pdt_title"));
				pv.setProduct_good(rs.getInt("pdt_good"));
				list.add(pv);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return list;
	}
	
	public ArrayList<ProductDTO> getProductListForPageSearch(String select, String search, PageInfoVO pinfo) {
		dbConnect();
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		String SQL = "select * from product where " + select + " LIKE '%" + search + "%' limit ?, ?";
		String SQL2 = "select count(*) from product where " + select + " LIKE '%" + search +"%'";
		
		try {
			ps = conn.prepareStatement(SQL2);
			rs = ps.executeQuery();
			while(rs.next()) {
				pinfo.setRecordCnt(rs.getInt(1));
				System.out.println(pinfo.getRecordCnt());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		pinfo.adjPageInfo();
		
		try {
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, pinfo.getStartRecord());
			ps.setInt(2, pinfo.getLimitCnt());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductDTO pv = new ProductDTO();
				pv.setProduct_serial(rs.getInt("pdt_serial"));
				pv.setProduct_name(rs.getString("pdt_name"));
				pv.setProduct_price(rs.getInt("pdt_price"));
				pv.setProduct_picture_url(rs.getString("pdt_thumb"));
				pv.setProduct_title(rs.getString("pdt_title"));
				pv.setProduct_good(rs.getInt("pdt_good"));
				list.add(pv);
			}
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			disConnect();
		}
		return list;
	}
	
	public ArrayList<ProductDTO> productPage(int product_serial_num) {
		dbConnect();
		String sql = "select * from product where pdt_serial = ?";
		String sql2 = "select a.i_fileName from productimage as a inner join product as b on a.pdt_code = b.pdt_code where b.pdt_serial = ?";
		ArrayList<ProductDTO> list = new ArrayList<>();
		ArrayList<String> arrayList = new ArrayList<>();
		ProductDTO pv = new ProductDTO();
		
		try {
			ps = conn.prepareStatement(sql2);
			ps.setInt(1, product_serial_num);
			rs = ps.executeQuery();
			while(rs.next()) {
				arrayList.add(rs.getString("i_fileName"));
				pv.setProduct_image(arrayList);
				System.out.println(pv.getProduct_image());
				list.add(pv);
			}
		}catch(SQLException e){
			System.out.println("error");
			e.printStackTrace();
		}
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, product_serial_num);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				pv.setProduct_name(rs.getString("pdt_name"));
				pv.setProduct_description(rs.getString("pdt_desc"));
				pv.setProduct_code(rs.getString("pdt_code"));
				pv.setProduct_picture_url(rs.getString("pdt_thumb"));
				pv.setProduct_serial(rs.getInt("pdt_serial"));
				pv.setProduct_title(rs.getString("pdt_title"));
				pv.setProduct_price(rs.getInt("pdt_price"));
				pv.setProduct_good(rs.getInt("pdt_good"));
				pv.setProduct_cnt(rs.getInt("pdt_cnt"));
				list.add(pv);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return list;
	}
	public void bookmark(String user_id, int serialNumber, int pdt_cnt) {
		dbConnect();
		boolean c = false;
		String sql = "insert into basket(user_id, pdt_serial, pdt_cnt) values(?, ?, ?)";
		String sql2 = "select * from basket where user_id = ? and pdt_serial = ?";
		
		try {
			ps = conn.prepareStatement(sql2);
			ps.setString(1, user_id);
			ps.setInt(2, serialNumber);
			rs = ps.executeQuery();
			if(rs != null) {
				if(rs.next()) {
					c = true;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(c==false) {
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, user_id);
				ps.setInt(2, serialNumber);
				ps.setInt(3, pdt_cnt);
				ps.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				disConnect();
			}
		}
	}
	
	public int productCateMojong(String cate) {
		ProductDTO pv = new ProductDTO();
		dbConnect();
		String sql = "select mojong_code from mojongcate where mojong_name = ?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, cate);
			rs = ps.executeQuery();
			if(rs.next()) {
				pv.setProduct_sebu_cate(rs.getInt("mojong_code"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return pv.getProduct_sebu_cate();
	}
	
	public int productCateMomok(String cate) {
		ProductDTO pv = new ProductDTO();
		dbConnect();
		String sql = "select momok_code from momokcate where momok_name = ?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, cate);
			rs = ps.executeQuery();
			if(rs.next()) {
				pv.setProduct_sebu_cate(rs.getInt("momok_code"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return pv.getProduct_sebu_cate();
	}
	
	public int ItemCheck(String cate) {
		dbConnect();
		int max = 0;
		String sql = "select pdt_code from product where pdt_sebu_cate = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cate);
			rs = ps.executeQuery();
			while(rs.next()) {
				String s = rs.getString("pdt_code");
				int check = Integer.parseInt(s.substring(7));
				if(check > max) {
					max = check;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return max + 1;
	}
	
	public ArrayList<ProductDTO> getMyListForPageSelect(String user_id, PageInfoVO pinfo) {
		dbConnect();
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		String sql = "select a.pdt_serial, a.pdt_name, a.pdt_price, a.pdt_thumb, a.pdt_title, a.pdt_regi, a.pdt_good, a.pdt_code, a.pdt_cnt, b.pdt_cnt ";
			   sql += "from product as a inner join basket as b ";
			   sql += "on a.pdt_serial = b.pdt_serial where b.user_id = ? limit ?, ?";
		String sql2 = "select count(*) from basket where user_id = ?";
		try {
			ps = conn.prepareStatement(sql2);
			ps.setString(1, user_id);
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
			ps.setString(1, user_id);
			ps.setInt(2, pinfo.getStartRecord());
			ps.setInt(3, pinfo.getLimitCnt());
			rs = ps.executeQuery();
			while(rs.next()) {
				ProductDTO pv = new ProductDTO();
				pv.setProduct_code(rs.getString("pdt_code"));
				pv.setProduct_serial(rs.getInt("pdt_serial"));
				pv.setProduct_name(rs.getString("pdt_name"));
				pv.setProduct_price(rs.getInt("pdt_price"));
				pv.setProduct_picture_url(rs.getString("pdt_thumb"));
				pv.setProduct_title(rs.getString("pdt_title"));
				pv.setProduct_good(rs.getInt("pdt_good"));
				pv.setProduct_cnt(rs.getInt("b.pdt_cnt"));
				pv.setTotal_product_cnt(rs.getInt("a.pdt_cnt"));
				list.add(pv);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return list;
	}
	
	public ArrayList<ProductDTO> getMyProductPageSelect(String user_id, PageInfoVO pinfo) {
		dbConnect();
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		String sql = "select * from product where pdt_regi = ? limit ?, ?";
		String sql2 = "select count(*) from product where pdt_regi = ?";
		try {
			ps = conn.prepareStatement(sql2);
			ps.setString(1, user_id);
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
			ps.setString(1, user_id);
			ps.setInt(2, pinfo.getStartRecord());
			ps.setInt(3, pinfo.getLimitCnt());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductDTO pv = new ProductDTO();
				pv.setProduct_code(rs.getString("pdt_code"));
				pv.setProduct_serial(rs.getInt("pdt_serial"));
				pv.setProduct_name(rs.getString("pdt_name"));
				pv.setProduct_price(rs.getInt("pdt_price"));
				pv.setProduct_picture_url(rs.getString("pdt_thumb"));
				pv.setProduct_title(rs.getString("pdt_title"));
				pv.setProduct_good(rs.getInt("pdt_good"));
				list.add(pv);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return list;
	}
	
	public void updateGoodUp(int serial_num) {
		dbConnect();
		int good_num = 0;
		String sql = "update product set pdt_good = pdt_good + 1 where pdt_serial = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, serial_num);
			ps.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
	
	public ArrayList<ProductDTO> topProduct(String pdt_cate) {
		dbConnect();
		ArrayList<ProductDTO> list = new ArrayList<>(); 
		String sql = "select * from product where  pdt_cate = ? order by pdt_good desc limit 10";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pdt_cate);
			rs = ps.executeQuery();
			while(rs.next()) {
				ProductDTO pv = new ProductDTO();
				pv.setProduct_serial(rs.getInt("pdt_serial"));
				pv.setProduct_name(rs.getString("pdt_name"));
				pv.setProduct_price(rs.getInt("pdt_price"));
				pv.setProduct_picture_url(rs.getString("pdt_thumb"));
				pv.setProduct_title(rs.getString("pdt_title"));
				pv.setProduct_good(rs.getInt("pdt_good"));
				list.add(pv);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return list;
	}
	public ArrayList<ProductDTO> productInfo(String pdt_code) {
		dbConnect();
		ArrayList<ProductDTO> list = new ArrayList<>();
		ProductDTO pdto = new ProductDTO();
		String sql = "select * from product where pdt_code = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pdt_code);
			rs = ps.executeQuery();
			if(rs.next()) {
				pdto.setProduct_code(pdt_code);
				pdto.setProduct_link_url(rs.getString("pdt_thumb"));
				pdto.setProduct_category(rs.getString("pdt_cate"));
				pdto.setProduct_price(rs.getInt("pdt_price"));
				pdto.setProduct_name(rs.getString("pdt_name"));
				pdto.setProduct_description(rs.getString("pdt_desc"));
				pdto.setProduct_title(rs.getString("pdt_title"));
				list.add(pdto);
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				disConnect();
			}
			return list;
		}
	public void productUpdate(String pdt_code, String pdt_name, int pdt_price, String pdt_title, String pdt_desc, String pdt_sebu_cate) {
		dbConnect();
		String sql = "update product set pdt_name = ?, pdt_price = ?, pdt_title = ?, pdt_desc = ?, pdt_sebu_cate = ? where pdt_code = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pdt_name);
			ps.setInt(2, pdt_price);
			ps.setString(3, pdt_title);
			ps.setString(4, pdt_desc);
			ps.setString(5, pdt_sebu_cate);
			ps.setString(6, pdt_code);
			ps.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
	
	public void myProductDelete(String pdt_code) {
		dbConnect();
		String sql = "delete from product where pdt_code = ?";
		String sql2 = "delete from productimage where pdt_code = ?";
		try {
			ps = conn.prepareStatement(sql2);
			ps.setString(1, pdt_code);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pdt_code);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
//	public ArrayList<ProductDTO> productPaymentInfo(String user_id, String pdt_code) {
//		dbConnect();
//		ArrayList<ProductDTO> list = new ArrayList<>();
//		ProductDTO pdto = new ProductDTO();
//		String sql = "select * from product where pdt_code = ?";
//		String sql2 = "select user_addr, user_zipcode, user_addrDetail, user_name from user where user_id = ?";
//		try {
//			ps = conn.prepareStatement(sql2);
//			ps.setString(1, user_id);
//			rs = ps.executeQuery();
//			if(rs.next()) {
//				pdto.setProduct_user_recipient(rs.getString("user_name"));
//				pdto.setProduct_user_addr(rs.getString("user_addr"));
//				pdto.setProduct_user_addrDetail(rs.getString("user_addrDetail"));
//				pdto.setProduct_user_zipcode(rs.getString("user_zipcode"));
//				list.add(pdto);
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//		
//		try {
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, pdt_code);
//			rs = ps.executeQuery();
//			if(rs.next()) {
//				pdto.setProduct_regi(rs.getString("pdt_regi"));
//				pdto.setProduct_picture_url(rs.getString("pdt_thumb"));
//				pdto.setProduct_code(pdt_code);
//				pdto.setProduct_category(rs.getString("pdt_cate"));
//				pdto.setProduct_price(rs.getInt("pdt_price"));
//				pdto.setProduct_name(rs.getString("pdt_name"));
//				pdto.setProduct_description(rs.getString("pdt_desc"));
//				pdto.setProduct_title(rs.getString("pdt_title"));
//				list.add(pdto);
//			}
//			}catch(SQLException e) {
//				e.printStackTrace();
//			}finally {
//				disConnect();
//			}
//			return list;
//		}
	
	public void productPayment(String user_id, String addr, String method, String code, int price, String recipient, String requests, String pdt_regi, int pdt_cnt_req, int pdt_total_price) {
		dbConnect();
		String sql = "insert into product_payment (pay_user_id, pay_addr, pay_method, pay_pdt_code, pay_price, pay_recipient, pay_request, pay_pdt_regi, pdt_cnt_req, pdt_total_price) ";
		sql += "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			ps.setString(2, addr);
			ps.setString(3, method);
			ps.setString(4, code);
			ps.setInt(5, price);
			ps.setString(6, recipient);
			ps.setString(7, requests);
			ps.setString(8, pdt_regi);
			ps.setInt(9, pdt_cnt_req);
			ps.setInt(10, pdt_total_price);
			ps.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
	
	public ArrayList<ProductDTO> test(ArrayList<String> pdt_code, int pdt_cnt) {
		dbConnect();
		ArrayList<ProductDTO> list = new ArrayList<>();
		String sql = "select * from product where pdt_code = ?";
		try {
			for(int i=0; i<pdt_code.size(); i++) {
				ps = conn.prepareStatement(sql);
				ps.setString(1, pdt_code.get(i));
				rs = ps.executeQuery();
				if(rs.next()) {
					ProductDTO pdto = new ProductDTO();
					pdto.setProduct_regi(rs.getString("pdt_regi"));
					pdto.setProduct_picture_url(rs.getString("pdt_thumb"));
					pdto.setProduct_code(pdt_code.get(i));
					pdto.setProduct_category(rs.getString("pdt_cate"));
					pdto.setProduct_price(rs.getInt("pdt_price"));
					pdto.setProduct_name(rs.getString("pdt_name"));
					pdto.setProduct_description(rs.getString("pdt_desc"));
					pdto.setProduct_title(rs.getString("pdt_title"));
					pdto.setProduct_cnt(pdt_cnt);
					list.add(pdto);
			}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
			return list;
		}
	
	public ArrayList<ProductDTO> test(ArrayList<String> pdt_code, ArrayList<Integer> pdt_cnt) {
		dbConnect();
		ArrayList<ProductDTO> list = new ArrayList<>();
		String sql = "select * from product where pdt_code = ?";
		try {
			for(int i=0; i<pdt_code.size(); i++) {
				ps = conn.prepareStatement(sql);
				ps.setString(1, pdt_code.get(i));
				rs = ps.executeQuery();
				if(rs.next()) {
					ProductDTO pdto = new ProductDTO();
					pdto.setProduct_regi(rs.getString("pdt_regi"));
					pdto.setProduct_picture_url(rs.getString("pdt_thumb"));
					pdto.setProduct_code(pdt_code.get(i));
					pdto.setProduct_category(rs.getString("pdt_cate"));
					pdto.setProduct_price(rs.getInt("pdt_price"));
					pdto.setProduct_name(rs.getString("pdt_name"));
					pdto.setProduct_description(rs.getString("pdt_desc"));
					pdto.setProduct_title(rs.getString("pdt_title"));
					pdto.setProduct_cnt(pdt_cnt.get(i));
					pdto.setTotal_product_cnt(rs.getInt("pdt_cnt"));
					list.add(pdto);
			}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
			return list;
		}
	public ArrayList<ProductDTO> test2(String user_id) {
		dbConnect();
		ArrayList<ProductDTO> list = new ArrayList<>();
		String sql = "select user_addr, user_zipcode, user_addrDetail, user_name from user where user_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			rs = ps.executeQuery();
			if(rs.next()) {
				ProductDTO pdto = new ProductDTO();
				pdto.setProduct_user_recipient(rs.getString("user_name"));
				pdto.setProduct_user_addr(rs.getString("user_addr"));
				pdto.setProduct_user_addrDetail(rs.getString("user_addrDetail"));
				pdto.setProduct_user_zipcode(rs.getString("user_zipcode"));
				list.add(pdto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
				disConnect();
		}
			return list;
		}
	
	public void productDelete(String pdt_code) {
		dbConnect();
		String sql = "delete from product where pdt_code = ?";
		String sql2 = "delete from productimage where pdt_code = ?";
		try {
			ps = conn.prepareStatement(sql2);
			ps.setString(1, pdt_code);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pdt_code);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
	
	public void updateBasket(String user_id, int pdt_serial, int pdt_cnt) {
		dbConnect();
		String sql = "update basket set pdt_cnt = ? where user_id = ? and pdt_serial = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pdt_cnt);
			ps.setString(2, user_id);
			ps.setInt(3, pdt_serial);
			ps.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
	
	public boolean deleteBasket(String user_id, int pdt_serial) {
		dbConnect();
		boolean b = false;
		String sql = "delete from basket where user_id = ? and pdt_serial = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			ps.setInt(2, pdt_serial);
			ps.executeUpdate();
			b = true;
		}catch(SQLException e) {
			e.printStackTrace();
			return b;
		}finally {
			disConnect();
		}
		return b;
	}
}
