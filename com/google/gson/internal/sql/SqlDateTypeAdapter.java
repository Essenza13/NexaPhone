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
/*    */ import java.sql.Date;
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
/*    */ final class SqlDateTypeAdapter
/*    */   extends TypeAdapter<Date>
/*    */ {
/* 40 */   static final TypeAdapterFactory FACTORY = new TypeAdapterFactory()
/*    */     {
/*    */       public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
/* 43 */         return (typeToken.getRawType() == Date.class) ? 
/* 44 */           new SqlDateTypeAdapter() : null;
/*    */       }
/*    */     };
/*    */   
/* 48 */   private final DateFormat format = new SimpleDateFormat("MMM d, yyyy");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Date read(JsonReader in) throws IOException {
/* 55 */     if (in.peek() == JsonToken.NULL) {
/* 56 */       in.nextNull();
/* 57 */       return null;
/*    */     } 
/* 59 */     String s = in.nextString();
/*    */     try {
/*    */       Date utilDate;
/* 62 */       synchronized (this) {
/* 63 */         utilDate = this.format.parse(s);
/*    */       } 
/* 65 */       return new Date(utilDate.getTime());
/* 66 */     } catch (ParseException e) {
/* 67 */       Date utilDate; throw new JsonSyntaxException("Failed parsing '" + s + "' as SQL Date; at path " + in.getPreviousPath(), utilDate);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void write(JsonWriter out, Date value) throws IOException {
/*    */     String dateString;
/* 73 */     if (value == null) {
/* 74 */       out.nullValue();
/*    */       
/*    */       return;
/*    */     } 
/* 78 */     synchronized (this) {
/* 79 */       dateString = this.format.format(value);
/*    */     } 
/* 81 */     out.value(dateString);
/*    */   }
/*    */   
/*    */   private SqlDateTypeAdapter() {}
/*    */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\com\google\gson\internal\sql\SqlDateTypeAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */