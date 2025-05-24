package com.aiquestionsolver.user;

public class ApiResponse {
    private boolean isImage;
    private String imageUrl;
    private String text;

    public ApiResponse(boolean isImage, String imageUrl, String text) {
        this.isImage = isImage;
        this.imageUrl = imageUrl;
        this.text = text;
    }

    public boolean isImage() {
        return isImage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getText() {
        return text;
    }

    // Define any other methods or properties as needed
}
