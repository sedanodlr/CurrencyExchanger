package sedano.com.currencyexchanger.injection.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import sedano.com.currencyexchanger.injection.context.ApplicationContext;
import sedano.com.currencyexchanger.injection.module.AppModule;
import sedano.com.currencyexchanger.service.ServiceClient;
import sedano.com.currencyexchanger.view.base.BaseActivity;

/**
 * A component whose lifetime is the life of the application.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    // Exposing to sub-graphs:
    @ApplicationContext
    Context context();

    ServiceClient serviceClient();

    void inject(BaseActivity baseActivity);
}