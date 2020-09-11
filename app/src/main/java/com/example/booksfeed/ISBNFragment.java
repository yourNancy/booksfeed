package com.example.booksfeed;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class ISBNFragment extends Fragment {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_isbn,
                container, false);
        Button button_scan = (Button) rootView.findViewById(R.id.button_scan);
        button_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail();
            }
        });

        Button searchISBNButton = (Button) rootView.findViewById(R.id.button_search_isbn);
        searchISBNButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchDetail();
            }

        });

        return rootView;
    }

    public void updateDetail() {
        Intent intent = new Intent(getActivity(), BarcodeActivity.class);
        startActivity(intent);
    }

    public void searchDetail() {
        Intent intent = new Intent(getActivity(), ISBNSearchActivity.class);
        startActivity(intent);
    }

}