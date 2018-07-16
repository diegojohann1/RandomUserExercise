package com.diegovolantino.mobile.randomuserexercise.activities.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.diegovolantino.mobile.randomuserexercise.R;
import com.diegovolantino.mobile.randomuserexercise.data.model.Result;
import com.diegovolantino.mobile.randomuserexercise.data.DataManager;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    private ArrayList<Result> results;

    ImageLoader imageLoader;

    @BindView(R.id.iv_picture)
    ImageView ivPicture;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_firstname)
    TextView tvFirstname;
    @BindView(R.id.tv_lastname)
    TextView tvLastname;
    @BindView(R.id.tv_mail)
    TextView tvMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        int pos = i.getIntExtra("Position", 0);
        results = DataManager.getInstance().getResultsArrayList();
        Result result = results.get(pos);

        imageLoader = ImageLoader.getInstance();

        // Bind annotated fields and methods in the specified activity
        ButterKnife.bind(this);

        ivPicture.setScaleType(ImageView.ScaleType.CENTER_CROP);

        //Large
        imageLoader.displayImage(result.getPicture().getLarge(),
                ivPicture);

        tvUsername.setText(result.getLogin().getUsername());
        tvFirstname.setText(result.getName().getFirst());
        tvLastname.setText(result.getName().getLast());
        tvMail.setText(result.getEmail());
    }
}
