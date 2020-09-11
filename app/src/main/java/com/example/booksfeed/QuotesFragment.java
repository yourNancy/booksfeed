package com.example.booksfeed;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.booksfeed.activities.ScanActivity;

public class QuotesFragment extends Fragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quotes,
                container, false);
        Button button_scan = (Button) rootView.findViewById(R.id.button_scan_ocr);
        button_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail();
            }
        });
        return rootView;
    }

    public void updateDetail() {
        Intent intent = new Intent(getActivity(), ScanActivity.class);
        startActivity(intent);
    }
}
