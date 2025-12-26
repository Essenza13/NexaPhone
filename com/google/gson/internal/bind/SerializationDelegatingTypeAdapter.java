package com.google.gson.internal.bind;

import com.google.gson.TypeAdapter;

public abstract class SerializationDelegatingTypeAdapter<T> extends TypeAdapter<T> {
  public abstract TypeAdapter<T> getSerializationDelegate();
}


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\com\google\gson\internal\bind\SerializationDelegatingTypeAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */