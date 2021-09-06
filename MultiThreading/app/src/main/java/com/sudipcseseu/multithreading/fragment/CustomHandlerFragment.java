package com.sudipcseseu.multithreading.fragment;

import android.annotation.SuppressLint;
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
import com.sudipcseseu.multithreading.handler.CustomHandler;


@SuppressLint("SetTextI18n")
public class CustomHandlerFragment extends Fragment {

    private static final int SECONDS_TO_COUNT = 5;

    public static Fragment newInstance() {
        return new CustomHandlerFragment();
    }

    private Button mBtnSendJob;

    private CustomHandler mCustomHandler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom_handler, container, false);

        mBtnSendJob = view.findViewById(R.id.btn_send_job);
        mBtnSendJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendJob();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mCustomHandler = new CustomHandler();
    }

    @Override
    public void onStop() {
        super.onStop();
        mCustomHandler.stop();
    }

    private void sendJob() {
        mCustomHandler.post(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i < SECONDS_TO_COUNT; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        return;
                    }
                    Log.d("CustomHandler", "iteration: " + i);
                }
            }
        });
    }

}
