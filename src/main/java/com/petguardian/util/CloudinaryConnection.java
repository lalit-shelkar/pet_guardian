package com.petguardian.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.util.Map;


public class CloudinaryConnection {
    private static final String CLOUDINARY_URL ="cloudinary://497586915996264:ZX5Sk7AeaHiIKr1dqJsK8ZdgvXA@dstxrkjmm";
    private static Cloudinary cloudinary;

    public static void initializeCloudinary() {
        if (cloudinary == null) {
            if (CLOUDINARY_URL == null || CLOUDINARY_URL.isEmpty()) {
                throw new IllegalStateException("CLOUDINARY_URL environment variable is not set.");
            }
            cloudinary = new Cloudinary(CLOUDINARY_URL);
            cloudinary.config.secure = true;
            System.out.println("Cloudinary initialized.");
        }
    }

    public String uploadToCloudinary(File selectedImageFile) {
        initializeCloudinary(); 

        if (selectedImageFile == null || !selectedImageFile.exists()) {
            System.err.println("Selected image file is null or does not exist.");
            return "failed";
        }

        try {
            Map<String, Object> params = ObjectUtils.asMap(
                    "use_filename", true,
                    "unique_filename", false,
                    "overwrite", true,
                    "folder", "petguardian/doctor"
            );
            Map<String, Object> response = cloudinary.uploader().upload(selectedImageFile, params);
            return response.get("secure_url").toString();
        } catch (Exception e) {
            System.err.println("Failed to upload: " + e.getMessage());
            e.printStackTrace();
            return "failed";
        }
    }
}
