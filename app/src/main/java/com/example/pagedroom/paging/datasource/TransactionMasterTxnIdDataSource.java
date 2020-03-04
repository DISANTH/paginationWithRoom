package com.example.pagedroom.paging.datasource;

import androidx.annotation.NonNull;
import androidx.paging.ItemKeyedDataSource;

import com.example.pagedroom.dao.MftDao;
import com.example.pagedroom.entity.TransactionMaster;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TransactionMasterTxnIdDataSource extends ItemKeyedDataSource<String, TransactionMaster> {
    MftDao mftDao;

    public TransactionMasterTxnIdDataSource(MftDao mftDao) {
        this.mftDao = mftDao;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<TransactionMaster> callback)
    {
        List<TransactionMaster> list = mftDao.getTotTransactionsByDate("2020-02-11 13:09:18",params.requestedLoadSize);
        callback.onResult(list);
    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<TransactionMaster> callback) {
        List<TransactionMaster> list = mftDao.getTotTransactionsByDate(params.key,params.requestedLoadSize);
        callback.onResult(list);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<TransactionMaster> callback) {
        List<TransactionMaster> list = mftDao.getTotTransactionsByDate(params.key,params.requestedLoadSize);
        callback.onResult(list);
    }

    @NonNull
    @Override
    public String getKey(@NonNull TransactionMaster item) {
//        String string = "January 2, 2010";
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
//        Date date = null;
//        try {
//            date = format.parse(string);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        System.out.println(date);
        return item.getTxnDate();
    }
}
