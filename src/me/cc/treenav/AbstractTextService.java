package me.cc.treenav;

import me.cc.beans.CcPyBean;
import me.cc.restclient.PythonClient;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractTextService implements Serializable {
    PythonClient fileREST = new PythonClient( "http://127.0.0.1:5555");


    CcPyBean ccPyBean;

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ccPyBean = (CcPyBean) facesContext.getApplication().createValueBinding("#{ccPyBean}").getValue(facesContext);
    }

    public void setPathKind(String path, String kind) {
        System.out.println(path + " " + kind);
        init();
        ccPyBean.setPath(path);
        ccPyBean.setKind(kind);
    }

    public TreeNode createCheckboxDocuments() {
        CheckboxTreeNode key;
        TreeNode root = new CheckboxTreeNode(new Document("Files"), null);
        HashMap<String, List<String>> topic_paths = getPaths();
        for (Map.Entry<String, List<String>> entry: topic_paths.entrySet()) {
            key = new CheckboxTreeNode(new Document(entry.getKey()), root);
            for (String path : entry.getValue()) {
                new CheckboxTreeNode(new Document(path), key);
            }
        }
        return root;
    }

    abstract HashMap<String, List<String>> getPaths();
    abstract String loadHtml(String path);
}
