package bsh40_mvc.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;

public class GoMyPage implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/myPage.jsp");
		return forward;
	}
}
