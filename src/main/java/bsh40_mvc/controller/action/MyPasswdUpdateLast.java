package bsh40_mvc.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo.exam01.dto.UserDAO;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;

public class MyPasswdUpdateLast implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		
		HttpSession session = request.getSession();
		
		String passwd = request.getParameter("mem_passwd");
		System.out.println(passwd);
		
		UserDAO udao = new UserDAO();
		
		String mem_passwd = request.getParameter("mem_passwd");
		String user_id = request.getParameter("user_id");
		
		udao.passwdUpdate(user_id, mem_passwd);
		session.setAttribute("loginState", "logout");
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/mainPage.jsp");
		return forward;
	}
}
