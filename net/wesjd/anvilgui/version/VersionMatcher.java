/*    */ package net.wesjd.anvilgui.version;
/*    */ 
/*    */ import org.bukkit.Bukkit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VersionMatcher
/*    */ {
/*    */   public VersionWrapper match() {
/* 26 */     String serverVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].substring(1);
/*    */     try {
/* 28 */       return Class.forName(getClass().getPackage().getName() + ".Wrapper" + serverVersion)
/* 29 */         .getDeclaredConstructor(new Class[0])
/* 30 */         .newInstance(new Object[0]);
/* 31 */     } catch (ClassNotFoundException exception) {
/* 32 */       throw new IllegalStateException("AnvilGUI does not support server version \"" + serverVersion + "\"", exception);
/*    */     }
/* 34 */     catch (ReflectiveOperationException exception) {
/* 35 */       throw new IllegalStateException("Failed to instantiate version wrapper for version " + serverVersion, exception);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\wesjd\anvilgui\version\VersionMatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */