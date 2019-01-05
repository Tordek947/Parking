package ua.hpopov.parking.datasource.dao.sql;

import ua.hpopov.parking.datasource.dao.DAO;
import ua.hpopov.parking.datasource.dao.DAOOperationException;
import ua.hpopov.parking.datasource.dao.Transaction;
import ua.hpopov.parking.datasource.dao.TransactionConnector;
import ua.hpopov.parking.datasource.dao.TransactionWork;

public abstract class SqlTransaction extends Transaction{

	public SqlTransaction(TransactionWork transactionWork) {
		super(transactionWork);
	}

	@Override
	public void execute() throws DAOOperationException {
		boolean exceptionOccurs = false;
		transactionConnector = makeTransactionConnector();
		for(DAO member : transactionMemberList) {
			member.setConnector(transactionConnector);
		}
		try {
			transactionWork.execute();
		} catch (DAOOperationException e) {
			exceptionOccurs = true;
		} finally {
			if (exceptionOccurs) {
				transactionConnector.rollBack();
			} else {
				transactionConnector.commit();
			}
			transactionConnector.free();
			for(DAO member : transactionMemberList) {
				member.setConnector(null);
			}
			if (exceptionOccurs) {
				throw new DAOOperationException();
			}
		}
	}

	protected abstract TransactionConnector makeTransactionConnector();

}
