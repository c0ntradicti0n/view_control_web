package me.cc.treenav;

import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean(name = "diffBean")
@ViewScoped
public class DiffBean extends AbstractTextBean implements Serializable {
    @Override
    public void initService() {
        service = new DiffService();
    }
}