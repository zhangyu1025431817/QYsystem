package com.qy.business.main.purchase.product.pay;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.qy.business.R;
import com.qy.business.bean.AddressBean;
import com.qy.business.bean.GetAdresslistBean;
import com.qy.business.config.Constant;
import com.qy.business.main.base.BaseActivity;
import com.qy.business.main.purchase.product.pay.result.PayResultActivity;
import com.qy.business.tools.SPUtils;
import com.qy.business.view.DialogDelegate;
import com.qy.business.view.SweetAlertDialogDelegate;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * Created by zhangyu on 2016/8/24.
 */
public class OrderPayActivity extends BaseActivity<OrderPayPresenter,OrderPayModel> implements OrderPayContract.View {
    private String mOrder;
    private List<AddressBean> mAddressList;
    private String mGoodsId = "42914";
    private String mGoodsBusinessId = "100003477";
    private String mDefaultAddressId = "2526";
    private Handler handler = new Handler();
    private DialogDelegate mDialogDelegate;
    @Override
    public int getLayoutId() {
        return R.layout.activity_order_pay;
    }

    @Override
    public void initView() {
        mDialogDelegate = new SweetAlertDialogDelegate(this);
    }

    @OnClick(R.id.tv_commit)
    public void commitOrder(){
       // mPresenter.pay(mOrder);
        mDialogDelegate.showProgressDialog(true,"正在提交订单...");
        new OrderThread().start();
    }
    @OnClick(R.id.btn_return)
    public void onReturn(){
        this.finish();
    }
    @Override
    public void paySucceed() {
        mDialogDelegate.clearDialog();
    }

    @Override
    public void aliPaySucceed() {
        startActivity(new Intent(this, PayResultActivity.class));
        finish();
    }

    @Override
    public void payFailed(String msg) {

    }

    @Override
    public void showAddress(GetAdresslistBean bean) {

    }

    // 提交订单线程-这个接口设计完全有问题，所以照着搬过来了，如果要修改就先修改接口
    class OrderThread extends Thread {
        @Override
        public void run() {
            try {
                String accountStr_AES = (String) SPUtils.get(OrderPayActivity.this,"username_aes","");
                String passwordStr_AES = (String) SPUtils.get(OrderPayActivity.this,"password","");
                String imei_AES = (String) SPUtils.get(OrderPayActivity.this,"imei","");
                String uri =  Constant.URL + Constant.BUSINESSES + "/suppliersubmit.html?username="
                        + accountStr_AES + "&userpass=" + passwordStr_AES + "&imei=" + imei_AES;
                HttpPost post = new HttpPost(uri);
                List<NameValuePair> Params = new ArrayList<NameValuePair>();
                Params.add(new BasicNameValuePair("address", mDefaultAddressId));
                Params.add(new BasicNameValuePair("goods_id[" + mGoodsBusinessId + "][]", mGoodsId));
                Params.add(new BasicNameValuePair("sku[" + mGoodsBusinessId + "][]", ""));
                Params.add(new BasicNameValuePair("buyattr[" + mGoodsBusinessId + "][]", ""));
                Params.add(new BasicNameValuePair("num[" + mGoodsBusinessId + "][]", "1"));
                Params.add(new BasicNameValuePair("note[" + mGoodsBusinessId + "]", ""));
                Params.add(new BasicNameValuePair("addr[" + mGoodsBusinessId + "]", "0"));
                post.setEntity(new UrlEncodedFormEntity(Params, "UTF-8"));
                // 3.设置超时
                HttpClient httpClient = new DefaultHttpClient();
                HttpParams httpParams = httpClient.getParams();
                HttpConnectionParams.setConnectionTimeout(httpParams, 10*1000);// 连接超时
                HttpConnectionParams.setSoTimeout(httpParams, 10*1000);// 读取超时

                // 4. 发送请求并获取反馈
                HttpResponse httpResponse = httpClient.execute(post);
                Log.i("result", "提交订单" + httpClient.toString());
                Log.i("result", "提交订单" + httpParams.toString());
                Log.i("result", "提交订单" + httpResponse.toString());
                if (httpResponse.getStatusLine().getStatusCode() == 200) { // StatusCode为200表示与服务端连接成功，404为连接不成功
                    String result = "";
                    result = EntityUtils.toString(httpResponse.getEntity());
                    Log.i("info4qiyitest", result);
                    if (result.length() == 0) {
//                        new AlertDialog.Builder(Tab_cgjh_swps_checkOrder_Activity.this).setTitle("网络异常")
//                                .setMessage("网络异常，请检查网络设置!")
//                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                    }
//                                }).create().show();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                mDialogDelegate.stopProgressWithFailed("订单提交","订单提交失败");
                            }
                        });
                    } else {
                        JSONTokener jt = new JSONTokener(result);
                        JSONObject bean = (JSONObject) jt.nextValue();
                        if (bean != null) {
                            String status = bean.getString("status");
                            if ("1".equals(status)) {
                                final String order_sn = bean.getString("data");
                                if (order_sn != null) {
//                                    Intent in = new Intent(Tab_cgjh_swps_checkOrder_Activity.this,
//                                            OrderPayActivity.class);
//                                    in.putExtra("order_sn", order_sn);
//                                    in.putExtra("order_price", totalMoney);
//                                    Log.i("info4qiyitest", "订单号 ：" + order_sn);
//                                    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    startActivity(in);
//                                    finish();
                                    mPresenter.pay(OrderPayActivity.this,order_sn);
                                }
                            } else {
                                final String msg = bean.getString("msg");
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(OrderPayActivity.this, msg, Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                });

                            }
                        }
                    }
                } else {
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
//                            new AlertDialog.Builder(Tab_cgjh_swps_checkOrder_Activity.this).setTitle("访问异常")
//                                    .setMessage("服务器访问异常，请稍候重试!")
//                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                        }
//                                    }).create().show();
                        }
                    });

                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                       // pd.dismiss();
                    }
                });
            }

        }
    }


}
