package com.luteng.thinkinlistview.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.luteng.thinkinlistview.R;
import com.luteng.thinkinlistview.model.ChatMessage;

import java.security.Policy;
import java.util.List;

/**
 * Created by John on 2015/12/26.
 */
public class ChatAdapter extends BaseAdapter{
    private static final  int TYPE_OTHER = 0;
    private static final  int TYPE_SELF = 1;
    private Context context;
    private List<ChatMessage> messages;

    public ChatAdapter(Context context, List<ChatMessage> messages) {
        this.context = context;
        this.messages = messages;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (messages != null) {
            ret = messages.size();
        }
        return ret;
    }

    @Override
    public ChatMessage getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 获取当前的ListView能够显示多少种布局类型
     *
     * 如果这个方法返回内容>1，那么必须重写getItemType（int type）方法
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {
        //返回2，是因为聊天界面，自己发送的在右侧，收到的在在左侧；相当于显示两种布局类型
        return 2;
    }

    /**
     * 因为getViewTypeCount返回值>1，那么每进行getView之前，
     * 都会对要获取的View的类型进行检查，根据索引来调用；
     * 这个方法，能够控制：
     * 1.ListView将移除的View是如何进行缓存的，决定缓冲区存放的位置
     * 2.ListView在每次调用getView之前都会进行getItemViewType（），获取的
     * 类型就可以从缓冲区加载View，形成 convertView
     * @param position
     * @return 最后的返回值{0,n}，n 代表 getViewTypeCount的返回值
     */



    @Override
    public int getItemViewType(int position) {
        int ret;
        ChatMessage chatMessage = messages.get(position);
        if(chatMessage.isSelf()){
            ret = TYPE_SELF;
        }else {
            ret = TYPE_OTHER;
        }
        return ret;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        Log.d("ChatAdapter", position + "  "+convertView);

        if(convertView!=null){
            ret = convertView;
        }else {
            LayoutInflater inflater = LayoutInflater.from(context);
            int itemViewType = getItemViewType(position);
            switch (itemViewType) {
                case TYPE_OTHER:
                    ret = inflater.inflate(R.layout.chat_other_item,parent,false);
                    break;
                case TYPE_SELF:
                    ret = inflater.inflate(R.layout.chat_self_item,parent,false);
            }
        }
        return ret;
    }
    private static class ViewHolder{

    }
}
