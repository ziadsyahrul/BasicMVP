package com.ziadsyahrul.basicmvp.login;

public interface LoginContract {
    // Membuat interface untuk method yang dibutuhkan pada View
    interface View{
        // Menampilkan dan menutup progress loading dialog
        void showProgress();
        void hideProgress();

        // Menampilkan dan melakukan sesuatu pada saat server merespon berhasil atau tidak
        void loginFailure(String msg);
        void loginSuccess(String token);
    }

    // Membuat interface untuk method yang dibutuhkan pada presenter / Mediator dengan model(Bisniss Logic)
    interface Presenter{
        // mmebuat method untuk mengeksekusi bisnis logic untuk log in contoh: (pengecekan data & pengiriman data ke internet
        void doLogin(String email, String password);


    }
}
