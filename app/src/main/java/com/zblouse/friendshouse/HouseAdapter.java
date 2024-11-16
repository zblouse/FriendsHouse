package com.zblouse.friendshouse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * RecyclerView Adapter for displaying Houses
 */
public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.ViewHolder> {

    private final List<House> houses;

    public HouseAdapter(List<House> houses){
        this.houses = houses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflates the layout from the home_display layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_display,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        House house = houses.get(position);
        holder.nameTextView.setText(house.getName());
        holder.descriptionTextView.setText(house.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //When the user clicks on this house, call the display house map in the MainActivity
                ((MainActivity)view.getContext()).displayHouseMap(house);
            }
        });
    }

    @Override
    public int getItemCount() {
        return houses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView descriptionTextView;

        public ViewHolder(View houseView){
            super(houseView);
            //Gets the UI Elements for this House display
            nameTextView = houseView.findViewById(R.id.house_name);
            descriptionTextView = houseView.findViewById(R.id.house_description);
        }
    }
}
