package com.example.a51044.myfirstapp.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a51044.myfirstapp.R;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/714:16<p>
 * <p>更改时间：2019/1/714:16<p>
 * <p>版本号：1<p>
 */
public class MyFlowLayout extends LinearLayout {
    private int mScreenWidth;
    private int mScreenHeight;
    private int textColor;

    public MyFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        mScreenWidth = displayMetrics.widthPixels;
        mScreenHeight = displayMetrics.heightPixels;
        setOrientation(VERTICAL);
        TypedArray type = context.obtainStyledAttributes(attrs, R.styleable.MyFlowLayout);
        if (type != null) {
            textColor = type.getColor(R.styleable.MyFlowLayout_textColor, 0);
            type.recycle();
        }
    }

    public void removeList() {
        removeAllViews();
    }

    public void setData(List<String> data) {
        LinearLayout linearLayout = getLin();
        for (int i = 0; i < data.size(); i++) {
            final String arr = data.get(i);
            int numWidth = 0;
            int childView = linearLayout.getChildCount();
            for (int j = 0; j < childView; j++) {
                TextView tv = (TextView) linearLayout.getChildAt(j);
                LayoutParams params = (LayoutParams) tv.getLayoutParams();
                int leftMargin = params.leftMargin;
                tv.measure(getWidth(), getMeasuredHeight());
                numWidth += tv.getMeasuredWidth() + tv.getPaddingRight() + tv.getPaddingLeft();
            }
            TextView dataText = getText();
            dataText.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), arr, Toast.LENGTH_SHORT).show();
                }
            });
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin = 15;
            params.topMargin = 10;
            dataText.setLayoutParams(params);
            dataText.setText(arr);
            dataText.measure(getMeasuredWidth(), getMeasuredHeight());
            int dataTextWidth = dataText.getMeasuredWidth() + dataText.getPaddingLeft() + dataText.getPaddingRight();
            if (mScreenWidth >= numWidth + dataTextWidth) {
                linearLayout.addView(dataText);
            } else {
                linearLayout = getLin();
                linearLayout.addView(dataText);
            }
        }
    }

    private TextView getText() {
        TextView textView = new TextView(getContext());
        textView.setTextColor(textColor);
        textView.setTextSize(20);
        textView.setBackgroundResource(R.drawable.text_style);
        textView.setPadding(10, 3, 10, 3);
        return textView;
    }

    private LinearLayout getLin() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        this.addView(linearLayout);
        return linearLayout;
    }

}
