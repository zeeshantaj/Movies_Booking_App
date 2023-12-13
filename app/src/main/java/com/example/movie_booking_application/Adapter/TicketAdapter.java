package com.example.movie_booking_application.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movie_booking_application.Activity.Booking_Activity;
import com.example.movie_booking_application.Model.BookingModel;
import com.example.movie_booking_application.R;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {

    List<BookingModel> ticketList;

    public TicketAdapter(List<BookingModel> ticketList) {
        this.ticketList = ticketList;
    }

    @NonNull
    @Override
    public TicketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketAdapter.ViewHolder holder, int position) {
        BookingModel model = ticketList.get(position);
        holder.showTime.setText(model.getTiming());
        holder.person.setText(model.getPerson());
        holder.date.setText(model.getCurrentTime());
        holder.title.setText(model.getMovieTitle());
        holder.seatNo.setText("Seat No: "+model.getSeatNo());


        Glide.with(holder.itemView.getContext())
                .load(model.getImageUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title,date,person,showTime,seatNo;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.ticket_movieTitle);
            date = itemView.findViewById(R.id.ticket_date);
            person = itemView.findViewById(R.id.ticket_person);
            imageView = itemView.findViewById(R.id.imageView2);
            showTime = itemView.findViewById(R.id.ticketShowTime);
            seatNo = itemView.findViewById(R.id.seatNoTxt);
        }
    }
}
