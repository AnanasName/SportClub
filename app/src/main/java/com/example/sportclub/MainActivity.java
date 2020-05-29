package com.example.sportclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sportclub.data.SportClubContract;
import com.example.sportclub.data.SportClubContract.MemberEntry;

import java.lang.reflect.Member;

public class MainActivity extends AppCompatActivity {
    TextView membersTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        membersTextView = findViewById(R.id.main_members_text_view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayData();
    }

    public void toAddMemberActivity(View view) {
        Intent intent = new Intent(MainActivity.this, AddMemberActivity.class);
        startActivity(intent);
    }

    private void displayData() {
        String[] projection = {
                MemberEntry._ID,
                MemberEntry.COLUMN_FIRST_NAME,
                MemberEntry.COLUMN_LAST_NAME,
                MemberEntry.COLUMN_GENDER,
                MemberEntry.COLUMN_SPORT};

        Cursor cursor = getContentResolver().
                query(MemberEntry.CONTENT_URI, projection, null, null ,null);

        membersTextView.setText("All members\n\n");
        membersTextView.append(MemberEntry._ID + " " +
                    MemberEntry.COLUMN_FIRST_NAME + " " +
                    MemberEntry.COLUMN_LAST_NAME + " " +
                    MemberEntry.COLUMN_GENDER + " " +
                    MemberEntry.COLUMN_SPORT);

        int idIndex = cursor.getColumnIndex(MemberEntry._ID);
        int idFirstName = cursor.getColumnIndex(MemberEntry.COLUMN_FIRST_NAME);
        int idLastName = cursor.getColumnIndex(MemberEntry.COLUMN_LAST_NAME);
        int idGender = cursor.getColumnIndex(MemberEntry.COLUMN_GENDER);
        int idSport = cursor.getColumnIndex(MemberEntry.COLUMN_SPORT);

        while(cursor.moveToNext()){
            int currentId = cursor.getInt(idIndex);
            String currentFirstName = cursor.getString(idFirstName);
            String currentLastName = cursor.getString(idLastName);
            Integer currentGender = cursor.getInt(idGender);
            String currentSport = cursor.getString(idSport);

            membersTextView.append("\n" +
                    currentId + " " +
                    currentFirstName + " " +
                    currentLastName + " " +
                    currentGender + " " +
                    currentSport);
        }
        cursor.close();
    }
}
