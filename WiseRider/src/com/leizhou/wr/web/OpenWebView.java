package com.leizhou.wr.web;
import org.json.JSONException;
import org.json.JSONObject;

import com.leizhou.wr.*;
import com.leizhou.wr.DAO.*;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class OpenWebView extends Activity {
	WebView myWebView;
	private Location mostRecentLocation;
	private static final String MAP_URL_start = "file:///android_asset/SelectStartAddress.html";
	private static final String MAP_URL_end = "file:///android_asset/endaddress_elevator.html";
	boolean isStartAddress;
	private Route userRoute;

	public Route getUserRoute() {
		return userRoute;
	}
	public void setUserRoute(Route userRoute) {
		this.userRoute = userRoute;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_webview);
		
		Bundle extras = getIntent().getExtras();
		isStartAddress=extras.getBoolean("isStartAddress");
		setWebView();

		// get user's most recent location from public data box
		mostRecentLocation = ((PublicDataBox) getApplication())
				.getMostRecentLocation();
	}
	private void setWebView(){
		
		WebView myWebView = (WebView) findViewById(R.id.singleWebView);
		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.setWebViewClient(new WebViewClient());
		if(isStartAddress){
			myWebView.loadUrl(MAP_URL_start);
		}else{
			myWebView.loadUrl(MAP_URL_end);	
		}
		
		
		/** Allows JavaScript calls to access application resources **/
		myWebView.addJavascriptInterface(new JavaScriptInterface(this),
				"android");
		
	}

	private class JavaScriptInterface {
		Context mContext;

		public JavaScriptInterface(Context c) {
			mContext = c;
		}


		@JavascriptInterface
		public void settempStartAddress(String address) {
			((PublicDataBox) getApplication()).setTempStartAddress(address);

		}

		@JavascriptInterface
		public void settempEndAddress(String address) {
			((PublicDataBox) getApplication()).setTempEndAddress(address);
		}

		@JavascriptInterface
		public double getLatitude() {
			//Log.i("message has been sent",String.valueOf(mostRecentLocation.getLatitude()));
			return mostRecentLocation.getLatitude();
		}

		@JavascriptInterface
		public double getLongitude() {
			// Log.i("message has been sent","EEEEE");
			return mostRecentLocation.getLongitude();
		}


		/** Show a toast from the web page */
		@JavascriptInterface
		public void showToast(String toast) {
			Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();

		}

		@JavascriptInterface
		public void saveStartPointTopublicBox(float lat, float lng) {

			((PublicDataBox) getApplication()).setStartPoint(new Points(
					(int) (lat * 1E6), (int) (lng * 1E6)));
			Toast.makeText(mContext, "start point saved", Toast.LENGTH_SHORT).show();
		}

		@JavascriptInterface
		public void saveEndPointTopublicBox(float lat, float lng) {

			((PublicDataBox) getApplication()).setEndPoint(new Points(
					(int) (lat * 1E6), (int) (lng * 1E6)));
			Toast.makeText(mContext, "end point saved", Toast.LENGTH_SHORT).show();
		}

		@JavascriptInterface
		public double getStartPointLat() {
			// Toast.makeText(mContext,
			// "saved "+String.valueOf((((PublicDataBox)
			// getApplication()).getAddressStart().getLatitudeE6()/1E6)),
			// Toast.LENGTH_SHORT).show();
			return (double) (((PublicDataBox) getApplication()).getStartPoint()
					.getLatE6() / 1E6);
		}

		@JavascriptInterface
		public double getStartPointLng() {

			return (double) (((PublicDataBox) getApplication()).getStartPoint()
					.getLngE6() / 1E6);
		}

		@JavascriptInterface
		public void finishActivie() {
			finish();
		}

	}
}
