/*     */ package net.wesjd.anvilgui.version;
/*     */ 
/*     */ import net.minecraft.server.v1_8_R3.BlockPosition;
/*     */ import net.minecraft.server.v1_8_R3.Blocks;
/*     */ import net.minecraft.server.v1_8_R3.ChatMessage;
/*     */ import net.minecraft.server.v1_8_R3.Container;
/*     */ import net.minecraft.server.v1_8_R3.ContainerAnvil;
/*     */ import net.minecraft.server.v1_8_R3.EntityHuman;
/*     */ import net.minecraft.server.v1_8_R3.EntityPlayer;
/*     */ import net.minecraft.server.v1_8_R3.IChatBaseComponent;
/*     */ import net.minecraft.server.v1_8_R3.ICrafting;
/*     */ import net.minecraft.server.v1_8_R3.Packet;
/*     */ import net.minecraft.server.v1_8_R3.PacketPlayOutCloseWindow;
/*     */ import net.minecraft.server.v1_8_R3.PacketPlayOutOpenWindow;
/*     */ import net.minecraft.server.v1_8_R3.Slot;
/*     */ import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
/*     */ import org.bukkit.craftbukkit.v1_8_R3.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ 
/*     */ public class Wrapper1_8_R3 implements VersionWrapper {
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
/*  39 */     (toNMS(player)).playerConnection
/*     */       
/*  41 */       .sendPacket((Packet)new PacketPlayOutOpenWindow(containerId, "minecraft:anvil", (IChatBaseComponent)new ChatMessage(Blocks.ANVIL
/*  42 */             .a() + ".name", new Object[0])));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPacketCloseWindow(Player player, int containerId) {
/*  50 */     (toNMS(player)).playerConnection.sendPacket((Packet)new PacketPlayOutCloseWindow(containerId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActiveContainerDefault(Player player) {
/*  58 */     (toNMS(player)).activeContainer = (toNMS(player)).defaultContainer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActiveContainer(Player player, VersionWrapper.AnvilContainerWrapper container) {
/*  66 */     (toNMS(player)).activeContainer = (Container)container;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActiveContainerId(VersionWrapper.AnvilContainerWrapper container, int containerId) {
/*  74 */     ((Container)container).windowId = containerId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addActiveContainerSlotListener(VersionWrapper.AnvilContainerWrapper container, Player player) {
/*  82 */     ((Container)container).addSlotListener((ICrafting)toNMS(player));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VersionWrapper.AnvilContainerWrapper newContainerAnvil(Player player, Object guiTitle) {
/*  90 */     return new AnvilContainer((EntityHuman)toNMS(player));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCustomTitleSupported() {
/*  95 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object literalChatComponent(String content) {
/* 100 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object jsonChatComponent(String json) {
/* 105 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private EntityPlayer toNMS(Player player) {
/* 115 */     return ((CraftPlayer)player).getHandle();
/*     */   }
/*     */ 
/*     */   
/*     */   private class AnvilContainer
/*     */     extends ContainerAnvil
/*     */     implements VersionWrapper.AnvilContainerWrapper
/*     */   {
/*     */     public AnvilContainer(EntityHuman entityhuman) {
/* 124 */       super(entityhuman.inventory, entityhuman.world, new BlockPosition(0, 0, 0), entityhuman);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void e() {
/* 130 */       Slot output = getSlot(2);
/* 131 */       if (!output.hasItem()) {
/* 132 */         output.set(getSlot(0).getItem().cloneItemStack());
/*     */       }
/*     */       
/* 135 */       this.a = 0;
/*     */ 
/*     */       
/* 138 */       b();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a(EntityHuman human) {
/* 143 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void b(EntityHuman entityhuman) {}
/*     */ 
/*     */     
/*     */     public Inventory getBukkitInventory() {
/* 151 */       return getBukkitView().getTopInventory();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\wesjd\anvilgui\version\Wrapper1_8_R3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */