package fitness.com;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Random;

public class Cut_advices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cut_advices);
        String[] strings = {
                "Incorporate high-intensity cardiovascular exercises, such as interval training, to burn more calories",
                "Include resistance training to preserve muscle mass during weight loss",
                "Consume smaller portions and plan frequent meals to keep metabolism active",
                "Limit intake of added sugars and processed foods to reduce empty calories",
                "Increase fiber intake to promote satiety and improve digestion",
                "Stay consistently hydrated; thirst is often mistaken for hunger",
                "Avoid skipping meals; maintain a regular eating schedule",
                "Opt for lean protein-rich foods to support the maintenance of muscle mass",
                "Keep a record of your meals and workouts to track progress and stay motivated",
                "Limit consumption of calorie-laden beverages, favoring water and unsweetened tea",
                "Experiment with thermogenic foods like chili and ginger to boost metabolism",
                "Prepare healthy meals in advance to avoid impulsive food choices",
                "Consult with a nutritionist for a personalized weight loss meal plan",
                "Incorporate interval training exercises to maximize post-workout calorie burn",
                "Focus on quality sleep; lack of sleep can impact weight loss",
                "Limit alcohol intake, as it can interfere with fat metabolism",
                "Eat mindfully; slow down and savor each bite to prevent overeating",
                "Avoid eating too close to bedtime to facilitate digestion",
                "Include omega-3-rich foods like salmon to promote fat burning",
                "Seek the support of a personal trainer or group to stay motivated during the weight loss journey"
        };
        int randomNumber = new Random().nextInt(20);
        ((TextView) findViewById(R.id.advice_cut)).setText(strings[randomNumber]);
    }
}
