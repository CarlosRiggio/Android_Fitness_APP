package fitness.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class ResultBMIActivity extends AppCompatActivity {

    TextView bmi_display, bmi_category, gender_display;
    Intent intent;
    ImageView imageView;
    String string_bmi;
    double bmi;
    String string_height;
    String string_weight;
    double height, weight;
    RelativeLayout background;

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

        string_height = intent.getStringExtra("string_height");
        string_weight = intent.getStringExtra("string_weight");

        height = Double.parseDouble(string_height);
        weight = Double.parseDouble(string_weight);

        // conversion cm to m
        height = height / 100;

        bmi = weight / (height * height);

        DecimalFormat df = new DecimalFormat("#.##");
        string_bmi = df.format(bmi);


        if(bmi<16)
        {
            bmi_category.setText("Severe Thinness");
            background.setBackgroundColor(getColor(R.color.warn));
            imageView.setImageResource(R.drawable.crosss);
            System.out.println("canossa");

        }
        else if(bmi<16.9 && bmi>16)
        {
            bmi_category.setText("Moderate Thinness");
            background.setBackgroundColor(getColor(R.color.halfwarn));
            imageView.setImageResource(R.drawable.warning);

        }
        else if(bmi<18.4 && bmi>17)
        {
            bmi_category.setText("Mild Thinness");
            background.setBackgroundColor(getColor(R.color.halfwarn));
            imageView.setImageResource(R.drawable.warning);
        }
        else if(bmi<24.9 && bmi>18.5 )
        {
            bmi_category.setText("Normal");
            imageView.setImageResource(R.drawable.ok);
        }
        else if(bmi <29.9 && bmi>25)
        {
            bmi_category.setText("Overweight");
            background.setBackgroundColor(getColor(R.color.halfwarn));
            imageView.setImageResource(R.drawable.warning);
        }
        else if(bmi<34.9 && bmi>30)
        {
            bmi_category.setText("Obese Class I");
            background.setBackgroundColor(getColor(R.color.halfwarn));
            imageView.setImageResource(R.drawable.warning);
        }
        else
        {
            bmi_category.setText("Obese Class II");
            background.setBackgroundColor(getColor(R.color.warn));
            imageView.setImageResource(R.drawable.crosss);
        }


        gender_display.setText(intent.getStringExtra("gender"));
        bmi_display.setText(string_bmi);

    }

    public void openBMICalculator(View view){
        Intent intent = new Intent(this, BMIActivity.class);

        startActivity(intent);
        finish();
    }
}