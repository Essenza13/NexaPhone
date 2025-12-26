/*     */ package net.kyori.adventure.text;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import java.util.stream.Stream;
/*     */ import net.kyori.examination.ExaminableProperty;
/*     */ import org.jetbrains.annotations.Contract;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface KeybindComponent
/*     */   extends BuildableComponent<KeybindComponent, KeybindComponent.Builder>, ScopedComponent<KeybindComponent>
/*     */ {
/*     */   @NotNull
/*     */   String keybind();
/*     */   
/*     */   @Contract(pure = true)
/*     */   @NotNull
/*     */   KeybindComponent keybind(@NotNull String paramString);
/*     */   
/*     */   @Contract(pure = true)
/*     */   @NotNull
/*     */   default KeybindComponent keybind(@NotNull KeybindLike keybind) {
/*  72 */     return keybind(((KeybindLike)Objects.<KeybindLike>requireNonNull(keybind, "keybind")).asKeybind());
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   default Stream<? extends ExaminableProperty> examinableProperties() {
/*  77 */     return Stream.concat(
/*  78 */         Stream.of(
/*  79 */           ExaminableProperty.of("keybind", keybind())), super
/*     */         
/*  81 */         .examinableProperties());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface Builder
/*     */     extends ComponentBuilder<KeybindComponent, Builder>
/*     */   {
/*     */     @Contract("_ -> this")
/*     */     @NotNull
/*     */     Builder keybind(@NotNull String param1String);
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
/*     */     @Contract(pure = true)
/*     */     @NotNull
/*     */     default Builder keybind(@NotNull KeybindComponent.KeybindLike keybind) {
/* 125 */       return keybind(((KeybindComponent.KeybindLike)Objects.<KeybindComponent.KeybindLike>requireNonNull(keybind, "keybind")).asKeybind());
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface KeybindLike {
/*     */     @NotNull
/*     */     String asKeybind();
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\text\KeybindComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */