package com.ainwik.wallpaper;

public class ImageModel {
    private UriModel src;

    public ImageModel(UriModel src) {
        this.src = src;
    }

    public UriModel getSrc() {
        return src;
    }

    public void setSrc(UriModel src) {
        this.src = src;
    }
}
