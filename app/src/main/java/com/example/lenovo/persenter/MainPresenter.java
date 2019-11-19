package com.example.lenovo.persenter;

import android.util.Log;

import com.example.lenovo.apis.ResultCallBack;
import com.example.lenovo.beans.Bean;
import com.example.lenovo.gao_tk2_1.MainActivity;
import com.example.lenovo.moder.MainModel;
import com.example.lenovo.view.MainView;

public class MainPresenter {
    private MainView view;
    private  MainModel mainModel;

    public MainPresenter(MainView view) {
        mainModel = new MainModel();
        this.view = view;
    }

    public void getData() {
        mainModel.getData(new ResultCallBack<Bean>() {
            @Override
            public void onSuccess(Bean bean) {
                if (bean != null && bean.getResults().size()>0 && view!=null){
                    view.setData(bean);
                }
            }
            @Override
            public void onFail(String error) {
                Log.i("Tag",error);
            }
        });
    }
}
