package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class AuthenticatedActivity extends AppCompatActivity {
    TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticated);

        welcomeText = (TextView) findViewById(R.id.welcomeText);

        // Get username from intent, this approach is still valid.
        // Intent intent = getIntent();
        // String username = intent.getStringExtra("username");

        // Get username from sharedPreferences.
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        welcomeText.setText("Welcome " + username + "!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            // Remove username form sharedPreferences.
            SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
            sharedPreferences.edit().remove("username").apply();

            // Return to login screen.
            goToLoginScreen();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToLoginScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
