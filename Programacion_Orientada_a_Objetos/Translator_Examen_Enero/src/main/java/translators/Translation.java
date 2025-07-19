package translators;

public class Translation {

  private String originLanguage;
  private String originText;
  private String targetLanguage;

  public Translation(String originLanguage, String originText,
      String targetLanguage) {
    this.originLanguage = originLanguage;
    this.originText = originText;
    this.targetLanguage = targetLanguage;
  }

  public String getOriginLanguage() {
    return this.originLanguage;
  }

  public String getOriginText() {
    return this.originText;
  }

  public String getTargetLanguage() {
    return this.targetLanguage;
  }

  public int getNumWordsInText(){
    int num = 0;
    String[] separacion = this.originText.split(" ");
    if(separacion.length>1){
      while (separacion[0] != null){
        num++;
        separacion[0] = separacion[1];
      }
    }
    return num;
  }
}
