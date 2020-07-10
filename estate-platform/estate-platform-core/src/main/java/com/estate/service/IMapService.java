package com.estate.service;

import com.estate.dto.MapModel;

public interface IMapService {
    MapModel getLatLngFromAddress(String location);
}
