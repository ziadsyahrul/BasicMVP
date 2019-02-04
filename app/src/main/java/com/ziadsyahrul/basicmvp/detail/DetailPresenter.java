package com.ziadsyahrul.basicmvp.detail;

import android.os.Bundle;

import com.ziadsyahrul.basicmvp.model.SingleUserResponse;
import com.ziadsyahrul.basicmvp.network.ApiClient;
import com.ziadsyahrul.basicmvp.network.ApiInterface;
import com.ziadsyahrul.basicmvp.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter implements DetailContract.Presenter{

    // TODO 1 Membuat konstruktor dan variable yang dibutuhkan
    private final DetailContract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private int id;

    public DetailPresenter(DetailContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataSingleUser(Bundle bundle) {
        // TODO 2 Mencek bundle apakah ada isinya?
        if (bundle != null){
            // Mengambil id dan dimasukkan ke dalam variable id
            id = bundle.getInt(Constant.KEY_ID);

            // TODO 3 Mengambil data dengan id
            // Tampilkan progress dialog
            view.showProgress();

            Call<SingleUserResponse> call = apiInterface.getDataSingleUser(id);
            call.enqueue(new Callback<SingleUserResponse>() {
                @Override
                public void onResponse(Call<SingleUserResponse> call, Response<SingleUserResponse> response) {
                    // Menutup progress dialog
                    view.hideProgress();

                    // Mencek response body
                    if (response.body() != null){
                        // memasukkan response body ke dalam singleUserResponse
                        SingleUserResponse singleUserResponse = response.body();
                        // Mencek apakah singleUserResponse data ada isinya?
                        if (singleUserResponse.getData() != null){
                            // Mengirimkan data single user ke view untuk ditampilkan
                            view.showDataSingleUser(singleUserResponse.getData());
                        }
                    }
                }

                @Override
                public void onFailure(Call<SingleUserResponse> call, Throwable t) {
                    view.hideProgress();
                    view.showFailureMessage(t.getMessage());
                }
            });

        }

    }
}
