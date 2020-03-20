package me.cc.libraryservice;

import me.cc.beans.MainControlBean;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractTextService implements Serializable {
    MainControlBean mainControlBean;

    public AbstractTextService(MainControlBean mainControlBean)  {
        this.mainControlBean = mainControlBean;
    }

    public void setPathKind(String path, String kind) {
        mainControlBean.setPath(path);
        mainControlBean.setKind(kind);
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
    abstract String loadHtml(String path);
}