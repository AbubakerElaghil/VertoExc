package vertoexcersice.alaqel.com.vertoexcersice.Activities;

import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import vertoexcersice.alaqel.com.vertoexcersice.R;
import vertoexcersice.alaqel.com.vertoexcersice.Respository.Response.GeoSearch;
import vertoexcersice.alaqel.com.vertoexcersice.Respository.Response.Pages;
import vertoexcersice.alaqel.com.vertoexcersice.Respository.Response.ResponseObject;
import vertoexcersice.alaqel.com.vertoexcersice.ViewModels.MainViewModel;
import vertoexcersice.alaqel.com.vertoexcersice.utilities.ConnectionDetector;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;

public class MainActivity extends BaseActivity {

    public  FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;


    final int permsRequestCODE = 200;
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;

    MainViewModel mainViewModel;
    HashMap<String, Integer> imagesCounterMap = new HashMap<>();

    LinearLayout dataLayout;
    String TAG = "SendingRqst";
    int geoSearchListSize = 0;

    int RequestCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel();
        initViews();
        if (checkPermission()) {
            checkLocationSettings();
        } else {
            requestPermission();
        }
    }

    private void initViews() {
        dataLayout = (LinearLayout) findViewById(R.id.data_layout);
    }

    private void initViewModel() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    private void getArticles(Double Lat, Double longt) {
        if(ConnectionDetector.isConnectingToInternet(MainActivity.this)){

            if(dataLayout.getChildCount()>0){
                dataLayout.removeAllViews();
            }
        ShowProgress("Getting Data", "Loading...", MainActivity.this);
        mainViewModel.articlesLists.observe(this, new Observer<ResponseObject>() {
            @Override
            public void onChanged(@Nullable ResponseObject responseObject) {

                if (responseObject != null) {
                    List<GeoSearch> geoSearchList = responseObject.getQuery().getGeoSearchList();
                    if (geoSearchList != null && geoSearchList.size() > 0) {
                        geoSearchListSize = geoSearchList.size();
                        for (int i = 0; i < geoSearchList.size(); i++) {
                            getPageImages(geoSearchList.get(i).getPageid());
                        }

                    } else {
                        HideProgress();
                        Toast.makeText(MainActivity.this, "No Articles Found!", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    HideProgress();
                    Toast.makeText(MainActivity.this, "Error Happened!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        mainViewModel.getArticles(Lat.toString(), longt.toString());


        }
        else {
            Toast.makeText(this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }


    private void getPageImages(String pageId) {
        MutableLiveData<ResponseObject> pageImageLists = new MutableLiveData<>();
        pageImageLists.observe(this, new Observer<ResponseObject>() {
            @Override
            public void onChanged(@Nullable ResponseObject responseObject) {
                RequestCounter++;
                if (responseObject != null) {
                    Map<Integer, Pages> result = responseObject.getQuery().getResult();
                    if (result != null && result.size() > 0) {

                        Pages page = result.get(Integer.parseInt(responseObject.getPageId()));
                        for (int i = 0; i < page.getImagesList().size(); i++) {
                            String imageTitle = page.getImagesList().get(i).getTitle();
                            if (imagesCounterMap.containsKey(imageTitle)) {
                                int counter = imagesCounterMap.get(imageTitle);
                                counter++;
                                imagesCounterMap.put(imageTitle, counter);
                            } else {
                                imagesCounterMap.put(imageTitle, 1);
                            }
                        }
                    }
                }
                if (RequestCounter == geoSearchListSize) {
                    HideProgress();
                    HashMap<String, Integer> sortedHashMap = sortByValue(imagesCounterMap);
                    for (String key : sortedHashMap.keySet()) {
                        int noOfSimilarity = imagesCounterMap.get(key);
                        if (noOfSimilarity != 1) {
                            final LinearLayout rowLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.row_layout, null);
                            TextView imageTitle = (TextView) rowLayout.findViewById(R.id.image_title);
                            TextView noOfSimili = (TextView) rowLayout.findViewById(R.id.no_similar);

                            imageTitle.setText(key);
                            noOfSimili.setText(noOfSimilarity + "");
                            dataLayout.addView(rowLayout);
                        }
                    }
                }


            }
        });
        mainViewModel.getPageImages(pageId, pageImageLists);
    }




    //region LocationSection

    private void checkLocationSettings() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(this).checkLocationSettings(builder.build());
        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    getLocationRequest();

                } catch (ApiException exception) {
                    switch (exception.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {

                                ResolvableApiException resolvable = (ResolvableApiException) exception;
                                resolvable.startResolutionForResult(MainActivity.this, REQUEST_CHECK_SETTINGS);
                            } catch (IntentSender.SendIntentException e) {

                            } catch (ClassCastException e) {

                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            // Location settings are not satisfied. However, we have no way to fix the
                            // settings so we won't show the dialog.
                            break;
                    }
                }
            }
        });
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, permsRequestCODE);
    }

    private void getLocationRequest() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(20 * 1000);
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Log.e("On LOcation Update","On LOcation Update");

                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        Log.e("On LOcation Update","On LOcation Update");
                        if (mFusedLocationClient != null) {
                            mFusedLocationClient.removeLocationUpdates(locationCallback);
                        }
                        getArticles(location.getLatitude(), location.getLongitude());

                    }
                }
            }
        };

        getDeviceLocation();
    }

    private void getDeviceLocation() {
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {

                            getArticles(location.getLatitude(), location.getLongitude());
                            // Logic to handle location object
                        } else {
                            mFusedLocationClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());
                            Toast.makeText(MainActivity.this, "Cannot Find Location!, Will try Again!", Toast.LENGTH_SHORT).show();
                        }
                    }

                });


    }


    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {

        switch (permsRequestCode) {

            case permsRequestCODE:

                boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (locationAccepted) {
                    checkLocationSettings();
                } else {

                    Toast.makeText(this, "Permission Denied, You cannot access location data.", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(ACCESS_COARSE_LOCATION)) {
                            showMessageOKCancel("You need to allow access to location data",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                //  requestPermissions(new String[]{ACCESS_COARSE_LOCATION },permsRequestCODE);
                                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{ACCESS_COARSE_LOCATION}, permsRequestCODE);

                                            }
                                        }
                                    });
                            return;
                        }
                    }

                }


                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
// Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getLocationRequest();
                            }
                        }, 2000);

                        break;
                    case Activity.RESULT_CANCELED:
                        checkLocationSettings();//keep asking if imp or do whatever
                        break;
                }
                break;
        }
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
//endregion

}
