package fitness.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashBMIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_bmiactivity);


        // Intent to splash activity that last 1.5s
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashBMIActivity.this, BMIActivity.class);
            startActivity(intent);
            finish();
        },1500);

    }
}