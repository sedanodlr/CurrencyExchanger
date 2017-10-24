package sedano.com.currencyexchanger.service;

import android.support.annotation.Nullable;

import io.reactivex.Observable;
import sedano.com.currencyexchanger.model.CurrencyResponse;
import sedano.com.currencyexchanger.model.FlagResponse;

public interface ServiceClient {

    Observable<FlagResponse> getFlagForCurrency(String currency);

    Observable<CurrencyResponse> getExchangeRatesForCurrency(@Nullable String currency);

}
