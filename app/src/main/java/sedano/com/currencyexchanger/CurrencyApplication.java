package sedano.com.currencyexchanger;

import android.app.Application;

import sedano.com.currencyexchanger.injection.component.AppComponent;
import sedano.com.currencyexchanger.injection.component.DaggerAppComponent;
import sedano.com.currencyexchanger.injection.module.AppModule;

public class CurrencyApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        createAppComponent();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    protected void createAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

}
