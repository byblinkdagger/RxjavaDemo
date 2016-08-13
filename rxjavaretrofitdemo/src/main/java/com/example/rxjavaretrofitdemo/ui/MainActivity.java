package com.example.rxjavaretrofitdemo.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.rxjavaretrofitdemo.R;
import com.example.rxjavaretrofitdemo.base.CommonRecyclerAdapter;
import com.example.rxjavaretrofitdemo.bean.MovieInfo;
import com.example.rxjavaretrofitdemo.util.RetrofitUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mrecycler;
    private List<MovieInfo.SubjectsBean> list;
    private CommonRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initContent();
    }

    private void initContent() {
        list=new ArrayList<MovieInfo.SubjectsBean>();
        getUSMovie();
        mrecycler= (RecyclerView) findViewById(R.id.mrecycler);
        mrecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mrecycler.setItemAnimator(new DefaultItemAnimator());
        if (list != null) {
            adapter=new CommonRecyclerAdapter<MovieInfo.SubjectsBean>(this,R.layout.movie_item,list) {


                @Override
                protected void convert(ViewHolder holder, MovieInfo.SubjectsBean subjectBean) {
                    holder.setText(R.id.titleTextView,subjectBean.getSubject().getTitle());
                    holder.setImageURI(R.id.contentImageView,subjectBean.getSubject().getImages().getLarge());
                }
            };
            mrecycler.setAdapter(adapter);
        }

    }

    public void getUSMovie() {
        Subscriber<MovieInfo> subscriber = new Subscriber<MovieInfo>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "getUSMovie Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                //mTextView.setText(e.getMessage());
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("lxg",e.getMessage());
            }

            @Override
            public void onNext(MovieInfo movieInfo) {

                if (movieInfo.getSubjects() != null) {
                    Log.e("lxg","datas != null");
                    if (list != null) {
                        list.clear();
                    }
                    list.addAll(movieInfo.getSubjects());
                    adapter.notifyDataSetChanged();
                }
                Log.e("lxg","datas == null");

            }
        };
        RetrofitUtil.getInstance().getUSMovie(subscriber);
    }
}
