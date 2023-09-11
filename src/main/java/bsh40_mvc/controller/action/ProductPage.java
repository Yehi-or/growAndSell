package bsh40_mvc.controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;
import bsh40_mvc.model.ProductDAO;
import bsh40_mvc.model.ProductDTO;

public class ProductPage implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		int product_serial = Integer.parseInt(request.getParameter("product_serial"));
		
		ProductDAO po = new ProductDAO();
		ArrayList<ProductDTO> product;
		
		product = po.productPage(product_serial);
		request.setAttribute("product", product);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/productPage.jsp");
		return forward;
	}
}
