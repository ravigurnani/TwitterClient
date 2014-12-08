package com.codepath.apps.twitterclient;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.apps.twitterclient.fragments.HomeTimelineFragment;
import com.codepath.apps.twitterclient.fragments.MentionsTimelineFragment;
import com.codepath.apps.twitterclient.listeners.FragmentTabListener;

public class TimelineActivity extends FragmentActivity {

   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		
		setupTabs();
	}
	
//	@Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.photos, menu);
//        return true;
//    }
	
	
	
		
		private void setupTabs() {
			ActionBar actionBar = getActionBar();
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			actionBar.setDisplayShowTitleEnabled(true);
			

			Tab tab1 = actionBar
				.newTab()
				.setText("Home")
				.setIcon(R.drawable.ic_home_tab)
				.setTag("HomeTimelineFragment")
				.setTabListener(
				new FragmentTabListener<HomeTimelineFragment>(R.id.flContainer, this, "home",HomeTimelineFragment.class));

			actionBar.addTab(tab1);
			actionBar.selectTab(tab1);

			Tab tab2 = actionBar
				.newTab()
				.setText("Mentions")
				.setIcon(R.drawable.ic_mentions_tag)
				.setTag("MentionsTimelineFragment")
				.setTabListener(
				new FragmentTabListener<MentionsTimelineFragment>(R.id.flContainer, this, "mentions",MentionsTimelineFragment.class));

			actionBar.addTab(tab2);
		}
		
	@Override
  public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.photos, menu);
      getMenuInflater().inflate(R.menu.profile,menu);
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
	
	public void onProfileView(MenuItem mi){
		
		Intent i = new Intent(this, ProfileActivity.class);
		startActivity(i);
	}
	
	
}
