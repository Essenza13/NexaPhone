/*    */ package me.dervinocap.originphone.listeners.sms;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import java.util.UUID;
/*    */ import me.dervinocap.originphone.database.ContactDatabase;
/*    */ import me.dervinocap.originphone.database.NumberDatabase;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.MSG;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.AsyncPlayerChatEvent;
/*    */ 
/*    */ public class SendMessageChat
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onChat(AsyncPlayerChatEvent event) {
/* 20 */     Player player = event.getPlayer();
/*    */     
/* 22 */     if (!MSG.sendingMessage.containsKey(player.getUniqueId())) {
/*    */       return;
/*    */     }
/*    */     
/* 26 */     Player playerCalling = Bukkit.getPlayer(Objects.<UUID>requireNonNull((UUID)MSG.sendingMessage.get(player.getUniqueId())));
/*    */     
/* 28 */     if (playerCalling == null)
/*    */       return; 
/* 30 */     if (event.getMessage().equalsIgnoreCase("annulla")) {
/* 31 */       BasicsFunction.sendActionBar(player, "§cOperazione §lannullata.");
/* 32 */       MSG.sendingMessage.remove(player.getUniqueId(), playerCalling.getUniqueId());
/* 33 */       MSG.sendingMessage.remove(playerCalling.getUniqueId(), player.getUniqueId());
/* 34 */       event.setCancelled(true);
/*    */       
/*    */       return;
/*    */     } 
/* 38 */     if (ContactDatabase.getPlayerContactsUUID(playerCalling).contains(player.getUniqueId())) {
/* 39 */       playerCalling.sendMessage(BasicsFunction.hex("&#3DBFE9&l[SMS] &8• &7" + player.getName() + " &8> &f" + event.getMessage()));
/*    */     } else {
/* 41 */       playerCalling.sendMessage(BasicsFunction.hex("&#3DBFE9&l[SMS] &8• &7" + NumberDatabase.getPhoneNumberFormatted(player) + " &8> &f" + event.getMessage()));
/*    */     } 
/*    */     
/* 44 */     player.sendMessage(BasicsFunction.hex("&#3DBFE9&l[SMS] &8• &7" + player.getName() + " &8> &f" + event.getMessage()));
/*    */     
/* 46 */     NumberDatabase.setSms(player, NumberDatabase.getSms(player).intValue() - 1);
/*    */     
/* 48 */     MSG.sendingMessage.remove(player.getUniqueId(), playerCalling.getUniqueId());
/* 49 */     MSG.sendingMessage.remove(playerCalling.getUniqueId(), player.getUniqueId());
/*    */     
/* 51 */     event.setCancelled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\listeners\sms\SendMessageChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */