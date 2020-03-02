package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        EditText nameInput = (EditText) findViewById(R.id.nameInput);
        String name = nameInput.getText().toString();
        goToAuthenticatedScreen(name);
    }

    public void goToAuthenticatedScreen(String s) {
        Intent intent = new Intent(this, AuthenticatedActivity.class);
        intent.putExtra("name", s);
        startActivity(intent);
    }
}
