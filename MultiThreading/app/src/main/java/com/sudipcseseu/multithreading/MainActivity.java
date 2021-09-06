package com.sudipcseseu.multithreading;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;


import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sudipcseseu.multithreading.adapter.ViewPagerAdapter;
import com.sudipcseseu.multithreading.fragment.Exercise1Fragment;
import com.sudipcseseu.multithreading.fragment.Exercise2Fragment;
import com.sudipcseseu.multithreading.fragment.Exercise3Fragment;

public class MainActivity extends AppCompatActivity {

    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);

        // Setting up the adapter
        viewPagerAdapter = new ViewPagerAdapter(this);

        // Add the fragments
        viewPagerAdapter.add(new Exercise1Fragment(),"Exercise 1");
        viewPagerAdapter.add(new Exercise2Fragment(),"Exercise 2");
        viewPagerAdapter.add(new Exercise3Fragment(),"Exercise 3");

        // Set the adapter
        viewPager.setAdapter(viewPagerAdapter);

        // The Page (fragment) titles will be displayed in the
        // tabLayout hence we need to  set the page viewer
        // we use the TabLayoutMediator().

        tabLayout =  findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText("Exercise " + (position + 1));
                    }
                }).attach();


    }
}