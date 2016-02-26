package com.mai.sensitiveword.entity;

/**
 * Created by sks on 2015/12/8.
 */
public class FilteredResult {

    private Integer level;// 文本最终警告级别
    private String filteredContent;// 屏蔽敏感词后的文本内容
    private String badWords;// 屏蔽的敏感词串,eg:色魔,法轮功,GCD
    private String originalContent;//原始内容

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getFilteredContent() {
        return filteredContent;
    }

    public void setFilteredContent(String filteredContent) {
        this.filteredContent = filteredContent;
    }

    public String getBadWords() {
        return badWords;
    }

    public void setBadWords(String badWords) {
        this.badWords = badWords;
    }

    public String getOriginalContent() {
        return originalContent;
    }

    public void setOriginalContent(String originalContent) {
        this.originalContent = originalContent;
    }
}
