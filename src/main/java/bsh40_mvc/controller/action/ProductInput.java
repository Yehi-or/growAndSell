package bsh40_mvc.controller.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bsh40_mvc.controller.growandsell.Action;
import bsh40_mvc.controller.growandsell.ActionForward;
import bsh40_mvc.controller.growandsell.IdCheckController;
import bsh40_mvc.controller.growandsell.ThumController;
import bsh40_mvc.model.FileDAO;
import bsh40_mvc.model.ProductDAO;
import bsh40_mvc.model.ProductDTO;

public class ProductInput implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ProductDTO pv = new ProductDTO();
		ThumController thum = new ThumController();
		IdCheckController id = new IdCheckController();
		ProductDAO po = new ProductDAO();
		FileDAO file = new FileDAO();
		String pdt_code = "";
		String cate = "";
		String sebu_cate = "";
		
		String uploadDir = this.getClass().getResource("").getPath();
		uploadDir = uploadDir.substring(1,uploadDir.indexOf(".metadata"))+"test/src/main/webapp/com/image";
		
		int maxSize = 1024 * 1024 * 100;
		String encoding = "UTF-8";
		
		MultipartRequest multipartRequest
		= new MultipartRequest(request, uploadDir, maxSize, encoding,
				new DefaultFileRenamePolicy());
		
		pv.setProduct_name(multipartRequest.getParameter("product_name"));
		if(multipartRequest.getParameter("product_price").equals("")) {
			pv.setProduct_price(0);
		}else {
			pv.setProduct_price(Integer.parseInt(multipartRequest.getParameter("product_price")));
		}
		pv.setProduct_title(multipartRequest.getParameter("product_title"));
		pv.setProduct_description(multipartRequest.getParameter("product_description"));
		pv.setProduct_regi(multipartRequest.getParameter("product_regi"));
		Enumeration<?> files = multipartRequest.getFileNames();
		
		String pdt_name = pv.getProduct_name();
		String pdt_desc = pv.getProduct_description();
		int pdt_price = pv.getProduct_price();
		String pdt_regi = pv.getProduct_regi();
		String pdt_title = pv.getProduct_title();
		
		String action = multipartRequest.getParameter("action");
		
		if(action.equals("모종")) {
			cate = "모종";
			String mojong = multipartRequest.getParameter("mojong");
			sebu_cate = mojong;
			int s1 = po.productCateMojong(mojong);
			int s2 = po.ItemCheck(mojong);
			pdt_code = id.IdCheck("PL01", s1, s2);
		}else if(action.equals("묘목")){
			cate = "묘목";
			String momok = multipartRequest.getParameter("momok");
			sebu_cate = momok;
			int s1 = po.productCateMomok(momok);
			int s2 = po.ItemCheck(momok);
			pdt_code = id.IdCheck("PL02", s1, s2);
		}
	
		String element = "";
		String filesystemName = "";
		String originalFileName = "";
		String contentType = "";
		String thumbNail = "";
		long length = 0;
		int index = 0;
		String extension = "";
	
		while(files.hasMoreElements()) {
			int imageCnt = 0;
			element = (String)files.nextElement();
			filesystemName = multipartRequest.getFilesystemName(element);
			originalFileName = multipartRequest.getOriginalFileName(element);
			contentType = multipartRequest.getContentType(element);
			if(multipartRequest.getFile(element) != null) {
				length = multipartRequest.getFile(element).length();
			}else {
				length = 0;
			}
			if(filesystemName != null) {
				index = filesystemName.lastIndexOf(".");
			}else {
				index = 0;
			}
			if(filesystemName != null) {
				extension = filesystemName.substring(index + 1);
			}else {
				extension = "";
			}
			if(filesystemName != null) {
				imageCnt++;
				if(imageCnt==1) {
					thumbNail = thum.makeThum(uploadDir, filesystemName);
				}
				file.upload(pdt_code, filesystemName, originalFileName, length, extension);
			}
		}
		po.insertProduct(pdt_code, pdt_name, pdt_title, pdt_desc, thumbNail, pdt_price, pdt_regi, 0, cate, sebu_cate, 100);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./mainPage.jsp");
		return forward;
	}
}
