package fitness.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openBMICalculator(View view){
        // intent go to splash activity that last 1.5s just to show up
        Intent intent = new Intent(this, SplashBMIActivity.class);
        startActivity(intent);
    }
}