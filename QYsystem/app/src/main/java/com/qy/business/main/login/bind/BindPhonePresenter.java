package com.qy.business.main.login.bind;

import com.qy.business.bean.ISBindBean;
import com.qy.business.bean.SafeVerify;
import com.qy.business.network.MySubscriber;

/**
 * Created by zhangyu on 2016/5/12.
 */
public class BindPhonePresenter extends BindPhoneContract.Presenter {
    @Override
    public void getMessageCode() {
        final String phoneNumber = mView.getPhoneNumber();
        mRxManager.add(mModel.getMessageCode(phoneNumber).subscribe(new MySubscriber<ISBindBean>() {
            @Override
            public void onNext(ISBindBean isBindBean) {
                super.onNext(isBindBean);
                if (!"1".equals(isBindBean.getStatus())) {
                    mView.showDialogError(isBindBean.getMsg(), isBindBean.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                e.printStackTrace();
                mView.showDialogError(e.getMessage(), e.getMessage());
            }
        }));

    }

    @Override
    public void commit() {
        String phoneNumber = mView.getPhoneNumber();
        String messageCode = mView.getMessageCode();
        String safePassword = mView.getSafePassword();
        mRxManager.add(mModel.commit(phoneNumber, messageCode, safePassword).subscribe(new MySubscriber<SafeVerify>(){
            @Override
            public void onNext(SafeVerify bean) {
                super.onNext(bean);
                switch (bean.getStatus()){
                    case 2:
                        mView.showProgressError("手机绑定","验证码过期或错误，请重新获取");
                        break;
                    case 3:
                        mView.showProgressError("手机绑定","该手机号已绑定其他用户，请重新输入手机号");
                        break;
                    case 15:
                        mView.showSucceed();
                        break;
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.showProgressError("安全验证",e.getMessage());
            }
        }));

    }

    @Override
    public void onStart() {

    }
}
