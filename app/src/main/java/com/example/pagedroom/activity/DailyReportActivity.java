package com.example.pagedroom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import com.example.pagedroom.R;
import com.example.pagedroom.adapter.DailyReportAdapter;
import com.example.pagedroom.databinding.ActivityDailyReportBinding;
import com.example.pagedroom.entity.TransactionMaster;
import com.example.pagedroom.viewModel.ReportViewModel;


import java.util.ArrayList;
import java.util.List;

public class DailyReportActivity extends AppCompatActivity {

    ActivityDailyReportBinding dailyReportBinding;

    List<TransactionMaster> transactionMasterList = new ArrayList<>();
    ReportViewModel reportViewModel;
    ReportViewModel.TransactionMasterFilter transactionMasterFilter = new ReportViewModel.TransactionMasterFilter("%%","%%");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        dailyReportBinding = DataBindingUtil.setContentView(this, R.layout.activity_daily_report );

        reportViewModel = ViewModelProviders.of(this).get(ReportViewModel.class);
        reportViewModel.setFilter(transactionMasterFilter);
        //reportViewModel.init();
        dailyReportBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());

        dailyReportBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        Log.e("[transactionMasterList]"," Size = "+String.valueOf(transactionMasterList.size()));
        final DailyReportAdapter dailyReportAdapter = new DailyReportAdapter();
        dailyReportBinding.recyclerView.setAdapter(dailyReportAdapter);

        reportViewModel.getTransactionMasterList().observe( this, new Observer<PagedList<TransactionMaster>>() {
            @Override
            public void onChanged(PagedList<TransactionMaster> transactionMasters) {
                dailyReportAdapter.submitList(transactionMasters);
            }
        });


        dailyReportBinding.acNoAutoCompleteTextView.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String acNo = dailyReportBinding.acNoAutoCompleteTextView.getText().toString();
                transactionMasterFilter.setAcNo(acNo);
                reportViewModel.setFilter(transactionMasterFilter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
