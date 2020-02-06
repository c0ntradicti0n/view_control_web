package me.cc.treenav;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "documentService")
@ApplicationScoped
public class DocumentService extends AbstractTextService {
    static Logger logger = Logger.getLogger(DocumentService.class);

    @Override
    public HashMap<String, List<String>> getPaths ()  {
        logger.info("retrieve paths of all parsed documents");
        return fileREST.getDocsPaths();
    }

    public static final String RESTURL = "get_doc";
    @Override
    public String loadHtml(String path) {
        setPathKind(path, RESTURL);
        return fileREST.getDoc(path, RESTURL);
    }
}
