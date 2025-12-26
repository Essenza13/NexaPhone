/*    */ package com.google.gson.internal;
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
/*    */ public final class $Gson$Preconditions
/*    */ {
/*    */   private $Gson$Preconditions() {
/* 36 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public static <T> T checkNotNull(T obj) {
/* 46 */     if (obj == null) {
/* 47 */       throw new NullPointerException();
/*    */     }
/* 49 */     return obj;
/*    */   }
/*    */   
/*    */   public static void checkArgument(boolean condition) {
/* 53 */     if (!condition)
/* 54 */       throw new IllegalArgumentException(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\com\google\gson\internal\$Gson$Preconditions.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */