package sedano.com.currencyexchanger.view.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import sedano.com.currencyexchanger.CurrencyApplication;
import sedano.com.currencyexchanger.injection.component.ActivityComponent;
import sedano.com.currencyexchanger.injection.component.AppComponent;
import sedano.com.currencyexchanger.injection.component.DaggerActivityComponent;
import sedano.com.currencyexchanger.injection.module.ActivityModule;

public class BaseActivity extends AppCompatActivity {
    private ActivityComponent mActivityComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
        initializeInjector();
    }


    protected AppComponent getApplicationComponent() {
        return ((CurrencyApplication) getApplication()).getAppComponent();
    }

    private void initializeInjector() {
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }
}

