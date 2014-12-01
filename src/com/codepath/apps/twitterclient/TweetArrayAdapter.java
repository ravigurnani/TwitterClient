package com.codepath.apps.twitterclient;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.twitterclient.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetArrayAdapter extends ArrayAdapter<Tweet> {

	public TweetArrayAdapter(Context context, List<Tweet> tweets){
		super(context, 0, tweets);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Tweet tweet = getItem(position);
		
		View v = null;
		
		if (convertView == null){
			LayoutInflater inflator = LayoutInflater.from(getContext());
			v = inflator.inflate(R.layout.tweet_item, parent, false);
		} else {
			v = convertView;
		}
        //Toast.makeText(getContext(), "Hi", Toast.LENGTH_SHORT);
		ImageView profilePic = (ImageView) v.findViewById(R.id.ivProfilePic);
		TextView tvName = (TextView) v.findViewById(R.id.tvName);
		TextView tvScreenName = (TextView) v.findViewById(R.id.tvUserScreenName);
		TextView tvTweetLife = (TextView) v.findViewById(R.id.tvTweetLife);
		TextView tvTweetBody  = (TextView) v.findViewById(R.id.tvTweetBody)	;
		TextView tvRetweetCnt = (TextView) v.findViewById(R.id.tvRetweetCnt);
		TextView tvFavCnt = (TextView) v.findViewById(R.id.tvFavoriteCnt);
		
		//nullify the image view
		profilePic.setImageResource(android.R.color.transparent);
		
		//Instantiate the image loader
		ImageLoader imgLoader = ImageLoader.getInstance();
		
		//populate the data to the views
		if (tweet.getUser().getProfileImageUrl() != null) {
		  imgLoader.displayImage(tweet.getUser().getProfileImageUrl(), profilePic);
		}
		if (tweet.getUser().getScreenName() != null ){
		  tvName.setText(tweet.getUser().getName());
         // tvScreenName.Sty
		}
		
	    tvScreenName.setText("@"+tweet.getUser().getScreenName());
	    
	    if (tweet.getLifeOfTweet() != null && tweet.getLifeOfTweet() != "Tweet Life"){
	    	tvTweetLife.setText(tweet.getLifeOfTweet());
	    }
	    
	    
		if (tweet.getBody() != null){
		  tvTweetBody.setText(tweet.getBody());
		}
		
		tvRetweetCnt.setText(Integer.toString(tweet.getRetweetCount()));
		tvFavCnt.setText(Integer.toString(tweet.getUser().getFavCount()));
		
		return v;
	}
	
}
