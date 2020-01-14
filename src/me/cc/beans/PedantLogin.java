package me.cc.beans;

import me.cc.model.AnyAnswer;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


import java.io.Serializable;
import java.util.ArrayList;

@SessionScoped
@ManagedBean(name = "pedantLogin")
public class PedantLogin implements Serializable {
    private String user;
    private String password;
    private String _user;
    private String _password;
    CcPyBean ccPyBean;

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ccPyBean = (CcPyBean) facesContext.getApplication().createValueBinding("#{ccPyBean}").getValue(facesContext);
        System.out.println("345678909iuhjinbjh");


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
        FacesMessage message = null;
        boolean loggedIn = false;

        if(user != null && user.equals("admin") && password != null && password.equals("admin")) {
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




















