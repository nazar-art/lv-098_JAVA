package tc.lv.service;

import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import tc.lv.dao.IpV4AddressDao;
import tc.lv.dao.IpV6AddressDao;
import tc.lv.dao.SourceDao;
import tc.lv.domain.IpV4Address;
import tc.lv.domain.IpV6Address;
import tc.lv.exceptions.BlackListServiceException;

public class BlackListServiceImpl implements BlackListService {
    private static final Logger LOGGER = Logger
	    .getLogger(BlackListServiceImpl.class);

    @Autowired
    private IpV4AddressDao ipV4AddressDao;

    @Autowired
    private IpV6AddressDao ipV6AddressDao;

    @Autowired
    private SourceDao sourceDao;

    @Override
    public boolean deleteIpV4(String address) throws BlackListServiceException {

	try {
	    IpV4Address tempObject = ipV4AddressDao.findByAddress(address);

	    if (tempObject != null) {
		ipV4AddressDao.removeFromBlackList(tempObject);
		return true;

	    } else {
		return false;
	    }

	} catch (Exception e) {
	    LOGGER.error(e);
	    throw new BlackListServiceException("Entity manager Exception", e);
	}
    }

    @Override
    public boolean deleteIpV6(String address) throws BlackListServiceException {

	try {
	    IpV6Address tempObject = ipV6AddressDao.findByAddress(address);

	    if (tempObject != null) {
		ipV6AddressDao.removeFromBlackList(tempObject);
		return true;

	    } else {
		return false;
	    }
	} catch (Exception e) {
	    LOGGER.error(e);
	    throw new BlackListServiceException(
		    "Current IP-address don't exist in BlackList", e);
	}
    }

    @Override
    public boolean saveIpV4(String address) throws BlackListServiceException {
        
	try {
	    IpV4Address tempIpV4 = ipV4AddressDao.findByAddress(address);

	    if (tempIpV4 == null) {
		tempIpV4 = new IpV4Address(address, new Date());
		tempIpV4.getSourceSet().add(
			sourceDao.findByName("Admin BlackList"));
		tempIpV4.setWhiteList(false);
		ipV4AddressDao.save(tempIpV4);
		return true;

	    } else if (tempIpV4.getWhiteList() != true) {
		tempIpV4.setWhiteList(false);
		ipV4AddressDao.save(tempIpV4);
		return true;

	    } else {
		return false;
	    }

	} catch (Exception e) {
	    LOGGER.error(e);
	    throw new BlackListServiceException("Entity manager Exception", e);
	}

    }

    @Override
    public boolean saveIpV6(String address) throws BlackListServiceException {

	try {
	    IpV6Address tempIpV6 = ipV6AddressDao.findByAddress(address);

	    if (tempIpV6 == null) {
		tempIpV6 = new IpV6Address(address, new Date());
		tempIpV6.getSourceSet().add(
			sourceDao.findByName("Admin BlackList"));
		tempIpV6.setWhiteList(false);
		ipV6AddressDao.save(tempIpV6);
		return true;

	    } else if (tempIpV6.getWhiteList() != true) {
		tempIpV6.setWhiteList(false);
		ipV6AddressDao.save(tempIpV6);
		return true;

	    } else {
		return false;
	    }

	} catch (Exception e) {
	    LOGGER.error(e);
	    throw new BlackListServiceException("Entity manager Exception", e);
	}
    }

    @Override
    public Collection<IpV4Address> loadIpV4List()
	    throws BlackListServiceException {

	try {
	    return ipV4AddressDao.getBlackList();

	} catch (Exception e) {
	    LOGGER.error(e);
	    throw new BlackListServiceException("Entity manager Exception", e);
	}
    }

    @Override
    public Collection<IpV6Address> loadIpV6List()
	    throws BlackListServiceException {

	try {
	    return ipV6AddressDao.getBlackList();

	} catch (Exception e) {
	    LOGGER.error(e);
	    throw new BlackListServiceException("Entity manager Exception", e);
	}
    }

    @Override
    public Collection<IpV4Address> loadIpV4ListByRange(int from, int count)
	    throws BlackListServiceException {

	try {
	    return ipV4AddressDao.getBlackList(from, count);

	} catch (Exception e) {
	    LOGGER.error(e);
	    throw new BlackListServiceException("Entity manager Exception", e);
	}
    }

    @Override
    public Collection<IpV6Address> loadIpV6ListByRange(int from, int count)
	    throws BlackListServiceException {

	try {
	    return ipV6AddressDao.getBlackList(from, count);

	} catch (Exception e) {
	    LOGGER.error(e);
	    throw new BlackListServiceException("Entity manager Exception", e);
	}
    }
}
