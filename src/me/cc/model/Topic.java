package me.cc.model;

import java.io.Serializable;
import java.util.List;

public class Topic implements Serializable {

	private static final long serialVersionUID = 20111120L;

	private String name;
	private List<Document> documents;

	public Topic(String name, List<Document> _documents) {
		this.name = name;
		this.documents = _documents;
	}

	public String getName() {
		return name;
	}

	public List<Document> getDocumentWithMeta() {
		return documents;
	}
}


