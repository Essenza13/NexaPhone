/*    */ package me.dervinocap.originphone;
/*    */ 
/*    */ import me.dervinocap.originphone.utils.customloader.PluginCustomLoader;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public final class RealityPhone
/*    */   extends JavaPlugin {
/*    */   public static RealityPhone getInstance() {
/*  9 */     return instance;
/*    */   }
/*    */   
/*    */   private static RealityPhone instance;
/*    */   
/*    */   public void onEnable() {
/* 15 */     instance = this;
/*    */     
/* 17 */     PluginCustomLoader.getInstance().loadPlugin();
/*    */   }
/*    */   
/*    */   public void onDisable() {}
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\me\dervinocap\originphone\RealityPhone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */