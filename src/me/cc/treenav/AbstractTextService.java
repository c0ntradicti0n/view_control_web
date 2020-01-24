package me.cc.treenav;

import me.cc.beans.CcPyBean;
import me.cc.restclient.PythonClient;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class AbstractTextService implements Serializable {
    PythonClient fileREST = new PythonClient( "http://127.0.0.1:5555");


    CcPyBean ccPyBean;

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ccPyBean = (CcPyBean) facesContext.getApplication().createValueBinding("#{ccPyBean}").getValue(facesContext);
    }

    public void setPathKind(String path, String kind) {
        init();
        System.out.println(path + " " +kind);
        ccPyBean.setPath(path);
        ccPyBean.setKind(kind);
    }

    public TreeNode createCheckboxDocuments() {
        TreeNode root = new CheckboxTreeNode(new Document("Files"), null);

        ArrayList<String> paths = getPaths();
        for (String p:paths)  {
            new CheckboxTreeNode(new Document(p), root);
        }

        return root;
    }

    abstract ArrayList<String> getPaths();
    abstract String loadHtml(String path);
}
