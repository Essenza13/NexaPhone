/*    */ package net.kyori.adventure.text.serializer.gson;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.kyori.adventure.text.serializer.json.JSONComponentSerializer;
/*    */ import net.kyori.adventure.util.Services;
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
/*    */ @Internal
/*    */ public final class JSONComponentSerializerProviderImpl
/*    */   implements JSONComponentSerializer.Provider, Services.Fallback
/*    */ {
/*    */   @NotNull
/*    */   public JSONComponentSerializer instance() {
/* 41 */     return GsonComponentSerializer.gson();
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Supplier<JSONComponentSerializer.Builder> builder() {
/* 46 */     return GsonComponentSerializer::builder;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     return "JSONComponentSerializerProviderImpl[GsonComponentSerializer]";
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\text\serializer\gson\JSONComponentSerializerProviderImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */