package sedano.com.currencyexchanger.injection.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import sedano.com.currencyexchanger.CurrencyApplication;
import sedano.com.currencyexchanger.injection.context.ApplicationContext;
import sedano.com.currencyexchanger.service.ServiceClient;
import sedano.com.currencyexchanger.service.ServiceClientImpl;

import static org.mockito.Mockito.mock;

@Module
public class AppTestModule {
    protected final CurrencyApplication app;

    public AppTestModule(CurrencyApplication app) {
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
        return mock(ServiceClientImpl.class);
    }
}