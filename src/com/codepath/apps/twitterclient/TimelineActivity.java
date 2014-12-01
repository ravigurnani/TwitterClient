package com.codepath.apps.twitterclient;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.twitterclient.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TimelineActivity extends Activity {
    private TwitterClient client;
    private ArrayList<Tweet> tweetArr;
    private ArrayAdapter<Tweet> tweetAdap;
    ListView lvTweet;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		client = TwitterApp.getRestClient();
		parseTimelineTweets();
		
		lvTweet = (ListView) findViewById(R.id.lvTweets);
		tweetArr = new ArrayList<Tweet>();
		tweetAdap = new TweetArrayAdapter(this, tweetArr);
		//tweetAdap = new ArrayAdapter<Tweet>(this, android.R.layout.simple_list_item_1, tweetArr);
		lvTweet.setAdapter(tweetAdap);
		
		//Endless Scrolling
		lvTweet.setOnScrollListener(new EndlessScrollListener() {
		    @Override
		    public void onLoadMore(int page, int totalItemsCount) {
	                // Triggered only when new data needs to be appended to the list
	                // Add whatever code is needed to append new items to your AdapterView
		        //customLoadMoreDataFromApi(page);
		    	//Log.d("debug","Value of page is"+Integer.toString(page));
		    	//Log.d("debug","Value of total items is"+Integer.toString(totalItemsCount));
		    	parseTimelineTweets();
	                // or customLoadMoreDataFromApi(totalItemsCount); 
		    }
	        });
	}
	
//	@Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.photos, menu);
//        return true;
//    }
	
	
	public void parseTimelineTweets() {

		//Toast.makeText(this, "Hi", Toast.LENGTH_LONG).show();
		client.HomeTimelineClient(new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(int arg0, JSONArray jsonArr) {

				Log.d("debug",jsonArr.toString());
				tweetAdap.addAll(Tweet.fromJSONArray(jsonArr));
			}
			
			@Override
			public void onFailure(Throwable evnt, JSONArray jsonArr) {
			
				Log.d("debug",evnt.toString());
				Log.d("debug",jsonArr.toString());
				
				
			}

			
			
		});
	}
	
	@Override
  public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.photos, menu);
      return true;
  }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.miComposeTweet:
	            //composeMessage();
	        	Intent i = new Intent(this, ComposetweetActivity.class);
	   		    startActivity(i);
	        	//Toast.makeText(this, "Compose Tweet", Toast.LENGTH_SHORT).show();
	            return true;
	        
	        default:
	        	Toast.makeText(this, "Hitting Default", Toast.LENGTH_SHORT).show();
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	
}
