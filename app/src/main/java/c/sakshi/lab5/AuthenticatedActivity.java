package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AuthenticatedActivity extends AppCompatActivity {
    TextView welcomeText;
    public static ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticated);

        // 1. Display welcome message. Fetch username from SharedPreferences.
        welcomeText = (TextView) findViewById(R.id.welcomeText);

        // Get username from intent, this approach is still valid.
        // Intent intent = getIntent();
        // String username = intent.getStringExtra("username");

        // Get username from sharedPreferences.
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        welcomeText.setText("Welcome " + username + "!");

        // 2. Get SQLiteDatabase instance.
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        // 4. Create an ArrayList<String> object by iterating over notes object
        ArrayList<String> displayNotes = new ArrayList<>();
        for (Note note : notes) {
            displayNotes.add(String.format("Title: %s\nDate:%s", note.getTitle(), note.getDate()));
        }

        // 5. Use a ListView view to display the notes on screen.
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
        ListView listView = (ListView) findViewById(R.id.notesListView);
        listView.setAdapter(adapter);

        // 6. Add onItemClickListener for ListView item, a note in our case.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Initialize intent to take user to third activity (NoteAcitivty in this case).
                Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
                // Add the position of the item that was clicked on as "noteid".
                intent.putExtra("noteId", position);
                startActivity(intent);
            }
        });

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
        } else if (item.getItemId() == R.id.addNote) {
            goToAddNoteScreen();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToLoginScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToAddNoteScreen() {
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);
    }
}
