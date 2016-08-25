package com.qy.business.main.purchase.product.search;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qy.business.R;
import com.qy.business.bean.SearchKey;
import com.qy.business.main.base.BaseActivity;
import com.qy.business.main.purchase.product.search.result.SearchResultActivity;
import com.qy.business.tools.SPUtils;
import com.qy.business.tools.T;
import com.qy.business.view.flow.FlowLayout;
import com.qy.business.view.flow.TagAdapter;
import com.qy.business.view.flow.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by zhangyu on 2016/8/9.
 */
public class SearchActivity extends BaseActivity <SearchPresenter,SearchModel> implements SearchContract.View{
    private static final String SEARCH_KEY = "searchKey";
    private static final int SHOP = 0;
    private static final int GOODS = 1;
    @Bind(R.id.id_flow_layout_hot)
    TagFlowLayout tagFlowLayoutHot;
    @Bind(R.id.id_flow_layout_history)
    TagFlowLayout tagFlowLayoutHistory;
    @Bind(R.id.et_keyword)
    EditText editText;
    @Bind(R.id.tv_clear)
    TextView textViewClear;
    @Bind(R.id.tv_type)
    TextView tvType;
    List<SearchKey> keywordList;
    private int mCurrentType = GOODS;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        setSearchKey();
        mPresenter.getHotKeyList();
    }
    @OnClick(R.id.tv_search)
    public void onSearch(){
        String key = editText.getText().toString().trim();
        if(key.isEmpty()){
            T.showShort(this,"请输入搜索关键字");
            return;
        }

        goToSearch(key,mCurrentType);

        if(keywordList == null){
            keywordList = new ArrayList<>();
        }
        keywordList.add(new SearchKey(key,mCurrentType));
        SPUtils.putObject(this,SEARCH_KEY,keywordList);
        setSearchKey();

    }
    @OnClick(R.id.tv_clear)
    public void onClear(){
        SPUtils.remove(this,SEARCH_KEY);
        setSearchKey();
    }

    @OnClick(R.id.btn_return)
    public void onReturn(){
        finish();
    }
    @OnClick(R.id.tv_type)
    public void checkType() {
        if (mCurrentType == SHOP) {
            tvType.setText("商品");
            mCurrentType = GOODS;
        } else {
            tvType.setText("供应商");
            mCurrentType = SHOP;
        }
    }
    private void setSearchKey(){
        keywordList = (List<SearchKey>) SPUtils.getObject(this,SEARCH_KEY);
        final LayoutInflater mInflater = LayoutInflater.from(this);
        if(keywordList != null && !keywordList.isEmpty()){
            tagFlowLayoutHistory.setAdapter(new TagAdapter<SearchKey>(keywordList) {


                @Override
                public View getView(FlowLayout parent, int position, SearchKey searchKey) {
                    TextView tv = (TextView) mInflater.inflate(R.layout.item_search_key,
                            tagFlowLayoutHistory, false);
                    tv.setText(searchKey.getName());
                    return tv;
                }
            });
            tagFlowLayoutHistory.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    SearchKey searchKey = keywordList.get(position);
                    String name = searchKey.getName();
                    int type = searchKey.getType();
                    goToSearch(name,type);
                    return true;
                }
            });
            textViewClear.setVisibility(View.VISIBLE);
        }else{
            tagFlowLayoutHistory.removeAllViews();
            textViewClear.setVisibility(View.GONE);
        }
    }
    private void setHotKey(final List<SearchKey> list){
        final LayoutInflater mInflater = LayoutInflater.from(this);
        if(list != null && !list.isEmpty()){
            tagFlowLayoutHot.setAdapter(new TagAdapter<SearchKey>(list) {
                @Override
                public View getView(FlowLayout parent, int position, SearchKey s) {
                    TextView tv = (TextView) mInflater.inflate(R.layout.item_search_key,
                            tagFlowLayoutHot, false);
                    tv.setText(s.getName());
                    return tv;
                }
            });
            tagFlowLayoutHot.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    SearchKey searchKey = list.get(position);
                    String name = searchKey.getName();
                    int type = searchKey.getType();
                    goToSearch(name,type);
                    return true;
                }
            });
        }else{
            tagFlowLayoutHot.removeAllViews();
        }
    }

    @Override
    public void showHotKey(List<SearchKey> list) {
        setHotKey(list);
    }

    private void goToSearch(String keyword,int type){
        Intent intent = new Intent();
        intent.putExtra("type",type);
        intent.putExtra("keyword",keyword);
        intent.setClass(this, SearchResultActivity.class);
        startActivity(intent);
    }
}
