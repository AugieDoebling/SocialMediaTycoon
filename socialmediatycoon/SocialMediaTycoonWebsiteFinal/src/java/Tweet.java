
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
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
@Named(value = "tweet")
@SessionScoped
@ManagedBean
public class Tweet 
{
	private static final String INDEX = "index";
	
    private DBConnect dbConnect = new DBConnect();
    private Integer id;
    private String text;
    private String destination = "Rome Tor Vergata";
    private String dbConnection = "Can't get database connection";
    private String playerLogin = "player_login";
    private Player player = new Player();
    private Integer score;
    private Integer appropriate;
    private Integer warning;
    private Date createdDate = new Date();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public DBConnect getDbConnect() {
        return dbConnect;
    }

    public void setDbConnect(DBConnect dbConnect) {
        this.dbConnect = dbConnect;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }        

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }        

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public int getAppropriate() {
        return appropriate;
    }

    public void setAppropriate(Integer appropriate) {
        this.appropriate = appropriate;
    }

    public Integer getWarning() {
        return warning;
    }

    public void setWarning(Integer warning) {
        this.warning = warning;
    }
    
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    public String send() throws SQLException {        
        Random randomGenerator = new Random();
        int low = 60;
        int high = 100;           
        int randomScore = randomGenerator.nextInt(high - low) + low;
        int rewardLevel;
        int randomAppropriateness;
        
        if (randomScore < 10) {
            randomAppropriateness = 0;
        }
        else {
            randomAppropriateness = 1;
        }
                
        Connection con = dbConnect.getConnection();

        if (con == null) {
            throw new SQLException(dbConnection);
        }
        
        con.setAutoCommit(false);       
        
        PreparedStatement preparedStatement = con.prepareStatement("select coalesce(reward_id, 0) as reward_id from rewards where player_id = (select id from player where login = ?)");
        
        preparedStatement.setString(1, Util.getPlayerLogin());
        
        ResultSet result = preparedStatement.executeQuery();        
        
        result.next();
        
        rewardLevel = result.getInt("reward_id");
        
        result.close();

        preparedStatement = con.prepareStatement("insert into tweet(text, player_id, destination_id, appropriate, score, created_date) "
                    + "values(?,(select id from player where login = ?),(select id from destination where name = ?),?,?,?)");
        
        preparedStatement.setString(1, this.text);
        preparedStatement.setString(2, Util.getPlayerLogin());
        preparedStatement.setString(3, this.destination);        
        preparedStatement.setInt(4, randomAppropriateness);
        
        switch (rewardLevel) {
            case 1:
                preparedStatement.setInt(5, randomScore);
                break;
            case 2:
                low = 70;
                high = 100;           
                randomScore = randomGenerator.nextInt(high - low) + low;
                preparedStatement.setInt(5, randomScore);
                break;
            case 3:
                low = 80;
                high = 100;           
                randomScore = randomGenerator.nextInt(high - low) + low;
                preparedStatement.setInt(5, randomScore);
                break;
            default:
                low = 1;
                high = 100;           
                randomScore = randomGenerator.nextInt(high - low) + low;
                preparedStatement.setInt(5, randomScore);
        }
                
        preparedStatement.setDate(6, new java.sql.Date(this.createdDate.getTime()));
        preparedStatement.executeUpdate();
                
        con.commit();
        con.close();      
        
        clear();
        
        return INDEX;
    }
    
    public String saveDestination() throws SQLException {
        Connection con = dbConnect.getConnection();

        if (con == null) {
            throw new SQLException(dbConnection);
        }
        
        con.setAutoCommit(false);

        Statement statement = con.createStatement();

        PreparedStatement preparedStatement = con.prepareStatement("insert into destination(name) values(?) "
                + "                                                 ON CONFLICT (name) DO NOTHING;");
        
        preparedStatement.setString(1, this.destination);
        preparedStatement.executeUpdate();
        
        statement.close();
        con.commit();
        con.close();      
        
        return "sendTweet";
    }
    
    public List<Tweet> getTweetList() throws SQLException {
        Connection con = dbConnect.getConnection();
        List<Tweet> tweetList = new ArrayList<>();
        
        if (con == null) {
            throw new SQLException(dbConnection);
        }

        PreparedStatement preparedStatement
                = con.prepareStatement("select text, login as player_login, destination.name as destination_name, tweet.score, tweet.warning, coalesce(reward.value, '') as reward_value, tweet.created_date from "
                        + "tweet join player on tweet.player_id = player.id left join rewards on rewards.player_id = player.id "
                        + " left join reward on rewards.reward_id = reward.id join destination on tweet.destination_id = destination.id where player.login = ?");
        
        preparedStatement.setString(1, Util.getPlayerLogin());
        
        ResultSet result = preparedStatement.executeQuery();        
    
        while (result.next()) {
            Tweet tweet = new Tweet();                 
            
            tweet.setText("<span style='" + result.getString("reward_value") + "'> " + result.getString("text") + "</span>");
            
            tweet.player.setPlayerLogin(result.getString(playerLogin));
            
            tweet.setDestination(result.getString("destination_name"));
            
            tweet.setScore(result.getInt("score"));
            
            tweet.setWarning(result.getInt("warning"));
            
            tweet.setCreatedDate(result.getDate("created_date"));          
   
            //store all data into a List
            tweetList.add(tweet);
        }
        
        result.close();
        con.close();
        
        return tweetList;
    }
    
    public List<Tweet> getComplaintsList() throws SQLException {
        Connection con = dbConnect.getConnection();
        List<Tweet> tweetList = new ArrayList<>();
        
        if (con == null) {
            throw new SQLException(dbConnection);
        }

        PreparedStatement preparedStatement
                = con.prepareStatement("select tweet.id, text, login as player_login, destination.name as destination_name, tweet.score, tweet.appropriate, coalesce(reward.value, '') as reward_value, tweet.created_date from "
                        + "tweet join player on tweet.player_id = player.id left join rewards on rewards.player_id = player.id "
                        + " left join reward on rewards.reward_id = reward.id join destination on tweet.destination_id = destination.id where tweet.appropriate = 0");               
        
        ResultSet result = preparedStatement.executeQuery();        
    
        while (result.next()) {
            Tweet tweet = new Tweet();      
            
            tweet.setId(result.getInt("id"));
            
            tweet.setText("<span style='" + result.getString("reward_value") + "'> " + result.getString("text") + "</span>");
            
            tweet.player.setPlayerLogin(result.getString(playerLogin));
            
            tweet.setDestination(result.getString("destination_name"));
            
            tweet.setScore(result.getInt("score"));
            
            tweet.setAppropriate(result.getInt("appropriate"));
            
            tweet.setCreatedDate(result.getDate("created_date"));          
   
            //store all data into a List
            tweetList.add(tweet);
        }
        
        result.close();
        con.close();
        
        return tweetList;
    }
    
    public List<Tweet> getLeaderList() throws SQLException {
        Connection con = dbConnect.getConnection();
        List<Tweet> leaderList = new ArrayList<>();
        
        if (con == null) {
            throw new SQLException(dbConnection);
        }

        PreparedStatement preparedStatement
                = con.prepareStatement("select login as player_login, sum(tweet.score) as sum_score from "
                        + "tweet join player on tweet.player_id = player.id group by player.id order by sum_score desc");                
        
        ResultSet result = preparedStatement.executeQuery();        
    
        while (result.next()) {
            Tweet tweet = new Tweet();                                         
            
            tweet.player.setPlayerLogin(result.getString(playerLogin));
            
            tweet.setScore(result.getInt("sum_score"));                   
   
            //store all data into a List
            leaderList.add(tweet);
        }
        
        result.close();
        con.close();
        
        return leaderList;
    }

    public void executeUpdateQuery(String query, Integer tweetId) {
        Connection con = dbConnect.getConnection();

        if (con == null) {
            throw new SQLException(dbConnection);
        }
        
        con.setAutoCommit(false);
        
        PreparedStatement preparedStatement = con.prepareStatement(query);
        
        preparedStatement.setInt(1, tweetId);
        preparedStatement.executeUpdate();

        con.commit();
        con.close();
    }
    
    public String sendWarning(int tweetId) {
        executeUpdateQuery("update tweet set warning = 1 where id = ?", tweetId);
                
        return INDEX;
    }
    
    public String deleteTweet(int tweetId) {
        executeUpdateQuery("delete from tweet where id = ?", tweetId);
                
        return INDEX;
    }
    
    public String checkWarning(Integer warning) {
        if (warning != null && warning.equals(1)) {
            return "text-danger";
        }
        
        return "";
    }
    
    public void clear() {
        setText(null);
        setDestination(null);
    }

}
