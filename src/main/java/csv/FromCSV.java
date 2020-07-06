package csv;

import model.ClientBalance;
import model.TransactionRequest;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FromCSV {

    private static final String purchase = "ПОКУПКА";
    private static final String sale = "ПРОДАЖА";

    private static final int CL_CLIENT_ID = 0;
    private static final int CL_ASSETS = 1;
    private static final int CL_AMOUNT_ASSETS = 2;

    private static final int TR_UNIQUE_NUMBER = 0;
    private static final int TR_CLIENT_ID = 1;
    private static final int TR_PURCHASE_SALE = 2;
    private static final int TR_ASSETS = 3;
    private static final int TR_AMOUNT_ASSETS = 4;



    private Path pathCSVClients;
    private Path pathCSVTransactionRequest;
    private String defSeparator = ";";

    public FromCSV(Path pathCSVClients, Path pathCSVTransactionRequest) {
        this.pathCSVClients = pathCSVClients;
        this.pathCSVTransactionRequest = pathCSVTransactionRequest;
    }

    public void replaceSeparator(String separator){
        defSeparator = separator;
    }

    private List<String> getLines(Path pathCSV){
        List<String> lines = null;
        Charset charset = Charset.defaultCharset();
        while (lines == null){
            try {
                lines = Files.readAllLines(pathCSV, charset);
            } catch (MalformedInputException e){
                charset = Charset.forName("windows-1251");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }

    public ArrayList<ClientBalance> getClientBalances(){
        return getLines(pathCSVClients).stream()
                .skip(1)
                .map(s -> s.split(defSeparator))
                .map(strings -> {
                    ClientBalance clientBalance = new ClientBalance();
                    clientBalance.setClientId(Integer.parseInt(strings[CL_CLIENT_ID]));
                    clientBalance.setAssets(strings[CL_ASSETS]);
                    clientBalance.setAmountAssets(Integer.parseInt(strings[CL_AMOUNT_ASSETS]));
                    return clientBalance;
                }).collect(Collectors.toCollection(ArrayList::new));
    }
    public ArrayList<TransactionRequest> getTransactionRequests(){
        return getLines(pathCSVTransactionRequest).stream()
                .skip(1)
                .map(s -> s.split(defSeparator))
                .map(strings -> {
                    TransactionRequest transactionRequest = new TransactionRequest();
                    transactionRequest.setUniqueNumber(Integer.parseInt(strings[TR_UNIQUE_NUMBER]));
                    transactionRequest.setClientId(Integer.parseInt(strings[TR_CLIENT_ID]));
                    transactionRequest.setPurchaseSale(getPurchaseSale(strings[TR_PURCHASE_SALE]));
                    transactionRequest.setAssets(strings[TR_ASSETS]);
                    transactionRequest.setAmountAssets(Integer.parseInt(strings[TR_AMOUNT_ASSETS]));
                    return transactionRequest;
                }).collect(Collectors.toCollection(ArrayList::new));
    }
    private boolean getPurchaseSale(String purchaseSale){
        return purchaseSale.toUpperCase().equals(purchase);
    }
}
