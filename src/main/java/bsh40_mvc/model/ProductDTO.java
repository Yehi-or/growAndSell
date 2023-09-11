package bsh40_mvc.model;

import java.util.ArrayList;

public class ProductDTO {
	
	private int product_serial;
	private int product_price;
	private int product_cate;
	private int product_sebu_cate;
	private int product_good;
	private int product_cnt;
	private int total_product_cnt;
	private int product_cnt_req;
	private String product_name;
	private String product_category;
	private String product_picture_url;
	private String product_link_url;
	private String product_description;
	private String product_regi;
	private String product_title;
	private String product_code;
	private String product_user_addr;
	private String product_user_addrDetail;
	private String product_user_zipcode;
	private String product_user_recipient;
	private ArrayList<String> product_image;
	
	public ProductDTO() {
		super();
	}
	
	public void setProduct_image(ArrayList<String> product_image) {
		this.product_image = product_image;
	}
	
	public ArrayList<String> getProduct_image() {
		return this.product_image;
	}
	
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	public void setProduct_picture_url(String product_picture_url) {
		this.product_picture_url = product_picture_url;
	}
	
	public void setProduct_serial(int product_serial) {
		this.product_serial =  product_serial;
	}
	
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	
	public void setProduct_link_url(String product_link_url) {
		this.product_link_url = product_link_url;
	}
	
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public int getProduct_serial() {
		return product_serial;
	}

	public int getProduct_price() {
		return product_price;
	}

	public String getProduct_name() {
		return product_name;
	}

	public String getProduct_picture_url() {
		return product_picture_url;
	}

	public String getProduct_link_url() {
		return product_link_url;
	}

	public String getProduct_description() {
		return product_description;
	}

	public int getProduct_sebu_cate() {
		return product_sebu_cate;
	}

	public void setProduct_sebu_cate(int product_sebu_cate) {
		this.product_sebu_cate = product_sebu_cate;
	}

	public int getProduct_cate() {
		return product_cate;
	}

	public void setProduct_cate(int product_cate) {
		this.product_cate = product_cate;
	}

	public String getProduct_regi() {
		return product_regi;
	}

	public void setProduct_regi(String product_regi) {
		this.product_regi = product_regi;
	}

	public String getProduct_title() {
		return product_title;
	}

	public void setProduct_title(String product_title) {
		this.product_title = product_title;
	}

	public int getProduct_good() {
		return product_good;
	}

	public void setProduct_good(int product_good) {
		this.product_good = product_good;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getProduct_category() {
		return product_category;
	}

	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}

	public String getProduct_user_addrDetail() {
		return product_user_addrDetail;
	}

	public void setProduct_user_addrDetail(String product_user_addrDetail) {
		this.product_user_addrDetail = product_user_addrDetail;
	}

	public String getProduct_user_addr() {
		return product_user_addr;
	}

	public void setProduct_user_addr(String product_user_addr) {
		this.product_user_addr = product_user_addr;
	}

	public String getProduct_user_zipcode() {
		return product_user_zipcode;
	}

	public void setProduct_user_zipcode(String product_user_zipcode) {
		this.product_user_zipcode = product_user_zipcode;
	}

	public String getProduct_user_recipient() {
		return product_user_recipient;
	}

	public void setProduct_user_recipient(String product_user_recipient) {
		this.product_user_recipient = product_user_recipient;
	}

	public int getProduct_cnt() {
		return product_cnt;
	}

	public void setProduct_cnt(int product_cnt) {
		this.product_cnt = product_cnt;
	}

	public int getTotal_product_cnt() {
		return total_product_cnt;
	}

	public void setTotal_product_cnt(int total_product_cnt) {
		this.total_product_cnt = total_product_cnt;
	}

	public int getProduct_cnt_req() {
		return product_cnt_req;
	}

	public void setProduct_cnt_req(int product_cnt_req) {
		this.product_cnt_req = product_cnt_req;
	}
	
	
}
