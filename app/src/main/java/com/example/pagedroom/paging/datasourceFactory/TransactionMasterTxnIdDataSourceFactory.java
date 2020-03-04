package com.example.pagedroom.paging.datasourceFactory;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.pagedroom.dao.MftDao;
import com.example.pagedroom.entity.TransactionMaster;
import com.example.pagedroom.paging.datasource.TransactionMasterTxnIdDataSource;

import java.util.Date;

public class TransactionMasterTxnIdDataSourceFactory extends DataSource.Factory<String, TransactionMaster> {

    private MftDao mftDao;

    public TransactionMasterTxnIdDataSourceFactory(MftDao mftDao) {
        this.mftDao = mftDao;
    }

    private MutableLiveData<TransactionMasterTxnIdDataSource> sourceLiveData =
            new MutableLiveData<>();

    private TransactionMasterTxnIdDataSource latestSource;

    @NonNull
    @Override
    public DataSource<String, TransactionMaster> create() {
        latestSource = new TransactionMasterTxnIdDataSource(mftDao);
        sourceLiveData.postValue(latestSource);
        return latestSource;
    }
}
