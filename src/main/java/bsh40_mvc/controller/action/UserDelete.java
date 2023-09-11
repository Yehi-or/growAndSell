package bsh40_mvc.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo.exam01.dto.UserDAO;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;

public class UserDelete implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		UserDAO udao = new UserDAO();
		String user_id = request.getParameter("user_id");
		String check = request.getParameter("check");
		System.out.println(check);
		udao.userDelete(user_id);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		if(check.equals("1")) {
			forward.setPath("/com/view/allUser.do");
		}else {
			session.setAttribute("loginState", "logout");
			forward.setPath("/com/view/mainPage.jsp");
		}
		return forward;
	}
}
