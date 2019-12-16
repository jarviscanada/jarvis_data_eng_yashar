package ca.jrvs.apps.twitter.dao.model;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Hashtag {
    private String text;
    private int[] indices;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int[] getIndices() {
        return indices;
    }

    public void setIndices(int[] indices) {
        this.indices = indices;
    }

}
