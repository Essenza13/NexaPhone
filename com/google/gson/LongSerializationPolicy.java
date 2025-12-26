/*    */ package com.google.gson;
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
/*    */ public enum LongSerializationPolicy
/*    */ {
/* 36 */   DEFAULT {
/*    */     public JsonElement serialize(Long value) {
/* 38 */       if (value == null) {
/* 39 */         return JsonNull.INSTANCE;
/*    */       }
/* 41 */       return new JsonPrimitive(value);
/*    */     }
/*    */   },
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 52 */   STRING {
/*    */     public JsonElement serialize(Long value) {
/* 54 */       if (value == null) {
/* 55 */         return JsonNull.INSTANCE;
/*    */       }
/* 57 */       return new JsonPrimitive(value.toString());
/*    */     }
/*    */   };
/*    */   
/*    */   public abstract JsonElement serialize(Long paramLong);
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\com\google\gson\LongSerializationPolicy.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */