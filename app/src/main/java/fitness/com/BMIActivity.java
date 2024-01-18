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

    private List<Dati> datiList = new ArrayList<>();
    TextView currentheight, currentage, currentweight;
    ImageView incrementage, decrementage;
    SeekBar seekbarforheight;
    RelativeLayout male, female;

    double weight;
    int age;
    int currentprogress;
    String string_progress = "170";
    String typeofuser = "0";
    String string_weight;
    String string_age = "";


    // creo coppie chiave valore di dati da memorizzare
    public static final String GenderState = "GenderKey";
    public static final String HeightState = "HeightKey";
    public static final String WeightState = "WeightKey";
    public static final String AgeState = "AgeKey";
    public static final String MyPREFERENCES = "MyPrefs";
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

        seekbarforheight = findViewById(R.id.seekbar_for_height);

        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

        // loading the seek bar progress
        seekbarforheight.setMax(300);

        // creating sharedpreferences object
        sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

        // checking if there are any preferences
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

        // checking if there are any preferences
        string_progress = sharedpreferences.getString(HeightState, "170");
        seekbarforheight.setProgress(Integer.parseInt(string_progress));
        currentheight.setText(string_progress);

        // checking if there are any preferences
        string_age = sharedpreferences.getString(AgeState, null);
        currentage.setText(string_age);


        // checking if there are any preferences
        string_weight = sharedpreferences.getString(WeightState, null);
        currentweight.setText(string_weight);


        // clicking male button
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typeofuser="Male";

                // creting editor interface to modify preferences
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(GenderState, typeofuser);
                editor.apply();

            }
        });

        // clicking female button
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                typeofuser="Female";

                // creting editor interface to modify preferences
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(GenderState, typeofuser);
                editor.apply();
            }
        });

        // seekbar is changing
        seekbarforheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                currentprogress = progress;
                string_progress = String.valueOf(currentprogress);
                currentheight.setText(string_progress);

                // creting editor interface to modify preferences
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
                    string_age = currentage.getText().toString();
                    age = Integer.parseInt(string_age);

                    age ++;
                    string_age = String.valueOf(age);
                    currentage.setText(string_age);

                    // creting editor interface to modify preferences
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(AgeState, string_age);
                    editor.apply();
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

                    // creting editor interface to modify preferences
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(AgeState, string_age);
                    editor.apply();
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
            // creting editor interface to modify preferences
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(WeightState, string_weight);
            editor.apply();

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
                //Calcolo bmi pre
                double height = (double) currentprogress / 100;
                double bmi = weight / (height * height);
                DecimalFormat df = new DecimalFormat("#.##");
                String string_bmi = df.format(bmi);

                //getting the data

                LocalDate currentDate = LocalDate.now();

                String date = currentDate.getDayOfMonth() + "-" + currentDate.getMonthValue() + "-" + currentDate.getYear();

                //Scrittura dati su lista
                datiList.add(new Dati(date, string_weight, string_bmi));

                // Esegui AsyncTask per salvare i dati in un file CSV
                new SalvaDatiAsyncTask().execute();
                Toast.makeText(this, "Dati salvati con successo", Toast.LENGTH_SHORT).show();

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


    private class SalvaDatiAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            salvaDatiInCSV();
            return null;
        }
    }
    private void salvaDatiInCSV() {
        try {
            File file = new File(getExternalFilesDir(null), "dati.csv");

            // check if the file already exist
            boolean isFileExist = file.exists();

            // FileWriter opend in append mode
            FileWriter fileWriter = new FileWriter(file, true);
            CSVWriter csvWriter = new CSVWriter(fileWriter);

            // Write intexation if file don't exist yet
            if (!isFileExist) {
                csvWriter.writeNext(new String[]{"Data", "Weight", "BMI"});
            }

            // Writing new data
            for (Dati dati : datiList) {
                csvWriter.writeNext(new String[]{dati.getData(), String.valueOf(dati.getPeso()), String.valueOf(dati.getBmi())});
            }

            // Close writer
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}