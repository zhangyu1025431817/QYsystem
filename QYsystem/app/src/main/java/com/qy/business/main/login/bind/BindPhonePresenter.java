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

        mRxManager.add(mModel.getMessageCode(mView.getUserName(), mView.getPassword(), phoneNumber).subscribe(new MySubscriber<ISBindBean>() {
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
        final String safePassword = mView.getSafePassword();
        mRxManager.add(mModel.commit(mView.getUserName(), mView.getPassword(), phoneNumber, messageCode, safePassword).subscribe(new MySubscriber<SafeVerify>() {
            @Override
            public void onNext(SafeVerify bean) {
                super.onNext(bean);
                String status = bean.getStatus();
                if ("-1966".equals(status)) {
                    mView.showProgressError("手机绑定", "验证码过期或错误，请重新获取");
                } else if ("-1967".equals(status)) {
                    mView.showProgressError("手机绑定", "该手机号已绑定其他用户，请重新输入手机号");
                } else if ("-1954".equals(status)) {
                    mView.showProgressError("手机绑定", "服务器异常");
                } else if ("1".equals(status)) {
                    mView.showSucceed();
                } else {
                    mView.showProgressError("手机绑定", "未知错误");
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.showProgressError("安全验证", e.getMessage());
            }
        }));

    }

    @Override
    public void onStart() {

    }
}
