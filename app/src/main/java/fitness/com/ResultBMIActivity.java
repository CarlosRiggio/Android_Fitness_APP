package fitness.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class ResultBMIActivity extends AppCompatActivity {

    TextView bmi_display, bmi_category, gender_display;
    Intent intent;
    ImageView imageView;
    String string_bmi;
    double bmi;
    String gender;
    RelativeLayout background;

    SeekBar bmiSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_bmiactivity);

        intent = getIntent();

        imageView = findViewById(R.id.image_view);
        bmi_display = findViewById(R.id.bmidisplay);
        bmi_category = findViewById(R.id.bmicategorydispaly);
        gender_display = findViewById(R.id.genderdisplay);
        background = findViewById(R.id.contentlayout);

        bmi = intent.getDoubleExtra("bmi", -999);
        string_bmi = intent.getStringExtra("string_bmi");
        gender = intent.getStringExtra("gender");


        plottingBmiScoreForMaleFemale(gender, bmi, string_bmi);

    }

    public void plottingBmiScoreForMaleFemale(String gender, double bmi, String string_bmi){
        Drawable progressDrawable;

        if(gender.equals("Male"))
        {
            // bmi score for male
            if(bmi < 16)
            {
                bmi_category.setText("Severe Thinness");
                background.setBackgroundColor(getColor(R.color.warn));
                imageView.setImageResource(R.drawable.crosss);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_red, null);

            }
            else if(bmi < 16.9 && bmi > 16)
            {
                bmi_category.setText("Moderate Thinness");
                background.setBackgroundColor(getColor(R.color.halfwarn));
                imageView.setImageResource(R.drawable.warning);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_red, null);

            }
            else if(bmi < 18.4 && bmi > 17)
            {
                bmi_category.setText("Mild Thinness");
                background.setBackgroundColor(getColor(R.color.halfwarn));
                imageView.setImageResource(R.drawable.warning);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_red, null);
            }
            else if(bmi < 20 && bmi > 18.5)
            {
                bmi_category.setText("Underweight");
                background.setBackgroundColor(getColor(R.color.halfwarn));
                imageView.setImageResource(R.drawable.warning);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_yellow, null);
            }
            else if(bmi < 24.9 && bmi > 20.1)
            {
                bmi_category.setText("Normal");
                imageView.setImageResource(R.drawable.ok);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_green, null);
            }
            else if(bmi < 29.9 && bmi > 25)
            {
                bmi_category.setText("Overweight");
                background.setBackgroundColor(getColor(R.color.halfwarn));
                imageView.setImageResource(R.drawable.warning);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_yellow, null);
            }
            else if(bmi < 35 && bmi > 30)
            {
                bmi_category.setText("Obese Class I");
                background.setBackgroundColor(getColor(R.color.halfwarn));
                imageView.setImageResource(R.drawable.warning);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_yellow, null);
            }
            else if (bmi < 40 && bmi > 35)
            {
                bmi_category.setText("Obese Class II");
                background.setBackgroundColor(getColor(R.color.warn));
                imageView.setImageResource(R.drawable.crosss);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_red, null);
            }
            else
            {
                bmi_category.setText("Obese Class III");
                background.setBackgroundColor(getColor(R.color.warn));
                imageView.setImageResource(R.drawable.crosss);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_red, null);
            }
        }
        else
        {
            // bmi score for female
            if(bmi < 16)
            {
                bmi_category.setText("Severe Thinness");
                background.setBackgroundColor(getColor(R.color.warn));
                imageView.setImageResource(R.drawable.crosss);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_red, null);

            }
            else if(bmi < 16.9 && bmi > 16)
            {
                bmi_category.setText("Moderate Thinness");
                background.setBackgroundColor(getColor(R.color.halfwarn));
                imageView.setImageResource(R.drawable.warning);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_red, null);

            }
            else if(bmi < 17.9 && bmi > 17)
            {
                bmi_category.setText("Mild Thinness");
                background.setBackgroundColor(getColor(R.color.halfwarn));
                imageView.setImageResource(R.drawable.warning);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_red, null);
            }
            else if(bmi < 18.7 && bmi > 17.9)
            {
                bmi_category.setText("Underweight");
                background.setBackgroundColor(getColor(R.color.halfwarn));
                imageView.setImageResource(R.drawable.warning);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_yellow, null);
            }
            else if(bmi < 23.8 && bmi > 18.7)
            {
                bmi_category.setText("Normal");
                imageView.setImageResource(R.drawable.ok);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_green, null);
            }
            else if(bmi < 28.6 && bmi > 23.8)
            {
                bmi_category.setText("Overweight");
                background.setBackgroundColor(getColor(R.color.halfwarn));
                imageView.setImageResource(R.drawable.warning);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_yellow, null);
            }
            else if(bmi < 35 && bmi > 28.6)
            {
                bmi_category.setText("Obese Class I");
                background.setBackgroundColor(getColor(R.color.halfwarn));
                imageView.setImageResource(R.drawable.warning);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_yellow, null);
            }
            else if (bmi < 40 && bmi > 35)
            {
                bmi_category.setText("Obese Class II");
                background.setBackgroundColor(getColor(R.color.warn));
                imageView.setImageResource(R.drawable.crosss);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_red, null);
            }
            else
            {
                bmi_category.setText("Obese Class III");
                background.setBackgroundColor(getColor(R.color.warn));
                imageView.setImageResource(R.drawable.crosss);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_red, null);
            }
        }

        bmiSeekBar = findViewById(R.id.bmiSeekBar);
        bmiSeekBar.setEnabled(false);
        int calculatedBMI = (int) bmi;
        bmiSeekBar.setProgress(calculatedBMI);

        bmiSeekBar.setProgressDrawable(progressDrawable);

        gender_display.setText(gender);
        bmi_display.setText(string_bmi);

    }

    public void openBMICalculator(View view){
        Intent intent = new Intent(this, BMIActivity.class);

        startActivity(intent);
        finish();
    }
}