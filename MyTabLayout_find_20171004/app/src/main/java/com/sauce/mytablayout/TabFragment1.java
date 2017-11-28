package com.sauce.mytablayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Junyoung on 2016-06-23.
 */

public class TabFragment1 extends Fragment {

    //TODO ; num은 파이어베이스 서버에서 불러오는걸로
    int num;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.tab_fragment_1, container, false);
        ImageView imageView=(ImageView) rootView.findViewById(R.id.imoticonImage);
        imageView.setImageResource(R.drawable.verygood);

        return inflater.inflate(R.layout.tab_fragment_1, container, false);
    }
}
