package tc.lv.service.implementation;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tc.lv.dao.IpAddressDao;
import tc.lv.dao.SourceDao;
import tc.lv.domain.IpAddress;
import tc.lv.domain.IpV4Address;
import tc.lv.exceptions.IpStatusListServiceException;
import tc.lv.service.IpStatusListService;
import tc.lv.utils.IpVersionUtil;

@Service
public class IpStatusListServiceImpl implements IpStatusListService {

	private static final Logger LOGGER = Logger
			.getLogger(IpStatusListServiceImpl.class);
	private static final String ADMIN_WHITE_LIST = "Admin WhiteList";
	private static final String ADMIN_BLACK_LIST = "Admin BlackList";

	@Autowired
	private IpAddressDao ipAddressDao;

	@Autowired
	private SourceDao sourceDao;

	@Transactional
	@Override
	public Collection<IpAddress> findIpList(int from, int count, int ipType,
			int status) throws IpStatusListServiceException {
		try {
				return ipAddressDao.findStatusList(IpVersionUtil.makeStatus(status),from, count,
						null, IpVersionUtil.makeIpType(ipType));
		} catch (Exception e) {
			LOGGER.error(e);
			throw new IpStatusListServiceException(
					"Could not load IP List by range", e);
		}
	}

}
