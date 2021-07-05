package com.example.iatiimd_eindoplevering;

import android.content.Context;

import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Idee.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract IdeeDAO ideeDAO();

    //Can be used outside of this function, without having to have the instance of this class
    private static AppDatabase instance;
//
//    static synchronized String setIdee(String idee){
//       idee = " ";
//       return idee;
//    }

    //Synchronizes with different threads
    static synchronized AppDatabase getInstance(Context context){
        //At the beginning it doesn't have an instance, when the application runs this class, it builds one and returns it getInstance().
        if(instance == null){
            instance = create(context);

        }
        //Makes sure we only have one instance of RoomDatabase in the entire application. Singleton
        return instance;
    }

    private static AppDatabase create(final Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "idee").fallbackToDestructiveMigration().build();
    }
}
