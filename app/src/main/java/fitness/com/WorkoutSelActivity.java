package fitness.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class WorkoutSelActivity extends AppCompatActivity {

    RadioGroup radio_bulk, radio_maint, radio_cut;
    int radio_sel_id, age;
    int radio_bulk_prev_id, radio_maint_prev_id, radio_cut_prev_id;
    RelativeLayout bulk, maint, cut;
    String typeofworkout = "", type_day_workout = "";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_sel);

        // finding all of the view object by id
        bulk = findViewById(R.id.bulk_workout);
        maint = findViewById(R.id.maint_workout);
        cut = findViewById(R.id.cut_workout);

        radio_bulk = findViewById(R.id.radio_bulk_selection);
        radio_maint = findViewById(R.id.radio_maint_selection);
        radio_cut = findViewById(R.id.radio_cut_selection);

        // getting extra from the intent
        intent = getIntent();
        radio_sel_id = intent.getIntExtra("radio_id", -999);
        age = intent.getIntExtra("age", -999);
        // previous single radio id
        radio_bulk_prev_id = intent.getIntExtra("bulk_id", -999);
        radio_maint_prev_id = intent.getIntExtra("maint_id", -999);
        radio_cut_prev_id = intent.getIntExtra("cut_id", -999);


        suggestionLogic(radio_sel_id, age, radio_bulk_prev_id, radio_maint_prev_id, radio_cut_prev_id);

        // clicking muscle gain button
        bulk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // changing the color background
                bulk.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                maint.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                cut.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typeofworkout = "Bulk";

                // showing the radio button
                radio_bulk.setVisibility(View.VISIBLE);
                radio_cut.setVisibility(View.INVISIBLE);
                radio_maint.setVisibility(View.INVISIBLE);
            }
        });

        // clicking maintenance button
        maint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // changing the color background
                maint.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                bulk.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                cut.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typeofworkout = "Maint";

                // showing the radio button
                radio_maint.setVisibility(View.VISIBLE);
                radio_bulk.setVisibility(View.INVISIBLE);
                radio_cut.setVisibility(View.INVISIBLE);
            }
        });

        // clicking cut button
        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // changing the color background
                cut.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                bulk.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                maint.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typeofworkout = "Cut";

                // showing the radio button
                radio_cut.setVisibility(View.VISIBLE);
                radio_maint.setVisibility(View.INVISIBLE);
                radio_bulk.setVisibility(View.INVISIBLE);
            }
        });

    }

    public void suggestionLogic(int radio_sel_id, int age, int radio_bulk_prev_id, int radio_maint_prev_id, int radio_cut_prev_id){
        if (radio_sel_id == radio_bulk_prev_id && radio_sel_id != -999)
        {
            // changing the color background
            bulk.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
            maint.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
            cut.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
            typeofworkout = "Bulk";

            // showing the radio button
            radio_bulk.setVisibility(View.VISIBLE);
            radio_cut.setVisibility(View.INVISIBLE);
            radio_maint.setVisibility(View.INVISIBLE);

            if(age > 15 && age < 35)
            {
                radio_bulk.check(R.id.rd_bulk_6d);
            }
            else if (age > 35 && age < 50)
            {
                radio_bulk.check(R.id.rd_bulk_4d);
            }
            else
            {
                radio_bulk.check(R.id.rd_bulk_2d);
            }
        }
        else if(radio_sel_id == radio_maint_prev_id && radio_sel_id != -999)
        {
            // changing the color background
            maint.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
            bulk.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
            cut.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
            typeofworkout = "Maint";

            // showing the radio button
            radio_bulk.setVisibility(View.INVISIBLE);
            radio_cut.setVisibility(View.INVISIBLE);
            radio_maint.setVisibility(View.VISIBLE);

            if(age > 15 && age < 35)
            {
                radio_maint.check(R.id.rd_maint_6d);
            }
            else if (age > 35 && age < 50)
            {
                radio_maint.check(R.id.rd_maint_4d);
            }
            else
            {
                radio_maint.check(R.id.rd_maint_2d);
            }
        }
        else if (radio_sel_id == radio_cut_prev_id && radio_sel_id != -999)
        {
            // changing the color background
            cut.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
            bulk.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
            maint.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
            typeofworkout = "Cut";

            // showing the radio button
            radio_bulk.setVisibility(View.INVISIBLE);
            radio_cut.setVisibility(View.VISIBLE);
            radio_maint.setVisibility(View.INVISIBLE);

            if(age > 15 && age < 35)
            {
                radio_cut.check(R.id.rd_cut_6d);
            }
            else if (age > 35 && age < 50)
            {
                radio_cut.check(R.id.rd_cut_4d);
            }
            else
            {
                radio_cut.check(R.id.rd_cut_2d);
            }
        }
    }

    public void openSingleWorkout(View view){

        if(typeofworkout.equals("Bulk"))
        {
            if(radio_bulk.getCheckedRadioButtonId() == R.id.rd_bulk_2d)
            {
                // 2d bulk plan page
                type_day_workout = "2d_bulk";
                openIntentWorkout();
            }
            else if(radio_bulk.getCheckedRadioButtonId() == R.id.rd_bulk_4d)
            {
                // 4d bulk plan page
                type_day_workout = "4d_bulk";
                openIntentWorkout();
            }
            else if(radio_bulk.getCheckedRadioButtonId() == R.id.rd_bulk_6d)
            {
                // 6d bulk plan page
                type_day_workout = "6d_bulk";
                openIntentWorkout();
            }
            else
            {
                Toast.makeText(this, "Select a workout plan", Toast.LENGTH_SHORT).show();
            }
        }
        else if(typeofworkout.equals("Maint"))
        {
            if(radio_maint.getCheckedRadioButtonId() == R.id.rd_maint_2d)
            {
                // 2d maint plan page
                type_day_workout = "2d_maint";
                openIntentWorkout();
            }
            else if(radio_maint.getCheckedRadioButtonId() == R.id.rd_maint_4d)
            {
                // 4d maint plan page
                type_day_workout = "4d_maint";
                openIntentWorkout();
            }
            else if(radio_maint.getCheckedRadioButtonId() == R.id.rd_maint_6d)
            {
                // 6d maint plan page
                type_day_workout = "6d_maint";
                openIntentWorkout();
            }
            else
            {
                Toast.makeText(this, "Select a workout plan", Toast.LENGTH_SHORT).show();
            }
        }
        else if(typeofworkout.equals("Cut"))
        {
            if(radio_cut.getCheckedRadioButtonId() == R.id.rd_cut_2d)
            {
                // 2d cut plan page
                type_day_workout = "2d_cut";
                openIntentWorkout();
            }
            else if(radio_cut.getCheckedRadioButtonId() == R.id.rd_cut_4d)
            {
                // 4d cut plan page
                type_day_workout = "4d_cut";
                openIntentWorkout();
            }
            else if(radio_cut.getCheckedRadioButtonId() == R.id.rd_cut_6d)
            {
                // 6d cut plan page
                type_day_workout = "6d_cut";
                openIntentWorkout();
            }
            else
            {
                Toast.makeText(this, "Select a workout plan", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "Select a workout plan", Toast.LENGTH_SHORT).show();
        }
    }

    public void openIntentWorkout(){
        Intent intent_to_exercise = new Intent(this, Workout.class);
        intent_to_exercise.putExtra("type_day_workout_key", type_day_workout);
        startActivity(intent_to_exercise);
    }

}