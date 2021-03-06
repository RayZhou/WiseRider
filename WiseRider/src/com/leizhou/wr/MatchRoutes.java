package com.leizhou.wr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import com.leizhou.wr.DAO.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MatchRoutes extends Activity {

	private static final String MAP_URL = "file:///android_asset/MatchingRoutes.html";
	private static final String ROUTES_XML = "/routes.xml";
	private String res_distance = "1";
	private WebView myWebView;
	private Person UserRoute=new Person();
	private ArrayList<Person> MatchedPassengerList=new ArrayList<Person>();
	private ArrayList<Person> MatchedDriverList=new ArrayList<Person>();
	private double fuelConsumedFactor=0;
	
	private static final String[] lakes = {
        "Superior",
        "Victoria",
        };

	// ArrayList<Route> driverroute = new ArrayList<Route>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_webview);
		
		Bundle extras = getIntent().getExtras();
		fuelConsumedFactor=extras.getDouble("fuelConsumedFactor");
		((PublicDataBox) getApplication()).setUserCarFuelFactor(fuelConsumedFactor);
		setupData();

		myWebView = (WebView) findViewById(R.id.singleWebView);
		setWebView();

		drawRoutesOnMap();

	}

	private void drawRoutesOnMap() {
		// TODO Auto-generated method stub
		// */
		// this is a test

	}

	private void setupData() {
		//loading user's route
		
		Points start=((PublicDataBox) getApplication()).getStartPoint();
		Points end=((PublicDataBox) getApplication()).getEndPoint();
		UserRoute=new Person(((PublicDataBox) getApplication()).getUsername(),
				((PublicDataBox) getApplication()).isUserModel(),
				start,end);
		UserRoute.setTravalDistance(((PublicDataBox) getApplication()).getDistanceFromStartToEnd());
		Preferences myPrefer=new Preferences(false, true);
		UserRoute.setMyPreference(myPrefer);
		CarType mycar=(((PublicDataBox) getApplication()).getUserCar());
		mycar.calculate();
		UserRoute.setMyCar(mycar);
		UserRoute.cal_originalCost();
		
		
		//seting some fake date into requestList
		//4 request into requestList
		((PublicDataBox) getApplication()).clearOfferList();
		((PublicDataBox) getApplication()).clearRequestList();
		
		start=new Points(-45.863447,170.523348);
		end=new Points(-45.877222,170.49978);
		
		Person peter=new Person("Peter",false,start,end);
		peter.setTravalDistance(3.3);
		
		myPrefer=new Preferences(false, true);
		peter.setMyPreference(myPrefer);
		
		mycar=new CarType(true);//Petrol car
		mycar.setFuelConsumeFactor(7);// 7 liter per 100 KM;
		mycar.calculate();
		peter.setMyCar(mycar);
		
		peter.cal_originalCost();
		
		//Route route1=new Route("request",peter);
		((PublicDataBox) getApplication()).addRequestToList(peter);
		Log.i(" ADD one route", String.valueOf(((PublicDataBox) getApplication()).getRequestListSize()));
		
		
		start=new Points(-45.862759,170.519743);
		end=new Points(-45.899447,170.506253);
		
		
		Person david=new Person("David",true,start,end);
		david.setTravalDistance(5.4);
		
		//Preferences myPrefer=new Preferences(false, true);
		david.setMyPreference(myPrefer);
		
		//CarType mycar=new CarType(true);//Petrol car
		mycar.setFuelConsumeFactor(7);// 7 liter per 100 KM;
		mycar.calculate();
		david.setMyCar(mycar);
		
		david.cal_originalCost();
		((PublicDataBox) getApplication()).addRequestToList(david);
		Log.i(" ADD two route", String.valueOf(((PublicDataBox) getApplication()).getRequestListSize()));
		/*
		start=new Points(-45.866346,170.506082);
		end=new Points(-45.912646,170.481534);
		Person amy=new Person("Amy",start,end);
		amy.setTravalDistance(6.6);
		Route route3=new Route("request",amy);
		((PublicDataBox) getApplication()).addRequestToList(route3);
		Log.i(" ADD one route", String.valueOf(((PublicDataBox) getApplication()).getRequestListSize()));
		
		start=new Points(-45.858346,170.516082);
		end=new Points(-45.852646,170.281534);
		Person john=new Person("John",start,end);
		john.setTravalDistance(24);
		Route route4=new Route("request",john);
		((PublicDataBox) getApplication()).addRequestToList(route4);
		Log.i(" ADD one route", String.valueOf(((PublicDataBox) getApplication()).getRequestListSize()));
*/

	}

	private Route ReadRouteByID(String filename, int id, String routeModel) {
		File myFile = new File(getExternalFilesDir(null), filename);
		com.leizhou.wr.web.RoutesXML routes = new com.leizhou.wr.web.RoutesXML(
				getExternalFilesDir(null).toString(), filename);
		Route rs = new Route();

		if (myFile.exists()) {
			FileInputStream fIn;
			try {
				fIn = new FileInputStream(myFile);
				rs = routes.readRouteByID(fIn, id, routeModel);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				routes.createXML();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

	private void setWebView() {

		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.setWebViewClient(new WebViewClient());
		myWebView.loadUrl(MAP_URL);

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
		public String usersLocation() throws JSONException {
			// current user location
			Location mostRecentLocation = ((PublicDataBox) getApplication())
					.getMostRecentLocation();

			JSONObject json = new JSONObject();
			json.put("lat", mostRecentLocation.getLatitude());
			json.put("lng", mostRecentLocation.getLongitude());

			return (json.toString());

		}

		/** Show a toast from the web page */
		@JavascriptInterface
		public void showToast(String toast) {
			Toast.makeText(mContext, String.valueOf(toast), Toast.LENGTH_SHORT)
					.show();

		}

		@JavascriptInterface
		public void finishActivie() {
			finish();
		}

		@JavascriptInterface
		public void receiveDistance(String rs) {

			// Log.i("res_distance", String.valueOf(rs));
			res_distance = String.valueOf(rs);
			// ((PublicDataBox)
			// getApplication()).setTestInt(Integer.parseInt(rs.toString()));
			// Log.i("res_distance",res_distance);

		}

	}
	//for driver to find passenger
	private void findPassenger() {
		// persons in car is 2, driver and a passenger
		int numberPerson = 2;
		double detourDistance = 0;
		// its description see bottom of function
		int condition = 2;
		if (UserRoute.isDriver()) {
			// if user is driver doing below

			Person temp = new Person();
			// find matched passenger from list
			// get list of passengers

			// firstly to get detour distance
			detourDistance = 0;
			temp = ((PublicDataBox) getApplication()).getRequestByIndex(0);
			// calculate detourCost for driver
			calDistance(UserRoute.getDeparturePoint(), temp.getDeparturePoint());
			// waiting results
			SystemClock.sleep(1000);
			detourDistance += Double.parseDouble(res_distance);

			calDistance(UserRoute.getArrivalPoint(), temp.getArrivalPoint());
			// waiting results
			SystemClock.sleep(1000);
			detourDistance += Double.parseDouble(res_distance);
			// then calculate detour cost for driver
			UserRoute.cal_detourCost(detourDistance/1000);

			Log.i("detourDistance = = ", String.valueOf(detourDistance/1000));
			Log.i("orignial travel distance== ", String.valueOf(UserRoute.getTravalDistance()));
		

			// calculate sharedCost for driver, sharedDistance is passenger's
			// original cost
			UserRoute.cal_sharedCost(temp.getTravalDistance(), numberPerson);
			//SystemClock.sleep(1000);

			 Log.i("sharedDistance = = ",
			 String.valueOf(temp.getTravalDistance()));
			Log.i("sharedCost = = ",
			String.valueOf(UserRoute.getSharedCost().getCarbonConsumed()));

			// if detourCost+sharedCost < driver.origialCost
			// condition 1:
			// if driver's original total carbon omission > shared + detoured
			// carbon omission
			// then put this one into matched list.
			if (condition == 1) {
				if (UserRoute.getOriginalCost().getCarbonConsumed() > (UserRoute
						.getDetourCost().getCarbonConsumed() + UserRoute
						.getSharedCost().getCarbonConsumed())) {
					MatchedPassengerList.add(temp);
					Log.i("ADD    a  Passenger", temp.getUsername());
				}
			}
			// condition 2:
			// if the sum of driver's original carbon omission and passengers' >
			// driver's shared + detoured carbon omission
			// then put this one into matched list.
			if (condition == 2) {

				Log.i(" oinignal Cost ===== ", String.valueOf(UserRoute
						.getOriginalCost().getCarbonConsumed()));
				Log.i(" shared cost ===== ", String.valueOf(UserRoute
						.getSharedCost().getCarbonConsumed()));
				Log.i(" detoured cost ===== ", String.valueOf(UserRoute
						.getDetourCost().getCarbonConsumed()));

				if ((UserRoute.getOriginalCost().getCarbonConsumed() + temp
						.getOriginalCost().getCarbonConsumed()) > (UserRoute
						.getDetourCost().getCarbonConsumed() + UserRoute
						.getSharedCost().getCarbonConsumed())) {
					MatchedPassengerList.add(temp);
					Log.i("ADD    a  Passenger", temp.getUsername());
				}
			}
			// only for testing.
			AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
			builderSingle.setIcon(R.drawable.ic_launcher);
			builderSingle.setTitle("if there any, Select One Passenger:-");
			final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
					this, android.R.layout.select_dialog_singlechoice);
			if (MatchedPassengerList.size() > 0) {
				arrayAdapter.add(MatchedPassengerList.get(0).getUsername() + "  Carbon Cost= "+ 
						UserRoute.getSharedCost().getCarbonConsumed()+" + "+UserRoute.getDetourCost().getCarbonConsumed());
			}else{
				arrayAdapter.add("NOT FOUND MATCH");
			}

			builderSingle.setNegativeButton("cancel",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});

			builderSingle.setAdapter(arrayAdapter,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									
									dialog.dismiss();
									
								}
							};

						}
					});
			builderSingle.show();
			drawPathonMap(0);
		}

	}
	private void calDistance(Points From, Points To){
		JSONObject json = new JSONObject();
		
		// detour distance from driver's departure to passenger's 
		try {
			json.put("depalat", From.getLatDouble());
			json.put("depalng", From.getLngDouble());
			json.put("dstlat", To.getLatDouble());
			json.put("dstlng", To.getLngDouble());
			//send to WebView to calculate, result back to res_distance
			myWebView.loadUrl("javascript:caldistance(" + "'"
					+ json.toString() + "'" + ")");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void findRequestMatch() {

		
		int shortestpath = Integer.MAX_VALUE, index = 0;
		try {
			int detourDistance = 0;
			Person temp = new Person();
			// find shortest path between userroute.startpoint,
			// ,WayPoint[request.start point, request.end point],
			// userroute,endpoint
			int size = ((PublicDataBox) getApplication()).getRequestListSize();
			Log.i("size()===", String.valueOf(size));
			if (size > 0) {
				for (int i = 0; i < size; ++i) {
					JSONObject json = new JSONObject();
					JSONObject json2 = new JSONObject();
					JSONObject json3 = new JSONObject();

					temp = ((PublicDataBox) getApplication())
							.getRequestByIndex(i);

					// detour distance from driver's departure to passenger's 
					json.put("depalat", UserRoute.getDeparturePoint().getLatDouble());
					json.put("depalng", UserRoute.getDeparturePoint().getLngDouble());
					json.put("dstlat", temp.getDeparturePoint().getLatDouble());
					json.put("dstlng", temp.getDeparturePoint().getLngDouble());

					myWebView.loadUrl("javascript:caldistance(" + "'"
							+ json.toString() + "'" + ")");
					SystemClock.sleep(1000);

					detourDistance += Integer.parseInt(res_distance);
					// Log.i("json res_distance ",String.valueOf(temp_short));
					//detour distance from driver's arrivalPoint to passengers'
					json2.put("dstlat", UserRoute.getArrivalPoint().getLatDouble());
					json2.put("dstlat", UserRoute.getArrivalPoint().getLngDouble());
					json2.put("depalat", temp.getArrivalPoint().getLatDouble());
					json2.put("depalng", temp.getArrivalPoint().getLngDouble());

					myWebView.loadUrl("javascript:caldistance(" + "'"
							+ json2.toString() + "'" + ")");
					// Log.i("res_distance ",res_distance);
					detourDistance += Integer.parseInt(res_distance);
					
					//shared distance with passenger
					json3.put("dstlat", temp.getArrivalPoint().getLatDouble());
					json3.put("dstlng", temp.getArrivalPoint().getLngDouble());
					json3.put("depalat", temp.getDeparturePoint().getLatDouble());
					json3.put("depalng", temp.getDeparturePoint().getLngDouble());
					myWebView.loadUrl("javascript:caldistance(" + "'"
							+ json3.toString() + "'" + ")");
					// Log.i("res_distance ",res_distance);
					detourDistance += Integer.parseInt(res_distance) / 2;
					
					//if user.carben+temp_short
					Log.i("temp_short== ", String.valueOf(detourDistance));
					if (detourDistance < shortestpath) {
						shortestpath = detourDistance;
						detourDistance = 0;
						index = i;
					}
				}	

			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Log.i("shortest one is  ", String.valueOf(shortestpath));
		Log.i("found index is  ", String.valueOf(index));
		//testing listview in a dialog
		/*AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Select passenger");

		ListView modeList = new ListView(this);
		ArrayList<String> stringArray = new ArrayList<String>();
		stringArray.add("Passenger1");
		stringArray.add("Passenger2");
		stringArray.add("Passenger3");
		ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, stringArray);
		modeList.setAdapter(modeAdapter);

		builder.setView(modeList);
		final Dialog dialog = builder.create();

		dialog.show();*/
		
		//end testing
		drawPathonMap(index);

	}

	private void drawPathonMap(int index) {

		//Person userRoute = new Person();
		//userRoute = ((PublicDataBox) getApplication()).getUser();
		JSONObject json = new JSONObject();
		Person temp = new Person(); 
		temp= ((PublicDataBox) getApplication())
				.getRequestByIndex(index);

		try {
			json.put("depalat", UserRoute.getDeparturePoint()
					.getLatDouble());
			json.put("depalng", UserRoute.getDeparturePoint()
					.getLngDouble());

			json.put("wp1lat", temp.getDeparturePoint()
					.getLatDouble());
			json.put("wp1lng", temp.getDeparturePoint()
					.getLngDouble());
			json.put("wp2lat", temp.getArrivalPoint()
					.getLatDouble());
			json.put("wp2lng", temp.getArrivalPoint()
					.getLngDouble());

			json.put("dstlat", UserRoute.getArrivalPoint()
					.getLatDouble());
			json.put("dstlng", UserRoute.getArrivalPoint()
					.getLngDouble());

			//Log.i("json= ", json.toString());
			myWebView.loadUrl("javascript:draw_path(" + "'" + json.toString()
					+ "'" + ")");
			/*myWebView.loadUrl("javascript:draw_path2(" + "'" + json.toString()
					+ "'" + ")");
			myWebView.loadUrl("javascript:draw_path_markers(" + "'" + json.toString()
							+ "'" + ")");*/
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_showOfferRoutes: {

			try {
				Person temp = new Person();
				int size = ((PublicDataBox) getApplication())
						.getOfferListSize();
				if (size > 0) {
					for (int i = 0; i < size; ++i) {
						JSONObject json = new JSONObject();
						temp = ((PublicDataBox) getApplication())
								.getOfferByIndex(i);

						json.put("id", temp.getUsername());
						json.put("depalat", temp
								.getDeparturePoint().getLatDouble());
						json.put("depalng", temp
								.getDeparturePoint().getLngDouble());
						json.put("dstlat", temp.getArrivalPoint()
								.getLatDouble());
						json.put("dstlng", temp.getArrivalPoint()
								.getLngDouble());

						myWebView.loadUrl("javascript:draw_markers(" + "'"
								+ json.toString() + "'" + ")");

						Log.i("json= ", json.toString());
					}
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
			break;
		case R.id.action_showRequestRoutes: {

		}
			break;
		case R.id.action_findMatch: {
			//findRequestMatch();
			findPassenger();
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
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.testmenu, menu);
		return true;
	}
}
