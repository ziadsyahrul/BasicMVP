package com.ziadsyahrul.basicmvp.detail;

import android.os.Bundle;

import com.ziadsyahrul.basicmvp.model.UserData;

public interface DetailContract {
    interface View{
        void showProgress();
        void hideProgress();
        void showDataSingleUser(UserData userData);
        void showFailureMessage(String msg);
    }

    interface Presenter{
        void getDataSingleUser(Bundle bundle);

    }
}
