package com.pamon.app.pamon.model;

/**
 * Created by Sofiyanudin on 05/11/2017.
 */

public class CardRecyclerView {
    int id;
    String urlImage = null,title = null,content = null;
    int layout;
    public final static int LAYOUT_A = 0; //WITH IMAGE
    public final static int LAYOUT_B = 1; //WITHOUT IMAGE

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
