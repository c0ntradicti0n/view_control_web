package me.cc.beans;

import org.apache.log4j.Logger;
import org.primefaces.component.commandlink.CommandLink;
import org.primefaces.model.TreeNode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import me.cc.model.Tag;
import me.cc.model.annotationTagsFactory;
import me.cc.restclient.PythonClient;
import me.cc.treenav.Document;
import me.cc.treenav.SelectionView;

@SessionScoped
@ManagedBean(name = "ccPyBean")
public class CcPyBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(CcPyBean.class);
	
	private ArrayList<ArrayList<Tag>> annotationSets = annotationTagsFactory.produce(2, Arrays.asList("Contrast", "Subject"));
	private String annotationMarkup = "?";
	
	@PostConstruct
	public void init()  {
		System.out.println(annotationSets);
	}
  

	private List<String> documents;
	private String path = "";
	private String html = "";
	public static PythonClient pycl = new PythonClient();
	
	public void displaySelectedNode(TreeNode node) {
        if(node != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Your choices:", "" + node);
            FacesContext.getCurrentInstance().addMessage(null, message);
    		path = ((Document) node.getData()).getName();
    		loadHtml(path);
        }
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

	public ArrayList<ArrayList<Tag>> getAnnotationSets() {
		return annotationSets;
	}

	public void setAnnotationSets(ArrayList<ArrayList<Tag>> annotationSets) {
		this.annotationSets = annotationSets;
	}

	public String getAnnotationMarkup() {
		return annotationMarkup;
	}

	public void setAnnotationMarkup(String annotationMarkup) {
		this.annotationMarkup = annotationMarkup;
	}

}
