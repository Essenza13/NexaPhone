/*    */ package dev.triumphteam.gui.builder.gui;
/*    */ 
/*    */ import dev.triumphteam.gui.components.util.Legacy;
/*    */ import dev.triumphteam.gui.guis.BaseGui;
/*    */ import dev.triumphteam.gui.guis.PaginatedGui;
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
/*    */ public class PaginatedBuilder
/*    */   extends BaseGuiBuilder<PaginatedGui, PaginatedBuilder>
/*    */ {
/* 38 */   private int pageSize = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   @Contract("_ -> this")
/*    */   public PaginatedBuilder pageSize(int pageSize) {
/* 49 */     this.pageSize = pageSize;
/* 50 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   @Contract(" -> new")
/*    */   public PaginatedGui create() {
/* 62 */     PaginatedGui gui = new PaginatedGui(getRows(), this.pageSize, Legacy.SERIALIZER.serialize(getTitle()), getModifiers());
/*    */     
/* 64 */     Consumer<PaginatedGui> consumer = getConsumer();
/* 65 */     if (consumer != null) consumer.accept(gui);
/*    */     
/* 67 */     return gui;
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\dev\triumphteam\gui\builder\gui\PaginatedBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */