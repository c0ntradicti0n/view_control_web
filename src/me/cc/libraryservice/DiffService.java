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

}
