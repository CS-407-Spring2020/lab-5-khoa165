package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AuthenticatedActivity extends AppCompatActivity {
    TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticated);

        welcomeText = (TextView) findViewById(R.id.welcomeText);
        Intent intent = getIntent();
        String str = intent.getStringExtra("name");
        welcomeText.setText("Welcome " + str + "!");
    }
}
