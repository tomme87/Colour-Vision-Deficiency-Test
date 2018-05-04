package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;

/**
 * DAO interface for TestInfo objects.
 */
@Dao
public interface TestInfoDAO {

    @Query("SELECT * FROM local_tests WHERE processed = 1")
    List<TestInfo> getAll();

    @Query("SELECT * FROM local_tests WHERE downloadId = :downloadId")
    TestInfo getByDownloadId(long downloadId);

    @Query("SELECT * FROM local_tests WHERE id = :id")
    TestInfo getById(String id);

    @Query("UPDATE local_tests SET processed = 1 WHERE id = :id")
    void setProcessedById(String id);

    @Insert
    void insert(TestInfo testInfo);

    @Delete
    void delete(TestInfo testInfo);

    @Query("DELETE FROM local_tests")
    void delete();
}
