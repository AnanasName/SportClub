package com.example.sportclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.sportclub.data.SportClubContract.MemberEntry;

public class AddMemberActivity extends AppCompatActivity {
    EditText lastNameEditText;
    EditText firstNameEditText;
    Spinner genderSpinner;
    EditText groupEditText;
    private int gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        ArrayAdapter spinnerAdapter;

        lastNameEditText = findViewById(R.id.adding_new_member_last_name_edit_text);
        firstNameEditText = findViewById(R.id.adding_new_member_first_name_edit_text);
        groupEditText = findViewById(R.id.adding_new_member_group_edit_text);
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
                return true;
            case R.id.delete_member:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
