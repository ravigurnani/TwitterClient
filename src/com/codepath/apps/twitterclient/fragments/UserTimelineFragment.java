package com.codepath.apps.twitterclient.fragments;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.twitterclient.TwitterApp;
import com.codepath.apps.twitterclient.TwitterClient;
import com.codepath.apps.twitterclient.models.Tweet;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

public class UserTimelineFragment extends TweetsListFragment {

	private TwitterClient client;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//Endless Scrolling
		client = TwitterApp.getRestClient();
		client.getUserTimeline(new JsonHttpResponseHandler(){
			
			@Override
			public void onSuccess(int arg0, JSONArray jsonArr) {

				Log.d("debug",jsonArr.toString());
				//tweetAdap.addAll(Tweet.fromJSONArray(jsonArr));
				addAllTweets(Tweet.fromJSONArray(jsonArr));
			}
			
			@Override
			public void onFailure(Throwable evnt, JSONArray jsonArr) {
			
				Log.d("debug",evnt.toString());
				Log.d("debug",jsonArr.toString());
				
				
			}
		});
		
	}
}
