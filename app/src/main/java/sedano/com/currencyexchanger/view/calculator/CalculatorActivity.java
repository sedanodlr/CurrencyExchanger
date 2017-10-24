package sedano.com.currencyexchanger.view.calculator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import sedano.com.currencyexchanger.R;
import sedano.com.currencyexchanger.view.base.BaseActivity;

public class CalculatorActivity extends BaseActivity {

    public static final String BASE_CURRENCY = "base_currency";
    public static final String CONVERT_TO = "convert_to";
    public static final String RATE = "rate";

    private String baseCurrency, convertTo;
    private float rate;

    @Bind(R.id.tvBase)
    TextView tvBase;
    @Bind(R.id.tvExchange)
    TextView tvExchange;

    @Bind(R.id.etBase)
    EditText etBase;
    @Bind(R.id.etExchange)
    EditText etExchange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        baseCurrency = getIntent().getStringExtra(BASE_CURRENCY);
        convertTo = getIntent().getStringExtra(CONVERT_TO);
        rate = getIntent().getFloatExtra(RATE, 0f);

        if (baseCurrency == null || convertTo == null || rate == 0f) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getString(R.string.error_loading_calculator_data));
            builder.setPositiveButton(getString(R.string.ok), (dialog, which) -> finish());
            builder.create().show();
        }

        setupScreen();
    }

    private void setupScreen() {
        tvBase.setText(baseCurrency);
        tvExchange.setText(convertTo);
        etBase.addTextChangedListener(baseCurrencyTextWatcher);
    }

    private TextWatcher baseCurrencyTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (etBase.getText().length() > 0) {
                float value = Float.valueOf(etBase.getText().toString());
                float exchanged = value * rate;
                etExchange.setText(String.valueOf(exchanged));
            } else {
                etExchange.setText(null);
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }
}
