package me.cc.libraryservice;
import me.cc.beans.MainControlBean;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "documentService")
@ApplicationScoped
public class DocumentService extends AbstractTextService {
    static Logger logger = Logger.getLogger(DocumentService.class);

    public DocumentService(MainControlBean mainControlBean) {
        super(mainControlBean);
    }

    @Override
    public HashMap<String, List<String>> getPaths ()  {
        logger.info("retrieve paths of all parsed documents");
        return mainControlBean.fileREST.getDocsPaths();
    }

    public static final String RESTURL = "get_doc";
    @Override
    public String loadHtml(String path) {
        setPathKind(path, RESTURL);
        return mainControlBean.fileREST.getDoc(path, RESTURL);
    }
}
