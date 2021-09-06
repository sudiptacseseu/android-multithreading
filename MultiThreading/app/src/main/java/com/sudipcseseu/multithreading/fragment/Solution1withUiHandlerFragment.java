package com.sudipcseseu.multithreading.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sudipcseseu.multithreading.R;


@SuppressLint("SetTextI18n")
public class Solution1withUiHandlerFragment extends Fragment {

    private static final int ITERATIONS_COUNTER_DURATION_SEC = 10;

    public static Fragment newInstance() {
        return new Solution1withUiHandlerFragment();
    }

    private Button mBtnCountIterations;

    private final Handler mUiHandler = new Handler(Looper.getMainLooper());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ui_handler_with_solution1, container, false);

        mBtnCountIterations = view.findViewById(R.id.btn_count_iterations);
        mBtnCountIterations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                final int iterationsCountFinal = iterationsCount;

                /*
                Handler to pass the value from background thread to ui thread and show it on views.
                **/
                mUiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("UiHandler", "Current thread: " + Thread.currentThread().getName());
                        mBtnCountIterations.setText("Iterations: " + iterationsCountFinal);
                    }
                });
            }
        }).start();
    }
}
