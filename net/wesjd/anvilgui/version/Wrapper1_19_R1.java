/*     */ package net.wesjd.anvilgui.version;
/*     */ import net.minecraft.core.BlockPosition;
/*     */ import net.minecraft.network.chat.IChatBaseComponent;
/*     */ import net.minecraft.network.protocol.Packet;
/*     */ import net.minecraft.network.protocol.game.PacketPlayOutOpenWindow;
/*     */ import net.minecraft.server.level.EntityPlayer;
/*     */ import net.minecraft.world.IInventory;
/*     */ import net.minecraft.world.entity.player.EntityHuman;
/*     */ import net.minecraft.world.inventory.Container;
/*     */ import net.minecraft.world.inventory.Slot;
/*     */ import net.minecraft.world.level.World;
/*     */ import net.wesjd.anvilgui.version.special.AnvilContainer1_19_1_R1;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
/*     */ import org.bukkit.craftbukkit.v1_19_R1.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ 
/*     */ public final class Wrapper1_19_R1 implements VersionWrapper {
/*  21 */   private final boolean IS_ONE_NINETEEN_ONE = (Bukkit.getBukkitVersion().contains("1.19.1") || 
/*  22 */     Bukkit.getBukkitVersion().contains("1.19.2"));
/*     */   
/*     */   private int getRealNextContainerId(Player player) {
/*  25 */     return toNMS(player).nextContainerCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private EntityPlayer toNMS(Player player) {
/*  35 */     return ((CraftPlayer)player).getHandle();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNextContainerId(Player player, VersionWrapper.AnvilContainerWrapper container) {
/*  40 */     if (this.IS_ONE_NINETEEN_ONE) {
/*  41 */       return ((AnvilContainer1_19_1_R1)container).getContainerId();
/*     */     }
/*  43 */     return ((AnvilContainer)container).getContainerId();
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleInventoryCloseEvent(Player player) {
/*  48 */     CraftEventFactory.handleInventoryCloseEvent((EntityHuman)toNMS(player));
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendPacketOpenWindow(Player player, int containerId, Object inventoryTitle) {
/*  53 */     (toNMS(player)).b.a((Packet)new PacketPlayOutOpenWindow(containerId, Containers.h, (IChatBaseComponent)inventoryTitle));
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendPacketCloseWindow(Player player, int containerId) {
/*  58 */     (toNMS(player)).b.a((Packet)new PacketPlayOutCloseWindow(containerId));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActiveContainerDefault(Player player) {
/*  63 */     (toNMS(player)).bU = (Container)(toNMS(player)).bT;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActiveContainer(Player player, VersionWrapper.AnvilContainerWrapper container) {
/*  68 */     (toNMS(player)).bU = (Container)container;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActiveContainerId(VersionWrapper.AnvilContainerWrapper container, int containerId) {}
/*     */ 
/*     */   
/*     */   public void addActiveContainerSlotListener(VersionWrapper.AnvilContainerWrapper container, Player player) {
/*  76 */     toNMS(player).a((Container)container);
/*     */   }
/*     */ 
/*     */   
/*     */   public VersionWrapper.AnvilContainerWrapper newContainerAnvil(Player player, Object title) {
/*  81 */     if (this.IS_ONE_NINETEEN_ONE) {
/*  82 */       return (VersionWrapper.AnvilContainerWrapper)new AnvilContainer1_19_1_R1(player, getRealNextContainerId(player), (IChatBaseComponent)title);
/*     */     }
/*  84 */     return new AnvilContainer(player, getRealNextContainerId(player), (IChatBaseComponent)title);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object literalChatComponent(String content) {
/*  89 */     return IChatBaseComponent.b(content);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object jsonChatComponent(String json) {
/*  94 */     return IChatBaseComponent.ChatSerializer.a(json);
/*     */   }
/*     */   
/*     */   private static class AnvilContainer extends ContainerAnvil implements VersionWrapper.AnvilContainerWrapper {
/*     */     public AnvilContainer(Player player, int containerId, IChatBaseComponent guiTitle) {
/*  99 */       super(containerId, ((CraftPlayer)player)
/*     */           
/* 101 */           .getHandle().fB(), 
/* 102 */           ContainerAccess.a((World)((CraftWorld)player.getWorld()).getHandle(), new BlockPosition(0, 0, 0)));
/* 103 */       this.checkReachable = false;
/* 104 */       setTitle(guiTitle);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void l() {
/* 110 */       Slot output = b(2);
/* 111 */       if (!output.f()) {
/* 112 */         output.e(b(0).e().o());
/*     */       }
/*     */       
/* 115 */       this.w.a(0);
/*     */ 
/*     */       
/* 118 */       b();
/* 119 */       d();
/*     */     }
/*     */ 
/*     */     
/*     */     public void b(EntityHuman player) {}
/*     */ 
/*     */     
/*     */     protected void a(EntityHuman player, IInventory container) {}
/*     */     
/*     */     public int getContainerId() {
/* 129 */       return this.j;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getRenameText() {
/* 134 */       return this.v;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void setRenameText(String text) {
/* 140 */       Slot inputLeft = b(0);
/* 141 */       if (inputLeft.f()) {
/* 142 */         inputLeft.e().a((IChatBaseComponent)IChatBaseComponent.b(text));
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public Inventory getBukkitInventory() {
/* 148 */       return getBukkitView().getTopInventory();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\wesjd\anvilgui\version\Wrapper1_19_R1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */