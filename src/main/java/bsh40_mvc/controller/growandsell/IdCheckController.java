package bsh40_mvc.controller.growandsell;

public class IdCheckController {
	String num1;
	String num2;
	String full;
	public String IdCheck(String type, int cate, int max) {
		num1 = String.format("%03d", cate);
		num2 = String.format("%03d", max);
		full = type + num1 + num2;
		return full;
	}
}
