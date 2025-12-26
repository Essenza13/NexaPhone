package com.google.gson;

import com.google.gson.stream.JsonReader;
import java.io.IOException;

public interface ToNumberStrategy {
  Number readNumber(JsonReader paramJsonReader) throws IOException;
}


/* Location:              C:\Users\.essenza\Downloads\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\RealityPhone-1.0-SNAPSHOT.jar!\com\google\gson\ToNumberStrategy.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */