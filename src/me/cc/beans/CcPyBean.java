package me.cc.beans;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.primefaces.component.commandlink.CommandLink;
import org.primefaces.model.TreeNode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
	
	private ArrayList<HashMap<String, Tag>> annotationSets = annotationTagsFactory.produce(2, Arrays.asList("Contrast", "Subject"));
	private String annotationMarkup = "?";
	public String text = "Abstract and surrealism arose out of unrelated ideas and "
			+ "different influences. Each was first used in a different artistic medium, "
			+ "even though both are most evident today in paintings. They also differ in "
			+ "the specific norm or idea that they are rejecting or departing from. And "
			+ "although each artwork can be seen as being from the artist�s imagination, "
			+ "the idea and goal behind each artistic style is also different. Abstract "
			+ "and surrealism are discussed further below as well as their differences.";
	
	@PostConstruct
	public void init()  {
		System.out.println(annotationSets);
	}
  

	private List<String> documents;
	private String path = "";
	private String html = "";
	public JSONObject spans = new JSONObject();
	private String inputTextAreaSelectedText;

	public static PythonClient pycl = new PythonClient();
	
	public void displaySelectedNode(TreeNode node) {
        if(node != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Your choices:", "" + node);
            FacesContext.getCurrentInstance().addMessage(null, message);
    		path = ((Document) node.getData()).getName();
    		loadHtml(path);
        }
    }    

	public void updateAnnotation()  {		
		HashMap<String, Object> ret = pycl.stdCall("predictmarkup", text);
		annotationMarkup = (String) ret.get("markup");
		annotationSets = (ArrayList<HashMap<String, Tag>>) ret.get("spans");
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

	public ArrayList<HashMap<String, Tag>> getAnnotationSets() {
		return annotationSets;
	}

	public void setAnnotationSets(ArrayList<HashMap<String, Tag>> annotationSets) {
		this.annotationSets = annotationSets;
	}

	public String getAnnotationMarkup() {
		return annotationMarkup;
	}

	public void setAnnotationMarkup(String annotationMarkup) {
		this.annotationMarkup = annotationMarkup;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getInputTextAreaSelectedText() {
		return inputTextAreaSelectedText;
	}

	public void setInputTextAreaSelectedText(String inputTextAreaSelectedText) {
		this.inputTextAreaSelectedText = inputTextAreaSelectedText;
	}

}
