package sedano.com.currencyexchanger.injection.component;

import dagger.Component;
import sedano.com.currencyexchanger.injection.module.ActivityModule;
import sedano.com.currencyexchanger.injection.scope.PerActivity;
import sedano.com.currencyexchanger.view.calculator.CalculatorActivity;
import sedano.com.currencyexchanger.view.currencies.CurrenciesActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(CurrenciesActivity currenciesActivity);

    void inject(CalculatorActivity calculatorActivity);
}