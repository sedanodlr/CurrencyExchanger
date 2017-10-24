package sedano.com.currencyexchanger;

import sedano.com.currencyexchanger.injection.component.AppComponent;
import sedano.com.currencyexchanger.injection.component.DaggerAppTestComponent;
import sedano.com.currencyexchanger.injection.module.AppTestModule;

/**
 * Created by luissedano on 22/10/17.
 */

public class TestCurrencyApplication extends CurrencyApplication {

    private AppComponent appComponent;

    @Override
    public AppComponent getAppComponent() {
        if (appComponent == null) {
            createAppComponent();
        }
        return appComponent;
    }

    @Override
    protected void createAppComponent() {
        appComponent = DaggerAppTestComponent.builder()
                .appTestModule(new AppTestModule(this))
                .build();
    }
}
