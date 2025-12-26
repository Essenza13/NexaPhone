/*    */ package net.kyori.adventure.identity;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
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
/*    */ final class NilIdentity
/*    */   implements Identity
/*    */ {
/* 31 */   static final UUID NIL_UUID = new UUID(0L, 0L);
/* 32 */   static final Identity INSTANCE = new NilIdentity();
/*    */   
/*    */   @NotNull
/*    */   public UUID uuid() {
/* 36 */     return NIL_UUID;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 41 */     return "Identity.nil()";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(@Nullable Object that) {
/* 46 */     return (this == that);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 51 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\identity\NilIdentity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */