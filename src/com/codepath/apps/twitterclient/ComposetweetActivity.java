package com.codepath.apps.twitterclient;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

public class ComposetweetActivity extends Activity {
	private ImageView ivProfilePic;
	private TextView tvUserName;
	private EditText etTweet;
	private TwitterClient client;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_composetweet);

		//Get the views for the layout
		ivProfilePic = (ImageView) findViewById(R.id.ivComposeProfilePic);
		tvUserName = (TextView) findViewById(R.id.tvComposeUserName);
		etTweet = (EditText) findViewById(R.id.etTweetBox);
		
		//Initialize the api client
		client = TwitterApp.getRestClient();
		
	}
	
	public void postTweet(View v){

		//get the tweet text
		String tweetTxt = etTweet.getText().toString();
		
		if (!tweetTxt.isEmpty()){
			//Toast.makeText(this, "Post Tweet", Toast.LENGTH_SHORT).show();
			
			client.PostTweetClient(tweetTxt, new JsonHttpResponseHandler(){

				@Override
				public void onFailure(Throwable arg0, JSONObject json) {
					// TODO Auto-generated method stub
					//super.onFailure(arg0, arg1);
					Log.d("debug", json.toString())	;
				}

				@Override
				public void onSuccess(int arg0, JSONObject jsonObj) {
					// TODO Auto-generated method stub
					//super.onSuccess(arg0, arg1);
				    Log.d("debug", jsonObj.toString())	;
				    
				   // Toast.makeText(this, "Tweet Posted", Toast.LENGTH_SHORT).show();
				    
				    //Lets go back to parent Timeline activity
				    loadParent();
				    //Intent i = new Intent(this, TimelineActivity.class);
				    
				}
				
				
			});
			
			//invoke the client to post this
			
		}
	}
	
	public void loadParent(){
		
		Toast.makeText(this, "Tweet Posted", Toast.LENGTH_SHORT).show();
		
		Intent i = new Intent(this, TimelineActivity.class);
		startActivity(i);
	}

}
