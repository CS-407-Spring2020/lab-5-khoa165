package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
    }

    public void saveNote(View view) {
        // Get note from text field.
        EditText usernameInput = (EditText) findViewById(R.id.usernameInput);
        String username = usernameInput.getText().toString();
    }
}
