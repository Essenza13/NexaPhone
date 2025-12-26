/*    */ package net.kyori.adventure.text;
/*    */ 
/*    */ import java.util.stream.Stream;
/*    */ import net.kyori.adventure.key.Key;
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
/*    */ public interface StorageNBTComponent
/*    */   extends NBTComponent<StorageNBTComponent, StorageNBTComponent.Builder>, ScopedComponent<StorageNBTComponent>
/*    */ {
/*    */   @NotNull
/*    */   Key storage();
/*    */   
/*    */   @Contract(pure = true)
/*    */   @NotNull
/*    */   StorageNBTComponent storage(@NotNull Key paramKey);
/*    */   
/*    */   @NotNull
/*    */   default Stream<? extends ExaminableProperty> examinableProperties() {
/* 68 */     return Stream.concat(
/* 69 */         Stream.of(
/* 70 */           ExaminableProperty.of("storage", storage())), super
/*    */         
/* 72 */         .examinableProperties());
/*    */   }
/*    */   
/*    */   public static interface Builder extends NBTComponentBuilder<StorageNBTComponent, Builder> {
/*    */     @Contract("_ -> this")
/*    */     @NotNull
/*    */     Builder storage(@NotNull Key param1Key);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\text\StorageNBTComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */