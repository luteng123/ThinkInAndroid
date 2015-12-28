package com.luteng.thinkinlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import com.luteng.thinkinlistview.adapters.CartAdapter;
import com.luteng.thinkinlistview.model.cartItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private CartAdapter adapter;
    private List<cartItem> cartItems;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.cart_list);
        cartItems = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            cartItem item = new cartItem();
            item.setProductID(i);
            item.setProductName("item"+i);
            item.setProductPrice(100.0f);
            item.setCount(1);
            cartItems.add(item);
        }
        adapter = new CartAdapter(this,cartItems);
        adapter.setOnClickListener(this);
        adapter.setOnCheckedChangeListener(this);
        listView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Object tag = v.getTag();
        int position = -1;
        if(tag!=null && tag instanceof  Integer){
            position = (int) tag;
        }
        switch (id) {
            case R.id.item_cart_delete:
                if(position>-1){
                    cartItems.remove(position);
                }
                break;
            case R.id.item_cart_dec:
                if(position>-1){
                    cartItem item = cartItems.get(position);
                    int count = item.getCount();
                    count--;
                    if(count==0){
                        cartItems.remove(item);
                    }else{
                        item.setCount(count);
                    }
                }
                break;
            case R.id.item_cart_inc:
                if(position>-1){
                    cartItem item = cartItems.get(position);
                    int count = item.getCount();
                    count++;
                    if(count==0){
                        cartItems.remove(item);
                    }else{
                        item.setCount(count);
                    }
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * ListView的编辑模式
     * @param view
     */
    public void btnSwitchEditMode(View view) {
        adapter.switchEditMode();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        switch (id) {
            case R.id.item_cart_check:
                Object tag = buttonView.getTag();
                if(tag!=null && tag instanceof  Integer){
                    int position = (int)tag;
                    cartItems.get(position).setIsChecked(isChecked);
                }
                break;
        }
    }
    //！！！！
    //1.ListView永远要通过Adapter来刷新；
    //2.Adapter永远由数据来更新
    //3.结论：任何ListView显示的内容，不论出现什么变化，都必须通过数据来改变
    //4.即使只是修改一个文本丶数值丶或者是一种选中状态，都必须
    //通过数据来刷新ListView；
    //5.Item中内部控件的点击事件，不要直接操作UI，每一个控件需要来进行当前位置的识别；
    //6.ListView.getView的规则，在显示数据内容的时候，每种数据项状态，都必须要进行展现和变化；


}
