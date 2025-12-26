/*    */ package net.kyori.adventure.identity;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.kyori.adventure.internal.Internals;
/*    */ import net.kyori.examination.Examinable;
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
/*    */ final class IdentityImpl
/*    */   implements Examinable, Identity
/*    */ {
/*    */   private final UUID uuid;
/*    */   
/*    */   IdentityImpl(UUID uuid) {
/* 36 */     this.uuid = uuid;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public UUID uuid() {
/* 41 */     return this.uuid;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     return Internals.toString(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(@Nullable Object other) {
/* 51 */     if (this == other) return true; 
/* 52 */     if (!(other instanceof Identity)) return false; 
/* 53 */     Identity that = (Identity)other;
/* 54 */     return this.uuid.equals(that.uuid());
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 59 */     return this.uuid.hashCode();
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\identity\IdentityImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */