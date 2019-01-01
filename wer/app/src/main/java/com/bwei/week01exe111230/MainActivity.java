package com.bwei.week01exe111230;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.xw.repo.XEditText;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import adapter.MyAdapter;
import beans.Goods;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import presenter.MainPresenter;
import view.MainView;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.main_back)
    ImageView mMainBack;
    @BindView(R.id.main_img)
    ImageView mMainImg;
    @BindView(R.id.main_recycler)
    RecyclerView mMainRecycler;
    @BindView(R.id.main_text)
    XEditText mMainText;
    @BindView(R.id.main_btn)
    Button mMainBtn;
    private MainPresenter mainPresenter = new MainPresenter(this);
    private String keywords = "笔记本";
    private int page = 1;
    private List<Goods.DataBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(context());
        ButterKnife.bind(this);

        mainPresenter.getData(keywords, page);
    }

    @OnClick({R.id.main_back, R.id.main_img, R.id.main_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.main_back:
                onDestroy();
                break;
            case R.id.main_img:
                break;
            case R.id.main_btn:
                String text = String.valueOf(mMainText.getText());
                keywords=text;
                mainPresenter.getData(keywords, page);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void onSuccess(Goods goods) {
        //Toast.makeText(context(), "成功" + goods.getData().toString(), Toast.LENGTH_SHORT).show();
        final List<Goods.DataBean> list = goods.getData();
        LinearLayoutManager manager = new LinearLayoutManager(context());
        mMainRecycler.setLayoutManager(manager);
        final MyAdapter adapter = new MyAdapter(list, context());
        mMainRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Goods.DataBean bean = list.get(position);
                EventBus.getDefault().postSticky(bean);
                Intent intent = new Intent(MainActivity.this, ScendActivity.class);
                startActivity(intent);
            }
        });
        adapter.setOnItemLongClickListener(new MyAdapter.OnItemLongClickListener() {
            @Override
            public void OnItemLongClick(View view, final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("数据");
                builder.setMessage("确定删除吗");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.show();
            }
        });

    }

    @Override
    public void onError(String error) {
        Toast.makeText(context(), "失败", Toast.LENGTH_SHORT).show();

    }
}
