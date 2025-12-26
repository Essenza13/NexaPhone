/*    */ package net.kyori.adventure.text.serializer.json;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import java.util.function.Supplier;
/*    */ import net.kyori.adventure.util.Services;
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
/*    */ final class JSONComponentSerializerAccessor
/*    */ {
/* 31 */   private static final Optional<JSONComponentSerializer.Provider> SERVICE = Services.serviceWithFallback(JSONComponentSerializer.Provider.class);
/*    */ 
/*    */ 
/*    */   
/*    */   static final class Instances
/*    */   {
/* 37 */     static final JSONComponentSerializer INSTANCE = JSONComponentSerializerAccessor.SERVICE
/* 38 */       .map(JSONComponentSerializer.Provider::instance)
/* 39 */       .orElse(DummyJSONComponentSerializer.INSTANCE);
/*    */     
/* 41 */     static final Supplier<JSONComponentSerializer.Builder> BUILDER_SUPPLIER = JSONComponentSerializerAccessor.SERVICE
/* 42 */       .map(JSONComponentSerializer.Provider::builder)
/* 43 */       .orElse(BuilderImpl::new);
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\net\kyori\adventure\text\serializer\json\JSONComponentSerializerAccessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */