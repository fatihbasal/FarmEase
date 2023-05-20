
package com.example.farmease;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

public class Calendar extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore fstore;
    DatabaseReference databaseReference;
    CalendarView calendarView;
    EditText eventEditText;
    String selectedDate;
    String eventDetails;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Calendar");
        fstore = FirebaseFirestore.getInstance();
        calendarView = findViewById(R.id.calendarView);
        eventEditText = findViewById(R.id.eventText);
        selectedDate = "";
        eventDetails = "";

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate =  year + "-" + (month+1)+ "-" + dayOfMonth;
                retrieveEventDeatails(selectedDate);
            }
        });
    }

    private void retrieveEventDeatails(String selectedDate)
    {
        databaseReference.child(selectedDate).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    eventDetails = snapshot.getValue(String.class);
                    eventEditText.setText(eventDetails);
                }
                else{
                    eventEditText.setText("null");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addEvent(View view)
    {
        databaseReference.child(selectedDate).setValue(eventDetails);
        CollectionReference userEventsRef = fstore.collection("Users").document(user.getUid()).collection("Events");
        Map<String, Object> eventInfo = new HashMap<>();
        eventInfo.put("Event",eventDetails);
        userEventsRef.document(selectedDate).set(eventInfo);
    }





}
