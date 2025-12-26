/*     */ package com.google.gson.internal.bind;
/*     */ 
/*     */ import com.google.gson.FieldNamingStrategy;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.JsonIOException;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import com.google.gson.ReflectionAccessFilter;
/*     */ import com.google.gson.TypeAdapter;
/*     */ import com.google.gson.TypeAdapterFactory;
/*     */ import com.google.gson.annotations.JsonAdapter;
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import com.google.gson.internal.;
/*     */ import com.google.gson.internal.ConstructorConstructor;
/*     */ import com.google.gson.internal.Excluder;
/*     */ import com.google.gson.internal.ObjectConstructor;
/*     */ import com.google.gson.internal.Primitives;
/*     */ import com.google.gson.internal.ReflectionAccessFilterHelper;
/*     */ import com.google.gson.internal.reflect.ReflectionHelper;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import com.google.gson.stream.JsonReader;
/*     */ import com.google.gson.stream.JsonToken;
/*     */ import com.google.gson.stream.JsonWriter;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.AccessibleObject;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Member;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
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
/*     */ public final class ReflectiveTypeAdapterFactory
/*     */   implements TypeAdapterFactory
/*     */ {
/*     */   private final ConstructorConstructor constructorConstructor;
/*     */   private final FieldNamingStrategy fieldNamingPolicy;
/*     */   private final Excluder excluder;
/*     */   private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
/*     */   private final List<ReflectionAccessFilter> reflectionFilters;
/*     */   
/*     */   public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor, FieldNamingStrategy fieldNamingPolicy, Excluder excluder, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory, List<ReflectionAccessFilter> reflectionFilters) {
/*  72 */     this.constructorConstructor = constructorConstructor;
/*  73 */     this.fieldNamingPolicy = fieldNamingPolicy;
/*  74 */     this.excluder = excluder;
/*  75 */     this.jsonAdapterFactory = jsonAdapterFactory;
/*  76 */     this.reflectionFilters = reflectionFilters;
/*     */   }
/*     */   
/*     */   private boolean includeField(Field f, boolean serialize) {
/*  80 */     return (!this.excluder.excludeClass(f.getType(), serialize) && !this.excluder.excludeField(f, serialize));
/*     */   }
/*     */ 
/*     */   
/*     */   private List<String> getFieldNames(Field f) {
/*  85 */     SerializedName annotation = f.<SerializedName>getAnnotation(SerializedName.class);
/*  86 */     if (annotation == null) {
/*  87 */       String name = this.fieldNamingPolicy.translateName(f);
/*  88 */       return Collections.singletonList(name);
/*     */     } 
/*     */     
/*  91 */     String serializedName = annotation.value();
/*  92 */     String[] alternates = annotation.alternate();
/*  93 */     if (alternates.length == 0) {
/*  94 */       return Collections.singletonList(serializedName);
/*     */     }
/*     */     
/*  97 */     List<String> fieldNames = new ArrayList<>(alternates.length + 1);
/*  98 */     fieldNames.add(serializedName);
/*  99 */     Collections.addAll(fieldNames, alternates);
/* 100 */     return fieldNames;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
/* 105 */     Class<? super T> raw = type.getRawType();
/*     */     
/* 107 */     if (!Object.class.isAssignableFrom(raw)) {
/* 108 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 112 */     ReflectionAccessFilter.FilterResult filterResult = ReflectionAccessFilterHelper.getFilterResult(this.reflectionFilters, raw);
/* 113 */     if (filterResult == ReflectionAccessFilter.FilterResult.BLOCK_ALL) {
/* 114 */       throw new JsonIOException("ReflectionAccessFilter does not permit using reflection for " + raw + ". Register a TypeAdapter for this type or adjust the access filter.");
/*     */     }
/*     */ 
/*     */     
/* 118 */     boolean blockInaccessible = (filterResult == ReflectionAccessFilter.FilterResult.BLOCK_INACCESSIBLE);
/*     */ 
/*     */ 
/*     */     
/* 122 */     if (ReflectionHelper.isRecord(raw)) {
/*     */ 
/*     */       
/* 125 */       TypeAdapter<T> adapter = new RecordAdapter<>((Class)raw, getBoundFields(gson, type, raw, blockInaccessible, true), blockInaccessible);
/* 126 */       return adapter;
/*     */     } 
/*     */     
/* 129 */     ObjectConstructor<T> constructor = this.constructorConstructor.get(type);
/* 130 */     return new FieldReflectionAdapter<>(constructor, getBoundFields(gson, type, raw, blockInaccessible, false));
/*     */   }
/*     */   
/*     */   private static <M extends AccessibleObject & Member> void checkAccessible(Object object, M member) {
/* 134 */     if (!ReflectionAccessFilterHelper.canAccess((AccessibleObject)member, Modifier.isStatic(((Member)member).getModifiers()) ? null : object)) {
/* 135 */       String memberDescription = ReflectionHelper.getAccessibleObjectDescription((AccessibleObject)member, true);
/* 136 */       throw new JsonIOException(memberDescription + " is not accessible and ReflectionAccessFilter does not permit making it accessible. Register a TypeAdapter for the declaring type, adjust the access filter or increase the visibility of the element and its declaring type.");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BoundField createBoundField(final Gson context, final Field field, final Method accessor, String name, final TypeToken<?> fieldType, boolean serialize, boolean deserialize, final boolean blockInaccessible) {
/* 147 */     final boolean isPrimitive = Primitives.isPrimitive(fieldType.getRawType());
/*     */     
/* 149 */     int modifiers = field.getModifiers();
/* 150 */     final boolean isStaticFinalField = (Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers));
/*     */     
/* 152 */     JsonAdapter annotation = field.<JsonAdapter>getAnnotation(JsonAdapter.class);
/* 153 */     TypeAdapter<?> mapped = null;
/* 154 */     if (annotation != null)
/*     */     {
/* 156 */       mapped = this.jsonAdapterFactory.getTypeAdapter(this.constructorConstructor, context, fieldType, annotation);
/*     */     }
/*     */     
/* 159 */     final boolean jsonAdapterPresent = (mapped != null);
/* 160 */     if (mapped == null) mapped = context.getAdapter(fieldType);
/*     */ 
/*     */     
/* 163 */     final TypeAdapter<Object> typeAdapter = (TypeAdapter)mapped;
/* 164 */     return new BoundField(name, field.getName(), serialize, deserialize) {
/*     */         void write(JsonWriter writer, Object source) throws IOException, IllegalAccessException {
/*     */           Object fieldValue;
/* 167 */           if (!this.serialized)
/* 168 */             return;  if (blockInaccessible) {
/* 169 */             if (accessor == null) {
/* 170 */               ReflectiveTypeAdapterFactory.checkAccessible(source, (M)field);
/*     */             }
/*     */             else {
/*     */               
/* 174 */               ReflectiveTypeAdapterFactory.checkAccessible(source, (M)accessor);
/*     */             } 
/*     */           }
/*     */ 
/*     */           
/* 179 */           if (accessor != null) {
/*     */             try {
/* 181 */               fieldValue = accessor.invoke(source, new Object[0]);
/* 182 */             } catch (InvocationTargetException e) {
/* 183 */               String accessorDescription = ReflectionHelper.getAccessibleObjectDescription(accessor, false);
/* 184 */               throw new JsonIOException("Accessor " + accessorDescription + " threw exception", e.getCause());
/*     */             } 
/*     */           } else {
/* 187 */             fieldValue = field.get(source);
/*     */           } 
/* 189 */           if (fieldValue == source) {
/*     */             return;
/*     */           }
/*     */           
/* 193 */           writer.name(this.name);
/*     */           
/* 195 */           TypeAdapter<Object> t = jsonAdapterPresent ? typeAdapter : new TypeAdapterRuntimeTypeWrapper(context, typeAdapter, fieldType.getType());
/* 196 */           t.write(writer, fieldValue);
/*     */         }
/*     */ 
/*     */         
/*     */         void readIntoArray(JsonReader reader, int index, Object[] target) throws IOException, JsonParseException {
/* 201 */           Object fieldValue = typeAdapter.read(reader);
/* 202 */           if (fieldValue == null && isPrimitive) {
/* 203 */             throw new JsonParseException("null is not allowed as value for record component '" + this.fieldName + "' of primitive type; at path " + reader
/* 204 */                 .getPath());
/*     */           }
/* 206 */           target[index] = fieldValue;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         void readIntoField(JsonReader reader, Object target) throws IOException, IllegalAccessException {
/* 212 */           Object fieldValue = typeAdapter.read(reader);
/* 213 */           if (fieldValue != null || !isPrimitive) {
/* 214 */             if (blockInaccessible) {
/* 215 */               ReflectiveTypeAdapterFactory.checkAccessible(target, (M)field);
/* 216 */             } else if (isStaticFinalField) {
/*     */ 
/*     */               
/* 219 */               String fieldDescription = ReflectionHelper.getAccessibleObjectDescription(field, false);
/* 220 */               throw new JsonIOException("Cannot set value of 'static final' " + fieldDescription);
/*     */             } 
/* 222 */             field.set(target, fieldValue);
/*     */           } 
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   private Map<String, BoundField> getBoundFields(Gson context, TypeToken<?> type, Class<?> raw, boolean blockInaccessible, boolean isRecord) {
/* 230 */     Map<String, BoundField> result = new LinkedHashMap<>();
/* 231 */     if (raw.isInterface()) {
/* 232 */       return result;
/*     */     }
/*     */     
/* 235 */     Type declaredType = type.getType();
/* 236 */     Class<?> originalRaw = raw;
/* 237 */     while (raw != Object.class) {
/* 238 */       Field[] fields = raw.getDeclaredFields();
/*     */ 
/*     */       
/* 241 */       if (raw != originalRaw && fields.length > 0) {
/* 242 */         ReflectionAccessFilter.FilterResult filterResult = ReflectionAccessFilterHelper.getFilterResult(this.reflectionFilters, raw);
/* 243 */         if (filterResult == ReflectionAccessFilter.FilterResult.BLOCK_ALL) {
/* 244 */           throw new JsonIOException("ReflectionAccessFilter does not permit using reflection for " + raw + " (supertype of " + originalRaw + "). Register a TypeAdapter for this type or adjust the access filter.");
/*     */         }
/*     */ 
/*     */         
/* 248 */         blockInaccessible = (filterResult == ReflectionAccessFilter.FilterResult.BLOCK_INACCESSIBLE);
/*     */       } 
/*     */       
/* 251 */       for (Field field : fields) {
/* 252 */         boolean serialize = includeField(field, true);
/* 253 */         boolean deserialize = includeField(field, false);
/* 254 */         if (serialize || deserialize) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 259 */           Method accessor = null;
/* 260 */           if (isRecord)
/*     */           {
/*     */ 
/*     */             
/* 264 */             if (Modifier.isStatic(field.getModifiers())) {
/* 265 */               deserialize = false;
/*     */             } else {
/* 267 */               accessor = ReflectionHelper.getAccessor(raw, field);
/*     */               
/* 269 */               if (!blockInaccessible) {
/* 270 */                 ReflectionHelper.makeAccessible(accessor);
/*     */               }
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 276 */               if (accessor.getAnnotation(SerializedName.class) != null && field
/* 277 */                 .getAnnotation(SerializedName.class) == null) {
/* 278 */                 String methodDescription = ReflectionHelper.getAccessibleObjectDescription(accessor, false);
/* 279 */                 throw new JsonIOException("@SerializedName on " + methodDescription + " is not supported");
/*     */               } 
/*     */             } 
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 286 */           if (!blockInaccessible && accessor == null) {
/* 287 */             ReflectionHelper.makeAccessible(field);
/*     */           }
/* 289 */           Type fieldType = .Gson.Types.resolve(type.getType(), raw, field.getGenericType());
/* 290 */           List<String> fieldNames = getFieldNames(field);
/* 291 */           BoundField previous = null;
/* 292 */           for (int i = 0, size = fieldNames.size(); i < size; i++) {
/* 293 */             String name = fieldNames.get(i);
/* 294 */             if (i != 0) serialize = false; 
/* 295 */             BoundField boundField = createBoundField(context, field, accessor, name, 
/* 296 */                 TypeToken.get(fieldType), serialize, deserialize, blockInaccessible);
/* 297 */             BoundField replaced = result.put(name, boundField);
/* 298 */             if (previous == null) previous = replaced; 
/*     */           } 
/* 300 */           if (previous != null) {
/* 301 */             throw new IllegalArgumentException(declaredType + " declares multiple JSON fields named " + previous.name);
/*     */           }
/*     */         } 
/*     */       } 
/* 305 */       type = TypeToken.get(.Gson.Types.resolve(type.getType(), raw, raw.getGenericSuperclass()));
/* 306 */       raw = type.getRawType();
/*     */     } 
/* 308 */     return result;
/*     */   }
/*     */   
/*     */   static abstract class BoundField
/*     */   {
/*     */     final String name;
/*     */     final String fieldName;
/*     */     final boolean serialized;
/*     */     final boolean deserialized;
/*     */     
/*     */     protected BoundField(String name, String fieldName, boolean serialized, boolean deserialized) {
/* 319 */       this.name = name;
/* 320 */       this.fieldName = fieldName;
/* 321 */       this.serialized = serialized;
/* 322 */       this.deserialized = deserialized;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     abstract void write(JsonWriter param1JsonWriter, Object param1Object) throws IOException, IllegalAccessException;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     abstract void readIntoArray(JsonReader param1JsonReader, int param1Int, Object[] param1ArrayOfObject) throws IOException, JsonParseException;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     abstract void readIntoField(JsonReader param1JsonReader, Object param1Object) throws IOException, IllegalAccessException;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static abstract class Adapter<T, A>
/*     */     extends TypeAdapter<T>
/*     */   {
/*     */     final Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Adapter(Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields) {
/* 353 */       this.boundFields = boundFields;
/*     */     }
/*     */ 
/*     */     
/*     */     public void write(JsonWriter out, T value) throws IOException {
/* 358 */       if (value == null) {
/* 359 */         out.nullValue();
/*     */         
/*     */         return;
/*     */       } 
/* 363 */       out.beginObject();
/*     */       try {
/* 365 */         for (ReflectiveTypeAdapterFactory.BoundField boundField : this.boundFields.values()) {
/* 366 */           boundField.write(out, value);
/*     */         }
/* 368 */       } catch (IllegalAccessException e) {
/* 369 */         throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
/*     */       } 
/* 371 */       out.endObject();
/*     */     }
/*     */ 
/*     */     
/*     */     public T read(JsonReader in) throws IOException {
/* 376 */       if (in.peek() == JsonToken.NULL) {
/* 377 */         in.nextNull();
/* 378 */         return null;
/*     */       } 
/*     */       
/* 381 */       A accumulator = createAccumulator();
/*     */       
/*     */       try {
/* 384 */         in.beginObject();
/* 385 */         while (in.hasNext()) {
/* 386 */           String name = in.nextName();
/* 387 */           ReflectiveTypeAdapterFactory.BoundField field = this.boundFields.get(name);
/* 388 */           if (field == null || !field.deserialized) {
/* 389 */             in.skipValue(); continue;
/*     */           } 
/* 391 */           readField(accumulator, in, field);
/*     */         }
/*     */       
/* 394 */       } catch (IllegalStateException e) {
/* 395 */         throw new JsonSyntaxException(e);
/* 396 */       } catch (IllegalAccessException e) {
/* 397 */         throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
/*     */       } 
/* 399 */       in.endObject();
/* 400 */       return finalize(accumulator);
/*     */     }
/*     */ 
/*     */     
/*     */     abstract A createAccumulator();
/*     */ 
/*     */     
/*     */     abstract void readField(A param1A, JsonReader param1JsonReader, ReflectiveTypeAdapterFactory.BoundField param1BoundField) throws IllegalAccessException, IOException;
/*     */ 
/*     */     
/*     */     abstract T finalize(A param1A);
/*     */   }
/*     */   
/*     */   private static final class FieldReflectionAdapter<T>
/*     */     extends Adapter<T, T>
/*     */   {
/*     */     private final ObjectConstructor<T> constructor;
/*     */     
/*     */     FieldReflectionAdapter(ObjectConstructor<T> constructor, Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields) {
/* 419 */       super(boundFields);
/* 420 */       this.constructor = constructor;
/*     */     }
/*     */ 
/*     */     
/*     */     T createAccumulator() {
/* 425 */       return (T)this.constructor.construct();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     void readField(T accumulator, JsonReader in, ReflectiveTypeAdapterFactory.BoundField field) throws IllegalAccessException, IOException {
/* 431 */       field.readIntoField(in, accumulator);
/*     */     }
/*     */ 
/*     */     
/*     */     T finalize(T accumulator) {
/* 436 */       return accumulator;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class RecordAdapter<T> extends Adapter<T, Object[]> {
/* 441 */     static final Map<Class<?>, Object> PRIMITIVE_DEFAULTS = primitiveDefaults();
/*     */ 
/*     */     
/*     */     private final Constructor<T> constructor;
/*     */     
/*     */     private final Object[] constructorArgsDefaults;
/*     */     
/* 448 */     private final Map<String, Integer> componentIndices = new HashMap<>();
/*     */     
/*     */     RecordAdapter(Class<T> raw, Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields, boolean blockInaccessible) {
/* 451 */       super(boundFields);
/* 452 */       this.constructor = ReflectionHelper.getCanonicalRecordConstructor(raw);
/*     */       
/* 454 */       if (blockInaccessible) {
/* 455 */         ReflectiveTypeAdapterFactory.checkAccessible(null, (M)this.constructor);
/*     */       } else {
/*     */         
/* 458 */         ReflectionHelper.makeAccessible(this.constructor);
/*     */       } 
/*     */       
/* 461 */       String[] componentNames = ReflectionHelper.getRecordComponentNames(raw);
/* 462 */       for (int i = 0; i < componentNames.length; i++) {
/* 463 */         this.componentIndices.put(componentNames[i], Integer.valueOf(i));
/*     */       }
/* 465 */       Class<?>[] parameterTypes = this.constructor.getParameterTypes();
/*     */ 
/*     */ 
/*     */       
/* 469 */       this.constructorArgsDefaults = new Object[parameterTypes.length];
/* 470 */       for (int j = 0; j < parameterTypes.length; j++)
/*     */       {
/* 472 */         this.constructorArgsDefaults[j] = PRIMITIVE_DEFAULTS.get(parameterTypes[j]);
/*     */       }
/*     */     }
/*     */     
/*     */     private static Map<Class<?>, Object> primitiveDefaults() {
/* 477 */       Map<Class<?>, Object> zeroes = new HashMap<>();
/* 478 */       zeroes.put(byte.class, Byte.valueOf((byte)0));
/* 479 */       zeroes.put(short.class, Short.valueOf((short)0));
/* 480 */       zeroes.put(int.class, Integer.valueOf(0));
/* 481 */       zeroes.put(long.class, Long.valueOf(0L));
/* 482 */       zeroes.put(float.class, Float.valueOf(0.0F));
/* 483 */       zeroes.put(double.class, Double.valueOf(0.0D));
/* 484 */       zeroes.put(char.class, Character.valueOf(false));
/* 485 */       zeroes.put(boolean.class, Boolean.valueOf(false));
/* 486 */       return zeroes;
/*     */     }
/*     */ 
/*     */     
/*     */     Object[] createAccumulator() {
/* 491 */       return (Object[])this.constructorArgsDefaults.clone();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     void readField(Object[] accumulator, JsonReader in, ReflectiveTypeAdapterFactory.BoundField field) throws IOException {
/* 497 */       Integer componentIndex = this.componentIndices.get(field.fieldName);
/* 498 */       if (componentIndex == null) {
/* 499 */         throw new IllegalStateException("Could not find the index in the constructor '" + 
/* 500 */             ReflectionHelper.constructorToString(this.constructor) + "' for field with name '" + field.fieldName + "', unable to determine which argument in the constructor the field corresponds to. This is unexpected behavior, as we expect the RecordComponents to have the same names as the fields in the Java class, and that the order of the RecordComponents is the same as the order of the canonical constructor parameters.");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 507 */       field.readIntoArray(in, componentIndex.intValue(), accumulator);
/*     */     }
/*     */ 
/*     */     
/*     */     T finalize(Object[] accumulator) {
/*     */       try {
/* 513 */         return this.constructor.newInstance(accumulator);
/* 514 */       } catch (IllegalAccessException e) {
/* 515 */         throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
/*     */ 
/*     */       
/*     */       }
/* 519 */       catch (InstantiationException|IllegalArgumentException e) {
/* 520 */         throw new RuntimeException("Failed to invoke constructor '" + 
/* 521 */             ReflectionHelper.constructorToString(this.constructor) + "' with args " + 
/* 522 */             Arrays.toString(accumulator), e);
/*     */       }
/* 524 */       catch (InvocationTargetException e) {
/*     */         
/* 526 */         throw new RuntimeException("Failed to invoke constructor '" + 
/* 527 */             ReflectionHelper.constructorToString(this.constructor) + "' with args " + 
/* 528 */             Arrays.toString(accumulator), e.getCause());
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\com\google\gson\internal\bind\ReflectiveTypeAdapterFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */