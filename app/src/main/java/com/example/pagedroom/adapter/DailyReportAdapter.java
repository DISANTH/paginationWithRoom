package com.example.pagedroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pagedroom.databinding.ReportRecyclerItemBinding;
import com.example.pagedroom.entity.TransactionMaster;

import java.util.List;

public class DailyReportAdapter extends PagedListAdapter<TransactionMaster,DailyReportAdapter.ViewHolder>
{
    private List<TransactionMaster> transactionMasterList;
    private Context context;

    public DailyReportAdapter() {
        super( DIFF_CALLBACK );
    }


//    public void setTransactionMasterList(List<TransactionMaster> transactionMasterList) {
//        this.transactionMasterList = transactionMasterList;
//        notifyDataSetChanged();
//    }


//    public DailyReportAdapter(List<TransactionMaster> transactionMasterList, Context context) {
//        this.transactionMasterList = transactionMasterList;
//        this.context = context;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ReportRecyclerItemBinding itemBinding =
                ReportRecyclerItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
//        TransactionMaster transactionMaster = transactionMasterList.get(position);
//        holder.bind(transactionMaster);

        TransactionMaster transactionMaster = getItem(position);
        if (transactionMaster != null) {
            holder.bind(transactionMaster);
        }
    }

//    @Override
//    public int getItemCount() {
//        return transactionMasterList == null ? 0 : transactionMasterList.size();
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ReportRecyclerItemBinding itemRowBinding;

        public ViewHolder(ReportRecyclerItemBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(TransactionMaster transactionMaster) {
            itemRowBinding.setTransaction(transactionMaster);
            itemRowBinding.executePendingBindings();
        }
    }

     private static DiffUtil.ItemCallback<TransactionMaster> DIFF_CALLBACK = new DiffUtil.ItemCallback<TransactionMaster>() {
        @Override
        public boolean areItemsTheSame(@NonNull TransactionMaster oldItem, @NonNull TransactionMaster newItem) {
            return oldItem.getTxnID() == newItem.getTxnID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull TransactionMaster oldItem, @NonNull TransactionMaster newItem) {
            return oldItem.getTxnID().equals(newItem.getTxnID());
        }
    };
}
