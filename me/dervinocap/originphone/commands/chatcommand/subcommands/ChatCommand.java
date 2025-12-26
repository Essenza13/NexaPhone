/*    */ package me.dervinocap.originphone.commands.chatcommand.subcommands;
/*    */ 
/*    */ import com.google.common.collect.HashBiMap;
/*    */ import it.realityrp.realitygeneral.database.ImpattoSilenziosoDB;
/*    */ import java.util.UUID;
/*    */ import me.dervinocap.originphone.database.ChatDatabase;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatCommand
/*    */   implements CommandExecutor
/*    */ {
/*    */   public String allArgs(int start, String[] args) {
/* 23 */     String temp = "";
/* 24 */     for (int i = start; i < args.length; i++) {
/* 25 */       temp = temp + args[i] + " ";
/*    */     }
/* 27 */     return temp.trim();
/*    */   }
/*    */   
/* 30 */   public static HashBiMap<UUID, String> toggleChat = HashBiMap.create();
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
/* 35 */     if (!(commandSender instanceof Player)) return true;
/*    */     
/* 37 */     Player player = (Player)commandSender;
/*    */     
/* 39 */     if (strings.length < 1) {
/* 40 */       player.sendMessage(BasicsFunction.hex("&f &#F59F0BQuesto comando richiede ulteriori argomenti!"));
/* 41 */       return true;
/*    */     } 
/*    */     
/* 44 */     if (ImpattoSilenziosoDB.checkIfEnabled()) {
/* 45 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Segnale non trovato, riprova piu' tardi."));
/* 46 */       return true;
/*    */     } 
/*    */     
/* 49 */     String chatName = strings[0];
/* 50 */     String message = allArgs(1, strings);
/*    */     
/* 52 */     if (!ChatDatabase.checkIfChatsExist(chatName)) {
/* 53 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727La chat inserità non esiste!"));
/* 54 */       return true;
/*    */     } 
/*    */     
/* 57 */     if (!ChatDatabase.checkChatOwner(chatName).equalsIgnoreCase(player.getUniqueId().toString()) && !ChatDatabase.getChatMembers(chatName).contains(player.getUniqueId()) && !player.hasPermission("realityphone.chat." + chatName) && !player.hasPermission("realityphone.chatadmin")) {
/* 58 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Non fai parte di questa chat!"));
/* 59 */       return true;
/*    */     } 
/*    */     
/* 62 */     if (!BasicsFunction.hasPhone(player) && !player.hasPermission("realityphone.chatadmin")) {
/* 63 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Non hai un telefono, quindi non puoi scrivere in questa chat."));
/* 64 */       return true;
/*    */     } 
/*    */     
/* 67 */     if (message == "" && !toggleChat.containsKey(player.getUniqueId())) {
/* 68 */       BasicsFunction.sendActionBar(player, "§aDa ora scriverai nella chat §l" + chatName);
/* 69 */       toggleChat.put(player.getUniqueId(), chatName);
/* 70 */       return true;
/* 71 */     }  if (message == "" && toggleChat.containsKey(player.getUniqueId())) {
/* 72 */       BasicsFunction.sendActionBar(player, "§aDa ora scriverai nella chat §lglobale");
/* 73 */       toggleChat.remove(player.getUniqueId());
/* 74 */       return true;
/*    */     } 
/*    */     
/* 77 */     if (ChatDatabase.checkChatOwner(chatName).equalsIgnoreCase(player.getUniqueId().toString()) || ChatDatabase.getChatMembers(chatName).contains(player.getUniqueId()) || player.hasPermission("realityphone.chat." + chatName) || player.hasPermission("realityphone.chatadmin")) {
/*    */       
/* 79 */       BasicsFunction.sendChatMessage(player, chatName, message);
/*    */     } else {
/* 81 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Non possiedi abbastanza permessi per eseguire questo comando!"));
/*    */     } 
/* 83 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\commands\chatcommand\subcommands\ChatCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */