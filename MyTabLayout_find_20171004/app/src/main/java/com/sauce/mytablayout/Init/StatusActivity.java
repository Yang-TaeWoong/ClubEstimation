package com.sauce.mytablayout.Init;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.sauce.mytablayout.MainFragment.MainActivity;
import com.sauce.mytablayout.R;

public class StatusActivity extends AppCompatActivity {

    //TODO : 데이터 베이스에서 값 받아오는것을 int num이라고 가정, 파이어베이스 서버에서 값을 가져오는것으로 한다.
    double num=4.3;
    int arr[]={0, R.drawable.verybad,R.drawable.bad,R.drawable.notbad,R.drawable.good,R.drawable.verygood};
    String txt[]={"","very bad","bad","not bad","good","very good"};
    private ImageView imageView;
    private TextView textView;
    GestureDetectorCompat gestureObject;
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        gestureObject = new GestureDetectorCompat(this, new LearnGesture());
        init();
        setImageView();
        setTextView();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    void init(){
        imageView=(ImageView)findViewById(R.id.imoticonImage);
        textView=(TextView)findViewById(R.id.title);
    }
    void setImageView(){
        imageView.setImageResource(arr[(int)num]);
    }
    void setTextView(){
        textView.setText(txt[(int)num]);
    }
    class LearnGesture extends GestureDetector.SimpleOnGestureListener{
        public boolean onFling(MotionEvent event1,MotionEvent event2,float velocityX,float velocityY) {
            if (event2.getX() < event1.getX() ||event1.getX()==event2.getX()) {
                Intent intent = new Intent(StatusActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
            return true;
        }
    }//어떻게 밀어서 다른 액티비티로 갈것인지 결정
}
