package fitness.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Food_Workout_Tips extends AppCompatActivity {

    private Button bulkButton;
    private Button maintenanceButton;
    private Button cutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_workout_tips);

        bulkButton = (Button) findViewById(R.id.bulkButton);
        bulkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBulkTips();
            }
        });

        maintenanceButton = (Button) findViewById(R.id.maintenanceButton);
        maintenanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMaintenanceTips();
            }
        });

        cutButton = (Button) findViewById(R.id.cutButton);
        cutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCutTips();
            }
        });

    }

    public void openBulkTips() {
        Intent intent = new Intent(this, Bulk_advices.class);
        startActivity(intent);
    }

    public void openMaintenanceTips() {
        Intent intent = new Intent(this, Maintenance_advices.class);
        startActivity(intent);
    }

    public void openCutTips() {
        Intent intent = new Intent(this, Cut_advices.class);
        startActivity(intent);
    }

}