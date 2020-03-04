package com.example.pagedroom.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.pagedroom.dao.MftDao;
import com.example.pagedroom.entity.TransactionMaster;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Database(entities = {TransactionMaster.class},version = 1,exportSchema = false)
public abstract class MftDataBase extends RoomDatabase
{
    private final static String TAG = "[MftDataBase]";
    private static final String DATABASE_NAME = "mft.sqlite";
    private static MftDataBase INSTANCE;
    public abstract MftDao getMftDao();
    public static MftDataBase getInstance(Context context)
    {
        if(INSTANCE == null)
        {
            copyAttachedDatabase(context,DATABASE_NAME);
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(), MftDataBase.class,DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().addCallback(callback).build();
        }
        return INSTANCE;
    }
    private static void copyAttachedDatabase(Context context, String databaseName) {
        final File dbPath = context.getDatabasePath(databaseName);

        // If the database already exists, return
        if (dbPath.exists()) {
            return;
        }

        // Make sure we have a path to the file
        dbPath.getParentFile().mkdirs();

        // Try to copy database file
        try {
            final InputStream inputStream = context.getAssets().open("databases/" + databaseName);
            final OutputStream output = new FileOutputStream(dbPath);

            byte[] buffer = new byte[8192];
            int length;

            while ((length = inputStream.read(buffer, 0, 8192)) > 0) {
                output.write(buffer, 0, length);
            }

            output.flush();
            output.close();
            inputStream.close();
        }
        catch (IOException e) {
            Log.d(TAG, "[DataBaseOpen] Failed to open copy Database file", e);
            e.printStackTrace();
        }
    }
    public static MftDataBase.Callback callback=new MftDataBase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate( db );
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    public static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>
    {
        MftDataBase appDatabase;
        public PopulateDbAsyncTask(MftDataBase appDatabase)
        {
            this.appDatabase = appDatabase;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}


