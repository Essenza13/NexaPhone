/*    */ package me.dervinocap.originphone.commands.chatcommand.subcommands.admincommands;
/*    */ 
/*    */ import me.dervinocap.originphone.commands.SubCommand;
/*    */ import me.dervinocap.originphone.database.ChatDatabase;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.customloader.PluginCustomLoader;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class ChatCreateCommand
/*    */   extends SubCommand
/*    */ {
/*    */   public boolean isPlayerOnly() {
/* 14 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermission() {
/* 19 */     return null;
/*    */   }
/*    */   
/* 22 */   private final PluginCustomLoader customLoader = PluginCustomLoader.getInstance();
/*    */   
/*    */   public String allArgs(int start, String[] args) {
/* 25 */     String temp = "";
/* 26 */     for (int i = start; i < args.length; i++) {
/* 27 */       temp = temp + args[i] + " ";
/*    */     }
/* 29 */     return temp.trim();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 35 */     Player player = (Player)sender;
/*    */     
/* 37 */     if (args.length < 3) {
/* 38 */       player.sendMessage(BasicsFunction.hex("&f &#F59F0BQuesto comando richiede ulteriori argomenti!"));
/*    */       
/*    */       return;
/*    */     } 
/* 42 */     if (!player.hasPermission("realityphone.chatadmin")) {
/* 43 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Non possiedi abbastanza permessi per eseguire questo comando!"));
/*    */       
/*    */       return;
/*    */     } 
/* 47 */     String chatName = args[1];
/* 48 */     String chatPrefix = allArgs(2, args);
/*    */     
/* 50 */     if (ChatDatabase.checkIfChatsExist(chatName)) {
/* 51 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727La chat inserità è già esistente!"));
/*    */       
/*    */       return;
/*    */     } 
/* 55 */     player.sendMessage(BasicsFunction.hex("&f &#84CC17Hai creato con successo la chat " + chatName));
/* 56 */     ChatDatabase.createChat(chatName, player.getUniqueId(), chatPrefix);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\commands\chatcommand\subcommands\admincommands\ChatCreateCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */