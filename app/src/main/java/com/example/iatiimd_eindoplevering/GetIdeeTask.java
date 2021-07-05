package com.example.iatiimd_eindoplevering;

import android.util.Log;

public class GetIdeeTask implements  Runnable{

    AppDatabase db;

    public GetIdeeTask(AppDatabase db){
        this.db = db;
    }

    @Override
    public void run() {
        String title = db.ideeDAO().getAll().get(0).getTitle();
        Log.d("title", title);
    }
}
