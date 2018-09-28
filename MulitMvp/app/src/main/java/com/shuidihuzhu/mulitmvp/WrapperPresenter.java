package com.shuidihuzhu.mulitmvp;

/**
 * 项目名称：MulitMvp
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2018/9/18 下午3:45
 * 修改人：chunlinwang
 * 修改时间：2018/9/18 下午3:45
 * 修改备注：
 */
public abstract class WrapperPresenter<T extends BaseView> implements Presenter<T> {
    T mView;


    @Override
    public void attachView(T view) {
        mView=view;
    }


    @Override
    public void detachView() {
        mView=null;
    }
}
