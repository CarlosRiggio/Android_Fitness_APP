package fitness.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Workout extends AppCompatActivity {
    String type_day_workout = "";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        intent = getIntent();
        type_day_workout = intent.getStringExtra("type_day_workout_key");

        if(type_day_workout.equals("2d_buk"))
        {
            // function opening  workout from csv readed
        }
        else if(type_day_workout.equals("4d_buk"))
        {
            // function opening  workout from csv readed
        }
        else if(type_day_workout.equals("6d_buk"))
        {
            // function opening  workout from csv readed
        }
        else if(type_day_workout.equals("2d_maint"))
        {
            // function opening  workout from csv readed
        }
        else if(type_day_workout.equals("4d_maint"))
        {
            // function opening  workout from csv readed
        }
        else if(type_day_workout.equals("6d_maint"))
        {
            // function opening  workout from csv readed
        }
        else if(type_day_workout.equals("2d_cut"))
        {
            // function opening  workout from csv readed
        }
        else if(type_day_workout.equals("4d_cut"))
        {
            // function opening  workout from csv readed
        }
        else if(type_day_workout.equals("6d_cut"))
        {
            // function opening  workout from csv readed
        }
        else
        {
            Toast.makeText(this, "Problem", Toast.LENGTH_SHORT).show();
        }




        // !!!this will be in a function to read from a csv file for each workout!!!
        List<Exercise> exercises = new ArrayList<Exercise>();
        exercises.add(new Exercise("Push up", "80", "23kg", "10s",R.drawable.logo_main));
        exercises.add(new Exercise("Dips", "30", "653kg", "10s",R.drawable.logo_main));
        exercises.add(new Exercise("Pull ups", "20", "33kg", "10s",R.drawable.logo_main));
        exercises.add(new Exercise("Squats", "15", "24kg", "10s",R.drawable.logo_main));
        exercises.add(new Exercise("Dumble press", "10", "23kg", "10s",R.drawable.logo_main));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), exercises));
    }



}