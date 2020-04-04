package com.sanyouseki.fwzd.service.impl;

import com.sanyouseki.fwzd.dao.ImageMapper;
import com.sanyouseki.fwzd.entity.Image;
import com.sanyouseki.fwzd.service.IImageService;
import com.sanyouseki.fwzd.util.MyDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements IImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public void addImage(Image entity) {
        imageMapper.add(entity.getUploader(), entity.getName(), entity.getUrl(), entity.getThumbUrl());
    }

    @Override
    public void deleteImageById(int id) {
        imageMapper.safeDelete(1, id);
    }

    @Override
    public void updateImage(Image entity) {
        imageMapper.update(entity.getUploader(), entity.getName(), entity.getUrl(), entity.getThumbUrl(), entity.getRank(), entity.getId());
    }

    @Override
    public Image getImageById(int id) {
        return imageMapper.findImage(id);
    }

    @Override
    public List<Image> getImageByUrl(String url) {
        return imageMapper.findImageByUrl(url);
    }

    @Override
    public List<Image> getImageByUploader(String uploader) {
        return imageMapper.findImageByUploader(uploader);
    }

    @Override
    public List<Image> getAllImages() {
        return imageMapper.findImageList();
    }

    @Override
    public boolean isImageUploadedByUploader(String url, String uploader) {
        List<Image> images = getImageByUrl(url);
        for (Image image : images) {
            if (image.getUploader().equals(uploader)) {
                return true;
            }
        }
        return false;
    }
}
