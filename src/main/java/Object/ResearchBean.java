package Object;

public class ResearchBean implements java.io.Serializable{
    private String ExpertName;
    private String PublicationName;
    private String KeyWord;

    public String getExpertName() {
        return ExpertName;
    }

    public void setExpertName(String expertName) {
        ExpertName = expertName;
    }

    public String getPublicationName() {
        return PublicationName;
    }

    public void setPublicationName(String publicationName) {
        PublicationName = publicationName;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }
}
