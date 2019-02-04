package com.ziadsyahrul.basicmvp.main;

import com.ziadsyahrul.basicmvp.model.UserResponse;
import com.ziadsyahrul.basicmvp.network.ApiClient;
import com.ziadsyahrul.basicmvp.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {

    // TODO 1 Membuat variable dan konstruktor untuk menerima context dari MainActivity
    // agar kita dapat mengakses method yang ada di MainActivity
    private final MainContract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }


    @Override
    public void getDataListUser() {
        // TODO 2 Menampilkan progress dialog
        view.showProgress();

        // TODO 3 Merequest data ke API
        // Mmebuat object call untuk mensetting endpoint dan parameter yang dibutuhkan
        Call<UserResponse> call = apiInterface.getUser(12);
        // Menjalankan request ke API
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                // Menutup progress
                view.hideProgress();

                // Mencek data response body
                if (response.body() != null){
                    UserResponse userResponse = response.body();
                    // Mencek dataListUser
                    if (userResponse.getUserDataList() != null){
                        // Mengirimkan data list user ke view untuk ditampilkan
                        view.showDataListUser(userResponse.getUserDataList());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                // Menutup progress
                view.hideProgress();
                // Menampilkan pesan penolakan server ke view
                view.showFailureMessage(t.getMessage());
            }
        });
    }
}
