package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteActivity extends AppCompatActivity {
    int noteId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        // 1. Get EditText view.
        EditText noteInput = findViewById(R.id.noteInput);

        // 2. Get Intent.
        Intent intent = getIntent();

        // 3. Get the value of integer "noteId" from intent.
        noteId = intent.getIntExtra("noteId", -1);

        // 4. Initialize class variable "noteid" with the value from intent.
        if (noteId != -1) {
            // Display the content of note by retrieving "notes" ArrayList in SecondActivity
            Note note = AuthenticatedActivity.notes.get(noteId);
            String noteContent = note.getContent();

            // Use setText() to display the contents of this note on screen.
            noteInput.setText(noteContent);
        }
    }

    public void saveNote(View view) {
        // 1. Get the content that the user entered.
        EditText noteInput = findViewById(R.id.noteInput);
        String content = noteInput.getText().toString();

        // 2. Initialize database instance.
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);

        // 3. Initialize DBHelper class.
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        // 4. Set username in the following variable by fetching it from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        // 5. Save information to database.
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteId == -1) {
            title = "NOTE_" + (AuthenticatedActivity.notes.size() + 1);
            dbHelper.saveNotes(username, title, content, date);
        } else {
            title = "NOTE_" + (noteId + 1);
            dbHelper.updateNote(title, date, content);
        }

        // 6. Go to second activity using intent.
        Intent intent = new Intent(this, AuthenticatedActivity.class);
        startActivity(intent);
    }
}
