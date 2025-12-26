/*     */ package net.wesjd.anvilgui.version;
/*     */ import net.minecraft.core.BlockPosition;
/*     */ import net.minecraft.network.chat.IChatBaseComponent;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.PacketPlayOutOpenWindow;
/*     */ import net.minecraft.server.level.EntityPlayer;
/*     */ import net.minecraft.world.IInventory;
/*     */ import net.minecraft.world.entity.player.EntityHuman;
/*     */ import net.minecraft.world.inventory.Container;
/*     */ import net.minecraft.world.inventory.ContainerAccess;
/*     */ import net.minecraft.world.inventory.Slot;
/*     */ import net.minecraft.world.level.World;
/*     */ import org.bukkit.craftbukkit.v1_19_R2.entity.CraftPlayer;
/*     */ import org.bukkit.craftbukkit.v1_19_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ 
/*     */ public final class Wrapper1_19_R2 implements VersionWrapper {
/*     */   private int getRealNextContainerId(Player player) {
/*  20 */     return toNMS(player).nextContainerCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private EntityPlayer toNMS(Player player) {
/*  30 */     return ((CraftPlayer)player).getHandle();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNextContainerId(Player player, VersionWrapper.AnvilContainerWrapper container) {
/*  35 */     return ((AnvilContainer)container).getContainerId();
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleInventoryCloseEvent(Player player) {
/*  40 */     CraftEventFactory.handleInventoryCloseEvent((EntityHuman)toNMS(player));
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendPacketOpenWindow(Player player, int containerId, Object inventoryTitle) {
/*  45 */     (toNMS(player)).b.a((Packet)new PacketPlayOutOpenWindow(containerId, Containers.h, (IChatBaseComponent)inventoryTitle));
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendPacketCloseWindow(Player player, int containerId) {
/*  50 */     (toNMS(player)).b.a((Packet)new PacketPlayOutCloseWindow(containerId));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActiveContainerDefault(Player player) {
/*  55 */     (toNMS(player)).bU = (Container)(toNMS(player)).bT;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActiveContainer(Player player, VersionWrapper.AnvilContainerWrapper container) {
/*  60 */     (toNMS(player)).bU = (Container)container;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActiveContainerId(VersionWrapper.AnvilContainerWrapper container, int containerId) {}
/*     */ 
/*     */   
/*     */   public void addActiveContainerSlotListener(VersionWrapper.AnvilContainerWrapper container, Player player) {
/*  68 */     toNMS(player).a((Container)container);
/*     */   }
/*     */ 
/*     */   
/*     */   public VersionWrapper.AnvilContainerWrapper newContainerAnvil(Player player, Object title) {
/*  73 */     return new AnvilContainer(player, getRealNextContainerId(player), (IChatBaseComponent)title);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object literalChatComponent(String content) {
/*  78 */     return IChatBaseComponent.b(content);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object jsonChatComponent(String json) {
/*  83 */     return IChatBaseComponent.ChatSerializer.a(json);
/*     */   }
/*     */   
/*     */   private static class AnvilContainer extends ContainerAnvil implements VersionWrapper.AnvilContainerWrapper {
/*     */     public AnvilContainer(Player player, int containerId, IChatBaseComponent guiTitle) {
/*  88 */       super(containerId, ((CraftPlayer)player)
/*     */           
/*  90 */           .getHandle().fE(), 
/*  91 */           ContainerAccess.a((World)((CraftWorld)player.getWorld()).getHandle(), new BlockPosition(0, 0, 0)));
/*  92 */       this.checkReachable = false;
/*  93 */       setTitle(guiTitle);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void l() {
/*  99 */       Slot output = b(2);
/* 100 */       if (!output.f()) {
/* 101 */         output.e(b(0).e().o());
/*     */       }
/*     */       
/* 104 */       this.w.a(0);
/*     */ 
/*     */       
/* 107 */       b();
/* 108 */       d();
/*     */     }
/*     */ 
/*     */     
/*     */     public void b(EntityHuman player) {}
/*     */ 
/*     */     
/*     */     protected void a(EntityHuman player, IInventory container) {}
/*     */     
/*     */     public int getContainerId() {
/* 118 */       return this.j;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getRenameText() {
/* 123 */       return this.v;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void setRenameText(String text) {
/* 129 */       Slot inputLeft = b(0);
/* 130 */       if (inputLeft.f()) {
/* 131 */         inputLeft.e().a((IChatBaseComponent)IChatBaseComponent.b(text));
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public Inventory getBukkitInventory() {
/* 137 */       return getBukkitView().getTopInventory();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\wesjd\anvilgui\version\Wrapper1_19_R2.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */