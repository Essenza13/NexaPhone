/*    */ package net.kyori.adventure.util;
/*    */ 
/*    */ import java.util.ServiceLoader;
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
/*    */ final class Services0
/*    */ {
/*    */   static <S> ServiceLoader<S> loader(Class<S> type) {
/* 33 */     return ServiceLoader.load(type, type.getClassLoader());
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventur\\util\Services0.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */