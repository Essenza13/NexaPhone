/*    */ package me.dervinocap.originphone.listeners.emergency;
/*    */ 
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.EmergencyCall;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerChatEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EmergencyChatCall
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onChat(PlayerChatEvent event) {
/* 18 */     Player player = event.getPlayer();
/*    */     
/* 20 */     if (event.getMessage().equalsIgnoreCase("annulla")) {
/* 21 */       BasicsFunction.sendActionBar(player, "§cOperazione §lannullata.");
/* 22 */       EmergencyCall.callingPolice.remove(player.getUniqueId());
/* 23 */       EmergencyCall.callingAmbulance.remove(player.getUniqueId());
/* 24 */       EmergencyCall.callingVDF.remove(player.getUniqueId());
/* 25 */       event.setCancelled(true);
/*    */       
/*    */       return;
/*    */     } 
/* 29 */     if (EmergencyCall.callingPolice.contains(player.getUniqueId())) {
/*    */       
/* 31 */       BasicsFunction.sendEmergency(player, "realityphone.police", "Polizia", event.getMessage());
/* 32 */       player.performCommand("azione Sta chiamando la Polizia");
/*    */       
/* 34 */       event.setCancelled(true);
/*    */       
/* 36 */       EmergencyCall.callingPolice.remove(player.getUniqueId());
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 41 */     if (EmergencyCall.callingVDF.contains(player.getUniqueId())) {
/*    */       
/* 43 */       BasicsFunction.sendEmergency(player, "realityphone.vdf", "Vigili del Fuoco", event.getMessage());
/* 44 */       player.performCommand("azione Sta chiamando i Vigili del Fuoco");
/*    */       
/* 46 */       event.setCancelled(true);
/*    */       
/* 48 */       EmergencyCall.callingVDF.remove(player.getUniqueId());
/*    */       
/*    */       return;
/*    */     } 
/* 52 */     if (EmergencyCall.callingAmbulance.contains(player.getUniqueId())) {
/*    */       
/* 54 */       BasicsFunction.sendEmergency(player, "realityphone.ambulance", "Ospedale", event.getMessage());
/* 55 */       player.performCommand("azione Sta chiamando un Ambulanza");
/*    */       
/* 57 */       event.setCancelled(true);
/*    */       
/* 59 */       EmergencyCall.callingAmbulance.remove(player.getUniqueId());
/*    */       return;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\listeners\emergency\EmergencyChatCall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */