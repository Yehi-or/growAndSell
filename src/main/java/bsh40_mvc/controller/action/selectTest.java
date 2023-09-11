package bsh40_mvc.controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;
import bsh40_mvc.model.ProductDAO;
import bsh40_mvc.model.ProductDTO;

public class selectTest implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		//상품리스트 조회
		ProductDAO po = new ProductDAO();
		
		ArrayList<ProductDTO> productList;
		productList = po.getList();
		request.setAttribute("productList", productList);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/imageSelectTest2.jsp");
		return forward;
	}
}
