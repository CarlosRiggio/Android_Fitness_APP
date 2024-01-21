package fitness.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Workout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        List<Excercise> excercises = new ArrayList<Excercise>();
        excercises.add(new Excercise("Push up", "80", "23kg", "10s",R.drawable.logo_main));
        excercises.add(new Excercise("Dips", "30", "653kg", "10s",R.drawable.logo_main));
        excercises.add(new Excercise("Pull ups", "20", "33kg", "10s",R.drawable.logo_main));
        excercises.add(new Excercise("Squats", "15", "24kg", "10s",R.drawable.logo_main));
        excercises.add(new Excercise("Dumble press", "10", "23kg", "10s",R.drawable.logo_main));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), excercises));

    }
}