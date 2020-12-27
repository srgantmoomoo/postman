package me.srgantmoomoo.postman.settings;

import me.srgantmoomoo.postman.Main;
import me.srgantmoomoo.postman.module.Module;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

public class NumberSetting extends Setting implements com.lukflug.panelstudio.settings.NumberSetting {
  public double value;
  
  public double minimun;
  
  public double maximum;
  
  public double increment;
  
  public NumberSetting(String name, Module parent, double value, double minimun, double maximum, double increment) {
    this.name = name;
    this.parent = parent;
    this.value = value;
    this.minimun = minimun;
    this.maximum = maximum;
    this.increment = increment;
  }
  
  public double getValue() {
    return this.value;
  }
  
  public void setValue(double value) {
    double precision = 1.0D / this.increment;
    //this.value = value;
    this.value = Math.round(Math.max(this.minimun, Math.min(this.maximum, value)) * precision) / precision;
    
    if(Main.saveLoad != null) {
		Main.saveLoad.save();
	}
  }
  
  public void increment(boolean positive) {
    setValue(getValue() + (positive ? 1 : -1) * increment);
  }
  
  public double getMinimun() {
    return this.minimun;
  }
  
  public void setMinimun(double minimun) {
    this.minimun = minimun;
  }
  
  public double getMaximum() {
    return this.maximum;
  }
  
  public void setMaximum(double maximum) {
    this.maximum = maximum;
  }
  
  public double getIncrement() {
    return this.increment;
  }
  
  public void setIncrement(double increment) {
    this.increment = increment;
  }

@Override
public double getMaximumValue() {
	 return this.maximum;
}

@Override
public double getMinimumValue() {
	return this.minimun;
}

@Override
public double getNumber() {
    return this.value;
}

@Override
public int getPrecision() {
	return 1;
}

@Override
public void setNumber(double value) {
	 double precision = 1.0D / this.increment;
	 this.value = Math.round(Math.max(this.minimun, Math.min(this.maximum, value)) * precision) / precision;
}
}
