package sedano.com.currencyexchanger.view.currencies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import sedano.com.currencyexchanger.Constants;
import sedano.com.currencyexchanger.R;
import sedano.com.currencyexchanger.model.CurrencyPresentation;
import sedano.com.currencyexchanger.presenter.CurrenciesPresenter;
import sedano.com.currencyexchanger.view.base.BaseActivity;
import sedano.com.currencyexchanger.view.calculator.CalculatorActivity;

import static sedano.com.currencyexchanger.view.calculator.CalculatorActivity.BASE_CURRENCY;
import static sedano.com.currencyexchanger.view.calculator.CalculatorActivity.CONVERT_TO;
import static sedano.com.currencyexchanger.view.calculator.CalculatorActivity.RATE;

public class CurrenciesActivity extends BaseActivity implements CurrenciesMvpView, CurrenciesAdapter.OnItemClickListener {

    @Bind(R.id.spCurrency)
    Spinner currencySpinner;
    @Bind(R.id.rvCurrencies)
    RecyclerView rvCurrencies;
    CurrenciesAdapter currenciesAdapter;

    ArrayList<String> spinnerCurrencies;

    @Inject
    CurrenciesPresenter presenter;

    String mCurrency = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        presenter.attachView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.updateCurrencyListForCurrency(mCurrency);
    }

    @Override
    public void showErrorLoadingList() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.error_loading_list));
        builder.setPositiveButton(getString(R.string.ok), null);
        builder.create().show();
    }

    @Override
    public void updateCurrenciesList(ArrayList<CurrencyPresentation> list) {
        if (spinnerCurrencies == null) {
            spinnerCurrencies = new ArrayList<>();
            for (CurrencyPresentation presentation : list) {
                spinnerCurrencies.add(presentation.getName());
            }
            setSpinnerAdapter(spinnerCurrencies);
        }

        resetRecyclerView(list);
    }

    private void setSpinnerAdapter(ArrayList<String> spinnerCurrencies) {
        spinnerCurrencies.add(0, Constants.USD);
        ArrayAdapter<String> currencySpinnerAdapter = new ArrayAdapter<>(this,
                R.layout.currency_spinner_adapter, spinnerCurrencies);

        currencySpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // Apply the adapter to the your spinner
        currencySpinner.setAdapter(currencySpinnerAdapter);
        currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mCurrency = (String) parent.getItemAtPosition(position);
                presenter.updateCurrencyListForCurrency(mCurrency);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mCurrency = Constants.USD;
            }
        });
    }

    private void resetRecyclerView(ArrayList<CurrencyPresentation> list) {
        rvCurrencies.setLayoutManager(new LinearLayoutManager(this));
        if (currenciesAdapter == null) {
            currenciesAdapter = new CurrenciesAdapter(this, list, this);
        } else {
            currenciesAdapter.setList(list);
        }

        rvCurrencies.setAdapter(currenciesAdapter);
    }

    @Override
    public void onItemSelected(CurrencyPresentation currencyPresentation) {
        if (currenciesAdapter != null) {
            Intent intent = new Intent(this, CalculatorActivity.class);
            intent.putExtra(BASE_CURRENCY, mCurrency);
            intent.putExtra(CONVERT_TO, currencyPresentation.getName());
            intent.putExtra(RATE, currencyPresentation.getExchangeRate());
            startActivity(intent);
        }
    }
}
