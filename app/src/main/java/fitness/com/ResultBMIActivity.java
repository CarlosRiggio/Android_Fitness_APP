package fitness.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ResultBMIActivity extends AppCompatActivity {

    // initializing all of the view
    TextView bmi_display, bmi_category, gender_display, suggestion_display;
    ImageView imageView;

    // initializing the explicit intent to receive
    Intent intent;
    String string_bmi;
    double bmi;
    int age;
    String gender;
    RelativeLayout background;
    SeekBar bmiSeekBar;
    RadioGroup rdgroup;
    int radio_sel_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_bmiactivity);

        // finding view by id
        imageView = findViewById(R.id.image_view);
        bmi_display = findViewById(R.id.bmidisplay);
        bmi_category = findViewById(R.id.bmicategorydispaly);
        gender_display = findViewById(R.id.genderdisplay);
        background = findViewById(R.id.contentlayout);
        suggestion_display = findViewById(R.id.suggestion);
        rdgroup = findViewById(R.id.radio_group);
        rdgroup.clearCheck();

        // getting the intent
        intent = getIntent();
        string_bmi = intent.getStringExtra("string_bmi");
        bmi = Double.parseDouble(string_bmi);
        gender = intent.getStringExtra("gender");
        age = intent.getIntExtra("age", -999);


        plottingBmiScoreForMaleFemale(gender, bmi, string_bmi);
        plottingRadioChanges();

        suggestion();

    }

    public void plottingBmiScoreForMaleFemale(String gender, double bmi, String string_bmi){
        Drawable progressDrawable;

        if(gender.equals("Male"))
        {
            // bmi score for male
            if(bmi < 16)
            {
                bmi_category.setText(R.string.severe_thinness);
                background.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.warn_background, null));
                imageView.setImageResource(R.drawable.crosss);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_red, null);

            }
            else if(bmi < 16.9 && bmi > 16)
            {
                bmi_category.setText(R.string.moderate_thinness);
                background.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.half_warn_background, null));
                imageView.setImageResource(R.drawable.warning);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_red, null);

            }
            else if(bmi < 18.4 && bmi > 17)
            {
                bmi_category.setText(R.string.mild_thinness);
                background.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.half_warn_background, null));
                imageView.setImageResource(R.drawable.warning);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_red, null);
            }
            else if(bmi < 20 && bmi > 18.5)
            {
                bmi_category.setText(R.string.underweight);
                background.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.half_warn_background, null));
                imageView.setImageResource(R.drawable.warning);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_yellow, null);
            }
            else if(bmi < 24.9 && bmi > 20.1)
            {
                bmi_category.setText(R.string.normal);
                background.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.ok_background, null));
                imageView.setImageResource(R.drawable.ok);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_green, null);
            }
            else if(bmi < 29.9 && bmi > 25)
            {
                bmi_category.setText(R.string.overweight);
                background.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.half_warn_background, null));
                imageView.setImageResource(R.drawable.warning);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_yellow, null);
            }
            else if(bmi < 35 && bmi > 30)
            {
                bmi_category.setText(R.string.obese_class_i);
                background.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.half_warn_background, null));
                imageView.setImageResource(R.drawable.warning);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_yellow, null);
            }
            else if (bmi < 40 && bmi > 35)
            {
                bmi_category.setText(R.string.obese_class_ii);
                background.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.warn_background, null));
                imageView.setImageResource(R.drawable.crosss);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_red, null);
            }
            else
            {
                bmi_category.setText(R.string.obese_class_iii);
                background.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.warn_background, null));
                imageView.setImageResource(R.drawable.crosss);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_red, null);
            }
        }
        else
        {
            // bmi score for female
            if(bmi < 16)
            {
                bmi_category.setText(R.string.severe_thinness);
                background.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.warn_background, null));
                imageView.setImageResource(R.drawable.crosss);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_red, null);

            }
            else if(bmi < 16.9 && bmi > 16)
            {
                bmi_category.setText(R.string.moderate_thinness);
                background.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.half_warn_background, null));
                imageView.setImageResource(R.drawable.warning);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_red, null);

            }
            else if(bmi < 17.9 && bmi > 17)
            {
                bmi_category.setText(R.string.mild_thinness);
                background.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.half_warn_background, null));
                imageView.setImageResource(R.drawable.warning);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_red, null);
            }
            else if(bmi < 18.7 && bmi > 17.9)
            {
                bmi_category.setText(R.string.underweight);
                background.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.half_warn_background, null));
                imageView.setImageResource(R.drawable.warning);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_yellow, null);
            }
            else if(bmi < 23.8 && bmi > 18.7)
            {
                bmi_category.setText(R.string.normal);
                background.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.ok_background, null));
                imageView.setImageResource(R.drawable.ok);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_green, null);
            }
            else if(bmi < 28.6 && bmi > 23.8)
            {
                bmi_category.setText(R.string.overweight);
                background.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.half_warn_background, null));
                imageView.setImageResource(R.drawable.warning);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_yellow, null);
            }
            else if(bmi < 35 && bmi > 28.6)
            {
                bmi_category.setText(R.string.obese_class_i);
                background.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.half_warn_background, null));
                imageView.setImageResource(R.drawable.warning);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_yellow, null);
            }
            else if (bmi < 40 && bmi > 35)
            {
                bmi_category.setText(R.string.obese_class_ii);
                background.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.warn_background, null));
                imageView.setImageResource(R.drawable.crosss);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_red, null);
            }
            else
            {
                bmi_category.setText(R.string.obese_class_iii);
                background.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.warn_background, null));
                imageView.setImageResource(R.drawable.crosss);
                progressDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.progress_red, null);
            }
        }

        // finding the seekbar and setting the progress
        bmiSeekBar = findViewById(R.id.bmiSeekBar);
        bmiSeekBar.setEnabled(false);
        int calculatedBMI = (int) bmi;
        bmiSeekBar.setProgress(calculatedBMI);
        bmiSeekBar.setProgressDrawable(progressDrawable);

        // showing gender
        gender_display.setText(gender);

        // showing numerical bmi score
        bmi_display.setText(string_bmi);
    }

    public void plottingRadioChanges(){

        rdgroup = findViewById(R.id.radio_group);
        rdgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                TextView sugg = findViewById(R.id.suggestion);
                String testo = sugg.getText().toString();

                if(radioButton.getId() == R.id.radio_bulk)
                {
                    if(testo.equals("I suggest you to bulk"))
                        Toast.makeText(getApplicationContext(),"Right choice",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(),testo,Toast.LENGTH_SHORT).show();
                }
                else if(radioButton.getId() == R.id.radio_maintenance)
                {
                    if(testo.equals("I suggest you to maintain"))
                        Toast.makeText(getApplicationContext(),"Right choice",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(),testo,Toast.LENGTH_SHORT).show();
                }
                else if (radioButton.getId() == R.id.radio_cut)
                {
                    if(testo.equals("I suggest you to cut"))
                        Toast.makeText(getApplicationContext(),"Right choice",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(),testo,Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void openBMICalculator(View view){
        Intent intent = new Intent(this, BMIActivity.class);

        startActivity(intent);
        finish();
    }

    public void openWorkoutSelectionPage(View view){

        rdgroup = findViewById(R.id.radio_group);


        if(rdgroup.getCheckedRadioButtonId() != R.id.radio_bulk && rdgroup.getCheckedRadioButtonId() != R.id.radio_maintenance && rdgroup.getCheckedRadioButtonId() != R.id.radio_cut)
        {
            Toast.makeText(this, "Select a workout plan", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(this, WorkoutSelActivity.class);
            radio_sel_id = rdgroup.getCheckedRadioButtonId();

            // passing all of the info in order to correctly select the workout plan
            intent.putExtra("age", age);
            intent.putExtra("radio_id", radio_sel_id);
            intent.putExtra("bulk_id", R.id.radio_bulk);
            intent.putExtra("maint_id", R.id.radio_maintenance);
            intent.putExtra("cut_id", R.id.radio_cut);

            startActivity(intent);
        }

    }

    public void suggestion(){
        bmi_category = findViewById(R.id.bmicategorydispaly);
        String category = bmi_category.getText().toString();

        suggestion_display = findViewById(R.id.suggestion);

        if (category.equals("Severe Thinness") || category.equals("Moderate Thinness") || category.equals("Mild Thinness") || category.equals("Underweight"))
        {
            suggestion_display.setText(R.string.i_suggest_you_to_bulk);
        }
        else if (category.equals("Normal"))
        {
            suggestion_display.setText(R.string.i_suggest_you_to_maintain);
        }
        else
        {
            suggestion_display.setText(R.string.i_suggest_you_to_cut);
        }
    }

}