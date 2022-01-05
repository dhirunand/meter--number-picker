package com.dhirunand.meterexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dhirunand.meter.MeterFragment;

public class MainActivity extends AppCompatActivity { //implements MeterFragment.

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentContainerView fcv = (FragmentContainerView) findViewById(R.id.fragmentContainerView);
        MeterFragment meterFragment = (MeterFragment) fcv.getFragment();

//        meterFragment.customizeMeter("Dhiru");

        button = findViewById(R.id.button);

        button.setOnClickListener(view -> {
            Toast.makeText(this, meterFragment.getItemSelected() + "", Toast.LENGTH_SHORT).show();

        });

    }
}