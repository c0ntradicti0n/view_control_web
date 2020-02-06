package me.cc.treenav;


import me.cc.beans.PedantLogin;
import org.apache.log4j.Logger;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.HashMap;
import java.util.List;

@ManagedBean(name = "diffService")
@ApplicationScoped
public class DiffService extends AbstractTextService {
    static Logger logger = Logger.getLogger(DiffService.class);

    @Override
    public HashMap<String, List<String>> getPaths ()  {
        logger.info("retrieve paths of all parsed differencebetween-pages");
        return fileREST.getDiffPaths();
    }

    public static final String RESTURL = "get_diff";
    @Override
    public String loadHtml(String path) {
        setPathKind(path, RESTURL);
        return fileREST.getDoc(path, RESTURL);
    }
}
