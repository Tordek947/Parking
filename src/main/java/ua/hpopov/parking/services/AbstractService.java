package ua.hpopov.parking.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.hpopov.parking.datasource.dao.DAOOperationException;

public class AbstractService {

	private static final Logger log = LoggerFactory.getLogger(AbstractService.class);
	
	protected void handleException(DAOOperationException e) {
		log.error("",e);
	}
}
