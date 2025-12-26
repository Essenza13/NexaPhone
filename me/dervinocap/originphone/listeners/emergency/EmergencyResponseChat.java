/*    */ package me.dervinocap.originphone.listeners.emergency;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import me.dervinocap.originphone.commands.sidecommands.RispondiCommand;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerChatEvent;
/*    */ 
/*    */ public class EmergencyResponseChat
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void chat(PlayerChatEvent event) {
/* 17 */     Player player = event.getPlayer();
/*    */     
/* 19 */     if (!RispondiCommand.playerHashMap.containsKey(player.getUniqueId()))
/*    */       return; 
/* 21 */     event.setCancelled(true);
/*    */     
/* 23 */     String color = "&f";
/*    */     
/* 25 */     if (((String)RispondiCommand.emergenzaHashMap.get(player.getUniqueId())).equalsIgnoreCase("Ambulanza")) {
/* 26 */       color = "&c";
/* 27 */     } else if (((String)RispondiCommand.emergenzaHashMap.get(player.getUniqueId())).equalsIgnoreCase("Polizia")) {
/* 28 */       color = "&3";
/* 29 */     } else if (((String)RispondiCommand.emergenzaHashMap.get(player.getUniqueId())).equalsIgnoreCase("VDF")) {
/* 30 */       color = "&4";
/*    */     } 
/*    */     
/* 33 */     if (event.getMessage().equalsIgnoreCase("Annulla")) {
/* 34 */       BasicsFunction.sendActionBar(player, BasicsFunction.hex("&cOperazione &lannullata..."));
/*    */       
/*    */       return;
/*    */     } 
/* 38 */     Player target = Bukkit.getPlayer((UUID)RispondiCommand.playerHashMap.get(player.getUniqueId()));
/* 39 */     player.sendMessage(BasicsFunction.hex(color + "[Emergenza] &8• &7" + player.getName() + " &8» &f" + event.getMessage()));
/* 40 */     assert target != null;
/* 41 */     target.sendMessage(BasicsFunction.hex(color + "[Emergenza] &8• &7" + player.getName() + " &8» &f" + event.getMessage()));
/*    */     
/* 43 */     RispondiCommand.playerHashMap.remove(player.getUniqueId());
/* 44 */     RispondiCommand.emergenzaHashMap.remove(player.getUniqueId());
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\listeners\emergency\EmergencyResponseChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */