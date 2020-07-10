package com.estate.dto;

public class CustomerServiceDTO extends AbstractDTO<CustomerServiceDTO> {

    private static final long serialVersionUID = 7370312437221324007L;

    private String date;
    private String content;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
