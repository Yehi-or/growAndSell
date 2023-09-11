package bsh40_mvc.controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;
import bsh40_mvc.model.ProductDAO;
import bsh40_mvc.model.ProductDTO;

public class Product implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		
		ProductDAO pd = new ProductDAO();
		String description = request.getParameter("desc");
		System.out.println(description);
		
		List<ProductDTO> productList = pd.selectAllProducts(description);
		request.setAttribute("productList", productList);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/productIndex.jsp");
		return forward;
	}
}
