package com.luteng.thinkinlistview.adapters;

import android.animation.FloatArrayEvaluator;
import android.content.Context;
import android.content.pm.LabeledIntent;
import android.text.StaticLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.luteng.thinkinlistview.R;
import com.luteng.thinkinlistview.model.cartItem;

import java.util.List;

/**
 * Created by John on 2015/12/26.
 */
public class CartAdapter extends BaseAdapter{
    private Context context;
    private List<cartItem> cartItemList;

    /**
     * 是否是编辑模式
     */
    private boolean editMode;

    public CartAdapter(Context context, List<cartItem> cartItemList) {
        this.context = context;
        this.cartItemList = cartItemList;
    }

    @Override
    public int getCount() {
        return cartItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        //1.复用
        if (convertView != null) {
            ret = convertView;
        }else {
            ret = LayoutInflater.from(context).inflate(R.layout.cart_item,parent,false);
        }
        //2.ViewHolder
        ViewHolder holder = (ViewHolder) ret.getTag();
        if(holder == null){
            holder = new ViewHolder(ret);
            ret.setTag(holder);
        }
        //3.显示内容
        cartItem item = cartItemList.get(position);
        holder.txtProductName.setText(item.getProductName());
        holder.txtProductPrice.setText(Float.toString(item.getProductPrice()));
        holder.txtCount.setText(String.valueOf(item.getCount()));

        //针对按钮丶CheckBox等可以交互的控件，必须要更新控件位置的标识；

        holder.btnInc.setTag(position);
        holder.btnDec.setTag(position);
        holder.btnDelete.setTag(position);

        //！！！CheckBox设置Tag，，，一定要在checkBox进行代码选中之前；
        holder.chbCheck.setTag(position);

        //编辑模式的处理

        if(editMode){
            holder.chbCheck.setVisibility(View.VISIBLE);
            holder.btnDelete.setVisibility(View.VISIBLE);
            //TODO
            holder.chbCheck.setChecked(item.isChecked());
        }else{
            holder.chbCheck.setVisibility(View.INVISIBLE);
            holder.btnDelete.setVisibility(View.INVISIBLE);
            holder.chbCheck.setChecked(false);
        }

        return ret;
    }

    private View.OnClickListener onClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    //方法内部调用Adapter刷新，因此这个方法必须在主线程调用
    public void switchEditMode() {
        editMode = !editMode;
        notifyDataSetChanged();
    }

    private  class ViewHolder {
        private CheckBox chbCheck;
        private ImageView imageView;
        private TextView txtProductName;
        private TextView txtProductPrice;
        private Button btnInc;
        private Button btnDec;
        private Button btnDelete;
        private TextView txtCount;

        /**
         * 利用构造方法，来初始化内部的成员变量
         * @param itemView
         */
        public ViewHolder(View itemView){
            chbCheck = (CheckBox) itemView.findViewById(R.id.item_cart_check);
            imageView = (ImageView) itemView.findViewById(R.id.item_cart_icon);
            txtProductName = (TextView) itemView.findViewById(R.id.item_cart_name);
            txtProductPrice = (TextView) itemView.findViewById(R.id.item_cart_price);
            btnInc = (Button) itemView.findViewById(R.id.item_cart_inc);
            btnDec = (Button) itemView.findViewById(R.id.item_cart_dec);
            btnDelete = (Button) itemView.findViewById(R.id.item_cart_delete);
            txtCount = (TextView) itemView.findViewById(R.id.item_cart_count);

           btnInc.setOnClickListener(onClickListener);
            btnDec.setOnClickListener(onClickListener);
            btnDelete.setOnClickListener(onClickListener);

            chbCheck.setOnCheckedChangeListener(onCheckedChangeListener);
        }

    }

}
