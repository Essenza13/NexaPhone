/*    */ package me.dervinocap.originphone.listeners.utilities;
/*    */ 
/*    */ import me.dervinocap.originphone.gui.app.EmergencyGUI;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerInteractAtEntityEvent;
/*    */ import org.bukkit.inventory.EquipmentSlot;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PhoneCabinUse
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void rightClick(PlayerInteractAtEntityEvent event) {
/* 18 */     if (!(event.getRightClicked() instanceof org.bukkit.entity.ArmorStand))
/*    */       return; 
/* 20 */     Player player = event.getPlayer();
/* 21 */     Entity entity = event.getRightClicked();
/*    */     
/* 23 */     if (event.getHand() != EquipmentSlot.HAND)
/*    */       return; 
/* 25 */     if (entity.getCustomName() == null)
/*    */       return; 
/* 27 */     if (!entity.getCustomName().equalsIgnoreCase("reality_cabina_phone"))
/*    */       return; 
/* 29 */     EmergencyGUI.open(player);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\listener\\utilities\PhoneCabinUse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */