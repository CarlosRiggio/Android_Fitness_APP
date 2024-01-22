package fitness.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

    Context context;
    List<Exercise> exercises;

    public MyAdapter(Context context, List<Exercise> exercises) {
        this.context = context;
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.exercise_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nameView.setText(exercises.get(position).getName());
        holder.imageView.setImageResource(exercises.get(position).getImage());
        holder.repView.setText(exercises.get(position).getRep());
        holder.recovery_tView.setText(exercises.get(position).getRecovery_t());
        holder.weightView.setText(exercises.get(position).getWeight());

    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }
}