/*    */ package net.kyori.adventure.translation;
/*    */ 
/*    */ import java.util.Locale;
/*    */ import java.util.function.Supplier;
/*    */ import net.kyori.adventure.internal.properties.AdventureProperties;
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
/*    */ final class TranslationLocales
/*    */ {
/*    */   private static final Supplier<Locale> GLOBAL;
/*    */   
/*    */   static {
/* 35 */     String property = (String)AdventureProperties.DEFAULT_TRANSLATION_LOCALE.value();
/* 36 */     if (property == null || property.isEmpty()) {
/* 37 */       GLOBAL = (() -> Locale.US);
/* 38 */     } else if (property.equals("system")) {
/* 39 */       GLOBAL = Locale::getDefault;
/*    */     } else {
/* 41 */       Locale locale = Translator.parseLocale(property);
/* 42 */       GLOBAL = (() -> locale);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static Locale global() {
/* 50 */     return GLOBAL.get();
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\translation\TranslationLocales.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */