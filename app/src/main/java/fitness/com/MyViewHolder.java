package fitness.com;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
public class MyViewHolder extends  RecyclerView.ViewHolder{

    // Declare ImageView and TextView variables for the views in the item layout
    ImageView imageView;
    TextView nameView, repView, recovery_tView, weightView;

    // Constructor for MyViewHolder, takes a View parameter
    public MyViewHolder( View itemView) {
        // Call the constructor of the superclass (RecyclerView.ViewHolder)
        super(itemView);

        // Initialize ImageView and TextView variables by finding views by their IDs in the item layout
        imageView = itemView.findViewById(R.id.imageView_Excercise);
        nameView = itemView.findViewById(R.id.title_Excercise);
        repView = itemView.findViewById(R.id.rep_Excercise);
        recovery_tView = itemView.findViewById(R.id.recovery_t_Excercise);
        weightView = itemView.findViewById(R.id.weight_Excercise);

    }
}