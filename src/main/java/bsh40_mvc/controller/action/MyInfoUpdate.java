package bsh40_mvc.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo.exam01.dto.UserDAO;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;

public class MyInfoUpdate implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserDAO userdao = new UserDAO();
		
		String user_id = request.getParameter("user_id");
		String user_name = request.getParameter("name");
		String user_zipCode = request.getParameter("zipcode");
		String user_addrDetail = request.getParameter("address_detail");
		String user_addr = request.getParameter("address");
		
		System.out.println(user_id);
		System.out.println(user_name);
		System.out.println(user_zipCode);
		System.out.println(user_addrDetail);
		System.out.println(user_addr);
			
		boolean b = userdao.myInfoUpdate(user_id, user_name, user_zipCode, user_addrDetail, user_addr);
		
		System.out.println(b);
		
		HttpSession session = request.getSession();
		session.setAttribute("loginState", "logout");
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/mainPage.jsp");
		return forward;
	}
}
