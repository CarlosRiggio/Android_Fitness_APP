package fitness.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import com.opencsv.CSVWriter;

public class BMIActivity extends AppCompatActivity {

    // list with data to write in the csv for the plotting
    private List<Datas> datiList = new ArrayList<>();
    // initializing all of View components
    TextView currentheight, currentage, currentweight;
    ImageView incrementage, decrementage;
    RelativeLayout male, female;
    SeekBar seekbarforheight;

    // initializing variables to process the data
    double weight;
    int age;
    int currentprogress;
    String string_progress = "170";
    String typeofuser = "0"; // 0 = "neither male or female", valid only the first time the app is opened
    String string_weight;
    String string_age;


    // creating key - value pair to remember
    public static final String GenderState = "GenderKey";
    public static final String HeightState = "HeightKey";
    public static final String WeightState = "WeightKey";
    public static final String AgeState = "AgeKey";
    public static final String BmiState = "BmiKey";
    public static final String MyPREFERENCES = "MyPrefs";

    // creating sharedpreferences object
    SharedPreferences sharedpreferences;

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

        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

        // loading the seek bar progress
        seekbarforheight = findViewById(R.id.seekbar_for_height);
        seekbarforheight.setMax(300);

        // instantiate sharedpreferences object with mypreferences, private mode
        sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

        // checking if there are any preferences in the gender
        typeofuser = sharedpreferences.getString(GenderState, "0");
        if(typeofuser.equals("Male"))
        {
            male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
            female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
        }
        else if(typeofuser.equals("Female"))
        {
            female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
            male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
        }

        // checking if there are any preferences in the height
        string_progress = sharedpreferences.getString(HeightState, "170");
        seekbarforheight.setProgress(Integer.parseInt(string_progress));
        currentheight.setText(string_progress);

        // checking if there are any preferences in the age
        string_age = sharedpreferences.getString(AgeState, null);
        currentage.setText(string_age);


        // checking if there are any preferences in the weight
        string_weight = sharedpreferences.getString(WeightState, null);
        currentweight.setText(string_weight);


        // clicking male button
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // changing the color background
                male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typeofuser="Male";

                // creating editor interface to modify preferences
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(GenderState, typeofuser);
                editor.apply();

            }
        });

        // clicking female button
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // changing the color background
                female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typeofuser="Female";

                // creating editor interface to modify preferences
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(GenderState, typeofuser);
                editor.apply();
            }
        });

        // seekbar is changing
        seekbarforheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // updating the progress bar
                currentprogress = progress;
                string_progress = String.valueOf(currentprogress);
                currentheight.setText(string_progress);

                // creating editor interface to modify preferences
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(HeightState, string_progress);
                editor.apply();

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
                    // getting the numerical age from text
                    string_age = currentage.getText().toString();
                    age = Integer.parseInt(string_age);
                    // incrementing the age
                    age ++;
                    // printing the age converted
                    string_age = String.valueOf(age);
                    currentage.setText(string_age);

                    // creating editor interface to modify preferences
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(AgeState, string_age);
                    editor.apply();
                    System.out.println(age);
                }
                catch (NumberFormatException exception) // age missing
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
                    // getting the numerical age from text
                    string_age = currentage.getText().toString();
                    age = Integer.parseInt(string_age);
                    // decrementing the age
                    age --;
                    string_age = String.valueOf(age);
                    currentage.setText(string_age);
                    // creating editor interface to modify preferences
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(AgeState, string_age);
                    editor.apply();
                }
                catch (NumberFormatException exception) // age missing
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
            // creating editor interface to modify preferences
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(WeightState, string_weight);
            editor.apply();

            // getting the age
            string_age = currentage.getText().toString();
            age = Integer.parseInt(string_age);
            // creating editor interface to modify preferences
            editor.putString(AgeState, string_age);
            editor.apply();

            // getting the height
            currentprogress = seekbarforheight.getProgress();
            string_progress = String.valueOf(currentprogress);
            // creating editor interface to modify preferences
            editor.putString(HeightState, string_progress);
            editor.apply();



            if(typeofuser.equals("0")) // no gender selected
            {
                Toast.makeText(getApplicationContext(),"Select Your Gender First",Toast.LENGTH_SHORT).show();
            }
            else if(string_progress.equals("0")) // no height selected
            {
                Toast.makeText(getApplicationContext(),"Select Your Height First",Toast.LENGTH_SHORT).show();
            }
            else if(age == 0 || age < 0) // invalid age selected
            {
                System.out.println(age);
                Toast.makeText(getApplicationContext(),"Select a Valid Age",Toast.LENGTH_SHORT).show();
            }

            else if(weight == 0 || weight < 0) // invalid weight selected
            {
                Toast.makeText(getApplicationContext(),"Select a Valid Weight",Toast.LENGTH_SHORT).show();
            }
            else {
                // BMI calculation (height must be in meters)
                double height = (double) currentprogress / 100;
                double bmi = weight / (height * height);
                // rounding to 2 decimal points
                DecimalFormat df = new DecimalFormat("#.##");
                String string_bmi = df.format(bmi);
                editor.putString("BmiKey", string_bmi);
                editor.apply();

                // getting the local date
                LocalDate currentDate = LocalDate.now();
                String date = currentDate.getDayOfMonth() + "-" + currentDate.getMonthValue() + "-" + currentDate.getYear();

                // writing new data in the list
                datiList.add(new Datas(date, string_weight, string_bmi));

                // save the data async in the csv
                new AsyncDataSave().execute();
                Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, ResultBMIActivity.class);
                intent.putExtra("gender", typeofuser);
                intent.putExtra("string_bmi", string_bmi);
                intent.putExtra("age", age);
                startActivity(intent);

            }
        }
        catch (NumberFormatException exception) // missing some information
        {
            Toast.makeText(getApplicationContext(),"Insert all of the data",Toast.LENGTH_SHORT).show();
        }
    }


    // class that save the data in async way
    private class AsyncDataSave extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            saveDataInCSV();
            return null;
        }
    }

    // method that handle the writing of new data inside CSV file
    private void saveDataInCSV() {
        try {
            File file = new File(getExternalFilesDir(null), "data.csv");

            // check if the file already exist
            boolean isFileExist = file.exists();

            // FileWriter opened in append mode
            FileWriter fileWriter = new FileWriter(file, true);
            CSVWriter csvWriter = new CSVWriter(fileWriter);

            // Write itentation if file don't exist yet
            if (!isFileExist) {
                csvWriter.writeNext(new String[]{"Data", "Weight", "BMI"});
            }

            // Writing new data
            for (Datas data : datiList) {
                csvWriter.writeNext(new String[]{data.getLocalData(), String.valueOf(data.getWeight()), String.valueOf(data.getBmi())});
            }

            // Close writer
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}