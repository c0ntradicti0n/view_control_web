package me.cc.libraryservice;


import me.cc.beans.MainControlBean;
import org.apache.log4j.Logger;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.HashMap;
import java.util.List;

@ManagedBean(name = "diffService")
@ApplicationScoped
public class DiffService extends AbstractTextService {
    static Logger logger = Logger.getLogger(DiffService.class);

    public DiffService(MainControlBean mainControlBean) {
        super(mainControlBean);
    }

    @Override
    public HashMap<String, List<String>> getPaths ()  {
        logger.info("retrieve paths of all parsed differencebetween-pages");
        return mainControlBean.fileREST.getDiffPaths();
    }

    public static final String RESTURL = "get_diff";
    @Override
    public String loadHtml(String path) {
        setPathKind(path, RESTURL);
        return mainControlBean.fileREST.getDoc(path, RESTURL);
    }
}
