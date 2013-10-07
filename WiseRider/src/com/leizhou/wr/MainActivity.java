package com.leizhou.wr;

import com.leizhou.wr.DAO.PublicDataBox;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends Activity implements LocationListener {
	//private static final String MAP_URL = "file:///android_asset/basic.html";
	//private   WebView webView;
	private  Location mostRecentLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		getLocation();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.setoffer:
	    {
	    	//TODO set an offer
	    	((PublicDataBox) getApplication()).setRouteModel("offer");
	    	
	    	
	        android.app.FragmentManager fm = getFragmentManager();
	        SetDirectionDialog setOfferDialog = new SetDirectionDialog();
	        //testDialog.setMostRecentLocation(mostRecentLocation);
	        setOfferDialog.setModel(true);
	        setOfferDialog.setRetainInstance(true);
	        setOfferDialog.setCancelable(false);
	        setOfferDialog.show(fm, "fragment_name");
	    }
	      break;
	    case R.id.request:{
	    	//TODO request staff
	    	((PublicDataBox) getApplication()).setRouteModel("request");
	    	
	        android.app.FragmentManager fm = getFragmentManager();
	        SetDirectionDialog setOfferDialog = new SetDirectionDialog();
	        //testDialog.setMostRecentLocation(mostRecentLocation);
	        setOfferDialog.setModel(false);
	        setOfferDialog.setRetainInstance(true);
	        setOfferDialog.setCancelable(false);
	        setOfferDialog.show(fm, "fragment_name");
	    }
	    	
	      break;
	      
	    case R.id.action_login:{
	    
	    	//do login
	        android.app.FragmentManager fm2 = getFragmentManager();
	        SetLoginDialog loginDialog = new SetLoginDialog();
	        //testDialog.setMostRecentLocation(mostRecentLocation);
	        loginDialog.setRetainInstance(true);
	        loginDialog.setCancelable(false);
	        loginDialog.show(fm2, "fragment_name");
	   
	    }
	      break;

	    default:
	      break;
	    }
		return true;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * The Location Manager manages location providers. This code searches for
	 * the best provider of data (GPS, WiFi/cell phone tower lookup, some other
	 * mechanism) and finds the last known location.
	 **/
	private void getLocation() {

		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		String provider = locationManager.getBestProvider(criteria, false);

		// In order to make sure the device is getting location, request
		// updates. locationManager.requestLocationUpdates(provider, 1, 0,
		// this);
		mostRecentLocation = locationManager.getLastKnownLocation(provider);

		// Log.i("mostRecentLocation.getLatitude()= ",String.valueOf(mostRecentLocation.getAltitude()));
		// store to global data box for other activity
		if(mostRecentLocation!=null){
		((PublicDataBox) getApplication())
				.setMostRecentLocation(mostRecentLocation);
		}

	}
	/** Sets the mostRecentLocation object to the current location of the device **/
	@Override
	public void onLocationChanged(Location location) {
		mostRecentLocation = location;
		if(mostRecentLocation!=null){
		((PublicDataBox) getApplication())
				.setMostRecentLocation(mostRecentLocation);
		}
	}

	/**
	 * The following methods are only necessary because WebMapActivity
	 * implements LocationListener
	 **/
	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

}
