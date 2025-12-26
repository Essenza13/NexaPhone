/*    */ package net.kyori.adventure.text.format;
/*    */ 
/*    */ import java.util.EnumMap;
/*    */ import java.util.Map;
/*    */ import net.kyori.adventure.key.Key;
/*    */ import net.kyori.adventure.text.event.ClickEvent;
/*    */ import net.kyori.adventure.text.event.HoverEvent;
/*    */ import org.jetbrains.annotations.ApiStatus.NonExtendable;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ @NonExtendable
/*    */ public interface StyleGetter
/*    */ {
/*    */   @Nullable
/*    */   Key font();
/*    */   
/*    */   @Nullable
/*    */   TextColor color();
/*    */   
/*    */   default boolean hasDecoration(@NotNull TextDecoration decoration) {
/* 70 */     return (decoration(decoration) == TextDecoration.State.TRUE);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   TextDecoration.State decoration(@NotNull TextDecoration paramTextDecoration);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   default Map<TextDecoration, TextDecoration.State> decorations() {
/* 92 */     Map<TextDecoration, TextDecoration.State> decorations = new EnumMap<>(TextDecoration.class);
/* 93 */     for (int i = 0, length = DecorationMap.DECORATIONS.length; i < length; i++) {
/* 94 */       TextDecoration decoration = DecorationMap.DECORATIONS[i];
/* 95 */       TextDecoration.State value = decoration(decoration);
/* 96 */       decorations.put(decoration, value);
/*    */     } 
/* 98 */     return decorations;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   ClickEvent clickEvent();
/*    */   
/*    */   @Nullable
/*    */   HoverEvent<?> hoverEvent();
/*    */   
/*    */   @Nullable
/*    */   String insertion();
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\text\format\StyleGetter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */