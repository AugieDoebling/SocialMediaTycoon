
import java.io.Serializable;
import java.sql.Connection;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named(value = "util")
@SessionScoped
@ManagedBean
public class Util implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(Util.class.getName());
	
    public static void invalidateSession() {
        HttpSession session = getSession();
        session.invalidate();
    }
    
    public static String validatePlayerSession(String playerLogin) {
        HttpSession session = getSession();
        session.setAttribute("playerLogin", playerLogin);
        return "success";
    }
    
    public static String validateAdminSession(String adminLogin) {
        HttpSession session = getSession();
        session.setAttribute("adminLogin", adminLogin);
        return "success";
    }

    public void validateDate(FacesContext context, UIComponent component, Object value)
    {

        try {
            Date d = (Date) value;
        } catch (Exception e) {
        	LOGGER.log(Level.FINE, "Input is not a valid date", e);
        }
    }
    
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                            .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
            return (HttpServletRequest) FacesContext.getCurrentInstance()
                            .getExternalContext().getRequest();
    }

    public static String getPlayerLogin() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                        .getExternalContext().getSession(false);
        
        return session.getAttribute("playerLogin").toString();
    }
    
    public static String getAdminLogin() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                        .getExternalContext().getSession(false);
        
        return session.getAttribute("adminLogin").toString();
    }
}
