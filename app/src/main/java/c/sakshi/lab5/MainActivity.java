package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);

        String username = sharedPreferences.getString("username", "");
        if (username != null && !username.equals("")) {
            goToAuthenticatedScreen(username);
        } else {
            setContentView(R.layout.activity_main);
        }
    }

    public void login(View view) {
        // Get username and password.
        EditText usernameInput = (EditText) findViewById(R.id.usernameInput);
        String username = usernameInput.getText().toString();
        EditText passwordInput = (EditText) findViewById(R.id.passwordInput);
        String password = passwordInput.getText().toString();

        // Go to authenticated screen.
        goToAuthenticatedScreen(username);
    }

    public void goToAuthenticatedScreen(String username) {
        // Add username to SharedPreferences object.
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", username).apply();

        // Pass data
        Intent intent = new Intent(this, AuthenticatedActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
