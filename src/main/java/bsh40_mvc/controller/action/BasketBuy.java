package bsh40_mvc.controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;
import bsh40_mvc.model.ProductDAO;
import bsh40_mvc.model.ProductDTO;

public class BasketBuy implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = String.valueOf(session.getAttribute("userid"));	
		ArrayList<String> code_list = new ArrayList<>();
		ArrayList<Integer> pdt_cnt_list = new ArrayList<>();
		
		int i=0;
		while(request.getParameter("orders[" + i + "].bookId") != null) {
			code_list.add(request.getParameter("orders[" + i + "].bookId"));
			pdt_cnt_list.add(Integer.parseInt((request.getParameter("orders[" + i + "].pdt_cnt"))));
			i++;
		}
		
		ProductDAO pd = new ProductDAO();
		ArrayList<ProductDTO> list = pd.test(code_list, pdt_cnt_list);
		request.setAttribute("productlist", list);
		
		ArrayList<ProductDTO> list2 = pd.test2(user_id);
		request.setAttribute("productlist2", list2);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/basketPayment.jsp");
		return forward;
	}
}