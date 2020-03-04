package com.example.pagedroom.dao;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Transaction;
import androidx.room.Update;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;
import com.example.pagedroom.entity.TransactionMaster;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Dao
public abstract class MftDao
{
    @Insert
    public abstract Long insertTransaction(TransactionMaster transactionMaster);

    @Query("SELECT TXN_ID,TXN_DATE,TYPE,USER_ID,BF_TXN_AMT,CURR_TXN_AMT,AF_TXN_AMT,SYNC_STATUS,BR_CODE ,GL_CODE,AC_NO,SETTLEMENT_ID,CUST_ID,DEVICE_ID FROM  transaction_master where AC_NO LIKE :acNo and TXN_ID LIKE :txn_id order by TXN_DATE DESC LIMIT 1000")
    public abstract DataSource.Factory<Integer,TransactionMaster> getTotTransactions(String acNo,String txn_id);

    @Query("SELECT TXN_ID,TXN_DATE,TYPE,USER_ID,BF_TXN_AMT,CURR_TXN_AMT,AF_TXN_AMT,SYNC_STATUS,BR_CODE ,GL_CODE,AC_NO,SETTLEMENT_ID,CUST_ID,DEVICE_ID FROM  transaction_master where Date(TXN_DATE) >= Date(:date) order by TXN_DATE DESC LIMIT :limit")
    public abstract List<TransactionMaster> getTotTransactionsByDate(String date, int limit);
}
