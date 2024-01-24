package fitness.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

    // Context to be used in the adapter
    Context context;

    // List of Exercise objects to be displayed in the RecyclerView
    List<Exercise> exercises;

    // Constructor to initialize the context and list of exercises
    public MyAdapter(Context context, List<Exercise> exercises) {
        this.context = context;
        this.exercises = exercises;
    }

    // Create a new ViewHolder instance when needed
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the exercise_view layout and return a new MyViewHolder instance
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.exercise_view, parent, false));
    }

    // Bind data to the views in the ViewHolder at the given position
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Set the name, image, rep, recovery time, and weight for the Exercise at the current position
        holder.nameView.setText(exercises.get(position).getName());
        holder.imageView.setImageResource(exercises.get(position).getImage());
        holder.repView.setText(exercises.get(position).getRep());
        holder.recovery_tView.setText(exercises.get(position).getRecovery_t());
        holder.weightView.setText(exercises.get(position).getWeight());
    }

    // Return the total number of items in the data set
    @Override
    public int getItemCount() {
        return exercises.size();
    }
}
