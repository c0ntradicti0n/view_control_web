package me.cc.beans;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import me.cc.model.Spot;
import me.cc.model.Tag;
import me.cc.model.annotationTagsFactory;
import me.cc.restclient.PythonClient;
import me.cc.run.Exec;

@SessionScoped
@ManagedBean(name = "mainControlBean")
public class MainControlBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(MainControlBean.class);

	public boolean isLocalExists() {
		return localExists;
	}

	public void setLocalExists(boolean localExists) {
		this.localExists = localExists;
	}

	private boolean localExists;

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	private boolean loggedIn = true;

    private  List<String> possibleTags = Arrays.asList("Contrast", "Subject");

	public ArrayList<ArrayList<Tag>> annotationSets = annotationTagsFactory.produce(2,
			Arrays.asList("Contrast", "Subject"));
	public String annotationMarkup = "?";
	public String text = "---- Text not set ----";
	public Integer textlen;

	@PostConstruct
	public void init() {
		System.out.println(annotationSets);
		pingStatus ();
	}

	private List<String> documents;
	private String path = ""; // which document
	private String kind = ""; // of which kind of ressource
	public String html = ""; // what is the content
	private String inputTextAreaSelectedText;
	public Spot spot;

	private boolean restActive = false;
	private boolean annoActive = false;
	private boolean trainActive = false;

	public static PythonClient annotationREST = new PythonClient("http://127.0.0.1:5000");
	public PythonClient fileREST  = new PythonClient( "http://127.0.0.1:5555");
	public PythonClient scienceREST  = new PythonClient( "http://127.0.0.1:5556");
	public boolean getRestActive() {
		return restActive;
	}
	public void setRestActive(boolean restActive) {
		restActive = restActive;
	}
	public boolean getAnnoActive() {
		return annoActive;
	}
	public void setAnnoActive(boolean annoActive) {
		annoActive = annoActive;
	}
	ObjectMapper om = new ObjectMapper();
	public String logs;
	public String logs_which;


	public String updateAnnotation() {
		pingStatus ();
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
		System.out.println(spot);
		textlen = (Integer) annotationREST.stdCall("textlen", spot, textlen , null, PythonClient.Int_Type);
		logger.info("got: " +  textlen);
		annotationSets = (ArrayList<ArrayList<Tag>>) annotationREST.stdCall("predict", spot, annotationSets, null, PythonClient.AS_Type);
		logger.info("got: " +  Exec.nestedToString(annotationSets));
		annotationMarkup =  annotationREST.stdCall("markup", spot, annotationMarkup, annotationSets, PythonClient.String_Type);
		logger.info("got: " +  annotationMarkup);
		text = getSpot().getText();
		logger.info("updating the markup thing:" + annotationMarkup);
		logger.info(annotationSets);
		return "manipulator";
	}

	public void pingStatus ()  {
		System.out.println("ping");
		documents = new ArrayList( Arrays.asList(fileREST.getDocsPaths()));
		if (documents.size()>0)  {
			restActive = true;
			annoActive = annotationREST.ping();
		}
		else  
		{
			restActive = false;
		}
	}

	public List<String> getDocuments() {
		documents = new ArrayList( Arrays.asList( fileREST.getDocsPaths()));
		return documents;
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

	public boolean getTrainActive() {
		return trainActive;
	}

	public void setTrainActive(boolean trainActive) {
		this.trainActive = trainActive;
	}

	public String getLogs() {
		return logs;
	}

	public void setLogs(String logs) {
		this.logs = logs;
	}

	public String getLogs_which() {
		return logs_which;
	}

	public void setLogs_which(String logs_which) {
		this.logs_which = logs_which;
	}


	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

    public String getLocalDocument() {
		String localPath = "document/" +
				path.replace('.', '_').replace(' ', '_').replace('-', '_') +
			".html";
		File lf = new File(localPath);
		this.localExists = lf.exists();
		return localPath;

    }
}
