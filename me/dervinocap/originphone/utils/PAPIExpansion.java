/*    */ package me.dervinocap.originphone.utils;
/*    */ 
/*    */ import me.clip.placeholderapi.expansion.PlaceholderExpansion;
/*    */ import me.dervinocap.originphone.RealityPhone;
/*    */ import me.dervinocap.originphone.database.NumberDatabase;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class PAPIExpansion
/*    */   extends PlaceholderExpansion {
/*    */   private RealityPhone plugin;
/*    */   
/*    */   public void PAPIExpansions(RealityPhone plugin) {
/* 13 */     this.plugin = plugin;
/*    */   }
/*    */   
/*    */   public String getAuthor() {
/* 17 */     return "DerviWorks";
/*    */   }
/*    */   
/*    */   public String getIdentifier() {
/* 21 */     return "realityphone";
/*    */   }
/*    */   
/*    */   public String getVersion() {
/* 25 */     return "1.0";
/*    */   }
/*    */   
/*    */   public boolean canRegister() {
/* 29 */     return true;
/*    */   }
/*    */   
/*    */   public boolean persist() {
/* 33 */     return true;
/*    */   }
/*    */   
/*    */   public String onPlaceholderRequest(Player player, String params) {
/* 37 */     if (player == null) {
/* 38 */       return "";
/*    */     }
/* 40 */     if (params.equalsIgnoreCase("phone_number")) {
/*    */       
/* 42 */       if (NumberDatabase.getPhoneNumber(player) == null) {
/* 43 */         return "Nessuno";
/*    */       }
/* 45 */       return NumberDatabase.getPhoneNumber(player);
/*    */     } 
/*    */ 
/*    */     
/* 49 */     if (params.equalsIgnoreCase("phone_number_formatted")) {
/* 50 */       return NumberDatabase.getPhoneNumberFormatted(player);
/*    */     }
/*    */     
/* 53 */     if (params.equalsIgnoreCase("sms")) {
/* 54 */       return String.valueOf(NumberDatabase.getSms(player));
/*    */     }
/*    */     
/* 57 */     if (params.equalsIgnoreCase("minutes")) {
/* 58 */       return String.valueOf(NumberDatabase.getMinutes(player));
/*    */     }
/*    */     
/* 61 */     if (params.equalsIgnoreCase("abbonamento")) {
/* 62 */       return NumberDatabase.getAbbonamento(player);
/*    */     }
/*    */     
/* 65 */     if (params.equalsIgnoreCase("abbonamento_expires")) {
/* 66 */       return NumberDatabase.getAbbonamentoExpire(player);
/*    */     }
/*    */     
/* 69 */     if (params.equalsIgnoreCase("abbonamento_expires_formatted")) {
/* 70 */       return NumberDatabase.getAbbonamentoExpireFormatted(player);
/*    */     }
/*    */     
/* 73 */     return params;
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphon\\utils\PAPIExpansion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */