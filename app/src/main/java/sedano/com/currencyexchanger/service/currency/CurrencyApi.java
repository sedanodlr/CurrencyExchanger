package sedano.com.currencyexchanger.service.currency;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sedano.com.currencyexchanger.model.CurrencyResponse;

public interface CurrencyApi {

    @GET("latest")
    Observable<CurrencyResponse> getExchangeRatesForCurrency(@Query("base") String currency);
}
