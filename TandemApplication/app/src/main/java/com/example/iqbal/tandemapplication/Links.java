package com.example.iqbal.tandemapplication;

import java.io.Serializable;

/**
 * Created by Iqbal on 9/1/17.
 */
public class Links implements Serializable {
    private String mission_patch;
    private String article_link;
    private String video_link;

    public String getMission_patch() {
        return mission_patch;
    }

    public String getArticle_link() {
        return article_link;
    }

    public String getVideo_link() {
        return video_link;
    }

    @Override
    public String toString() {
        String str = "Mission Patch: ";
        str += mission_patch + ",\n";
        str += "Article Link: ";
        str += article_link + ",\n";
        str += "Video Link: ";
        str += video_link + ",\n";
        return str;
    }
}
