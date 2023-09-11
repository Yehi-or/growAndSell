package bsh40_mvc.controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;

public class Momok implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String val = request.getParameter("types");
		ArrayList<String> array = new ArrayList<>();
		array.add(val);
		System.out.println(val);
		request.setAttribute("val", array);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/productRegiSecond.jsp");
		return forward;
	}
}
