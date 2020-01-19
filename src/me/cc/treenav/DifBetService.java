package me.cc.treenav;


import me.cc.restclient.PythonClient;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;

@ManagedBean(name = "documentService")
@ApplicationScoped
public class DifBetService extends AbstractTextService {
    public ArrayList<String> getPaths ()  {
        return fileREST.getDifBetPaths();
    }
    public String loadHtml(String path) {
        return fileREST.getDifBet(path);
    }
}
