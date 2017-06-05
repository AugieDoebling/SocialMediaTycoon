
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author skahal
 */
@Named(value = "login")
@SessionScoped
@ManagedBean
public class Login implements Serializable {
    
    private String adminLogin;
    private String adminPassword;
    private String playerLogin;
    private String playerPassword;
    private final DBConnect dbConnect = new DBConnect();
    private UIInput loginUI;    

    public UIInput getLoginUI() {
        return loginUI;
    }

    public void setLoginUI(UIInput loginUI) {
        this.loginUI = loginUI;
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

    public String getPlayerLogin() {
        return playerLogin;
    }

    public void setPlayerLogin(String playerLogin) {
        this.playerLogin = playerLogin;
    }

    public String getPlayerPassword() {
        return playerPassword;
    }

    public void setPlayerPassword(String playerPassword) {
        this.playerPassword = playerPassword;
    }      
    
    public boolean checkPlayerLogin(String login, String password) throws SQLException {
        Connection con = dbConnect.getConnection();
        String loginDB, passwordDB;

        if (con == null) {
            throw new SQLException("Can't get database connection");
        }

        PreparedStatement ps
                = con.prepareStatement(
                        "select login, password from player where"
                                + " login = ? and password = ?");
        
        ps.setString(1, login);
        ps.setString(2, password);
        
        ResultSet result = ps.executeQuery();
        
        if (! result.next()) {
            return false;
        }

        loginDB = result.getString("login");
        passwordDB = result.getString("password");
        
        result.close();
        con.close();
        
        return (login.equals(loginDB) && password.equals(passwordDB));
    }
    
    public boolean checkAdminLogin(String login, String password) throws SQLException {
        Connection con = dbConnect.getConnection();
        String loginDB, passwordDB;

        if (con == null) {
            throw new SQLException("Can't get database connection");
        }

        PreparedStatement ps
                = con.prepareStatement(
                        "select login, password from admin where"
                                + " login = ? and password = ?");
        
        ps.setString(1, login);
        ps.setString(2, password);
        
        ResultSet result = ps.executeQuery();
        
        if (! result.next()) {
            return false;
        }

        loginDB = result.getString("login");
        passwordDB = result.getString("password");
        
        System.out.println(loginDB);
        System.out.println(passwordDB);
        
        result.close();
        con.close();
        
        return (login.equals(loginDB) && password.equals(passwordDB));
    }

    public void validatePlayer(FacesContext context, UIComponent component, Object value)
            throws ValidatorException, SQLException {
        String submittedLogin = loginUI.getLocalValue().toString();
        String submittedPassword = value.toString();

        if (! checkPlayerLogin(submittedLogin, submittedPassword)) {
            FacesMessage errorMessage = new FacesMessage("Wrong login/password");
            throw new ValidatorException(errorMessage);
        }
    }
    
    public void validateAdmin(FacesContext context, UIComponent component, Object value)
            throws ValidatorException, SQLException {
        String submittedLogin = loginUI.getLocalValue().toString();
        String submittedPassword = value.toString();

        if (! checkAdminLogin(submittedLogin, submittedPassword)) {
            FacesMessage errorMessage = new FacesMessage("Wrong login/password");
            throw new ValidatorException(errorMessage);
        }
    }

    public String playerGo() throws SQLException {
        Util.validatePlayerSession(playerLogin);
        
        Integer loginCount = updateLoginRecord(playerLogin);
        
        updateRewards(playerLogin, loginCount);
        
        return "success";
    }
    
    public String adminGo() {       
        Util.validateAdminSession(adminLogin);
        
        return "success";
    }
    
    //logout event, invalidate session
    public String adminLogout() {
        Util.invalidateSession();

        return "logout";
    }
    
    //logout event, invalidate session
    public String playerLogout() {
        Util.invalidateSession();

        return "logout";
    }
    
    // checks if two dates vary by 1 day or more
    public boolean dateDifference(Date d1, Date d2) { 
        long currentDateMilliSec = d1.getTime();
        long updateDateMilliSec = d2.getTime();
        long diffDays = (currentDateMilliSec - updateDateMilliSec) / (24 * 60 * 60 * 1000);
        
        return true;
        //return (diffDays >= 1);
    }
    
    public Integer updateLoginRecord(String playerLogin) throws SQLException {
        Integer loginCount;
        Connection con = dbConnect.getConnection();
        
        con.setAutoCommit(false);

        if (con == null) {
            throw new SQLException("Can't get database connection");
        }
        
        PreparedStatement ps
                = con.prepareStatement(
                        "select count(*) as count from login_record where "
                                + " player_id = (select id from player where login = ?)");
        
        ps.setString(1, playerLogin);
        
        ResultSet result = ps.executeQuery();
        
        result.next();
        
        loginCount = result.getInt("count");

        if (loginCount == 0) {
            ps = con.prepareStatement("insert into login_record(player_id, login_date, count) "
                                    + " values((select id from player where login = ?), now(), 1)");

            ps.setString(1, playerLogin);

            ps.executeUpdate();
        }
        else {
            ps = con.prepareStatement("select login_date from login_record where "
                                + " player_id = (select id from player where login = ?)");
        
            ps.setString(1, playerLogin);

            result = ps.executeQuery();

            result.next();

            if (dateDifference(new Date(), new Date(result.getDate("login_date").getTime()))) {
                ps = con.prepareStatement("update login_record set "
                                    + " player_id = (select id from player where login = ?), login_date = now(), count = count + 1");

                ps.setString(1, playerLogin);

                ps.executeUpdate();  
            }
        }
        
        ps = con.prepareStatement(
                        "select count from login_record where "
                                + "player_id = (select id from player where login = ?)");
        
        ps.setString(1, playerLogin);
        
        result = ps.executeQuery();
        
        result.next();
        
        loginCount = result.getInt("count");
        
        con.commit();
        con.close();     
        
        return loginCount;
    }
    
    public void updateRewards(String playerLogin, Integer loginCount) throws SQLException {
        Connection con = dbConnect.getConnection();
        
        con.setAutoCommit(false);

        if (con == null) {
            throw new SQLException("Can't get database connection");
        }
        
        PreparedStatement ps;                

        if (loginCount > 1 && loginCount <= 10) {
            ps = con.prepareStatement("insert into rewards(reward_id, player_id) "
                                    + " values(1, (select id from player where login = ?)) ON CONFLICT DO NOTHING");

            ps.setString(1, playerLogin);

            ps.executeUpdate();
        }
        else if (loginCount > 10 && loginCount <= 20) {
            ps = con.prepareStatement("update rewards set reward_id = 2 where "
                                    + "player_id = (select id from player where login = ?)");

            ps.setString(1, playerLogin);

            ps.executeUpdate();
        }
        else if (loginCount > 20) {
            ps = con.prepareStatement("update rewards set reward_id = 3 where "
                                    + "player_id = (select id from player where login = ?)");

            ps.setString(1, playerLogin);

            ps.executeUpdate();
        }
        
        con.commit();
        con.close();     
    }
}
