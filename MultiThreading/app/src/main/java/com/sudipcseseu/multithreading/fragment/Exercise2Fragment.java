package com.sudipcseseu.multithreading.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sudipcseseu.multithreading.R;

public class Exercise2Fragment extends Fragment {

    public Exercise2Fragment(){
        //required empty public constructor.
    }

    private byte[] mDummyData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDummyData = new byte[50 * 1000 * 1000];
        return inflater.inflate(R.layout.fragment_exercise_2, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        //countScreenTime();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void countScreenTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int screenTimeSeconds = 0;
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        return;
                    }
                    screenTimeSeconds++;
                    Log.d("Exercise 2", "screen time: " + screenTimeSeconds + "s");
                }
            }
        }).start();
    }
}
