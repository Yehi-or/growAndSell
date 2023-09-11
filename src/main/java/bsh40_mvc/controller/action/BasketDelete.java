package bsh40_mvc.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;
import bsh40_mvc.model.ProductDAO;

public class BasketDelete implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int pdt_serial = Integer.parseInt(request.getParameter("pdt_serial"));
		String user_id = request.getParameter("user_id");
		ProductDAO pdao = new ProductDAO();
		
		System.out.println(pdt_serial);
		System.out.println(user_id);
		
		boolean b = pdao.deleteBasket(user_id, pdt_serial);
		System.out.println(b);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/basket.do");
		return forward;
	}
}