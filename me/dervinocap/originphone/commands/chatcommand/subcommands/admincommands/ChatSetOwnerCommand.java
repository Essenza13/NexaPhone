/*    */ package me.dervinocap.originphone.commands.chatcommand.subcommands.admincommands;
/*    */ 
/*    */ import me.dervinocap.originphone.commands.SubCommand;
/*    */ import me.dervinocap.originphone.database.ChatDatabase;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.customloader.PluginCustomLoader;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class ChatSetOwnerCommand
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
/* 35 */     if (!player.hasPermission("realityphone.chatadmin")) {
/* 36 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Non possiedi abbastanza permessi per eseguire questo comando!"));
/*    */       
/*    */       return;
/*    */     } 
/* 40 */     String chatName = args[1];
/* 41 */     Player target = Bukkit.getPlayerExact(args[2]);
/*    */     
/* 43 */     if (target == null) {
/* 44 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Il cittadino inserito non è stato trovato!"));
/*    */       
/*    */       return;
/*    */     } 
/* 48 */     if (!ChatDatabase.checkIfChatsExist(chatName)) {
/* 49 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727La chat inserità non esiste!"));
/*    */       
/*    */       return;
/*    */     } 
/* 53 */     player.sendMessage(BasicsFunction.hex("&f &#84CC17Hai impostato " + target.getName() + " nuovo owner della chat " + chatName));
/*    */     
/* 55 */     ChatDatabase.setOwner(chatName, target.getUniqueId());
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\commands\chatcommand\subcommands\admincommands\ChatSetOwnerCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */