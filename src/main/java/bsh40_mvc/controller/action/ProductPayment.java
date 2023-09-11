package bsh40_mvc.controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;
import bsh40_mvc.model.ProductDAO;
import bsh40_mvc.model.ProductDTO;

public class ProductPayment implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		
		HttpSession session = request.getSession();
		session.getAttribute("product");
		
		ArrayList<ProductDTO> list = (ArrayList<ProductDTO>) session.getAttribute("product");

		for(int i=0; i<list.size(); i++) {
			ProductDAO pdao = new ProductDAO();
			String code = list.get(i).getProduct_code();
			int price = list.get(i).getProduct_price();
			int pdt_cnt = list.get(i).getProduct_cnt();
			int total_price = price * pdt_cnt;
			String regi = list.get(i).getProduct_regi();
			String recipient = request.getParameter("recipient");
			String zipcode = request.getParameter("zipcode");
			String address = request.getParameter("address");
			String addr_detail = request.getParameter("address_detail");
			String user_id = request.getParameter("user_id");
			String addr = zipcode + " " + address + " " + addr_detail;
			String requests = request.getParameter("requests");
			String method = request.getParameter("method");
			pdao.productPayment(user_id, addr, method, code, price, recipient, requests, regi, pdt_cnt ,total_price);
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/purchaseSuccess.jsp");
		return forward;
	}
}
