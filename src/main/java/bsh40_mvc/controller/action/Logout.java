package bsh40_mvc.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;

public class Logout implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		session.setAttribute("loginState", "logout");
		//12월 9일 추가 오류나면 삭제
		session.removeAttribute("user_name");
		session.removeAttribute("userclass");
		session.removeAttribute("userid");
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/mainPage.jsp");
		return forward;
	}
}
