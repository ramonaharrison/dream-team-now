package com.ramonaharrison.dev.dreamteamnow.NYTAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Doc implements Serializable{

    @SerializedName("web_url")
    @Expose
    private String webUrl;
    @Expose
    private String snippet;
    @SerializedName("lead_paragraph")
    @Expose
    private Object leadParagraph;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @SerializedName("print_page")
    @Expose
    private Object printPage;
    @Expose
    private List<Object> blog = new ArrayList<Object>();
    @Expose
    private String source;
    @Expose
    private List<Object> multimedia = new ArrayList<Object>();
    @Expose
    private Headline headline;
    @Expose
    private List<Object> keywords = new ArrayList<Object>();
    @SerializedName("pub_date")
    @Expose
    private String pubDate;
    @SerializedName("document_type")
    @Expose
    private String documentType;
    @SerializedName("news_desk")
    @Expose
    private Object newsDesk;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("subsection_name")
    @Expose
    private String subsectionName;
    @SerializedName("type_of_material")
    @Expose
    private String typeOfMaterial;
    @SerializedName("_id")
    @Expose
    private String Id;
    @SerializedName("word_count")
    @Expose
    private String wordCount;

    /**
     *
     * @return
     * The webUrl
     */
    public String getWebUrl() {
        return webUrl;
    }

    /**
     *
     * @param webUrl
     * The web_url
     */
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    /**
     *
     * @return
     * The snippet
     */
    public String getSnippet() {
        return snippet;
    }

    /**
     *
     * @param snippet
     * The snippet
     */
    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    /**
     *
     * @return
     * The leadParagraph
     */
    public Object getLeadParagraph() {
        return leadParagraph;
    }

    /**
     *
     * @param leadParagraph
     * The lead_paragraph
     */
    public void setLeadParagraph(Object leadParagraph) {
        this.leadParagraph = leadParagraph;
    }

    /**
     *
     * @return
     * The _abstract
     */
    public String getAbstract() {
        return _abstract;
    }

    /**
     *
     * @param _abstract
     * The abstract
     */
    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    /**
     *
     * @return
     * The printPage
     */
    public Object getPrintPage() {
        return printPage;
    }

    /**
     *
     * @param printPage
     * The print_page
     */
    public void setPrintPage(Object printPage) {
        this.printPage = printPage;
    }

    /**
     *
     * @return
     * The blog
     */
    public List<Object> getBlog() {
        return blog;
    }

    /**
     *
     * @param blog
     * The blog
     */
    public void setBlog(List<Object> blog) {
        this.blog = blog;
    }

    /**
     *
     * @return
     * The source
     */
    public String getSource() {
        return source;
    }

    /**
     *
     * @param source
     * The source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     *
     * @return
     * The multimedia
     */
    public List<Object> getMultimedia() {
        return multimedia;
    }

    /**
     *
     * @param multimedia
     * The multimedia
     */
    public void setMultimedia(List<Object> multimedia) {
        this.multimedia = multimedia;
    }

    /**
     *
     * @return
     * The headline
     */
    public Headline getHeadline() {
        return headline;
    }

    /**
     *
     * @param headline
     * The headline
     */
    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    /**
     *
     * @return
     * The keywords
     */
    public List<Object> getKeywords() {
        return keywords;
    }

    /**
     *
     * @param keywords
     * The keywords
     */
    public void setKeywords(List<Object> keywords) {
        this.keywords = keywords;
    }

    /**
     *
     * @return
     * The pubDate
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     *
     * @param pubDate
     * The pub_date
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    /**
     *
     * @return
     * The documentType
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     *
     * @param documentType
     * The document_type
     */
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    /**
     *
     * @return
     * The newsDesk
     */
    public Object getNewsDesk() {
        return newsDesk;
    }

    /**
     *
     * @param newsDesk
     * The news_desk
     */
    public void setNewsDesk(Object newsDesk) {
        this.newsDesk = newsDesk;
    }

    /**
     *
     * @return
     * The sectionName
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     *
     * @param sectionName
     * The section_name
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    /**
     *
     * @return
     * The subsectionName
     */
    public String getSubsectionName() {
        return subsectionName;
    }

    /**
     *
     * @param subsectionName
     * The subsection_name
     */
    public void setSubsectionName(String subsectionName) {
        this.subsectionName = subsectionName;
    }

    /**
     *
     * @return
     * The typeOfMaterial
     */
    public String getTypeOfMaterial() {
        return typeOfMaterial;
    }

    /**
     *
     * @param typeOfMaterial
     * The type_of_material
     */
    public void setTypeOfMaterial(String typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    /**
     *
     * @return
     * The Id
     */
    public String getId() {
        return Id;
    }

    /**
     *
     * @param Id
     * The _id
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     *
     * @return
     * The wordCount
     */
    public String getWordCount() {
        return wordCount;
    }

    /**
     *
     * @param wordCount
     * The word_count
     */
    public void setWordCount(String wordCount) {
        this.wordCount = wordCount;
    }

}