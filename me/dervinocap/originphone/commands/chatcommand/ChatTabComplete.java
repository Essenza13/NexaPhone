/*    */ package me.dervinocap.originphone.commands.chatcommand;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import me.dervinocap.originphone.database.ChatDatabase;
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
/*    */ 
/*    */ 
/*    */ public class ChatTabComplete
/*    */   implements TabCompleter
/*    */ {
/* 21 */   public static List<String> names = new ArrayList<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
/* 26 */     Player player = (Player)sender;
/*    */     
/* 28 */     if (args.length == 1)
/* 29 */       return (List<String>)StringUtil.copyPartialMatches(args[0], ChatDatabase.getPlayerChats(player.getUniqueId()), new ArrayList()); 
/* 30 */     if (args.length == 2) {
/* 31 */       return (List<String>)StringUtil.copyPartialMatches(args[1], ChatDatabase.getPlayerChats(player.getUniqueId()), new ArrayList());
/*    */     }
/*    */     
/* 34 */     for (Player player1 : Bukkit.getOnlinePlayers()) names.add(player1.getName()); 
/* 35 */     return names;
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\commands\chatcommand\ChatTabComplete.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */