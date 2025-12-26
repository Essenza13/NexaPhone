/*    */ package me.dervinocap.originphone.listeners.utilities;
/*    */ 
/*    */ import de.tr7zw.nbtapi.NBTItem;
/*    */ import me.dervinocap.originphone.database.NumberDatabase;
/*    */ import me.dervinocap.originphone.gui.phone.Ipad;
/*    */ import me.dervinocap.originphone.gui.phone.Iphone;
/*    */ import me.dervinocap.originphone.gui.phone.Nokia;
/*    */ import me.dervinocap.originphone.gui.phone.Samsung;
/*    */ import me.dervinocap.originphone.gui.telefonia.AcceptCallGUI;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.Call;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.inventory.EquipmentSlot;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PhoneOpenGUI
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void openGUI(PlayerInteractEvent event) {
/* 27 */     Player player = event.getPlayer();
/*    */     
/* 29 */     if (event.getHand() != EquipmentSlot.HAND)
/*    */       return; 
/* 31 */     if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
/*    */       
/* 33 */       if (player.getInventory().getItemInMainHand() == null)
/* 34 */         return;  if (player.getInventory().getItemInMainHand().getType() == null)
/* 35 */         return;  if (player.getInventory().getItemInMainHand().getAmount() == 0)
/* 36 */         return;  if (player.getInventory().getItemInMainHand().getType() == Material.AIR)
/*    */         return; 
/* 38 */       if (!BasicsFunction.isPhone(player.getInventory().getItemInMainHand()))
/*    */         return; 
/* 40 */       NBTItem nbtItem = new NBTItem(player.getInventory().getItemInMainHand());
/*    */       
/* 42 */       if (!nbtItem.getString("Player").equalsIgnoreCase(player.getName())) {
/* 43 */         player.sendMessage(BasicsFunction.hex("&f &#DE2727Non puoi aprire un telefono non tuo!"));
/*    */         
/*    */         return;
/*    */       } 
/* 47 */       if (nbtItem.getInteger("Carica").intValue() == 0) {
/* 48 */         player.sendMessage(BasicsFunction.hex("&f &#DE2727Il telefono è scarico!"));
/*    */         
/*    */         return;
/*    */       } 
/* 52 */       if (Call.calling.containsValue(player.getUniqueId())) {
/* 53 */         AcceptCallGUI.open(player, nbtItem);
/*    */         
/*    */         return;
/*    */       } 
/* 57 */       if (!NumberDatabase.checkIfPlayerHasNumber(player)) {
/* 58 */         player.sendMessage(BasicsFunction.hex("&f &#DE2727Non hai un numero di telefono!"));
/*    */         
/*    */         return;
/*    */       } 
/* 62 */       if (nbtItem.getString("Phone").equalsIgnoreCase("Nokia")) {
/* 63 */         Nokia.open(player, nbtItem);
/*    */       }
/*    */       
/* 66 */       if (nbtItem.getString("Phone").equalsIgnoreCase("s22")) {
/* 67 */         Samsung.open(player, nbtItem);
/*    */       }
/*    */       
/* 70 */       if (nbtItem.getString("Phone").equalsIgnoreCase("iPadPro")) {
/* 71 */         Ipad.open(player, nbtItem);
/*    */       }
/*    */       
/* 74 */       if (nbtItem.getString("Phone").equalsIgnoreCase("iPhone14"))
/* 75 */         Iphone.open(player, nbtItem); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\listener\\utilities\PhoneOpenGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */