package com.chunlin.mulitmvp;

/**
 * 项目名称：MulitMvp
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2018/9/18 下午3:43
 * 修改人：chunlinwang
 * 修改时间：2018/9/18 下午3:43
 * 修改备注：
 */
public interface BaseView {
    void showLoading();

    void hideLoading();

    void showError(String message);
}
