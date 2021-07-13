package com.example.register;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@SuppressWarnings("UnusedReturnValue")
@SuppressLint("ApplySharedPref")
public class Register implements SharedPreferences {
   
   private final SharedPreferences        mSharedPreferences;
   private final Editor editor;
   private final Gson                     gson;
   
   public static Register getRegister(Context context, String name){
      
      return new Register(context, name);
   }
   
   public Register(Context context) {
      
      this(context, "main_pref");
   }
   
   @SuppressLint("CommitPrefEdits")
   public Register(@NonNull final Context context, @NonNull final String prefName) {
      
      mSharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
      editor             = mSharedPreferences.edit();
      gson               = new Gson();
   }
   
   public void apply() {
      
      editor.apply();
   }
   
   public void commit() {
      
      editor.commit();
   }
   
   public Register putInt(String key, int value) {
      
      editor.putInt(key, value);
      return this;
   }
   
   @Override
   public int getInt(String key, int defaultValue) {
      
      return mSharedPreferences.getInt(key, defaultValue);
   }
   
   public Register putBoolean(String key, boolean value) {
      
      editor.putBoolean(key, value);
      return this;
   }
   
   public Register remove(String key) {
      
      editor.remove(key);
      return this;
   }
   
   public Register clear() {
      
      editor.clear();
      return this;
   }
   
   @Override
   public boolean getBoolean(String key, boolean defaultValue) {
      
      return mSharedPreferences.getBoolean(key, defaultValue);
   }
   
   @Override
   public boolean contains(String key) {
      
      return mSharedPreferences.contains(key);
   }
   
   @Override
   public Editor edit() {
      
      return editor;
   }
   
   public Register putFloat(String key, float value) {
      
      editor.putFloat(key, value);
      return this;
   }
   
   @Override
   public float getFloat(String key, float defaultValue) {
      
      return mSharedPreferences.getFloat(key, defaultValue);
   }
   
   public Register putLong(String key, long value) {
      
      editor.putLong(key, value);
      return this;
   }
   
   @Override
   public long getLong(String key, long defaultValue) {
      
      return mSharedPreferences.getLong(key, defaultValue);
   }
   
   public Register putString(String key, String value) {
      
      editor.putString(key, value);
      return this;
   }
   
   public Register putStringSet(String key, Set<String> values) {
      
      editor.putStringSet(key, values);
      return this;
   }
   
   @Override
   public String getString(String key, String defaultValue) {
      
      return mSharedPreferences.getString(key, defaultValue);
   }
   
   @Override
   @Nullable
   public Set<String> getStringSet(String key, @Nullable Set<String> defValues) {
      
      return mSharedPreferences.getStringSet(key, defValues);
   }
   
   public <T> Editor putObject(String key, T object) {
      
      String objectString = gson.toJson(object);
      editor.putString(key, objectString);
      return editor;
   }
   
   public <T> T getObject(String key, Class<T> classType) {
      
      String objectString = mSharedPreferences.getString(key, null);
      
      if (objectString != null) {
         
         return gson.fromJson(objectString, classType);
      }
      
      return null;
   }
   
   public <T> Editor putObjectsList(String key, List<T> objectList) {
      
      String objectString = gson.toJson(objectList);
      editor.putString(key, objectString);
      return editor;
   }
   
   public <T> List<T> getObjectsList(String key, Class<T> classType) {
      
      String objectString = mSharedPreferences.getString(key, null);
      
      if (objectString != null) {
         
         List<T> t = gson.fromJson(objectString, new TypeToken<ArrayList<T>>() {}.getType());
         
         List<T> finalList = new ArrayList<>();
         
         for (int i = 0; i < t.size(); i++) {
            String s = String.valueOf(t.get(i));
            finalList.add(gson.fromJson(s, classType));
         }
         
         return finalList;
      }
      
      return new ArrayList<>();
   }
   
   public void clearSession() {
      
      editor.clear();
      editor.commit();
   }
   
   public boolean deleteValue(String key) {
      
      if (isKeyExists(key)) {
         
         editor.remove(key);
         editor.commit();
         return true;
      }
      
      return false;
   }
   
   public boolean isKeyExists(String key) {
      
      Map<String, ?> map = mSharedPreferences.getAll();
      return map.containsKey(key);
   }
   
   @Override
   public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
      
      mSharedPreferences.registerOnSharedPreferenceChangeListener(listener);
   }
   
   @Override
   public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
      
      mSharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
   }
   
   @Override
   public Map<String, ?> getAll() {
      
      return mSharedPreferences.getAll();
   }
   
   public Set<String> keys() {
      
      return mSharedPreferences.getAll().keySet();
   }
}
