package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;

@Dao
public interface TestInfoDAO {

    @Query("SELECT * FROM local_tests")
    List<TestInfo> getAll();

    @Insert
    void insertAll(TestInfo tests);

    @Insert
    void insert(TestInfo testInfo);

    @Delete
    void delete(TestInfo testInfo);

    @Query("DELETE FROM local_tests")
    void delete();
}
