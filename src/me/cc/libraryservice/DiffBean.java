package me.cc.libraryservice;

import me.cc.beans.MainControlBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "diffBean")
@ViewScoped
public class DiffBean extends AbstractTextBean implements Serializable {
    @Override
    public void initService(MainControlBean mainControlBean) {
        service = new DiffService(mainControlBean);
    }
}