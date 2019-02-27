package com.chunlin.mulitmvp;

/**
 * 项目名称：MulitMvp
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2018/9/18 下午3:55
 * 修改人：chunlinwang
 * 修改时间：2018/9/18 下午3:55
 * 修改备注：
 */
public class BannerAct extends BaseMvpAct<BannerPresenter> implements BannerView {
    @Override
    public void render(BannerBean bean) {

    }


    @Override
    public void initialize() {

    }


    @Override
    protected BannerPresenter createP() {
        return new BannerPresenter();
    }


    @Override
    protected int addContentView() {
        return 0;
    }


    @Override
    public void showLoading() {

    }


    @Override
    public void hideLoading() {

    }


    @Override
    public void showError(String message) {

    }
}
