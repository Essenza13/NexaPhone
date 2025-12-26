/*    */ package net.kyori.adventure.text;
/*    */ 
/*    */ import net.kyori.adventure.util.Nag;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ final class LegacyFormattingDetected
/*    */   extends Nag
/*    */ {
/*    */   private static final long serialVersionUID = -947793022628807411L;
/*    */   
/*    */   LegacyFormattingDetected(Component component) {
/* 32 */     super("Legacy formatting codes have been detected in a component - this is unsupported behaviour. Please refer to the Adventure documentation (https://docs.advntr.dev) for more information. Component: " + component);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\text\LegacyFormattingDetected.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */