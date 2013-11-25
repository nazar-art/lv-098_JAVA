package tc.lv.web;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tc.lv.domain.City;
import tc.lv.domain.Country;
import tc.lv.domain.IpAddress;
import tc.lv.domain.PaginationSettings;
import tc.lv.exceptions.LocationServiceException;
import tc.lv.service.LocationService;
import tc.lv.service.implementation.JsonServiceImpl;
import tc.lv.service.implementation.PaginationServiceImpl;
import tc.lv.utils.ExceptionUtil;
import tc.lv.utils.IpVersionUtil;

@Controller
public class LocationController {

    private static final Logger LOGGER = Logger.getLogger(LocationController.class);
    @Autowired
    LocationService locationService;

    @Autowired
    PaginationServiceImpl paginationServiceImpl;

    // showStatusListByCity
    @RequestMapping(value = "secure_showIpListByCity", method = RequestMethod.GET)
    public String showStatusListByCity() {
        return "secure_showIpListByCity";
    }

    // showStatusListByCity
    @RequestMapping(value = "secure_showIpListByCity", method = RequestMethod.POST)
    public String showStatusListByCity(@ModelAttribute("pageNumber") int pageNumber,
            @ModelAttribute("countIpPerPage") int countIpPerPage, @ModelAttribute("location") String cityName,
            @ModelAttribute("ipType") String ipTypeUI, @ModelAttribute("status") String statusUI,
            Map<String, Object> map) {
        int from;
        int ipCount;
        int pageCount;
        List<IpAddress> ipList;
        List<PaginationSettings> pageList;
        List<City> locationList;
        try {
            cityName = "Taipei";
            System.out.println("SSSSSS");
            ipCount = locationService.countStatusIpByCityName(cityName, IpVersionUtil.ipVersion(ipTypeUI),
                    IpVersionUtil.isWhiteIpAddress(statusUI)).intValue();
            LOGGER.info("ipCount -" + ipCount );
            
            
            pageCount = ipCount / countIpPerPage + 1;
            from = (pageNumber - 1) * countIpPerPage;
            ipList = locationService.findStatusListByCity(from, countIpPerPage, cityName,
                    IpVersionUtil.ipVersion(ipTypeUI), IpVersionUtil.isWhiteIpAddress(statusUI));
            LOGGER.info("ipList -" + ipList.toString());
            
            
            locationList = locationService.findCityListByStatus(IpVersionUtil.ipVersion(ipTypeUI),
                    IpVersionUtil.isWhiteIpAddress(statusUI));
            LOGGER.info("locationList -" + locationList.toString() );
            
            
            pageList = paginationServiceImpl.loadPages();
            LOGGER.info("pageList -" + pageList.toString() );
            
            map.put("pageList", pageList); //
            map.put("pageCount", pageCount); // Count of pages
            map.put("ipList", ipList); // List of IP-addresses
            map.put("locationList", locationList); // List of cities

        } catch (LocationServiceException e) {
            map.put("errorList", ExceptionUtil.createErrorList(e));
            map.put("errorMsg", e.getMessage());
            return "result";
        }
        return "secure_table";
    }

    // showStatusListByCountry
    @RequestMapping(value = "secure_showIpListByCountry", method = RequestMethod.GET)
    public String showStatusListByCountry() {
        return "secure_showIpListByCountry";
    }

    // showStatusListByCountry
    @RequestMapping(value = "secure_showIpListByCountry", method = RequestMethod.POST)
    public String showStatusListByCountry(@ModelAttribute("pageNumber") int pageNumber,
            @ModelAttribute("countIpPerPage") int countIpPerPage, @ModelAttribute("location") String countryName,
            @ModelAttribute("ipType") String ipTypeUI, @ModelAttribute("status") String statusUI,
            Map<String, Object> map) {
        int from;
        int ipCount;
        int pageCount;
        List<IpAddress> ipList;
        List<PaginationSettings> pageList;
        List<Country> locationList;
        try {

            ipCount = locationService.countStatusIpByCountryName(countryName, IpVersionUtil.ipVersion(ipTypeUI),
                    IpVersionUtil.isWhiteIpAddress(statusUI)).intValue();
            pageCount = ipCount / countIpPerPage + 1;

            from = (pageNumber - 1) * countIpPerPage;
            ipList = locationService.findStatusListByCountry(from, countIpPerPage, countryName,
                    IpVersionUtil.ipVersion(ipTypeUI), IpVersionUtil.isWhiteIpAddress(statusUI));

            locationList = locationService.findCountryListByStatus(IpVersionUtil.ipVersion(ipTypeUI),
                    IpVersionUtil.isWhiteIpAddress(statusUI));
            pageList = paginationServiceImpl.loadPages();

            map.put("pageList", pageList); //
            map.put("pageCount", pageCount); // Count of pages
            map.put("ipList", ipList); // List of IP-addresses
            map.put("locationList", locationList); // List of cities

        } catch (LocationServiceException e) {
            map.put("errorList", ExceptionUtil.createErrorList(e));
            map.put("errorMsg", e.getMessage());
            return "result";
        }
        return "secure_table";
    }

}
