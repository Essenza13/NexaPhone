/*    */ package me.dervinocap.originphone.commands.chatcommand.subcommands;
/*    */ 
/*    */ import me.dervinocap.originphone.commands.SubCommand;
/*    */ import me.dervinocap.originphone.database.ChatDatabase;
/*    */ import me.dervinocap.originphone.database.ChatSymDatabase;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.customloader.PluginCustomLoader;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class ChatSetSymCommand
/*    */   extends SubCommand
/*    */ {
/*    */   public boolean isPlayerOnly() {
/* 16 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermission() {
/* 21 */     return null;
/*    */   }
/*    */   
/* 24 */   private final PluginCustomLoader customLoader = PluginCustomLoader.getInstance();
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 29 */     Player player = (Player)sender;
/*    */     
/* 31 */     if (args.length < 3) {
/* 32 */       player.sendMessage(BasicsFunction.hex("&f &#F59F0BQuesto comando richiede ulteriori argomenti!"));
/*    */       
/*    */       return;
/*    */     } 
/* 36 */     String chatName = args[1];
/* 37 */     String chatSym = args[2];
/*    */     
/* 39 */     if (!ChatDatabase.checkIfChatsExist(chatName)) {
/* 40 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727La chat inserità non esiste!"));
/*    */       
/*    */       return;
/*    */     } 
/* 44 */     if (!ChatDatabase.getChatMembers(chatName).contains(player.getUniqueId())) {
/* 45 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Non fai parte della chat inserita!"));
/*    */       
/*    */       return;
/*    */     } 
/* 49 */     ChatSymDatabase.setSym(player, chatName, chatSym);
/* 50 */     player.sendMessage(BasicsFunction.hex("&f &#84CC17Hai impostato come simbolo della chat " + chatName + " " + chatSym));
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\commands\chatcommand\subcommands\ChatSetSymCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */