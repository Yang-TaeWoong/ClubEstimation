package com.sauce.mytablayout;

/**
 * Created by WY on 2017-10-03.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JongMin on 2015-11-20.
 */
public class CustomAdapter extends BaseAdapter {

    private ArrayList<ItemData> itemDatas = null; //인자로 받아온 itemDatas를 저장하기 위한 어댑터 내의 ArrayList<ItemData> 객체
    private LayoutInflater layoutInflater = null; //ListView 아이템 레이아웃을 가져오기 위한 클래스


    /*생성자를 통해서 itemDatas를 받아옵니다.
      ctx는 BaseAdapter에 내재되어 있지 않으므로 인자로 받아오니다.*/

    public CustomAdapter(ArrayList<ItemData> itemDatas, Context ctx){
        this.itemDatas = itemDatas;
        this.layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //생성자를 통해서가 아닌 메소드를 통해 itemDatas를 받아오기 위한 메소드
    private void setItemDatas(ArrayList<ItemData> itemDatas){
        this.itemDatas = itemDatas;
        this.notifyDataSetChanged();
    }

    //int getCount 메소드 -> ListView의 아이템 개수를 int형으로 반환
    @Override
    public int getCount() {
        return (itemDatas != null) ? itemDatas.size() : 0;
    }

    //Object getItem 메소드 -> position에 해당하는 아이템을 객체의 형태로 반환
    @Override
    public Object getItem(int position) {
        return (itemDatas != null && (0 <= position && position < itemDatas.size()) ? itemDatas.get(position) : null ); // A ? B : C 아시조??
    }

    //long getItemId 메소드 -> position에 해당하는 아이템의 Id를 반환
    @Override
    public long getItemId(int position) {
        return (itemDatas != null && (0 <= position && position < itemDatas.size()) ? position : 0);
    }

    //View getView 메소드 -> 받아온 데이터(itemDatas)를 ListView의 아이템으로 만들어주는 메소드
    //convertView는 재활용이 가능한 View
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = new ViewHolder(); //화면에서 사라진 convertView를 담아두기 위한 용도로 viewHolder를 사용

        if(convertView == null){ //'convertView == null' = 'convertView가 한번도 생성이 된 적 없다면'

            convertView = layoutInflater.inflate(R.layout.list_item, parent, false); //TextView 2개를 포함하고 있는 list_item을 inflate해서 convertView에 대입

            //viewHolder를 초기화. 한번 해두면 이 position의 convertView는 다시 findViewById를 할 필요가 없어집니다.
            viewHolder.TextView_Title = (TextView)convertView.findViewById(R.id.txtTitle_item);
            //viewHolder.TextView_Description = (TextView)convertView.findViewById(R.id.txtDescription_item);

            convertView.setTag(viewHolder); //convertView에 viewHolder를 태그로 달아줍니다.이렇게 해두면 화면에서 ListView의 아이템(convertView)가 사라져도 메모리에서 지워지지 않습니다.
        }

        else{
            viewHolder = (ViewHolder)convertView.getTag();//convertView가 존재한다면(한번이라도 생성이 되었다면) viewHolder가 존재. 이를 다시 가져옵니다.
        }

        ItemData itemData = itemDatas.get(position); //position에 해당하는 itemData를 받아옵니다.

        viewHolder.TextView_Title.setText(itemData.Title); //받아온 itemData의 데이터를 viewHolder의 TextView에 띄웁니다.
        //viewHolder.TextView_Description.setText(itemData.Description);

        return convertView;
    }
}
