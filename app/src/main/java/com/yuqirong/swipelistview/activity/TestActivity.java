package com.yuqirong.swipelistview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.yuqirong.swipelistview.R;
import com.yuqirong.swipelistview.view.SwipeListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 胡泊洋
 * @date 2016年11月03日 18时20分
 */

public class TestActivity extends Activity {

    private ListView listView;
    private SwipeListAdapter<String> adapter;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_act);
        initView();
        initData();
        initAdapter();
    }

    private void initView() {
        listView = (ListView) this.findViewById(R.id.lv_test);
    }

    private void initAdapter() {
        adapter = new SwipeListAdapter<String>( data, R.id.sll_main) {
            @Override
            protected View getViewImpl(final int position, View convertView, ViewGroup parent) {
                Holder holder;
                if (convertView == null) {
                    holder = new Holder();
                    convertView = getLayoutInflater().inflate(R.layout.slip_list_item, null);
                    holder.nameTv = (TextView) convertView.findViewById(R.id.tv_name);
                    holder.delBtn = (TextView) convertView.findViewById(R.id.tv_delete);
                } else {
                    holder = (Holder) convertView.getTag();
                }
                holder.nameTv.setText(getItem(position));
                holder.delBtn.setText("删除" + position);
                holder.delBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearAll();
                        getSrc().remove(position);
                        notifyDataSetChanged();
                    }
                });
                convertView.setTag(holder);
                return convertView;
            }

            class Holder {
                TextView nameTv;
                TextView delBtn;
            }
        };
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    //当listview开始滑动时，若有item的状态为Open，则Close，然后移除
                    case SCROLL_STATE_TOUCH_SCROLL:
                        adapter.clearAll();
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private void initData() {
        data = new ArrayList<>();
        data.add("a");
        data.add("b");
        data.add("c");
        data.add("d");
        data.add("e");
        data.add("f");
        data.add("h");
        data.add("i");
        data.add("j");
        data.add("k");
        data.add("l");
        data.add("m");
        data.add("n");
        data.add("o");
        data.add("p");
        data.add("q");
        data.add("r");
        data.add("s");
        data.add("t");
        data.add("u");
        data.add("v");
        data.add("w");
    }


}
