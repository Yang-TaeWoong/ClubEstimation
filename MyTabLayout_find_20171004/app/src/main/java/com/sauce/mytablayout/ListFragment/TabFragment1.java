package com.sauce.mytablayout.ListFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sauce.mytablayout.Estimation.EstimationActivity;
import com.sauce.mytablayout.R;

/**
 * Created by Junyoung on 2016-06-23.
 */

public class TabFragment1 extends Fragment {

    //TODO ; num은 파이어베이스 서버에서 불러오는걸로
    double num = 3;
    String clubName="club name";

    //TODO : clubName을 listView에서 받아와야함
    int arr[] = {0, R.drawable.verybad, R.drawable.bad, R.drawable.notbad, R.drawable.good, R.drawable.verygood};
    String st[] = {"오늘은 가지마~", "굳이 오늘가야해?", "심심하면 가보는것도?", "나름 괜차나", "오늘이 각이다"};
    ImageView image;
    TextView text;
    TextView score;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_fragment_1, container, false);
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //here you can initialise your variables,listeners,e.t.c ;
        super.onActivityCreated(savedInstanceState);

        image = (ImageView) findViewById(R.id.imoticonImage);
        text = (TextView) findViewById(R.id.textView1);
        score = (TextView) findViewById(R.id.textView2);
        button = (Button) findViewById(R.id.button);
        setImage();
        setText();
        buttonClick();
    }

    void setImage() {
        image.setImageResource(arr[(int) num]);
    }

    void setText() {
        text.setText(st[(int) num]);
        score.setText(String.valueOf(num));
    }

    void buttonClick() {
        button.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(getActivity(), EstimationActivity.class);

            @Override
            public void onClick(View v) {
                intent.putExtra(clubName,"clubName");
                startActivity(intent);
                //평가하기 액티비티로 넘길때 클럽이름을 넘겨줌
            }
        });
    }
    protected View findViewById(int id) {
        return getView().findViewById(id);
    }
}
