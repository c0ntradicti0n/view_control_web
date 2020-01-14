package me.cc.treenav;


import java.util.ArrayList;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

import me.cc.restclient.PythonClient;
 
@ManagedBean(name = "documentService")
@ApplicationScoped
public class DocumentService {
     
    public TreeNode createCheckboxDocuments() {
        TreeNode root = new CheckboxTreeNode(new Document("Files"), null);
         
        PythonClient fileREST = new PythonClient( "http://127.0.0.1:5555");
  		ArrayList<String> paths = fileREST.getPaths();
		for (String p:paths)  {
        	new CheckboxTreeNode(new Document(p), root);
		}

        return root;
    }
}
