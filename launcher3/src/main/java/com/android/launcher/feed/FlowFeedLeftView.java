package com.android.launcher.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.android.launcher.R;

/**
 * Created by mr.kong on 2017/9/7.
 */

public class FlowFeedLeftView extends LinearLayout {
    private Context mContext;
    public View mMainView;

    public FlowFeedLeftView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    private void initView() {
        mMainView = LayoutInflater.from(mContext).inflate(R.layout.layout_flowfeed_left_content,null);
    }


}
