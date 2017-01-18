package com.example.zhongweikang.myqq;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ForwardingListener;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     private SwipeMenuListView listView;
     private ArrayList<String> list=new ArrayList<>();
     private  myAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i <10 ; i++) {
            list.add("我叫MT"+i);
        }
        adapter=new myAdapter(this,list);
        initView();
        creator();
        listView.setAdapter(adapter);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override  // 设置监听事件
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:  // 在这里写入相应的额逻辑
                        Toast.makeText(MainActivity.this,"我打开了",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:  // 写入相应的逻辑
                        Toast.makeText(MainActivity.this,"我删除了",Toast.LENGTH_SHORT).show();
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                        break;
                }
                return false;  // false : close the menu; true : not close the menu
            }
        });

    }
    private void creator() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {    // SwipeMenuCreator对象的复写方法进行属性设置
            @Override
            public void create(SwipeMenu menu) {
                //创建打开
                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                openItem.setWidth(120);
                openItem.setTitle("打开");
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem); //
                // 创建删除
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,0x3F, 0x25)));
                deleteItem.setWidth(120);
                deleteItem.setIcon(R.mipmap.ic_launcher);
                menu.addMenuItem(deleteItem);
            }
        };
        listView.setMenuCreator(creator); // listview 设置 添加creator
        listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT); // 设置滑出方式为左滑
    }
    private void initView() {
        listView= (SwipeMenuListView) findViewById(R.id.listView);
    }
}
