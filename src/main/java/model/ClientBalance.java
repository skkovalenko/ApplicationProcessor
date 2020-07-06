package model;

public class ClientBalance {
    private int clientId;
    private String assets;
    private int amountAssets;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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

    @Override
    public String toString() {
        return clientId + ";" + assets + ";" + amountAssets;
    }
}
