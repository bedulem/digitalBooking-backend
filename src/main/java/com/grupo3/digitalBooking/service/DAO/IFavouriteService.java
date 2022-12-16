package com.grupo3.digitalBooking.service.DAO;

import com.grupo3.digitalBooking.model.Favourite;

import java.util.Collection;
import java.util.Optional;

public interface IFavouriteService {
    void createFavourite(Favourite feature);
    Favourite updateFavourite (Favourite feature);
    Collection<Favourite> readFavourites ();
    Optional<Favourite> readFavourite (Long id);
    void deleteFavourite(Long id);
}
