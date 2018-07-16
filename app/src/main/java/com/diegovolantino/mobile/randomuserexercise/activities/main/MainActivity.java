package com.diegovolantino.mobile.randomuserexercise.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.GridView;

import com.diegovolantino.mobile.randomuserexercise.R;
import com.diegovolantino.mobile.randomuserexercise.activities.detail.DetailActivity;
import com.diegovolantino.mobile.randomuserexercise.activities.main.adapters.GalleryImageAdapter;
import com.diegovolantino.mobile.randomuserexercise.data.DataManager;
import com.diegovolantino.mobile.randomuserexercise.data.model.RandomUser;
import com.diegovolantino.mobile.randomuserexercise.data.model.Result;
import com.diegovolantino.mobile.randomuserexercise.data.network.api.ApiInteractor;

import java.util.ArrayList;

/**
 * Created by Diego Pablo Volantino on 12/7/18.
 */

public class MainActivity extends AppCompatActivity {

    private static int mImageSpacing;
    private RandomUser rUser;
    private ArrayList<Result> resultsArrayList;

    private GridView gridview = null;

    private GalleryImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize gridview
        gridview = findViewById(R.id.gridview);

        // Initialize adapter
        adapter = new GalleryImageAdapter();

        // Initialize ArrayList
        resultsArrayList = new ArrayList<Result>();

        // Size images in GridView depending on number of columns
        // and image width
        gridview.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        int numColumns = 3;
                        final int columnWidth = (gridview.getWidth() / numColumns)
                                - mImageSpacing;
                        adapter.setItemHeight(columnWidth);
                    }
                });

        ApiInteractor interactor = new ApiInteractor();

        // Get Order Detail
        interactor.getResults(new ApiInteractor.ResponseCallback() {
            @Override
            public void onDataReady(RandomUser response) {
                rUser = response;
                if (rUser != null) {
                    if (rUser.getResults() != null && rUser.getResults().size() > 0) {
//                        setOrderDetailData();
                        resultsArrayList = rUser.getResults();
                        DataManager.getInstance().setResultsArrayList(resultsArrayList);
                        adapter = new GalleryImageAdapter(getApplicationContext(),
                                resultsArrayList);
                        gridview.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onError(Throwable t) {
                Log.e("error", " =>" + t.getMessage());
            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Result result = resultsArrayList.get(position);
                Intent i = new Intent(getApplicationContext(), DetailActivity.class);
                i.putExtra("Position", position);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
}