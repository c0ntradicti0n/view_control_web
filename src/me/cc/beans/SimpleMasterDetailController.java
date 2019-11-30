package me.cc.beans;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import me.cc.model.Meta;
import me.cc.model.Document;
import me.cc.model.Topic;

@ManagedBean
@ViewScoped
public class SimpleMasterDetailController implements Serializable {

	private static final long serialVersionUID = 20111120L;

	private List<Topic> topics;
	private int currentLevel = 1;

	public SimpleMasterDetailController() {
		if (topics == null) {
			topics = new ArrayList<Topic>();

			// football
			List<Document> documents = new ArrayList<Document>();
			Document document = new Document("Switzerland", "CH", "Football", getmetas("Switzerland"));
			documents.add(document);
			document = new Document("England", "UK", "Football", getmetas("England"));
			documents.add(document);
			document = new Document("Spain", "ES", "Football", getmetas("Spain"));
			documents.add(document);
			document = new Document("Netherlands", "NL", "Football", getmetas("Netherlands"));
			documents.add(document);
			topics.add(new Topic("Football", documents));

			//basketball
			documents = new ArrayList<Document>();
			document = new Document("Germany", "DE", "Basketball", getmetas("Germany"));
			documents.add(document);
			document = new Document("USA", "US", "Basketball", getmetas("USA"));
			documents.add(document);
			document = new Document("Poland", "PL", "Basketball", getmetas("Poland"));
			documents.add(document);
			topics.add(new Topic("Basketball", documents));

			// ice hockey
			documents = new ArrayList<Document>();
			document = new Document("Russia", "RU", "Ice Hockey", getmetas("Russia"));
			documents.add(document);
			document = new Document("Canada", "CA", "Ice Hockey", getmetas("Canada"));
			documents.add(document);
			topics.add(new Topic("Ice Hockey", documents));
		}
	}

	public List<Topic> gettopics() {
		return topics;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	private List<Meta> getmetas(String document) {
		List<Meta> metas = new ArrayList<Meta>();

		metas.add(new Meta(document + " Supermeta", 20));
		metas.add(new Meta(document + " NotBadmeta", 15));
		metas.add(new Meta(document + " Crapmeta", 30));

		return metas;
	}
}
            