package com.sanyouseki.fwzd.service;

import com.sanyouseki.fwzd.entity.Image;

import java.util.List;

public interface IImageService {
    void addImage(Image entity);
    void deleteImageById(int id);
    void updateImage(Image entity);
    Image getImageById(int id);
    List<Image> getImageByUrl(String url);
    List<Image> getImageByUploader(String uploader);
    List<Image> getAllImages();
    boolean isImageUploadedByUploader(String url, String uploader);
}
