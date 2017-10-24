package sedano.com.currencyexchanger.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import sedano.com.currencyexchanger.ui.CurrenciesActivityTest;
import sedano.com.currencyexchanger.injection.module.AppTestModule;

/**
 * Created by luissedano on 22/10/17.
 */

@Singleton
@Component(modules = AppTestModule.class)
public interface AppTestComponent extends AppComponent{
    void inject(CurrenciesActivityTest currenciesActivityTest);
}
