package com.munchies.storage;

import com.munchies.model.Restaurant;
import com.munchies.repositories.RestaurantJpaRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {

    private Path rootLocation;

    @Autowired
    public StorageServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Autowired
    private RestaurantJpaRepository restaurantJpaRepository;

    @Override
    public String storeFile(MultipartFile file) throws StorageException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.getOriginalFilename().isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
                return FilenameUtils.removeExtension(file.getOriginalFilename());
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    @Override
    public String store(MultipartFile file, Long id) throws StorageException {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        Optional<Restaurant> restaurant = restaurantJpaRepository.findById(id);
        try {
            if (file.getOriginalFilename().isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                if (restaurant.isPresent()) {
                    Restaurant saveRest = restaurant.get();
                    if (saveRest.getMenuUrl().equals(FilenameUtils.removeExtension(file.getOriginalFilename()))) {
                        return saveRest.getMenuUrl();
                    }
                    FileSystemUtils.deleteRecursively(load(filename));
                    Files.copy(inputStream, this.rootLocation.resolve(filename),
                            StandardCopyOption.REPLACE_EXISTING);
                    saveRest.setMenuUrl(file.getOriginalFilename().replace(file.getOriginalFilename(), FilenameUtils.removeExtension(file.getOriginalFilename())));
                    return saveRest.getMenuUrl();
                }
                return FilenameUtils.removeExtension(file.getOriginalFilename());

            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }


    public StorageServiceImpl() {
    }

    @Override
    public void init() throws StorageException {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) throws StorageFileNotFoundException {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void deleteFilesByRestaurantId(Long id) {
        Path path = load(restaurantJpaRepository.getOne(id).getMenuUrl());
        File f = new File(path.toUri());
        FileSystemUtils.deleteRecursively(f);


    }
}

