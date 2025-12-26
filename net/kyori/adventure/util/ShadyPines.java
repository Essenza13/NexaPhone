/*    */ package net.kyori.adventure.util;
/*    */ 
/*    */ import java.util.Set;
/*    */ import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public final class ShadyPines
/*    */ {
/*    */   @Deprecated
/*    */   @SafeVarargs
/*    */   @ScheduledForRemoval(inVersion = "5.0.0")
/*    */   @NotNull
/*    */   public static <E extends Enum<E>> Set<E> enumSet(Class<E> type, E... constants) {
/* 54 */     return MonkeyBars.enumSet(type, constants);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean equals(double a, double b) {
/* 66 */     return (Double.doubleToLongBits(a) == Double.doubleToLongBits(b));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean equals(float a, float b) {
/* 78 */     return (Float.floatToIntBits(a) == Float.floatToIntBits(b));
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventur\\util\ShadyPines.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */