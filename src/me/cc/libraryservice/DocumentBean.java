package me.cc.libraryservice;

import me.cc.beans.MainControlBean;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "documentBean")
@ViewScoped
public class DocumentBean extends AbstractTextBean implements Serializable {
    static Logger logger = Logger.getLogger(DocumentService.class);

    @Override
    public HashMap<String, List<String>> getPaths ()  {
        logger.info("retrieve paths of all parsed documents");
        return fileREST.getDocsPaths();
    }

    public static final String RESTURL = "get_doc";
}