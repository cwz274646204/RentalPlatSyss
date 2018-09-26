package RPS.RentalPlatSys.Util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@SessionScoped

public class Util { 
	private static Util instance;
	private static final String projectName = "RentalPlatSys";
	private static final String projectPomPath = "C:\\Users\\KAMI\\workspace\\RentalPlatSys\\pom.xml";
	private Util() {};
	
	public static Util getInstance() {
		if (instance == null) {
			instance = new Util();
		}
		return instance;
	}
	
	public String submit(InputStream input, String savePath) {
		HttpSession session =(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		String rootPath = session.getServletContext().getRealPath(savePath);  
			String filename = String.valueOf(System.currentTimeMillis())+".jpg";
	        try {
				Files.copy(input, new File(rootPath, filename).toPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return filename;
	   
    }
	
	public int getUserId() {
		HttpSession session =(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		return (Integer)session.getAttribute("userid");
	}
	
	public static String getMD5(String str) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(str.getBytes());
	        return new BigInteger(1, md.digest()).toString(16);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return null;
	}
	
	public Boolean isLogin() {
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		if(session.getAttribute("userid")!=null)return true;
		else return false;
	}
	
	public void logout() {
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("userid", null);
	}
	
	public static String getProjectname() {
		return projectName;
	}
	
	public static String getProjectpompath() {
		return projectPomPath;
	}
}
