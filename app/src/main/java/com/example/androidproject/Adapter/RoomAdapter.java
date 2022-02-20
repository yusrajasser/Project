package com.example.androidproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidproject.Entity.Room;
import com.example.androidproject.R;
import com.example.androidproject.ReserveActivity;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    private Context context;
    private List<Room> rooms;

    public RoomAdapter(Context context, List<Room> rooms) {
        this.context = context;
        this.rooms = rooms;
    }

    @NonNull
    @Override
    public RoomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.roomcard, parent, false);
        return new RoomAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Room room = rooms.get(position);
        CardView cardView = holder.cardView;
        TextView rnum = cardView.findViewById(R.id.room_no) ;
        rnum.setText("Room "+room.getRoom_number());
        TextView rprice = cardView.findViewById(R.id.room_price) ;
        rprice.setText(room.getPrice()+" $");
        TextView floor = cardView.findViewById(R.id.room_floor) ;
        floor.setText("floor "+room.getRoom_floor());
        TextView tel = cardView.findViewById(R.id.room_tel) ;
        tel.setText("telephone:  "+room.getRoom_tel());
        TextView bed = cardView.findViewById(R.id.bed_rooms) ;
        bed.setText("bedrooms:  "+room.getBeds_number());
        ImageView imageView = cardView.findViewById(R.id.roomimg);
        Glide.with(context).load(room.getImg()).centerCrop().into(imageView);
        if(room.getBooked_condition()==0) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ReserveActivity.class) ;
                    intent.putExtra("room_id",room.getRoom_id()+"") ;
                    context.startActivity(intent);
                }
            });
        }else{
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "this room already Reserved", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;

        }


    }
}