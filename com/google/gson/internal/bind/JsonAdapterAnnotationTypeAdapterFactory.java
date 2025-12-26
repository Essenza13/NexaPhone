/*    */ package com.google.gson.internal.bind;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import com.google.gson.TypeAdapter;
/*    */ import com.google.gson.TypeAdapterFactory;
/*    */ import com.google.gson.annotations.JsonAdapter;
/*    */ import com.google.gson.internal.ConstructorConstructor;
/*    */ import com.google.gson.reflect.TypeToken;
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
/*    */ public final class JsonAdapterAnnotationTypeAdapterFactory
/*    */   implements TypeAdapterFactory
/*    */ {
/*    */   private final ConstructorConstructor constructorConstructor;
/*    */   
/*    */   public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor constructorConstructor) {
/* 38 */     this.constructorConstructor = constructorConstructor;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> targetType) {
/* 44 */     Class<? super T> rawType = targetType.getRawType();
/* 45 */     JsonAdapter annotation = rawType.<JsonAdapter>getAnnotation(JsonAdapter.class);
/* 46 */     if (annotation == null) {
/* 47 */       return null;
/*    */     }
/* 49 */     return (TypeAdapter)getTypeAdapter(this.constructorConstructor, gson, targetType, annotation);
/*    */   }
/*    */   
/*    */   TypeAdapter<?> getTypeAdapter(ConstructorConstructor constructorConstructor, Gson gson, TypeToken<?> type, JsonAdapter annotation) {
/*    */     TypeAdapter<?> typeAdapter;
/* 54 */     Object instance = constructorConstructor.get(TypeToken.get(annotation.value())).construct();
/*    */ 
/*    */     
/* 57 */     boolean nullSafe = annotation.nullSafe();
/* 58 */     if (instance instanceof TypeAdapter) {
/* 59 */       typeAdapter = (TypeAdapter)instance;
/* 60 */     } else if (instance instanceof TypeAdapterFactory) {
/* 61 */       typeAdapter = ((TypeAdapterFactory)instance).create(gson, type);
/* 62 */     } else if (instance instanceof JsonSerializer || instance instanceof JsonDeserializer) {
/*    */ 
/*    */       
/* 65 */       JsonSerializer<?> serializer = (instance instanceof JsonSerializer) ? (JsonSerializer)instance : null;
/*    */ 
/*    */       
/* 68 */       JsonDeserializer<?> deserializer = (instance instanceof JsonDeserializer) ? (JsonDeserializer)instance : null;
/*    */ 
/*    */       
/* 71 */       TypeAdapter<?> tempAdapter = new TreeTypeAdapter(serializer, deserializer, gson, type, null, nullSafe);
/* 72 */       typeAdapter = tempAdapter;
/*    */       
/* 74 */       nullSafe = false;
/*    */     } else {
/* 76 */       throw new IllegalArgumentException("Invalid attempt to bind an instance of " + instance
/* 77 */           .getClass().getName() + " as a @JsonAdapter for " + type.toString() + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 82 */     if (typeAdapter != null && nullSafe) {
/* 83 */       typeAdapter = typeAdapter.nullSafe();
/*    */     }
/*    */     
/* 86 */     return typeAdapter;
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\com\google\gson\internal\bind\JsonAdapterAnnotationTypeAdapterFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */