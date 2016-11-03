package com.yuqirong.swipelistview.view;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 胡泊洋
 * @date 2016年11月03日 17时49分
 */

public abstract class SwipeListAdapter<T> extends BaseAdapter {

    private Context context;
    private List<T> src;
    private List<SwipeListLayout> swipeListLayouts;
    private LayoutInflater inflater;
    private
    @LayoutRes
    int layoutId;
    private
    @IdRes
    int swipeLayoutId;

    public SwipeListAdapter(@NonNull Context _context, @NonNull List<T> _src, @IdRes int _layoutId, @IdRes int _swipeLayoutId) {
        src = _src;
        context = _context;
        inflater = LayoutInflater.from(context);
        layoutId = _layoutId;
        swipeLayoutId = _swipeLayoutId;
        swipeListLayouts = new ArrayList<>();
    }

    public void setSrc(@NonNull List<T> _src) {
        src = _src;
    }

    @Override
    public int getCount() {
        return src.size();
    }

    public List<T> getSrc() {
        return src;
    }

    @Override
    public T getItem(int position) {

        return src.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) {
            final SwipeListLayout swipeListLayout = (SwipeListLayout) convertView.findViewById(swipeLayoutId);
            if (swipeListLayout != null)
                swipeListLayout.setOnSwipeStatusListener(new SwipeListLayout.OnSwipeStatusListener() {
                    @Override
                    public void onStatusChanged(SwipeListLayout.Status status) {
                        statusChanged(status, swipeListLayout);
                    }

                    @Override
                    public void onStartCloseAnimation() {

                    }

                    @Override
                    public void onStartOpenAnimation() {

                    }
                });
        }


        return convertView;
    }


    private void statusChanged(SwipeListLayout.Status status, SwipeListLayout swipeListLayout) {
        if (status == SwipeListLayout.Status.Open) {
            //若有其他的item的状态为Open，则Close，然后移除
            clearAll();
            swipeListLayouts.add(swipeListLayout);
        } else {
            if (swipeListLayouts.contains(swipeListLayout))
                swipeListLayouts.remove(swipeListLayout);
        }
    }

    public void clearAll() {
        if (swipeListLayouts.size() > 0) {
            for (SwipeListLayout s : swipeListLayouts) {
                s.setStatus(SwipeListLayout.Status.Close, true);
                swipeListLayouts.remove(s);
            }
        }
    }


}
