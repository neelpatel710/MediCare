package com.example.gmit_sdp.beinghuman;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by DELL on 23-07-2018.
 */

    public class Maps extends Activity {
    WebView webview;
    private GoogleMap mMap;
    LatLng latLng;
    private String mGeolocationOrigin;
    private GeolocationPermissions.Callback mGeolocationCallback;

    private GoogleApiClient googleApiClient;

    private static final int LOCATION_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);
        webview = (WebView) findViewById(R.id.webView1);
        webview.setWebChromeClient(new WebChromeClient());
    }

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin,
                                                       GeolocationPermissions.Callback callback) {
            // Geolocation permissions coming from this app's Manifest will only be valid for devices with API_VERSION < 23.
            // On API 23 and above, we must check for permission, and possibly ask for it.
            final String permission = android.Manifest.permission.ACCESS_FINE_LOCATION;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                    ContextCompat.checkSelfPermission(Maps.this, permission) == PackageManager.PERMISSION_GRANTED) {
                // we're on SDK < 23 OR user has already granted permission
                callback.invoke(origin, true, false);
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(Maps.this, permission)) {
                    // user has denied this permission before and selected [/] DON'T ASK ME AGAIN
                    // TODO Best Practice: show an AlertDialog explaining why the user could allow this permission, then ask again
                } else {
                    // ask the user for permissions
                    ActivityCompat.requestPermissions(Maps.this, new String[]{permission}, 1);
                    mGeolocationOrigin = origin;
                    mGeolocationCallback = callback;
                }
            }
        }
    };
}


