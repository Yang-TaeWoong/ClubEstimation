package com.sauce.mytablayout;

/**
 * Created by WY on 2017-10-03.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TabFragment3Find extends AppCompatActivity {

    /*ListView에 들어갈 ArrayList<ItemData>형의 객체 선언
     *ArrayList<ItemData> -> Title과 Description 두 가지의 String 객체를 데이터로 갖는 ItemData라는 객체들을 배열의 형태로 저장하는 클래스
     */
    private ArrayList<ItemData> itemDatas = null;

    //사용자와 직접 상호작용 하는 View들 선언
    Button btnSubmit; //ListView에 데이터를 띄우기 위한 버튼
    EditText editTitle; //, editDescription; //사용자로부터 Title과 Description을 받아오기 위한 EditText
    ListView listView; //데이터들을 list의 형태로 보여 주기 위한 view

    //ListView와 Model(itemDatas)을 연결해주는 CustomAdapter 선언
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initModel(); //ListView에 들어갈 데이터들(위에서 ArrayList<ItemData>의 형태로 선언한 itemDatas)를 초기화하는 모듈
        initView(); //Button, EditText, ListView 3가지 view들을 초기화시키고 Listener를 달아주는 모듈
        makeList(); //Model(itemDatas)와 View(ListView)를 이어주는 역할을 하는 모듈
    }

    private void initModel() {
        itemDatas = new ArrayList<ItemData>(); //itemDatas 데이터 생성.
    }

    private void initView() {

        editTitle = (EditText)findViewById(R.id.editTitle);
        //editDescription = (EditText)findViewById(R.id.editDescription);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        listView = (ListView)findViewById(R.id.listView);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*만약 사용자가 EditText에 아무것도 입력하지 않은 채 submit 버튼을 누를 경우
                  이를 위해 예외 처리를 해주도록 하겠습니다.
                 */

                if(TextUtils.isEmpty(editTitle.getText())
                       /* || TextUtils.isEmpty(editDescription.getText())*/){

                    Toast.makeText(getApplicationContext(),"글자를 입력하셔야 합니다.",Toast.LENGTH_SHORT).show();

                    return;
                }

                else {

                    /*EditText를 통해 사용자로부터 String 형태의 Title과 Description을 받아오면 이것들을 저장할 변수 또는 객체가 필요합니다.
                      String 형태로 각각 Title과 Description을 저장할까요? 아니죠.
                      String 형의 Title과 Description 둘을 동시에 저장하기 위해 ItemData라는 클래스를 만들었으니 활용해야죠.
                      ItemData 객체를 생성해서 거기에 저장을 하는 겁니다.*/

                    ItemData itemData_submit = new ItemData(); //ItemData 객체 itemData_submit를 생성. submit될 것이므로 이름이 헷갈리지 않게 _submit를 덧붙였습니다.
                    itemData_submit.Title = editTitle.getText().toString(); //itemData_submit의 Title 멤버변수에 EditText의 내용 저장
                    //itemData_submit.Description = editDescription.getText().toString(); //itemData_submit의 Description 멤버변수에 EditText의 내용 저장

                    /*itemData_submit에 사용자가 입력했던 Title과 Description이 저장되었습니다.
                      이제 이 녀석을 ArrayList<ItemData> 객체인 itemDatas에 하나의 배열 데이터로 추가합니다.
                     */

                    itemDatas.add(0, itemData_submit);
                    adapter.notifyDataSetChanged(); //itemDatas에 데이터가 추가되었으니 이를 adapter에 알립니다.

                    editTitle.setText("");
                    //editDescription.setText("");

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ItemData itemData_temp = (ItemData)adapter.getItem(position); //getItem메소드는 Object형으로 반환을 하므로 ItemData형으로 캐스팅을 해주어야 합니다.
                            Toast.makeText(getApplicationContext(), itemData_temp.Title /*+ "\n" + itemData_temp.Description*/, Toast.LENGTH_SHORT).show();
                        }
                    });

                    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                            ItemData itemData_temp = (ItemData)adapter.getItem(position);
                            itemDatas.remove(itemData_temp); //itemDatas에서 해당 데이터를 가진 부분을 삭제합니다.
                            adapter.notifyDataSetChanged(); //itemDatas에 데이터가 삭제되었으니 이를 adapter에 알려야겠죠?

                            return false;
                        }
                    });
                }
            }

        });

    }

    private void makeList() {

        adapter = new CustomAdapter(itemDatas, getApplicationContext()); //CustomAdapter 객체인 adapter에 itemDatas와 Context를 인자로 넘겨주었습니다!!
        listView.setAdapter(adapter); //listView에 우리가 만든 CustomAdapter 객체인 adapter를 적용시켰습니다. 이제 Model(itemDatas와 listView가 연결되었습니다!!)

    }
}
