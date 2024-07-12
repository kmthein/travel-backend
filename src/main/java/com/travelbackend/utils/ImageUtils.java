package com.travelbackend.utils;

import com.travelbackend.entity.Image;

import java.util.List;
import java.util.stream.Collectors;

public class ImageUtils {
    public static List<Image> filterNonDeleteImages(List<Image> images) {
        return images.stream()
                .filter(image -> !image.isDelete())
                .collect(Collectors.toList());
    }
}
