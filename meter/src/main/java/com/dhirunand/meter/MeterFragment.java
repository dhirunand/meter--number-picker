package com.dhirunand.meter;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dhirunand.meter.databinding.FragmentMeterBinding;


public class MeterFragment extends Fragment {
    FragmentMeterBinding binding;
    int minWeight = 0;

    int current_pos;

    public MeterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMeterBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        MeterAdapter meterAdapter = new MeterAdapter(requireContext());

        binding.recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                //binding.recyclerView.scrollToPosition(30);

                LinearLayoutManager layoutManager = ((LinearLayoutManager) binding.recyclerView.getLayoutManager());
                layoutManager.scrollToPositionWithOffset(49, 0);
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                int centerPosition = (firstVisibleItemPosition + lastVisibleItemPosition) / 2;
                minWeight = centerPosition;
                meterAdapter.setLeftWidth(centerPosition);
                binding.textView.setText(String.valueOf(centerPosition - minWeight + 1));
                Log.v(MeterFragment.class.getSimpleName(), "center==" + centerPosition);
                binding.recyclerView.setVisibility(View.VISIBLE);

            }
        }, 1);


        binding.recyclerView.setVisibility(View.INVISIBLE);
        binding.recyclerView.setAdapter(meterAdapter);

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                //super.onScrollStateChanged(recyclerView, newState);

                LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                int centerPosition = (firstVisibleItemPosition + lastVisibleItemPosition) / 2;
                binding.textView.setText((centerPosition - minWeight + 1) + "");

                current_pos = centerPosition;

//                if (newState == RecyclerView.SCROLL_STATE_IDLE)
//                    binding.recyclerView.smoothScrollToPosition(firstVisibleItemPosition);
//                Log.v(MainActivity.class.getSimpleName(), "center==" + centerPosition);


            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                //super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                int centerPosition = (firstVisibleItemPosition + lastVisibleItemPosition) / 2;
                binding.textView.setText(String.valueOf(centerPosition - minWeight + 1));

            }
        });

        setGradientOnTextView(binding.textView);
        setGradientOnTextView(binding.textView2);

    }

    public static void setGradientOnTextView(TextView textView) {
        TextPaint paint = textView.getPaint();
        float width = paint.measureText((String) textView.getText());

        Shader textShader = new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
                        ContextCompat.getColor(textView.getContext(), R.color.start_color),
                        ContextCompat.getColor(textView.getContext(), R.color.end_color),
                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);
    }

    @Override
    public void onResume() {
        super.onResume();

        binding.recyclerView.scrollToPosition(current_pos);
    }

}