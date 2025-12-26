/*    */ package net.wesjd.anvilgui.version.special;
/*    */ import net.minecraft.server.v1_14_R1.BlockPosition;
/*    */ import net.minecraft.server.v1_14_R1.EntityHuman;
/*    */ import net.minecraft.server.v1_14_R1.IChatBaseComponent;
/*    */ import net.minecraft.server.v1_14_R1.Slot;
/*    */ import net.minecraft.server.v1_14_R1.World;
/*    */ import net.wesjd.anvilgui.version.VersionWrapper;
/*    */ import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public class AnvilContainer1_14_4_R1 extends ContainerAnvil implements VersionWrapper.AnvilContainerWrapper {
/*    */   public AnvilContainer1_14_4_R1(Player player, int containerId, IChatBaseComponent guiTitle) {
/* 14 */     super(containerId, 
/*    */         
/* 16 */         (((CraftPlayer)player).getHandle()).inventory, 
/* 17 */         ContainerAccess.at((World)((CraftWorld)player.getWorld()).getHandle(), new BlockPosition(0, 0, 0)));
/* 18 */     this.checkReachable = false;
/* 19 */     setTitle(guiTitle);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void e() {
/* 25 */     Slot output = getSlot(2);
/* 26 */     if (!output.hasItem()) {
/* 27 */       output.set(getSlot(0).getItem().cloneItemStack());
/*    */     }
/*    */     
/* 30 */     this.levelCost.set(0);
/*    */ 
/*    */     
/* 33 */     c();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(EntityHuman entityhuman) {}
/*    */ 
/*    */   
/*    */   protected void a(EntityHuman entityhuman, World world, IInventory iinventory) {}
/*    */   
/*    */   public int getContainerId() {
/* 43 */     return this.windowId;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRenameText() {
/* 48 */     return this.renameText;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRenameText(String text) {
/* 54 */     Slot inputLeft = getSlot(0);
/* 55 */     if (inputLeft.hasItem()) {
/* 56 */       inputLeft.getItem().a((IChatBaseComponent)new ChatComponentText(text));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getBukkitInventory() {
/* 62 */     return getBukkitView().getTopInventory();
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\wesjd\anvilgui\version\special\AnvilContainer1_14_4_R1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */