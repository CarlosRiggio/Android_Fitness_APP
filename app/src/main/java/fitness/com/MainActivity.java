package fitness.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button8; //Target button
    private Button button9; //Target button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button8 = (Button) findViewById(R.id.button8); //Food & Workout Tips Button
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openFoodWorkoutTips(); }
        });

        button9 = (Button) findViewById(R.id.button9); //Target button
        button9.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                openSetTarget();
            }
        });
    }

    public void openFoodWorkoutTips() {
        Intent intent = new Intent(this, Food_Workout_Tips.class);
        startActivity(intent);
    }

    public void openSetTarget() { //Target button
        Intent intent = new Intent(this, setTarget.class);
        startActivity(intent);
    }

}