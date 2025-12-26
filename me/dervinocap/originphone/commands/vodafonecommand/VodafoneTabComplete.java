/*    */ package me.dervinocap.originphone.commands.vodafonecommand;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.command.TabCompleter;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.util.StringUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VodafoneTabComplete
/*    */   implements TabCompleter
/*    */ {
/* 19 */   public static List<String> subCommandsString = new ArrayList<>();
/* 20 */   public static List<String> names = new ArrayList<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
/* 25 */     if (args.length == 1) {
/* 26 */       subCommandsString.addAll(VodafoneMainCommand.subCommands.keySet());
/* 27 */       return (List<String>)StringUtil.copyPartialMatches(args[0], subCommandsString, new ArrayList());
/* 28 */     }  if (args.length == 3) {
/* 29 */       return (List<String>)StringUtil.copyPartialMatches(args[2], Arrays.asList(new String[] { "red_pro", "red_max", "family", "infinito" }, ), new ArrayList());
/*    */     }
/* 31 */     for (Player player1 : Bukkit.getOnlinePlayers()) names.add(player1.getName()); 
/* 32 */     return names;
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\commands\vodafonecommand\VodafoneTabComplete.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */