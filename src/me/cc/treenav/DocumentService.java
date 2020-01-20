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

    @Override
    public String loadHtml(String path) {
        return fileREST.getHTML(path);
    }
}
