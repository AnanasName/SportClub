package com.example.sportclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sportclub.data.SportClubContract.MemberEntry;

import java.lang.reflect.Member;

public class AddMemberActivity extends AppCompatActivity {
    EditText lastNameEditText;
    EditText firstNameEditText;
    Spinner genderSpinner;
    EditText sportEditText;
    private int gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        ArrayAdapter spinnerAdapter;

        lastNameEditText = findViewById(R.id.adding_new_member_last_name_edit_text);
        firstNameEditText = findViewById(R.id.adding_new_member_first_name_edit_text);
        sportEditText = findViewById(R.id.adding_new_member_sport_edit_text);
        genderSpinner = findViewById(R.id.adding_new_member_gender_spinner);

        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_gender, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(spinnerAdapter);

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGender = (String) parent.getItemAtPosition(position);
                switch (selectedGender){
                    case "Male": gender = MemberEntry.GENDER_MALE; break;
                    case "Female": gender = MemberEntry.GENDER_FEMALE; break;
                    default: gender = MemberEntry.GENDER_UNKNOWN;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                gender = MemberEntry.GENDER_UNKNOWN;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_member_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.save_member:
                insertMethod();
                return true;
            case R.id.delete_member:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertMethod(){

        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String sport = sportEditText.getText().toString().trim();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MemberEntry.COLUMN_FIRST_NAME, firstName);
        contentValues.put(MemberEntry.COLUMN_LAST_NAME, lastName);
        contentValues.put(MemberEntry.COLUMN_SPORT, sport);
        contentValues.put(MemberEntry.COLUMN_GENDER, gender);

        ContentResolver contentResolver = getContentResolver();
        Uri uri = contentResolver.insert(MemberEntry.CONTENT_URI, contentValues);

        if (uri == null){
            Toast.makeText(this, "Insertion of data failed", Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }
}
