package sedano.com.currencyexchanger.model;

public class CurrencyPresentation {

    private String name;
    private float exchangeRate;

    public CurrencyPresentation(String name, float exchangeRate) {
        this.name = name;
        this.exchangeRate = exchangeRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(float exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
