package com.example.pagedroom.entity;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "transaction_master",primaryKeys = {"TXN_ID","TYPE"})
public class TransactionMaster extends BaseObservable
{
    @ColumnInfo(name = "TXN_ID")
    @NonNull
    @SerializedName("txnId")
    private String txnID;

    @SerializedName("txnDate")
    @ColumnInfo(name = "TXN_DATE")
    private String txnDate;

    @NonNull
    @SerializedName("type")
    @ColumnInfo(name = "TYPE")
    private String type;

    @SerializedName("userId")
    @ColumnInfo(name = "USER_ID")
    private String userID;

    @SerializedName("bfTxnAmt")
    @ColumnInfo(name = "BF_TXN_AMT")
    private String beforeTxnAmt;

    @SerializedName("currTxnAmt")
    @ColumnInfo(name = "CURR_TXN_AMT")
    private String currentTxnAmt;

    @SerializedName("afTxnAmt")
    @ColumnInfo(name = "AF_TXN_AMT")
    private String afterTxnAmt;

    @SerializedName("syncStatus")
    @ColumnInfo(name = "SYNC_STATUS")
    private String syncStatus;

    @SerializedName("brCode")
    @ColumnInfo(name = "BR_CODE")
    private String brCode;

    @SerializedName("agentCode")
    @ColumnInfo(name = "GL_CODE")
    private String glCode;

    @SerializedName("acNo")
    @ColumnInfo(name = "AC_NO")
    private String acNo;

    @SerializedName("settlementId")
    @ColumnInfo(name = "SETTLEMENT_ID")
    private String settlementID;

    @SerializedName("custId")
    @ColumnInfo(name = "CUST_ID")
    private String custID;

    @SerializedName("deviceId")
    @ColumnInfo(name = "DEVICE_ID")
    private String deviceID;

    public TransactionMaster(@NonNull String txnID, String txnDate, String type, String userID, String beforeTxnAmt, String currentTxnAmt, String afterTxnAmt, String syncStatus, String brCode, String glCode, String acNo, String settlementID, String custID, String deviceID) {
        this.txnID = txnID;
        this.txnDate = txnDate;
        this.type = type;
        this.userID = userID;
        this.beforeTxnAmt = beforeTxnAmt;
        this.currentTxnAmt = currentTxnAmt;
        this.afterTxnAmt = afterTxnAmt;
        this.syncStatus = syncStatus;
        this.brCode = brCode;
        this.glCode = glCode;
        this.acNo = acNo;
        this.settlementID = settlementID;
        this.custID = custID;
        this.deviceID = deviceID;
    }

    @Bindable
    public String getTxnID() {
        return txnID;
    }

    @Bindable
    public String getTxnDate() {
        return txnDate;
    }

    public String getType() {
        return type;
    }

    public String getUserID() {
        return userID;
    }

    @Bindable
    public String getBeforeTxnAmt() {
        return beforeTxnAmt;
    }

    @Bindable
    public String getCurrentTxnAmt() {
        return currentTxnAmt;
    }

    @Bindable
    public String getAfterTxnAmt() {
        return afterTxnAmt;
    }

    @Bindable
    public String getSyncStatus() {
        return syncStatus;
    }

    public String getBrCode() {
        return brCode;
    }

    public String getGlCode() {
        return glCode;
    }

    @Bindable
    public String getAcNo() {
        return acNo;
    }

    public String getSettlementID() {
        return settlementID;
    }

    public String getCustID() {
        return custID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }
}
