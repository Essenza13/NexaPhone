/*    */ package net.wesjd.anvilgui.version.special;
/*    */ 
/*    */ import net.minecraft.core.BlockPosition;
/*    */ import net.minecraft.network.chat.IChatBaseComponent;
/*    */ import net.minecraft.world.IInventory;
/*    */ import net.minecraft.world.entity.player.EntityHuman;
/*    */ import net.minecraft.world.inventory.ContainerAccess;
/*    */ import net.minecraft.world.inventory.ContainerAnvil;
/*    */ import net.minecraft.world.inventory.Slot;
/*    */ import net.minecraft.world.level.World;
/*    */ import net.wesjd.anvilgui.version.VersionWrapper;
/*    */ import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
/*    */ import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public class AnvilContainer1_19_1_R1 extends ContainerAnvil implements VersionWrapper.AnvilContainerWrapper {
/*    */   public AnvilContainer1_19_1_R1(Player player, int containerId, IChatBaseComponent guiTitle) {
/* 19 */     super(containerId, ((CraftPlayer)player)
/*    */         
/* 21 */         .getHandle().fA(), 
/* 22 */         ContainerAccess.a((World)((CraftWorld)player.getWorld()).getHandle(), new BlockPosition(0, 0, 0)));
/* 23 */     this.checkReachable = false;
/* 24 */     setTitle(guiTitle);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void l() {
/* 30 */     Slot output = b(2);
/* 31 */     if (!output.f()) {
/* 32 */       output.e(b(0).e().o());
/*    */     }
/*    */     
/* 35 */     this.w.a(0);
/*    */     
/* 37 */     b();
/* 38 */     d();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(EntityHuman player) {}
/*    */ 
/*    */   
/*    */   protected void a(EntityHuman player, IInventory container) {}
/*    */ 
/*    */   
/*    */   public String getRenameText() {
/* 49 */     return this.v;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRenameText(String text) {
/* 55 */     Slot inputLeft = b(0);
/* 56 */     if (inputLeft.f()) {
/* 57 */       inputLeft.e().a((IChatBaseComponent)IChatBaseComponent.b(text));
/*    */     }
/*    */   }
/*    */   
/*    */   public int getContainerId() {
/* 62 */     return this.j;
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getBukkitInventory() {
/* 67 */     return getBukkitView().getTopInventory();
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\wesjd\anvilgui\version\special\AnvilContainer1_19_1_R1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */