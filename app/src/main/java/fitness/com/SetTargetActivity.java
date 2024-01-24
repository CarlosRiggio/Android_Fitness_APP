package fitness.com;

import static fitness.com.BMIActivity.MyPREFERENCES;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

public class SetTargetActivity extends AppCompatActivity {


    // preferences key value
    public static final String ValueState = "targetValue";
    EditText editTextTarget;
    float targetValue;

    // sharedPreferences
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_target);

        // finding the editTarget editView
        editTextTarget = findViewById(R.id.editTextTarget);
        // Load the saved target value from SharedPreferences
        sharedPreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        targetValue = sharedPreferences.getFloat(ValueState, 0);
        editTextTarget.setText(String.valueOf(targetValue));

        // Add an onEditorAction listener to the EditText
        editTextTarget.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                String apo = editTextTarget.getText().toString();

                // Save the target value to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putFloat(ValueState, Float.parseFloat(apo));
                editor.apply();

                // Show a toast message
                Toast.makeText(SetTargetActivity.this, "Target set to " + apo + "Kg", Toast.LENGTH_SHORT).show();

                return true;
            }
            return false;
        });

    }
}