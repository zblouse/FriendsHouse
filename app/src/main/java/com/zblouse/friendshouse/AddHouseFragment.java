package com.zblouse.friendshouse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

/**
 * Fragment that allows the user to add a house to the list of houses
 */
public class AddHouseFragment extends Fragment {

    public AddHouseFragment(){
        super(R.layout.fragment_add_house);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_add_house,container,false);
        //Get references to all UI elements
        EditText nameEditText = layout.findViewById(R.id.name_edit_text);
        EditText descriptionEditText = layout.findViewById(R.id.description_edit_text);
        EditText longitudeEditText = layout.findViewById(R.id.longitude_edit_text);
        EditText latitudeEditText = layout.findViewById(R.id.latitude_edit_text);
        Button addHouseButton = layout.findViewById(R.id.add_house_button);
        //When the add house button is clicked, if some fields were left blank, the user is shown and error snackbar.
        //When the add house button is clicked, if all fields are valid, a House is added to the database
        addHouseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nameEditText.getText().toString().isEmpty() || descriptionEditText.getText().toString().isEmpty()
                    || longitudeEditText.getText().toString().isEmpty() || latitudeEditText.getText().toString().isEmpty()){
                    Snackbar snackbar = Snackbar.make(layout,"All fields are required",Toast.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    HouseDatabaseHelper helper = new HouseDatabaseHelper(layout.getContext());
                    House house = new House(nameEditText.getText().toString(),descriptionEditText.getText().toString(),
                            Double.valueOf(latitudeEditText.getText().toString()),Double.valueOf(longitudeEditText.getText().toString()));
                    helper.addHouseToDatabase(house);
                    //Return to the home fragment after creating a house
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new ListViewFragment()).commit();
                }
            }
        });

        return layout;
    }
}
