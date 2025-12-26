/*    */ package me.dervinocap.originphone.commands;
/*    */ 
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CMDExecutionValidator
/*    */ {
/*    */   public static boolean validate(SubCommand command, CommandSender sender) {
/* 10 */     if (command.isPlayerOnly() && !(sender instanceof org.bukkit.entity.Player)) {
/* 11 */       sender.sendMessage("§cOnly player can write this command!");
/* 12 */       return false;
/*    */     } 
/*    */     
/* 15 */     if (command.getPermission() != null && !sender.hasPermission(command.getPermission()) && !sender.hasPermission("*") && sender.isOp() && !sender.hasPermission("mmask.*")) {
/* 16 */       sender.sendMessage("§8§l(§9§lTelefono§8§l) §7Permessi insufficienti");
/* 17 */       return false;
/*    */     } 
/*    */     
/* 20 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\commands\CMDExecutionValidator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */