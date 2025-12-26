/*    */ package net.kyori.adventure.text.serializer.gson;
/*    */ 
/*    */ import com.google.gson.TypeAdapter;
/*    */ import net.kyori.adventure.text.format.TextDecoration;
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
/*    */ final class TextDecorationSerializer
/*    */ {
/* 30 */   static final TypeAdapter<TextDecoration> INSTANCE = IndexedSerializer.strict("text decoration", TextDecoration.NAMES);
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\text\serializer\gson\TextDecorationSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */