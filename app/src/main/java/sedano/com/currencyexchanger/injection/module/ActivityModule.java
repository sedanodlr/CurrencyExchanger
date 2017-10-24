package sedano.com.currencyexchanger.injection.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import sedano.com.currencyexchanger.injection.context.ActivityContext;
import sedano.com.currencyexchanger.injection.scope.PerActivity;
import sedano.com.currencyexchanger.presenter.CurrenciesPresenter;
import sedano.com.currencyexchanger.service.ServiceClient;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return activity;
    }

    @Provides
    @PerActivity
    CurrenciesPresenter provideCurrenciesPresenter(ServiceClient serviceClient){
        return new CurrenciesPresenter(serviceClient);
    }

}