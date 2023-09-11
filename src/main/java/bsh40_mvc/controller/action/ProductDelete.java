package bsh40_mvc.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;
import bsh40_mvc.model.ProductDAO;

public class ProductDelete implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
	
		String pdt_code = request.getParameter("pdt_code");
		System.out.println(pdt_code);
		ProductDAO pdao = new ProductDAO();
		
		pdao.myProductDelete(pdt_code);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/myProduct.do");
		return forward;
	}
}

