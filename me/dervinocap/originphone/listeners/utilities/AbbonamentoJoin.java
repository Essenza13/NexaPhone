/*    */ package me.dervinocap.originphone.listeners.utilities;
/*    */ 
/*    */ import java.time.LocalDate;
/*    */ import java.time.LocalDateTime;
/*    */ import java.time.format.DateTimeFormatter;
/*    */ import me.dervinocap.originphone.database.NumberDatabase;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AbbonamentoJoin
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onJoin(PlayerJoinEvent event) {
/* 20 */     Player player = event.getPlayer();
/*    */     
/* 22 */     if (NumberDatabase.getAbbonamentoExpireFormatted(player).equals("---"))
/*    */       return; 
/* 24 */     DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
/*    */     
/* 26 */     LocalDateTime today = LocalDateTime.now();
/*    */     
/* 28 */     String s = dateTimeFormatter.format(today);
/*    */     
/* 30 */     LocalDate now = LocalDate.parse(s, dateTimeFormatter);
/*    */     
/* 32 */     LocalDate dateStop = LocalDate.parse(NumberDatabase.getAbbonamentoExpireFormatted(player), dateTimeFormatter);
/*    */     
/* 34 */     if (now.isAfter(dateStop)) {
/*    */       
/* 36 */       player.sendMessage(" ");
/* 37 */       player.sendMessage(" §c§lREPHONE");
/* 38 */       player.sendMessage(" §7Oh No! Il tuo abbonamento");
/* 39 */       player.sendMessage(" §7E' scaduto! Vai a rinnovarlo!");
/* 40 */       player.sendMessage("");
/*    */       
/* 42 */       NumberDatabase.setAbbonamento(player, "Nessuno");
/* 43 */       NumberDatabase.resetAbbonamentoExpire(player);
/* 44 */       NumberDatabase.setSms(player, 0);
/* 45 */       NumberDatabase.setMinutes(player, 0);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\listener\\utilities\AbbonamentoJoin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */