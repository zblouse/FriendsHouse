package com.zblouse.friendshouse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Fragment to display all houses in a RecyclerView
 */
public class ListViewFragment extends Fragment {

    public ListViewFragment(){
        super(R.layout.fragment_list_view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflates the fragment UI elements from the fragment_list_view
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_list_view,container,false);
        HouseDatabaseHelper houseDatabaseHelper = new HouseDatabaseHelper(layout.getContext());
        RecyclerView houseListView = layout.findViewById(R.id.house_recycler_view);
        //Initializes a HouseAdapter with a list of all houses currently in the House Database
        houseListView.setAdapter(new HouseAdapter(houseDatabaseHelper.getAllHouses()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(layout.getContext());
        //DividerItemDecoration to put some space between each element in the RecyclerView
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(houseListView.getContext(),
                layout.getOrientation());
        dividerItemDecoration.setDrawable(layout.getContext().getDrawable(R.drawable.divider));
        houseListView.addItemDecoration(dividerItemDecoration);
        houseListView.setLayoutManager(layoutManager);
        return layout;
    }
}
