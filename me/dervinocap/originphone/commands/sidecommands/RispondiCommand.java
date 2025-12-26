/*    */ package me.dervinocap.originphone.commands.sidecommands;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.UUID;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class RispondiCommand
/*    */   implements CommandExecutor
/*    */ {
/* 17 */   public static HashMap<UUID, UUID> playerHashMap = new HashMap<>();
/* 18 */   public static HashMap<UUID, String> emergenzaHashMap = new HashMap<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
/* 23 */     if (!(commandSender instanceof Player)) return true;
/*    */     
/* 25 */     Player player = (Player)commandSender;
/*    */     
/* 27 */     if (player.hasPermission("realityphone.ambulance") || player.hasPermission("realityphone.vdf") || player.hasPermission("realityphone.police")) {
/*    */       
/* 29 */       String emergenza = strings[0];
/* 30 */       Player target = Bukkit.getPlayerExact(strings[1]);
/*    */       
/* 32 */       if (target == null) return true;
/*    */       
/* 34 */       player.sendMessage(BasicsFunction.hex("&f &#84CC17Scrivi in chat il messaggio di risposta a " + target.getName()));
/*    */       
/* 36 */       playerHashMap.put(player.getUniqueId(), target.getUniqueId());
/* 37 */       emergenzaHashMap.put(player.getUniqueId(), emergenza);
/*    */     } 
/*    */ 
/*    */     
/* 41 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\commands\sidecommands\RispondiCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */