package fitness.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Workout extends AppCompatActivity {
    String type_day_workout = "";
    Intent intent;

    TextView workout_type_text,day_workout_text, workout_duration_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        // getting info from the intet
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        intent = getIntent();
        type_day_workout = intent.getStringExtra("type_day_workout_key");

        // getting textview from the layout
        workout_type_text = findViewById(R.id.type_of_workout_text);
        workout_duration_text = findViewById(R.id.workout_duration_text);
        day_workout_text = findViewById(R.id.day_of_workout);


        if(type_day_workout.equals("2d_bulk"))
        {
            // function opening  workout from csv readed
            workout_type_text.setText("Bulking");
            day_workout_text.setText("2 day");
            workout_duration_text.setText("90 min");
        }
        else if(type_day_workout.equals("4d_bulk"))
        {
            // function opening  workout from csv readed
            workout_type_text.setText("Bulking");
            day_workout_text.setText("4 day");
            workout_duration_text.setText("90 min");
        }
        else if(type_day_workout.equals("6d_bulk"))
        {
            // function opening  workout from csv readed
            workout_type_text.setText("Bulking");
            day_workout_text.setText("6 day");
            workout_duration_text.setText("90 min");
        }
        else if(type_day_workout.equals("2d_maint"))
        {
            // function opening  workout from csv readed
            workout_type_text.setText("Maintenance");
            day_workout_text.setText("2 day");
            workout_duration_text.setText("90 min");
        }
        else if(type_day_workout.equals("4d_maint"))
        {
            // function opening  workout from csv readed
            workout_type_text.setText("Maintenance");
            day_workout_text.setText("4 day");
            workout_duration_text.setText("90 min");
        }
        else if(type_day_workout.equals("6d_maint"))
        {
            // function opening  workout from csv readed
            workout_type_text.setText("Maintenance");
            day_workout_text.setText("6 day");
            workout_duration_text.setText("90 min");
        }
        else if(type_day_workout.equals("2d_cut"))
        {
            // function opening  workout from csv readed
            workout_type_text.setText("Cutting");
            day_workout_text.setText("2 day");
            workout_duration_text.setText("90 min");
        }
        else if(type_day_workout.equals("4d_cut"))
        {
            // function opening  workout from csv readed
            workout_type_text.setText("Cutting");
            day_workout_text.setText("4 day");
            workout_duration_text.setText("90 min");
        }
        else if(type_day_workout.equals("6d_cut"))
        {
            // function opening  workout from csv readed
            workout_type_text.setText("Cutting");
            day_workout_text.setText("6 day");
            workout_duration_text.setText("90 min");
        }
        else
        {
            Toast.makeText(this, "Problem", Toast.LENGTH_SHORT).show();
        }




        // !!!this will be in a function to read from a csv file for each workout!!!
        List<Exercise> exercises = new ArrayList<Exercise>();
        exercises.add(new Exercise("Push up", "Set x Reps: 3 x 12", "Recovery: 60\"", "Weight: 30 Kg",R.drawable.logo_main));
        exercises.add(new Exercise("Dips", "30", "653kg", "10s",R.drawable.logo_main));
        exercises.add(new Exercise("Pull ups", "20", "33kg", "10s",R.drawable.logo_main));
        exercises.add(new Exercise("Squats", "15", "24kg", "10s",R.drawable.logo_main));
        exercises.add(new Exercise("Dumble press", "10", "23kg", "10s",R.drawable.logo_main));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), exercises));
    }



}