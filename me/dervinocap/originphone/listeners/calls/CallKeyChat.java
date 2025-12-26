/*    */ package me.dervinocap.originphone.listeners.calls;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import java.util.UUID;
/*    */ import me.dervinocap.originphone.database.ContactDatabase;
/*    */ import me.dervinocap.originphone.database.NumberDatabase;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.Call;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.AsyncPlayerChatEvent;
/*    */ 
/*    */ public class CallKeyChat
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onChat(AsyncPlayerChatEvent event) {
/* 20 */     Player player = event.getPlayer();
/*    */     
/* 22 */     if (!Call.inCall.containsValue(player.getUniqueId())) {
/*    */       return;
/*    */     }
/*    */     
/* 26 */     Player playerCalling = Bukkit.getPlayer(Objects.<UUID>requireNonNull((UUID)Call.inCall.inverse().get(player.getUniqueId())));
/*    */     
/* 28 */     if (playerCalling == null)
/*    */       return; 
/* 30 */     if (Call.inCall.containsKey(playerCalling.getUniqueId()) && Call.inCall.containsValue(player.getUniqueId())) {
/*    */       
/* 32 */       if (ContactDatabase.getPlayerContactsUUID(playerCalling).contains(player.getUniqueId())) {
/* 33 */         playerCalling.sendMessage(BasicsFunction.hex("&#3DBFE9&l[Chiamata] &8• &7" + player.getName() + " &8> &f" + event.getMessage()));
/*    */       } else {
/* 35 */         playerCalling.sendMessage(BasicsFunction.hex("&#3DBFE9&l[Chiamata] &8• &7" + NumberDatabase.getPhoneNumberFormatted(player) + " &8> &f" + event.getMessage()));
/*    */       } 
/*    */       
/* 38 */       player.sendMessage(BasicsFunction.hex("&#3DBFE9&l[Chiamata] &8• &7" + player.getName() + " &8> &f" + event.getMessage()));
/*    */       
/* 40 */       event.setCancelled(true);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\listeners\calls\CallKeyChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */