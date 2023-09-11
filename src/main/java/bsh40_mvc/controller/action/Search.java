package bsh40_mvc.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;

public class Search implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/imageSelectTest2.jsp");
		return forward;
	}
}
