/*    */ package me.dervinocap.originphone.listeners.utilities;
/*    */ 
/*    */ import me.dervinocap.originphone.commands.chatcommand.subcommands.ChatCommand;
/*    */ import me.dervinocap.originphone.database.ChatDatabase;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.AsyncPlayerChatEvent;
/*    */ 
/*    */ public class WriteInChat
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onChat(AsyncPlayerChatEvent event) {
/* 16 */     Player player = event.getPlayer();
/*    */     
/* 18 */     if (!ChatCommand.toggleChat.containsKey(player.getUniqueId()))
/*    */       return; 
/* 20 */     String chatName = (String)ChatCommand.toggleChat.get(player.getUniqueId());
/*    */     
/* 22 */     if (ChatDatabase.checkChatOwner(chatName).equalsIgnoreCase(player.getUniqueId().toString()) || ChatDatabase.getChatMembers(chatName).contains(player.getUniqueId()) || player.hasPermission("realityphone.chat." + chatName) || player.hasPermission("realityphone.chatadmin")) {
/*    */       
/* 24 */       BasicsFunction.sendChatMessage(player, chatName, event.getMessage());
/* 25 */       event.setCancelled(true);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\listener\\utilities\WriteInChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */