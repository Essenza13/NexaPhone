/*     */ package net.wesjd.anvilgui.version;
/*     */ 
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
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
/*     */ public interface VersionWrapper
/*     */ {
/*     */   int getNextContainerId(Player paramPlayer, AnvilContainerWrapper paramAnvilContainerWrapper);
/*     */   
/*     */   void handleInventoryCloseEvent(Player paramPlayer);
/*     */   
/*     */   void sendPacketOpenWindow(Player paramPlayer, int paramInt, Object paramObject);
/*     */   
/*     */   void sendPacketCloseWindow(Player paramPlayer, int paramInt);
/*     */   
/*     */   void setActiveContainerDefault(Player paramPlayer);
/*     */   
/*     */   void setActiveContainer(Player paramPlayer, AnvilContainerWrapper paramAnvilContainerWrapper);
/*     */   
/*     */   void setActiveContainerId(AnvilContainerWrapper paramAnvilContainerWrapper, int paramInt);
/*     */   
/*     */   void addActiveContainerSlotListener(AnvilContainerWrapper paramAnvilContainerWrapper, Player paramPlayer);
/*     */   
/*     */   AnvilContainerWrapper newContainerAnvil(Player paramPlayer, Object paramObject);
/*     */   
/*     */   default boolean isCustomTitleSupported() {
/*  94 */     return true;
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
/*     */   Object literalChatComponent(String paramString);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Object jsonChatComponent(String paramString);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface AnvilContainerWrapper
/*     */   {
/*     */     default String getRenameText() {
/* 126 */       return null;
/*     */     }
/*     */     
/*     */     default void setRenameText(String text) {}
/*     */     
/*     */     Inventory getBukkitInventory();
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\wesjd\anvilgui\version\VersionWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */