package com.zooplus.crypto.service;

import com.zooplus.crypto.client.LocationClient;
import com.zooplus.crypto.model.IPLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    private final LocationClient locationClient;

    @Autowired
    public LocationService(LocationClient locationClient) {
        this.locationClient = locationClient;
    }

    public IPLocation fetchLocation(String ipAddress) {
        return locationClient.fetchCurrencyByIP(ipAddress);
    }

}
