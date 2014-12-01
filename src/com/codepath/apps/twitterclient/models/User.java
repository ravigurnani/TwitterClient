package com.codepath.apps.twitterclient.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

	private String name;
	private long uid;
	private String screenName;
	private String profileImageUrl;
	private int favCount = 0;

	
	public static User fromJSON(JSONObject jsonObj) {
	
		User user = new User();
	
		try {
			user.name=jsonObj.getJSONObject("user").getString("name");
			user.uid = jsonObj.getJSONObject("user").getLong("id");
			user.screenName = jsonObj.getJSONObject("user").getString("screen_name");
			user.profileImageUrl = jsonObj.getJSONObject("user").getString("profile_image_url");
			user.favCount = Integer.parseInt(jsonObj.getJSONObject("user").getString("favourites_count"));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return user;
		
	}

	public String getName() {
		return name;
	}

	public long getUid() {
		return uid;
	}

	public String getScreenName() {
		return screenName;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public int getFavCount() {
		return favCount;
	}

	public void setFavCount(int favCount) {
		this.favCount = favCount;
	}

}
