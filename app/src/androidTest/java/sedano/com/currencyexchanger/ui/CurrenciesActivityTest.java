package sedano.com.currencyexchanger.ui;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import sedano.com.currencyexchanger.R;
import sedano.com.currencyexchanger.TestCurrencyApplication;
import sedano.com.currencyexchanger.injection.component.AppTestComponent;
import sedano.com.currencyexchanger.model.CurrencyResponse;
import sedano.com.currencyexchanger.service.ServiceClient;
import sedano.com.currencyexchanger.view.calculator.CalculatorActivity;
import sedano.com.currencyexchanger.view.currencies.CurrenciesActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.when;
import static sedano.com.currencyexchanger.view.calculator.CalculatorActivity.BASE_CURRENCY;
import static sedano.com.currencyexchanger.view.calculator.CalculatorActivity.CONVERT_TO;
import static sedano.com.currencyexchanger.view.calculator.CalculatorActivity.RATE;

/**
 * Created by luissedano on 22/10/17.
 */

@RunWith(AndroidJUnit4.class)
public class CurrenciesActivityTest {

    @Inject
    ServiceClient serviceClient;

    @Rule
    public IntentsTestRule<CurrenciesActivity> currenciesActivityActivityTestRule =
            new IntentsTestRule<>(CurrenciesActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Intents.init();
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        TestCurrencyApplication app = (TestCurrencyApplication) instrumentation.getTargetContext().getApplicationContext();
        AppTestComponent component = (AppTestComponent) app.getAppComponent();
        component.inject(this);
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

    @Test
    public void testOnServiceErrorDialogIsShown() throws Exception {
        when(serviceClient.getExchangeRatesForCurrency(any(String.class)))
                .thenReturn(Observable.error(new Throwable("Error error")));

        currenciesActivityActivityTestRule.launchActivity(null);
        onView(ViewMatchers.withText(R.string.error_loading_list)).check(matches(isDisplayed()));
    }

    @Test
    public void testOnServiceSuccessAUDisInList() throws Exception {
        when(serviceClient.getExchangeRatesForCurrency("USD"))
                .thenReturn(Observable.just(generateResponse()));

        currenciesActivityActivityTestRule.launchActivity(null);

        onView(withText(contains("AUD"))).check(matches(isDisplayed()));
    }

    @Test
    public void testOnItemClickedLaunchesCalculatorActivity() throws Exception {
        when(serviceClient.getExchangeRatesForCurrency(any(String.class)))
                .thenReturn(Observable.just(generateResponse()));

        currenciesActivityActivityTestRule.launchActivity(null);

        onView(withText(contains("AUD"))).check(matches(isDisplayed()));

        Matcher<Intent> expectedIntent = allOf(hasExtra(BASE_CURRENCY, "EUR"),
                hasExtra(CONVERT_TO, "AUD"), hasExtra(RATE, 1.2f), hasComponent(CalculatorActivity.class.getName()));

        intended(expectedIntent);
    }

    private CurrencyResponse generateResponse() {
        CurrencyResponse response = new CurrencyResponse();
        Map<String, Float> rates = new HashMap<>();
        rates.put("AUD", 1.2f);
        rates.put("HKD", 6.3f);
        response.setRates(rates);
        return response;
    }
}
