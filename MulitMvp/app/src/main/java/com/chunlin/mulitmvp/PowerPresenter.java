package com.chunlin.mulitmvp;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：MulitMvp
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2018/9/18 下午4:01
 * 修改人：chunlinwang
 * 修改时间：2018/9/18 下午4:01
 * 修改备注：
 */
public class PowerPresenter<T extends BaseView> extends WrapperPresenter<T> {
    private T mView;
    private List<Presenter> mPresenters = new ArrayList<>();


    public  <Q extends Presenter<T>> void reqestPresenter(Q... cls) {
        for (Q cl : cls) {
            cl.attachView(mView);
            mPresenters.add(cl);
        }
    }


    public PowerPresenter(T mview) {
        this.mView = mview;
    }


    @Override
    public void destroy() {
        for (Presenter presenter : mPresenters) {
            presenter.destroy();
        }
    }
}
