package me.cc.libraryservice;

import me.cc.beans.MainControlBean;
import org.apache.log4j.Logger;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

public abstract class AbstractTextBean {
    static Logger logger = Logger.getLogger(DocumentService.class);

    public static String path;
    public static final long serialVersionUID = 1L;
    public TreeNode root;
    public TreeNode[] selectedNodes;
    public  TreeNode singleSelectedNode;
    public AbstractTextService service;

    public MainControlBean getMainControlBean() {
        return mainControlBean;
    }

    public void setMainControlBean(MainControlBean mainControlBean) {
        this.mainControlBean = mainControlBean;
    }

    @ManagedProperty(value = "#{mainControlBean}")
    private MainControlBean mainControlBean;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        AbstractTextBean.logger = logger;
    }

    @PostConstruct
    public void init() {
        initService();
        FacesContext facesContext = FacesContext.getCurrentInstance();
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
                mainControlBean.html = service.loadHtml(path);
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