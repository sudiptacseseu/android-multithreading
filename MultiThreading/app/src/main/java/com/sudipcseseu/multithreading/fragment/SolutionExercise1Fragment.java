package com.sudipcseseu.multithreading.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sudipcseseu.multithreading.R;

public class SolutionExercise1Fragment extends Fragment {

    private static final int ITERATIONS_COUNTER_DURATION_SEC = 10;

    public SolutionExercise1Fragment(){
        //required empty public constructor.
    }

    private Button mBtnCountIterations;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_1, container, false);

        mBtnCountIterations = view.findViewById(R.id.btn_count_iterations);
        mBtnCountIterations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                In this approach the thread will terminate after the time set by the external
                flag to prevent memory leak and make it available for garbage collector.
                **/
                countIterations();
            }
        });

        return view;
    }

    private void countIterations() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long startTimestamp = System.currentTimeMillis();
                long endTimestamp = startTimestamp + ITERATIONS_COUNTER_DURATION_SEC * 1000;

                int iterationsCount = 0;
                while (System.currentTimeMillis() <= endTimestamp) {
                    iterationsCount++;
                }

                Log.d(
                        "Exercise1",
                        "iterations in " + ITERATIONS_COUNTER_DURATION_SEC + "seconds: " + iterationsCount
                );
            }
        }).start();
    }
}
