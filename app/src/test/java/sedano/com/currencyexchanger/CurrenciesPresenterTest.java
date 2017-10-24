package sedano.com.currencyexchanger;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;
import sedano.com.currencyexchanger.model.CurrencyPresentation;
import sedano.com.currencyexchanger.model.CurrencyResponse;
import sedano.com.currencyexchanger.presenter.CurrenciesPresenter;
import sedano.com.currencyexchanger.service.ServiceClient;
import sedano.com.currencyexchanger.view.currencies.CurrenciesMvpView;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static sedano.com.currencyexchanger.Constants.USD;

/**
 * Created by luissedano on 22/10/17.
 */

public class CurrenciesPresenterTest {

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
    }

    @Mock
    ServiceClient serviceClient;

    @Mock
    CurrenciesMvpView view;

    private CurrenciesPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new CurrenciesPresenter(serviceClient);
        presenter.attachView(view);
    }

    @After
    public void tearDown() throws Exception {
        presenter.detachView();
    }

    @Test
    public void testOnErrorPresenterCallsViewError() throws Exception {
        //TODO investigate why does this not work as expected
        when(serviceClient.getExchangeRatesForCurrency(any(String.class)))
                .thenReturn(Observable.error(new Exception("Exception")));

        presenter.updateCurrencyListForCurrency(USD);
        verify(view, times(1)).showErrorLoadingList();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void testOnSuccessPresenterCallsViewSuccess() throws Exception {
        when(serviceClient.getExchangeRatesForCurrency(any(String.class)))
                .thenReturn(Observable.just(generateResponse()));

        presenter.updateCurrencyListForCurrency(USD);
        verify(view, times(1)).updateCurrenciesList(ArgumentMatchers.<ArrayList<CurrencyPresentation>>any());
        verifyNoMoreInteractions(view);
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
