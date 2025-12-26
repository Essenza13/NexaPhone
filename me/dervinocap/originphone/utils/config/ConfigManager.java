/*    */ package me.dervinocap.originphone.utils.config;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import me.dervinocap.originphone.RealityPhone;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ public enum ConfigManager
/*    */ {
/* 12 */   ABBONAMENTI_RED_PRO_SMS(".Abbonamenti.Red-Pro.minutes"),
/* 13 */   ABBONAMENTI_RED_PRO_MINUTI(".Abbonamenti.Red-Pro.sms"),
/*    */   
/* 15 */   ABBONAMENTI_RED_MAX_MINUTI(".Abbonamenti.Red-Max.minutes"),
/* 16 */   ABBONAMENTI_RED_MAX_SMS(".Abbonamenti.Red-Max.sms"),
/*    */   
/* 18 */   ABBONAMENTI_FAMILY_MINUTI(".Abbonamenti.Family.minutes"),
/* 19 */   ABBONAMENTI_FAMILY_SMS(".Abbonamenti.Family.sms"),
/*    */   
/* 21 */   ABBONAMENTI_INFINITO_MINUTI(".Abbonamenti.Infinito.minutes"),
/* 22 */   ABBONAMENTI_INFINITO_SMS(".Abbonamenti.Infinito.sms");
/*    */   
/*    */   private final String path;
/*    */   
/*    */   ConfigManager(String path) {
/* 27 */     this.path = path;
/*    */   }
/*    */   
/*    */   public boolean getBoolean() {
/* 31 */     return RealityPhone.getInstance().getConfig().getBoolean(this.path);
/*    */   }
/*    */   
/*    */   public String getFormattedString() {
/* 35 */     return ChatColor.translateAlternateColorCodes('&', RealityPhone.getInstance().getConfig().getString(this.path));
/*    */   }
/*    */   
/*    */   public Material getMaterial() {
/* 39 */     return Material.getMaterial(Objects.<String>requireNonNull(RealityPhone.getInstance().getConfig().getString(this.path)));
/*    */   }
/*    */   
/*    */   public String getString() {
/* 43 */     return RealityPhone.getInstance().getConfig().getString(this.path);
/*    */   }
/*    */   
/*    */   public int getInt() {
/* 47 */     return RealityPhone.getInstance().getConfig().getInt(this.path);
/*    */   }
/*    */   
/*    */   public List<String> getStringList() {
/* 51 */     return RealityPhone.getInstance().getConfig().getStringList(this.path);
/*    */   }
/*    */   
/*    */   public static String getFormattedString(String string) {
/* 55 */     return ChatColor.translateAlternateColorCodes('&', string);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphon\\utils\config\ConfigManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */