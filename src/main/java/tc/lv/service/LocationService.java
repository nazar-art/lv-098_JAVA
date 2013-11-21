package tc.lv.service;

import java.util.List;

import tc.lv.domain.IpAddress;
import tc.lv.exceptions.LocationServiceException;

public interface LocationService {

    public List<String> 
    
    public List<IpAddress> loadWhiteListOfCityByRange(int from, int count, String cityName)
            throws LocationServiceException;

    public List<IpAddress> loadBlackListOfCityByRange(int from, int count, String cityName)
            throws LocationServiceException;

    public List<IpAddress> loadWhiteListOfCountryByRange(int from, int count, String countryName)
            throws LocationServiceException;

    public List<IpAddress> loadBlackListOfCountryByRange(int from, int count, String countryName)
            throws LocationServiceException;
}
