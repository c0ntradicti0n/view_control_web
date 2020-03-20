package me.cc.libraryservice;

import me.cc.beans.MainControlBean;
import me.cc.restclient.PythonClient;
import me.cc.setup.PortConfig;
import org.apache.log4j.Logger;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractTextBean {
    static Logger logger = Logger.getLogger(DocumentService.class);

    public static String path;
    public static final long serialVersionUID = 1L;
    public TreeNode root;
    public TreeNode[] selectedNodes;
    public  TreeNode singleSelectedNode;
    public AbstractTextService service;

    public static PythonClient fileREST       = new PythonClient( "http://127.0.0.1:" + PortConfig.doc_port);


    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        AbstractTextBean.logger = logger;
    }

    @PostConstruct
    public void init() {
        root = createCheckboxDocuments();
    }

    public TreeNode getRoot() {
        return root;
    }
    public TreeNode[] getSelectedNodes() {
        return selectedNodes;
    }
    public void setSelectedNodes(TreeNode[] selectedNodes) {
        this.selectedNodes = selectedNodes;
    }
    public void setService(AbstractTextService service) {
        this.service = service;
    }
    public AbstractTextService getService() {
        return service;
    }

    public void displaySelectedNodes(TreeNode[] nodes) {
        if (nodes != null && nodes.length > 0) {
            StringBuilder builder = new StringBuilder();
            for (TreeNode node : nodes) {
                if (node.isLeaf()) {
                    builder.append(node.getData());
                    builder.append("<br />");
                }
            }
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Your choices:", builder.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void displaySelectedNode(String path) {
        if (path != null) {
            try {
                //mainControlBean.html = service.loadHtml(path);
            }
            catch (StringIndexOutOfBoundsException e) {
                logger.info ("no document to show for " + path);
            }
        }
    }



    public TreeNode createCheckboxDocuments() {
        CheckboxTreeNode key;
        TreeNode root = new CheckboxTreeNode(new Document("Files"), null);
        HashMap<String, List<String>> topic_paths = getPaths();
        Document pseudoTopicDocument;
        for (Map.Entry<String, List<String>> entry: topic_paths.entrySet()) {
            pseudoTopicDocument = new Document(entry.getKey());
            pseudoTopicDocument.setTopic(true);
            key = new CheckboxTreeNode(pseudoTopicDocument, root);
            for (String path : entry.getValue()) {
                new CheckboxTreeNode(new Document(path), key);
            }
        }
        return root;
    }

    abstract HashMap<String, List<String>> getPaths();
}
