package com.grupo3.digitalBooking.service;

import com.grupo3.digitalBooking.model.Feature;
import com.grupo3.digitalBooking.repository.FeaturesRepository;
import com.grupo3.digitalBooking.service.DAO.IFeatureService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class FeatureService implements IFeatureService {

    private final FeaturesRepository featuresRepository;

    public FeatureService(FeaturesRepository featuresRepository) {
        this.featuresRepository = featuresRepository;
    }

    @Override
    public void createFeature(Feature feature) {
        featuresRepository.save(feature);
    }

    @Override
    public Feature updateFeature(Feature feature) {
        return null;
    }

    @Override
    public Collection<Feature> readFeatures() {
        return featuresRepository.findAll();
    }

    @Override
    public Optional<Feature> readFeature(Long id) {
        Optional<Feature> feature = featuresRepository.findById(id);
        return feature;
    }

    @Override
    public void deleteFeature(Long id) {
        featuresRepository.deleteById(id);
    }
}
