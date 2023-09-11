package bsh40_mvc.controller.growandsell;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;

public class ActionForward {
	
	public boolean isRedirect = false;
	private String path = null;
	private String obj = null;
	
	public boolean isRedirect() {
		return this.isRedirect;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getObj() {
		return this.obj;
	}
	
	public void setObj(String obj) {
		this.obj = obj;
	}
	
}
