package com.codepath.apps.twitterclient.fragments;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.twitterclient.EndlessScrollListener;
import com.codepath.apps.twitterclient.TwitterApp;
import com.codepath.apps.twitterclient.TwitterClient;
import com.codepath.apps.twitterclient.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MentionsTimelineFragment extends TweetsListFragment {
private TwitterClient client;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		client = TwitterApp.getRestClient();
		parseTimelineTweets();
		//Endless Scrolling
//		lvTweet.setOnScrollListener(new EndlessScrollListener() {
//		    @Override
//		    public void onLoadMore(int page, int totalItemsCount) {
//	                // Triggered only when new data needs to be appended to the list
//	                // Add whatever code is needed to append new items to your AdapterView
//		        //customLoadMoreDataFromApi(page);
//		    	//Log.d("debug","Value of page is"+Integer.toString(page));
//		    	//Log.d("debug","Value of total items is"+Integer.toString(totalItemsCount));
//		    	parseTimelineTweets();
//	                // or customLoadMoreDataFromApi(totalItemsCount); 
//		    }
//	        });
	}

	public void parseTimelineTweets() {

		//Toast.makeText(this, "Hi", Toast.LENGTH_LONG).show();
		client.MentionsTimelineClient(new JsonHttpResponseHandler() {

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
