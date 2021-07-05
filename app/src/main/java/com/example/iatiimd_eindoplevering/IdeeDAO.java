package com.example.iatiimd_eindoplevering;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IdeeDAO {

    @Query("SELECT * FROM Idee")
    List<Idee> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIdee(Idee Idee);

    @Delete
    void delete(Idee Idee);
}
