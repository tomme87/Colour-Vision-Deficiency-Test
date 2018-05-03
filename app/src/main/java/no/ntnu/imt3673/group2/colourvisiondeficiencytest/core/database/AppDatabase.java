package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;

/**
 * Application database for storing information about downloaded (local) tests
 * based on singelton from https://medium.com/@magdamiu/android-room-persistence-library-97ad0d25668e
 */
@Database(entities = {TestInfo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TestInfoDAO testInfoDAO();

    private static AppDatabase INSTANCE;

    /**
     * Used get an instance of database
     * @param context
     * @return singelton instance of AppDatabase
     */
    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "tests_db")
                        .build();
        }
        return INSTANCE;
    }

    /**
     * Deletes a instance of the database.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

}
