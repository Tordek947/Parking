package ua.hpopov.parking.services;

import java.util.List;

import ua.hpopov.parking.beans.LoginInfoBean;
import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.beans.UserProfileWithoutPassword;
import ua.hpopov.parking.beans.UserTypeBean;
import ua.hpopov.parking.datasource.dao.DAOFactories;
import ua.hpopov.parking.datasource.dao.DAOOperationException;
import ua.hpopov.parking.datasource.dao.LoginInfoDAO;
import ua.hpopov.parking.datasource.dao.Transaction;
import ua.hpopov.parking.datasource.dao.TransactionWork;
import ua.hpopov.parking.datasource.dao.UserDAO;
import ua.hpopov.parking.presentation.paginationwrappers.PaginationBeanSet;

public class PaginationService extends AbstractService {

	private static PaginationService instance=null;
	public PaginationService() {}
	
	public static PaginationService getInstance() {
		if (instance == null) {
			instance = new PaginationService();
		}
		return instance;
	}
	
	public WalkPaginationResult getNewUsersSimplePage(Integer fromIndex, Boolean forward, Integer pageSize) {
		WalkPaginationResult result = new WalkPaginationResult();
		result.setPaginationResult(PaginationResult.ERROR);
		if (pageSize == null || pageSize <= 0) {
			return result;
		}
		if (fromIndex == null || fromIndex < 0) {
			return result;
		}
		if (forward == null) {
			return result;
		}
		UserDAO userDAO = DAOFactories.getFactory().createUserDAO();
		LoginInfoDAO loginInfoDAO = DAOFactories.getFactory().createLoginInfoDAO();
		Boolean isForwardDirection[] = new Boolean[1];
		isForwardDirection[0] = forward;
		UserProfileWithoutPassword header = makeNewUsersHeader();
		TransactionWork transactionWork = ()-> {
			List<UserProfileWithoutPassword> pageContent;
			pageContent = userDAO.getUnconfirmedUsersPage(fromIndex, isForwardDirection[0], pageSize);
			LoginInfoBean firstUser = loginInfoDAO.getEdgeUnconfirmedLoginInfo(true);
			if (firstUser == null) {
				result.setPaginationResult(PaginationResult.NO_RECORDS);
				return;
			}
			if (pageContent.isEmpty()) {
				result.setPaginationResult(PaginationResult.EMPTY_PAGE);
				return;
			}
			LoginInfoBean lastUser = loginInfoDAO.getEdgeUnconfirmedLoginInfo(false);
			result.setPaginationResult(PaginationResult.SUCCESS);
			result.setPaginationBeanSet(PaginationBeanSet.makeSet(pageContent, header));
			if (pageContent.get(0).getUserBean().getUserId() < firstUser.getUserId()) {
				result.setHasPrevPage(true);
			} else {
				result.setHasPrevPage(false);
			}
			if (pageContent.get(pageContent.size()-1).getUserBean().getUserId() > lastUser.getUserId()) {
				result.setHasNextPage(true);
			} else {
				result.setHasNextPage(false);
			}
		};
		Transaction transaction = DAOFactories.getFactory().createTransaction(transactionWork);
		try {
			transaction.execute();
			if (result.getPaginationResult() != PaginationResult.EMPTY_PAGE) {
				return result;
			}
			isForwardDirection[0] = ! isForwardDirection[0];
			transaction.execute();
		} catch (DAOOperationException e) {
			handleException(e);
			result.setPaginationResult(PaginationResult.ERROR);
			return result;
		}
		if (result.getPaginationResult() == PaginationResult.EMPTY_PAGE) {
			result.setPaginationResult(PaginationResult.NO_RECORDS);
		}
		return result;
	}

	private UserProfileWithoutPassword makeNewUsersHeader() {
		UserProfileWithoutPassword header = new UserProfileWithoutPassword();
		header.setEmail("Email");
		header.setLogin("Login");
		UserBean headerUserBean = new UserBean();
		headerUserBean.setName("Name");
		headerUserBean.setSurname("Surname");
		header.setUserBean(headerUserBean);
		UserTypeBean userTypeHeader = new UserTypeBean();
		userTypeHeader.setUserTypeValue("User type");
		header.setUserTypeBean(userTypeHeader);
		return header;
	}
}
