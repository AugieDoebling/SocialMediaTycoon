package test;
import java.sql.SQLException;
import static org.junit.Assert.*;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import junit.framework.TestCase;

import smt.Player;
import smt.Tweet;

import org.junit.Test;

public class LoopsTest extends TestCase{

        public void setTweet(Tweet tweet, String playerLogin, int score) {
                tweet.getPlayer().setPlayerLogin(playerLogin);
            
                tweet.setScore(score); 
        }

        @Test
        public void testLeaderBoard() throws SQLException
        {
                List<Tweet> actualTweetList = (new Tweet()).getLeaderList();
                List<Tweet> expectedTweetList = new ArrayList<>();

                Tweet tweet = new Tweet();                                         
                setTweet(tweet, "carla", 511);
                expectedTweetList.add(tweet);

                tweet = new Tweet();
                setTweet(tweet, "dfalessi", 100);
                expectedTweetList.add(tweet);

                tweet = new Tweet();
                setTweet(tweet, "hu", 88);
                expectedTweetList.add(tweet);

                tweet = new Tweet();
                setTweet(tweet, "suki", 6);
                expectedTweetList.add(tweet);

                assertEquals(expectedTweetList.get(0).getId(), actualTweetList.get(0).getId());
                assertEquals(expectedTweetList.get(1).getId(), actualTweetList.get(1).getId());
                assertEquals(expectedTweetList.get(2).getId(), actualTweetList.get(2).getId());
                assertEquals(expectedTweetList.get(3).getId(), actualTweetList.get(3).getId());
        }
        
        @Test 
        public void testComplaintsList() throws SQLException
        {
                List<Tweet> actualTweetList = (new Tweet()).getLeaderList().subList(0, 1);
                List<Tweet> expectedTweetList = new ArrayList<>();
                String inputString;
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date inputDate;

                Tweet tweet = new Tweet();      
            
                tweet.setId(4);
                
                tweet.setText("<span style='color:red'> Clovis is the best</span>");
                
                tweet.getPlayer().setPlayerLogin("carla");
                
                tweet.setDestination("Clovis, CA");
                
                tweet.setScore(96);
                
                tweet.setAppropriate(0);

                inputString = "2017-06-05";

                try {
					inputDate = dateFormat.parse(inputString);
				} catch (ParseException e) {
					fail();
					return;
				}
                
                tweet.setCreatedDate(inputDate);          

                expectedTweetList.add(tweet);

                assertEquals(expectedTweetList.get(0).getPlayer().getId(), 
                        actualTweetList.get(0).getPlayer().getId());
        }

        @Test 
        public void testPlayerList() throws SQLException
        {
                List<Player> actualPlayerList = (new Player()).getPlayerList().subList(2, 3);
                List<Player> expectedPlayerList = new ArrayList<>();
                String inputString;
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date inputDate;

                Player player = new Player();
            
                player.setPlayerLogin("suki");
                player.setFirstName("Ava");
                player.setLastName("Gonzalez");
                player.setEmail("ac.turpis.egestas@bommy.edu");
                player.setPostalAddress("Ap #136-1258 Lorem, St.");

                inputString = "2016-08-01";

                try {
					inputDate = dateFormat.parse(inputString);
				} catch (ParseException e) {
					fail();
					return;
				}
                
                player.setCreatedDate(inputDate);          

                expectedPlayerList.add(player);

                assertEquals(expectedPlayerList.size(), actualPlayerList.size());
                assertEquals(expectedPlayerList.get(0).getPlayerLogin(), 
                        actualPlayerList.get(0).getPlayerLogin());
        }
}
