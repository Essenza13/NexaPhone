/*    */ package net.kyori.adventure.text;
/*    */ 
/*    */ import java.util.stream.Stream;
/*    */ import net.kyori.examination.ExaminableProperty;
/*    */ import org.jetbrains.annotations.Contract;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public interface EntityNBTComponent
/*    */   extends NBTComponent<EntityNBTComponent, EntityNBTComponent.Builder>, ScopedComponent<EntityNBTComponent>
/*    */ {
/*    */   @NotNull
/*    */   String selector();
/*    */   
/*    */   @Contract(pure = true)
/*    */   @NotNull
/*    */   EntityNBTComponent selector(@NotNull String paramString);
/*    */   
/*    */   @NotNull
/*    */   default Stream<? extends ExaminableProperty> examinableProperties() {
/* 67 */     return Stream.concat(
/* 68 */         Stream.of(
/* 69 */           ExaminableProperty.of("selector", selector())), super
/*    */         
/* 71 */         .examinableProperties());
/*    */   }
/*    */   
/*    */   public static interface Builder extends NBTComponentBuilder<EntityNBTComponent, Builder> {
/*    */     @Contract("_ -> this")
/*    */     @NotNull
/*    */     Builder selector(@NotNull String param1String);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\text\EntityNBTComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */