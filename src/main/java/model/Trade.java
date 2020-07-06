package model;
public class Trade {
    private String uniqueId;
    private int numberTransactionRequestFrom;
    private int numberTransactionRequestTo;
    private int amountAssets;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public int getNumberTransactionRequestFrom() {
        return numberTransactionRequestFrom;
    }

    public void setNumberTransactionRequestFrom(int numberTransactionRequestFrom) {
        this.numberTransactionRequestFrom = numberTransactionRequestFrom;
    }

    public int getNumberTransactionRequestTo() {
        return numberTransactionRequestTo;
    }

    public void setNumberTransactionRequestTo(int numberTransactionRequestTo) {
        this.numberTransactionRequestTo = numberTransactionRequestTo;
    }

    public int getAmountAssets() {
        return amountAssets;
    }

    public void setAmountAssets(int amountAssets) {
        this.amountAssets = amountAssets;
    }

    @Override
    public String toString() {
        return uniqueId + ";" + numberTransactionRequestFrom +
                ";" + numberTransactionRequestTo +
                ";" + amountAssets;
    }
}
