package translators;

public class TranslationBuilder {
  
  private String originLanguage;
  private String originText;
  private String targetLanguage;

  public TranslationBuilder() {
    this.originLanguage = "originLanguage";
    this.originText = "";
    this.targetLanguage = "targetLanguage";
  }

  public TranslationBuilder originLanguage(String originLanguage){
    this.originLanguage = originLanguage;
    return this;
  }

  public TranslationBuilder originText(String originText){
    this.originText = originText;
    return this;
  }

  public TranslationBuilder targetLanguage(String targetLanguage){
    this.targetLanguage = targetLanguage;
    return this;
  }

  public Translation build(){
    return new Translation(this.originLanguage, this.originText, this.targetLanguage);
  }

}
