package bsh40_mvc.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;
import bsh40_mvc.model.ProductDAO;

public class ProductUpdate2 implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		String cate = request.getParameter("cate");
		String cate2;
		
		ProductDAO pdao = new ProductDAO();
		
		if(cate.equals("모종")) {
			cate2 = request.getParameter("mojong");
		}else {
			cate2 = request.getParameter("momok");
		}
		
		String pdt_code = request.getParameter("pdt_code");
		String pdt_name = request.getParameter("product_name");
		int pdt_price = Integer.parseInt(request.getParameter("product_price"));
		String pdt_title = request.getParameter("product_title");
		String pdt_desc = request.getParameter("product_description");
		
		System.out.println(pdt_name);
		System.out.println(pdt_price);
		System.out.println(pdt_title);
		
		pdao.productUpdate(pdt_code, pdt_name, pdt_price, pdt_title, pdt_desc, cate2);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/mainPage.jsp");
		return forward;
	}
}
