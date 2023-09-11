package bsh40_mvc.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;
import bsh40_mvc.model.ProductDAO;

public class ManagePdtDelete implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
	
		String pdt_code = request.getParameter("pdt_code");
		int currentPageNo = Integer.parseInt( request.getParameter("currentPageNo"));
		ProductDAO pdao = new ProductDAO();
		
		pdao.productDelete(pdt_code);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/allProduct.do?currentPageNo=" + currentPageNo);
		return forward;
	}
}
