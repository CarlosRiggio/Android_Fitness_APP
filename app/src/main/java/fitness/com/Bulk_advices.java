package fitness.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class Bulk_advices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulk_advices);

        // Crea un array delle stringhe
        String[] strings = {"AA AA AA AA", "BB BB BB BB", "CC CC CC CC", "DD DD DD DD"};

        // Genera un numero casuale tra 0 e 3
        int randomNumber = new Random().nextInt(4);

        // Imposta il testo della TextView con la stringa casuale
        ((TextView) findViewById(R.id.textView)).setText(strings[randomNumber]);


    }
}