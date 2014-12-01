package com.codepath.apps.twitterclient.models;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Tweet {

	private String body;
	private long uid;
	private String createdAt;
	private User user;
	private String lifeOfTweet;
	private int retweetCount = 0;
	private static long lastIdx=1;
	
	@SuppressWarnings("deprecation")
	public static Tweet fromJSON (JSONObject jsonObj){
		
		Tweet tweet = new Tweet();
		
		try {
			tweet.body = jsonObj.getString("text").replace("&amp;","&");
			tweet.body = tweet.body.replace("&gt;", ">");
			tweet.uid  = jsonObj.getLong("id");
            tweet.createdAt = jsonObj.getString("created_at");
            tweet.user = User.fromJSON(jsonObj);
            tweet.retweetCount = Integer.parseInt(jsonObj.getString("retweet_count"));
            tweet.lastIdx = Long.parseLong(jsonObj.getString("id"));
            
            //"created_at": "Tue Aug 28 21:16:23 +0000 2012",
            String createdDate = jsonObj.getString("created_at");
            
            Date crtDate = new Date(createdDate);
            //Log.d("debug",crtDate.toString());
            //Toast.makeText(getC, "Hi", Toast.LENGTH_SHORT).show();
            
            Date currDate = new Date(System.currentTimeMillis());

            if (currDate.getDate() == crtDate.getDate()) {
            	//Same date, lets get hours diff
            	int diff = currDate.getHours() - crtDate.getHours();
            	//Log.d("debug","currDate is: "+Integer.toString(currDate.getHours())+
            	//		 "and crdate is: "+Integer.toString(crtDate.getHours()));
            	if (diff > 0) {
            		tweet.setLifeOfTweet(Integer.toString(diff)+"h");
            	} else if(diff == 0){
            		tweet.setLifeOfTweet("0h");
            	} else {
                    tweet.lifeOfTweet = null;
            	} 
            	
            } else {
        		tweet.lifeOfTweet = "1d";
        	}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return tweet;
	}

	public String getBody() {
		return body;
	}

	public long getUid() {
		return uid;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public User getUser() {
		return user;
	}

	public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArr) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>(jsonArr.length());
		
		for (int i=0; i<jsonArr.length();i++){
			JSONObject jsonObj = null;
			Tweet tweet = null;
		  try {
			jsonObj = jsonArr.getJSONObject(i);
		    } catch (JSONException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		    }
		  tweet = fromJSON(jsonObj);
		  if (tweet != null){
			  tweets.add(tweet);
		  }
		  
		  
		}
		return tweets;
		
	}

	public String getLifeOfTweet() {
		return lifeOfTweet;
	}

	public void setLifeOfTweet(String lifeOfTweet) {
		this.lifeOfTweet = lifeOfTweet;
	}

	public int getRetweetCount() {
		return retweetCount;
	}

	public void setRetweetCount(int retweetCount) {
		this.retweetCount = retweetCount;
	}

	public long getLastIdx() {
		return lastIdx;
	}

	public void setLastIdx(long lastIdx) {
		this.lastIdx = lastIdx;
	}


}
