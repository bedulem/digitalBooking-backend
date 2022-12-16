package com.grupo3.digitalBooking.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo3.digitalBooking.model.*;
import com.grupo3.digitalBooking.repository.ProductRepository;
import com.grupo3.digitalBooking.service.DAO.IProductService;
import com.grupo3.digitalBooking.util.BookingsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final BookingService bookingService;
    private final  RuleService ruleService;
    private final ImageService imageService;
    private final HealthSecurityService healthSecurityService;
    private final PolicyService policyService;
    private  final CategoryService categoryService;
    private final CityService cityService;
    private final FeatureService featureService;

    @Autowired
    public ProductService(ProductRepository productRepository, BookingService bookingService, RuleService ruleService, ImageService imageService, HealthSecurityService healthSecurityService, PolicyService policyService, CategoryService categoryService, CityService cityService, FeatureService featureService) {

        this.productRepository = productRepository;
        this.bookingService = bookingService;
        this.ruleService = ruleService;
        this.imageService = imageService;
        this.healthSecurityService = healthSecurityService;
        this.policyService = policyService;
        this.categoryService = categoryService;
        this.cityService = cityService;
        this.featureService = featureService;

    }

    @Autowired
    BookingsMapper bookingsMapper;

    @Autowired
    ObjectMapper mapper;

    @Override
    public void createProduct(ProductDto productDto) {
        Product product = mapper.convertValue(productDto,Product.class);
        Product product1 = productRepository.save(product);

        //  CREATE RULES  //
        if(productDto.getRules().size() > 0){
            for (Object ruleAux: productDto.getRules()) {
                Rule rule = mapper.convertValue(ruleAux,Rule.class);
                System.out.println(rule);
                rule.setProduct(product1);
                ruleService.createRule(rule);
            }
        }
        // CREATE IMAGES //
        if(productDto.getImages().size() > 0){
            for (Object imageAux: productDto.getImages()) {
                Image image = mapper.convertValue(imageAux,Image.class);
                image.setProduct(product1);
                imageService.createImage(image);
            }
        }

        //CREATE HEALTH-SECURITY //
        if(productDto.getHealthSecurity().size() > 0) {
            for (Object healthAux : productDto.getHealthSecurity()) {
                HealthSecurity healthSecurity = mapper.convertValue(healthAux, HealthSecurity.class);
                healthSecurity.setProduct(product1);
                healthSecurityService.createHealthSecurity(healthSecurity);
            }
        }

        //CREATE POLICIES //
        if(productDto.getPolicies().size() > 0) {
            for (Object policyAux : productDto.getPolicies()) {
                Policy policy = mapper.convertValue(policyAux, Policy.class);
                policy.setProduct(product1);
                policyService.createPolicy(policy);
            }
        }
    }

    @Override
    public Product updateProduct(Product product, ProductDto productDto) {
        if(productDto.getTitle() != null) {
            product.setTitle(productDto.getTitle());
        }
        if(productDto.getDescription() != null) {
            product.setDescription(productDto.getDescription());
        }
        if(productDto.getStars() > 0) {
            product.setStars(productDto.getStars());
        }
        if(productDto.getAddress() != null) {
            product.setDescription(productDto.getDescription());
        }
        if(productDto.getLongitude() != null) {
            product.setLongitude(productDto.getLongitude());
        }
        if(productDto.getLatitude() != null) {
            product.setLatitude(productDto.getLatitude());
        }
        if(productDto.getNearLocation() != null) {
            product.setNearLocation(productDto.getNearLocation());
        }
        if(productDto.getNearLocation() != null) {
            product.setNearLocation(productDto.getNearLocation());
        }
        if(productDto.getSlogan() != null) {
            product.setSlogan(productDto.getSlogan());
        }
        if(productDto.getCategory() != null) {
             Category category = categoryService.readCategory(productDto.getCategory().getId()).orElse(new Category());
            product.setCategory(category);
        }
        if(productDto.getCity() != null) {
            City city = cityService.readCity(productDto.getCity().getId()).orElse(new City());
            product.setCity(city);
        }

        if(productDto.getImages().size() > 0) {
            Set<Image> images = new HashSet<>();
            imageService.deleteImageByProductId(product.getId());
            for (Object imageAux: productDto.getImages()) {
                Image image = mapper.convertValue(imageAux,Image.class);
                image.setProduct(product);
                imageService.createImage(image);
                images.add(image);
            }
            product.setImages(images);
        }

        if(productDto.getRules().size() > 0) {
            Set<Rule> rules = new HashSet<>();
            ruleService.deleteRuleByProductID(product.getId());
            for (Object ruleAux: productDto.getRules()) {
                Rule rule = mapper.convertValue(ruleAux,Rule.class);
                rule.setProduct(product);
                ruleService.createRule(rule);
                rules.add(rule);
            }
            product.setRules(rules);
        }

        if(productDto.getHealthSecurity().size() > 0) {
            Set<HealthSecurity> healths = new HashSet<>();
            healthSecurityService.deleteHealthByProductID(product.getId());
            for (Object healthAux: productDto.getHealthSecurity()) {
                HealthSecurity health = mapper.convertValue(healthAux,HealthSecurity.class);
                health.setProduct(product);
                healthSecurityService.createHealthSecurity(health);
                healths.add(health);
            }
            product.setHealthSecurity(healths);
        }

        if(productDto.getPolicies().size() > 0) {
            Set<Policy> policies = new HashSet<>();
            policyService.deletePolicyByProductID(product.getId());
            for (Object policyAux: productDto.getPolicies()) {
                Policy policy = mapper.convertValue(policyAux,Policy.class);
                policy.setProduct(product);
                policyService.createPolicy(policy);
                policies.add(policy);
            }
            product.setPolicies(policies);
        }

        if(productDto.getFeatures().size() > 0) {
            Set<Feature> features = new HashSet<>();
            for (FeatureDto featureAux: productDto.getFeatures()) {
                Feature feature = featureService.readFeature(featureAux.getId()).orElse(new Feature());
                features.add(feature);
            }
            product.setFeatures(features);
        }

        productRepository.save(product);
        return product;
    }

    @Override
    public Collection<Product> readProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<ProductBookingDto> readProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            return Optional.empty();
        }
            Collection<Booking> bookings = bookingService.findByProductId(id);
            ArrayList<Map<String, LocalDate>> bookingList = bookingsMapper.checkInCheckOutMapper(bookings);
            ProductBookingDto productBookingDto = bookingsMapper.productsBookingMapper(product, bookingList);

            return Optional.ofNullable(productBookingDto);
    }

    public Optional<Product> readSimpleProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return Optional.empty();
        }
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById( id);
    }

    public Collection<Product> findByCategoryId (Long id) {
        return productRepository.findByCategoryId(id);

    }

    public Collection<Product> findByCityId (Long id) {
        return productRepository.findByCityId(id);

    }
    public Collection<Product> readProductsPaginated(Long cityId, Long categoryId,String order, int limit, int offset) {
        if(order.contains("random")) {
            if (cityId == null && categoryId == null) return productRepository.findAllPaginatedRandom( limit, offset);
            if(cityId != null && categoryId != null) return productRepository.findAllPaginatedRandomCityAndCategory( cityId, categoryId, limit, offset);
            if(cityId == null) return productRepository.findAllPaginatedRandomCategory(categoryId, limit, offset);
            if(categoryId == null) return productRepository.findAllPaginatedRandomCity(cityId, limit, offset);
        }else{
            if (cityId == null && categoryId == null) return productRepository.findAllPaginated(order, limit, offset);
            if(cityId != null && categoryId != null) return productRepository.findByCityAndCategory( cityId, categoryId, order, limit, offset);
            if(cityId == null) return productRepository.findByCategory(categoryId, order, limit, offset);
            if(categoryId == null) return productRepository.findByCity(cityId, order, limit, offset);
        }
        return null;
    }

    public  Collection<Product> filterByCityAndDates(Long citiId, LocalDate checkIn, LocalDate checkOut) {
        return productRepository.findByCityAndDate(citiId,checkIn,checkOut);
    }

    public  Collection<Product> filterByCityAndDatesAndCategory(Long citiId, LocalDate checkIn, LocalDate checkOut, Long categoryId) {
        return productRepository.findByCityAndDateAndCategory(citiId,checkIn,checkOut, categoryId);
    }
}
