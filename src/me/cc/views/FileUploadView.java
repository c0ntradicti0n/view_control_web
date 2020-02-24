package me.cc.views;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import me.cc.beans.MainControlBean;

@ViewScoped
@ManagedBean(name = "fileUploadView")
public class FileUploadView {
	private MainControlBean centralBean;
	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		centralBean = context.getApplication().evaluateExpressionGet(context, "#{mainControlBean}", MainControlBean.class);
	}
	
	public void upload(FileUploadEvent event) {
	    UploadedFile uploadedFile = event.getFile();
	    String fileName = uploadedFile.getFileName();
	    String contentType = uploadedFile.getContentType();
	    byte[] contents = uploadedFile.getContents(); // Or getInputStream()
	    
        centralBean.fileREST.sendTextFile(contents, fileName);
    }


}