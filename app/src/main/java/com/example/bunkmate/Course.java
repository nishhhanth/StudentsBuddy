package com.example.bunkmate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class Course extends Fragment {
    WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        // List of images (replace with your own drawable resources)
        List<Integer> imageList = Arrays.asList(
                R.drawable.fcs, // Replace with your drawable
                R.drawable.udemy,
                R.drawable.onlinecourse,
                R.drawable.coursesity,
                R.drawable.freecourseweb,
                R.drawable.logo_udemy,
                R.drawable.courseera
        );

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ImageAdapter adapter = new ImageAdapter(imageList, this::onImageClick);
        recyclerView.setAdapter(adapter);
        return view;
    }
    private void onImageClick(int position) {
        // Show toast message
        Toast.makeText(getContext(), "Image " + (position + 1) + " clicked!", Toast.LENGTH_SHORT).show();
    }
}
