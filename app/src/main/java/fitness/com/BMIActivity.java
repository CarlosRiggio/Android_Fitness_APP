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

    /* OBSERVATION: View.OnClickListener() can be replaced with lambda expression */

    // list with data to write in the csv for the plotting
    private List<Datas> datiList = new ArrayList<>();
    // initializing all of View components
    TextView currentheight, currentage, currentweight;
    ImageView incrementage, decrementage, incrementheight, decrementheight;
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
        incrementheight = findViewById(R.id.increment_height);
        decrementheight = findViewById(R.id.decrement_height);


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

        // increment the height
        incrementheight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting the numerical height from text
                string_progress = currentheight.getText().toString();
                currentprogress = Integer.parseInt(string_progress);
                // incrementing the height
                currentprogress ++;
                // printing the height converted
                string_progress = String.valueOf(currentprogress);
                currentheight.setText(string_progress);

                // creating editor interface to modify preferences
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(HeightState, string_progress);
                editor.apply();
            }
        });


        // decrement the height
        decrementheight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting the numerical height from text
                string_progress = currentheight.getText().toString();
                currentprogress = Integer.parseInt(string_progress);
                // incrementing the height
                currentprogress --;
                // printing the height converted
                string_progress = String.valueOf(currentprogress);
                currentheight.setText(string_progress);

                // creating editor interface to modify preferences
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(HeightState, string_progress);
                editor.apply();
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

    // calculating BMI score and opening result's page
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

            // checking the value before calculate
            if(typeofuser.equals("0")) // no gender selected
            {
                Toast.makeText(getApplicationContext(),"Select Your Gender First",Toast.LENGTH_SHORT).show();
            }
            else if(string_progress.equals("0")) // no height selected
            {
                Toast.makeText(getApplicationContext(),"Select Your Height First",Toast.LENGTH_SHORT).show();
            }
            else if(age <= 0) // invalid age selected
            {
                Toast.makeText(getApplicationContext(),"Select a Valid Age",Toast.LENGTH_SHORT).show();
            }

            else if(weight <= 0) // invalid weight selected
            {
                Toast.makeText(getApplicationContext(),"Select a Valid Weight",Toast.LENGTH_SHORT).show();
            }
            else {
                // BMI calculation (height must be in meters)
                double height_meters = (double) currentprogress / 100;
                double bmi = weight / (height_meters * height_meters);
                // rounding to 2 decimal points
                DecimalFormat df = new DecimalFormat("#.##");
                String string_bmi = df.format(bmi);
                editor.putString(BmiState, string_bmi);
                editor.apply();

                // getting the local date
                LocalDate currentDate = LocalDate.now();
                String date = currentDate.getDayOfMonth() + "-" + currentDate.getMonthValue() + "-" + currentDate.getYear();

                // writing new data in the list
                datiList.add(new Datas(date, string_weight, string_bmi));

                // save the data in async way in the csv
                // common practice in Android to avoid freezing of the UI during operations which can take some time
                new AsyncDataSave().execute();
                Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();

                // creating the explicit intent and putting extra
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


    // class that save the data in async way, extend AsyncTask<Params, Progress, Results>
    // no param, no progress, no results provide in this intern class
    private class AsyncDataSave extends AsyncTask<Void, Void, Void> {
        @Override
        // execution of this function in separated thread from the UI thread
        // Void output Voids array object as input
        protected Void doInBackground(Void... voids) {
            saveDataInCSV();
            return null;
        }
    }

    // method that handle the writing of new data inside CSV file
    private void saveDataInCSV() {
        try {
            // creating file object at external path accessible only from the app named data.csv
            File file = new File(getExternalFilesDir(null), "data.csv");

            // check if the file already exist
            boolean isFileExist = file.exists();

            // FileWriter object opened in append mode for file Object
            FileWriter fileWriter = new FileWriter(file, true);
            // CSVWriter object is created in order to simplify the writing on the csv file
            CSVWriter csvWriter = new CSVWriter(fileWriter);

            // Write indentation if file don't exist yet
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
            // printing in the standard error logcat the stack trace of the exception thrown
            e.printStackTrace();
        }
    }

}