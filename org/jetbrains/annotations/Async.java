/*    */ package org.jetbrains.annotations;
/*    */ 
/*    */ import java.lang.annotation.ElementType;
/*    */ import java.lang.annotation.Retention;
/*    */ import java.lang.annotation.RetentionPolicy;
/*    */ import java.lang.annotation.Target;
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
/*    */ public final class Async
/*    */ {
/*    */   private Async() {
/* 35 */     throw new AssertionError("Async should not be instantiated");
/*    */   }
/*    */   
/*    */   @Retention(RetentionPolicy.CLASS)
/*    */   @Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
/*    */   public static @interface Execute {}
/*    */   
/*    */   @Retention(RetentionPolicy.CLASS)
/*    */   @Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
/*    */   public static @interface Schedule {}
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\org\jetbrains\annotations\Async.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */