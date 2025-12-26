/*    */ package net.kyori.adventure.permission;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import java.util.function.Predicate;
/*    */ import net.kyori.adventure.key.Key;
/*    */ import net.kyori.adventure.pointer.Pointer;
/*    */ import net.kyori.adventure.util.TriState;
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
/*    */ public interface PermissionChecker
/*    */   extends Predicate<String>
/*    */ {
/* 46 */   public static final Pointer<PermissionChecker> POINTER = Pointer.pointer(PermissionChecker.class, Key.key("adventure", "permission"));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   static PermissionChecker always(@NotNull TriState state) {
/* 56 */     Objects.requireNonNull(state);
/* 57 */     if (state == TriState.TRUE) return PermissionCheckers.TRUE; 
/* 58 */     if (state == TriState.FALSE) return PermissionCheckers.FALSE; 
/* 59 */     return PermissionCheckers.NOT_SET;
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
/*    */ 
/*    */   
/*    */   default boolean test(@NotNull String permission) {
/* 73 */     return (value(permission) == TriState.TRUE);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   TriState value(@NotNull String paramString);
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\permission\PermissionChecker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */