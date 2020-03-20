package me.cc.libraryservice;

import me.cc.beans.MainControlBean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "documentBean")
@ViewScoped
public class DocumentBean extends AbstractTextBean implements Serializable {
    @Override
    public void initService() {
        service = new DocumentService(this.getMainControlBean());

    }
}