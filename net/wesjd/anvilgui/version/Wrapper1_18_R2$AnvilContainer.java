/*     */ package net.wesjd.anvilgui.version;
/*     */ 
/*     */ import net.minecraft.core.BlockPosition;
/*     */ import net.minecraft.network.chat.ChatComponentText;
/*     */ import net.minecraft.network.chat.IChatBaseComponent;
/*     */ import net.minecraft.world.IInventory;
/*     */ import net.minecraft.world.entity.player.EntityHuman;
/*     */ import net.minecraft.world.inventory.ContainerAccess;
/*     */ import net.minecraft.world.inventory.ContainerAnvil;
/*     */ import net.minecraft.world.inventory.Slot;
/*     */ import net.minecraft.world.level.World;
/*     */ import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
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
/*  89 */     super(containerId, ((CraftPlayer)player)
/*     */         
/*  91 */         .getHandle().fr(), 
/*  92 */         ContainerAccess.a((World)((CraftWorld)player.getWorld()).getHandle(), new BlockPosition(0, 0, 0)));
/*  93 */     this.checkReachable = false;
/*  94 */     setTitle(guiTitle);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void l() {
/* 100 */     Slot output = b(2);
/* 101 */     if (!output.f()) {
/* 102 */       output.d(b(0).e().n());
/*     */     }
/*     */     
/* 105 */     this.w.a(0);
/*     */ 
/*     */     
/* 108 */     b();
/* 109 */     d();
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(EntityHuman player) {}
/*     */ 
/*     */   
/*     */   protected void a(EntityHuman player, IInventory container) {}
/*     */   
/*     */   public int getContainerId() {
/* 119 */     return this.j;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getRenameText() {
/* 124 */     return this.v;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRenameText(String text) {
/* 130 */     Slot inputLeft = b(0);
/* 131 */     if (inputLeft.f()) {
/* 132 */       inputLeft.e().a((IChatBaseComponent)new ChatComponentText(text));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory getBukkitInventory() {
/* 138 */     return getBukkitView().getTopInventory();
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\wesjd\anvilgui\version\Wrapper1_18_R2$AnvilContainer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */