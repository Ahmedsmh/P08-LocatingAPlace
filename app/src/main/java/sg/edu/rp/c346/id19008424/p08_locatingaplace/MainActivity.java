package sg.edu.rp.c346.id19008424.p08_locatingaplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button BtnNorth,BtnCentral,BtnEast;
    private GoogleMap map;
    Spinner spnLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BtnNorth = findViewById(R.id.Northbtn);
        BtnCentral = findViewById(R.id.Centralbtn);
        BtnEast = findViewById(R.id.Eastbtn);
        spnLocation = findViewById(R.id.spinnerLocation);
        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                LatLng poi_SG = new LatLng(1.3521, 103.8198);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_SG,
                        10));
                LatLng poi_North = new LatLng(1.4380597023501693, 103.82240385937693);
                LatLng poi_Central = new LatLng(1.316973778817244, 103.87203134659482);
                LatLng poi_East = new LatLng(1.3497832565657657, 103.93580745362281);
                Marker cpNorth = map.addMarker(new
                        MarkerOptions()
                        .position(poi_North)
                        .title("HQ - North")
                        .snippet("Block 333, Admiralty Ave 3, 765654 ")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
                Marker cpCentral = map.addMarker(new
                        MarkerOptions()
                        .position(poi_Central)
                        .title("HQ - Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 ")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                Marker cpEast = map.addMarker(new
                        MarkerOptions()
                        .position(poi_East)
                        .title("HQ - East")
                        .snippet("Block 555, Tampines Ave 3, 287788 ")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }
                spnLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        switch (position){
                            case 0:
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_SG,
                                        10));
                                break;
                            case 1:
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North,
                                        15));
                                break;
                            case 2:
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central,
                                        15));
                                break;
                            case 3:
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East,
                                        15));
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                BtnNorth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North,
                                15));

                    }
                });
                BtnCentral.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central,
                                15));

                    }
                });
                BtnEast.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East,
                                15));

                    }
                });
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        String markerName = marker.getTitle();
                        Toast.makeText(MainActivity.this,markerName,Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
            }
        });
    }
}