/*     */ package net.kyori.adventure.translation;
/*     */ 
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Locale;
/*     */ import net.kyori.adventure.key.Key;
/*     */ import net.kyori.adventure.text.Component;
/*     */ import net.kyori.adventure.text.TranslatableComponent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface Translator
/*     */ {
/*     */   @Nullable
/*     */   static Locale parseLocale(@NotNull String string) {
/*  59 */     String[] segments = string.split("_", 3);
/*  60 */     int length = segments.length;
/*  61 */     if (length == 1)
/*  62 */       return new Locale(string); 
/*  63 */     if (length == 2)
/*  64 */       return new Locale(segments[0], segments[1]); 
/*  65 */     if (length == 3) {
/*  66 */       return new Locale(segments[0], segments[1], segments[2]);
/*     */     }
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   Key name();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   MessageFormat translate(@NotNull String paramString, @NotNull Locale paramLocale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   default Component translate(@NotNull TranslatableComponent component, @NotNull Locale locale) {
/* 103 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\translation\Translator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */