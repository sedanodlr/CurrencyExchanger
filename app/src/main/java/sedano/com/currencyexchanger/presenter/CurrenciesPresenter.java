package sedano.com.currencyexchanger.presenter;

import android.support.annotation.Nullable;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import sedano.com.currencyexchanger.model.CurrencyPresentation;
import sedano.com.currencyexchanger.model.CurrencyResponse;
import sedano.com.currencyexchanger.presenter.base.BasePresenter;
import sedano.com.currencyexchanger.service.ServiceClient;
import sedano.com.currencyexchanger.view.currencies.CurrenciesMvpView;

public class CurrenciesPresenter extends BasePresenter<CurrenciesMvpView> {

    private final ServiceClient serviceClient;

    @Inject
    public CurrenciesPresenter(ServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }

    public void updateCurrencyListForCurrency(@Nullable String currency) {
        serviceClient.getExchangeRatesForCurrency(currency)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> getMvpView().showErrorLoadingList())
                .doOnNext(currencyResponse -> {
                    ArrayList<CurrencyPresentation> currencies = new ArrayList<>();
                    for (String mCurrency : currencyResponse.getRates().keySet()) {
                        currencies.add(new CurrencyPresentation(mCurrency, currencyResponse.getRates().get(mCurrency)));
                    }
                    getMvpView().updateCurrenciesList(currencies);
                })
                .subscribe();
    }
}
