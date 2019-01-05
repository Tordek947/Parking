package ua.hpopov.parking.datasource.dao.sql.mysql;

import ua.hpopov.parking.datasource.dao.TransactionConnector;
import ua.hpopov.parking.datasource.dao.TransactionWork;
import ua.hpopov.parking.datasource.dao.sql.SqlTransaction;

public class MySqlTransaction extends SqlTransaction {

	public MySqlTransaction(TransactionWork transactionWork) {
		super(transactionWork);
	}

	@Override
	protected TransactionConnector makeTransactionConnector() {
		return MySqlConnectorProvider.getInstance().makeTransactionConnector();
	}

}
