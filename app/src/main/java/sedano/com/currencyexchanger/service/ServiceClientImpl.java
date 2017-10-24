package sedano.com.currencyexchanger.service;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import io.reactivex.Observable;
import sedano.com.currencyexchanger.Constants;
import sedano.com.currencyexchanger.model.CurrencyResponse;
import sedano.com.currencyexchanger.model.FlagResponse;
import sedano.com.currencyexchanger.service.currency.CurrencyApi;
import sedano.com.currencyexchanger.service.flag.FlagsApi;

import static sedano.com.currencyexchanger.service.ServiceCreator.CURRENCY;
import static sedano.com.currencyexchanger.service.ServiceCreator.FLAG;

public class ServiceClientImpl implements ServiceClient {

    @Inject
    public ServiceClientImpl(){
        //Empty
    }

    @Override
    public Observable<FlagResponse> getFlagForCurrency(String currency) {
        return ServiceCreator.createService(FLAG, FlagsApi.class).getFlagForCurrency(currency);
    }

    @Override
    public Observable<CurrencyResponse> getExchangeRatesForCurrency(@Nullable String currency) {
        if (currency == null) {
            currency = Constants.USD;
        }
        return ServiceCreator.createService(CURRENCY, CurrencyApi.class).getExchangeRatesForCurrency(currency);
    }
}
