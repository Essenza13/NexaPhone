/*    */ package net.kyori.adventure.text;
/*    */ 
/*    */ import java.util.stream.Stream;
/*    */ import net.kyori.examination.ExaminableProperty;
/*    */ import org.jetbrains.annotations.Contract;
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
/*    */ public interface SelectorComponent
/*    */   extends BuildableComponent<SelectorComponent, SelectorComponent.Builder>, ScopedComponent<SelectorComponent>
/*    */ {
/*    */   @NotNull
/*    */   String pattern();
/*    */   
/*    */   @Contract(pure = true)
/*    */   @NotNull
/*    */   SelectorComponent pattern(@NotNull String paramString);
/*    */   
/*    */   @Nullable
/*    */   Component separator();
/*    */   
/*    */   @NotNull
/*    */   SelectorComponent separator(@Nullable ComponentLike paramComponentLike);
/*    */   
/*    */   @NotNull
/*    */   default Stream<? extends ExaminableProperty> examinableProperties() {
/* 85 */     return Stream.concat(
/* 86 */         Stream.of(new ExaminableProperty[] {
/* 87 */             ExaminableProperty.of("pattern", pattern()), 
/* 88 */             ExaminableProperty.of("separator", separator())
/*    */           
/* 90 */           }), super.examinableProperties());
/*    */   }
/*    */   
/*    */   public static interface Builder extends ComponentBuilder<SelectorComponent, Builder> {
/*    */     @Contract("_ -> this")
/*    */     @NotNull
/*    */     Builder pattern(@NotNull String param1String);
/*    */     
/*    */     @Contract("_ -> this")
/*    */     @NotNull
/*    */     Builder separator(@Nullable ComponentLike param1ComponentLike);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\text\SelectorComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */