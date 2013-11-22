package tc.lv.service.implementation;

import java.io.FileWriter;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tc.lv.dao.IpAddressDao;
import tc.lv.dao.implementations.IpQueryEnum;
import tc.lv.domain.IpAddress;
import tc.lv.exceptions.JsonServiceException;
import tc.lv.service.JsonService;

@Service
public class JsonServiceImpl implements JsonService {
    private static final Logger LOGGER = Logger.getLogger(JsonServiceImpl.class);

    @Autowired
    IpAddressDao ipAddressDao;

    @Override
    //@Transactional
    public void createJsonForCountryMap(String path, String fileName, Class<? extends IpAddress> ipType,
            boolean status) throws JsonServiceException {
        JSONObject json = new JSONObject();
        FileWriter file;
        try {
            LOGGER.info("Start creating JSON-file for " + (status == true ? "White" : "Black") + "Map.");

            for (String country : ipAddressDao.findCountryListByStatus(ipType, status)) {
                json.put(country, ipAddressDao.countStatusIpByCountryName(country, ipType, status));
            }
            file = new FileWriter(path + fileName);
            file.write("var array =");
            file.write(json.toString());
            file.close();
            LOGGER.info("Finish creating JSON-file for " + (status == true ? "White" : "Black") + "Map.");

        } catch (Exception e) {
            LOGGER.error("Could not create JSON file", e);
            throw new JsonServiceException("Could not create JSON file", e);

        }
    }

    @Override
    @Transactional
    @Deprecated
    public void createJsonCountryWhiteList(String path) throws JsonServiceException {
        JSONObject json = new JSONObject();
        FileWriter file;
        try {
            LOGGER.info("Start creating JSON-file for WhiteList Map.");

            for (String country : ipAddressDao.findCountriesWhiteList(IpQueryEnum.IP)) {
                json.put(country, ipAddressDao.findWhiteListByCountyName(country, IpQueryEnum.IP).size());
            }

            file = new FileWriter(path + "countryJsonWhiteList.js");
            file.write("var array =");
            file.write(json.toString());
            file.close();

            LOGGER.info("Finish creating JSON-file for WhiteList Map.");

        } catch (Exception e) {
            LOGGER.error("Could not create JSON file", e);
            throw new JsonServiceException("Could not create JSON file", e);

        }
    }

    @Override
    @Transactional
    @Deprecated
    public void createJsonCountryBlackList(String path) throws JsonServiceException {
        JSONObject json = new JSONObject();
        FileWriter file;
        try {
            LOGGER.info("Start creating JSON-file for BlackList Map.");

            for (String country : ipAddressDao.findCountriesBlackList(IpQueryEnum.IP)) {
                json.put(country, ipAddressDao.findBlackListByCountryName(country, IpQueryEnum.IP).size());
            }

            file = new FileWriter(path + "countryJsonBlackList.js");
            file.write("var array =");
            file.write(json.toString());
            file.close();

            LOGGER.info("Finish creating JSON-file for BlackList Map.");

        } catch (Exception e) {
            LOGGER.error("Could not create JSON file", e);
            throw new JsonServiceException("Could not create JSON file", e);
        }
    }
}
