package test;
import static org.junit.Assert.*;
import java.util.*;
import java.text.DateFormat;

import org.junit.Test;

public class TestLoops {

	@Test
	public void testLeaderBoard()
	{
		List<Tweet> actualTweetList = (new Tweet()).getLeaderBoard();
		List<Tweet> expectedTweetList = new ArrayList<>();

		Tweet tweet = new Tweet();                                         
            
        tweet.player.setPlayerLogin("carla");
            
        tweet.setScore(511); 

        expectedTweetList.add(tweet);

        tweet = new Tweet();

        tweet.player.setPlayerLogin("dfalessi");
            
        tweet.setScore(100); 

        expectedTweetList.add(tweet);

        tweet = new Tweet();

        tweet.player.setPlayerLogin("hu");
            
        tweet.setScore(88); 

        expectedTweetList.add(tweet);

        tweet = new Tweet();

        tweet.player.setPlayerLogin("suki");
            
        tweet.setScore(6); 

        expectedTweetList.add(tweet);

		assertArrayEquals(expectedTweetList.toArray(), actualTweetList.toArray());
	}
	
	@Test 
	public void testComplaintsList()
	{
		List<Tweet> actualTweetList = (new Tweet()).getLeaderBoard();
		List<Tweet> expectedTweetList = new ArrayList<>();
		String inputString;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date inputDate;

		Tweet tweet = new Tweet();      
            
        tweet.setId(4);
        
        tweet.setText("<span style='color:red'> Clovis is the best</span>");
        
        tweet.player.setPlayerLogin("carla");
        
        tweet.setDestination("Clovis, CA");
        
        tweet.setScore(96);
        
        tweet.setAppropriate(0);

        inputString = '2017-06-05';

        inputDate = dateFormat.parse(inputString);
        
        tweet.setCreatedDate(inputDate);          

        expectedTweetList.add(tweet);

        assertArrayEquals(expectedTweetList.toArray(), actualTweetList.toArray());
	}

	@Test 
	public void testPlayerList()
	{
		List<Player> actualPlayerList = (new player()).getPlayerList().subList(0, 1);
		List<Player> expectedPlayerList = new ArrayList<>();
		String inputString;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date inputDate;

		Player player = new Player();
            
        player.setPlayerLogin("suki");
        player.setFirstName(result.getString("Ava"));
        player.setLastName(result.getString("Gonzalez"));
        player.setEmail(result.getString("ac.turpis.egestas@bommy.edu"));
        player.setPostalAddress(result.getString("Ap #136-1258 Lorem, St."));

        inputString = '2016-08-01';

        inputDate = dateFormat.parse(inputString);
        
        player.setCreatedDate(inputDate);          

        expectedPlayerList.add(player);

        assertArrayEquals(expectedTweetList.toArray(), actualTweetList.toArray());
	}
}