package tc.lv.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tc.lv.dao.IpV4AddressDao;
import tc.lv.dao.IpV6AddressDao;
import tc.lv.dao.SourceDao;
import tc.lv.domain.Source;
import tc.lv.exceptions.SourceServiseException;

@Service
public class SourceServiceImpl implements SourceService {

    private static final Logger logger = Logger
	    .getLogger(SourceServiceImpl.class);

    @Autowired
    private SourceDao sourceDao;

    @Autowired
    private IpV4AddressDao ipV4AddressDao;

    @Autowired
    private IpV6AddressDao ipV6AddressDao;

    @Transactional
    @Override
    public void addNewFeed(String parser, String sourceName, String url,
	    String listType, Double rank) throws SourceServiseException {
	try {

	    if (sourceDao.findByName(sourceName) == null) {
		Source tempSource = new Source(parser, sourceName, url,
			listType, rank);
		sourceDao.save(tempSource);
	    } else {
		throw new SourceServiseException("Current feed already exist");
	    }
	} catch (Exception e) {
	    logger.error(e);
	    throw new SourceServiseException("Entity manager Exception", e);
	}
    }

    @Transactional
    @Override
    public List<Source> getListOfSourcess() throws SourceServiseException {
	try {
	    return sourceDao.getAll();
	} catch (Exception e) {
	    logger.error(e);
	    throw new SourceServiseException("Entity manager Exception", e);
	}
    }

    @Transactional
    @Override
    public void deleteFeed(String sourceName) throws SourceServiseException {
	try {
	    if (sourceDao.findByName(sourceName) != null) {
		Source source = sourceDao.findByName(sourceName);
		sourceDao.delete(source);
	    } else {
		throw new SourceServiseException("Current feed don't exist");
	    }
	} catch (Exception e) {
	    logger.error(e);
	    throw new SourceServiseException("Entity manager Exception", e);
	}
    }
}
