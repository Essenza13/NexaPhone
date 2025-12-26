/*    */ package dev.triumphteam.gui.builder.gui;
/*    */ 
/*    */ import dev.triumphteam.gui.components.ScrollType;
/*    */ import dev.triumphteam.gui.components.util.Legacy;
/*    */ import dev.triumphteam.gui.guis.BaseGui;
/*    */ import dev.triumphteam.gui.guis.ScrollingGui;
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
/*    */ public final class ScrollingBuilder
/*    */   extends BaseGuiBuilder<ScrollingGui, ScrollingBuilder>
/*    */ {
/*    */   private ScrollType scrollType;
/* 42 */   private int pageSize = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ScrollingBuilder(@NotNull ScrollType scrollType) {
/* 50 */     this.scrollType = scrollType;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   @Contract("_ -> this")
/*    */   public ScrollingBuilder scrollType(@NotNull ScrollType scrollType) {
/* 62 */     this.scrollType = scrollType;
/* 63 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   @Contract("_ -> this")
/*    */   public ScrollingBuilder pageSize(int pageSize) {
/* 75 */     this.pageSize = pageSize;
/* 76 */     return this;
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
/*    */   public ScrollingGui create() {
/* 88 */     ScrollingGui gui = new ScrollingGui(getRows(), this.pageSize, Legacy.SERIALIZER.serialize(getTitle()), this.scrollType, getModifiers());
/*    */     
/* 90 */     Consumer<ScrollingGui> consumer = getConsumer();
/* 91 */     if (consumer != null) consumer.accept(gui);
/*    */     
/* 93 */     return gui;
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\dev\triumphteam\gui\builder\gui\ScrollingBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */