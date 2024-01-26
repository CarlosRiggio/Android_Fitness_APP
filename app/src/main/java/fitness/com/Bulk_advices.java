package fitness.com;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Random;

public class Bulk_advices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulk_advices);

        String[] strings = {
                "Prioritize protein-rich foods like chicken, fish, eggs, and dairy to support muscle growth",
                "Incorporate complex carbohydrates such as oats and brown rice for energy during intense workouts",
                "Experiment with compound exercises like squats and deadlifts to maximize muscle development",
                "Maintain a moderate caloric surplus to support growth, avoiding excess that could lead to fat gain",
                "Ensure sufficient intake of healthy fats, such as those from avocados and nuts, for hormonal balance",
                "Program resistance workouts with higher sets and repetitions to promote muscle hypertrophy",
                "Snack on post-workout protein to expedite recovery and protein synthesis",
                "Include micronutrient-rich foods like leafy greens to support overall health",
                "Stay hydrated; drink enough water to keep your body hydrated during muscle growth",
                "Variety in foods is crucial; don't limit yourself to a single source of protein or carbohydrates",
                "Add supplements like creatine to enhance strength and performance in your workouts",
                "Get sufficient sleep; recovery mainly occurs during rest",
                "Minimize alcohol consumption, as it can interfere with protein synthesis",
                "Keep a workout journal to track progress and make any necessary adjustments to your plan",
                "Prepare meals in advance to avoid unhealthy food choices",
                "Allow adequate recovery breaks between workout sessions to prevent overtraining",
                "Incorporate fiber-rich foods to promote digestion and gut health",
                "Set realistic short and long-term goals to maintain motivation",
                "Consult with a fitness professional or nutritionist for a personalized plan",
                "Be consistent, muscle growth takes time and consistent effort"
        };

        int randomNumber = new Random().nextInt(strings.length);

        // further implementation may be a daily notification that remember the user to see the
        // daily advice
        // additionally changing the textView by tapping some specific area of the page
        ((TextView) findViewById(R.id.advice_bulk)).setText(strings[randomNumber]);
    }
}