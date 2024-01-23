package fitness.com;

import static fitness.com.BMIActivity.MyPREFERENCES;
import static fitness.com.BMIActivity.WeightState;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Workout extends AppCompatActivity {
    String type_day_workout = "";
    Intent intent;
    int resourceId ;
    String csv_file="";
    SharedPreferences sharedPreferences;
    TextView workout_type_text, day_workout_text, workout_duration_text, number_exercises;
    String weight_string;
    double weight;

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
        number_exercises = findViewById(R.id.number_of_exercises);

        // getting application context
        Context context = getApplicationContext();

        // getting preferences
        sharedPreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        weight_string = sharedPreferences.getString(WeightState, "");
        weight = Double.parseDouble(weight_string);

        List<Exercise> exercises = new ArrayList<>();

        switch (type_day_workout) {
            case "d2_bulk":
                printingWorkoutPlan("Bulking", "2", "90 min", "10", exercises, recyclerView, context);
                break;
            case "d4_bulk":
                printingWorkoutPlan("Bulking", "4", "75 min", "8", exercises, recyclerView, context);
                break;
            case "d6_bulk":
                printingWorkoutPlan("Bulking", "6", "60 min", "6", exercises, recyclerView, context);
                break;
            case "d2_maint":
                printingWorkoutPlan("Maintenance", "2", "90 min", "10", exercises, recyclerView, context);
                break;
            case "d4_maint":
                printingWorkoutPlan("Maintenance", "4", "75 min", "8", exercises, recyclerView, context);
                break;
            case "d6_maint":
                printingWorkoutPlan("Maintenance", "6", "60 min", "6", exercises, recyclerView, context);
                break;
            case "d2_cut":
                printingWorkoutPlan("Cutting", "2", "90 min", "10", exercises, recyclerView, context);
                break;
            case "d4_cut":
                printingWorkoutPlan("Cutting", "4", "75 min", "8", exercises, recyclerView, context);
                break;
            case "d6_cut":
                printingWorkoutPlan("Cutting", "6", "60 min", "6", exercises, recyclerView, context);
                break;
            default:
                Toast.makeText(this, "Problem", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    public List<Exercise> readAndRetrieveExercises(Context context, int resourceId) {
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

                    switch (values[3]) {
                        case "Your bodyweight x 1/5":

                            values[3] = values[3] + " = " + Math.round(weight * 1 / 5);
                            break;
                        case "Your bodyweight x 1/7":
                            values[3] = values[3] + " = " + Math.round(weight * 1 / 7);
                            break;
                        case "Your bodyweight x 1/2":
                            values[3] = values[3] + " = " + Math.round(weight * 1 / 2);
                            break;
                    }



                    // Create an Exercise object and add it to the list
                    exercises.add(new Exercise(
                            values[0],
                            "Sets x Reps: " + values[1],
                            "Recovery time: " + values[2],
                            values[3].isEmpty() ? "Weight: 0 Kg" : "Weight: " + values[3] + " Kg", // Handle empty weight values
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


    public void printingWorkoutPlan(String workout, String dayOfWorkout, String duration, String numEx, List<Exercise> exercises, RecyclerView recyclerView, Context context){
        workout_type_text.setText(workout);
        dayOfWorkout += " day";
        day_workout_text.setText(dayOfWorkout);
        workout_duration_text.setText(duration);
        numEx += " Exercises";
        number_exercises.setText(numEx);

        csv_file = type_day_workout;
        // possibile substitution with R.raw.type_day_workout with switch logic
        resourceId = context.getResources().getIdentifier(csv_file, "raw", context.getPackageName());
        exercises = readAndRetrieveExercises(context, resourceId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), exercises));
    }

}