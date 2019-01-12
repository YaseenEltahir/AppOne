package com.example.yaseen.myapplication.Objects;

import com.google.gson.annotations.SerializedName;

public class Essay {
    //01-12 17:54:30.112 4248-4248/? E/pin: [{"essay_id":2,"essay_title":"مقالة 1","essay_body":"<p>لب الموضوع<\/p>","file_name":"My CV_1547157677.pdf","is_active":1,"created_at":"2019-01-10 22:01:17","updated_at":"2019-01-10 22:01:17"}]

    @SerializedName("essay_id")
    private String  essay_id;

    @SerializedName("essay_title")
    private String  essay_title;

    @SerializedName("essay_body")
    private String  essay_body;

    @SerializedName("file_name")
    private String  file_name;

    @SerializedName("is_active")
    private String  is_active;

    @SerializedName("created_at")
    private String  created_at;

    @SerializedName("updated_at")
    private String  updated_at;

    public String getEssay_id() {
        return essay_id;
    }

    public void setEssay_id(String essay_id) {
        this.essay_id = essay_id;
    }

    public String getEssay_title() {
        return essay_title;
    }

    public void setEssay_title(String essay_title) {
        this.essay_title = essay_title;
    }

    public String getEssay_body() {
        return essay_body;
    }

    public void setEssay_body(String essay_body) {
        this.essay_body = essay_body;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
