package com.example.rxjavaretrofitdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.rxjavaretrofitdemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MovieDetailActivity extends AppCompatActivity {

    @InjectView(R.id.firTextView)
    TextView firTextView;
    @InjectView(R.id.secTextViewTextView)
    TextView secTextViewTextView;
    @InjectView(R.id.thirdTextView)
    TextView thirdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.inject(this);
        /*
                    intent.putExtra("fragTitle",list.get(position).getSubject().getTitle());
                    intent.putExtra("fragRank",list.get(position).getRank());
                    intent.putExtra("fraggenres", String.valueOf(list.get(position).getSubject().getGenres()));
        * */
        Intent intent = getIntent();
        String title = intent.getStringExtra("fragTitle");
        int rank = intent.getIntExtra("fragRank",0);
        String genre = intent.getStringExtra("fraggenres");
        firTextView.setText(title);
        secTextViewTextView.setText("排名："+rank);
        thirdTextView.setText("类型"+genre);

    }
}
