package me.cc.treenav;

import me.cc.beans.CcPyBean;
import me.cc.model.AnyAnswer;
import me.cc.restclient.PythonClient;
import org.apache.log4j.Logger;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;

public abstract class AbstractTextBean {
    static Logger logger = Logger.getLogger(DocumentService.class);

    public static String path;
    public static final long serialVersionUID = 1L;
    public TreeNode root;
    public TreeNode[] selectedNodes;
    public  TreeNode singleSelectedNode;
    public AbstractTextService service;

    CcPyBean ccPyBean;

    @PostConstruct
    public void init() {
        initService();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ccPyBean = (CcPyBean) facesContext.getApplication().createValueBinding("#{ccPyBean}").getValue(facesContext);
        root = service.createCheckboxDocuments();

    }

    abstract public void initService();
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
    public AbstractTextService getService( ) {
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
                ccPyBean.html = service.loadHtml(path);
            }
            catch (StringIndexOutOfBoundsException e) {
                logger.info ("no document to show  for " + path);
            }
        }
    }

    public TreeNode getSingleSelectedNode() {
        return singleSelectedNode;
    }

    public void setSingleSelectedNode(TreeNode singleSelectedNode) {
        this.singleSelectedNode = singleSelectedNode;
    }

}
