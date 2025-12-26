/*    */ package me.dervinocap.originphone.commands.chatcommand.subcommands;
/*    */ 
/*    */ import me.dervinocap.originphone.commands.SubCommand;
/*    */ import me.dervinocap.originphone.database.ChatDatabase;
/*    */ import me.dervinocap.originphone.database.ChatNamesDatabase;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.customloader.PluginCustomLoader;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class ChatSetNameCommand
/*    */   extends SubCommand
/*    */ {
/*    */   public boolean isPlayerOnly() {
/* 17 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermission() {
/* 22 */     return null;
/*    */   }
/*    */   
/* 25 */   private final PluginCustomLoader customLoader = PluginCustomLoader.getInstance();
/*    */   
/*    */   public String allArgs(int start, String[] args) {
/* 28 */     String temp = "";
/* 29 */     for (int i = start; i < args.length; i++) {
/* 30 */       temp = temp + args[i] + " ";
/*    */     }
/* 32 */     return temp.trim();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 38 */     Player player = (Player)sender;
/*    */     
/* 40 */     if (args.length < 4) {
/* 41 */       player.sendMessage(BasicsFunction.hex("&f &#F59F0BQuesto comando richiede ulteriori argomenti!"));
/*    */       
/*    */       return;
/*    */     } 
/* 45 */     String chatName = args[1];
/* 46 */     Player target = Bukkit.getPlayerExact(args[2]);
/* 47 */     String chatNamePlayer = allArgs(3, args);
/*    */     
/* 49 */     if (target == null) {
/* 50 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Cittadino non trovato!"));
/*    */       
/*    */       return;
/*    */     } 
/* 54 */     if (!ChatDatabase.checkIfChatsExist(chatName)) {
/* 55 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727La chat inserità non esiste!"));
/*    */       
/*    */       return;
/*    */     } 
/* 59 */     if (!ChatDatabase.getChatMembers(chatName).contains(player.getUniqueId())) {
/* 60 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Non fai parte della chat inserita!"));
/*    */       
/*    */       return;
/*    */     } 
/* 64 */     ChatNamesDatabase.setName(target, chatName, BasicsFunction.hex(chatNamePlayer));
/* 65 */     player.sendMessage(BasicsFunction.hex("&f &#84CC17Hai impostato come nome del cittadino a " + chatNamePlayer + "!"));
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\commands\chatcommand\subcommands\ChatSetNameCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */