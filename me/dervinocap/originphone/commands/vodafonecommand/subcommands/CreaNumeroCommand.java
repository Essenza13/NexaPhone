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
/*    */ public class CreaNumeroCommand
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
/* 30 */     if (args.length < 2) {
/* 31 */       player.sendMessage(BasicsFunction.hex("&f &#F59F0BQuesto comando richiede ulteriori argomenti!"));
/*    */       
/*    */       return;
/*    */     } 
/* 35 */     if (!player.hasPermission("realityphone.rephone")) {
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
/* 47 */     NumberDatabase.createPhoneNumber(target);
/*    */     
/* 49 */     player.sendMessage(BasicsFunction.hex("&f &#84CC17Hai creato il numero a " + target.getName()));
/*    */     
/* 51 */     target.sendMessage("");
/* 52 */     target.sendMessage(" §cBenvenuto in §lRePhone");
/* 53 */     target.sendMessage(" §7Il tuo nuovo numero di telefono");
/* 54 */     target.sendMessage(" §7è stato registrato con successo!");
/* 55 */     target.sendMessage(" ");
/* 56 */     target.sendMessage(" §7Il tuo numero è ora §c" + NumberDatabase.getPhoneNumberFormatted(target));
/* 57 */     target.sendMessage("");
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\commands\vodafonecommand\subcommands\CreaNumeroCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */