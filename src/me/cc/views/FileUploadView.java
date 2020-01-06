package me.cc.views;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import me.cc.beans.CcPyBean;

@ViewScoped
@ManagedBean(name = "fileUploadView")
public class FileUploadView {
	private CcPyBean centralBean;
	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		centralBean = context.getApplication().evaluateExpressionGet(context, "#{ccPyBean}", CcPyBean.class);
	}
	
	public void upload(FileUploadEvent event) {
	    UploadedFile uploadedFile = event.getFile();
	    String fileName = uploadedFile.getFileName();
	    String contentType = uploadedFile.getContentType();
	    byte[] contents = uploadedFile.getContents(); // Or getInputStream()
	    
        centralBean.pycl.sendTextFile(contents, fileName);
    }


}