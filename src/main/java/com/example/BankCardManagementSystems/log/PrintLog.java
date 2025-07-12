package com.example.BankCardManagementSystems.log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrintLog {

    public static void startLogTransaction(String method){
        log.debug("Starting {} - Created query and transaction", method);
    }

    public static void theEndLogTransaction(String method){
        log.debug("Transaction commited successfully. {}", method);
    }
}
