package me.cc.treenav;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.TreeNode;

@ManagedBean(name = "DocumentBean")
@ViewScoped
public class DocumentBean extends AbstractTextBean implements Serializable {
    @ManagedProperty("#{documentService}")
    private AbstractTextService service = new DocumentService();
}