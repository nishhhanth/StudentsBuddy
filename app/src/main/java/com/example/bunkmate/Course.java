package com.example.bunkmate;

import android.content.Intent;
import android.net.Uri;
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


        List<Integer> imageList = Arrays.asList(
                R.drawable.fcs,
                R.drawable.udemy,
                R.drawable.onlinecourse,
                R.drawable.coursesity,
                R.drawable.freecourseweb,
                R.drawable.logo_udemy,
                R.drawable.courseera
        );

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ImageAdapter adapter = new ImageAdapter(imageList, this::onImageClick);
        recyclerView.setAdapter(adapter);
        return view;
    }
    private void onImageClick(int position) {
        String url;
        if(position==0){
            url="https://freecoursesite.com/";
            Intent in=new Intent(Intent.ACTION_VIEW);
            in.setData(Uri.parse(url));
            startActivity(in);
        }
        else if(position==1){
            url="https://udemyfreecourses.org/";
            Intent in=new Intent(Intent.ACTION_VIEW);
            in.setData(Uri.parse(url));
            startActivity(in);
        }
        else if(position==2){
            url="https://onlinecoursedownload.com/";
            Intent in=new Intent(Intent.ACTION_VIEW);
            in.setData(Uri.parse(url));
            startActivity(in);
        }
        else if(position==3){
            url="https://coursesity.com/";
            Intent in=new Intent(Intent.ACTION_VIEW);
            in.setData(Uri.parse(url));
            startActivity(in);
        }
        else if(position==4){
            url="https://www.freecourseweb.org/";
            Intent in=new Intent(Intent.ACTION_VIEW);
            in.setData(Uri.parse(url));
            startActivity(in);
        }
        else if(position==5){
            url="https://www.udemy.com/";
            Intent in=new Intent(Intent.ACTION_VIEW);
            in.setData(Uri.parse(url));
            startActivity(in);
        }
        else if(position==6){
            url="https://www.coursera.org/";
            Intent in=new Intent(Intent.ACTION_VIEW);
            in.setData(Uri.parse(url));
            startActivity(in);
        }
    }
}
