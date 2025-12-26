/*    */ package me.dervinocap.originphone.commands.sidecommands;
/*    */ 
/*    */ import it.realityrp.realitygeneral.utils.BasicsFunction;
/*    */ import me.dervinocap.originphone.utils.ItemManager;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.ArmorStand;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class CabinCreateCommand
/*    */   implements CommandExecutor
/*    */ {
/*    */   public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
/* 18 */     if (!(commandSender instanceof Player)) return true;
/*    */     
/* 20 */     Player player = (Player)commandSender;
/*    */     
/* 22 */     if (!player.hasPermission("realitydiscoteca.admin")) {
/* 23 */       player.sendMessage(BasicsFunction.hex("&f &#DE2727Non possiedi abbastanza permessi per eseguire questo comando!"));
/* 24 */       return true;
/*    */     } 
/*    */     
/* 27 */     Location loc = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());
/*    */     
/* 29 */     ArmorStand armorstand = (ArmorStand)player.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
/*    */     
/* 31 */     armorstand.setGravity(false);
/* 32 */     armorstand.setHelmet(ItemManager.cabin());
/* 33 */     armorstand.setCustomName("reality_cabina_phone");
/* 34 */     armorstand.setCustomNameVisible(false);
/* 35 */     armorstand.setVisible(false);
/* 36 */     armorstand.setInvulnerable(true);
/*    */     
/* 38 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\commands\sidecommands\CabinCreateCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */