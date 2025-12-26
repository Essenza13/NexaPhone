/*    */ package me.dervinocap.originphone.listeners.utilities;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.UUID;
/*    */ import me.dervinocap.originphone.RealityPhone;
/*    */ import me.dervinocap.originphone.database.NumberDatabase;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ 
/*    */ 
/*    */ public class DataJoin
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onJoin(PlayerJoinEvent event) {
/* 18 */     Player player = event.getPlayer();
/*    */     
/* 20 */     UUID playerName = player.getUniqueId();
/* 21 */     File userdata = new File(RealityPhone.getInstance().getDataFolder(), "numbers");
/* 22 */     File f = new File(userdata, File.separator + playerName + ".yml");
/*    */     
/* 24 */     File userdata1 = new File(RealityPhone.getInstance().getDataFolder(), "contacts");
/* 25 */     File f1 = new File(userdata1, File.separator + playerName + ".yml");
/*    */     
/* 27 */     if (!f.exists())
/* 28 */       NumberDatabase.createPlayerData(player); 
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\listener\\utilities\DataJoin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */