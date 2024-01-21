package fitness.com;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class setTarget extends AppCompatActivity {

    private static final String KEY_TARGET_VALUE = "targetValue";
    private EditText editTextTarget;
    private int targetValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_target);

        editTextTarget = (EditText) findViewById(R.id.editTextTarget);

        // Load the saved target value from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        targetValue = sharedPreferences.getInt(KEY_TARGET_VALUE, 0);
        editTextTarget.setText(String.valueOf(targetValue));

        // Add an onEditorAction listener to the EditText
        editTextTarget.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Save the target value to SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(KEY_TARGET_VALUE, Integer.parseInt(editTextTarget.getText().toString()));
                    editor.apply();

                    // Show a toast message
                    Toast.makeText(setTarget.this, "Target set to " + targetValue, Toast.LENGTH_SHORT).show();

                    return true;
                }
                return false;
            }
        });
    }

    public void setTargetValue(View view) {
        targetValue = Integer.parseInt(editTextTarget.getText().toString());

        // Save the target value to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_TARGET_VALUE, targetValue);
        editor.apply();

        Toast.makeText(this, "Target set to " + targetValue, Toast.LENGTH_SHORT).show();
    }

}