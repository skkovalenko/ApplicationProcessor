import csv.FromCSV;
import csv.ToCSV;
import service.ApplicationProcessor;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

public class Main {

    private static final String PATH_CSV_CLIENTS = "D:/Java/Tests/Alfa/balance_clients.csv";
    private static final String PATH_TRANSACTION_REQUEST = "D:/Java/Tests/Alfa/transaction_request.csv";

    private static final String PATH_NEW_SCV_CLIENTS = "data/new_client_balances.csv";
    private static final String PATH_TRANSACTION_REPORT = "data/new_transaction_report.csv";

    public static void main(String[] args) {
        FromCSV csvService = new FromCSV(
                Path.of(PATH_CSV_CLIENTS),
                Path.of(PATH_TRANSACTION_REQUEST));
        ApplicationProcessor applicationProcessor = new ApplicationProcessor(
                csvService.getClientBalances(),
                csvService.getTransactionRequests());
        applicationProcessor.startApplicationProcessor();
        ToCSV toCSV = new ToCSV(
                Path.of(PATH_NEW_SCV_CLIENTS),
                Path.of(PATH_TRANSACTION_REPORT));
        toCSV.setData(applicationProcessor.getClientBalances(), applicationProcessor.getTrades());
    }
}
