/*     */ package net.wesjd.anvilgui.version;
/*     */ import net.minecraft.core.BlockPosition;
/*     */ import net.minecraft.network.chat.ChatComponentText;
/*     */ import net.minecraft.network.chat.IChatBaseComponent;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.PacketPlayOutOpenWindow;
/*     */ import net.minecraft.server.level.EntityPlayer;
/*     */ import net.minecraft.world.IInventory;
/*     */ import net.minecraft.world.entity.player.EntityHuman;
/*     */ import net.minecraft.world.inventory.Container;
/*     */ import net.minecraft.world.inventory.Slot;
/*     */ import net.minecraft.world.level.World;
/*     */ import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer;
/*     */ import org.bukkit.craftbukkit.v1_18_R1.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ 
/*     */ public final class Wrapper1_18_R1 implements VersionWrapper {
/*     */   private int getRealNextContainerId(Player player) {
/*  21 */     return toNMS(player).nextContainerCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private EntityPlayer toNMS(Player player) {
/*  31 */     return ((CraftPlayer)player).getHandle();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNextContainerId(Player player, VersionWrapper.AnvilContainerWrapper container) {
/*  36 */     return ((AnvilContainer)container).getContainerId();
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleInventoryCloseEvent(Player player) {
/*  41 */     CraftEventFactory.handleInventoryCloseEvent((EntityHuman)toNMS(player));
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendPacketOpenWindow(Player player, int containerId, Object inventoryTitle) {
/*  46 */     (toNMS(player)).b.a((Packet)new PacketPlayOutOpenWindow(containerId, Containers.h, (IChatBaseComponent)inventoryTitle));
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendPacketCloseWindow(Player player, int containerId) {
/*  51 */     (toNMS(player)).b.a((Packet)new PacketPlayOutCloseWindow(containerId));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActiveContainerDefault(Player player) {
/*  56 */     (toNMS(player)).bW = (Container)(toNMS(player)).bV;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActiveContainer(Player player, VersionWrapper.AnvilContainerWrapper container) {
/*  61 */     (toNMS(player)).bW = (Container)container;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActiveContainerId(VersionWrapper.AnvilContainerWrapper container, int containerId) {}
/*     */ 
/*     */   
/*     */   public void addActiveContainerSlotListener(VersionWrapper.AnvilContainerWrapper container, Player player) {
/*  69 */     toNMS(player).a((Container)container);
/*     */   }
/*     */ 
/*     */   
/*     */   public VersionWrapper.AnvilContainerWrapper newContainerAnvil(Player player, Object title) {
/*  74 */     return new AnvilContainer(player, getRealNextContainerId(player), (IChatBaseComponent)title);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object literalChatComponent(String content) {
/*  79 */     return new ChatComponentText(content);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object jsonChatComponent(String json) {
/*  84 */     return IChatBaseComponent.ChatSerializer.a(json);
/*     */   }
/*     */   
/*     */   private static class AnvilContainer extends ContainerAnvil implements VersionWrapper.AnvilContainerWrapper {
/*     */     public AnvilContainer(Player player, int containerId, IChatBaseComponent guiTitle) {
/*  89 */       super(containerId, ((CraftPlayer)player)
/*     */           
/*  91 */           .getHandle().fq(), 
/*  92 */           ContainerAccess.a((World)((CraftWorld)player.getWorld()).getHandle(), new BlockPosition(0, 0, 0)));
/*  93 */       this.checkReachable = false;
/*  94 */       setTitle(guiTitle);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void l() {
/* 100 */       Slot output = a(2);
/* 101 */       if (!output.f()) {
/* 102 */         output.d(a(0).e().m());
/*     */       }
/*     */       
/* 105 */       this.w.a(0);
/*     */ 
/*     */       
/* 108 */       b();
/* 109 */       d();
/*     */     }
/*     */ 
/*     */     
/*     */     public void b(EntityHuman player) {}
/*     */ 
/*     */     
/*     */     protected void a(EntityHuman player, IInventory container) {}
/*     */     
/*     */     public int getContainerId() {
/* 119 */       return this.j;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getRenameText() {
/* 124 */       return this.v;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void setRenameText(String text) {
/* 130 */       Slot inputLeft = a(0);
/* 131 */       if (inputLeft.f()) {
/* 132 */         inputLeft.e().a((IChatBaseComponent)new ChatComponentText(text));
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public Inventory getBukkitInventory() {
/* 138 */       return getBukkitView().getTopInventory();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\wesjd\anvilgui\version\Wrapper1_18_R1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */