/*     */ package com.google.gson.internal.reflect;
/*     */ 
/*     */ import com.google.gson.JsonIOException;
/*     */ import java.lang.reflect.AccessibleObject;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReflectionHelper
/*     */ {
/*     */   private static final RecordHelper RECORD_HELPER;
/*     */   
/*     */   static {
/*     */     RecordHelper instance;
/*     */     try {
/*  18 */       instance = new RecordSupportedHelper();
/*  19 */     } catch (NoSuchMethodException e) {
/*  20 */       instance = new RecordNotSupportedHelper();
/*     */     } 
/*  22 */     RECORD_HELPER = instance;
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
/*     */   public static void makeAccessible(AccessibleObject object) throws JsonIOException {
/*     */     try {
/*  35 */       object.setAccessible(true);
/*  36 */     } catch (Exception exception) {
/*  37 */       String description = getAccessibleObjectDescription(object, false);
/*  38 */       throw new JsonIOException("Failed making " + description + " accessible; either increase its visibility or write a custom TypeAdapter for its declaring type.", exception);
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
/*     */   public static String getAccessibleObjectDescription(AccessibleObject object, boolean uppercaseFirstLetter) {
/*     */     String description;
/*  55 */     if (object instanceof Field) {
/*  56 */       Field field = (Field)object;
/*  57 */       description = "field '" + field.getDeclaringClass().getName() + "#" + field.getName() + "'";
/*  58 */     } else if (object instanceof Method) {
/*  59 */       Method method = (Method)object;
/*     */       
/*  61 */       StringBuilder methodSignatureBuilder = new StringBuilder(method.getName());
/*  62 */       appendExecutableParameters(method, methodSignatureBuilder);
/*  63 */       String methodSignature = methodSignatureBuilder.toString();
/*     */       
/*  65 */       description = "method '" + method.getDeclaringClass().getName() + "#" + methodSignature + "'";
/*  66 */     } else if (object instanceof Constructor) {
/*  67 */       description = "constructor '" + constructorToString((Constructor)object) + "'";
/*     */     } else {
/*  69 */       description = "<unknown AccessibleObject> " + object.toString();
/*     */     } 
/*     */     
/*  72 */     if (uppercaseFirstLetter && Character.isLowerCase(description.charAt(0))) {
/*  73 */       description = Character.toUpperCase(description.charAt(0)) + description.substring(1);
/*     */     }
/*  75 */     return description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String constructorToString(Constructor<?> constructor) {
/*  83 */     StringBuilder stringBuilder = new StringBuilder(constructor.getDeclaringClass().getName());
/*  84 */     appendExecutableParameters(constructor, stringBuilder);
/*     */     
/*  86 */     return stringBuilder.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void appendExecutableParameters(AccessibleObject executable, StringBuilder stringBuilder) {
/*  91 */     stringBuilder.append('(');
/*     */ 
/*     */ 
/*     */     
/*  95 */     Class<?>[] parameters = (executable instanceof Method) ? ((Method)executable).getParameterTypes() : ((Constructor)executable).getParameterTypes();
/*  96 */     for (int i = 0; i < parameters.length; i++) {
/*  97 */       if (i > 0) {
/*  98 */         stringBuilder.append(", ");
/*     */       }
/* 100 */       stringBuilder.append(parameters[i].getSimpleName());
/*     */     } 
/*     */     
/* 103 */     stringBuilder.append(')');
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
/*     */   public static String tryMakeAccessible(Constructor<?> constructor) {
/*     */     try {
/* 116 */       constructor.setAccessible(true);
/* 117 */       return null;
/* 118 */     } catch (Exception exception) {
/* 119 */       return "Failed making constructor '" + constructorToString(constructor) + "' accessible; either increase its visibility or write a custom InstanceCreator or TypeAdapter for its declaring type: " + exception
/*     */ 
/*     */         
/* 122 */         .getMessage();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isRecord(Class<?> raw) {
/* 128 */     return RECORD_HELPER.isRecord(raw);
/*     */   }
/*     */   
/*     */   public static String[] getRecordComponentNames(Class<?> raw) {
/* 132 */     return RECORD_HELPER.getRecordComponentNames(raw);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Method getAccessor(Class<?> raw, Field field) {
/* 137 */     return RECORD_HELPER.getAccessor(raw, field);
/*     */   }
/*     */   
/*     */   public static <T> Constructor<T> getCanonicalRecordConstructor(Class<T> raw) {
/* 141 */     return RECORD_HELPER.getCanonicalRecordConstructor(raw);
/*     */   }
/*     */ 
/*     */   
/*     */   public static RuntimeException createExceptionForUnexpectedIllegalAccess(IllegalAccessException exception) {
/* 146 */     throw new RuntimeException("Unexpected IllegalAccessException occurred (Gson 2.10). Certain ReflectionAccessFilter features require Java >= 9 to work correctly. If you are not using ReflectionAccessFilter, report this to the Gson maintainers.", exception);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static RuntimeException createExceptionForRecordReflectionException(ReflectiveOperationException exception) {
/* 155 */     throw new RuntimeException("Unexpected ReflectiveOperationException occurred (Gson 2.10). To support Java records, reflection is utilized to read out information about records. All these invocations happens after it is established that records exist in the JVM. This exception is unexpected behavior.", exception);
/*     */   }
/*     */ 
/*     */   
/*     */   private static abstract class RecordHelper
/*     */   {
/*     */     private RecordHelper() {}
/*     */ 
/*     */     
/*     */     abstract boolean isRecord(Class<?> param1Class);
/*     */ 
/*     */     
/*     */     abstract String[] getRecordComponentNames(Class<?> param1Class);
/*     */     
/*     */     abstract <T> Constructor<T> getCanonicalRecordConstructor(Class<T> param1Class);
/*     */     
/*     */     public abstract Method getAccessor(Class<?> param1Class, Field param1Field);
/*     */   }
/*     */   
/*     */   private static class RecordSupportedHelper
/*     */     extends RecordHelper
/*     */   {
/*     */     private final Method isRecord;
/*     */     private final Method getRecordComponents;
/*     */     private final Method getName;
/*     */     private final Method getType;
/*     */     
/*     */     private RecordSupportedHelper() throws NoSuchMethodException {
/* 183 */       this.isRecord = Class.class.getMethod("isRecord", new Class[0]);
/* 184 */       this.getRecordComponents = Class.class.getMethod("getRecordComponents", new Class[0]);
/*     */       
/* 186 */       Class<?> classRecordComponent = this.getRecordComponents.getReturnType().getComponentType();
/* 187 */       this.getName = classRecordComponent.getMethod("getName", new Class[0]);
/* 188 */       this.getType = classRecordComponent.getMethod("getType", new Class[0]);
/*     */     }
/*     */ 
/*     */     
/*     */     boolean isRecord(Class<?> raw) {
/*     */       try {
/* 194 */         return ((Boolean)this.isRecord.invoke(raw, new Object[0])).booleanValue();
/* 195 */       } catch (ReflectiveOperationException e) {
/* 196 */         throw ReflectionHelper.createExceptionForRecordReflectionException(e);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     String[] getRecordComponentNames(Class<?> raw) {
/*     */       try {
/* 203 */         Object[] recordComponents = (Object[])this.getRecordComponents.invoke(raw, new Object[0]);
/* 204 */         String[] componentNames = new String[recordComponents.length];
/* 205 */         for (int i = 0; i < recordComponents.length; i++) {
/* 206 */           componentNames[i] = (String)this.getName.invoke(recordComponents[i], new Object[0]);
/*     */         }
/* 208 */         return componentNames;
/* 209 */       } catch (ReflectiveOperationException e) {
/* 210 */         throw ReflectionHelper.createExceptionForRecordReflectionException(e);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public <T> Constructor<T> getCanonicalRecordConstructor(Class<T> raw) {
/*     */       try {
/* 217 */         Object[] recordComponents = (Object[])this.getRecordComponents.invoke(raw, new Object[0]);
/* 218 */         Class<?>[] recordComponentTypes = new Class[recordComponents.length];
/* 219 */         for (int i = 0; i < recordComponents.length; i++) {
/* 220 */           recordComponentTypes[i] = (Class)this.getType.invoke(recordComponents[i], new Object[0]);
/*     */         }
/*     */ 
/*     */         
/* 224 */         return raw.getDeclaredConstructor(recordComponentTypes);
/* 225 */       } catch (ReflectiveOperationException e) {
/* 226 */         throw ReflectionHelper.createExceptionForRecordReflectionException(e);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Method getAccessor(Class<?> raw, Field field) {
/*     */       try {
/* 235 */         return raw.getMethod(field.getName(), new Class[0]);
/* 236 */       } catch (ReflectiveOperationException e) {
/* 237 */         throw ReflectionHelper.createExceptionForRecordReflectionException(e);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static class RecordNotSupportedHelper
/*     */     extends RecordHelper
/*     */   {
/*     */     private RecordNotSupportedHelper() {}
/*     */     
/*     */     boolean isRecord(Class<?> clazz) {
/* 249 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     String[] getRecordComponentNames(Class<?> clazz) {
/* 254 */       throw new UnsupportedOperationException("Records are not supported on this JVM, this method should not be called");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     <T> Constructor<T> getCanonicalRecordConstructor(Class<T> raw) {
/* 260 */       throw new UnsupportedOperationException("Records are not supported on this JVM, this method should not be called");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Method getAccessor(Class<?> raw, Field field) {
/* 266 */       throw new UnsupportedOperationException("Records are not supported on this JVM, this method should not be called");
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\com\google\gson\internal\reflect\ReflectionHelper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */