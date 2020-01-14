package me.cc.beans;



import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import org.apache.log4j.Logger;

@SessionScoped
@ManagedBean(name = "htmlRenderBean")
public class HtmlRenderBean implements Serializable {
	static Logger logger = Logger.getLogger(HtmlRenderBean.class);

	private String path = "kuchen";
	
	public String giveHtml()  {
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get("file"), StandardCharsets.UTF_8);
		} catch (IOException e) {
			logger.error("Couldn't read html file: '" + getPath() + "'");
		}
		return lines.toString();
	}
	
	
	public void onPathSet(String p)  {
		System.out.println("path...");
		//path = (String) ((CommandLink) e.getSource()).getValue();
		System.out.println("path..." + path);

	}
	

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	

}
