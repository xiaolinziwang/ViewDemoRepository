package com.shuidihuzhu.mulitmvp;

/**
 * 项目名称：MulitMvp
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2018/9/18 下午3:44
 * 修改人：chunlinwang
 * 修改时间：2018/9/18 下午3:44
 * 修改备注：
 */
public interface Presenter<T extends BaseView> {
    void destroy();

    void attachView(T view);

    void detachView();
}
