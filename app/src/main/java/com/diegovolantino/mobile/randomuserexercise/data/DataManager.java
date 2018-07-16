package com.diegovolantino.mobile.randomuserexercise.data;

import com.diegovolantino.mobile.randomuserexercise.data.model.Result;

import java.util.ArrayList;

/**
 * Created by Diego Pablo Volantino on 12/7/18.
 */
public class DataManager {

    private static DataManager instance = null;

    private ArrayList<Result> resultsArrayList;

    protected DataManager() { }

    /**
     * Create an instance of this class
     * @return DataManager
     */
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }
    
    public ArrayList<Result> getResultsArrayList() {
        return resultsArrayList;
    }

    public void setResultsArrayList(ArrayList<Result> resultsArrayList) {
        this.resultsArrayList = resultsArrayList;
    }
}