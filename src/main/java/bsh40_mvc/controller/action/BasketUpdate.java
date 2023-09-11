package bsh40_mvc.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;
import bsh40_mvc.model.ProductDAO;

public class BasketUpdate implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductDAO pdao = new ProductDAO();
		int pdt_cnt_basket = Integer.parseInt(request.getParameter("pdt_cnt"));
		int pdt_serial_basket = Integer.parseInt(request.getParameter("bookmark"));
		String pdt_user_id_basket = request.getParameter("user_id");
		System.out.println(pdt_cnt_basket);
		System.out.println(pdt_serial_basket);
		System.out.println(pdt_user_id_basket);
		pdao.updateBasket(pdt_user_id_basket, pdt_serial_basket, pdt_cnt_basket);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/basket.do");
		return forward;
	}
}
