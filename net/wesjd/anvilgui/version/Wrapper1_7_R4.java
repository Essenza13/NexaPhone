/*     */ package net.wesjd.anvilgui.version;
/*     */ 
/*     */ import net.minecraft.server.v1_7_R4.Container;
/*     */ import net.minecraft.server.v1_7_R4.ContainerAnvil;
/*     */ import net.minecraft.server.v1_7_R4.EntityHuman;
/*     */ import net.minecraft.server.v1_7_R4.EntityPlayer;
/*     */ import net.minecraft.server.v1_7_R4.ICrafting;
/*     */ import net.minecraft.server.v1_7_R4.Packet;
/*     */ import net.minecraft.server.v1_7_R4.PacketPlayOutCloseWindow;
/*     */ import net.minecraft.server.v1_7_R4.PacketPlayOutOpenWindow;
/*     */ import net.minecraft.server.v1_7_R4.Slot;
/*     */ import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
/*     */ import org.bukkit.craftbukkit.v1_7_R4.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Wrapper1_7_R4
/*     */   implements VersionWrapper
/*     */ {
/*     */   public int getNextContainerId(Player player, VersionWrapper.AnvilContainerWrapper container) {
/*  23 */     return toNMS(player).nextContainerCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleInventoryCloseEvent(Player player) {
/*  31 */     CraftEventFactory.handleInventoryCloseEvent((EntityHuman)toNMS(player));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPacketOpenWindow(Player player, int containerId, Object guiTitle) {
/*  39 */     (toNMS(player)).playerConnection.sendPacket((Packet)new PacketPlayOutOpenWindow(containerId, 8, "", 9, false));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPacketCloseWindow(Player player, int containerId) {
/*  48 */     (toNMS(player)).playerConnection.sendPacket((Packet)new PacketPlayOutCloseWindow(containerId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActiveContainerDefault(Player player) {
/*  56 */     (toNMS(player)).activeContainer = (toNMS(player)).defaultContainer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActiveContainer(Player player, VersionWrapper.AnvilContainerWrapper container) {
/*  64 */     (toNMS(player)).activeContainer = (Container)container;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActiveContainerId(VersionWrapper.AnvilContainerWrapper container, int containerId) {
/*  72 */     ((Container)container).windowId = containerId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addActiveContainerSlotListener(VersionWrapper.AnvilContainerWrapper container, Player player) {
/*  80 */     ((Container)container).addSlotListener((ICrafting)toNMS(player));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VersionWrapper.AnvilContainerWrapper newContainerAnvil(Player player, Object guiTitle) {
/*  88 */     return new AnvilContainer((EntityHuman)toNMS(player));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCustomTitleSupported() {
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object literalChatComponent(String content) {
/*  98 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object jsonChatComponent(String json) {
/* 103 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private EntityPlayer toNMS(Player player) {
/* 113 */     return ((CraftPlayer)player).getHandle();
/*     */   }
/*     */ 
/*     */   
/*     */   private class AnvilContainer
/*     */     extends ContainerAnvil
/*     */     implements VersionWrapper.AnvilContainerWrapper
/*     */   {
/*     */     public AnvilContainer(EntityHuman entityhuman) {
/* 122 */       super(entityhuman.inventory, entityhuman.world, 0, 0, 0, entityhuman);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void e() {
/* 128 */       Slot output = getSlot(2);
/* 129 */       if (!output.hasItem()) {
/* 130 */         output.set(getSlot(0).getItem().cloneItemStack());
/*     */       }
/*     */       
/* 133 */       this.a = 0;
/*     */ 
/*     */       
/* 136 */       b();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a(EntityHuman human) {
/* 141 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void b(EntityHuman entityhuman) {}
/*     */ 
/*     */     
/*     */     public Inventory getBukkitInventory() {
/* 149 */       return getBukkitView().getTopInventory();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\wesjd\anvilgui\version\Wrapper1_7_R4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */