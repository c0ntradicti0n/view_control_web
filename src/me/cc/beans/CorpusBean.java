package me.cc.beans;

import me.cc.model.Distinction;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;

@ViewScoped
@ManagedBean(name = "corpusBean")
public class CorpusBean implements Serializable {
	MainControlBean mainControlBean;
	public ArrayList<ArrayList<Distinction>> Distinctions;

	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		mainControlBean = (MainControlBean) facesContext.getApplication().createValueBinding("#{mainControlBean}").getValue(facesContext);
	}



}