package com.ziadsyahrul.basicmvp.main;

import com.ziadsyahrul.basicmvp.model.UserData;

import java.util.List;

public interface MainContract {
    interface View{
        void showProgress();
        void hideProgress();
        // Menampilkan data list view
        void showDataListUser(List<UserData> userDataList);

        // Menampilkan pesan gagal
        void showFailureMessage(String msg);

    }

    interface Presenter{
        // Membuat method untuk mengambil data dari ap
        void getDataListUser();
    }
}
