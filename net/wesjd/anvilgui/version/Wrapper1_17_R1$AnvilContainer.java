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
/*     */ import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
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
/*     */   public AnvilContainer(Player player, IChatBaseComponent guiTitle) {
/* 133 */     super(paramWrapper1_17_R1
/* 134 */         .getRealNextContainerId(player), ((CraftPlayer)player)
/* 135 */         .getHandle().getInventory(), 
/* 136 */         ContainerAccess.at((World)((CraftWorld)player.getWorld()).getHandle(), new BlockPosition(0, 0, 0)));
/* 137 */     this.checkReachable = false;
/* 138 */     setTitle(guiTitle);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void i() {
/* 144 */     Slot output = getSlot(2);
/* 145 */     if (!output.hasItem()) {
/* 146 */       output.set(getSlot(0).getItem().cloneItemStack());
/*     */     }
/*     */     
/* 149 */     this.w.set(0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     updateInventory();
/* 156 */     d();
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(EntityHuman player) {}
/*     */ 
/*     */   
/*     */   protected void a(EntityHuman player, IInventory container) {}
/*     */   
/*     */   public int getContainerId() {
/* 166 */     return this.j;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getRenameText() {
/* 171 */     return this.v;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRenameText(String text) {
/* 177 */     Slot inputLeft = getSlot(0);
/* 178 */     if (inputLeft.hasItem()) {
/* 179 */       inputLeft.getItem().a((IChatBaseComponent)new ChatComponentText(text));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Inventory getBukkitInventory() {
/* 185 */     return getBukkitView().getTopInventory();
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\wesjd\anvilgui\version\Wrapper1_17_R1$AnvilContainer.class
 * Java compiler version: 16 (60.0)
 * JD-Core Version:       1.1.3
 */