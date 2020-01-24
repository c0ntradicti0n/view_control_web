package me.cc.treenav;


import java.util.ArrayList;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "documentService")
@ApplicationScoped
public class DocumentService extends AbstractTextService {
    @Override
    ArrayList<String> getPaths() {
        return fileREST.getPaths();
    }

    public static final String RESTURL = "doc_html";
    @Override
    public String loadHtml(String path) {
        setPathKind(path, RESTURL);
        return fileREST.getDoc(path, RESTURL);
    }
}
