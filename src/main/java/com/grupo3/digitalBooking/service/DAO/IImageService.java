package com.grupo3.digitalBooking.service.DAO;

import com.grupo3.digitalBooking.model.Image;

import java.util.Collection;
import java.util.Optional;

public interface IImageService {
    void createImage(Image image);
    Image updateImage (Image image);
    Collection<Image> readImages ();
    Optional<Image> readImage (Long id);
    void deleteImage(Long id);
}
