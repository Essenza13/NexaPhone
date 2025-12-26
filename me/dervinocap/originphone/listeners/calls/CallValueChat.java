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
/*    */ public class CallValueChat
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onChat(AsyncPlayerChatEvent event) {
/* 20 */     Player player = event.getPlayer();
/*    */     
/* 22 */     if (!Call.inCall.containsKey(player.getUniqueId())) {
/*    */       return;
/*    */     }
/*    */     
/* 26 */     Player playerCalling = Bukkit.getPlayer(Objects.<UUID>requireNonNull((UUID)Call.inCall.get(player.getUniqueId())));
/*    */     
/* 28 */     if (Call.inCall.containsKey(player.getUniqueId()) && Call.inCall.containsValue(playerCalling.getUniqueId())) {
/*    */       
/* 30 */       if (ContactDatabase.getPlayerContactsUUID(playerCalling).contains(player.getUniqueId())) {
/* 31 */         playerCalling.sendMessage(BasicsFunction.hex("&#3DBFE9&l[Chiamata] &8• &7" + player.getName() + " &8> &f" + event.getMessage()));
/*    */       } else {
/* 33 */         playerCalling.sendMessage(BasicsFunction.hex("&#3DBFE9&l[Chiamata] &8• &7" + NumberDatabase.getPhoneNumberFormatted(player) + " &8> &f" + event.getMessage()));
/*    */       } 
/*    */       
/* 36 */       player.sendMessage(BasicsFunction.hex("&#3DBFE9&l[Chiamata] &8• &7" + player.getName() + " &8> &f" + event.getMessage()));
/* 37 */       event.setCancelled(true);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\listeners\calls\CallValueChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */