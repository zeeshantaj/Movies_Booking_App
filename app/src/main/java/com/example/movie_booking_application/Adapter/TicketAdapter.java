package com.example.movie_booking_application.Adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movie_booking_application.Activity.Booking_Activity;
import com.example.movie_booking_application.Model.BookingModel;
import com.example.movie_booking_application.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

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



        String person = model.getPerson();
        int per = Integer.parseInt(person);
        int multi = per * 1500;
        String formatted = String.format(Locale.US,"Price for %s person Rs: %d",model.getPerson(),multi);
       holder.priceTxt.setText(formatted);

        Glide.with(holder.itemView.getContext())
                .load(model.getImageUrl())
                .into(holder.imageView);

        Log.e("MyApp","adapterTicketId"+model.getTicketId());

        String ticketId = model.getTicketId();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("Booking_Request")
                .child(uid)
                .child(ticketId);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Delete Options");
                builder.setMessage("Are you sure you want to delete this ticket");
                builder.setPositiveButton("Yes", (dialog, which) -> {

                    reference.removeValue().addOnCompleteListener(task -> {
                        Toast.makeText(v.getContext(), "Item Deleted", Toast.LENGTH_SHORT).show();

                        holder.itemView.animate()
                                .alpha(0f) // Set alpha to 0 for fade-out effect
                                .setDuration(300) // Set duration for the animation in milliseconds
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        removeAt(position);
                                    }
                                })
                                .start();
                        dialog.dismiss();
                    }).addOnFailureListener(e -> Toast.makeText(v.getContext(), "Error "+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show());
                });
                builder.setNegativeButton("No", (dialog, which) -> {
                    // TODO: Do something, when user click on the positive button
                    dialog.dismiss();
                });
                builder.create().show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }
    public void removeAt(int position) {
        ticketList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, ticketList.size());
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title,date,person,showTime,seatNo,priceTxt;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.ticket_movieTitle);
            date = itemView.findViewById(R.id.ticket_date);
            person = itemView.findViewById(R.id.ticket_person);
            imageView = itemView.findViewById(R.id.imageView2);
            showTime = itemView.findViewById(R.id.ticketShowTime);
            seatNo = itemView.findViewById(R.id.seatNoTxt);
            priceTxt = itemView.findViewById(R.id.ticket_price);




        }
    }
}
