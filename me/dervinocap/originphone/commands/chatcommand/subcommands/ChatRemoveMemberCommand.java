/*    */ package me.dervinocap.originphone.commands.chatcommand.subcommands;
/*    */ 
/*    */ import me.dervinocap.originphone.commands.SubCommand;
/*    */ import me.dervinocap.originphone.database.ChatDatabase;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.customloader.PluginCustomLoader;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class ChatRemoveMemberCommand
/*    */   extends SubCommand
/*    */ {
/*    */   public boolean isPlayerOnly() {
/* 15 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermission() {
/* 20 */     return null;
/*    */   }
/*    */   
/* 23 */   private final PluginCustomLoader customLoader = PluginCustomLoader.getInstance();
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 28 */     Player player = (Player)sender;
/*    */     
/* 30 */     if (args.length < 3) {
/* 31 */       player.sendMessage(BasicsFunction.hex("&f &#F59F0BQuesto comando richiede ulteriori argomenti!"));
/*    */       
/*    */       return;
/*    */     } 
/* 35 */     String chatName = args[1].toLowerCase();
/* 36 */     Player target = Bukkit.getPlayerExact(args[2]);
/*    */     
/* 38 */     if (target == null) {
/* 39 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Il cittadino inserito non è stato trovato!"));
/*    */       
/*    */       return;
/*    */     } 
/* 43 */     if (target == player)
/*    */       return; 
/* 45 */     if (!ChatDatabase.checkIfChatsExist(chatName)) {
/* 46 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727La chat inserità non esiste!"));
/*    */       
/*    */       return;
/*    */     } 
/* 50 */     if (!ChatDatabase.getChatMembers(chatName).contains(target.getUniqueId())) {
/* 51 */       player.sendMessage(BasicsFunction.hex("&f &#F59F0BIl cittadino inserito non fa parte della chat!"));
/*    */       
/*    */       return;
/*    */     } 
/* 55 */     if (ChatDatabase.checkChatOwner(chatName).equalsIgnoreCase(player.getUniqueId().toString()) || player.hasPermission("realityphone.chatadmin")) {
/*    */       
/* 57 */       ChatDatabase.removeChatMember(chatName, target.getUniqueId());
/* 58 */       player.sendMessage(BasicsFunction.hex("&f &#84CC17Hai rimosso " + target.getName() + " dalla chat " + chatName));
/*    */     } else {
/*    */       
/* 61 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Non possiedi abbastanza permessi per eseguire questo comando!"));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\commands\chatcommand\subcommands\ChatRemoveMemberCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */