package com.grupo3.digitalBooking.service;

import com.grupo3.digitalBooking.model.Favourite;
import com.grupo3.digitalBooking.model.Product;
import com.grupo3.digitalBooking.repository.FavouriteRepository;
import com.grupo3.digitalBooking.service.DAO.IFavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Service
public class FavouriteService implements IFavouriteService {

    private final FavouriteRepository favouriteRepository;

    @Autowired
    public FavouriteService(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    @Override
    public void createFavourite(Favourite feature) {
        favouriteRepository.save(feature);
    }

    @Override
    public Favourite updateFavourite(Favourite feature) {
        return null;
    }

    @Override
    public Collection<Favourite> readFavourites() {
        return null;
    }

    @Override
    public Optional<Favourite> readFavourite(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteFavourite(Long id) {
        favouriteRepository.deleteById(id);
    }

    public Collection<Product> findByUserId(Long id) {
        Collection<Favourite> favourites = favouriteRepository.findByUserId(id);
        Collection<Product> products = new HashSet<>();
        for (Favourite favorite: favourites) {
            products.add(favorite.getProduct());
        }
        return products;

    }

    public Optional<Favourite> findByProductId(Long userId, Long productId) {
        Favourite favourite = favouriteRepository.findByProductId(userId, productId);
        return Optional.ofNullable(favourite);

    }
}
