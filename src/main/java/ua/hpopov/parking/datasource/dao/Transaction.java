package ua.hpopov.parking.datasource.dao;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class Transaction {

	private static Logger log = LoggerFactory.getLogger(Transaction.class);
	protected TransactionWork transactionWork;
	protected List<DAO> transactionMemberList;
	protected TransactionConnector transactionConnector;
	
	/**
	 * @param transactionWork Must not use instances of DAO interface as a local field
	 * in execute() method
	 */
	public Transaction(TransactionWork transactionWork) {
		this.transactionWork = transactionWork;
		defineDAOMembers();
	}

	protected void defineDAOMembers() {
		Field[] fields = transactionWork.getClass().getDeclaredFields();
		transactionMemberList = new LinkedList<>();
		try {
			for(Field f : fields) {
				f.setAccessible(true);
				Object field = f.get(transactionWork);
				if (DAO.class.isInstance(field)) {
					transactionMemberList.add((DAO)field);
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			log.error("Error on defining transaction DAO members", e);
		}
	}
	
	public abstract void execute() throws DAOOperationException;
	
}
