/*    */ package dev.triumphteam.gui.builder.gui;
/*    */ 
/*    */ import dev.triumphteam.gui.components.util.Legacy;
/*    */ import dev.triumphteam.gui.guis.BaseGui;
/*    */ import dev.triumphteam.gui.guis.StorageGui;
/*    */ import java.util.function.Consumer;
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
/*    */ public final class StorageBuilder
/*    */   extends BaseGuiBuilder<StorageGui, StorageBuilder>
/*    */ {
/*    */   @NotNull
/*    */   @Contract(" -> new")
/*    */   public StorageGui create() {
/* 47 */     StorageGui gui = new StorageGui(getRows(), Legacy.SERIALIZER.serialize(getTitle()), getModifiers());
/*    */     
/* 49 */     Consumer<StorageGui> consumer = getConsumer();
/* 50 */     if (consumer != null) consumer.accept(gui);
/*    */     
/* 52 */     return gui;
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\dev\triumphteam\gui\builder\gui\StorageBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */