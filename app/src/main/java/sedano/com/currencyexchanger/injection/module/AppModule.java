package sedano.com.currencyexchanger.injection.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import sedano.com.currencyexchanger.CurrencyApplication;
import sedano.com.currencyexchanger.injection.context.ApplicationContext;
import sedano.com.currencyexchanger.service.ServiceClient;
import sedano.com.currencyexchanger.service.ServiceClientImpl;

/**
 * This module provides objects which will live during the application lifecycle,
 * that is the reason why all of @Provide methods use a @Singleton scope.
 */
@Module
public class AppModule {

    protected final CurrencyApplication app;

    public AppModule(CurrencyApplication app) {
        this.app = app;
    }

    @Provides
    CurrencyApplication provideApplication() {
        return app;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return app;
    }

    @Provides
    ServiceClient provideServiceClient() {
        return new ServiceClientImpl();
    }


}