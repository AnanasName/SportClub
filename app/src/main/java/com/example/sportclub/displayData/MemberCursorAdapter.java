package com.example.sportclub.displayData;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.sportclub.R;
import com.example.sportclub.data.SportClubContract.MemberEntry;


public class MemberCursorAdapter extends CursorAdapter {
    public MemberCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_member, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView firstAndLastNameTextView = (TextView) view.findViewById(R.id.main_first_and_last_name_text_view);
        TextView genderTextView = (TextView) view.findViewById(R.id.main_gender_text_view);
        TextView sportTextView = (TextView) view.findViewById(R.id.main_sport_text_view);

        String firstName = cursor.getString(cursor.getColumnIndex(MemberEntry.COLUMN_FIRST_NAME));
        String lastName = cursor.getString(cursor.getColumnIndex(MemberEntry.COLUMN_LAST_NAME));
        String sport = cursor.getString(cursor.getColumnIndex(MemberEntry.COLUMN_SPORT));

        Integer gender = cursor.getInt(cursor.getColumnIndex(MemberEntry.COLUMN_GENDER));

        firstAndLastNameTextView.setText(firstName + " " + lastName);
        sportTextView.setText(sport);

        switch (gender){
            case MemberEntry.GENDER_UNKNOWN :
                 genderTextView.setText("Unknown");
                 break;
            case MemberEntry.GENDER_MALE:
                genderTextView.setText("Male");
                break;
            case MemberEntry.GENDER_FEMALE:
                genderTextView.setText("Female");
                break;
        }

    }
}
