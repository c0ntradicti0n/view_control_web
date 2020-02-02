package me.cc.beans;

import me.cc.model.AnyAnswer;
import me.cc.model.Distinction;
import me.cc.model.Tag;
import me.cc.model.annotationTagsFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;

@ViewScoped
@ManagedBean(name = "corpusBean")
public class CorpusBean implements Serializable {
	CcPyBean ccPyBean;
	public ArrayList<ArrayList<Distinction>> Distinctions;

	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ccPyBean = (CcPyBean) facesContext.getApplication().createValueBinding("#{ccPyBean}").getValue(facesContext);
	}



}