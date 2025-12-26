/*    */ package net.kyori.adventure.text.event;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.kyori.adventure.audience.Audience;
/*    */ import net.kyori.adventure.permission.PermissionChecker;
/*    */ import net.kyori.adventure.util.Services;
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
/*    */ final class ClickCallbackInternals
/*    */ {
/* 36 */   static final PermissionChecker ALWAYS_FALSE = PermissionChecker.always(TriState.FALSE);
/*    */   
/* 38 */   static final ClickCallback.Provider PROVIDER = Services.service(ClickCallback.Provider.class)
/* 39 */     .orElseGet(Fallback::new);
/*    */   
/*    */   static final class Fallback implements ClickCallback.Provider {
/*    */     @NotNull
/*    */     public ClickEvent create(@NotNull ClickCallback<Audience> callback, ClickCallback.Options options) {
/* 44 */       return ClickEvent.suggestCommand("Callbacks are not supported on this platform!");
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\text\event\ClickCallbackInternals.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */