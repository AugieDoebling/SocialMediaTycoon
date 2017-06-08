
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.el.ELContext;
import javax.faces.bean.ManagedProperty;

@Named(value = "player")
@SessionScoped
@ManagedBean
public class Player extends Person implements Serializable {
	
	private static final Logger LOGGER = Logger.getLogger( Connection.class.getName());
	private static final String DATABASE_ERROR = "Cant get database connection";

    @ManagedProperty(value = "#{login}")
    private Login login;
    private DBConnect dbConnect = new DBConnect();
    private Integer id;
    private String playerLogin;
    private String playerPassword;
    private String playerOldPassword;
    private String loginString = "login";
    private String count = "count";
    private String noDBconnection = "Can't get database connection";
    private Date createdDate = new Date();
    
    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    
    public DBConnect getDbConnect() {
        return dbConnect;
    }

    public void setDbConnect(DBConnect dbConnect) {
        this.dbConnect = dbConnect;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPlayerOldPassword() {
        return playerOldPassword;
    }

    public void setPlayerOldPassword(String playerOldPassword) {
        this.playerOldPassword = playerOldPassword;
    }

    public String getPlayerLoginFromSession() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        Login login2 = (Login) elContext.getELResolver().getValue(elContext, null, loginString);
    
        return login2.getPlayerLogin();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        this.createdDate = createdDate;
    }
    
    public String createPlayer() throws SQLException, ParseException {
        Connection con = dbConnect.getConnection();
        Statement statement;
        PreparedStatement preparedStatement;

        if (con == null) {
            throw new SQLException(noDBconnection);
        }
        
        con.setAutoCommit(false);

        try
        {
        	statement = con.createStatement();

            preparedStatement = con.prepareStatement("insert into player(login, password, first_name, last_name, email, postal_address, created_date) values(?,?,?,?,?,?,?)");
            preparedStatement.setString(1, playerLogin);
            preparedStatement.setString(2, playerPassword);
            preparedStatement.setString(3, this.getFirstName());
            preparedStatement.setString(4, this.getLastName());
            preparedStatement.setString(5, this.getEmail());
            preparedStatement.setString(6, this.getPostalAddress());
            preparedStatement.setDate(7, new java.sql.Date(createdDate.getTime()));
            preparedStatement.executeUpdate();
        }
        catch(Exception ex)
        {
        	LOGGER.log(Level.FINE, DATABASE_ERROR, ex);
        }
        finally
        {
        	if(statement != null)
        	{
        		statement.close();
        	}
        	if(preparedStatement != null)
        	{
        		preparedStatement.close();
        	}
        	if(con != null)
        	{
        		con.commit();
        		con.close();
        	}
        }    
        
        clear();
        
        return "index";
    }
    
    public String deletePlayer(String playerLogin) throws ParseException {
        Connection con = dbConnect.getConnection();
        PreparedStatement preparedStatement;
        
        try
        {
        	con.setAutoCommit(false);
            
            preparedStatement = con.prepareStatement("delete from player where login = ?");
            
            preparedStatement.setString(1, playerLogin);
            preparedStatement.executeUpdate();
        }
        catch(Exception ex)
        {
        	LOGGER.log(Level.FINE, DATABASE_ERROR, ex);
        }
        finally
        {
        	if(preparedStatement != null)
        	{
        		preparedStatement.close();
        	}
            if(con != null)
            {
            	con.commit();
            	con.close();
            }
        }
                
        return "viewPlayers";
    }

    public ResultSet executeSelectQuery(String submittedLogin) {
        Connection con = dbConnect.getConnection();
        PreparedStatement preparedStatement;
        ResultSet result;
        
        try
        {
        	con.setAutoCommit(false);

            preparedStatement = con.prepareStatement("select count(*) as count from player where login = ?");
            preparedStatement.setString(1, submittedLogin);
            
            result = preparedStatement.executeQuery();
        }
        catch(Exception some)
        {
        	LOGGER.log(Level.FINE, DATABASE_ERROR, some);
        }
        finally
        {
        	if(preparedStatement != null)
        	{
        		preparedStatement.close();
        	}
            if(con != null)
            {
            	con.close();
            }
        }
        return result;
    }
    
    public void validateLogin(FacesContext context, UIComponent component, Object value)
            throws ValidatorException, SQLException {                
        String submittedLogin = (String) value;

        ResultSet result = executeQuery(submittedLogin);

        result.next();
                
        if (result.getInt(count) != 0) {
            FacesMessage errorMessage = new FacesMessage("This login already exists, please pick another one.");
            throw new ValidatorException(errorMessage);
        }
        
        result.close();
    }
    
    public void validateLoginExistence(FacesContext context, UIComponent component, Object value)
            throws ValidatorException, SQLException {                
        String submittedLogin = (String) value;
        
        ResultSet result = executeSelectQuery(submittedLogin);

        result.next();
        
        result.close();
        
        if (result.getInt(count) == 0) {
            FacesMessage errorMessage = new FacesMessage("This login does not exist.");
            throw new ValidatorException(errorMessage);
        }
    }
    
    public List<Player> getPlayerList() throws SQLException {

        Connection con = dbConnect.getConnection();

        if (con == null) {
            throw new SQLException(noDBconnection);
        }
        
        con.setAutoCommit(false);

        PreparedStatement preparedStatement
                = con.prepareStatement(
                        "select login, first_name, last_name, email, postal_address, created_date from player order by first_name, last_name");

        //get customer data from database
        ResultSet result = preparedStatement.executeQuery();

        List<Player> playerList = new ArrayList<>();

        while (result.next()) {
            Player player = new Player();

            player.setPlayerLogin(result.getString(loginString));
            player.setFirstName(result.getString("first_name"));
            player.setLastName(result.getString("last_name"));
            player.setEmail(result.getString("email"));
            player.setPostalAddress(result.getString("postal_address"));
            player.setCreatedDate(result.getDate("created_date"));

            //store all data into a List
            playerList.add(player);
        }
        
        result.close();
        con.close();
        
        return playerList;
    }
    
    public Integer getPlayerId(String playerLogin) throws SQLException {
        Connection con = dbConnect.getConnection();

        if (con == null) {
            throw new SQLException(noDBconnection);
        }
        
        con.setAutoCommit(false);

        PreparedStatement preparedStatement
                = con.prepareStatement(
                        "select id from player where login = ?");

        preparedStatement.setString(1, playerLogin);
        
        //get customer data from database
        ResultSet result = preparedStatement.executeQuery();
        
        result.next();
        
        return result.getInt("id");
    }
    
    public String getPlayerLoginFromId(Integer id) throws SQLException {
        Connection con = dbConnect.getConnection();

        if (con == null) {
            throw new SQLException(noDBconnection);
        }
        
        con.setAutoCommit(false);

        PreparedStatement preparedStatement
                = con.prepareStatement(
                        "select login from player where id = ?");

        preparedStatement.setInt(1, id);
        
        //get customer data from database
        ResultSet result = preparedStatement.executeQuery();
        
        result.next();
        
        return result.getString(loginString);
    }
    
    public void clear() {
        setPlayerLogin(null);
        setPlayerPassword(null);
        setFirstName(null);
        setLastName(null);
        setEmail(null);
        setPostalAddress(null);        
    }
    
    public String changePassword() throws SQLException {
       Connection con = dbConnect.getConnection();

       if (con == null) {
           throw new SQLException(noDBconnection);
       }
       con.setAutoCommit(false);

       Statement statement = con.createStatement();

       PreparedStatement preparedStatement = con.prepareStatement("update player set password = ? where login = ?");
       preparedStatement.setString(1, playerPassword);
       preparedStatement.setString(2, Util.getPlayerLogin());
       preparedStatement.executeUpdate();

       statement.close();
       con.commit();
       con.close();
       clear();

       return "index";
    }
    
    public void validateOldPassword(FacesContext context, UIComponent component, Object value)
            throws ValidatorException, SQLException {
        
        String submittedPassword = (String) value;

        Connection con = dbConnect.getConnection();
        int count2;

        if (con == null) {
            throw new SQLException(noDBconnection);
        }
        
        con.setAutoCommit(false);

        PreparedStatement preparedStatement = con.prepareStatement("select count(*) as count from player where login = ? and password = ?");
        preparedStatement.setString(1, Util.getPlayerLogin());
        preparedStatement.setString(2, submittedPassword);
        
        ResultSet result = preparedStatement.executeQuery();
        
        result.next();
        
        count2 = result.getInt(count);
        
        if (count2 == 0) {
            FacesMessage errorMessage = new FacesMessage("Incorrect old password.");
            throw new ValidatorException(errorMessage);
        }
        
        result.close();
        con.close();
    }
    
    public String setPlayerProfile() throws SQLException  {
        Connection con = dbConnect.getConnection();

        if (con == null) {
            throw new SQLException(noDBconnection);
        }
        
        con.setAutoCommit(false);

        PreparedStatement preparedStatement = con.prepareStatement("select first_name, last_name from player where login = ?");
        preparedStatement.setString(1, Util.getPlayerLogin());
        
        ResultSet result = preparedStatement.executeQuery();
        
        result.next();
        
        this.setFirstName(result.getString("first_name"));
        this.setLastName(result.getString("last_name"));
        
        return "userProfile";
    }
}
