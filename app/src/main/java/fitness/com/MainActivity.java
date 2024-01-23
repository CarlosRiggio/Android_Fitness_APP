package fitness.com;

import static fitness.com.BMIActivity.MyPREFERENCES;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // creating key - value pair to remember
    public static final String GenderState = "GenderKey";
    public static final String AgeState = "AgeKey";
    public static final String BmiState = "BmiKey";

    // creating sharedpreferences object
    SharedPreferences sharedpreferences;

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

    public void openWorkoutSelectionPage(View view){
        // intent go to splash activity that last 1.5s just to show up
        Intent intent = new Intent(this, SplashWorkoutActivity.class);
        startActivity(intent);
    }

    public void openPreviousBMIData(View view){
        try {
            // instantiate sharedpreferences object with mypreferences, private mode
            sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

            // checking for the preferences

            String typeofuser = sharedpreferences.getString(GenderState, "0");
            String string_bmi = sharedpreferences.getString(BmiState, "-999");
            String string_age = sharedpreferences.getString(AgeState, null);
            int age = Integer.parseInt(string_age);

            // intent goes to the previous BMI results activity
            Intent intent = new Intent(this, ResultBMIActivity.class);
            intent.putExtra("gender", typeofuser);
            intent.putExtra("string_bmi", string_bmi);
            intent.putExtra("age", age);
            startActivity(intent);
        }
        catch (NumberFormatException exception)
        {
            Toast.makeText(getApplicationContext(),"You must calculate your BMI first",Toast.LENGTH_SHORT).show();
        }

    }
    public void openGraph(View view){
        Intent intent = new Intent(MainActivity.this, Graph.class);
        startActivity(intent);
    }

    public void openSetTargetActivity(View view){
        Intent intent = new Intent(this, SetTargetActivity.class);
        startActivity(intent);
    }

}