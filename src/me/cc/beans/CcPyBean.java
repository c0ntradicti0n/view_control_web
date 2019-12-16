package me.cc.beans;

import org.apache.log4j.Logger;
import org.primefaces.model.TreeNode;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import me.cc.model.Spot;
import me.cc.model.Tag;
import me.cc.model.annotationTagsFactory;
import me.cc.restclient.PythonClient;
import me.cc.run.Exec;
import me.cc.treenav.Document;

@SessionScoped
@ManagedBean(name = "ccPyBean")
public class CcPyBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(CcPyBean.class);
	
	private  List<String> possibleTags = Arrays.asList("Contrast", "Subject");

	public ArrayList<ArrayList<Tag>> annotationSets = annotationTagsFactory.produce(2,
			Arrays.asList("Contrast", "Subject"));
	public String annotationMarkup = "?";
	public String text = "---- Text not set ----";
	public Integer textlen;

	@PostConstruct
	public void init() {
		System.out.println(annotationSets);
	}

	private List<String> documents;
	private String path = "";
	private String html = "";
	private String inputTextAreaSelectedText;

	public static PythonClient pycl = new PythonClient();

	public void displaySelectedNode(TreeNode node) {
		if (node != null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Your choices:", "" + node);
			FacesContext.getCurrentInstance().addMessage(null, message);
			path = ((Document) node.getData()).getName();
			loadHtml(path);
		}
	}

	public TypeReference String_Type =  new TypeReference<String>() { };
	public TypeReference AS_Type = new TypeReference<ArrayList<ArrayList<Tag>>>() { };
	public TypeReference Int_Type = new TypeReference<Integer>()  { };
		
	ObjectMapper om = new ObjectMapper();
	
	public Spot spot;
	
	public String updateAnnotation() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String spotJson = params.get("selectedText");
		try {
			setSpot(om.readValue(spotJson, Spot.class));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Annotating the following text: '" + text + "'");
		System.out.println("Annotating the following text: '" + text + "'");
		textlen = (Integer) pycl.stdCall("textlen", spot, textlen , null, Int_Type);
		logger.info("got: " +  textlen);
		annotationSets = (ArrayList<ArrayList<Tag>>) pycl.stdCall("predict", spot, annotationSets, null, AS_Type);
		logger.info("got: " +  Exec.nestedToString(annotationSets));
		annotationMarkup =  pycl.stdCall("markup", spot, annotationMarkup, annotationSets, String_Type);
		logger.info("got: " +  annotationMarkup);
		text = getSpot().getText();
		System.out.println("updating the markup thing:" + annotationMarkup);
		System.out.println(annotationSets);
		return "ManipulationScreen";
	}

	private void loadHtml(String path2) {
		html = pycl.getHTML(path);
	};

	public List<String> getDocuments() {
		documents = new ArrayList( Arrays.asList( pycl.getPaths()));
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

	public List<String> getPossibleTags() {
		return possibleTags;
	}

	public void setPossibleTags(List<String> possibleTags) {
		this.possibleTags = possibleTags;
	}

	public Spot getSpot() {
		return spot;
	}

	public void setSpot(Spot spot) {
		this.spot = spot;
	}

}
