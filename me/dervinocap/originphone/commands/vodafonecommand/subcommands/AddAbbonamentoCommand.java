/*    */ package me.dervinocap.originphone.commands.vodafonecommand.subcommands;
/*    */ 
/*    */ import me.dervinocap.originphone.commands.SubCommand;
/*    */ import me.dervinocap.originphone.database.NumberDatabase;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.config.ConfigManager;
/*    */ import me.dervinocap.originphone.utils.customloader.PluginCustomLoader;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class AddAbbonamentoCommand
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
/* 36 */     if (!player.hasPermission("realityphone.rephone")) {
/* 37 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Non possiedi abbastanza permessi per eseguire questo comando!"));
/*    */       
/*    */       return;
/*    */     } 
/* 41 */     Player target = Bukkit.getPlayerExact(args[1]);
/*    */     
/* 43 */     if (target == null) {
/* 44 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Il cittadino inserito non è stato trovato!"));
/*    */       
/*    */       return;
/*    */     } 
/* 48 */     if (!NumberDatabase.checkIfPlayerHasNumber(target)) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 53 */     String abbonamento = args[2];
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 58 */     if (abbonamento.equalsIgnoreCase("RED_PRO") || abbonamento.equalsIgnoreCase("RED_MAX") || abbonamento.equalsIgnoreCase("FAMILY") || abbonamento.equalsIgnoreCase("INFINITO")) {
/*    */       
/* 60 */       String formattedArgSMS = "ABBONAMENTI_" + args[2].toUpperCase() + "_SMS";
/* 61 */       String formattedArgMINUTES = "ABBONAMENTI_" + args[2].toUpperCase() + "_MINUTI";
/*    */       
/* 63 */       ConfigManager configManagerSMS = ConfigManager.valueOf(formattedArgSMS);
/* 64 */       ConfigManager configManagerMinutes = ConfigManager.valueOf(formattedArgMINUTES);
/*    */       
/* 66 */       NumberDatabase.setAbbonamento(target, args[2].toUpperCase());
/* 67 */       NumberDatabase.setSms(target, configManagerSMS.getInt());
/* 68 */       NumberDatabase.setMinutes(target, configManagerMinutes.getInt());
/*    */       
/* 70 */       player.sendMessage(BasicsFunction.hex("&f &#84CC17Hai aggiunto un abbonamento §7" + args[2] + " §aa §7" + target.getName()));
/*    */       
/* 72 */       target.sendMessage("");
/* 73 */       target.sendMessage(" §c§lREPHONE");
/* 74 */       target.sendMessage(" §7Il tuo abbonamento è stato aggiornato!");
/* 75 */       target.sendMessage(" §c" + args[2]);
/* 76 */       target.sendMessage(" §7§oGrazie per averci scelto!");
/* 77 */       target.sendMessage("");
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 82 */     player.sendMessage(BasicsFunction.hex("&f &#F59F0BAbbonamento non trovato! Abbonamenti disponibili: Red_Pro, Red_Max, Family, Infinito"));
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\commands\vodafonecommand\subcommands\AddAbbonamentoCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */