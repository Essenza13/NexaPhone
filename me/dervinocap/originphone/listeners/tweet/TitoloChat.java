/*    */ package me.dervinocap.originphone.listeners.tweet;
/*    */ 
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.Tweets;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.AsyncPlayerChatEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TitoloChat
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onChat(AsyncPlayerChatEvent event) {
/* 21 */     Player player = event.getPlayer();
/*    */     
/* 23 */     if (!Tweets.sendingTitolo.contains(player.getUniqueId())) {
/*    */       return;
/*    */     }
/*    */     
/* 27 */     if (event.getMessage().equalsIgnoreCase("annulla")) {
/* 28 */       BasicsFunction.sendActionBar(player, "§cOperazione §lannullata.");
/* 29 */       Tweets.contenuto.remove(player.getUniqueId());
/* 30 */       Tweets.titolo.remove(player.getUniqueId());
/* 31 */       Tweets.sendingContenuto.remove(player.getUniqueId());
/* 32 */       Tweets.sendingTitolo.remove(player.getUniqueId());
/* 33 */       event.setCancelled(true);
/*    */       
/*    */       return;
/*    */     } 
/* 37 */     Tweets.titolo.put(player.getUniqueId(), event.getMessage());
/* 38 */     Tweets.sendingTitolo.remove(player.getUniqueId());
/*    */     
/* 40 */     Tweets.sendingContenuto.add(player.getUniqueId());
/* 41 */     player.sendMessage(BasicsFunction.hex("&f &#84CC17Scrivi in chat il contenuto del post, scrivi &7'Annulla'&#84CC17 per annullare."));
/*    */     
/* 43 */     event.setCancelled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\listeners\tweet\TitoloChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */