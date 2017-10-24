package sedano.com.currencyexchanger.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import sedano.com.currencyexchanger.Constants;

public class ServiceCreator {

    public static final String FLAG = "flag";
    public static final String CURRENCY = "currency";

    private static Retrofit flagRetrofit =
            new Retrofit.Builder()
                    .baseUrl(Constants.FLAG_BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();


    private static Retrofit currencyRetrofit =
            new Retrofit.Builder()
                    .baseUrl(Constants.CURRENCY_BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

    public static <S> S createService(String which, Class<S> sClass) {
        switch (which) {
            case FLAG:
                return flagRetrofit.create(sClass);
            case CURRENCY:
                return currencyRetrofit.create(sClass);
        }
        return null;
    }
}
