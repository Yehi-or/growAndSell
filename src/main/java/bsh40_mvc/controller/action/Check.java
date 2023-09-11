package bsh40_mvc.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;
import bsh40_mvc.model.ProductDAO;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.json.simple.JSONObject;

public class Check implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		
		Gson gson = new Gson();
		ProductDAO pd = new ProductDAO();
		String a = request.getParameter("id");
		String s = pd.checkId(a);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setObj(s);
		forward.setPath("/com/view/member_create.jsp");
		return forward;
	}
}
