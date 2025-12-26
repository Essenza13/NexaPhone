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
/*     */ import net.minecraft.world.inventory.ContainerAccess;
/*     */ import net.minecraft.world.inventory.Containers;
/*     */ import net.minecraft.world.inventory.Slot;
/*     */ import net.minecraft.world.level.World;
/*     */ import net.wesjd.anvilgui.version.special.AnvilContainer1_17_1_R1;
/*     */ import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
/*     */ import org.bukkit.craftbukkit.v1_17_R1.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ 
/*     */ public class Wrapper1_17_R1 implements VersionWrapper {
/*  23 */   private final boolean IS_ONE_SEVENTEEN_ONE = Bukkit.getBukkitVersion().contains("1.17.1");
/*     */   
/*     */   private int getRealNextContainerId(Player player) {
/*  26 */     return toNMS(player).nextContainerCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNextContainerId(Player player, VersionWrapper.AnvilContainerWrapper container) {
/*  34 */     if (this.IS_ONE_SEVENTEEN_ONE) {
/*  35 */       return ((AnvilContainer1_17_1_R1)container).getContainerId();
/*     */     }
/*  37 */     return ((AnvilContainer)container).getContainerId();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleInventoryCloseEvent(Player player) {
/*  45 */     CraftEventFactory.handleInventoryCloseEvent((EntityHuman)toNMS(player));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPacketOpenWindow(Player player, int containerId, Object guiTitle) {
/*  53 */     (toNMS(player)).b.sendPacket((Packet)new PacketPlayOutOpenWindow(containerId, Containers.h, (IChatBaseComponent)guiTitle));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPacketCloseWindow(Player player, int containerId) {
/*  62 */     (toNMS(player)).b.sendPacket((Packet)new PacketPlayOutCloseWindow(containerId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActiveContainerDefault(Player player) {
/*  70 */     (toNMS(player)).bV = (Container)(toNMS(player)).bU;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActiveContainer(Player player, VersionWrapper.AnvilContainerWrapper container) {
/*  78 */     (toNMS(player)).bV = (Container)container;
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
/*  94 */     toNMS(player).initMenu((Container)container);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VersionWrapper.AnvilContainerWrapper newContainerAnvil(Player player, Object guiTitle) {
/* 102 */     if (this.IS_ONE_SEVENTEEN_ONE) {
/* 103 */       return (VersionWrapper.AnvilContainerWrapper)new AnvilContainer1_17_1_R1(player, getRealNextContainerId(player), (IChatBaseComponent)guiTitle);
/*     */     }
/* 105 */     return new AnvilContainer(player, (IChatBaseComponent)guiTitle);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object literalChatComponent(String content) {
/* 110 */     return new ChatComponentText(content);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object jsonChatComponent(String json) {
/* 115 */     return IChatBaseComponent.ChatSerializer.a(json);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private EntityPlayer toNMS(Player player) {
/* 125 */     return ((CraftPlayer)player).getHandle();
/*     */   }
/*     */   
/*     */   private class AnvilContainer
/*     */     extends ContainerAnvil
/*     */     implements VersionWrapper.AnvilContainerWrapper
/*     */   {
/*     */     public AnvilContainer(Player player, IChatBaseComponent guiTitle) {
/* 133 */       super(Wrapper1_17_R1.this
/* 134 */           .getRealNextContainerId(player), ((CraftPlayer)player)
/* 135 */           .getHandle().getInventory(), 
/* 136 */           ContainerAccess.at((World)((CraftWorld)player.getWorld()).getHandle(), new BlockPosition(0, 0, 0)));
/* 137 */       this.checkReachable = false;
/* 138 */       setTitle(guiTitle);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void i() {
/* 144 */       Slot output = getSlot(2);
/* 145 */       if (!output.hasItem()) {
/* 146 */         output.set(getSlot(0).getItem().cloneItemStack());
/*     */       }
/*     */       
/* 149 */       this.w.set(0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 155 */       updateInventory();
/* 156 */       d();
/*     */     }
/*     */ 
/*     */     
/*     */     public void b(EntityHuman player) {}
/*     */ 
/*     */     
/*     */     protected void a(EntityHuman player, IInventory container) {}
/*     */     
/*     */     public int getContainerId() {
/* 166 */       return this.j;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getRenameText() {
/* 171 */       return this.v;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void setRenameText(String text) {
/* 177 */       Slot inputLeft = getSlot(0);
/* 178 */       if (inputLeft.hasItem()) {
/* 179 */         inputLeft.getItem().a((IChatBaseComponent)new ChatComponentText(text));
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public Inventory getBukkitInventory() {
/* 185 */       return getBukkitView().getTopInventory();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\wesjd\anvilgui\version\Wrapper1_17_R1.class
 * Java compiler version: 16 (60.0)
 * JD-Core Version:       1.1.3
 */