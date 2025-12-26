/*     */ package com.google.gson;
/*     */ 
/*     */ import com.google.gson.internal.LinkedTreeMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ public final class JsonObject
/*     */   extends JsonElement
/*     */ {
/*  37 */   private final LinkedTreeMap<String, JsonElement> members = new LinkedTreeMap(false);
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
/*     */   public JsonObject deepCopy() {
/*  53 */     JsonObject result = new JsonObject();
/*  54 */     for (Map.Entry<String, JsonElement> entry : (Iterable<Map.Entry<String, JsonElement>>)this.members.entrySet()) {
/*  55 */       result.add(entry.getKey(), ((JsonElement)entry.getValue()).deepCopy());
/*     */     }
/*  57 */     return result;
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
/*     */   public void add(String property, JsonElement value) {
/*  69 */     this.members.put(property, (value == null) ? JsonNull.INSTANCE : value);
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
/*     */   public JsonElement remove(String property) {
/*  81 */     return (JsonElement)this.members.remove(property);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addProperty(String property, String value) {
/*  92 */     add(property, (value == null) ? JsonNull.INSTANCE : new JsonPrimitive(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addProperty(String property, Number value) {
/* 103 */     add(property, (value == null) ? JsonNull.INSTANCE : new JsonPrimitive(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addProperty(String property, Boolean value) {
/* 114 */     add(property, (value == null) ? JsonNull.INSTANCE : new JsonPrimitive(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addProperty(String property, Character value) {
/* 125 */     add(property, (value == null) ? JsonNull.INSTANCE : new JsonPrimitive(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Map.Entry<String, JsonElement>> entrySet() {
/* 135 */     return this.members.entrySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<String> keySet() {
/* 145 */     return this.members.keySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 155 */     return this.members.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean has(String memberName) {
/* 165 */     return this.members.containsKey(memberName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonElement get(String memberName) {
/* 175 */     return (JsonElement)this.members.get(memberName);
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
/*     */   public JsonPrimitive getAsJsonPrimitive(String memberName) {
/* 187 */     return (JsonPrimitive)this.members.get(memberName);
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
/*     */   public JsonArray getAsJsonArray(String memberName) {
/* 199 */     return (JsonArray)this.members.get(memberName);
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
/*     */   public JsonObject getAsJsonObject(String memberName) {
/* 211 */     return (JsonObject)this.members.get(memberName);
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
/*     */   
/*     */   public Map<String, JsonElement> asMap() {
/* 227 */     return (Map<String, JsonElement>)this.members;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 237 */     return (o == this || (o instanceof JsonObject && ((JsonObject)o).members
/* 238 */       .equals(this.members)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 247 */     return this.members.hashCode();
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\com\google\gson\JsonObject.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */