package fitness.com;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class MyViewHolder extends  RecyclerView.ViewHolder{
    ImageView imageView;
    TextView nameView;
    TextView repView;
    TextView recovery_tView;
    TextView weightView;

    public MyViewHolder( View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView_Excercise);
        nameView = itemView.findViewById(R.id.title_Excercise);
        repView = itemView.findViewById(R.id.rep_Excercise);
        recovery_tView = itemView.findViewById(R.id.recovery_t_Excercise);
        weightView = itemView.findViewById(R.id.weight_Excercise);




    }
}