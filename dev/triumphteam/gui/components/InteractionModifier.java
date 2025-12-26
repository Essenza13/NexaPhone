/*    */ package dev.triumphteam.gui.components;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.EnumSet;
/*    */ import java.util.Set;
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
/*    */ public enum InteractionModifier
/*    */ {
/* 37 */   PREVENT_ITEM_PLACE,
/* 38 */   PREVENT_ITEM_TAKE,
/* 39 */   PREVENT_ITEM_SWAP,
/* 40 */   PREVENT_ITEM_DROP,
/* 41 */   PREVENT_OTHER_ACTIONS;
/*    */   static {
/* 43 */     VALUES = Collections.unmodifiableSet(EnumSet.allOf(InteractionModifier.class));
/*    */   }
/*    */   
/*    */   public static final Set<InteractionModifier> VALUES;
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\dev\triumphteam\gui\components\InteractionModifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */