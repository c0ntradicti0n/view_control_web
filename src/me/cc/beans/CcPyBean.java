package me.cc.beans;

import org.apache.log4j.Logger;
import org.primefaces.component.commandlink.CommandLink;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import me.cc.restclient.PythonClient;

@SessionScoped
@ManagedBean(name = "ccPyBean")
public class CcPyBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(CcPyBean.class);

	private List<String> documents;
	private String path = "";
	private String html = "";
	public static PythonClient pycl = new PythonClient();

	public CcPyBean() {
	}
	public void finalize() {
		// pyc.finalize();
	}

	public void changePath(ActionEvent ae) {
		path = (String) ((CommandLink) ae.getSource()).getValue();
		System.out.println("path..." + path);
		loadHtml(path);
	}

	private void loadHtml(String path2) {
		html = pycl.getHTML(path);
	};

	public List<String> getDocuments() {
		documents = pycl.getPaths();
		return documents;
	}

	public void compAll() {
		html = pycl.recomputeAll();
	}

	public void setDocuments(List<String> documents) {
		this.documents = documents;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
}
