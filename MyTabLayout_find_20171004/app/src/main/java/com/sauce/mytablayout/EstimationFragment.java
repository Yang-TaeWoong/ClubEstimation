package com.sauce.mytablayout;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Arduino on 2017-11-29.
 */

public class EstimationFragment extends Fragment{

    private ImageView logo;
    private TextView name;
    private HashMap<String, Integer> logos;  //클럽로고들을 저장하는 해시맵

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_estimation, container, false);
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logos = new HashMap<String, Integer>();
        logos.put("Hmv",R.drawable.hmv_logo);
        logos.put("Cocoon", R.drawable.cocoon_logo);
        logos.put("M2",R.drawable.m2_logo);
        logos.put("Mama",R.drawable.mama_logo);
        logos.put("Mint",R.drawable.mint_logo);
        logos.put("Papa",R.drawable.papa_logo);
        logos.put("Super7",R.drawable.super7_logo);
        logos.put("Vera",R.drawable.vera_logo);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("HashMap val", logos.get("Cocoon").toString());
        Log.d("R.drawable val", (String.valueOf(R.drawable.cocoon_logo)));
        logo = (ImageView) findViewById(R.id.logo);
        name = (TextView) findViewById(R.id.name_text);
        logo.setImageResource(logos.get("Papa"));  //인텐트로 받은 클럽 이름에 따라 logos 해시테이블에서 값을 가져옴.
        name.setText("NameText");

    }

    protected View findViewById(int id)
    {
        return getView().findViewById(id);
    }

}


