package com.grupo3.digitalBooking.service;

import com.grupo3.digitalBooking.model.Image;
import com.grupo3.digitalBooking.repository.ImageRepository;
import com.grupo3.digitalBooking.service.DAO.IImageService;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;

@Service
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void createImage(Image image) {
        imageRepository.save(image);
    }

    @Override
    public Image updateImage(Image image) {
        return null;
    }

    @Override
    public Collection<Image> readImages() {
        return imageRepository.findAll();
    }

    @Override
    public Optional<Image> readImage(Long id) {

        Optional<Image> image = imageRepository.findById(id);
        return image;
    }

    @Override
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    public void deleteImageByProductId(Long id) {
        imageRepository.removeByProductId(id);
    }
}
