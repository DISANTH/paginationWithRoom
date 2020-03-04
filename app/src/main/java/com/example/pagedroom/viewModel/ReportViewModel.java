package com.example.pagedroom.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.example.pagedroom.dao.MftDao;
import com.example.pagedroom.database.MftDataBase;
import com.example.pagedroom.entity.TransactionMaster;
import com.example.pagedroom.paging.datasourceFactory.TransactionMasterTxnIdDataSourceFactory;

import java.util.Date;

public class ReportViewModel extends AndroidViewModel {
    MftDataBase mftDataBase;
    MftDao mftDao;
    TransactionMasterTxnIdDataSourceFactory dataSourceFactory;
    private DataSource<String, TransactionMaster> mostRecentDataSource;

    private MutableLiveData<TransactionMasterFilter> filter = new MutableLiveData<>();

    public void setFilter(TransactionMasterFilter filter) {
        this.filter.setValue(filter);
    }

    private LiveData<PagedList<TransactionMaster>> transactionMasterList;

    public LiveData<PagedList<TransactionMaster>> getTransactionMasterList() {
        return transactionMasterList;
    }

    public ReportViewModel(@NonNull Application application) {
        super(application);
        TransactionMasterFilter transactionMasterFilter = new ReportViewModel.TransactionMasterFilter("%%","%%");
        setFilter(transactionMasterFilter);
        mftDataBase = MftDataBase.getInstance(application);
        mftDao = mftDataBase.getMftDao();
        dataSourceFactory =
                new TransactionMasterTxnIdDataSourceFactory(mftDao);
        mostRecentDataSource = dataSourceFactory.create();
        init();
    }

    public void init() {
        transactionMasterList = new LivePagedListBuilder(dataSourceFactory,50).build();
        PagedList.Config pagedListConfig =
                        (new PagedList.Config.Builder()).setEnablePlaceholders(true)
                                .setPrefetchDistance(150)
                                .setPageSize(50).build();
//        transactionMasterList = Transformations.switchMap( filter, new Function<TransactionMasterFilter, LiveData<PagedList<TransactionMaster>>>() {
//            @Override
//            public LiveData<PagedList<TransactionMaster>> apply(TransactionMasterFilter input)
//            {
//                DataSource.Factory<Integer,TransactionMaster> transactionMasterFactory;
//                PagedList.Config pagedListConfig =
//                        (new PagedList.Config.Builder()).setEnablePlaceholders(true)
//                                .setPrefetchDistance(150)
//                                .setPageSize(50).build();
//                transactionMasterFactory = mftDao.getTotTransactions(input.acNo,input.Txn_id);
//                return new LivePagedListBuilder(transactionMasterFactory,pagedListConfig).build();
//            }
//        } );
    }
    public static class TransactionMasterFilter
    {
        private String Txn_id;
        private String acNo;

        public String getTxn_id() {
            return Txn_id;
        }

        public void setTxn_id(String txn_id) {
            Txn_id = "%"+txn_id+"%";
        }

        public String getAcNo() {
            return acNo;
        }

        public void setAcNo(String acNo) {
            this.acNo = "%"+acNo+"%";
        }

        public TransactionMasterFilter(String txn_id, String acNo) {
            Txn_id = txn_id;
            this.acNo = acNo;
        }
    }
}
