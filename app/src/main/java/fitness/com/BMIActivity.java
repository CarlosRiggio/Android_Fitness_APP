package fitness.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class BMIActivity extends AppCompatActivity {

    TextView currentheight, currentage, currentweight;
    ImageView incrementage, decrementage;
    SeekBar seekbarforheight;
    RelativeLayout male, female;

    double weight = 55;
    int age;
    int currentprogress;
    String string_progress = "170";
    String typeofuser = "0";
    String string_weight = "55";
    String string_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);


        // finding all of the view object by id
        currentheight = findViewById(R.id.current_height);
        currentage = findViewById(R.id.current_age);
        currentweight = findViewById(R.id.current_weight);

        incrementage = findViewById(R.id.increment_age);
        decrementage = findViewById(R.id.decrement_age);

        seekbarforheight = findViewById(R.id.seekbar_for_height);

        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

        // clicking male button
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typeofuser="Male";

            }
        });

        // clicking female button
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typeofuser="Female";
            }
        });

        // loading the seek bar progress
        seekbarforheight.setMax(300);
        seekbarforheight.setProgress(170);
        seekbarforheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                currentprogress = progress;
                string_progress = String.valueOf(currentprogress);
                currentheight.setText(string_progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        // increment the age
        incrementage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    string_age = currentage.getText().toString();
                    age = Integer.parseInt(string_age);

                    age ++;
                    string_age = String.valueOf(age);
                    currentage.setText(string_age);
                }
                catch (NumberFormatException exception)
                {
                    Toast.makeText(getApplicationContext(),"Insert all of the data",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // decrement the age
        decrementage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    string_age = currentage.getText().toString();
                    age = Integer.parseInt(string_age);

                    age --;
                    string_age = String.valueOf(age);
                    currentage.setText(string_age);
                }
                catch (NumberFormatException exception)
                {
                    Toast.makeText(getApplicationContext(),"Insert all of the data",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void openBMIResult(View view){


        try {
            // getting the weight
            string_weight = currentweight.getText().toString();
            weight = Double.parseDouble(string_weight);
            if(typeofuser.equals("0"))
            {
                Toast.makeText(getApplicationContext(),"Select Your Gender First",Toast.LENGTH_SHORT).show();
            }
            else if(string_progress.equals("0"))
            {
                Toast.makeText(getApplicationContext(),"Select Your Height First",Toast.LENGTH_SHORT).show();
            }
            else if(age == 0 || age < 0)
            {
                Toast.makeText(getApplicationContext(),"Select a Valid Age",Toast.LENGTH_SHORT).show();
            }

            else if(weight == 0 || weight < 0)
            {
                Toast.makeText(getApplicationContext(),"Select a Valid Weight",Toast.LENGTH_SHORT).show();
            }
            else {

                Intent intent = new Intent(this, ResultBMIActivity.class);
                intent.putExtra("gender", typeofuser);
                intent.putExtra("string_height", string_progress);
                intent.putExtra("string_weight", string_weight);
                intent.putExtra("string_age", string_age);
                startActivity(intent);

            }
        }
        catch (NumberFormatException exception)
        {
            Toast.makeText(getApplicationContext(),"Insert all of the data",Toast.LENGTH_SHORT).show();
        }
    }
}