package sedano.com.currencyexchanger.service.flag;

import io.reactivex.Observable;
import retrofit2.http.GET;
import sedano.com.currencyexchanger.model.FlagResponse;

public interface FlagsApi {

    @GET("rest/v2/currency/{curr}")
    Observable<FlagResponse> getFlagForCurrency(String curr);
}
