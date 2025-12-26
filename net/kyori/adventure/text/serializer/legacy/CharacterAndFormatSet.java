/*    */ package net.kyori.adventure.text.serializer.legacy;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import net.kyori.adventure.text.format.TextColor;
/*    */ import net.kyori.adventure.text.format.TextFormat;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class CharacterAndFormatSet
/*    */ {
/* 33 */   static final CharacterAndFormatSet DEFAULT = of(CharacterAndFormat.defaults());
/*    */   final List<TextFormat> formats;
/*    */   final List<TextColor> colors;
/*    */   final String characters;
/*    */   
/*    */   static CharacterAndFormatSet of(List<CharacterAndFormat> pairs) {
/* 39 */     int size = pairs.size();
/* 40 */     List<TextColor> colors = new ArrayList<>();
/* 41 */     List<TextFormat> formats = new ArrayList<>(size);
/* 42 */     StringBuilder characters = new StringBuilder(size);
/* 43 */     for (int i = 0; i < size; i++) {
/* 44 */       CharacterAndFormat pair = pairs.get(i);
/* 45 */       characters.append(pair.character());
/* 46 */       TextFormat format = pair.format();
/* 47 */       formats.add(format);
/* 48 */       if (format instanceof TextColor) {
/* 49 */         colors.add((TextColor)format);
/*    */       }
/*    */     } 
/* 52 */     if (formats.size() != characters.length()) {
/* 53 */       throw new IllegalStateException("formats length differs from characters length");
/*    */     }
/* 55 */     return new CharacterAndFormatSet(Collections.unmodifiableList(formats), Collections.unmodifiableList(colors), characters.toString());
/*    */   }
/*    */   
/*    */   CharacterAndFormatSet(List<TextFormat> formats, List<TextColor> colors, String characters) {
/* 59 */     this.formats = formats;
/* 60 */     this.colors = colors;
/* 61 */     this.characters = characters;
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\text\serializer\legacy\CharacterAndFormatSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */