package lab4.android.wku.edu.songwriterassistant;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng cameraDefault = new LatLng(40.966035, -101.044399);
    LatLng latlng;

    int count = 0;

    private DBLocationsHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Tracer", "In class: MapsActivity In Method: onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private class GetCoordinates extends AsyncTask<String, Void, String> {
        //ProgressDialog dialog = new ProgressDialog(MapsActivity.this);

        @Override
        protected void onPreExecute() {
            Log.d("Tracer", "In class: MapsActivity In Method: onPreExecute");
            super.onPreExecute();
            /*dialog.setMessage("Please wait....");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();*/
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d("Tracer", "In class: MapsActivity In Method: doInBackground");
            String response;
            try{
                String address = strings[0];
                HttpDataHandler http = new HttpDataHandler();
                String url = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s",address);
                response = http.getHTTPData(url);
                return response;
            }
            catch (Exception ex)
            {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("Tracer", "In class: MapsActivity In Method: onPostExecute");
            try{
                JSONObject jsonObject = new JSONObject(s);

                String lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                        .getJSONObject("location").get("lat").toString();
                String lng = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                        .getJSONObject("location").get("lng").toString();

                double latitude = Double.parseDouble(lat);
                double longitude = Double.parseDouble(lng);
                latlng = new LatLng(latitude, longitude);

                String title = new String();

                ArrayList titles = mydb.getAllLocations();
                title = ""+titles.get(count);

                //txtCoord.setText(String.format("Coordinates : %s / %s ",lat,lng));
                Log.d("Coordinates", "Coordinates: "+latlng);

                mMap.addMarker(new MarkerOptions().position(latlng).title(title));

                count++;

                /*if(dialog.isShowing())
                    dialog.dismiss();*/

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("Tracer", "In class: MapsActivity In Method: onMapReady");
        mMap = googleMap;

        mMap.moveCamera(CameraUpdateFactory.newLatLng(cameraDefault));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }

        googleMap.setMyLocationEnabled(true);

        mydb = new DBLocationsHelper(this);
        ArrayList locations = mydb.getAllGeoAddresses();

        for(int i = 0; i < locations.size(); i++) {
            Log.d("Tracer", "In class: MapsActivity In Method: onCreate for loop");
            Log.d("Address", "Address: "+locations.get(i));
            String addressToGeocode = ""+locations.get(i);
            new GetCoordinates().execute(addressToGeocode);
        }
    }
}
