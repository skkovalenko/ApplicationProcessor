package csv;

import model.ClientBalance;
import model.Trade;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ToCSV {
    private static final String BALANCE_CLIENT = "Клиент";
    private static final String BALANCE_ASSETS = "Актив";
    private static final String BALANCE_AMOUNT_ASSETS = "Количество активов";

    private static final String TRADE_UNIQUE_NUMBER = "Номер сделки";
    private static final String TRADE_NUMBER_TRANSACTION_REQUEST_FROM = "Номер заявки продажи";
    private static final String TRADE_NUMBER_TRANSACTION_REQUEST_TO = "Номер заявки покупки";
    private static final String TRADE_AMOUNT_ASSETS = "Количество активов";

    private Path pathCSVClients;
    private Path pathTransactionReport;
    private String defSeparator = ";";

    public ToCSV(Path pathCSVClients, Path pathTransactionReport) {
        this.pathCSVClients = pathCSVClients;
        this.pathTransactionReport = pathTransactionReport;
    }


    public void setData(ArrayList<ClientBalance> clientBalances, ArrayList<Trade> trades){
        String[] clientBalanceLines = new String[clientBalances.size() + 1];
        String[] tradeLines = new String[trades.size() + 1];
        clientBalanceLines[0] = BALANCE_CLIENT + defSeparator +
                BALANCE_ASSETS + defSeparator +
                                BALANCE_AMOUNT_ASSETS;

        tradeLines[0] = TRADE_UNIQUE_NUMBER + defSeparator +
                        TRADE_NUMBER_TRANSACTION_REQUEST_FROM + defSeparator +
                        TRADE_NUMBER_TRANSACTION_REQUEST_TO + defSeparator +
                        TRADE_AMOUNT_ASSETS;

        for(int i = 1; i < clientBalanceLines.length; i++){
            ClientBalance clientBalance = clientBalances.get(i - 1);
            clientBalanceLines[i] = clientBalance.toString();
        }

        for(int i = 1; i < tradeLines.length; i++){
            Trade trade = trades.get(i - 1);
            tradeLines[i] = trade.toString();
        }
        createFiles();
        try {
            Files.write(pathCSVClients, Arrays.stream(clientBalanceLines).collect(Collectors.toList()), StandardOpenOption.WRITE);
            Files.write(pathTransactionReport, Arrays.stream(tradeLines).collect(Collectors.toList()), StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createFiles(){
        try {
            if(Files.notExists(pathTransactionReport) && Files.notExists(pathCSVClients)){
                Files.createDirectories(pathCSVClients.getParent());
                Files.createFile(pathCSVClients);
                Files.createFile(pathTransactionReport);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
