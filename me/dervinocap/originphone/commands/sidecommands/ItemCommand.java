/*    */ package me.dervinocap.originphone.commands.sidecommands;
/*    */ 
/*    */ import me.dervinocap.originphone.gui.ItemGUI;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class ItemCommand
/*    */   implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
/* 14 */     if (!(commandSender instanceof Player)) return true;
/*    */     
/* 16 */     Player player = (Player)commandSender;
/*    */     
/* 18 */     if (strings.length < 1) {
/* 19 */       player.sendMessage(BasicsFunction.hex("&f &#F59F0BQuesto comando richiede ulteriori argomenti!"));
/* 20 */       return true;
/*    */     } 
/*    */     
/* 23 */     if (!player.hasPermission("realityphone.items")) {
/* 24 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Non possiedi abbastanza permessi per eseguire questo comando!"));
/* 25 */       return true;
/*    */     } 
/*    */     
/* 28 */     if (!strings[0].equalsIgnoreCase("get")) return true;
/*    */     
/* 30 */     ItemGUI.open(player);
/*    */     
/* 32 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\commands\sidecommands\ItemCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */