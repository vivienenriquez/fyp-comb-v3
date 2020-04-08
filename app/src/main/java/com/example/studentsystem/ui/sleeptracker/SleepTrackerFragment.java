package com.example.studentsystem.ui.sleeptracker;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.studentsystem.R;

public class SleepTrackerFragment extends Fragment {
    private Chronometer chronometer;
    private boolean running;
    private long pauseOffset;

    private SleepTrackerViewModel sleepTrackerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sleepTrackerViewModel =
                ViewModelProviders.of(this).get(SleepTrackerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sleeptracker, container, false);
//        final TextView textView = root.findViewById(R.id.text_sleeptracker);
        sleepTrackerViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        chronometer = root.findViewById(R.id.chronometer);
        chronometer.setFormat ("Sleep Timer: %s");

        Button chronoStart = root.findViewById(R.id.chronoStart);
        chronoStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChronometer(v);
            }
        });

        Button chronoEnd= root.findViewById(R.id.chronoEnd);
        chronoEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endChronometer(v);
            }
        });
        Button chronoReset= root.findViewById(R.id.chronoReset);
        chronoReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetChronometer(v);
            }
        });
        return root;
    }

    public void startChronometer(View v) {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }
    public void endChronometer(View v) {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock. elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }
    public void resetChronometer(View v) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;

    }
}