package com.zblouse.friendshouse;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * MainActivity for the app
 */
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private House activeHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Finds the toolbarview and sets it as the SupportActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Creates the FloatingActionButton used to add Houses to the database
        FloatingActionButton floatingActionButton = findViewById(R.id.floating_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //When the floating action button is clicked, swap the active fragment to the AddHouseFragment
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new AddHouseFragment()).commit();
            }
        });
        //The initial fragment should be the ListViewFragment that displays all houses currently in the database
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new ListViewFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflates the UI elements in the action_bar menu
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection.
        if(item.getItemId() == R.id.action_home){
            //when the home button is clicked, switch the active fragment to the ListViewFragment
            //that displays the list of houses in the RecyclerView
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ListViewFragment()).commit();
        }
        return super.onOptionsItemSelected(item);

    }

    /**
     * Method that is called when a House is clicked on in the RecyclerView. Displays the MapFragment
     * @param house
     */
    public void displayHouseMap(House house){
        //Active House variable is used so the async onMapReady method knows which house to display on the map
        activeHouse = house;
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        //Swaps the active fragment to the Google Map Fragment
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mapFragment).commit();
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        //Creates a LatLng object from the ActiveHouse's lat and long
        LatLng house = new LatLng(activeHouse.getLatitude(), activeHouse.getLongitude());
        //Creates a marker on the map
        googleMap.addMarker(new MarkerOptions()
                //sets the lat and long for the marker
                .position(house)
                //Sets the description for the marker
                .snippet(activeHouse.getDescription())
                //sets the name of the marker
                .title(activeHouse.getName())
                //Make the Icon Orange to match the orange theme of the app
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        //Sets the Zoom to a reasonable level for a house
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(house,15));
        //enables the zoom UI on the map
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }
}