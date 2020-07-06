package model;

public class TransactionRequest {

    private int uniqueNumber;
    private int clientId;
    //флаг на покупку/продажу: покупка = true, продажа = false
    private boolean purchaseSale;
    private String assets;
    private int amountAssets;

    public int getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(int uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public boolean isPurchaseSale() {
        return purchaseSale;
    }

    public void setPurchaseSale(boolean purchaseSale) {
        this.purchaseSale = purchaseSale;
    }

    public String getAssets() {
        return assets;
    }

    public void setAssets(String assets) {
        this.assets = assets;
    }

    public int getAmountAssets() {
        return amountAssets;
    }

    public void setAmountAssets(int amountAssets) {
        this.amountAssets = amountAssets;
    }
}
