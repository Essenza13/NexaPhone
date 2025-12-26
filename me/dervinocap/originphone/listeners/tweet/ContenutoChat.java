/*    */ package me.dervinocap.originphone.listeners.tweet;
/*    */ 
/*    */ import me.dervinocap.originphone.RealityPhone;
/*    */ import me.dervinocap.originphone.database.TwitterDatabase;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.Tweets;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.AsyncPlayerChatEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class ContenutoChat
/*    */   implements Listener {
/*    */   @EventHandler
/*    */   public void onChat(AsyncPlayerChatEvent event) {
/* 18 */     final Player player = event.getPlayer();
/*    */     
/* 20 */     if (!Tweets.sendingContenuto.contains(player.getUniqueId())) {
/*    */       return;
/*    */     }
/*    */     
/* 24 */     if (event.getMessage().equalsIgnoreCase("annulla")) {
/* 25 */       BasicsFunction.sendActionBar(player, "§cOperazione §lannullata.");
/* 26 */       Tweets.contenuto.remove(player.getUniqueId());
/* 27 */       Tweets.titolo.remove(player.getUniqueId());
/* 28 */       Tweets.sendingContenuto.remove(player.getUniqueId());
/* 29 */       Tweets.sendingTitolo.remove(player.getUniqueId());
/* 30 */       event.setCancelled(true);
/*    */       
/*    */       return;
/*    */     } 
/* 34 */     Tweets.contenuto.put(player.getUniqueId(), event.getMessage());
/* 35 */     Tweets.sendingContenuto.remove(player.getUniqueId());
/*    */     
/* 37 */     int id = BasicsFunction.getNextId();
/*    */     
/* 39 */     TwitterDatabase.createTweet((String)Tweets.titolo.get(player.getUniqueId()), (String)Tweets.contenuto.get(player.getUniqueId()), id, player);
/*    */     
/* 41 */     Tweets.contenuto.remove(player.getUniqueId());
/* 42 */     Tweets.titolo.remove(player.getUniqueId());
/* 43 */     Tweets.sendingContenuto.remove(player.getUniqueId());
/* 44 */     Tweets.sendingTitolo.remove(player.getUniqueId());
/*    */     
/* 46 */     Tweets.cooldown.add(player.getUniqueId());
/*    */     
/* 48 */     Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin)RealityPhone.getInstance(), new Runnable()
/*    */         {
/*    */           public void run() {
/* 51 */             Tweets.cooldown.remove(player.getUniqueId());
/*    */           }
/*    */         },  6000L);
/*    */     
/* 55 */     for (Player op : Bukkit.getOnlinePlayers()) {
/*    */       
/* 57 */       if (BasicsFunction.hasPhone(op)) {
/* 58 */         op.sendMessage(BasicsFunction.hex("&b&lTwitter &8» &7&oHai nuove notifiche..."));
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 63 */     event.setCancelled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\listeners\tweet\ContenutoChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */