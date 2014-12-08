package com.codepath.apps.twitterclient.fragments;

import java.util.ArrayList;

import org.json.JSONArray;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codepath.apps.twitterclient.EndlessScrollListener;
import com.codepath.apps.twitterclient.R;
import com.codepath.apps.twitterclient.TweetArrayAdapter;
import com.codepath.apps.twitterclient.TwitterApp;
import com.codepath.apps.twitterclient.TwitterClient;
import com.codepath.apps.twitterclient.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TweetsListFragment extends Fragment {

	private ArrayList<Tweet> tweetArr;
    private ArrayAdapter<Tweet> tweetAdap;
    public ListView lvTweet;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
		tweetArr = new ArrayList<Tweet>();
		tweetAdap = new TweetArrayAdapter(getActivity(), tweetArr);
		//tweetAdap = new ArrayAdapter<Tweet>(this, android.R.layout.simple_list_item_1, tweetArr);
		
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragments_tweets_list, container, false);
		lvTweet = (ListView) v.findViewById(R.id.lvTweets);
		
		lvTweet.setAdapter(tweetAdap);
		
		
		

		return v;
	}
	


	public void addAllTweets(ArrayList<Tweet> allTweets){
		
		tweetAdap.addAll(allTweets);
	}

	

}

