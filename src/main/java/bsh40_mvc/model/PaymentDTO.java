package bsh40_mvc.model;

public class PaymentDTO {
	private String pay_user_id;
	private String pay_addr;
	private String pay_method;
	private String pay_pdt_code;
	private int pay_price;
	private int pdt_cnt_req;
	private int pdt_total_price;
	private String pay_request;
	private String pay_pdt_regi;
	private String pay_recipient;
	private String pay_date;
	
	
	public String getPay_user_id() {
		return pay_user_id;
	}
	public void setPay_user_id(String pay_user_id) {
		this.pay_user_id = pay_user_id;
	}
	public String getPay_addr() {
		return pay_addr;
	}
	public void setPay_addr(String pay_addr) {
		this.pay_addr = pay_addr;
	}
	public String getPay_method() {
		return pay_method;
	}
	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}
	public String getPay_pdt_code() {
		return pay_pdt_code;
	}
	public void setPay_pdt_code(String pay_pdt_code) {
		this.pay_pdt_code = pay_pdt_code;
	}
	public int getPay_price() {
		return pay_price;
	}
	public void setPay_price(int pay_price) {
		this.pay_price = pay_price;
	}
	public String getPay_request() {
		return pay_request;
	}
	public void setPay_request(String pay_request) {
		this.pay_request = pay_request;
	}
	public String getPay_pdt_regi() {
		return pay_pdt_regi;
	}
	public void setPay_pdt_regi(String pay_pdt_regi) {
		this.pay_pdt_regi = pay_pdt_regi;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public String getPay_recipient() {
		return pay_recipient;
	}
	public void setPay_recipient(String pay_recipient) {
		this.pay_recipient = pay_recipient;
	}
	public int getPdt_total_price() {
		return pdt_total_price;
	}
	public void setPdt_total_price(int pdt_total_price) {
		this.pdt_total_price = pdt_total_price;
	}
	public int getPdt_cnt_req() {
		return pdt_cnt_req;
	}
	public void setPdt_cnt_req(int pdt_cnt_req) {
		this.pdt_cnt_req = pdt_cnt_req;
	}
}
