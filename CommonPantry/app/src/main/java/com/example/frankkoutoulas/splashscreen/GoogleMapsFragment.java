package com.example.frankkoutoulas.splashscreen;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
/** Establishes a Google Map and indicates current location.
 * Builds a string to call Google places API with current location.
 * Sends string to a GetData task, which calls a ParseData task after the JSON is received.
 * The data is actually parsed in a JSONParser object and return to the ParseData object as a List.
 * The ParseData object takes the List of Places and adds markers with info to the established Google Map.
 *
 * The GetData and ParseData objects run as asynchronous threads to the Google Maps Fragment.
 */

/** Resources
 * Understanding Asynchronous Tasks
 * http://developer.android.com/reference/android/os/AsyncTask.html
 * http://stackoverflow.com/questions/6053602/what-arguments-are-passed-into-asynctaskarg1-arg2-arg3
 * Displaying Current Location
 * http://javapapers.com/android/android-show-current-location-on-map-using-google-maps-api/
 * Bouncing Icon
 * https://developers.google.com/maps/documentation/android-api/code-samples
 * Spinner Widget, Receiving and Parsing JSON data
 * http://wptrafficanalyzer.in/blog/showing-nearby-places-using-google-places-api-and-google-map-android-api-v2/
 * Snazzy Buttons
 * http://stackoverflow.com/questions/7690416/android-border-for-button
 *
 */

public class GoogleMapsFragment extends Fragment implements LocationListener, GoogleMap.OnMarkerClickListener{


    GoogleMap mMap;
    MapView mMapView;

    /* Used for a bouncing icon demo.  */
    private static final LatLng SFSU = new LatLng(37.722769, -122.476717);
    private Marker mSFSU;

    /* Used to make a Spinner */
    Spinner mPlaceSpinner;
    String[] mPlaceType = null;
    String[] mPlaceTypeName = null;

    double mLatitude = 0;
    double mLongitude = 0;

    Context context;
    Activity activity;
    View mView;

    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState){

        context = getActivity();
        activity = ((MainActivity)getActivity()).getMainActivity();


        mView = inflater.inflate(R.layout.fragment_googlemaps, container, false);
        //mView = inflater.from(context).inflate(R.layout.fragment_googlemaps, container, false);

        mMapView = (MapView) mView.findViewById(R.id.mapView);

        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();// needed to get the map to display immediately


         /* Arrays to hold place types and name of place types on Spinner
         * declared in my strings resource folder  */
        mPlaceType = getResources().getStringArray(R.array.place_type);
        mPlaceTypeName = getResources().getStringArray(R.array.place_type_name);

        /* Establishes layout for Spinner List */
        /* ArrayAdapters can represent/draw items in a list
         * arg1 - context, arg2 - layout type agr3 - names of place types */
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, mPlaceTypeName);
        mPlaceSpinner = (Spinner) mView.findViewById(R.id.spr_place_type);
        mPlaceSpinner.setAdapter(adapter);

        Button btnSearch;
        btnSearch = (Button) mView.findViewById(R.id.btnSearch);

        /* Check for Google Play Services availability */
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity.getBaseContext());
        if (status != ConnectionResult.SUCCESS) {
            // will prompt the User if error with Google Play Services
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, activity, requestCode);
            dialog.show();

        } else {/*
            /* Set-Up Map */
            //SupportMapFragment fragment = (SupportMapFragment) activity.getSupportFragmentManager().findFragmentById(R.id.map);
            //mMap = fragment.getMap();

            try {
                MapsInitializer.initialize(getActivity().getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }

            mMap = mMapView.getMap();

            //getChildFragmentManager().beginTransaction().add(R.id.framelayout_location_container, mMapFragment).commit();
            mMap.setMyLocationEnabled(true);
            //Set the map to current location
            LatLng position = new LatLng(mLatitude,mLongitude);

            mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

                @Override
                public void onMyLocationChange(Location location) {
                    mLatitude = location.getLatitude();
                    mLongitude = location.getLongitude();
                    LatLng position = new LatLng(mLatitude,mLongitude);


                    //CameraUpdate update = CameraUpdateFactory.newLatLngZoom(position, 12);

                    //Use map.animateCamera(update) if you want moving effect
                    //mMap.moveCamera(update);
                    //mMap.animateCamera(update);
                    mMapView.onResume();
                }});

            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(position, 12);
            mMap.moveCamera(update);
            mMap.animateCamera(update);
            //mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.setOnMarkerClickListener(this);


            /* Establish Current Location */
            LocationManager mlocManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            // accepts a criteria and a boolean value as to whether location services are enabled
            // get location provider
            String provider = mlocManager.getBestProvider(criteria, true);



            try {
                // Getting Current Location From GPS
                // if no last current location, call the location change function
                Location mlocation = mlocManager.getLastKnownLocation(provider);
                if (mlocation != null) {
                    onLocationChanged(mlocation);
                }
                // 20000 refers to milliseconds that need to pass for new location update
                // 0 refers to distance change in meters needed for new location update
                mlocManager.requestLocationUpdates(provider, 20000, 0, this);
            } catch (SecurityException e) {
                Log.e("Denied Permission", e.getMessage());
            }

            /* Search button*/
            btnSearch.setOnClickListener(new View.OnClickListener() {
                /* Send the call with parameters to Google Places API */
                @Override
                public void onClick(View v) {

                    int selectedPosition = mPlaceSpinner.getSelectedItemPosition();
                    String type = mPlaceType[selectedPosition];

                    // example API call
                    // https://maps.googleapis.com/maps/api/place/radarsearch/json?
                    // location=48.859294,2.347589&radius=5000&types=food|cafe&keyword=vegetarian&key=YOUR_API_KEY
                    StringBuffer sb = new StringBuffer("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                    sb.append("location=" + mLatitude + "," + mLongitude);
                    sb.append("&radius=5000");
                    sb.append("&types=" + type);
                    sb.append("&sensor=true");
                    sb.append("&key=AIzaSyDrNeDJuLBMlpv_X1IKAOcgE27MspKURxU");


                    /* Create thread to get JSON data from Google Places API*/
                    GetData places = new GetData();
                    places.execute(sb.toString());

                }
            });

        }

        return mView;
    }

    /**
     * Get JSON data from Google Places. Send it to ParseData to be parsed.
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream is = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            conn = (HttpURLConnection) url.openConnection();

            // Connecting to url
            conn.connect();

            // Reading data from url
            is = conn.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception url", e.toString());
        } finally {
            is.close();
            conn.disconnect();
        }

        return data;
    }

    /** Receive JSON Data from Google Places */
    private class GetData extends AsyncTask<String, Void, String> {

        String mJSONData = null;

        // Invoked by execute() method of this object
        @Override
        protected String doInBackground(String... url) {
            try{
                mJSONData = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task", e.toString());
            }
            return mJSONData;
        }

        // Executed after the complete execution of doInBackground() method
        @Override
        protected void onPostExecute(String result){
            ParseData parserTask = new ParseData();

            // Start parsing the Google places in JSON format
            // Invokes the "doInBackground()" method of the class ParseTask
            parserTask.execute(result);
        }

    }

    /** A class to parse the Google Places in JSON format
     * Invoked by execute() method of this object */
    private class ParseData extends AsyncTask<String, Integer, List<HashMap<String,String>>>{

        JSONObject mObject;

        @Override
        protected List<HashMap<String,String>> doInBackground(String... jsonData) {

            List<HashMap<String, String>> mplaces = null;
            JSONParser placeJsonParser = new JSONParser();

            try{
                mObject = new JSONObject(jsonData[0]);

                /** Getting the parsed data as a List construct */
                mplaces = placeJsonParser.parse(mObject);

            }catch(Exception e){
                Log.d("Exception",e.toString());
            }
            return mplaces;
        }

        // Executed after the complete execution of doInBackground() method
        @Override
        protected void onPostExecute(List<HashMap<String,String>> list){

            // Clears all the existing markers
            mMap.clear();
            LatLng mCurr = new LatLng(mLatitude, mLongitude);
            System.out.println(mCurr);
            MarkerOptions options = new MarkerOptions()
                    .position(mCurr)
                    .title("You Are Here")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .draggable(true);
            mSFSU = mMap.addMarker(new MarkerOptions()
                    .position(SFSU)
                    .title("SFSU")
                    .snippet("Campus Size: 29,465")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.addMarker(options);

            /* Go through the list of places and add markers */
            for(int i=0;i<list.size();i++){

                /** Create marker for every place in the list*/
                MarkerOptions mOptions = new MarkerOptions();
                HashMap<String, String> mPlace = list.get(i);


                double mlat = Double.parseDouble(mPlace.get("lat"));
                double mlng = Double.parseDouble(mPlace.get("lng"));
                String mName = mPlace.get("place_name");
                String mAddress = mPlace.get("vicinity");
                LatLng mlatLng = new LatLng(mlat, mlng);

                // Setting the position for the marker
                mOptions.position(mlatLng);

                /** markerOptions will set title and address of markers*/
                mOptions.title(mName)
                        .snippet("Address: "+ mAddress)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                mMap.addMarker(mOptions);

            }
        }
    }

    /*  Commenting-out this code, as it isn't Fragment-safe
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActivity().getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }*/

    @Override
    public void onLocationChanged(Location location) {
        mLatitude = location.getLatitude();
        mLongitude = location.getLongitude();
        LatLng latLng = new LatLng(mLatitude, mLongitude);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    /** Function taken from Google Maps Demo*/
    @Override
    public boolean onMarkerClick(final Marker marker) {
        if (marker.equals(mSFSU)) {

            final Handler handler = new Handler();
            final long start = SystemClock.uptimeMillis();
            final long duration = 1500;

            final Interpolator interpolator = new BounceInterpolator();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    long elapsed = SystemClock.uptimeMillis() - start;
                    float t = Math.max(
                            1 - interpolator.getInterpolation((float) elapsed / duration), 0);
                    marker.setAnchor(0.5f, 1.0f + 2 * t);

                    if (t > 0.0) {
                        handler.postDelayed(this, 16);
                    }
                }
            });
        }


        // We return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
}
