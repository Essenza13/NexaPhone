/*    */ package me.dervinocap.originphone.listeners.utilities;
/*    */ 
/*    */ import java.util.concurrent.atomic.AtomicReference;
/*    */ import me.dervinocap.originphone.database.ChatDatabase;
/*    */ import me.dervinocap.originphone.database.ChatSymDatabase;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.AsyncPlayerChatEvent;
/*    */ 
/*    */ 
/*    */ public class ChatSym
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onChat(AsyncPlayerChatEvent event) {
/* 18 */     Player player = event.getPlayer();
/*    */     
/* 20 */     AtomicReference<String> chatName = new AtomicReference<>("NULL");
/*    */     
/* 22 */     ChatDatabase.getPlayerChats(player.getUniqueId()).forEach(s -> {
/*    */           if (ChatSymDatabase.getSym(player, s) == null) {
/*    */             return;
/*    */           }
/*    */           if (!event.getMessage().startsWith(ChatSymDatabase.getSym(player, s))) {
/*    */             return;
/*    */           }
/*    */           chatName.set(s);
/*    */         });
/* 31 */     if (chatName.get() == null) {
/* 32 */       player.sendMessage(BasicsFunction.hex("&cSi è verificato un errore! Segnalalo a @Dervone"));
/*    */       
/*    */       return;
/*    */     } 
/* 36 */     if (((String)chatName.get()).equalsIgnoreCase("NULL"))
/*    */       return; 
/* 38 */     if (!event.getMessage().startsWith(ChatSymDatabase.getSym(player, chatName.get())))
/*    */       return; 
/* 40 */     if (ChatDatabase.checkChatOwner(chatName.get()).equalsIgnoreCase(player.getUniqueId().toString()) || ChatDatabase.getChatMembers(chatName.get()).contains(player.getUniqueId()) || player.hasPermission("realityphone.chat." + chatName) || player.hasPermission("realityphone.chatadmin")) {
/*    */       
/* 42 */       BasicsFunction.sendChatMessage(player, chatName.get(), event.getMessage().substring(1));
/* 43 */       event.setCancelled(true);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\listener\\utilities\ChatSym.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */