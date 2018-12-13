package com.munchies.storage;

import com.munchies.model.Restaurant;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public interface StorageService {

    void init() throws StorageException;

    String storeFileForNewRestaurant(MultipartFile file) throws StorageException;

    String store(MultipartFile file, Long id) throws StorageException;

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename) throws StorageFileNotFoundException;

    void deleteAll();

    void deleteFilesByRestaurantId(Long id);
}
