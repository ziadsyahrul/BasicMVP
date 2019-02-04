package com.ziadsyahrul.basicmvp.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.ziadsyahrul.basicmvp.R;
import com.ziadsyahrul.basicmvp.adapter.MainAdapter;
import com.ziadsyahrul.basicmvp.main.MainContract;
import com.ziadsyahrul.basicmvp.main.MainPresenter;
import com.ziadsyahrul.basicmvp.model.UserData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.rv_user)
    RecyclerView rvUser;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;

    // TODO 1 membuat variable yang dibutuhkan
    private ProgressDialog progressDialog;
    private final MainPresenter mainPresenter = new MainPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // TODO 2 Mengambil data ke internet yang dilakukan oleh presenter
        mainPresenter.getDataListUser();

        // TODO 5 membuat swipe listener
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainPresenter.getDataListUser();
            }
        });


    }

    // TODO 3 Mengisi perintah method view yang dibutuhkan
    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
        swiperefresh.setRefreshing(false);
    }

    @Override
    public void showDataListUser(List<UserData> userDataList) {
        // TODO 4 Mensetting adapter
        rvUser.setLayoutManager(new LinearLayoutManager(this));
        rvUser.setAdapter(new MainAdapter(this, userDataList));
    }

    @Override
    public void showFailureMessage(String msg) {
        // menampilkan pesan gagal
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
