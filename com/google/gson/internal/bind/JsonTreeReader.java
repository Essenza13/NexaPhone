/*     */ package com.google.gson.internal.bind;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.google.gson.stream.JsonReader;
/*     */ import com.google.gson.stream.JsonToken;
/*     */ import com.google.gson.stream.MalformedJsonException;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
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
/*     */ public final class JsonTreeReader
/*     */   extends JsonReader
/*     */ {
/*  40 */   private static final Reader UNREADABLE_READER = new Reader() {
/*     */       public int read(char[] buffer, int offset, int count) throws IOException {
/*  42 */         throw new AssertionError();
/*     */       }
/*     */       public void close() throws IOException {
/*  45 */         throw new AssertionError();
/*     */       }
/*     */     };
/*  48 */   private static final Object SENTINEL_CLOSED = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   private Object[] stack = new Object[32];
/*  54 */   private int stackSize = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   private String[] pathNames = new String[32];
/*  65 */   private int[] pathIndices = new int[32];
/*     */   
/*     */   public JsonTreeReader(JsonElement element) {
/*  68 */     super(UNREADABLE_READER);
/*  69 */     push(element);
/*     */   }
/*     */   
/*     */   public void beginArray() throws IOException {
/*  73 */     expect(JsonToken.BEGIN_ARRAY);
/*  74 */     JsonArray array = (JsonArray)peekStack();
/*  75 */     push(array.iterator());
/*  76 */     this.pathIndices[this.stackSize - 1] = 0;
/*     */   }
/*     */   
/*     */   public void endArray() throws IOException {
/*  80 */     expect(JsonToken.END_ARRAY);
/*  81 */     popStack();
/*  82 */     popStack();
/*  83 */     if (this.stackSize > 0) {
/*  84 */       this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/*     */     }
/*     */   }
/*     */   
/*     */   public void beginObject() throws IOException {
/*  89 */     expect(JsonToken.BEGIN_OBJECT);
/*  90 */     JsonObject object = (JsonObject)peekStack();
/*  91 */     push(object.entrySet().iterator());
/*     */   }
/*     */   
/*     */   public void endObject() throws IOException {
/*  95 */     expect(JsonToken.END_OBJECT);
/*  96 */     this.pathNames[this.stackSize - 1] = null;
/*  97 */     popStack();
/*  98 */     popStack();
/*  99 */     if (this.stackSize > 0) {
/* 100 */       this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws IOException {
/* 105 */     JsonToken token = peek();
/* 106 */     return (token != JsonToken.END_OBJECT && token != JsonToken.END_ARRAY && token != JsonToken.END_DOCUMENT);
/*     */   }
/*     */   
/*     */   public JsonToken peek() throws IOException {
/* 110 */     if (this.stackSize == 0) {
/* 111 */       return JsonToken.END_DOCUMENT;
/*     */     }
/*     */     
/* 114 */     Object o = peekStack();
/* 115 */     if (o instanceof Iterator) {
/* 116 */       boolean isObject = this.stack[this.stackSize - 2] instanceof JsonObject;
/* 117 */       Iterator<?> iterator = (Iterator)o;
/* 118 */       if (iterator.hasNext()) {
/* 119 */         if (isObject) {
/* 120 */           return JsonToken.NAME;
/*     */         }
/* 122 */         push(iterator.next());
/* 123 */         return peek();
/*     */       } 
/*     */       
/* 126 */       return isObject ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
/*     */     } 
/* 128 */     if (o instanceof JsonObject)
/* 129 */       return JsonToken.BEGIN_OBJECT; 
/* 130 */     if (o instanceof JsonArray)
/* 131 */       return JsonToken.BEGIN_ARRAY; 
/* 132 */     if (o instanceof JsonPrimitive) {
/* 133 */       JsonPrimitive primitive = (JsonPrimitive)o;
/* 134 */       if (primitive.isString())
/* 135 */         return JsonToken.STRING; 
/* 136 */       if (primitive.isBoolean())
/* 137 */         return JsonToken.BOOLEAN; 
/* 138 */       if (primitive.isNumber()) {
/* 139 */         return JsonToken.NUMBER;
/*     */       }
/* 141 */       throw new AssertionError();
/*     */     } 
/* 143 */     if (o instanceof com.google.gson.JsonNull)
/* 144 */       return JsonToken.NULL; 
/* 145 */     if (o == SENTINEL_CLOSED) {
/* 146 */       throw new IllegalStateException("JsonReader is closed");
/*     */     }
/* 148 */     throw new MalformedJsonException("Custom JsonElement subclass " + o.getClass().getName() + " is not supported");
/*     */   }
/*     */ 
/*     */   
/*     */   private Object peekStack() {
/* 153 */     return this.stack[this.stackSize - 1];
/*     */   }
/*     */   
/*     */   private Object popStack() {
/* 157 */     Object result = this.stack[--this.stackSize];
/* 158 */     this.stack[this.stackSize] = null;
/* 159 */     return result;
/*     */   }
/*     */   
/*     */   private void expect(JsonToken expected) throws IOException {
/* 163 */     if (peek() != expected) {
/* 164 */       throw new IllegalStateException("Expected " + expected + " but was " + 
/* 165 */           peek() + locationString());
/*     */     }
/*     */   }
/*     */   
/*     */   private String nextName(boolean skipName) throws IOException {
/* 170 */     expect(JsonToken.NAME);
/* 171 */     Iterator<?> i = (Iterator)peekStack();
/* 172 */     Map.Entry<?, ?> entry = (Map.Entry<?, ?>)i.next();
/* 173 */     String result = (String)entry.getKey();
/* 174 */     this.pathNames[this.stackSize - 1] = skipName ? "<skipped>" : result;
/* 175 */     push(entry.getValue());
/* 176 */     return result;
/*     */   }
/*     */   
/*     */   public String nextName() throws IOException {
/* 180 */     return nextName(false);
/*     */   }
/*     */   
/*     */   public String nextString() throws IOException {
/* 184 */     JsonToken token = peek();
/* 185 */     if (token != JsonToken.STRING && token != JsonToken.NUMBER) {
/* 186 */       throw new IllegalStateException("Expected " + JsonToken.STRING + " but was " + token + 
/* 187 */           locationString());
/*     */     }
/* 189 */     String result = ((JsonPrimitive)popStack()).getAsString();
/* 190 */     if (this.stackSize > 0) {
/* 191 */       this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/*     */     }
/* 193 */     return result;
/*     */   }
/*     */   
/*     */   public boolean nextBoolean() throws IOException {
/* 197 */     expect(JsonToken.BOOLEAN);
/* 198 */     boolean result = ((JsonPrimitive)popStack()).getAsBoolean();
/* 199 */     if (this.stackSize > 0) {
/* 200 */       this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/*     */     }
/* 202 */     return result;
/*     */   }
/*     */   
/*     */   public void nextNull() throws IOException {
/* 206 */     expect(JsonToken.NULL);
/* 207 */     popStack();
/* 208 */     if (this.stackSize > 0) {
/* 209 */       this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/*     */     }
/*     */   }
/*     */   
/*     */   public double nextDouble() throws IOException {
/* 214 */     JsonToken token = peek();
/* 215 */     if (token != JsonToken.NUMBER && token != JsonToken.STRING) {
/* 216 */       throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + token + 
/* 217 */           locationString());
/*     */     }
/* 219 */     double result = ((JsonPrimitive)peekStack()).getAsDouble();
/* 220 */     if (!isLenient() && (Double.isNaN(result) || Double.isInfinite(result))) {
/* 221 */       throw new MalformedJsonException("JSON forbids NaN and infinities: " + result);
/*     */     }
/* 223 */     popStack();
/* 224 */     if (this.stackSize > 0) {
/* 225 */       this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/*     */     }
/* 227 */     return result;
/*     */   }
/*     */   
/*     */   public long nextLong() throws IOException {
/* 231 */     JsonToken token = peek();
/* 232 */     if (token != JsonToken.NUMBER && token != JsonToken.STRING) {
/* 233 */       throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + token + 
/* 234 */           locationString());
/*     */     }
/* 236 */     long result = ((JsonPrimitive)peekStack()).getAsLong();
/* 237 */     popStack();
/* 238 */     if (this.stackSize > 0) {
/* 239 */       this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/*     */     }
/* 241 */     return result;
/*     */   }
/*     */   
/*     */   public int nextInt() throws IOException {
/* 245 */     JsonToken token = peek();
/* 246 */     if (token != JsonToken.NUMBER && token != JsonToken.STRING) {
/* 247 */       throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + token + 
/* 248 */           locationString());
/*     */     }
/* 250 */     int result = ((JsonPrimitive)peekStack()).getAsInt();
/* 251 */     popStack();
/* 252 */     if (this.stackSize > 0) {
/* 253 */       this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/*     */     }
/* 255 */     return result;
/*     */   }
/*     */   
/*     */   JsonElement nextJsonElement() throws IOException {
/* 259 */     JsonToken peeked = peek();
/* 260 */     if (peeked == JsonToken.NAME || peeked == JsonToken.END_ARRAY || peeked == JsonToken.END_OBJECT || peeked == JsonToken.END_DOCUMENT)
/*     */     {
/*     */ 
/*     */       
/* 264 */       throw new IllegalStateException("Unexpected " + peeked + " when reading a JsonElement.");
/*     */     }
/* 266 */     JsonElement element = (JsonElement)peekStack();
/* 267 */     skipValue();
/* 268 */     return element;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 272 */     this.stack = new Object[] { SENTINEL_CLOSED };
/* 273 */     this.stackSize = 1;
/*     */   }
/*     */   public void skipValue() throws IOException {
/*     */     String unused;
/* 277 */     JsonToken peeked = peek();
/* 278 */     switch (peeked) {
/*     */       case NAME:
/* 280 */         unused = nextName(true);
/*     */       
/*     */       case END_ARRAY:
/* 283 */         endArray();
/*     */       
/*     */       case END_OBJECT:
/* 286 */         endObject();
/*     */       
/*     */       case END_DOCUMENT:
/*     */         return;
/*     */     } 
/*     */     
/* 292 */     popStack();
/* 293 */     if (this.stackSize > 0) {
/* 294 */       this.pathIndices[this.stackSize - 1] = this.pathIndices[this.stackSize - 1] + 1;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 301 */     return getClass().getSimpleName() + locationString();
/*     */   }
/*     */   
/*     */   public void promoteNameToValue() throws IOException {
/* 305 */     expect(JsonToken.NAME);
/* 306 */     Iterator<?> i = (Iterator)peekStack();
/* 307 */     Map.Entry<?, ?> entry = (Map.Entry<?, ?>)i.next();
/* 308 */     push(entry.getValue());
/* 309 */     push(new JsonPrimitive((String)entry.getKey()));
/*     */   }
/*     */   
/*     */   private void push(Object newTop) {
/* 313 */     if (this.stackSize == this.stack.length) {
/* 314 */       int newLength = this.stackSize * 2;
/* 315 */       this.stack = Arrays.copyOf(this.stack, newLength);
/* 316 */       this.pathIndices = Arrays.copyOf(this.pathIndices, newLength);
/* 317 */       this.pathNames = Arrays.<String>copyOf(this.pathNames, newLength);
/*     */     } 
/* 319 */     this.stack[this.stackSize++] = newTop;
/*     */   }
/*     */   
/*     */   private String getPath(boolean usePreviousPath) {
/* 323 */     StringBuilder result = (new StringBuilder()).append('$');
/* 324 */     for (int i = 0; i < this.stackSize; i++) {
/* 325 */       if (this.stack[i] instanceof JsonArray) {
/* 326 */         if (++i < this.stackSize && this.stack[i] instanceof Iterator) {
/* 327 */           int pathIndex = this.pathIndices[i];
/*     */ 
/*     */ 
/*     */           
/* 331 */           if (usePreviousPath && pathIndex > 0 && (i == this.stackSize - 1 || i == this.stackSize - 2)) {
/* 332 */             pathIndex--;
/*     */           }
/* 334 */           result.append('[').append(pathIndex).append(']');
/*     */         } 
/* 336 */       } else if (this.stack[i] instanceof JsonObject && 
/* 337 */         ++i < this.stackSize && this.stack[i] instanceof Iterator) {
/* 338 */         result.append('.');
/* 339 */         if (this.pathNames[i] != null) {
/* 340 */           result.append(this.pathNames[i]);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 345 */     return result.toString();
/*     */   }
/*     */   
/*     */   public String getPreviousPath() {
/* 349 */     return getPath(true);
/*     */   }
/*     */   
/*     */   public String getPath() {
/* 353 */     return getPath(false);
/*     */   }
/*     */   
/*     */   private String locationString() {
/* 357 */     return " at path " + getPath();
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\com\google\gson\internal\bind\JsonTreeReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */