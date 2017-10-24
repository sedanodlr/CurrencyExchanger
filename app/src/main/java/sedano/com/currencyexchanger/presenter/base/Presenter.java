package sedano.com.currencyexchanger.presenter.base;

import sedano.com.currencyexchanger.view.base.MvpView;

public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}