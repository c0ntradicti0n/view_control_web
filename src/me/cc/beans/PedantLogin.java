package me.cc.beans;

import me.cc.model.AnyAnswer;
import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

@SessionScoped
@ManagedBean(name = "pedantLogin")
public class PedantLogin implements Serializable {
    static Logger logger = Logger.getLogger(PedantLogin.class);

    private String user;
    private String password;
    private String _user;
    private String _password;
    CcPyBean ccPyBean;
    private String admin_user;
    private String admin_password;


    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ccPyBean = (CcPyBean) facesContext.getApplication().createValueBinding("#{ccPyBean}").getValue(facesContext);
        read_config();

    }

    private void read_config() {
        Properties prop = new Properties();
        String fileName = "app.config";
        InputStream is = null;
        try {
            is = new FileInputStream(fileName);
        } catch (FileNotFoundException ex) {
            logger.info("config file not found in " + System.getProperty("user.dir"));
        }
        try {
            prop.load(is);
        } catch (Exception ex) {
            logger.info("config file not readable " + System.getProperty("user.dir"));
        }
        admin_user = prop.getProperty("app.admin_user");
        admin_password = prop.getProperty("app.admin_password");
    }

    public PedantLogin() {
    }

    public PedantLogin(String user, String password, String _user, String _password) {
        this.user = user;
        this.password = password;
        this._user = _user;
        this._password = _password;
    }

    public String login() {
        String myCurrentDir = System.getProperty("user.dir")
                + File.separator
                + System.getProperty("sun.java.command")
                .substring(0, System.getProperty("sun.java.command").lastIndexOf("."))
                .replace(".", File.separator);
        System.out.println(myCurrentDir);


        FacesMessage message = null;
        boolean loggedIn = false;

        if(user != null && user.equals(admin_user) && password != null && password.equals(admin_password)) {
            ccPyBean.setLoggedIn(true);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", user);
        } else {
            ccPyBean.setLoggedIn(false);
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", ccPyBean.isLoggedIn());

        return "reader";
    }

    public String logout() {
        ccPyBean.setLoggedIn(false);

        return "reader";
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String get_user() {
        return _user;
    }

    public void set_user(String _user) {
        this._user = _user;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

}




















