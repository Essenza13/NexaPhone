/*     */ package com.google.gson;
/*     */ 
/*     */ import com.google.gson.internal.Streams;
/*     */ import com.google.gson.stream.JsonReader;
/*     */ import com.google.gson.stream.JsonToken;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class JsonStreamParser
/*     */   implements Iterator<JsonElement>
/*     */ {
/*     */   private final JsonReader parser;
/*     */   private final Object lock;
/*     */   
/*     */   public JsonStreamParser(String json) {
/*  62 */     this(new StringReader(json));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonStreamParser(Reader reader) {
/*  70 */     this.parser = new JsonReader(reader);
/*  71 */     this.parser.setLenient(true);
/*  72 */     this.lock = new Object();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonElement next() throws JsonParseException {
/*  86 */     if (!hasNext()) {
/*  87 */       throw new NoSuchElementException();
/*     */     }
/*     */     
/*     */     try {
/*  91 */       return Streams.parse(this.parser);
/*  92 */     } catch (StackOverflowError e) {
/*  93 */       throw new JsonParseException("Failed parsing JSON source to Json", e);
/*  94 */     } catch (OutOfMemoryError e) {
/*  95 */       throw new JsonParseException("Failed parsing JSON source to Json", e);
/*  96 */     } catch (JsonParseException e) {
/*  97 */       throw (e.getCause() instanceof java.io.EOFException) ? new NoSuchElementException() : e;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasNext() {
/* 109 */     synchronized (this.lock) {
/*     */       
/* 111 */       return (this.parser.peek() != JsonToken.END_DOCUMENT);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove() {
/* 127 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\com\google\gson\JsonStreamParser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */