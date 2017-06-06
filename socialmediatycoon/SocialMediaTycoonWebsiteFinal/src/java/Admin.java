
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.el.ELContext;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shubhamkahal
 */
@Named(value = "admin")
@SessionScoped
@ManagedBean
public class Admin extends Person implements Serializable {
	
    private Integer id;
    private String adminLogin;
    private String adminPassword;
    private String adminOldPassword;

    @ManagedProperty(value = "#{login}")
    private Login login;

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }  

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getAdminLogin() {
        return adminLogin;
    }

    public void setAdminLogin(String adminLogin) {
        this.adminLogin = adminLogin;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminOldPassword() {
        return adminOldPassword;
    }

    public void setAdminOldPassword(String adminOldPassword) {
        this.adminOldPassword = adminOldPassword;
    }
    
    public String getAdminLoginFromSession() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        login = (Login) elContext.getELResolver().getValue(elContext, null, "login");
    
        return login.getAdminLogin();
    }
}
