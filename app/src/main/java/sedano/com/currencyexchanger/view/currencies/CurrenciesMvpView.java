package sedano.com.currencyexchanger.view.currencies;

import java.util.ArrayList;

import sedano.com.currencyexchanger.model.CurrencyPresentation;
import sedano.com.currencyexchanger.view.base.MvpView;

public interface CurrenciesMvpView extends MvpView {
    void showErrorLoadingList();

    void updateCurrenciesList(ArrayList<CurrencyPresentation> list);
}
