/*    */ package net.kyori.adventure.internal;
/*    */ 
/*    */ import net.kyori.examination.Examinable;
/*    */ import net.kyori.examination.Examiner;
/*    */ import net.kyori.examination.string.StringExaminer;
/*    */ import org.jetbrains.annotations.ApiStatus.Internal;
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
/*    */ @Internal
/*    */ public final class Internals
/*    */ {
/*    */   @NotNull
/*    */   public static String toString(@NotNull Examinable examinable) {
/* 47 */     return (String)examinable.examine((Examiner)StringExaminer.simpleEscaping());
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\internal\Internals.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */