package com.grupo3.digitalBooking.service.DAO;

import com.grupo3.digitalBooking.model.Feature;

import java.util.Collection;
import java.util.Optional;

public interface IFeatureService {
    void createFeature(Feature feature);
    Feature updateFeature (Feature feature);
    Collection<Feature> readFeatures ();
    Optional<Feature> readFeature (Long id);
    void deleteFeature(Long id);
}
