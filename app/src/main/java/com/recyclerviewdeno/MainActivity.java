package com.recyclerviewdeno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.adapter.DividerItemDecoration;
import com.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.widget.GridLayout.VERTICAL;

/**
 * @className: MainActivity
 * @classDescription: RecyclerView Demo
 * @author: miao
 * @createTime: 2017年2月21日
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //数据
    private List<String> mData = new ArrayList<String>();
    //RecyclerView
    private RecyclerView mRecyclerView;
    //适配器
    private RecyclerViewAdapter rva;
    //布局管理器
    private RecyclerView.LayoutManager mLayoutManager;
    //新增条目按钮
    private Button add_item;
    //改变条目按钮
    private Button change_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取数据
        getData();
        //加载布局
        initView();
    }

    /**
     * 获取数据
     * @author miao
     * @createTime 2017年2月21日
     * @lastModify 2017年2月21日
     * @param
     * @return mData
     */
    public List<String> getData() {
        for(int i = 0;i < 20;i++){
            mData.add("我是条目："+i);
        }
        return mData;
    }

    /**
     * 加载布局
     * @author miao
     * @createTime 2017年2月21日
     * @lastModify 2017年2月21日
     * @param
     * @return
     */
    private void initView() {

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        add_item = (Button) findViewById(R.id.add_item);
        add_item.setOnClickListener(this);
        change_item = (Button) findViewById(R.id.change_item);
        change_item.setOnClickListener(this);

        //给view之间添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        //获取一个布局管理器
        mLayoutManager = new LinearLayoutManager(this);
        //设置LinearLayout布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);

        //设置GridLayout布局管理器
        //mRecyclerView.setLayoutManager(new GridLayoutManager(this,3,VERTICAL,false));
        //设置StaggeredGridLayout(瀑布流)布局管理器
        //mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

        //获取适配器
        rva = new RecyclerViewAdapter(mData);
        //设置点击监听
        rva.setOnItemClickListener(new RecyclerViewAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Toast.makeText(MainActivity.this,"点击了条目："+postion,Toast.LENGTH_SHORT).show();
            }
        });
        //设置长按监听
        rva.setOnItemLongClickListener(new RecyclerViewAdapter.onItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int postion) {
                Toast.makeText(MainActivity.this,"移除了条目："+postion,Toast.LENGTH_SHORT).show();
                rva.removeData(postion);
            }
        });
        //为RecyclerView设置适配器
        mRecyclerView.setAdapter(rva);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_item:
                Toast.makeText(MainActivity.this,"在位置3新增了条目",Toast.LENGTH_SHORT).show();
                rva.addData(3);
                break;
            case R.id.change_item:
                Toast.makeText(MainActivity.this,"改变了位置4的内容",Toast.LENGTH_SHORT).show();
                rva.changeData(4);
                break;
        }
    }
}
