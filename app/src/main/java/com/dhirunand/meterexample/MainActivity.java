package com.dhirunand.meterexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.dhirunand.meter.MeterFragment;

public class MainActivity extends AppCompatActivity { //implements MeterFragment.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MeterFragment meterFragment = new MeterFragment();
        //meterFragment.customizeMeter("Dhiru");

//        Toast.makeText(this, meterFragment.getSelectedNumber(), Toast.LENGTH_SHORT).show();

        meterFragment.setOnItemSelectedListener(new MeterFragment.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MeterFragment meterFragment, int position, String name) {
                Toast.makeText(MainActivity.this, meterFragment.getSelectedNumber(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}