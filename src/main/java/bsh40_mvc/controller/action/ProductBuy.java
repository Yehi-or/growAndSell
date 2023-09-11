package bsh40_mvc.controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;
import bsh40_mvc.model.ProductDAO;
import bsh40_mvc.model.ProductDTO;

public class ProductBuy implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
	
		String pdt_code = request.getParameter("pdt_code");
		String user_id = request.getParameter("user_id");
		int product_cnt = Integer.parseInt(request.getParameter("product_cnt"));
		System.out.println(product_cnt);
		ArrayList<String> code_list = new ArrayList<String>();
		
		code_list.add(pdt_code);
		ProductDAO pd = new ProductDAO();
		
		ArrayList<ProductDTO> list = pd.test(code_list, product_cnt);
		request.setAttribute("productlist", list);
		
		ArrayList<ProductDTO> list2 = pd.test2(user_id);
		request.setAttribute("productlist2", list2);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/basketPayment.jsp");
		return forward;
	}
}
