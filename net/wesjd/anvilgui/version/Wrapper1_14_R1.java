/*     */ package net.wesjd.anvilgui.version;
/*     */ import net.minecraft.server.v1_14_R1.BlockPosition;
/*     */ import net.minecraft.server.v1_14_R1.ChatComponentText;
/*     */ import net.minecraft.server.v1_14_R1.Container;
/*     */ import net.minecraft.server.v1_14_R1.EntityHuman;
/*     */ import net.minecraft.server.v1_14_R1.IChatBaseComponent;
/*     */ import net.minecraft.server.v1_14_R1.Packet;
/*     */ import net.minecraft.server.v1_14_R1.Slot;
/*     */ import net.minecraft.server.v1_14_R1.World;
/*     */ import net.wesjd.anvilgui.version.special.AnvilContainer1_14_4_R1;
/*     */ import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class Wrapper1_14_R1 implements VersionWrapper {
/*  15 */   private final boolean IS_ONE_FOURTEEN = Bukkit.getBukkitVersion().contains("1.14.4");
/*     */   
/*     */   private int getRealNextContainerId(Player player) {
/*  18 */     return toNMS(player).nextContainerCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNextContainerId(Player player, VersionWrapper.AnvilContainerWrapper container) {
/*  26 */     if (this.IS_ONE_FOURTEEN) {
/*  27 */       return ((AnvilContainer1_14_4_R1)container).getContainerId();
/*     */     }
/*  29 */     return ((AnvilContainer)container).getContainerId();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleInventoryCloseEvent(Player player) {
/*  38 */     CraftEventFactory.handleInventoryCloseEvent((EntityHuman)toNMS(player));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPacketOpenWindow(Player player, int containerId, Object guiTitle) {
/*  46 */     (toNMS(player)).playerConnection
/*     */       
/*  48 */       .sendPacket((Packet)new PacketPlayOutOpenWindow(containerId, Containers.ANVIL, (IChatBaseComponent)guiTitle));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPacketCloseWindow(Player player, int containerId) {
/*  56 */     (toNMS(player)).playerConnection.sendPacket((Packet)new PacketPlayOutCloseWindow(containerId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActiveContainerDefault(Player player) {
/*  64 */     (toNMS(player)).activeContainer = (Container)(toNMS(player)).defaultContainer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActiveContainer(Player player, VersionWrapper.AnvilContainerWrapper container) {
/*  72 */     (toNMS(player)).activeContainer = (Container)container;
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
/*  88 */     ((Container)container).addSlotListener((ICrafting)toNMS(player));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VersionWrapper.AnvilContainerWrapper newContainerAnvil(Player player, Object guiTitle) {
/*  96 */     if (this.IS_ONE_FOURTEEN) {
/*  97 */       return (VersionWrapper.AnvilContainerWrapper)new AnvilContainer1_14_4_R1(player, getRealNextContainerId(player), (IChatBaseComponent)guiTitle);
/*     */     }
/*  99 */     return new AnvilContainer(player, (IChatBaseComponent)guiTitle);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object literalChatComponent(String content) {
/* 105 */     return new ChatComponentText(content);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object jsonChatComponent(String json) {
/* 110 */     return IChatBaseComponent.ChatSerializer.a(json);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private EntityPlayer toNMS(Player player) {
/* 120 */     return ((CraftPlayer)player).getHandle();
/*     */   }
/*     */ 
/*     */   
/*     */   private class AnvilContainer
/*     */     extends ContainerAnvil
/*     */     implements VersionWrapper.AnvilContainerWrapper
/*     */   {
/*     */     public AnvilContainer(Player player, IChatBaseComponent guiTitle) {
/* 129 */       super(Wrapper1_14_R1.this
/* 130 */           .getRealNextContainerId(player), 
/* 131 */           (((CraftPlayer)player).getHandle()).inventory, 
/* 132 */           ContainerAccess.at((World)((CraftWorld)player.getWorld()).getHandle(), new BlockPosition(0, 0, 0)));
/* 133 */       this.checkReachable = false;
/* 134 */       setTitle(guiTitle);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void e() {
/* 140 */       Slot output = getSlot(2);
/* 141 */       if (!output.hasItem()) {
/* 142 */         output.set(getSlot(0).getItem().cloneItemStack());
/*     */       }
/*     */       
/* 145 */       this.levelCost.a(0);
/*     */ 
/*     */       
/* 148 */       c();
/*     */     }
/*     */ 
/*     */     
/*     */     public void b(EntityHuman entityhuman) {}
/*     */ 
/*     */     
/*     */     protected void a(EntityHuman entityhuman, World world, IInventory iinventory) {}
/*     */     
/*     */     public int getContainerId() {
/* 158 */       return this.windowId;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getRenameText() {
/* 163 */       return this.renameText;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void setRenameText(String text) {
/* 169 */       Slot inputLeft = getSlot(0);
/* 170 */       if (inputLeft.hasItem()) {
/* 171 */         inputLeft.getItem().a((IChatBaseComponent)new ChatComponentText(text));
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public Inventory getBukkitInventory() {
/* 177 */       return getBukkitView().getTopInventory();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\wesjd\anvilgui\version\Wrapper1_14_R1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */