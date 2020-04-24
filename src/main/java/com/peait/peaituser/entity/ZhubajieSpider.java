package com.peait.peaituser.entity;

public class ZhubajieSpider {
    private String id;

    private String contentDetail;

    private String moneyCount;

    private String pubTime;

    private String sHref;

    private String titleValue;

    private Integer isSend;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getContentDetail() {
        return contentDetail;
    }

    public void setContentDetail(String contentDetail) {
        this.contentDetail = contentDetail == null ? null : contentDetail.trim();
    }

    public String getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(String moneyCount) {
        this.moneyCount = moneyCount == null ? null : moneyCount.trim();
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime == null ? null : pubTime.trim();
    }

    public String getsHref() {
        return sHref;
    }

    public void setsHref(String sHref) {
        this.sHref = sHref == null ? null : sHref.trim();
    }

    public String getTitleValue() {
        return titleValue;
    }

    public void setTitleValue(String titleValue) {
        this.titleValue = titleValue == null ? null : titleValue.trim();
    }

    public Integer getIsSend() {
        return isSend;
    }

    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }
}