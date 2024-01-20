package fitness.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class WorkoutSelActivity extends AppCompatActivity {

    RadioGroup radio_bulk, radio_maint, radio_cut;
    RelativeLayout bulk, maint, cut;
    String typeofworkout = "";

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
                typeofworkout = "Maintenance";

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
}