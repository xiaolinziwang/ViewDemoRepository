package com.shuidihuzhu.mulitmvp;

/**
 * 项目名称：MulitMvp
 * 类描述：
 * 创建人：chunlinwang
 * 创建时间：2018/9/18 下午4:13
 * 修改人：chunlinwang
 * 修改时间：2018/9/18 下午4:13
 * 修改备注：
 */
public class BannerActPower extends BaseMvpAct<PowerPresenter> implements BannerView  {
    @Override
    public void render(BannerBean bean) {

    }


    @Override
    public void initialize() {

    }


    @Override
    protected PowerPresenter createP() {
        PowerPresenter presenter = new PowerPresenter<>(this);
        Banner1Presenter presenter1 = new Banner1Presenter();
        Banner2Presenter presenter2 = new Banner2Presenter();
        //····(这里你可以添加任何你需要的presenter)····
        presenter.reqestPresenter(presenter1,presenter2);
        return presenter;
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
