/*     */ package net.wesjd.anvilgui.version;
/*     */ import net.minecraft.server.v1_16_R3.ChatComponentText;
/*     */ import net.minecraft.server.v1_16_R3.Container;
/*     */ import net.minecraft.server.v1_16_R3.EntityHuman;
/*     */ import net.minecraft.server.v1_16_R3.IChatBaseComponent;
/*     */ import net.minecraft.server.v1_16_R3.Packet;
/*     */ import net.minecraft.server.v1_16_R3.Slot;
/*     */ import net.minecraft.server.v1_16_R3.World;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class Wrapper1_16_R3 implements VersionWrapper {
/*     */   private int getRealNextContainerId(Player player) {
/*  13 */     return toNMS(player).nextContainerCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNextContainerId(Player player, VersionWrapper.AnvilContainerWrapper container) {
/*  21 */     return ((AnvilContainer)container).getContainerId();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleInventoryCloseEvent(Player player) {
/*  29 */     CraftEventFactory.handleInventoryCloseEvent((EntityHuman)toNMS(player));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPacketOpenWindow(Player player, int containerId, Object guiTitle) {
/*  37 */     (toNMS(player)).playerConnection
/*     */       
/*  39 */       .sendPacket((Packet)new PacketPlayOutOpenWindow(containerId, Containers.ANVIL, (IChatBaseComponent)guiTitle));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPacketCloseWindow(Player player, int containerId) {
/*  47 */     (toNMS(player)).playerConnection.sendPacket((Packet)new PacketPlayOutCloseWindow(containerId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActiveContainerDefault(Player player) {
/*  55 */     (toNMS(player)).activeContainer = (Container)(toNMS(player)).defaultContainer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActiveContainer(Player player, VersionWrapper.AnvilContainerWrapper container) {
/*  63 */     (toNMS(player)).activeContainer = (Container)container;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActiveContainerId(VersionWrapper.AnvilContainerWrapper container, int containerId) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addActiveContainerSlotListener(VersionWrapper.AnvilContainerWrapper container, Player player) {
/*  79 */     ((Container)container).addSlotListener((ICrafting)toNMS(player));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VersionWrapper.AnvilContainerWrapper newContainerAnvil(Player player, Object guiTitle) {
/*  87 */     return new AnvilContainer(player, (IChatBaseComponent)guiTitle);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object literalChatComponent(String content) {
/*  92 */     return new ChatComponentText(content);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object jsonChatComponent(String json) {
/*  97 */     return IChatBaseComponent.ChatSerializer.a(json);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private EntityPlayer toNMS(Player player) {
/* 107 */     return ((CraftPlayer)player).getHandle();
/*     */   }
/*     */ 
/*     */   
/*     */   private class AnvilContainer
/*     */     extends ContainerAnvil
/*     */     implements VersionWrapper.AnvilContainerWrapper
/*     */   {
/*     */     public AnvilContainer(Player player, IChatBaseComponent guiTitle) {
/* 116 */       super(Wrapper1_16_R3.this
/* 117 */           .getRealNextContainerId(player), 
/* 118 */           (((CraftPlayer)player).getHandle()).inventory, 
/* 119 */           ContainerAccess.at((World)((CraftWorld)player.getWorld()).getHandle(), new BlockPosition(0, 0, 0)));
/* 120 */       this.checkReachable = false;
/* 121 */       setTitle(guiTitle);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void e() {
/* 127 */       Slot output = getSlot(2);
/* 128 */       if (!output.hasItem()) {
/* 129 */         output.set(getSlot(0).getItem().cloneItemStack());
/*     */       }
/*     */       
/* 132 */       this.levelCost.set(0);
/*     */ 
/*     */       
/* 135 */       c();
/*     */     }
/*     */ 
/*     */     
/*     */     public void b(EntityHuman entityhuman) {}
/*     */ 
/*     */     
/*     */     protected void a(EntityHuman entityhuman, World world, IInventory iinventory) {}
/*     */     
/*     */     public int getContainerId() {
/* 145 */       return this.windowId;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getRenameText() {
/* 150 */       return this.renameText;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void setRenameText(String text) {
/* 156 */       Slot inputLeft = getSlot(0);
/* 157 */       if (inputLeft.hasItem()) {
/* 158 */         inputLeft.getItem().a((IChatBaseComponent)new ChatComponentText(text));
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public Inventory getBukkitInventory() {
/* 164 */       return getBukkitView().getTopInventory();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\wesjd\anvilgui\version\Wrapper1_16_R3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */