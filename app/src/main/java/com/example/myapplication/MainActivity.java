package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.ui.home.CalendarAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private DatabaseReference pisosData;

    @RequiresApi(api = Build.VERSION_CODES.O)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
//        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery,
                R.id.nav_slideshow, R.id.nav_cards,
                R.id.nav_timetable, R.id.nav_poker)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        toStartCalendar();
        FirebaseDatabase pisosData = FirebaseDatabase.getInstance();
        DatabaseReference dbPrisoner  = pisosData.getReference("Prisoner");

//        DatabaseReference dbCase  = pisosData.getReference("Case");
//        DatabaseReference dbRollCall  = pisosData.getReference("RollCall");
//        DatabaseReference dbActivity  = pisosData.getReference("Activity");
//        DatabaseReference dbTimetable  = pisosData.getReference("Timetable");
//        DatabaseReference dbWeek  = pisosData.getReference("Week");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private LocalDate selectedDate;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void toStartCalendar() {
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, String dayText) {
        if(!dayText.equals(""))
        {
            if (dayText.equals("27")) {
                String message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate);
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }

            if (dayText.equals("8")) {
                String message = "Задача на сегодня: провести уборку";
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }

            if (dayText.equals("17")) {
                String message = "Задача на сегодня: проверить карцер";
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }

            if (dayText.equals("20")) {
                String message = "Задача на сегодня: отвести заключённых на прогулку";
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }

            if (dayText.equals("14")) {
                String message = "Задача на сегодня: наблюдать за тренажёрным залом";
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }

            if (dayText.equals("11") || dayText.equals("12") || dayText.equals("18") || dayText.equals("19")
                    || dayText.equals("25") || dayText.equals("26")) {
                String message = "Сегодня нет задач.";
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }

        }
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousMonthAction(View view) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthAction(View view) {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> daysInMonthArray(LocalDate date)
    {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i <= 42; i++)
        {
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add("");
            }
            else
            {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return  daysInMonthArray;
    }

    public void onRead(View view){
        Intent i = new Intent(MainActivity.this, ReadActivity.class);
        startActivity(i);
    }


//    private PopupWindow myPopUp;
//    private Button prisoner1;

//    private void showPopUp(View view)
//    {
//        prisoner1 = findViewById(R.id.button);
//        View.OnClickListener myCard = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View customView = inflater.inflate(R.layout.popup_layout1, null);
//                myPopUp = new PopupWindow(
//                        customView,
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT
//                );
//                ImageButton closePopUp = customView.findViewById(R.id.imageButton);
//                closePopUp.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        myPopUp.dismiss();
//                    }
//                });
//            }
//        };
//        prisoner1.setOnClickListener(myCard);
//    }
}