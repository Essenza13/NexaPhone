/*    */ package com.google.gson.internal.sql;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import com.google.gson.TypeAdapter;
/*    */ import com.google.gson.TypeAdapterFactory;
/*    */ import com.google.gson.reflect.TypeToken;
/*    */ import com.google.gson.stream.JsonReader;
/*    */ import com.google.gson.stream.JsonToken;
/*    */ import com.google.gson.stream.JsonWriter;
/*    */ import java.io.IOException;
/*    */ import java.sql.Time;
/*    */ import java.text.DateFormat;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
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
/*    */ final class SqlTimeTypeAdapter
/*    */   extends TypeAdapter<Time>
/*    */ {
/* 41 */   static final TypeAdapterFactory FACTORY = new TypeAdapterFactory()
/*    */     {
/*    */       public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
/* 44 */         return (typeToken.getRawType() == Time.class) ? new SqlTimeTypeAdapter() : null;
/*    */       }
/*    */     };
/*    */   
/* 48 */   private final DateFormat format = new SimpleDateFormat("hh:mm:ss a");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Time read(JsonReader in) throws IOException {
/* 54 */     if (in.peek() == JsonToken.NULL) {
/* 55 */       in.nextNull();
/* 56 */       return null;
/*    */     } 
/* 58 */     String s = in.nextString();
/*    */     try {
/* 60 */       synchronized (this) {
/* 61 */         Date date = this.format.parse(s);
/* 62 */         return new Time(date.getTime());
/*    */       } 
/* 64 */     } catch (ParseException e) {
/* 65 */       throw new JsonSyntaxException("Failed parsing '" + s + "' as SQL Time; at path " + in.getPreviousPath(), e);
/*    */     } 
/*    */   }
/*    */   public void write(JsonWriter out, Time value) throws IOException {
/*    */     String timeString;
/* 70 */     if (value == null) {
/* 71 */       out.nullValue();
/*    */       
/*    */       return;
/*    */     } 
/* 75 */     synchronized (this) {
/* 76 */       timeString = this.format.format(value);
/*    */     } 
/* 78 */     out.value(timeString);
/*    */   }
/*    */   
/*    */   private SqlTimeTypeAdapter() {}
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\com\google\gson\internal\sql\SqlTimeTypeAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */