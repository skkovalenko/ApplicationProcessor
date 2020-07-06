package service;

import model.ClientBalance;
import model.Trade;
import model.TransactionRequest;
import java.util.ArrayList;

public class ApplicationProcessor {

    private ArrayList<ClientBalance> clientBalances;
    private ArrayList<TransactionRequest> transactionRequests;
    private ArrayList<Trade> trades = new ArrayList<>();

    public ApplicationProcessor(ArrayList<ClientBalance> clientBalances,
                                ArrayList<TransactionRequest> transactionRequests) {
        this.clientBalances = clientBalances;
        this.transactionRequests = transactionRequests;
    }

    public void startApplicationProcessor(){
        ArrayList<TransactionRequest> purchaseList = new ArrayList<>();
        ArrayList<TransactionRequest> saleList = new ArrayList<>();
        for(TransactionRequest transactionRequest : transactionRequests){
            //флаг isPurchaseSale: true -> Покупка; false -> Продажа;
            if(transactionRequest.isPurchaseSale()){
                purchaseList.add(transactionRequest);
            }else saleList.add(transactionRequest);
        }
        assetSwap(purchaseList, saleList);
    }
    private void assetSwap(ArrayList<TransactionRequest> purchaseList, ArrayList<TransactionRequest> saleList){
        
        for(TransactionRequest purchase : purchaseList){
            int saleAmountAssetsForThisPurchase = 0;
            for(TransactionRequest sale : saleList){
                if(purchase.getAssets().equals(sale.getAssets())){
                    saleAmountAssetsForThisPurchase += sale.getAmountAssets();
                }
            }
            if(purchase.getAmountAssets() <= saleAmountAssetsForThisPurchase){
                applicationProcessing(purchase, saleList);
            }
        }
    }
    private void applicationProcessing(TransactionRequest purchase, ArrayList<TransactionRequest> saleList){
        for (TransactionRequest sale : saleList){
            if(purchase.getAssets().equals(sale.getAssets())){
                if(purchase.getAmountAssets() == 0){
                    return;
                }
                if(purchase.getAmountAssets() > sale.getAmountAssets() && sale.getAmountAssets() > 0){
                    Trade trade = new Trade();
                    trade.setUniqueId(purchase.getUniqueNumber() + "" + sale.getUniqueNumber());
                    trade.setNumberTransactionRequestFrom(sale.getUniqueNumber());
                    trade.setNumberTransactionRequestTo(purchase.getUniqueNumber());
                    trade.setAmountAssets(sale.getAmountAssets());
                    trades.add(trade);
                    
                    purchase.setAmountAssets(purchase.getAmountAssets() - sale.getAmountAssets());
                    
                    ClientBalance clientBalanceSale = getClient(sale.getClientId());
                    clientBalanceSale.setAmountAssets(clientBalanceSale.getAmountAssets() - sale.getAmountAssets());
                    ClientBalance clientBalancePurchase = getClient(purchase.getClientId());
                    clientBalancePurchase.setAmountAssets(clientBalancePurchase.getAmountAssets() + sale.getAmountAssets());
                    sale.setAmountAssets(0);
                    continue;
                }
                
                if(purchase.getAmountAssets() <= sale.getAmountAssets()){
                    Trade trade = new Trade();
                    trade.setUniqueId(purchase.getUniqueNumber() + "" + sale.getUniqueNumber());
                    trade.setNumberTransactionRequestFrom(sale.getUniqueNumber());
                    trade.setNumberTransactionRequestTo(purchase.getUniqueNumber());
                    trade.setAmountAssets(purchase.getAmountAssets());
                    trades.add(trade);
                    ClientBalance clientBalancePurchase = getClient(purchase.getClientId());
                    clientBalancePurchase.setAmountAssets(clientBalancePurchase.getAmountAssets() + purchase.getAmountAssets());
                    ClientBalance clientBalanceSale = getClient(sale.getClientId());
                    clientBalanceSale.setAmountAssets(clientBalanceSale.getAmountAssets() - purchase.getAmountAssets());
                    
                    sale.setAmountAssets(sale.getAmountAssets() - purchase.getAmountAssets());
                    purchase.setAmountAssets(0);
                }
            }
        }
    }

    private ClientBalance getClient(int clientId){
        for(ClientBalance clientBalance : clientBalances){
            if(clientBalance.getClientId() == clientId){
                return clientBalance;
            }
        }
        return null;
    }

    public ArrayList<ClientBalance> getClientBalances() {
        return clientBalances;
    }
    public ArrayList<Trade> getTrades() {
        return trades;
    }

}
