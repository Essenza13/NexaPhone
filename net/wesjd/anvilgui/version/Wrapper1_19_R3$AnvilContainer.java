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
/*     */ import org.bukkit.craftbukkit.v1_19_R3.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_19_R3.entity.CraftPlayer;
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
/*     */ class AnvilContainer
/*     */   extends ContainerAnvil
/*     */   implements VersionWrapper.AnvilContainerWrapper
/*     */ {
/*     */   public AnvilContainer(Player player, int containerId, IChatBaseComponent guiTitle) {
/*  88 */     super(containerId, ((CraftPlayer)player)
/*     */         
/*  90 */         .getHandle().fJ(), 
/*  91 */         ContainerAccess.a((World)((CraftWorld)player.getWorld()).getHandle(), new BlockPosition(0, 0, 0)));
/*  92 */     this.checkReachable = false;
/*  93 */     setTitle(guiTitle);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void m() {
/*  99 */     Slot output = b(2);
/* 100 */     if (!output.f()) {
/* 101 */       output.e(b(0).e().o());
/*     */     }
/*     */     
/* 104 */     this.w.a(0);
/*     */ 
/*     */     
/* 107 */     b();
/* 108 */     d();
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(EntityHuman player) {}
/*     */ 
/*     */   
/*     */   protected void a(EntityHuman player, IInventory container) {}
/*     */   
/*     */   public int getContainerId() {
/* 118 */     return this.j;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getRenameText() {
/* 123 */     return this.v;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRenameText(String text) {
/* 129 */     Slot inputLeft = b(0);
/* 130 */     if (inputLeft.f()) {
/* 131 */       inputLeft.e().a((IChatBaseComponent)IChatBaseComponent.b(text));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory getBukkitInventory() {
/* 137 */     return getBukkitView().getTopInventory();
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\wesjd\anvilgui\version\Wrapper1_19_R3$AnvilContainer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */