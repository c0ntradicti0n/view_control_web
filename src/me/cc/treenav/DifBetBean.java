package me.cc.treenav;

import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean(name = "DocumentBean")
@ViewScoped
public class DifBetBean extends AbstractTextBean implements Serializable {
    private AbstractTextService service = new DifBetService();

    private static final long serialVersionUID = 1L;
    private TreeNode root;
    private TreeNode[] selectedNodes;
    private TreeNode singleSelectedNode;

    @PostConstruct
    public void init() {
        root = service.createCheckboxDocuments();
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

    public void setService( DifBetService service) {
        this.service = service;
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

    public void displaySelectedNode(TreeNode node) {
        if (node != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Your choices:", "" + node);
            FacesContext.getCurrentInstance().addMessage(null, message);
            path = ((Document) node.getData()).getName();
            ccPyBean.html = service.loadHtml(path);
        }
    }

    public TreeNode getSingleSelectedNode() {
        return singleSelectedNode;
    }

    public void setSingleSelectedNode(TreeNode singleSelectedNode) {
        this.singleSelectedNode = singleSelectedNode;
    }
}