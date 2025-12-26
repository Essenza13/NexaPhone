/*    */ package com.google.gson.internal.bind;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.TypeAdapter;
/*    */ import com.google.gson.reflect.TypeToken;
/*    */ import com.google.gson.stream.JsonReader;
/*    */ import com.google.gson.stream.JsonWriter;
/*    */ import java.io.IOException;
/*    */ import java.lang.reflect.Type;
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
/*    */ final class TypeAdapterRuntimeTypeWrapper<T>
/*    */   extends TypeAdapter<T>
/*    */ {
/*    */   private final Gson context;
/*    */   private final TypeAdapter<T> delegate;
/*    */   private final Type type;
/*    */   
/*    */   TypeAdapterRuntimeTypeWrapper(Gson context, TypeAdapter<T> delegate, Type type) {
/* 33 */     this.context = context;
/* 34 */     this.delegate = delegate;
/* 35 */     this.type = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public T read(JsonReader in) throws IOException {
/* 40 */     return (T)this.delegate.read(in);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void write(JsonWriter out, T value) throws IOException {
/* 51 */     TypeAdapter<T> chosen = this.delegate;
/* 52 */     Type runtimeType = getRuntimeTypeIfMoreSpecific(this.type, value);
/* 53 */     if (runtimeType != this.type) {
/*    */       
/* 55 */       TypeAdapter<T> runtimeTypeAdapter = this.context.getAdapter(TypeToken.get(runtimeType));
/*    */ 
/*    */       
/* 58 */       if (!(runtimeTypeAdapter instanceof ReflectiveTypeAdapterFactory.Adapter)) {
/*    */         
/* 60 */         chosen = runtimeTypeAdapter;
/* 61 */       } else if (!isReflective(this.delegate)) {
/*    */ 
/*    */         
/* 64 */         chosen = this.delegate;
/*    */       } else {
/*    */         
/* 67 */         chosen = runtimeTypeAdapter;
/*    */       } 
/*    */     } 
/* 70 */     chosen.write(out, value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static boolean isReflective(TypeAdapter<?> typeAdapter) {
/* 80 */     while (typeAdapter instanceof SerializationDelegatingTypeAdapter) {
/* 81 */       TypeAdapter<?> delegate = ((SerializationDelegatingTypeAdapter)typeAdapter).getSerializationDelegate();
/*    */       
/* 83 */       if (delegate == typeAdapter) {
/*    */         break;
/*    */       }
/* 86 */       typeAdapter = delegate;
/*    */     } 
/*    */     
/* 89 */     return typeAdapter instanceof ReflectiveTypeAdapterFactory.Adapter;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static Type getRuntimeTypeIfMoreSpecific(Type<?> type, Object value) {
/* 96 */     if (value != null && (type instanceof Class || type instanceof java.lang.reflect.TypeVariable)) {
/* 97 */       type = value.getClass();
/*    */     }
/* 99 */     return type;
/*    */   }
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\com\google\gson\internal\bind\TypeAdapterRuntimeTypeWrapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */