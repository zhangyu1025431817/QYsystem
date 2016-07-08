package com.qy.business.main.login.bind;

/**
 * Created by zhangyu on 2016/5/12.
 */
public class BindPhonePresenter extends BindPhoneContract.Presenter {
    @Override
    public void getMessageCode() {
        String safePassword = mView.getSafePassword();
        final String phoneNumber = mView.getPhoneNumber();
        mModel.getMessageCode(safePassword,phoneNumber, new BindPhoneModel.OnGetMessageListener() {
            @Override
            public void onSucceed() {

            }

            @Override
            public void onError(String msg) {
                mView.showDialogError(msg, msg);
            }
        });


    }

    @Override
    public void commit() {
        String phoneNumber = mView.getPhoneNumber();
        String messageCode = mView.getMessageCode();

        mModel.commit(phoneNumber, messageCode, new BindPhoneModel.OnBindListener() {
            @Override
            public void onSucceed() {
                mView.showSucceed();
            }

            @Override
            public void onError(String msg) {
                mView.showProgressError(null, msg);
            }
        });

    }

    @Override
    public void onStart() {

    }
}
