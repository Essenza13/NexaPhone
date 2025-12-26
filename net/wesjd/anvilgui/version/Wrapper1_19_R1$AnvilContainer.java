/*     */ package net.wesjd.anvilgui.version;
/*     */ 
/*     */ import net.minecraft.core.BlockPosition;
/*     */ import net.minecraft.network.chat.IChatBaseComponent;
/*     */ import net.minecraft.world.IInventory;
/*     */ import net.minecraft.world.entity.player.EntityHuman;
/*     */ import net.minecraft.world.inventory.ContainerAccess;
/*     */ import net.minecraft.world.inventory.ContainerAnvil;
/*     */ import net.minecraft.world.inventory.Slot;
/*     */ import net.minecraft.world.level.World;
/*     */ import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
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
/*     */ class AnvilContainer
/*     */   extends ContainerAnvil
/*     */   implements VersionWrapper.AnvilContainerWrapper
/*     */ {
/*     */   public AnvilContainer(Player player, int containerId, IChatBaseComponent guiTitle) {
/*  99 */     super(containerId, ((CraftPlayer)player)
/*     */         
/* 101 */         .getHandle().fB(), 
/* 102 */         ContainerAccess.a((World)((CraftWorld)player.getWorld()).getHandle(), new BlockPosition(0, 0, 0)));
/* 103 */     this.checkReachable = false;
/* 104 */     setTitle(guiTitle);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void l() {
/* 110 */     Slot output = b(2);
/* 111 */     if (!output.f()) {
/* 112 */       output.e(b(0).e().o());
/*     */     }
/*     */     
/* 115 */     this.w.a(0);
/*     */ 
/*     */     
/* 118 */     b();
/* 119 */     d();
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(EntityHuman player) {}
/*     */ 
/*     */   
/*     */   protected void a(EntityHuman player, IInventory container) {}
/*     */   
/*     */   public int getContainerId() {
/* 129 */     return this.j;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getRenameText() {
/* 134 */     return this.v;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRenameText(String text) {
/* 140 */     Slot inputLeft = b(0);
/* 141 */     if (inputLeft.f()) {
/* 142 */       inputLeft.e().a((IChatBaseComponent)IChatBaseComponent.b(text));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory getBukkitInventory() {
/* 148 */     return getBukkitView().getTopInventory();
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\wesjd\anvilgui\version\Wrapper1_19_R1$AnvilContainer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */