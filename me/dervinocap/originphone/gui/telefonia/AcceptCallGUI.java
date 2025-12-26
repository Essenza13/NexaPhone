/*    */ package me.dervinocap.originphone.gui.telefonia;
/*    */ 
/*    */ import de.tr7zw.nbtapi.NBTItem;
/*    */ import dev.triumphteam.gui.builder.gui.SimpleBuilder;
/*    */ import dev.triumphteam.gui.builder.item.ItemBuilder;
/*    */ import dev.triumphteam.gui.guis.Gui;
/*    */ import dev.triumphteam.gui.guis.GuiItem;
/*    */ import java.util.Objects;
/*    */ import java.util.UUID;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.Call;
/*    */ import me.dervinocap.originphone.utils.Icon;
/*    */ import net.kyori.adventure.text.Component;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AcceptCallGUI
/*    */ {
/*    */   public static void open(Player player, NBTItem nbtItem) {
/* 28 */     Gui gui = ((SimpleBuilder)((SimpleBuilder)Gui.gui().title((Component)Component.text("§8§lAccetti?"))).rows(3)).create();
/*    */     
/* 30 */     gui.setDefaultClickAction(event -> event.setCancelled(true));
/*    */ 
/*    */ 
/*    */     
/* 34 */     GuiItem si = ((ItemBuilder)ItemBuilder.from(Icon.check()).setName("§aAccetta §lChiamata")).asGuiItem(event -> {
/*    */           if (Call.inCall.containsValue(player.getUniqueId()) || Call.inCall.containsKey(player.getUniqueId())) {
/*    */             return;
/*    */           }
/*    */           
/*    */           if (Call.calling.containsValue(player.getUniqueId())) {
/*    */             Player playerCalling = Bukkit.getPlayer(Objects.<UUID>requireNonNull((UUID)Call.calling.inverse().get(player.getUniqueId())));
/*    */             
/*    */             BasicsFunction.sendActionBar(playerCalling, "§eLa chiamata è §liniziata...");
/*    */             
/*    */             BasicsFunction.sendActionBar(player, "§eLa chiamata è §liniziata...");
/*    */             
/*    */             Call.inCall.put(playerCalling.getUniqueId(), player.getUniqueId());
/*    */             
/*    */             Call.calling.remove(playerCalling.getUniqueId(), player.getUniqueId());
/*    */             
/*    */             Call.callList.remove(playerCalling.getUniqueId());
/*    */             Call.callList.remove(player.getUniqueId());
/*    */           } 
/*    */           player.closeInventory();
/*    */         });
/* 55 */     GuiItem no = ((ItemBuilder)ItemBuilder.from(Icon.cross()).setName("§cRifiuta §lChiamata")).asGuiItem(event -> {
/*    */           if (Call.inCall.containsValue(player.getUniqueId()) || Call.inCall.containsKey(player.getUniqueId())) {
/*    */             return;
/*    */           }
/*    */           
/*    */           if (Call.calling.containsValue(player.getUniqueId())) {
/*    */             Player playerCall = Bukkit.getPlayer((UUID)Call.calling.inverse().get(player.getUniqueId()));
/*    */             
/*    */             BasicsFunction.sendActionBar(playerCall, "§eLa chiamata è stata §lrifiutata...");
/*    */             
/*    */             BasicsFunction.sendActionBar(player, "§eLa chiamata è stata §lrifiutata...");
/*    */             
/*    */             Call.calling.remove(playerCall.getUniqueId(), player.getUniqueId());
/*    */             
/*    */             Call.callList.remove(playerCall.getUniqueId());
/*    */             Call.callList.remove(player.getUniqueId());
/*    */           } 
/*    */         });
/* 73 */     gui.getFiller().fill(new GuiItem(ItemBuilder.from(Icon.filler()).asGuiItem().getItemStack()));
/*    */     
/* 75 */     gui.setItem(2, 4, si);
/* 76 */     gui.setItem(2, 6, no);
/*    */     
/* 78 */     gui.open((HumanEntity)player);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\gui\telefonia\AcceptCallGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */