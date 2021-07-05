package com.example.iatiimd_eindoplevering;

import android.util.Log;

public class InsertIdeeTask implements Runnable{

    AppDatabase db;
    Idee idee;

    public InsertIdeeTask(AppDatabase db, Idee idee){
        this.db = db;
        this.idee = idee;
    }

    @Override
    public void run() {
        db.ideeDAO().insertIdee(this.idee);
        db.ideeDAO().getAll().get(0).getTitle();
    }
}
