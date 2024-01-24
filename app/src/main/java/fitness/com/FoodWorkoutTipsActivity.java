package fitness.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FoodWorkoutTipsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_workout_tips);
    }


    public void openBulkTips(View view) {
        Intent intent = new Intent(this, Bulk_advices.class);
        startActivity(intent);
    }

    public void openMaintenanceTips(View view) {
        Intent intent = new Intent(this, Maintenance_advices.class);
        startActivity(intent);
    }

    public void openCutTips(View view) {
        Intent intent = new Intent(this, Cut_advices.class);
        startActivity(intent);
    }

}