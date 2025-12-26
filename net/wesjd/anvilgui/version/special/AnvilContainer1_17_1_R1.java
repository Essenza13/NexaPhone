/*    */ package net.wesjd.anvilgui.version.special;
/*    */ 
/*    */ import net.minecraft.core.BlockPosition;
/*    */ import net.minecraft.network.chat.ChatComponentText;
/*    */ import net.minecraft.network.chat.IChatBaseComponent;
/*    */ import net.minecraft.world.IInventory;
/*    */ import net.minecraft.world.entity.player.EntityHuman;
/*    */ import net.minecraft.world.inventory.ContainerAccess;
/*    */ import net.minecraft.world.inventory.ContainerAnvil;
/*    */ import net.minecraft.world.inventory.Slot;
/*    */ import net.minecraft.world.level.World;
/*    */ import net.wesjd.anvilgui.version.VersionWrapper;
/*    */ import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
/*    */ import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public class AnvilContainer1_17_1_R1 extends ContainerAnvil implements VersionWrapper.AnvilContainerWrapper {
/*    */   public AnvilContainer1_17_1_R1(Player player, int containerId, IChatBaseComponent guiTitle) {
/* 20 */     super(containerId, ((CraftPlayer)player)
/*    */         
/* 22 */         .getHandle().getInventory(), 
/* 23 */         ContainerAccess.at((World)((CraftWorld)player.getWorld()).getHandle(), new BlockPosition(0, 0, 0)));
/* 24 */     this.checkReachable = false;
/* 25 */     setTitle(guiTitle);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void l() {
/* 31 */     Slot output = getSlot(2);
/* 32 */     if (!output.hasItem()) {
/* 33 */       output.set(getSlot(0).getItem().cloneItemStack());
/*    */     }
/*    */     
/* 36 */     this.w.set(0);
/*    */ 
/*    */     
/* 39 */     updateInventory();
/* 40 */     d();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(EntityHuman player) {}
/*    */ 
/*    */   
/*    */   protected void a(EntityHuman player, IInventory container) {}
/*    */   
/*    */   public int getContainerId() {
/* 50 */     return this.j;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRenameText() {
/* 55 */     return this.v;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRenameText(String text) {
/* 61 */     Slot inputLeft = getSlot(0);
/* 62 */     if (inputLeft.hasItem()) {
/* 63 */       inputLeft.getItem().a((IChatBaseComponent)new ChatComponentText(text));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getBukkitInventory() {
/* 69 */     return getBukkitView().getTopInventory();
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\wesjd\anvilgui\version\special\AnvilContainer1_17_1_R1.class
 * Java compiler version: 16 (60.0)
 * JD-Core Version:       1.1.3
 */