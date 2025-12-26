/*    */ package me.dervinocap.originphone.commands.vodafonecommand.subcommands;
/*    */ 
/*    */ import de.tr7zw.nbtapi.NBTItem;
/*    */ import java.util.Arrays;
/*    */ import me.dervinocap.originphone.commands.SubCommand;
/*    */ import me.dervinocap.originphone.database.NumberDatabase;
/*    */ import me.dervinocap.originphone.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.customloader.PluginCustomLoader;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AssignPhoneCommand
/*    */   extends SubCommand
/*    */ {
/*    */   public boolean isPlayerOnly() {
/* 22 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermission() {
/* 27 */     return null;
/*    */   }
/*    */   
/* 30 */   private final PluginCustomLoader customLoader = PluginCustomLoader.getInstance();
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String[] args) {
/* 35 */     Player player = (Player)sender;
/*    */     
/* 37 */     if (args.length < 2) {
/* 38 */       player.sendMessage(BasicsFunction.hex("&f &#F59F0BQuesto comando richiede ulteriori argomenti!"));
/*    */       
/*    */       return;
/*    */     } 
/* 42 */     if (!player.hasPermission("realityphone.rephone")) {
/* 43 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Non possiedi abbastanza permessi per eseguire questo comando!"));
/*    */       
/*    */       return;
/*    */     } 
/* 47 */     Player target = Bukkit.getPlayerExact(args[1]);
/*    */     
/* 49 */     if (target == null) {
/* 50 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Il cittadino inserito non è stato trovato!"));
/*    */       
/*    */       return;
/*    */     } 
/* 54 */     if (!NumberDatabase.checkIfPlayerHasNumber(target)) {
/* 55 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Il cittadino non ha un numero di telefono!"));
/*    */       
/*    */       return;
/*    */     } 
/* 59 */     if (player.getInventory().getItemInMainHand() == null) {
/* 60 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Devi avere un telefono in mano!"));
/*    */       
/*    */       return;
/*    */     } 
/* 64 */     if (player.getInventory().getItemInMainHand().getAmount() == 0) {
/* 65 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Devi avere un telefono in mano!"));
/*    */       
/*    */       return;
/*    */     } 
/* 69 */     if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
/* 70 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Devi avere un telefono in mano!"));
/*    */       
/*    */       return;
/*    */     } 
/* 74 */     if (BasicsFunction.isPhone(player.getInventory().getItemInMainHand())) {
/*    */       
/* 76 */       NBTItem nbtItem = new NBTItem(player.getInventory().getItemInMainHand(), true);
/*    */       
/* 78 */       nbtItem.setString("Player", target.getName());
/*    */       
/* 80 */       ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
/*    */       
/* 82 */       meta.setLore(Arrays.asList(new String[] { "§7Numero di " + target.getName(), "§e" + NumberDatabase.getPhoneNumberFormatted(target) }));
/*    */       
/* 84 */       player.getInventory().getItemInMainHand().setItemMeta(meta);
/*    */       
/* 86 */       player.sendMessage(BasicsFunction.hex("&f &#84CC17Hai assegnato questo telefono a " + target.getName()));
/*    */     } else {
/*    */       
/* 89 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Devi avere un telefono in mano!"));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\commands\vodafonecommand\subcommands\AssignPhoneCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */