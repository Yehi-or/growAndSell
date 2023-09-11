package bsh40_mvc.controller.growandsell;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo.exam01.dto.UserDAO;

import bsh40_mvc.controller.action.AllProducts;
import bsh40_mvc.controller.action.AllUsers;
import bsh40_mvc.controller.action.Allpayment;
import bsh40_mvc.controller.action.Basket;
import bsh40_mvc.controller.action.BasketBuy;
import bsh40_mvc.controller.action.BasketDelete;
import bsh40_mvc.controller.action.BasketUpdate;
import bsh40_mvc.controller.action.Check2;
import bsh40_mvc.controller.action.GoMyPage;
import bsh40_mvc.controller.action.GotoMain;
import bsh40_mvc.controller.action.ProductInput;
import bsh40_mvc.controller.action.Login;
import bsh40_mvc.controller.action.Logout;
import bsh40_mvc.controller.action.ManagePage;
import bsh40_mvc.controller.action.ManagePdtDelete;
import bsh40_mvc.controller.action.Mojong;
import bsh40_mvc.controller.action.Momok;
import bsh40_mvc.controller.action.MyBuyProduct;
import bsh40_mvc.controller.action.MyInfo;
import bsh40_mvc.controller.action.MyInfoUpdate;
import bsh40_mvc.controller.action.MyPasswdUpdate;
import bsh40_mvc.controller.action.MyPasswdUpdate2;
import bsh40_mvc.controller.action.MyPasswdUpdateLast;
import bsh40_mvc.controller.action.MyProduct;
import bsh40_mvc.controller.action.Product;
import bsh40_mvc.controller.action.ProductBuy;
import bsh40_mvc.controller.action.ProductDelete;
import bsh40_mvc.controller.action.ProductInputPage;
import bsh40_mvc.controller.action.ProductPage;
import bsh40_mvc.controller.action.ProductPayment;
import bsh40_mvc.controller.action.ProductUpdate1;
import bsh40_mvc.controller.action.ProductUpdate2;
import bsh40_mvc.controller.action.Search;
import bsh40_mvc.controller.action.SignUp;
import bsh40_mvc.controller.action.SignUpPage;
import bsh40_mvc.controller.action.TopProduct;
import bsh40_mvc.controller.action.UserDelete;
import bsh40_mvc.controller.action.selectTest;
import bsh40_mvc.controller.action.selectTest2;
import bsh40_mvc.model.PageInfoVO;
import bsh40_mvc.model.ProductDAO;
import bsh40_mvc.model.ProductDTO;

public class FrontController extends HttpServlet implements Servlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		command = command.substring(command.lastIndexOf("/"));
		System.out.println(command);
		
		ProductDAO pdao = new ProductDAO();
		UserDAO udao = new UserDAO();
		
		ActionForward forward = null;
		Action action = null;
		
		switch(command) {
		case "/signUp.do":
			action = new SignUp();
			break;
		case "/signUp2.do":
			action = new SignUpPage();
			break;
		case "/login.do":
			ProductDAO pd = new ProductDAO();
			String id = request.getParameter("mem_id");
			String passwd = request.getParameter("mem_pass");
			String s1 = pd.checkLogin(id, passwd, request);
			response.getWriter().print(s1);
			break;
		case "/login2.do":
			action = new Login();
			break;
		case "/login3.do":
			String checkId = request.getParameter("mem_id");
			String checkPasswd = request.getParameter("mem_pass");
			String pass = udao.checkPasswd(checkId, checkPasswd);
			response.getWriter().print(pass);
			break;
		case "/logout.do":
			action = new Logout();
			break;
		case "/Check2.do":
			action = new Check2();
			break;
		case "/Check.do":
			ProductDAO pd2 = new ProductDAO();
			String a = request.getParameter("id");
			String s = pd2.checkId(a); //전달할 데이터
			response.getWriter().print(s);
			break;
		case "/check12.do":
			action = new Product();
			break;
		case "/productInput.do":
			action = new ProductInput();
			break;
		case "/imageSelect.do":
			action = new selectTest();
			break;
		case "/imageSelect2.do":
			action = new selectTest2();
			break;
		case "/productPage.do":
			action = new ProductPage();
			break;
		case "/buy.do":
			action = new ProductBuy();
			break;
		case "/bookmark.do":
			ProductDAO pd3 = new ProductDAO();
			int check = Integer.parseInt(request.getParameter("bookmark"));
			int pdt_cnt = Integer.parseInt(request.getParameter("pdt_cnt"));
			String user_id = request.getParameter("bookmarkId");
			String checks = udao.basketReady(user_id, check);
			if(checks.equals("{\"result\":\"fail\"}")) {
				pd3.bookmark(user_id, check, pdt_cnt);
			}
			response.getWriter().print(checks);
			break;
		case "/good.do":
			int serial_num = Integer.parseInt(request.getParameter("serial_num"));
			pdao.updateGoodUp(serial_num);
			break;
		case "/mojong1.do":
			action= new Mojong();
			break;
		case "/momok.do":
			action = new Momok();
			break;
		case "/search.do":
			action = new Search();
			break;
		case "/basket.do":
			action = new Basket();
			break;
		case "/productRegi.do":
			action = new ProductInputPage();
			break;
		case "/myProduct.do":
			action = new MyProduct();
			break;
		case "/myInfo.do":
			action = new MyInfo();
			break;
		case "/myPage.do":
			action = new GoMyPage();
			break;
		case "/myInfoUpdate.do":
			action = new MyInfoUpdate();
			break;
		case "/myPasswd.do":
			action = new MyPasswdUpdate();
			break;
		case "/myPasswdUpdate.do":
			action = new MyPasswdUpdate2();
			break;
		case "/passwdUpdateLast.do":
			action = new MyPasswdUpdateLast();
			break;
		case "/topProduct.do":
			action = new TopProduct();
			break;
		case "/productUpdate.do":
			action = new ProductUpdate1();
			break;
		case "/productUpdate2.do":
			action = new ProductUpdate2();
			break;
		case "/productDelete.do":
			action = new ProductDelete();
			break;
		case "/payment.do":
			action = new ProductPayment();
			break;
		case "/myBuyProduct.do":
			action = new MyBuyProduct();
			break;
		case "/order.do":
			action = new BasketBuy();
			break;
		case "/tomain.do":
			action = new GotoMain();
			break;
		case "/manage.do":
			action = new ManagePage();
			break;
		case "/allUser.do":
			action = new AllUsers();
			break;
		case "/userDelete.do":
			action = new UserDelete();
			break;
		case "/allProduct.do":
			action = new AllProducts();
			break;
		case "/managePdtDelete.do":
			action = new ManagePdtDelete();
			break;
		case "/allPayment.do":
			action = new Allpayment(); 
			break;
		case "/basketUpdate.do":
			action = new BasketUpdate();
			break;
		case "/basketDelete.do":
			action = new BasketDelete();
			break;
		}
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
		}
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
}


