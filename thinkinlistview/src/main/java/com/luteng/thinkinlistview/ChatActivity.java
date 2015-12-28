package com.luteng.thinkinlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.luteng.thinkinlistview.adapters.ChatAdapter;
import com.luteng.thinkinlistview.model.ChatMessage;

import java.util.LinkedList;
    //ListView多布局复用
    //1.ListView内部包含了一个缓冲区，存储不同分类的缓冲试图，这些试图应用于convertView；
    //2.BaseAdapter 内部可以重写两个方法，这两个方法，用于多布局的复用；
    //3.getViewTypeCount（）方法；代表当前ListView中Adapter需要显示的布局的类型数量；有多少种不同的布局；
    //2.getViewTypeCount（）类型数>1代表ListView加载多种布局；那么每一种布局的类型数要唯一；
public class ChatActivity extends AppCompatActivity {
    private LinkedList<ChatMessage> messages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ListView listView = (ListView) findViewById(R.id.chat_list_view);
        messages = new LinkedList<>();
        for (int i = 0; i < 123; i++) {
            ChatMessage msg = new ChatMessage();
            msg.setMessage("你好");
            msg.setTime(System.currentTimeMillis());
            msg.setIsSelf(i % 2 == 0);
            messages.add(msg);
        }
        ChatAdapter adapter = new ChatAdapter(this,messages);
        listView.setAdapter(adapter);

    }
}
