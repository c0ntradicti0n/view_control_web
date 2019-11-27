package me.cc.views;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import me.cc.beans.CcPyBean;

@RequestScoped
@ManagedBean(name = "fileUploadView")
public class FileUploadView {
     
    private UploadedFile file;
 
    public UploadedFile getFile() {
        return file;
    }
    
	private CcPyBean centralBean;
	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		centralBean = context.getApplication().evaluateExpressionGet(context, "#{ccPyBean}", CcPyBean.class);
	}
 
    public void setFile(UploadedFile file) {
        this.file = file;
        centralBean.pycl.sendTextFile(file.getContents(), file.getFileName());
    }

 
    public void upload() {
        if (file != null) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
     

     
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}