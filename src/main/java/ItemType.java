public enum ItemType {
//Value      Code
  AUDIO("AU"),
  VISUAL("VI"),
  AUDIO_MOBILE("AM"),
  VISUAL_MOBILE("VM");

  final String code;

  ItemType(String code) {
    this.code = code;
  }

}
