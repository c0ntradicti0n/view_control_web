package me.cc.treenav;


import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import org.primefaces.component.commandlink.CommandLink;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

import me.cc.restclient.PythonClient;
 
@ManagedBean(name = "documentService")
@ApplicationScoped
public class DocumentService {
     
    public TreeNode createCheckboxDocuments() {
        TreeNode root = new CheckboxTreeNode(new Document("Files"), null);
         
        PythonClient pycl = new PythonClient();
  		ArrayList<String> paths = pycl.getPaths();
		for (String p:paths)  {
        	TreeNode tn = new CheckboxTreeNode(new Document(p), root);
		}

        return root;
    }
}
