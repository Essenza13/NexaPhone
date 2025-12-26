/*    */ package me.dervinocap.originphone.commands.chatcommand.subcommands.admincommands;
/*    */ 
/*    */ import me.dervinocap.originphone.commands.SubCommand;
/*    */ import me.dervinocap.originphone.database.ChatDatabase;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.customloader.PluginCustomLoader;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class ChatDeleteCommand
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
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 27 */     Player player = (Player)sender;
/*    */     
/* 29 */     if (args.length < 2) {
/* 30 */       player.sendMessage(BasicsFunction.hex("&f &#F59F0BQuesto comando richiede ulteriori argomenti!"));
/*    */       
/*    */       return;
/*    */     } 
/* 34 */     if (!player.hasPermission("realityphone.chatadmin")) {
/* 35 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Non possiedi abbastanza permessi per eseguire questo comando!"));
/*    */       
/*    */       return;
/*    */     } 
/* 39 */     String chatName = args[1];
/*    */     
/* 41 */     if (!ChatDatabase.checkIfChatsExist(chatName)) {
/* 42 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727La chat inserità non esiste!"));
/*    */       return;
/*    */     } 
/* 45 */     player.sendMessage(BasicsFunction.hex("&f &#84CC17Hai eliminato con successo la chat " + chatName));
/* 46 */     ChatDatabase.removeChat(chatName);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\commands\chatcommand\subcommands\admincommands\ChatDeleteCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */