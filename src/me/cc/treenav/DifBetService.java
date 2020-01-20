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
    @Override
    public String loadHtml(String path) {
        return fileREST.getDifBet(path);
    }
}
