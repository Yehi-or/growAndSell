package bsh40_mvc.controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.exam01.dto.UserDAO;
import com.example.demo.exam01.dto.UserDTO;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;
import bsh40_mvc.model.ProductDAO;
import bsh40_mvc.model.ProductDTO;

public class ProductUpdate1 implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		
		String pdt_code = request.getParameter("pdt_code");
		ProductDAO pdao = new ProductDAO();
		ArrayList<ProductDTO> list;
		list = pdao.productInfo(pdt_code);
		request.setAttribute("productInfo", list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/com/view/productUpdate.jsp");
		return forward;
	}
}
