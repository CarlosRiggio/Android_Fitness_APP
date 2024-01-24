package fitness.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class Maintenance_advices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_advices);
        String[] strings = {
                "Maintain a balanced calorie intake, consuming enough to support your current weight",
                "Focus on a mix of cardiovascular and strength exercises to maintain overall fitness",
                "Vary your workout routine to avoid boredom and consistently challenge yourself",
                "Include nutrient-rich foods like fruits, vegetables, and lean proteins in your daily diet",
                "Adjust portion sizes based on your daily energy needs, avoiding excess",
                "Schedule at least 3-4 workout sessions per week to sustain muscle strength and tone",
                "Ensure an adequate protein intake to support muscle mass during maintenance",
                "Regularly get blood tests to monitor nutrient levels and caloric intake",
                "Stay hydrated with enough water to maintain proper overall health",
                "Practice flexibility and mobility exercises to preserve a full range of motion",
                "Experiment with different sports or physical activities to maintain interest and motivation",
                "Limit consumption of added sugars and highly processed foods for improved overall health",
                "Make small adjustments in your diet based on your needs but maintain a solid nutritional foundation",
                "Remember to rest adequately; recovery is crucial for maintaining performance",
                "Consult with a nutritionist for a personalized meal plan that supports maintenance",
                "Be mindful of your portions to avoid unwanted weight gain during maintenance",
                "Engage in relaxing activities like yoga or meditation to manage stress",
                "Choose whole, nutrient-dense foods over high-calorie, nutrient-poor options",
                "Keep a record of your physical activities and eating habits to stay in control of your progress",
                "Set realistic and sustainable goals for long-term maintenance of your fitness level"
        };
        int randomNumber = new Random().nextInt(20);
        ((TextView) findViewById(R.id.advice_maintain)).setText(strings[randomNumber]);
    }
}