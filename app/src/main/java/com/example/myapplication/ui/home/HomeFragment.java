package com.example.myapplication.ui.home;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.FragmentHomeBinding;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private LocalDate selectedDate;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    Activity context;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.nextMonth.setOnClickListener(v -> nextMonthAction());
        binding.previousMonth.setOnClickListener(v -> previousMonthAction());

        toStartCalendar();
        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void toStartCalendar() {
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousMonthAction() {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthAction() {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth,
                new CalendarAdapter.OnItemListener() {
                    @Override
                    public void onItemClick(int position, String dayText) {
                        if(!dayText.equals(""))
                        {
                            if (dayText.equals("27")) {
                                String message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate);
                                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                            }

                            if (dayText.equals("8")) {
                                String message = "???????????? ???? ??????????????: ???????????????? ????????????";
                                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                            }

                            if (dayText.equals("17")) {
                                String message = "???????????? ???? ??????????????: ?????????????????? ????????????";
                                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                            }

                            if (dayText.equals("20")) {
                                String message = "???????????? ???? ??????????????: ?????????????? ?????????????????????? ???? ????????????????";
                                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                            }

                            if (dayText.equals("14")) {
                                String message = "???????????? ???? ??????????????: ?????????????????? ???? ?????????????????????? ??????????";
                                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                            }

                            if (dayText.equals("11") || dayText.equals("12") || dayText.equals("18") || dayText.equals("19")
                                    || dayText.equals("25") || dayText.equals("26")) {
                                String message = "?????????????? ?????? ??????????.";
                                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                });
        RecyclerView.LayoutManager layoutManager
                = new GridLayoutManager(getContext(), 7);
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

    private void initWidgets()
    {
        calendarRecyclerView = binding.calendarRecyclerView;
        monthYearText = binding.monthYearTV;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}