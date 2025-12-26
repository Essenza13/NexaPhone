/*      */ package com.google.gson;
/*      */ 
/*      */ import com.google.gson.internal.ConstructorConstructor;
/*      */ import com.google.gson.internal.Excluder;
/*      */ import com.google.gson.internal.LazilyParsedNumber;
/*      */ import com.google.gson.internal.Primitives;
/*      */ import com.google.gson.internal.Streams;
/*      */ import com.google.gson.internal.bind.ArrayTypeAdapter;
/*      */ import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
/*      */ import com.google.gson.internal.bind.DateTypeAdapter;
/*      */ import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
/*      */ import com.google.gson.internal.bind.JsonTreeReader;
/*      */ import com.google.gson.internal.bind.JsonTreeWriter;
/*      */ import com.google.gson.internal.bind.MapTypeAdapterFactory;
/*      */ import com.google.gson.internal.bind.NumberTypeAdapter;
/*      */ import com.google.gson.internal.bind.ObjectTypeAdapter;
/*      */ import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
/*      */ import com.google.gson.internal.bind.SerializationDelegatingTypeAdapter;
/*      */ import com.google.gson.internal.bind.TypeAdapters;
/*      */ import com.google.gson.internal.sql.SqlTypesSupport;
/*      */ import com.google.gson.reflect.TypeToken;
/*      */ import com.google.gson.stream.JsonReader;
/*      */ import com.google.gson.stream.JsonToken;
/*      */ import com.google.gson.stream.JsonWriter;
/*      */ import com.google.gson.stream.MalformedJsonException;
/*      */ import java.io.EOFException;
/*      */ import java.io.IOException;
/*      */ import java.io.Reader;
/*      */ import java.io.StringReader;
/*      */ import java.io.StringWriter;
/*      */ import java.io.Writer;
/*      */ import java.lang.reflect.Type;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Objects;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ import java.util.concurrent.ConcurrentMap;
/*      */ import java.util.concurrent.atomic.AtomicLong;
/*      */ import java.util.concurrent.atomic.AtomicLongArray;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class Gson
/*      */ {
/*      */   static final boolean DEFAULT_JSON_NON_EXECUTABLE = false;
/*      */   static final boolean DEFAULT_LENIENT = false;
/*      */   static final boolean DEFAULT_PRETTY_PRINT = false;
/*      */   static final boolean DEFAULT_ESCAPE_HTML = true;
/*      */   static final boolean DEFAULT_SERIALIZE_NULLS = false;
/*      */   static final boolean DEFAULT_COMPLEX_MAP_KEYS = false;
/*      */   static final boolean DEFAULT_SPECIALIZE_FLOAT_VALUES = false;
/*      */   static final boolean DEFAULT_USE_JDK_UNSAFE = true;
/*  150 */   static final String DEFAULT_DATE_PATTERN = null;
/*  151 */   static final FieldNamingStrategy DEFAULT_FIELD_NAMING_STRATEGY = FieldNamingPolicy.IDENTITY;
/*  152 */   static final ToNumberStrategy DEFAULT_OBJECT_TO_NUMBER_STRATEGY = ToNumberPolicy.DOUBLE;
/*  153 */   static final ToNumberStrategy DEFAULT_NUMBER_TO_NUMBER_STRATEGY = ToNumberPolicy.LAZILY_PARSED_NUMBER;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String JSON_NON_EXECUTABLE_PREFIX = ")]}'\n";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  164 */   private final ThreadLocal<Map<TypeToken<?>, FutureTypeAdapter<?>>> calls = new ThreadLocal<>();
/*      */ 
/*      */   
/*  167 */   private final ConcurrentMap<TypeToken<?>, TypeAdapter<?>> typeTokenCache = new ConcurrentHashMap<>();
/*      */ 
/*      */   
/*      */   private final ConstructorConstructor constructorConstructor;
/*      */ 
/*      */   
/*      */   private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
/*      */ 
/*      */   
/*      */   final List<TypeAdapterFactory> factories;
/*      */ 
/*      */   
/*      */   final Excluder excluder;
/*      */ 
/*      */   
/*      */   final FieldNamingStrategy fieldNamingStrategy;
/*      */ 
/*      */   
/*      */   final Map<Type, InstanceCreator<?>> instanceCreators;
/*      */ 
/*      */   
/*      */   final boolean serializeNulls;
/*      */ 
/*      */   
/*      */   final boolean complexMapKeySerialization;
/*      */ 
/*      */   
/*      */   final boolean generateNonExecutableJson;
/*      */ 
/*      */   
/*      */   final boolean htmlSafe;
/*      */ 
/*      */   
/*      */   final boolean prettyPrinting;
/*      */ 
/*      */   
/*      */   final boolean lenient;
/*      */ 
/*      */   
/*      */   final boolean serializeSpecialFloatingPointValues;
/*      */   
/*      */   final boolean useJdkUnsafe;
/*      */   
/*      */   final String datePattern;
/*      */   
/*      */   final int dateStyle;
/*      */   
/*      */   final int timeStyle;
/*      */   
/*      */   final LongSerializationPolicy longSerializationPolicy;
/*      */   
/*      */   final List<TypeAdapterFactory> builderFactories;
/*      */   
/*      */   final List<TypeAdapterFactory> builderHierarchyFactories;
/*      */   
/*      */   final ToNumberStrategy objectToNumberStrategy;
/*      */   
/*      */   final ToNumberStrategy numberToNumberStrategy;
/*      */   
/*      */   final List<ReflectionAccessFilter> reflectionFilters;
/*      */ 
/*      */   
/*      */   public Gson() {
/*  230 */     this(Excluder.DEFAULT, DEFAULT_FIELD_NAMING_STRATEGY, 
/*  231 */         Collections.emptyMap(), false, false, false, true, false, false, false, true, LongSerializationPolicy.DEFAULT, DEFAULT_DATE_PATTERN, 2, 2, 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  236 */         Collections.emptyList(), Collections.emptyList(), 
/*  237 */         Collections.emptyList(), DEFAULT_OBJECT_TO_NUMBER_STRATEGY, DEFAULT_NUMBER_TO_NUMBER_STRATEGY, 
/*  238 */         Collections.emptyList());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Gson(Excluder excluder, FieldNamingStrategy fieldNamingStrategy, Map<Type, InstanceCreator<?>> instanceCreators, boolean serializeNulls, boolean complexMapKeySerialization, boolean generateNonExecutableGson, boolean htmlSafe, boolean prettyPrinting, boolean lenient, boolean serializeSpecialFloatingPointValues, boolean useJdkUnsafe, LongSerializationPolicy longSerializationPolicy, String datePattern, int dateStyle, int timeStyle, List<TypeAdapterFactory> builderFactories, List<TypeAdapterFactory> builderHierarchyFactories, List<TypeAdapterFactory> factoriesToBeAdded, ToNumberStrategy objectToNumberStrategy, ToNumberStrategy numberToNumberStrategy, List<ReflectionAccessFilter> reflectionFilters) {
/*  252 */     this.excluder = excluder;
/*  253 */     this.fieldNamingStrategy = fieldNamingStrategy;
/*  254 */     this.instanceCreators = instanceCreators;
/*  255 */     this.constructorConstructor = new ConstructorConstructor(instanceCreators, useJdkUnsafe, reflectionFilters);
/*  256 */     this.serializeNulls = serializeNulls;
/*  257 */     this.complexMapKeySerialization = complexMapKeySerialization;
/*  258 */     this.generateNonExecutableJson = generateNonExecutableGson;
/*  259 */     this.htmlSafe = htmlSafe;
/*  260 */     this.prettyPrinting = prettyPrinting;
/*  261 */     this.lenient = lenient;
/*  262 */     this.serializeSpecialFloatingPointValues = serializeSpecialFloatingPointValues;
/*  263 */     this.useJdkUnsafe = useJdkUnsafe;
/*  264 */     this.longSerializationPolicy = longSerializationPolicy;
/*  265 */     this.datePattern = datePattern;
/*  266 */     this.dateStyle = dateStyle;
/*  267 */     this.timeStyle = timeStyle;
/*  268 */     this.builderFactories = builderFactories;
/*  269 */     this.builderHierarchyFactories = builderHierarchyFactories;
/*  270 */     this.objectToNumberStrategy = objectToNumberStrategy;
/*  271 */     this.numberToNumberStrategy = numberToNumberStrategy;
/*  272 */     this.reflectionFilters = reflectionFilters;
/*      */     
/*  274 */     List<TypeAdapterFactory> factories = new ArrayList<>();
/*      */ 
/*      */     
/*  277 */     factories.add(TypeAdapters.JSON_ELEMENT_FACTORY);
/*  278 */     factories.add(ObjectTypeAdapter.getFactory(objectToNumberStrategy));
/*      */ 
/*      */     
/*  281 */     factories.add(excluder);
/*      */ 
/*      */     
/*  284 */     factories.addAll(factoriesToBeAdded);
/*      */ 
/*      */     
/*  287 */     factories.add(TypeAdapters.STRING_FACTORY);
/*  288 */     factories.add(TypeAdapters.INTEGER_FACTORY);
/*  289 */     factories.add(TypeAdapters.BOOLEAN_FACTORY);
/*  290 */     factories.add(TypeAdapters.BYTE_FACTORY);
/*  291 */     factories.add(TypeAdapters.SHORT_FACTORY);
/*  292 */     TypeAdapter<Number> longAdapter = longAdapter(longSerializationPolicy);
/*  293 */     factories.add(TypeAdapters.newFactory(long.class, Long.class, longAdapter));
/*  294 */     factories.add(TypeAdapters.newFactory(double.class, Double.class, 
/*  295 */           doubleAdapter(serializeSpecialFloatingPointValues)));
/*  296 */     factories.add(TypeAdapters.newFactory(float.class, Float.class, 
/*  297 */           floatAdapter(serializeSpecialFloatingPointValues)));
/*  298 */     factories.add(NumberTypeAdapter.getFactory(numberToNumberStrategy));
/*  299 */     factories.add(TypeAdapters.ATOMIC_INTEGER_FACTORY);
/*  300 */     factories.add(TypeAdapters.ATOMIC_BOOLEAN_FACTORY);
/*  301 */     factories.add(TypeAdapters.newFactory(AtomicLong.class, atomicLongAdapter(longAdapter)));
/*  302 */     factories.add(TypeAdapters.newFactory(AtomicLongArray.class, atomicLongArrayAdapter(longAdapter)));
/*  303 */     factories.add(TypeAdapters.ATOMIC_INTEGER_ARRAY_FACTORY);
/*  304 */     factories.add(TypeAdapters.CHARACTER_FACTORY);
/*  305 */     factories.add(TypeAdapters.STRING_BUILDER_FACTORY);
/*  306 */     factories.add(TypeAdapters.STRING_BUFFER_FACTORY);
/*  307 */     factories.add(TypeAdapters.newFactory(BigDecimal.class, TypeAdapters.BIG_DECIMAL));
/*  308 */     factories.add(TypeAdapters.newFactory(BigInteger.class, TypeAdapters.BIG_INTEGER));
/*      */     
/*  310 */     factories.add(TypeAdapters.newFactory(LazilyParsedNumber.class, TypeAdapters.LAZILY_PARSED_NUMBER));
/*  311 */     factories.add(TypeAdapters.URL_FACTORY);
/*  312 */     factories.add(TypeAdapters.URI_FACTORY);
/*  313 */     factories.add(TypeAdapters.UUID_FACTORY);
/*  314 */     factories.add(TypeAdapters.CURRENCY_FACTORY);
/*  315 */     factories.add(TypeAdapters.LOCALE_FACTORY);
/*  316 */     factories.add(TypeAdapters.INET_ADDRESS_FACTORY);
/*  317 */     factories.add(TypeAdapters.BIT_SET_FACTORY);
/*  318 */     factories.add(DateTypeAdapter.FACTORY);
/*  319 */     factories.add(TypeAdapters.CALENDAR_FACTORY);
/*      */     
/*  321 */     if (SqlTypesSupport.SUPPORTS_SQL_TYPES) {
/*  322 */       factories.add(SqlTypesSupport.TIME_FACTORY);
/*  323 */       factories.add(SqlTypesSupport.DATE_FACTORY);
/*  324 */       factories.add(SqlTypesSupport.TIMESTAMP_FACTORY);
/*      */     } 
/*      */     
/*  327 */     factories.add(ArrayTypeAdapter.FACTORY);
/*  328 */     factories.add(TypeAdapters.CLASS_FACTORY);
/*      */ 
/*      */     
/*  331 */     factories.add(new CollectionTypeAdapterFactory(this.constructorConstructor));
/*  332 */     factories.add(new MapTypeAdapterFactory(this.constructorConstructor, complexMapKeySerialization));
/*  333 */     this.jsonAdapterFactory = new JsonAdapterAnnotationTypeAdapterFactory(this.constructorConstructor);
/*  334 */     factories.add(this.jsonAdapterFactory);
/*  335 */     factories.add(TypeAdapters.ENUM_FACTORY);
/*  336 */     factories.add(new ReflectiveTypeAdapterFactory(this.constructorConstructor, fieldNamingStrategy, excluder, this.jsonAdapterFactory, reflectionFilters));
/*      */ 
/*      */     
/*  339 */     this.factories = Collections.unmodifiableList(factories);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GsonBuilder newBuilder() {
/*  350 */     return new GsonBuilder(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public Excluder excluder() {
/*  359 */     return this.excluder;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public FieldNamingStrategy fieldNamingStrategy() {
/*  368 */     return this.fieldNamingStrategy;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean serializeNulls() {
/*  378 */     return this.serializeNulls;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean htmlSafe() {
/*  388 */     return this.htmlSafe;
/*      */   }
/*      */   
/*      */   private TypeAdapter<Number> doubleAdapter(boolean serializeSpecialFloatingPointValues) {
/*  392 */     if (serializeSpecialFloatingPointValues) {
/*  393 */       return TypeAdapters.DOUBLE;
/*      */     }
/*  395 */     return new TypeAdapter<Number>() {
/*      */         public Double read(JsonReader in) throws IOException {
/*  397 */           if (in.peek() == JsonToken.NULL) {
/*  398 */             in.nextNull();
/*  399 */             return null;
/*      */           } 
/*  401 */           return Double.valueOf(in.nextDouble());
/*      */         }
/*      */         public void write(JsonWriter out, Number value) throws IOException {
/*  404 */           if (value == null) {
/*  405 */             out.nullValue();
/*      */             return;
/*      */           } 
/*  408 */           double doubleValue = value.doubleValue();
/*  409 */           Gson.checkValidFloatingPoint(doubleValue);
/*  410 */           out.value(doubleValue);
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   private TypeAdapter<Number> floatAdapter(boolean serializeSpecialFloatingPointValues) {
/*  416 */     if (serializeSpecialFloatingPointValues) {
/*  417 */       return TypeAdapters.FLOAT;
/*      */     }
/*  419 */     return new TypeAdapter<Number>() {
/*      */         public Float read(JsonReader in) throws IOException {
/*  421 */           if (in.peek() == JsonToken.NULL) {
/*  422 */             in.nextNull();
/*  423 */             return null;
/*      */           } 
/*  425 */           return Float.valueOf((float)in.nextDouble());
/*      */         }
/*      */         public void write(JsonWriter out, Number value) throws IOException {
/*  428 */           if (value == null) {
/*  429 */             out.nullValue();
/*      */             return;
/*      */           } 
/*  432 */           float floatValue = value.floatValue();
/*  433 */           Gson.checkValidFloatingPoint(floatValue);
/*      */ 
/*      */           
/*  436 */           Number floatNumber = (value instanceof Float) ? value : Float.valueOf(floatValue);
/*  437 */           out.value(floatNumber);
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   static void checkValidFloatingPoint(double value) {
/*  443 */     if (Double.isNaN(value) || Double.isInfinite(value)) {
/*  444 */       throw new IllegalArgumentException(value + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static TypeAdapter<Number> longAdapter(LongSerializationPolicy longSerializationPolicy) {
/*  451 */     if (longSerializationPolicy == LongSerializationPolicy.DEFAULT) {
/*  452 */       return TypeAdapters.LONG;
/*      */     }
/*  454 */     return new TypeAdapter<Number>() {
/*      */         public Number read(JsonReader in) throws IOException {
/*  456 */           if (in.peek() == JsonToken.NULL) {
/*  457 */             in.nextNull();
/*  458 */             return null;
/*      */           } 
/*  460 */           return Long.valueOf(in.nextLong());
/*      */         }
/*      */         public void write(JsonWriter out, Number value) throws IOException {
/*  463 */           if (value == null) {
/*  464 */             out.nullValue();
/*      */             return;
/*      */           } 
/*  467 */           out.value(value.toString());
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   private static TypeAdapter<AtomicLong> atomicLongAdapter(final TypeAdapter<Number> longAdapter) {
/*  473 */     return (new TypeAdapter<AtomicLong>() {
/*      */         public void write(JsonWriter out, AtomicLong value) throws IOException {
/*  475 */           longAdapter.write(out, Long.valueOf(value.get()));
/*      */         }
/*      */         public AtomicLong read(JsonReader in) throws IOException {
/*  478 */           Number value = longAdapter.read(in);
/*  479 */           return new AtomicLong(value.longValue());
/*      */         }
/*  481 */       }).nullSafe();
/*      */   }
/*      */   
/*      */   private static TypeAdapter<AtomicLongArray> atomicLongArrayAdapter(final TypeAdapter<Number> longAdapter) {
/*  485 */     return (new TypeAdapter<AtomicLongArray>() {
/*      */         public void write(JsonWriter out, AtomicLongArray value) throws IOException {
/*  487 */           out.beginArray();
/*  488 */           for (int i = 0, length = value.length(); i < length; i++) {
/*  489 */             longAdapter.write(out, Long.valueOf(value.get(i)));
/*      */           }
/*  491 */           out.endArray();
/*      */         }
/*      */         public AtomicLongArray read(JsonReader in) throws IOException {
/*  494 */           List<Long> list = new ArrayList<>();
/*  495 */           in.beginArray();
/*  496 */           while (in.hasNext()) {
/*  497 */             long value = ((Number)longAdapter.read(in)).longValue();
/*  498 */             list.add(Long.valueOf(value));
/*      */           } 
/*  500 */           in.endArray();
/*  501 */           int length = list.size();
/*  502 */           AtomicLongArray array = new AtomicLongArray(length);
/*  503 */           for (int i = 0; i < length; i++) {
/*  504 */             array.set(i, ((Long)list.get(i)).longValue());
/*      */           }
/*  506 */           return array;
/*      */         }
/*  508 */       }).nullSafe();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> TypeAdapter<T> getAdapter(TypeToken<T> type) {
/*  518 */     Objects.requireNonNull(type, "type must not be null");
/*  519 */     TypeAdapter<?> cached = this.typeTokenCache.get(type);
/*  520 */     if (cached != null) {
/*      */       
/*  522 */       TypeAdapter<T> adapter = (TypeAdapter)cached;
/*  523 */       return adapter;
/*      */     } 
/*      */     
/*  526 */     Map<TypeToken<?>, FutureTypeAdapter<?>> threadCalls = this.calls.get();
/*  527 */     boolean requiresThreadLocalCleanup = false;
/*  528 */     if (threadCalls == null) {
/*  529 */       threadCalls = new HashMap<>();
/*  530 */       this.calls.set(threadCalls);
/*  531 */       requiresThreadLocalCleanup = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  536 */     FutureTypeAdapter<T> ongoingCall = (FutureTypeAdapter<T>)threadCalls.get(type);
/*  537 */     if (ongoingCall != null) {
/*  538 */       return (TypeAdapter<T>)ongoingCall;
/*      */     }
/*      */     
/*      */     try {
/*  542 */       FutureTypeAdapter<T> call = new FutureTypeAdapter<>();
/*  543 */       threadCalls.put(type, call);
/*      */       
/*  545 */       for (TypeAdapterFactory factory : this.factories) {
/*  546 */         TypeAdapter<T> candidate = factory.create(this, type);
/*  547 */         if (candidate != null) {
/*      */           
/*  549 */           TypeAdapter<T> existingAdapter = (TypeAdapter<T>)this.typeTokenCache.putIfAbsent(type, candidate);
/*      */           
/*  551 */           if (existingAdapter != null) {
/*  552 */             candidate = existingAdapter;
/*      */           }
/*      */           
/*  555 */           call.setDelegate(candidate);
/*  556 */           return candidate;
/*      */         } 
/*      */       } 
/*  559 */       throw new IllegalArgumentException("GSON (2.10) cannot handle " + type);
/*      */     } finally {
/*  561 */       threadCalls.remove(type);
/*      */       
/*  563 */       if (requiresThreadLocalCleanup) {
/*  564 */         this.calls.remove();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> TypeAdapter<T> getDelegateAdapter(TypeAdapterFactory skipPast, TypeToken<T> type) {
/*      */     JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory;
/*  622 */     if (!this.factories.contains(skipPast)) {
/*  623 */       jsonAdapterAnnotationTypeAdapterFactory = this.jsonAdapterFactory;
/*      */     }
/*      */     
/*  626 */     boolean skipPastFound = false;
/*  627 */     for (TypeAdapterFactory factory : this.factories) {
/*  628 */       if (!skipPastFound) {
/*  629 */         if (factory == jsonAdapterAnnotationTypeAdapterFactory) {
/*  630 */           skipPastFound = true;
/*      */         }
/*      */         
/*      */         continue;
/*      */       } 
/*  635 */       TypeAdapter<T> candidate = factory.create(this, type);
/*  636 */       if (candidate != null) {
/*  637 */         return candidate;
/*      */       }
/*      */     } 
/*  640 */     throw new IllegalArgumentException("GSON cannot serialize " + type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> TypeAdapter<T> getAdapter(Class<T> type) {
/*  650 */     return getAdapter(TypeToken.get(type));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JsonElement toJsonTree(Object src) {
/*  669 */     if (src == null) {
/*  670 */       return JsonNull.INSTANCE;
/*      */     }
/*  672 */     return toJsonTree(src, src.getClass());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JsonElement toJsonTree(Object src, Type typeOfSrc) {
/*  694 */     JsonTreeWriter writer = new JsonTreeWriter();
/*  695 */     toJson(src, typeOfSrc, (JsonWriter)writer);
/*  696 */     return writer.get();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toJson(Object src) {
/*  716 */     if (src == null) {
/*  717 */       return toJson(JsonNull.INSTANCE);
/*      */     }
/*  719 */     return toJson(src, src.getClass());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toJson(Object src, Type typeOfSrc) {
/*  741 */     StringWriter writer = new StringWriter();
/*  742 */     toJson(src, typeOfSrc, writer);
/*  743 */     return writer.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void toJson(Object src, Appendable writer) throws JsonIOException {
/*  765 */     if (src != null) {
/*  766 */       toJson(src, src.getClass(), writer);
/*      */     } else {
/*  768 */       toJson(JsonNull.INSTANCE, writer);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void toJson(Object src, Type typeOfSrc, Appendable writer) throws JsonIOException {
/*      */     try {
/*  794 */       JsonWriter jsonWriter = newJsonWriter(Streams.writerForAppendable(writer));
/*  795 */       toJson(src, typeOfSrc, jsonWriter);
/*  796 */     } catch (IOException e) {
/*  797 */       throw new JsonIOException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void toJson(Object src, Type typeOfSrc, JsonWriter writer) throws JsonIOException {
/*  817 */     TypeAdapter<Object> adapter = getAdapter(TypeToken.get(typeOfSrc));
/*  818 */     boolean oldLenient = writer.isLenient();
/*  819 */     writer.setLenient(true);
/*  820 */     boolean oldHtmlSafe = writer.isHtmlSafe();
/*  821 */     writer.setHtmlSafe(this.htmlSafe);
/*  822 */     boolean oldSerializeNulls = writer.getSerializeNulls();
/*  823 */     writer.setSerializeNulls(this.serializeNulls);
/*      */     try {
/*  825 */       adapter.write(writer, src);
/*  826 */     } catch (IOException e) {
/*  827 */       throw new JsonIOException(e);
/*  828 */     } catch (AssertionError e) {
/*  829 */       AssertionError error = new AssertionError("AssertionError (GSON 2.10): " + e.getMessage());
/*  830 */       error.initCause(e);
/*  831 */       throw error;
/*      */     } finally {
/*  833 */       writer.setLenient(oldLenient);
/*  834 */       writer.setHtmlSafe(oldHtmlSafe);
/*  835 */       writer.setSerializeNulls(oldSerializeNulls);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toJson(JsonElement jsonElement) {
/*  847 */     StringWriter writer = new StringWriter();
/*  848 */     toJson(jsonElement, writer);
/*  849 */     return writer.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void toJson(JsonElement jsonElement, Appendable writer) throws JsonIOException {
/*      */     try {
/*  862 */       JsonWriter jsonWriter = newJsonWriter(Streams.writerForAppendable(writer));
/*  863 */       toJson(jsonElement, jsonWriter);
/*  864 */     } catch (IOException e) {
/*  865 */       throw new JsonIOException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JsonWriter newJsonWriter(Writer writer) throws IOException {
/*  882 */     if (this.generateNonExecutableJson) {
/*  883 */       writer.write(")]}'\n");
/*      */     }
/*  885 */     JsonWriter jsonWriter = new JsonWriter(writer);
/*  886 */     if (this.prettyPrinting) {
/*  887 */       jsonWriter.setIndent("  ");
/*      */     }
/*  889 */     jsonWriter.setHtmlSafe(this.htmlSafe);
/*  890 */     jsonWriter.setLenient(this.lenient);
/*  891 */     jsonWriter.setSerializeNulls(this.serializeNulls);
/*  892 */     return jsonWriter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JsonReader newJsonReader(Reader reader) {
/*  904 */     JsonReader jsonReader = new JsonReader(reader);
/*  905 */     jsonReader.setLenient(this.lenient);
/*  906 */     return jsonReader;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void toJson(JsonElement jsonElement, JsonWriter writer) throws JsonIOException {
/*  923 */     boolean oldLenient = writer.isLenient();
/*  924 */     writer.setLenient(true);
/*  925 */     boolean oldHtmlSafe = writer.isHtmlSafe();
/*  926 */     writer.setHtmlSafe(this.htmlSafe);
/*  927 */     boolean oldSerializeNulls = writer.getSerializeNulls();
/*  928 */     writer.setSerializeNulls(this.serializeNulls);
/*      */     try {
/*  930 */       Streams.write(jsonElement, writer);
/*  931 */     } catch (IOException e) {
/*  932 */       throw new JsonIOException(e);
/*  933 */     } catch (AssertionError e) {
/*  934 */       AssertionError error = new AssertionError("AssertionError (GSON 2.10): " + e.getMessage());
/*  935 */       error.initCause(e);
/*  936 */       throw error;
/*      */     } finally {
/*  938 */       writer.setLenient(oldLenient);
/*  939 */       writer.setHtmlSafe(oldHtmlSafe);
/*  940 */       writer.setSerializeNulls(oldSerializeNulls);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
/*  969 */     T object = fromJson(json, TypeToken.get(classOfT));
/*  970 */     return Primitives.wrap(classOfT).cast(object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> T fromJson(String json, Type typeOfT) throws JsonSyntaxException {
/* 1001 */     return fromJson(json, TypeToken.get(typeOfT));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> T fromJson(String json, TypeToken<T> typeOfT) throws JsonSyntaxException {
/* 1030 */     if (json == null) {
/* 1031 */       return null;
/*      */     }
/* 1033 */     StringReader reader = new StringReader(json);
/* 1034 */     return fromJson(reader, typeOfT);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> T fromJson(Reader json, Class<T> classOfT) throws JsonSyntaxException, JsonIOException {
/* 1062 */     T object = fromJson(json, TypeToken.get(classOfT));
/* 1063 */     return Primitives.wrap(classOfT).cast(object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> T fromJson(Reader json, Type typeOfT) throws JsonIOException, JsonSyntaxException {
/* 1094 */     return fromJson(json, TypeToken.get(typeOfT));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> T fromJson(Reader json, TypeToken<T> typeOfT) throws JsonIOException, JsonSyntaxException {
/* 1123 */     JsonReader jsonReader = newJsonReader(json);
/* 1124 */     T object = fromJson(jsonReader, typeOfT);
/* 1125 */     assertFullConsumption(object, jsonReader);
/* 1126 */     return object;
/*      */   }
/*      */   
/*      */   private static void assertFullConsumption(Object obj, JsonReader reader) {
/*      */     try {
/* 1131 */       if (obj != null && reader.peek() != JsonToken.END_DOCUMENT) {
/* 1132 */         throw new JsonSyntaxException("JSON document was not fully consumed.");
/*      */       }
/* 1134 */     } catch (MalformedJsonException e) {
/* 1135 */       throw new JsonSyntaxException(e);
/* 1136 */     } catch (IOException e) {
/* 1137 */       throw new JsonIOException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> T fromJson(JsonReader reader, Type typeOfT) throws JsonIOException, JsonSyntaxException {
/* 1173 */     return fromJson(reader, TypeToken.get(typeOfT));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> T fromJson(JsonReader reader, TypeToken<T> typeOfT) throws JsonIOException, JsonSyntaxException {
/* 1207 */     boolean isEmpty = true;
/* 1208 */     boolean oldLenient = reader.isLenient();
/* 1209 */     reader.setLenient(true);
/*      */     try {
/* 1211 */       reader.peek();
/* 1212 */       isEmpty = false;
/* 1213 */       TypeAdapter<T> typeAdapter = getAdapter(typeOfT);
/* 1214 */       T object = typeAdapter.read(reader);
/* 1215 */       return object;
/* 1216 */     } catch (EOFException e) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1221 */       if (isEmpty) {
/* 1222 */         return null;
/*      */       }
/* 1224 */       throw new JsonSyntaxException(e);
/* 1225 */     } catch (IllegalStateException e) {
/* 1226 */       throw new JsonSyntaxException(e);
/* 1227 */     } catch (IOException e) {
/*      */       
/* 1229 */       throw new JsonSyntaxException(e);
/* 1230 */     } catch (AssertionError e) {
/* 1231 */       AssertionError error = new AssertionError("AssertionError (GSON 2.10): " + e.getMessage());
/* 1232 */       error.initCause(e);
/* 1233 */       throw error;
/*      */     } finally {
/* 1235 */       reader.setLenient(oldLenient);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> T fromJson(JsonElement json, Class<T> classOfT) throws JsonSyntaxException {
/* 1261 */     T object = fromJson(json, TypeToken.get(classOfT));
/* 1262 */     return Primitives.wrap(classOfT).cast(object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> T fromJson(JsonElement json, Type typeOfT) throws JsonSyntaxException {
/* 1290 */     return fromJson(json, TypeToken.get(typeOfT));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> T fromJson(JsonElement json, TypeToken<T> typeOfT) throws JsonSyntaxException {
/* 1316 */     if (json == null) {
/* 1317 */       return null;
/*      */     }
/* 1319 */     return fromJson((JsonReader)new JsonTreeReader(json), typeOfT);
/*      */   }
/*      */   
/*      */   static class FutureTypeAdapter<T> extends SerializationDelegatingTypeAdapter<T> {
/*      */     private TypeAdapter<T> delegate;
/*      */     
/*      */     public void setDelegate(TypeAdapter<T> typeAdapter) {
/* 1326 */       if (this.delegate != null) {
/* 1327 */         throw new AssertionError();
/*      */       }
/* 1329 */       this.delegate = typeAdapter;
/*      */     }
/*      */     
/*      */     private TypeAdapter<T> delegate() {
/* 1333 */       if (this.delegate == null) {
/* 1334 */         throw new IllegalStateException("Delegate has not been set yet");
/*      */       }
/* 1336 */       return this.delegate;
/*      */     }
/*      */     
/*      */     public TypeAdapter<T> getSerializationDelegate() {
/* 1340 */       return delegate();
/*      */     }
/*      */     
/*      */     public T read(JsonReader in) throws IOException {
/* 1344 */       return delegate().read(in);
/*      */     }
/*      */     
/*      */     public void write(JsonWriter out, T value) throws IOException {
/* 1348 */       delegate().write(out, value);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1354 */     return "{serializeNulls:" + this.serializeNulls + 
/* 1355 */       ",factories:" + 
/* 1356 */       this.factories + ",instanceCreators:" + 
/* 1357 */       this.constructorConstructor + "}";
/*      */   }
/*      */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\com\google\gson\Gson.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */