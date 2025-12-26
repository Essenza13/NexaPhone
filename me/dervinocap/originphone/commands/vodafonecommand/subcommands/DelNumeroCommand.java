/*    */ package me.dervinocap.originphone.commands.vodafonecommand.subcommands;
/*    */ 
/*    */ import me.dervinocap.originphone.commands.SubCommand;
/*    */ import me.dervinocap.originphone.database.NumberDatabase;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.customloader.PluginCustomLoader;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class DelNumeroCommand
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
/* 30 */     if (args.length < 1) {
/* 31 */       player.sendMessage(BasicsFunction.hex("&f &#F59F0BQuesto comando richiede ulteriori argomenti!"));
/*    */       
/*    */       return;
/*    */     } 
/* 35 */     if (!player.hasPermission("realityphone.rephone.delnumero")) {
/* 36 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Non possiedi abbastanza permessi per eseguire questo comando!"));
/*    */       
/*    */       return;
/*    */     } 
/* 40 */     Player target = Bukkit.getPlayerExact(args[1]);
/*    */     
/* 42 */     if (target == null) {
/* 43 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Il cittadino inserito non è stato trovato!"));
/*    */       
/*    */       return;
/*    */     } 
/* 47 */     if (!NumberDatabase.checkIfPlayerHasNumber(target)) {
/* 48 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Il cittadino non ha un numero di telefono!"));
/*    */       
/*    */       return;
/*    */     } 
/* 52 */     player.sendMessage(BasicsFunction.hex("&f &#84CC17Hai eliminato il numero di telefono a " + target.getName()));
/*    */     
/* 54 */     NumberDatabase.deletePhoneNumber(target);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\commands\vodafonecommand\subcommands\DelNumeroCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */