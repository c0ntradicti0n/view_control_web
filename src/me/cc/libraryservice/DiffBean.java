package me.cc.libraryservice;

import me.cc.beans.MainControlBean;
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@ManagedBean(name = "diffBean")
@ViewScoped
public class DiffBean extends AbstractTextBean implements Serializable {
    static Logger logger = Logger.getLogger(DiffService.class);

    @Override
    public HashMap<String, List<String>> getPaths ()  {
        logger.info("retrieving paths of all parsed differencebetween-pages");
        return fileREST.getDiffPaths();
    }

    public static final String RESTURL = "get_diff";

}