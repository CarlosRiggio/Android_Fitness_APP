package fitness.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.SQLOutput;
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
        int resourceId ;
        String csv_file="";
        Context context = getApplicationContext();// obtain the context from your Android application

        List<Exercise> exercises = new ArrayList<Exercise>();


        switch (type_day_workout) {
            case "2d_bulk":
                csv_file = "d2_bulk";
                resourceId= context.getResources().getIdentifier(csv_file, "raw", context.getPackageName());
                exercises=readAndRetrieveExercises(context, resourceId);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new MyAdapter(getApplicationContext(), exercises));
                // function opening workout from CSV read
                break;
            case "4d_bulk":
                csv_file = "d4_bulk";
                resourceId= context.getResources().getIdentifier(csv_file, "raw", context.getPackageName());
                exercises=readAndRetrieveExercises(context, resourceId);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new MyAdapter(getApplicationContext(), exercises));
                break;
            case "6d_bulk":
                csv_file = "d6_bulk";
                resourceId= context.getResources().getIdentifier(csv_file, "raw", context.getPackageName());
                exercises=readAndRetrieveExercises(context, resourceId);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new MyAdapter(getApplicationContext(), exercises));
                break;
            case "2d_maint":
                csv_file = "d2_maint";
                resourceId= context.getResources().getIdentifier(csv_file, "raw", context.getPackageName());
                exercises=readAndRetrieveExercises(context, resourceId);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new MyAdapter(getApplicationContext(), exercises));
                break;
            case "4d_maint":
                csv_file = "d4_maint";
                resourceId= context.getResources().getIdentifier(csv_file, "raw", context.getPackageName());
                exercises=readAndRetrieveExercises(context, resourceId);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new MyAdapter(getApplicationContext(), exercises));
                break;
            case "6d_maint":
                csv_file = "d6_maint";
                resourceId= context.getResources().getIdentifier(csv_file, "raw", context.getPackageName());
                exercises=readAndRetrieveExercises(context, resourceId);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new MyAdapter(getApplicationContext(), exercises));
                break;
            case "2d_cut":
                csv_file = "d2_cut";
                resourceId= context.getResources().getIdentifier(csv_file, "raw", context.getPackageName());
                exercises=readAndRetrieveExercises(context, resourceId);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new MyAdapter(getApplicationContext(), exercises));
                break;
            case "4d_cut":
                csv_file = "d4_cut";
                resourceId= context.getResources().getIdentifier(csv_file, "raw", context.getPackageName());
                exercises=readAndRetrieveExercises(context, resourceId);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new MyAdapter(getApplicationContext(), exercises));
                break;
            case "6d_cut":
                csv_file = "d6_cut";
                resourceId= context.getResources().getIdentifier(csv_file, "raw", context.getPackageName());
                exercises=readAndRetrieveExercises(context, resourceId);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new MyAdapter(getApplicationContext(), exercises));
                break;
            default:
                Toast.makeText(this, "Problem", Toast.LENGTH_SHORT).show();
                break;
        }

    }



    public static List<Exercise> readAndRetrieveExercises(Context context, int resourceId) {
        List<Exercise> exercises = new ArrayList<>();

        // Open the CSV file from the raw resources
        InputStream inputStream = context.getResources().openRawResource(resourceId);

        // Create a BufferedReader to read the file line by line
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            // Read and discard the header
            String header = reader.readLine();

            // Iterate through each line in the CSV file
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into an array of values using semicolon as the delimiter
                String[] values = line.split(";");

                // Check if the array has enough elements before accessing them
                if (values.length >= 5) {
                    // Extract the file name without extension from the CSV
                    String imageName = values[4].split("\\.")[0];

                    // Create an Exercise object and add it to the list
                    exercises.add(new Exercise(
                            values[0],
                            values[1],
                            values[2],
                            values[3].isEmpty() ? "0" : values[3], // Handle empty weight values
                            context.getResources().getIdentifier(imageName, "drawable", context.getPackageName())
                    ));
                } else {
                    // Handle the case where there are not enough elements in the array
                    System.out.println("Invalid line in CSV file: " + String.join("; ", values));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return exercises;
    }


}


