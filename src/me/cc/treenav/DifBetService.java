package me.cc.treenav;


import me.cc.restclient.PythonClient;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;

@ManagedBean(name = "difBetService")
@ApplicationScoped
public class DifBetService extends AbstractTextService {

    @Override
    public ArrayList<String> getPaths ()  {
        System.out.println("Difbetpaths");
        return fileREST.getDifBetPaths();
    }

    public static final String RESTURL = "difbet_html";
    @Override
    public String loadHtml(String path) {
        setPathKind(path, RESTURL);
        return fileREST.getDoc(path, RESTURL);
    }
}
